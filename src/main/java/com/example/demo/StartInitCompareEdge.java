package com.example.demo;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;

import com.telenav.modules.mapping.graph.Edge;
import com.telenav.modules.mapping.graph.Graph;
import com.telenav.tdk.framework.modules.logging.Logger;
import com.telenav.tdk.framework.modules.logging.LoggerFactory;
import com.telenav.tdk.framework.utilities.filesystem.File;

public class StartInitCompareEdge implements CommandLineRunner
{
	public static Map<Long, Edge> compareMapZero = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> compareMapOne = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> compareMapTwo = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> compareMapThree = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> compareMapFour = new Hashtable<Long, Edge>();

	public void initEdgeToMap(final int level, final Edge edge)
	{
		switch (level)
		{
			case 0:
				compareMapZero.put(edge.getIdentifierAsLong(), edge);
				break;
			case 1:
				compareMapOne.put(edge.getIdentifierAsLong(), edge);
				break;
			case 2:
				compareMapTwo.put(edge.getIdentifierAsLong(), edge);
			case 3:
				compareMapThree.put(edge.getIdentifierAsLong(), edge);
			case 4:
				compareMapFour.put(edge.getIdentifierAsLong(), edge);

		}
	}

	@Override
	public void run(final String... args) throws Exception
	{
		final Logger logger = LoggerFactory.newLogger();
		final File file = new File("D:\\files and word\\NT-CN.graph");
		final Graph graph = Graph.forGraphResource(file, logger);
		final List<Edge> list = graph.edges().asList();
		for (int i = 0; i < list.size(); i++)
		{
			initEdgeToMap(list.get(i).getRoadFunctionalClass().getIdentifier(), list.get(i));
		}

	}

}
