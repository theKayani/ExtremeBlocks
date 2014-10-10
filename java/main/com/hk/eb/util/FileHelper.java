package main.com.hk.eb.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.zip.ZipException;
import net.minecraft.nbt.CompressedStreamTools;
import net.minecraft.nbt.NBTTagCompound;

/**
 * Helper when it comes to files.
 * 
 * <p>
 * This Class contains methods that process many different operations that help
 * you handle files that you might see as a huge obstacle much like Writing to a
 * file. This is fixed by the method
 * {@link #writeToFile(String, String, boolean)} that writes to the given file
 * for you. Which makes life much easier! YAY!
 * 
 * <p>
 * The Methods in this class are as follows:
 * 
 * <li> {@link #createFile(String)}
 * <li> {@link #createFile(String, boolean)}
 * <li> {@link #deleteDirectory(File)}
 * <li> {@link #deleteFile(String)}
 * <li> {@link #getFileReader(String)}
 * <li> {@link #resetFile(String)}
 * <li> {@link #resetFile(String, String)}
 * <li> {@link #writeToFile(String, String)}
 * <li> {@link #writeToFile(String, String, boolean)}
 * <li> {@link #getFilesInFolder(File)}
 * 
 * @see java.nio.file.Files
 */
public class FileHelper
{
	/**
	 * Create a file by default (Not a directory)
	 * 
	 * @param dir
	 *            The path for the file
	 * @return True: If the file was created
	 */
	public static boolean createFile(String dir)
	{
		return FileHelper.createFile(dir, false);
	}

	/**
	 * Create a file or a directory by default
	 * 
	 * @param dir
	 *            The path for the file
	 * @param isDirectory
	 *            Is is a directory of a file
	 * @return True: If the file was created
	 */
	public static boolean createFile(String dir, boolean isDirectory)
	{
		boolean returning = false;

		Path p = Paths.get(dir);
		try
		{
			if (Files.exists(p))
			{
				returning = true;
			}
			else if (isDirectory)
			{
				Files.createDirectory(p);
				returning = true;
			}
			else
			{
				Files.createFile(p);
				returning = true;
			}
		}
		catch (IOException e)
		{
			System.err.println("Error Creating File!");
			System.err.println("Path: " + dir);
			System.err.println("Directory: " + isDirectory);
			e.printStackTrace();
		}
		return returning;
	}

	/**
	 * Deletes all of the files and directories in the given directory.
	 * 
	 * @param dir
	 *            The directory you would like to delete
	 * @return True: If the Directory and all of it's content were deleted
	 */
	public static boolean deleteDirectory(File dir)
	{
		if (dir == null || !dir.exists() || !dir.isDirectory()) throw new IllegalArgumentException("'dir' Must exist and Must be a directory!");

		String[] files = dir.list();

		for (String file : files)
		{
			File f = new File(dir, file);

			if (f.isDirectory())
			{
				deleteDirectory(f);
			}
			else
			{
				f.delete();
			}
		}
		return dir.delete();
	}

	/**
	 * Deletes the given file
	 * 
	 * @param fileName
	 *            The path for the file
	 * @return True: If the file was successfully deleted
	 */
	public static boolean deleteFile(String fileName)
	{
		Path p = Paths.get(fileName);

		try
		{
			return Files.deleteIfExists(p);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return false;
	}

	private static ArrayList<File> files(File dir)
	{
		ArrayList<File> files = new ArrayList<File>();

		if (!dir.isDirectory()) throw new IllegalArgumentException("dir Isn't a Directory! " + dir);

		for (int i = 0; i < dir.listFiles().length; i++)
		{
			if (dir.listFiles()[i].isDirectory())
			{
				files.addAll(files(dir.listFiles()[i]));
			}
			files.add(dir.listFiles()[i]);
		}

		return files;
	}

	/**
	 * Retrieves all the lines of a file and neatly puts them into an array!
	 * 
	 * @param fileName
	 *            The path for the file
	 * @return The Lines of the given file
	 */
	public static String[] getFileContents(String fileName)
	{
		ArrayList<String> lines = new ArrayList<String>();
		String line = "";
		BufferedReader reader = getFileReader(fileName);

		try
		{
			while ((line = reader.readLine()) != null)
			{
				lines.add(line);
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return lines.toArray(new String[0]);
	}

	/**
	 * Creates a <code>BufferedReader</code> for the given File
	 * <p>
	 * <b><i>WARNING:</i></b> CAN STILL, VERY EASILY CAUSE AN
	 * {@link IOException}
	 * <p>
	 * Recommended you don't use this and use {@link #getFileContents(String)}
	 * instead!
	 * 
	 * @param fileName
	 *            The path for the file
	 * @return The given file's <code>BufferedReader</code>
	 */
	public static BufferedReader getFileReader(String fileName)
	{
		Charset c = Charset.forName("US-ASCII");
		Path p = Paths.get(fileName);

		try
		{
			return Files.newBufferedReader(p, c);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Returns all the Files in the specified directory and all sub-directories.
	 * 
	 * <p>
	 * For instance, If you have a folder, /Files/Documents/Maps, and call this
	 * method for Hello. It will return all the files in Documents and all the
	 * files in Maps!
	 * 
	 * @param directory
	 *            The directory to check.
	 * @return All the files in the folder and sub folders
	 */
	public static File[] getFilesInFolder(File dir)
	{
		return files(dir).toArray(new File[0]);
	}

	/**
	 * Prints the files lines to the console
	 * 
	 * @param fileName
	 *            The path for the file
	 */
	public static void printFileContents(String fileName)
	{
		String[] lines = getFileContents(fileName);

		for (int i = 0; i < lines.length; i++)
		{
			System.out.println("Line[" + i + "]: " + lines[i]);
		}
	}

	/**
	 * Deletes the given file and creates a new one with no content
	 * 
	 * @param fileName
	 *            The path for the file
	 * @return A Path to the given File
	 */
	public static Path resetFile(String fileName)
	{
		return resetFile(fileName, "");
	}

	/**
	 * Deletes the given file and creates a new one with the given text
	 * 
	 * @param fileName
	 *            The path for the file
	 * @param textToAdd
	 *            Any text you would like to add to the new file
	 * @return A Path to the given File
	 */
	public static Path resetFile(String fileName, String textToAdd)
	{
		Path p = Paths.get(fileName);

		deleteFile(fileName);
		createFile(fileName, false);
		FileHelper.writeToFile(fileName, textToAdd, false);

		return p;
	}

	/**
	 * Writes the given string to the given File with a new line afterwards
	 * 
	 * @param fileName
	 *            The path for the file
	 * @param stuff
	 *            The String you want to write to the given file
	 * @return True: if the String was written to the file
	 */
	public static boolean writeToFile(String fileName, String stuff)
	{
		return FileHelper.writeToFile(fileName, stuff, true);
	}

	/**
	 * Writes the given string to the given File with
	 * 
	 * @param fileName
	 *            The path for the file
	 * @param stuff
	 *            The String you want to write to the given file
	 * @param newLine
	 *            If you want a '\n' character after the 'stuff' parameter
	 * @return True: if the String was written to the file
	 */
	public static boolean writeToFile(String fileName, String stuff, boolean newLine)
	{
		BufferedWriter writer = null;
		try
		{
			writer = new BufferedWriter(new FileWriter(fileName, true));
			writer.write(stuff);
			if (newLine)
			{
				writer.newLine();
			}
			return true;
		}
		catch (IOException x)
		{
			System.err.format("IOException: %s%n", x);
			x.printStackTrace();
			return false;
		}
		finally
		{
			try
			{
				writer.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/**
	 * Read from the specified file with a new NBTTagCompound and return said
	 * NBT
	 * 
	 * @param file
	 *            the file to be read
	 * @return the newly constructed NBTTagCompound
	 * @throws IOException
	 */
	public static NBTTagCompound readNBT(File file) throws IOException
	{
		if (!file.exists()) return null;
		FileInputStream in = new FileInputStream(file);
		NBTTagCompound tag;
		try
		{
			tag = CompressedStreamTools.readCompressed(in);
		}
		catch (ZipException e)
		{
			if (e.getMessage().equals("Not in GZIP format"))
			{
				tag = CompressedStreamTools.read(file);
			}
			else throw e;
		}
		in.close();
		return tag;
	}

	/**
	 * Write the default NBTTagCompound to the specified file
	 * 
	 * @param tag
	 *            The tag to write
	 * @param file
	 *            the file to write to
	 * @throws IOException
	 */
	public static void writeNBT(NBTTagCompound tag, File file) throws IOException
	{
		FileOutputStream out = new FileOutputStream(file);
		CompressedStreamTools.writeCompressed(tag, out);
		out.close();
	}
}
