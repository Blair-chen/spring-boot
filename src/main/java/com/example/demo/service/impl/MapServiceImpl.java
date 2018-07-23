package com.example.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.mapper.SpeedMapper;
import com.example.demo.mapper.WayAndDateMapper;
import com.example.demo.model.BoundRequest;
import com.example.demo.model.Position;
import com.example.demo.model.RoadesResponse;
import com.example.demo.model.Speed;
import com.example.demo.model.WayAndDate;
import com.example.demo.service.MapService;
import com.example.demo.utils.EdgeUtil;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.telenav.modules.mapping.geography.Location;
import com.telenav.modules.mapping.graph.Edge;

@Service
public class MapServiceImpl implements MapService
{
	@Autowired
	private WayAndDateMapper wayAndDateMapper;

	@Autowired
	private SpeedMapper speedMapper;

	public RTree<Edge, Geometry> CreateTree(final int level)
	{
		RTree<Edge, Geometry> tree = RTree.create();
		final Map<Long, Edge> map = findListEdge(level);
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

	@Override
	public List<RoadesResponse> findByWayId(final long wayId) throws Exception
	{
		final List<RoadesResponse> result = new ArrayList<RoadesResponse>();
		Edge edge = null;
		if (EdgeUtil.mapZero.containsKey(wayId))
		{
			edge = EdgeUtil.mapZero.get(wayId);
		}
		else if (EdgeUtil.mapOne.containsKey(wayId))
		{
			edge = EdgeUtil.mapOne.get(wayId);
		}
		else if (EdgeUtil.mapTwo.containsKey(wayId))
		{
			edge = EdgeUtil.mapTwo.get(wayId);
		}
		else if (EdgeUtil.mapThree.containsKey(wayId))
		{
			edge = EdgeUtil.mapThree.get(wayId);
		}
		else if (EdgeUtil.mapFour.containsKey(wayId))
		{
			edge = EdgeUtil.mapFour.get(wayId);
		}
		if (edge != null)
		{
			final RoadesResponse response = getWayResponse(edge);
			if (response != null)
			{
				result.add(response);
				return result;
			}

			return null;

		}

		return null;
	}

	public Map<Long, Edge> findListEdge(final int level)
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

	@Override
	public List<RoadesResponse> findposition(final BoundRequest bound) throws Exception
	{
		final String anzUrl = "http://10.189.103.146:8080/traffic-service/maps/v4/ngx-traffic-ids/json?map_source=here&traffic_source=here&authorized_region=ANZ&type=flow,incident&locale=en&time=2014-02-04T00:56Z";

		final List<RoadesResponse> result = new ArrayList<RoadesResponse>();
		final RTree<Edge, Geometry> tree = CreateTree(bound.getZoom());
		final List<Entry<Edge,
				Geometry>> list = tree.search(Geometries.rectangle(bound.getSourthwest().getLat(),
						bound.getSourthwest().getLng(), bound.getNortheast().getLat(),
						bound.getNortheast().getLng())).toList().toBlocking().single();
		System.out.println(list.size());
		for (final Entry<Edge, Geometry> entry : list)
		{

			final RoadesResponse response = getWayResponse(entry.value());
			if (response != null)
			{
				result.add(getWayResponse(entry.value()));
			}

		}

		return result;

	}

	@Override
	public List<Speed> findSpeed(final long wayid, final String date)
	{
		return this.speedMapper.findSpeedByWayidAndDate(wayid, date);

	}

	@Override
	public List<String> findWayAndDateById(final long id)
	{
		final List<WayAndDate> list = this.wayAndDateMapper.findWayAndDateByWayid(id);
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		final List<String> result = new ArrayList<String>();
		for (final WayAndDate way : list)
		{
			result.add(simpleDateFormat.format(way.getTime()));
		}
		return result;
	}

	public RoadesResponse getWayResponse(final Edge edge) throws Exception
	{
		// final String anzUrl =
		// "http://10.189.103.146:8080/traffic-service/maps/v4/ngx-traffic-ids/json?map_source=here&traffic_source=here&authorized_region=ANZ&type=flow,incident&locale=en&time=2014-02-04T00:56Z";
		//
		// final JSONObject jsonObject = JSONObject
		// .parseObject("{" + HttpUtil.getFlowReport(anzUrl, edge.getIdentifierAsLong()));
		// // final Object o = str.get("traffic_flow");
		// if (jsonObject.containsKey("traffic_flow"))
		// {
		final List<Location> location = edge.getRoadShape().getLocations();
		final List<Position> position = new ArrayList<Position>();
		for (final Location lo : location)
		{
			position.add(new Position(lo.getLatitude().asDegrees(), lo.getLongitude().asDegrees()));
		}

		// final List strarr = (List) jsonObject.get("traffic_flow");
		//
		// final JSONObject json = JSONObject.parseObject(strarr.get(0).toString());
		// final int flow = (int) json.get("traffic_level");

		return new RoadesResponse(edge.getIdentifierAsLong(), position, 2);
		// }
		// return null;
	}

}
