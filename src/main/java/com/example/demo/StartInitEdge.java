package com.example.demo;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;
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
public class StartInitEdge implements CommandLineRunner
{
	public static Map<Long, Edge> mapZero = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> mapOne = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> mapThree = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> mapFour = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> mapTwo = new Hashtable<Long, Edge>();

	public void initEdgeToMap(final int level, final Edge edge)
	{
		switch (level)
		{
			case 0:
				mapZero.put(edge.getIdentifierAsLong(), edge);
				break;
			case 1:
				mapOne.put(edge.getIdentifierAsLong(), edge);
				break;
			case 2:
				mapTwo.put(edge.getIdentifierAsLong(), edge);
			case 3:
				mapThree.put(edge.getIdentifierAsLong(), edge);
			case 4:
				mapFour.put(edge.getIdentifierAsLong(), edge);

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
