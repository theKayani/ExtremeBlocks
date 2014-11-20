package main.extremeblocks;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class VersionChecker
{
	public static final String WEBSITE = "https://raw.githubusercontent.com/Fir3will/ExtremeBlocks/master/version.txt";
	private static String message;
	private static String newVersion;

	public static boolean shouldUpdate()
	{
		String location = WEBSITE;
		HttpURLConnection conn = null;
		try
		{
			while (location != null && !location.isEmpty())
			{
				URL url = new URL(location);

				if (conn != null)
				{
					conn.disconnect();
				}

				conn = (HttpURLConnection) url.openConnection();
				conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows; U; Windows NT 6.0; ru; rv:1.9.0.11) Gecko/2009060215 Firefox/3.0.11 (.NET CLR 3.5.30729)");
				conn.connect();
				location = conn.getHeaderField("Location");
			}
			if (conn == null) throw new NullPointerException("conn == null");
			BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));

			String line;
			while ((line = reader.readLine()) != null)
			{
				if (line.startsWith("Version: "))
				{
					if (!Init.VERSION.equals(line.replaceAll("Version: ", "")))
					{
						newVersion = line.replaceAll("Version: ", "");
					}
					else return false;
				}
				if (line.startsWith("Summary Message: "))
				{
					message = line.replaceAll("Summary Message: ", "");
				}
			}

			Vars.logger.warn("Found new Version for Extreme Blocks. Current: " + Init.VERSION + ", New: " + newVersion);
		}
		catch (IOException e)
		{
			System.err.println("ERROR CHECKING FOR EB VERSION!");
		}
		return newVersion != null && message != null;
	}

	public static String getMessage()
	{
		return message == null ? "Unable to get Message" : message;
	}

	public static String getNewVersion()
	{
		return newVersion == null ? Init.VERSION : newVersion;
	}
}
