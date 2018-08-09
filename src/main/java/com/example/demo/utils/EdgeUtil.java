package com.example.demo.utils;

import java.util.Map;

import com.example.demo.StartInitCompareEdge;
import com.example.demo.StartInitEdge;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.telenav.modules.mapping.graph.Edge;

public class EdgeUtil
{
	public static RTree<Edge, Geometry> CreateTree(final Map<Long, Edge> map)
	{
		//
		RTree<Edge, Geometry> tree = RTree.create();

		for (final Edge edge : map.values())
		{
			tree = tree.add(edge,
					Geometries.rectangle(edge.bounds().getBottomLeft().getLatitude().asDegrees(),
							edge.bounds().getBottomLeft().getLongitude().asDegrees(),
							edge.bounds().getTopRight().getLatitude().asDegrees(),
							edge.bounds().getTopRight().getLongitude().asDegrees()));
		}
		return tree;
	}

	public static Map<Long, Edge> findCompareEdge(final int level)
	{
		switch (level)
		{
			case 0:
				return StartInitCompareEdge.comparelevelZero;

			case 1:
				return StartInitCompareEdge.comparelevelOne;

			case 2:
				return StartInitCompareEdge.comparelevelTwo;

			case 3:
				return StartInitCompareEdge.comparelevelThree;

			case 4:
				return StartInitCompareEdge.comparelevelFour;

		}
		return null;
	}

	public static Map<Long, Edge> findListEdge(final int level)
	{
		switch (level)
		{
			case 0:
				return StartInitEdge.levelZero;

			case 1:
				return StartInitEdge.levelOne;

			case 2:
				return StartInitEdge.levelTwo;

			case 3:
				return StartInitEdge.levelThree;

			case 4:
				return StartInitEdge.levelFour;

		}
		return null;
	}

	public static Edge getEdge(final long wayId)
	{
		if (StartInitEdge.levelZero.containsKey(wayId))
		{
			return StartInitEdge.levelZero.get(wayId);
		}
		else if (StartInitEdge.levelOne.containsKey(wayId))
		{
			return StartInitEdge.levelOne.get(wayId);
		}
		else if (StartInitEdge.levelTwo.containsKey(wayId))
		{
			return StartInitEdge.levelTwo.get(wayId);
		}
		else if (StartInitEdge.levelThree.containsKey(wayId))
		{
			return StartInitEdge.levelThree.get(wayId);
		}
		else if (StartInitEdge.levelFour.containsKey(wayId))
		{
			return StartInitEdge.levelFour.get(wayId);
		}
		return null;
	}

}
