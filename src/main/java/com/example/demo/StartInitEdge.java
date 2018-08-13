package com.example.demo;

import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import org.springframework.boot.CommandLineRunner;

import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;
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

public class StartInitEdge implements CommandLineRunner
{
	public static Map<Long, Edge> levelZero = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> levelOne = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> levelThree = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> levelFour = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> levelTwo = new Hashtable<Long, Edge>();

	public static RTree<Edge, Geometry> levelZerotree = RTree.create();
	public static RTree<Edge, Geometry> levelOnetree = RTree.create();
	public static RTree<Edge, Geometry> levelThreetree = RTree.create();
	public static RTree<Edge, Geometry> levelFourtree = RTree.create();
	public static RTree<Edge, Geometry> levelTwotree = RTree.create();

	public void initEdgeToMap(final int level, final Edge edge)
	{
		switch (level)
		{
			case 0:
				levelZerotree = levelZerotree.add(edge,
						Geometries.rectangle(
								edge.bounds().getBottomLeft().getLatitude().asDegrees(),
								edge.bounds().getBottomLeft().getLongitude().asDegrees(),
								edge.bounds().getTopRight().getLatitude().asDegrees(),
								edge.bounds().getTopRight().getLongitude().asDegrees()));
				levelZero.put(edge.getIdentifierAsLong(), edge);
				break;
			case 1:
				levelZerotree = levelZerotree.add(edge,
						Geometries.rectangle(
								edge.bounds().getBottomLeft().getLatitude().asDegrees(),
								edge.bounds().getBottomLeft().getLongitude().asDegrees(),
								edge.bounds().getTopRight().getLatitude().asDegrees(),
								edge.bounds().getTopRight().getLongitude().asDegrees()));
				levelOne.put(edge.getIdentifierAsLong(), edge);
				break;
			case 2:
				levelTwotree = levelTwotree.add(edge,
						Geometries.rectangle(
								edge.bounds().getBottomLeft().getLatitude().asDegrees(),
								edge.bounds().getBottomLeft().getLongitude().asDegrees(),
								edge.bounds().getTopRight().getLatitude().asDegrees(),
								edge.bounds().getTopRight().getLongitude().asDegrees()));
				levelTwo.put(edge.getIdentifierAsLong(), edge);
				break;
			case 3:
				levelThreetree = levelThreetree.add(edge,
						Geometries.rectangle(
								edge.bounds().getBottomLeft().getLatitude().asDegrees(),
								edge.bounds().getBottomLeft().getLongitude().asDegrees(),
								edge.bounds().getTopRight().getLatitude().asDegrees(),
								edge.bounds().getTopRight().getLongitude().asDegrees()));
				levelThree.put(edge.getIdentifierAsLong(), edge);
				break;
			case 4:
				levelFourtree = levelFourtree.add(edge,
						Geometries.rectangle(
								edge.bounds().getBottomLeft().getLatitude().asDegrees(),
								edge.bounds().getBottomLeft().getLongitude().asDegrees(),
								edge.bounds().getTopRight().getLatitude().asDegrees(),
								edge.bounds().getTopRight().getLongitude().asDegrees()));
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
		System.out.println("search 的五棵树创建好啦");

	}

}
