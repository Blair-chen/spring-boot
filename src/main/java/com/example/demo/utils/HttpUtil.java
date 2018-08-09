package com.example.demo.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpUtil
{

	public static String getDtypeFlow() throws Exception
	{
		final String requesturl = "http://172.16.100.56:8080/demo/compare";
		BufferedReader br = null;
		try
		{
			final URL url = new URL(requesturl);

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
}
