package com.example.demo.service.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.dao.SpeedDao;
import com.example.demo.dao.WayAndDateDao;
import com.example.demo.model.BoundRequest;
import com.example.demo.model.EdgeVo;
import com.example.demo.model.Position;
import com.example.demo.model.RoadesResponse;
import com.example.demo.model.Speed;
import com.example.demo.model.SpeedVo;
import com.example.demo.model.WayAndDate;
import com.example.demo.service.MapService;
import com.example.demo.utils.BeanUtil;
import com.example.demo.utils.EdgeUtil;
import com.example.demo.utils.HttpUtil;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.RTree;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.telenav.modules.mapping.geography.Location;

@Service
public class MapServiceImpl implements MapService
{
	final String anzUrl = "http://10.189.103.146:8080/traffic-service/maps/v4/ngx-traffic-ids/json?map_source=here&traffic_source=here&authorized_region=ANZ&type=flow,incident&locale=en&time=2014-02-04T00:56Z";

	@Autowired
	private WayAndDateDao wayAndDateMapper;

	@Autowired
	private SpeedDao speedMapper;

	@Autowired
	BeanUtil beanUtil;

	@Override
	public List<RoadesResponse> findByTile(final BoundRequest bound) throws Exception
	{
		final List<RoadesResponse> result = new ArrayList<RoadesResponse>();
		final RTree<EdgeVo, Geometry> tree = EdgeUtil.findListEdge(bound.getZoom());
		final List<Entry<EdgeVo,
				Geometry>> list = tree.search(Geometries.rectangle(bound.getSourthwest().getLat(),
						bound.getSourthwest().getLng(), bound.getNortheast().getLat(),
						bound.getNortheast().getLng())).toList().toBlocking().single();
		System.out.println(list.size());
		final StringBuffer wayidStr = new StringBuffer();

		for (final Entry<EdgeVo, Geometry> entry : list)
		{
			final List<Location> location = entry.value().getLocation();
			final List<Position> position = new ArrayList<Position>();
			for (final Location lo : location)
			{
				position.add(
						new Position(lo.getLatitude().asDegrees(), lo.getLongitude().asDegrees()));
			}
			result.add(new RoadesResponse(entry.value().getId(), position));
			wayidStr.append(entry.value().getId() + ",");
		}

		final Map<String,
				String> map = wayidStr.length() > 0
						? getTrafficFlowToMap(wayidStr.substring(0, wayidStr.length() - 1))
						: null;
		final List<RoadesResponse> results = new ArrayList<RoadesResponse>();

		for (final RoadesResponse res : result)
		{
			if (map.containsKey(res.getWayid().toString()))
			{
				res.setFlow(Integer.parseInt(map.get(res.getWayid().toString())));
				results.add(res);
			}
		}
		return results;
	}

	@Override
	public List<RoadesResponse> findByWayId(final long wayId) throws Exception
	{
		final List<RoadesResponse> result = new ArrayList<RoadesResponse>();
		final EdgeVo edge = EdgeUtil.getEdge(wayId);
		if (edge != null)
		{
			final String str = HttpUtil.getFlowReport(this.anzUrl, edge.getId().toString());
			final Map<String, String> map = Str2TrafficFlow(str);
			if (map.size() > 0 && map.containsKey(edge.getId().toString()))
			{
				final List<Location> location = edge.getLocation();
				final List<Position> position = new ArrayList<Position>();
				for (final Location lo : location)
				{
					position.add(new Position(lo.getLatitude().asDegrees(),
							lo.getLongitude().asDegrees()));
				}
				result.add(new RoadesResponse(edge.getId(), position,
						Integer.parseInt(map.get(edge.getId().toString()))));
				return result;
			}
			return null;
		}
		return null;
	}

	@Override
	public List<SpeedVo> findSpeed(final long wayid, final String date)
	{
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		final List<SpeedVo> result = new ArrayList<SpeedVo>();
		SpeedVo svo = null;

		final List<Speed> list = this.speedMapper.findByWayidAndDateOrderByDtimeAsc(wayid, date);
		final int size = list.size();
		for (final Speed s : list)
		{
			svo = new SpeedVo(s);
			svo.setDtimeStr(simpleDateFormat.format(s.getDtime()));
			result.add(svo);
		}
		return result;
	}

	@Override
	public List<String> findWayAndDateById(final long id)
	{
		final List<WayAndDate> list = this.wayAndDateMapper.findByWayid(id);
		final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		final List<String> result = new ArrayList<String>();
		for (final WayAndDate way : list)
		{
			result.add(simpleDateFormat.format(way.getTime()));
		}
		return result;
	}

	/**
	 * Truncate String
	 *
	 * @param str
	 * @return Map<String, String>
	 * @throws Exception
	 */
	public Map<String, String> getTrafficFlowToMap(final String str) throws Exception
	{
		Map<String, String> map = new HashMap<String, String>();
		final List<Integer> indexList = new ArrayList<Integer>();
		int count = 1;
		int i = -1;
		do
		{

			i = this.beanUtil.ordIndexOf(str, ",", 3000 * count);
			if (i != -1)
			{
				indexList.add(i);
				count++;
			}
		}
		while ((i != -1));
		if (indexList.size() > 1)
		{
			map = Str2TrafficFlow(
					HttpUtil.getFlowReport(this.anzUrl, str.substring(0, indexList.get(0))));
			for (int j = 0; j < indexList.size() - 1; j++)
			{
				map.putAll(Str2TrafficFlow(HttpUtil.getFlowReport(this.anzUrl,
						str.substring(indexList.get(j) + 1, indexList.get(j + 1)))));
			}
			map.putAll(Str2TrafficFlow(HttpUtil.getFlowReport(this.anzUrl,
					str.substring(indexList.get(indexList.size() - 1) + 1, str.length()))));
		}
		else
		{
			map = Str2TrafficFlow(
					HttpUtil.getFlowReport(this.anzUrl, str.substring(0, str.length())));
		}
		return map;
	}

	/**
	 * Parsing string
	 *
	 * @param str
	 * @return
	 */
	public Map<String, String> Str2TrafficFlow(final String str)
	{
		final Map<String, String> map = new HashMap<String, String>();
		final JSONObject jsonObject = JSONObject.parseObject(str);
		if (jsonObject.containsKey("traffic_flow"))
		{
			JSONObject json = null;
			final List strarr = (List) jsonObject.get("traffic_flow");
			for (final Object obj : strarr)
			{
				json = JSONObject.parseObject(obj.toString());
				map.put(json.getString("traffic_id"), json.getString("traffic_level"));
			}
		}
		return map;
	}

}
