package main.extremeblocks.network;

public interface IPacketReceiver<E extends AbstractPacket>
{
	public void receivePacket(E packet);
}
