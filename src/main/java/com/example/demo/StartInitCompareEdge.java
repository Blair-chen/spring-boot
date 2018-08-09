package com.example.demo;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.telenav.modules.mapping.graph.Edge;
import com.telenav.modules.mapping.graph.Graph;
import com.telenav.tdk.framework.modules.logging.Logger;
import com.telenav.tdk.framework.modules.logging.LoggerFactory;
import com.telenav.tdk.framework.utilities.filesystem.File;

@Component
@Order(value = 2)
public class StartInitCompareEdge implements CommandLineRunner
{
	public static RTree<Edge, Geometry> compareLevelZerotree = RTree.create();
	public static RTree<Edge, Geometry> compareLevelOnetree = RTree.create();
	public static RTree<Edge, Geometry> compareLevelThreetree = RTree.create();
	public static RTree<Edge, Geometry> compareLevelFourtree = RTree.create();
	public static RTree<Edge, Geometry> compareLevelTwotree = RTree.create();

	public void initEdgeToMap(final int level, final Edge edge)
	{
		switch (level)
		{
			case 0:
				compareLevelZerotree = compareLevelZerotree.add(edge,
						Geometries.rectangle(
								edge.bounds().getBottomLeft().getLatitude().asDegrees(),
								edge.bounds().getBottomLeft().getLongitude().asDegrees(),
								edge.bounds().getTopRight().getLatitude().asDegrees(),
								edge.bounds().getTopRight().getLongitude().asDegrees()));

				break;
			case 1:
				compareLevelOnetree = compareLevelOnetree.add(edge,
						Geometries.rectangle(
								edge.bounds().getBottomLeft().getLatitude().asDegrees(),
								edge.bounds().getBottomLeft().getLongitude().asDegrees(),
								edge.bounds().getTopRight().getLatitude().asDegrees(),
								edge.bounds().getTopRight().getLongitude().asDegrees()));
				break;
			case 2:
				compareLevelTwotree = compareLevelTwotree.add(edge,
						Geometries.rectangle(
								edge.bounds().getBottomLeft().getLatitude().asDegrees(),
								edge.bounds().getBottomLeft().getLongitude().asDegrees(),
								edge.bounds().getTopRight().getLatitude().asDegrees(),
								edge.bounds().getTopRight().getLongitude().asDegrees()));
				break;
			case 3:
				compareLevelThreetree = compareLevelThreetree.add(edge,
						Geometries.rectangle(
								edge.bounds().getBottomLeft().getLatitude().asDegrees(),
								edge.bounds().getBottomLeft().getLongitude().asDegrees(),
								edge.bounds().getTopRight().getLatitude().asDegrees(),
								edge.bounds().getTopRight().getLongitude().asDegrees()));
				break;
			case 4:
				compareLevelFourtree = compareLevelFourtree.add(edge,
						Geometries.rectangle(
								edge.bounds().getBottomLeft().getLatitude().asDegrees(),
								edge.bounds().getBottomLeft().getLongitude().asDegrees(),
								edge.bounds().getTopRight().getLatitude().asDegrees(),
								edge.bounds().getTopRight().getLongitude().asDegrees()));
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
		System.out.println("compare 的五棵树创建好啦");
	}

}
