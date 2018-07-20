package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.example.demo.utils.HttpUtil;
import com.telenav.modules.mapping.graph.Edge;
import com.telenav.modules.mapping.graph.Graph;
import com.telenav.tdk.framework.modules.logging.Logger;
import com.telenav.tdk.framework.modules.logging.LoggerFactory;
import com.telenav.tdk.framework.utilities.filesystem.File;

public class Test
{
	public static String getFlowReport(final Long id) throws Exception
	{
		BufferedReader br = null;
		final String anzUrl = "http://10.189.103.146:8080/traffic-service/maps/v4/ngx-traffic-ids/json?map_source=here&traffic_source=here&authorized_region=ANZ&type=flow,incident&locale=en&time=2014-02-04T00:56Z";
		try
		{
			final URL url = new URL(anzUrl + "&trafficIds=" + id);
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

	public static void main(final String[] args) throws Exception
	{
		final Logger logger = LoggerFactory.newLogger();
		final File file = new File("D:\\files and word\\NT-ANZ.graph");

		final Graph graph = Graph.forGraphResource(file, logger);
		final String anzUrl = "http://10.189.103.146:8080/traffic-service/maps/v4/ngx-traffic-ids/json?map_source=here&traffic_source=here&authorized_region=ANZ&type=flow,incident&locale=en&time=2014-02-04T00:56Z";
		final int i = 0;
		for (final Edge edge : graph.edges().asList())
		{

			final Long id = edge.getIdentifierAsLong();

			final String str = HttpUtil.getFlowReport(anzUrl, id);
			System.out.println(id);
			System.out.println(str);
			//
			// str = "{" + str;
			// str = "{\"status\": 11200,\"message\": \"OK\",json:{\"status\": 11200,\"message\":
			// \"OK\"}}";
			// str = "{\"status\": {\"status\": 11200,\"message\": \"OK\"},\"dictionary\":
			// {\"level_pairs\": [{\"traffic_level_id\": 3,\"traffic_level_str\":"
			// + "\"CONGESTED\"},{\"traffic_level_id\": 1,\"traffic_level_str\":"
			// + "\"CLOSED\"},{\"traffic_level_id\": 5,\"traffic_level_str\":"
			// + "\"SLOW_SPEED\"},{\"traffic_level_id\": 7,\"traffic_level_str\":
			// \"FREE_FLOW\"}]}}";
			// final JSONObject jsStr = JSONObject.parseObject(str);
			// if (jsStr.containsKey("traffic_flow"))
			// {
			// i++;
			// final Object o = jsStr.get("traffic_flow");
			// final List strarr = (List) jsStr.get("traffic_flow");
			// // JSONObject.fromObject(strarr.get(0));
			// final JSONObject json = JSONObject.parseObject(strarr.get(0).toString());
			// System.out.println(json.get("traffic_level"));
			// // JSONObject.parseObject(strarr[0]).get("traffic_level");
			// }

			// "status": {"status": 11200,"message": "OK"},
			// "dictionary": {"level_pairs": [{"traffic_level_id": 3,"traffic_level_str":
			// "CONGESTED"},{"traffic_level_id": 1,"traffic_level_str":
			// "CLOSED"},{"traffic_level_id": 5,"traffic_level_str":
			// "SLOW_SPEED"},{"traffic_level_id": 7,"traffic_level_str": "FREE_FLOW"}]}}
			// System.out.println("经度" + edge.bounds().getBottomLeft().getLatitude().asDegrees() +
			// "纬度"
			// + edge.bounds().getBottomLeft().getLongitude().asDegrees());
			// System.out.println("bound:" + edge.bounds().getTopRight());
			// System.out.println("bound:" + edge.bounds());
			// System.out.println("id:" + edge.getIdentifierAsLong());
			// System.out.println("points:" + edge.getRoadShape().getLocations());
			// final List<Location> list = edge.getRoadShape().getLocations();
			// for (final Location lo : list)
			// {
			//
			// System.out.println(
			// new Position(lo.getLatitude().asDegrees(), lo.getLongitude().asDegrees())
			// .toString());
			// }
			// System.out.println(edge.getRoadFunctionalClass().getIdentifier());
		}
		System.out.println(i);
	}
	// public static void main(final String[] args)
	// {
	// final Position p1 = new Position(1, 1);
	// final Position p2 = new Position(2, 2);
	// final Position p3 = new Position(3, 3);
	// final Position p4 = new Position(4, 4);
	// RTree<Position, Geometry> tree = RTree.create();
	// tree = tree.add(p1, Geometries.rectangle(0, 0, 2, 2))
	// .add(p2, Geometries.rectangle(3, 3, 5, 5)).add(p3, Geometries.rectangle(1, 1, 6, 6))
	// .add(p4, Geometries.rectangle(4, 4, 7, 7));
	//
	// final List<Entry<Position, Geometry>> list = tree
	// .search(Geometries.rectangle(0.5, 0.5, 1, 1)).toList().toBlocking().single();
	// for (final Entry<Position, Geometry> entry : list)
	// {
	// System.out.println(entry.value());
	// }
	// }
}
