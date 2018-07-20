package com.example.demo.utils;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

import com.telenav.modules.mapping.graph.Edge;

public class EdgeUtil
{

	public static List<Edge> levelZero = new ArrayList<Edge>();
	public static List<Edge> levelOne = new ArrayList<Edge>();
	public static List<Edge> levelTwo = new ArrayList<Edge>();
	public static List<Edge> levelThree = new ArrayList<Edge>();
	public static List<Edge> levelFour = new ArrayList<Edge>();

	public static Map<Long, Edge> mapZero = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> mapOne = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> mapThree = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> mapFour = new Hashtable<Long, Edge>();
	public static Map<Long, Edge> mapTwo = new Hashtable<Long, Edge>();

	public static void initEdge(final int level, final Edge edge)
	{

		switch (level)
		{
			case 0:
				levelZero.add(edge);
				break;
			case 1:
				levelOne.add(edge);
				break;
			case 2:
				levelTwo.add(edge);
				break;
			case 3:
				levelThree.add(edge);
				break;
			case 4:
				levelFour.add(edge);
				break;

		}
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
