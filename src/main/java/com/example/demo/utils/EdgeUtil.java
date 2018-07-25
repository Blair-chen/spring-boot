package com.example.demo.utils;

import java.util.Hashtable;
import java.util.Map;

import com.telenav.modules.mapping.graph.Edge;

public class EdgeUtil
{

	public static Map<Long, Edge> mapZero = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> mapOne = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> mapThree = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> mapFour = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> mapTwo = new Hashtable<Long, Edge>();

	public static Map<Long, Edge> findListEdge(final int level)
	{
		switch (level)
		{
			case 0:
				return EdgeUtil.mapZero;

			case 1:
				return EdgeUtil.mapOne;

			case 2:
				return EdgeUtil.mapTwo;

			case 3:
				return EdgeUtil.mapThree;

			case 4:
				return EdgeUtil.mapFour;

		}
		return null;
	}

	public static Edge getEdge(final long wayId)
	{
		if (EdgeUtil.mapZero.containsKey(wayId))
		{
			return EdgeUtil.mapZero.get(wayId);
		}
		else if (EdgeUtil.mapOne.containsKey(wayId))
		{
			return EdgeUtil.mapOne.get(wayId);
		}
		else if (EdgeUtil.mapTwo.containsKey(wayId))
		{
			return EdgeUtil.mapTwo.get(wayId);
		}
		else if (EdgeUtil.mapThree.containsKey(wayId))
		{
			return EdgeUtil.mapThree.get(wayId);
		}
		else if (EdgeUtil.mapFour.containsKey(wayId))
		{
			return EdgeUtil.mapFour.get(wayId);
		}
		return null;
	}

	public static void initEdgeToMap(final int level, final Edge edge)
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

}
