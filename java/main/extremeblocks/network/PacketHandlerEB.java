package main.extremeblocks.network;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageCodec;
import java.util.Collections;
import java.util.Comparator;
import java.util.EnumMap;
import java.util.LinkedList;
import java.util.List;
import main.extremeblocks.network.packets.PacketAddToPlayer;
import main.extremeblocks.network.packets.PacketClearInv;
import main.extremeblocks.network.packets.PacketOpenGui;
import main.extremeblocks.network.packets.PacketRemoveEntity;
import main.extremeblocks.network.packets.PacketRemoveFromInv;
import main.extremeblocks.network.packets.PacketSyncRobot;
import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.network.INetHandler;
import net.minecraft.network.NetHandlerPlayServer;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.network.FMLEmbeddedChannel;
import cpw.mods.fml.common.network.FMLOutboundHandler;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.internal.FMLProxyPacket;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

@ChannelHandler.Sharable
public class PacketHandlerEB extends MessageToMessageCodec<FMLProxyPacket, AbstractPacket>
{
	private EnumMap<Side, FMLEmbeddedChannel> channels;
	private final LinkedList<Class<? extends AbstractPacket>> packets = new LinkedList<Class<? extends AbstractPacket>>();
	private boolean isPostInitialised = false;

	public boolean registerPacket(Class<? extends AbstractPacket> clazz)
	{
		if (packets.size() > 256) return false;
		if (packets.contains(clazz)) return false;
		if (isPostInitialised) return false;
		packets.add(clazz);
		return true;
	}

	@Override
	protected void encode(ChannelHandlerContext ctx, AbstractPacket msg, List<Object> out) throws Exception
	{
		ByteBuf buffer = Unpooled.buffer();
		Class<? extends AbstractPacket> clazz = msg.getClass();
		if (!packets.contains(msg.getClass())) throw new NullPointerException("No Packet Registered for: " + msg.getClass().getCanonicalName());
		byte discriminator = (byte) packets.indexOf(clazz);
		buffer.writeByte(discriminator);
		msg.encodeInto(buffer);
		FMLProxyPacket proxyPacket = new FMLProxyPacket(buffer.copy(), ctx.channel().attr(NetworkRegistry.FML_CHANNEL).get());
		out.add(proxyPacket);
	}

	@Override
	protected void decode(ChannelHandlerContext ctx, FMLProxyPacket msg, List<Object> out) throws Exception
	{
		ByteBuf payload = msg.payload();
		byte discriminator = payload.readByte();
		Class<? extends AbstractPacket> clazz = packets.get(discriminator);
		if (clazz == null) throw new NullPointerException("No packet registered for discriminator: " + discriminator);
		AbstractPacket pkt = clazz.newInstance();
		pkt.decodeInto(payload.slice());
		EntityPlayer player;
		switch (FMLCommonHandler.instance().getEffectiveSide())
		{
			case CLIENT:
				player = getClientPlayer();
				pkt.handleClientSide(player);
				break;
			case SERVER:
				INetHandler netHandler = ctx.channel().attr(NetworkRegistry.NET_HANDLER).get();
				player = ((NetHandlerPlayServer) netHandler).playerEntity;
				pkt.handleServerSide(player);
				break;
			default:
		}
		out.add(pkt);
	}

	public void initialise()
	{
		channels = NetworkRegistry.INSTANCE.newChannel("ExtremeBlocks", this);
		registerPackets();
	}

	public void registerPackets()
	{
		registerPacket(PacketOpenGui.class);
		registerPacket(PacketSyncRobot.class);
		registerPacket(PacketRemoveEntity.class);
		registerPacket(PacketClearInv.class);
		registerPacket(PacketRemoveFromInv.class);
		registerPacket(PacketAddToPlayer.class);
	}

	public void postInitialise()
	{
		if (isPostInitialised) return;
		isPostInitialised = true;
		Collections.sort(packets, new Comparator<Class<? extends AbstractPacket>>()
		{
			@Override
			public int compare(Class<? extends AbstractPacket> clazz1, Class<? extends AbstractPacket> clazz2)
			{
				int com = String.CASE_INSENSITIVE_ORDER.compare(clazz1.getCanonicalName(), clazz2.getCanonicalName());
				if (com == 0)
				{
					com = clazz1.getCanonicalName().compareTo(clazz2.getCanonicalName());
				}
				return com;
			}
		});
	}

	@SideOnly(Side.CLIENT)
	private EntityPlayer getClientPlayer()
	{
		return Minecraft.getMinecraft().thePlayer;
	}

	public void sendToAll(AbstractPacket message)
	{
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALL);
		channels.get(Side.SERVER).writeAndFlush(message);
	}

	public void sendTo(AbstractPacket message, EntityPlayerMP player)
	{
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.PLAYER);
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(player);
		channels.get(Side.SERVER).writeAndFlush(message);
	}

	public void sendToAllAround(AbstractPacket message, NetworkRegistry.TargetPoint point)
	{
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.ALLAROUNDPOINT);
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(point);
		channels.get(Side.SERVER).writeAndFlush(message);
	}

	public void sendToDimension(AbstractPacket message, int dimensionId)
	{
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.DIMENSION);
		channels.get(Side.SERVER).attr(FMLOutboundHandler.FML_MESSAGETARGETARGS).set(dimensionId);
		channels.get(Side.SERVER).writeAndFlush(message);
	}

	public void sendToServer(AbstractPacket message)
	{
		channels.get(Side.CLIENT).attr(FMLOutboundHandler.FML_MESSAGETARGET).set(FMLOutboundHandler.OutboundTarget.TOSERVER);
		channels.get(Side.CLIENT).writeAndFlush(message);
	}
}
