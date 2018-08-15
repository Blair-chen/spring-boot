package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil
{
	public static String getDtypeFlow() throws Exception
	{
		final String requesturl = "http://172.16.100.56:8080/demo/compare";
		BufferedReader br = null;
		InputStream is = null;
		try
		{
			final URL url = new URL(requesturl);

			final HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
			httpConnection.connect();
			is = httpConnection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is, "utf-8"));
			final StringBuffer sb = new StringBuffer();
			final int buffer_size = 1024;
			final char[] buffer = new char[buffer_size];
			int charRead = 0;
			while ((charRead = br.read(buffer, 0, buffer_size)) != -1)
			{
				sb.append(buffer, 0, charRead);
			}
			final String content = new String(sb);

			br.close();
			br = null;
			is.close();
			return content;
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (br != null)
			{
				br.close();
				is.close();
			}
		}
		return null;
	}

	/**
	 * get traffic flow
	 *
	 * @param requesturl
	 * @param id
	 * @return
	 * @throws Exception
	 */
	public static String getFlowReport(final String requesturl, final String id) throws Exception
	{
		BufferedReader br = null;
		try
		{
			final URL url = new URL(requesturl + "&trafficIds=" + id);

			final HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
			httpConnection.connect();
			final InputStream is = httpConnection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is, "utf-8"));
			final StringBuffer sb = new StringBuffer();
			String str = null;
			while ((str = br.readLine()) != null)
			{
				sb.append(str);
			}
			final String content = new String(sb);
			br.close();
			br = null;
			return content;
		}
		catch (final Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (br != null)
			{
				br.close();
			}
		}
		return null;
	}

	public static String readFileByLines(final String fileName)
	{
		String str = "";
		final File file = new File(fileName);
		BufferedReader reader = null;
		try
		{

			reader = new BufferedReader(new FileReader(file));
			String tempString = null;
			int line = 1;

			while ((tempString = reader.readLine()) != null)
			{
				// 显示行号
				// System.out.println("line " + line + ": " + tempString);
				str += tempString;
				line++;
			}
			reader.close();

		}
		catch (final IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (reader != null)
			{
				try
				{
					reader.close();

				}
				catch (final IOException e1)
				{
				}
			}
		}
		return str;
	}

	/**
	 * 将得到的写入txt文件
	 *
	 * @param newFile
	 * @param result
	 */
	public static void WriteFile(final String newFile, final String result)
	{
		Writer writer = null;
		BufferedWriter bufferedWriter = null;
		try
		{
			final String str = "这是写入的文件";
			writer = new FileWriter(newFile);
			bufferedWriter = new BufferedWriter(writer);
			bufferedWriter.write(result);
			bufferedWriter.flush();
			writer.flush();
		}
		catch (final IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
			try
			{
				bufferedWriter.close();
				writer.close();
			}
			catch (final IOException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}
