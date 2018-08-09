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

@Component
@Order(value = 2)
public class StartInitCompareEdge implements CommandLineRunner
{
	public static Map<Long, Edge> comparelevelZero = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> comparelevelOne = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> comparelevelTwo = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> comparelevelThree = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> comparelevelFour = new Hashtable<Long, Edge>();

	public void initEdgeToMap(final int level, final Edge edge)
	{
		switch (level)
		{
			case 0:
				comparelevelZero.put(edge.getIdentifierAsLong(), edge);
				break;
			case 1:
				comparelevelOne.put(edge.getIdentifierAsLong(), edge);
				break;
			case 2:
				comparelevelTwo.put(edge.getIdentifierAsLong(), edge);
				break;
			case 3:
				comparelevelThree.put(edge.getIdentifierAsLong(), edge);
				break;
			case 4:
				comparelevelFour.put(edge.getIdentifierAsLong(), edge);
				break;

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
