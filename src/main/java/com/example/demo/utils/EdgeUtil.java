package com.example.demo.utils;

import com.example.demo.StartInitCompareEdge;
import com.example.demo.StartInitEdge;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.telenav.modules.mapping.graph.Edge;

public class EdgeUtil
{

	public static RTree<Edge, Geometry> findCompareEdge(final int level)
	{
		switch (level)
		{
			case 0:
				return StartInitCompareEdge.compareLevelZerotree;

			case 1:
				return StartInitCompareEdge.compareLevelOnetree;

			case 2:
				return StartInitCompareEdge.compareLevelTwotree;

			case 3:
				return StartInitCompareEdge.compareLevelThreetree;

			case 4:
				return StartInitCompareEdge.compareLevelFourtree;

		}
		return null;
	}

	public static RTree<Edge, Geometry> findListEdge(final int level)
	{
		switch (level)
		{
			case 0:
				return StartInitEdge.levelZerotree;

			case 1:
				return StartInitEdge.levelOnetree;

			case 2:
				return StartInitEdge.levelTwotree;

			case 3:
				return StartInitEdge.levelThreetree;

			case 4:
				return StartInitEdge.levelFourtree;

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
