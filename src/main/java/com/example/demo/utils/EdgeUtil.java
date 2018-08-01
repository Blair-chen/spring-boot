package com.example.demo.utils;

import java.util.Map;

import com.example.demo.StartInitEdge;
import com.telenav.modules.mapping.graph.Edge;

public class EdgeUtil
{

	public static Map<Long, Edge> findListEdge(final int level)
	{
		switch (level)
		{
			case 0:
				return StartInitEdge.mapZero;

			case 1:
				return StartInitEdge.mapOne;

			case 2:
				return StartInitEdge.mapTwo;

			case 3:
				return StartInitEdge.mapThree;

			case 4:
				return StartInitEdge.mapFour;

		}
		return null;
	}

	public static Edge getEdge(final long wayId)
	{
		if (StartInitEdge.mapZero.containsKey(wayId))
		{
			return StartInitEdge.mapZero.get(wayId);
		}
		else if (StartInitEdge.mapOne.containsKey(wayId))
		{
			return StartInitEdge.mapOne.get(wayId);
		}
		else if (StartInitEdge.mapTwo.containsKey(wayId))
		{
			return StartInitEdge.mapTwo.get(wayId);
		}
		else if (StartInitEdge.mapThree.containsKey(wayId))
		{
			return StartInitEdge.mapThree.get(wayId);
		}
		else if (StartInitEdge.mapFour.containsKey(wayId))
		{
			return StartInitEdge.mapFour.get(wayId);
		}
		return null;
	}

}
