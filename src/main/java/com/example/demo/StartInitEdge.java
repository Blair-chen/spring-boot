package com.example.demo;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.telenav.modules.mapping.graph.Edge;
import com.telenav.modules.mapping.graph.Graph;
import com.telenav.tdk.framework.modules.logging.Logger;
import com.telenav.tdk.framework.modules.logging.LoggerFactory;
import com.telenav.tdk.framework.utilities.filesystem.File;

/**
 * 启动时开启一个线程加载graph文件
 *
 * @author qiongchen
 */
@Component
@Order(value = 1)
public class StartInitEdge implements CommandLineRunner
{
	public static Map<Long, Edge> levelZero = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> levelOne = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> levelThree = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> levelFour = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> levelTwo = new Hashtable<Long, Edge>();

	public void initEdgeToMap(final int level, final Edge edge)
	{
		switch (level)
		{
			case 0:
				levelZero.put(edge.getIdentifierAsLong(), edge);
				break;
			case 1:
				levelOne.put(edge.getIdentifierAsLong(), edge);
				break;
			case 2:
				levelTwo.put(edge.getIdentifierAsLong(), edge);
				break;
			case 3:
				levelThree.put(edge.getIdentifierAsLong(), edge);
				break;
			case 4:
				levelFour.put(edge.getIdentifierAsLong(), edge);
				break;

		}
	}

	@Override
	public void run(final String... args) throws Exception
	{
		final Logger logger = LoggerFactory.newLogger();
		final File file = new File("D:\\files and word\\NT-ANZ.graph");
		final Graph graph = Graph.forGraphResource(file, logger);
		final List<Edge> list = graph.edges().asList();
		for (int i = 0; i < list.size(); i++)
		{
			initEdgeToMap(list.get(i).getRoadFunctionalClass().getIdentifier(), list.get(i));
		}

	}

}
