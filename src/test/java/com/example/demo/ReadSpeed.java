package com.example.demo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.telenav.modules.mapping.graph.Edge;
import com.telenav.modules.mapping.graph.Graph;
import com.telenav.tdk.framework.modules.logging.Logger;
import com.telenav.tdk.framework.modules.logging.LoggerFactory;
import com.telenav.tdk.framework.utilities.filesystem.File;

public class ReadSpeed
{
	/**
	 * 读取远程文件-
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

	public static String initMapEdge()
	{
		final Map<Long, Object> map = new HashMap<Long, Object>();
		final Logger logger = LoggerFactory.newLogger();
		final File file = new File("D:\\files and word\\NT-ANZ.graph");
		final Graph graph = Graph.forGraphResource(file, logger);
		final List<Edge> list = graph.edges().asList();
		String str = "";
		for (final Edge edge : graph.edges().asList())
		{

			str += edge.getIdentifierAsLong();

		}
		return str;
	}

	@Test
	public void dateTest() throws ParseException
	{
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final String f = "2018-07-28 00:05:00";
		final Date date = sdf.parse(f);

		System.out.println(format.format(date));
	}

	/**
	 * 从远程服务器获取指定某一天的速度
	 *
	 * @throws Exception
	 */

	@Test
	public void GetSpeed() throws Exception
	{

		final String str = "1143235433,1143235432,1143236156,1143236155,1143235434,131840973,131840969,1143235431,131840949,-131840949,1143235430,1143143930,-1143143930,839598776,839597742,839597739,839597738,839597743,839598775,839598774,839598773,1143143929,-1143143929,1143235429,1143235428,131840755,131840900,-131840900,718014591,-718014591,736126635,-736126635,792712036,792712041,819732880,819732879,-1143959518,840105912,832535886,-840105912,1143959518,840105908,-840105908,840105907,-840105907,1143618879,1143618880,-1143618880,-1143618879,778658984,1464777823,-1464777823,1143959517,-1143959517,-841861681,-1060590595,841861682,-841861682,841861681,1060590595,1464741089,-1464741089,1464741088,-1464741088,-998678295,998678295,1143248791,-1143248791,1143248789,1143248790,-1143248790,-1143248789,131720344,-131720344,716802019,-716802019,820482258,735441195,-735441195,131726521,-131726521,131726111,131725786,-131726111,-131725786,131726637,-131726637,131726542,131726520,131726648,-131726648,131724882,131724046,-131724046,131723902,-131723902,-131723119,131723042,-131723042,131723119,131722569,131722527,716829571,-716829571,131721391,-131721391";

		final List<String> list = getTime();
		String url = "";
		final List<String> result = new ArrayList<String>();
		for (int i = 0; i < list.size(); i++)
		{
			url = "http://10.189.103.146:8080/traffic-service/maps/v4/ngx-traffic-ids/json?map_source=here&traffic_source=here&authorized_region=ANZ&type=flow,incident&locale=en&time=";
			url += list.get(i);
			result.add(
					"{" + "\"time\":" + "\"" + list.get(i) + "\"" + "," + getFlowReport(url, str));
		}
		final String fileName = "D:\\QMDownload\\data-realTime\\2018-07-28\\2018-07-29.txt";
		WriteFile(fileName, result.toString());
	}

	/**
	 * 一天内的所有时间
	 *
	 * @return
	 * @throws ParseException
	 */
	public List<String> getTime() throws ParseException
	{
		final List<String> list = new ArrayList<String>();
		final SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'");
		final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		final String f = " 2018-07-28 00:05:00 ";

		String str;

		// final Date date;

		for (int i = 1; i <= 283; i++)
		{
			str = "2018-07-29";
			final int hour = (i * 5) / 60;
			final int minute = (i * 5) % 60;
			if (hour < 10)
			{
				str += " 0" + hour + ":";
			}
			else
			{
				str += " " + hour + ":";
			}
			if (minute < 10)
			{
				str += "0" + minute + ":00";
			}
			else
			{
				str += minute + ":00";
			}

			// date = new Date(str);
			System.out.println(format.format(sdf.parse(str)));
			list.add(format.format(sdf.parse(str)));

		}
		return list;
	}

	/**
	 * 将得到的写入txt文件
	 *
	 * @param newFile
	 * @param result
	 */
	public void WriteFile(final String newFile, final String result)
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
