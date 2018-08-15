package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.telenav.modules.mapping.geography.Location;
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

			// System.out.println(edge.getRoadShape().getLocations());
			final List<Location> li = edge.getRoadShape().getLocations();
			for (int i = 0; i < list.size(); i++)
			{
				// System.out.println(li.get(i));
			}

			// .out.println(edge.getIdentifierAsLong());

		}
		System.out.println("des");
		return map;
	}

	public static void main(final String[] args) throws Exception
	{
		// System.out.println(initMapEdge());
		System.out.println(Runtime.getRuntime().maxMemory());
		System.out.println(Runtime.getRuntime().totalMemory());

	}

}
