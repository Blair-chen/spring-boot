package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;
import com.telenav.modules.mapping.graph.Edge;
import com.telenav.modules.mapping.graph.Graph;
import com.telenav.tdk.framework.modules.logging.Logger;
import com.telenav.tdk.framework.modules.logging.LoggerFactory;
import com.telenav.tdk.framework.utilities.filesystem.File;

public class Test
{
	public static String getFlowReport() throws Exception
	{
		BufferedReader br = null;
		final String anzUrl = "D:\\QMDownload\\ngx-data\\0000\\2018-02-28T000133.000Z";
		try
		{
			final URL url = new URL(anzUrl);
			final HttpURLConnection httpConnection = (HttpURLConnection) (url.openConnection());
			httpConnection.connect();
			final InputStream is = httpConnection.getInputStream();
			br = new BufferedReader(new InputStreamReader(is, "utf-8"));
			final StringBuffer sb = new StringBuffer();
			while (br.read() != -1)
			{
				sb.append(br.readLine());
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

	public static Map<Long, Object> initMapEdge()
	{
		final Map<Long, Object> map = new HashMap<Long, Object>();
		final Logger logger = LoggerFactory.newLogger();
		final File file = new File("D:\\files and word\\NT-CN.graph");
		final Graph graph = Graph.forGraphResource(file, logger);
		final List<Edge> list = graph.edges().asList();
		final String str = "";
		for (final Edge edge : graph.edges().asList())
		{

			System.out.println(edge.getIdentifierAsLong());

		}
		return map;
	}

	public static void insertSpeed() throws Exception
	{
		final Map<Long, Object> map = initMapEdge();
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		final String s = "2018-02-28 00:01:33";
		final Date date = format.parse(s);
		final String str = readFileByLines(
				"D:\\QMDownload\\data-realTime\\2018-07-01\\detail-data\\2018-07-01-000113\\2018-07-01-000113.txt");
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
							if (map.containsKey(Long.parseLong(arr[i].toString())))
							{
								System.out.println(arr[i]);
							}
						}
					}
					else
					{
						if (map.containsKey(Long.parseLong(json.get("traffic_id").toString())))
						{
							System.out.println(json.get("traffic_id"));
						}

					}

				}
			}
		}
	}

	public static void main(final String[] args) throws Exception
	{
		final Map<Long, Object> map = initMapEdge();

	}

	public static String readFileByLines(final String fileName)
	{
		String str = "";
		final File file = new File(fileName);
		BufferedReader reader = null;
		try
		{

			reader = new BufferedReader(new FileReader(fileName));
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
