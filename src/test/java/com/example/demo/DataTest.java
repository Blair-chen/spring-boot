package com.example.demo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

public class DataTest
{
	public static long getFlowReport()
	{

		long counts = 0;
		final File file = new File("D:\\QMDownload\\ngx-data\\0000\\2018-02-28T000133.000Z.txt");

		try
		{
			final FileInputStream fis = new FileInputStream(file);
			final FileChannel fc = fis.getChannel();
			final ByteBuffer bbuf = ByteBuffer.allocate(2048);
			int offset = 0;
			while ((offset = fc.read(bbuf)) != -1)
			{
				counts = counts + offset;
				bbuf.clear();
			}
			fc.close();
			fis.close();
		}
		catch (final IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return counts;
	}

	public static void main(final String[] args) throws Exception
	{
		final String str = readFileByLines(
				"D:\\files and word\\speed-data\\0000\\2018-02-28T000133.000Z.txt");
		final JSONObject jsonObject = JSONObject.parseObject(str);
		if (jsonObject.containsKey("traffic_flow"))
		{
			String traffic_id_str = "";
			String[] arr;
			JSONObject json = null;
			final List strarr = (List) jsonObject.get("traffic_flow");
			for (final Object obj : strarr)
			{
				json = JSONObject.parseObject(obj.toString());
				if (json.containsKey("traffic_id"))
				{
					traffic_id_str = json.get("traffic_id").toString();
					if (traffic_id_str.contains(","))
					{
						arr = traffic_id_str.split(",");
						for (int i = 0; i < arr.length; i++)
						{

						}
					}

				}
			}
		}
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
}
