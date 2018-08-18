package com.example.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.demo.StartInitCompareEdge;
import com.example.demo.model.BoundRequest;
import com.example.demo.service.AnalyFunctionClassService;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.telenav.modules.mapping.graph.Edge;

@Service
public class AnalyFunctionClassServiceImpl implements AnalyFunctionClassService
{

	@Override
	public Map<String, Object> findRouteCountByFunctionClass(final BoundRequest bound)
	{
		final Map<String, Object> map = new HashMap<String, Object>();
		map.put("bound", bound);
		if (bound.getNortheast().getLng() < bound.getSourthwest().getLng())
		{
			map.put("functionClassZero",
					functioncalss(StartInitCompareEdge.compareLevelZerotree, bound));
			map.put("functionClassOne",
					functioncalss(StartInitCompareEdge.compareLevelOnetree, bound));
			map.put("functionClassTwo",
					functioncalss(StartInitCompareEdge.compareLevelTwotree, bound));
			map.put("functionClassThree",
					functioncalss(StartInitCompareEdge.compareLevelThreetree, bound));
			map.put("functionClassFour",
					functioncalss(StartInitCompareEdge.compareLevelFourtree, bound));
		}
		else
		{
			getfunction(bound, map);
		}

		return map;
	}

	public int functioncalss(final RTree<Edge, Geometry> tree, final BoundRequest bound)
	{
		final List<Entry<Edge, Geometry>> list = tree
				.search(Geometries.rectangle(bound.getSourthwest().getLat(),
						bound.getSourthwest().getLng(), bound.getNortheast().getLat(), 180))
				.toList().toBlocking().single();
		list.addAll(tree
				.search(Geometries.rectangle(bound.getSourthwest().getLat(), -180,
						bound.getNortheast().getLat(), bound.getNortheast().getLng()))
				.toList().toBlocking().single());

		return list.size();
	}

	public void getfunction(final BoundRequest bound, final Map<String, Object> map)
	{
		final List<Entry<Edge, Geometry>> listZero = StartInitCompareEdge.compareLevelZerotree
				.search(Geometries.rectangle(bound.getSourthwest().getLat(),
						bound.getSourthwest().getLng(), bound.getNortheast().getLat(),
						bound.getNortheast().getLng()))
				.toList().toBlocking().single();
		map.put("functionClassZero", listZero.size());
		final List<Entry<Edge, Geometry>> listone = StartInitCompareEdge.compareLevelOnetree.search(
				Geometries.rectangle(bound.getSourthwest().getLat(), bound.getSourthwest().getLng(),
						bound.getNortheast().getLat(), bound.getNortheast().getLng()))
				.toList().toBlocking().single();
		map.put("functionClassOne", listone.size());
		final List<Entry<Edge, Geometry>> listtwo = StartInitCompareEdge.compareLevelTwotree.search(
				Geometries.rectangle(bound.getSourthwest().getLat(), bound.getSourthwest().getLng(),
						bound.getNortheast().getLat(), bound.getNortheast().getLng()))
				.toList().toBlocking().single();
		map.put("functionClassTwo", listtwo.size());
		final List<Entry<Edge, Geometry>> listthree = StartInitCompareEdge.compareLevelThreetree
				.search(Geometries.rectangle(bound.getSourthwest().getLat(),
						bound.getSourthwest().getLng(), bound.getNortheast().getLat(),
						bound.getNortheast().getLng()))
				.toList().toBlocking().single();
		map.put("functionClassThree", listthree.size());
		final List<Entry<Edge, Geometry>> listfour = StartInitCompareEdge.compareLevelFourtree
				.search(Geometries.rectangle(bound.getSourthwest().getLat(),
						bound.getSourthwest().getLng(), bound.getNortheast().getLat(),
						bound.getNortheast().getLng()))
				.toList().toBlocking().single();
		map.put("functionClassFour", listfour.size());

	}
}
