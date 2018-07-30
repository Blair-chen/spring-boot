package com.example.demo.controller;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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

	public static void main(final String[] args) throws Exception
	{
		System.out.println(getFlowReport());
	}

}
