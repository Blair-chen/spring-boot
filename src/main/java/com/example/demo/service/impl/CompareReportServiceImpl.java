package com.example.demo.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.model.BoundRequest;
import com.example.demo.model.CompareReport;
import com.example.demo.model.Position;
import com.example.demo.model.RoadesResponse;
import com.example.demo.model.SourceForm;
import com.example.demo.service.CompareReportService;
import com.example.demo.utils.EdgeUtil;
import com.example.demo.utils.HttpUtil;
import com.github.davidmoten.rtree.Entry;
import com.github.davidmoten.rtree.geometry.Geometries;
import com.github.davidmoten.rtree.geometry.Geometry;
import com.telenav.modules.mapping.geography.Location;
import com.telenav.modules.mapping.graph.Edge;

@Service
public class CompareReportServiceImpl implements CompareReportService
{

	@Override
	public CompareReport getReport(final BoundRequest bound) throws Exception
	{
		final CompareReport comapreReport = new CompareReport();
		comapreReport.setBound(bound);
		System.out.println(EdgeUtil.findCompareEdge(bound.getZoom()).size());

		final List<RoadesResponse> result = new ArrayList<RoadesResponse>();

		final List<Entry<Edge, Geometry>> list = EdgeUtil.findCompareEdge(bound.getZoom())
				.search(Geometries.rectangle(bound.getSourthwest().getLat(),
						bound.getSourthwest().getLng(), bound.getNortheast().getLat(),
						bound.getNortheast().getLng()))
				.toList().toBlocking().single();
		final String str = HttpUtil.getDtypeFlow();
		final Map<String, Map<String, Object>> map = str2Json(str, comapreReport);
		for (final Entry<Edge, Geometry> entry : list)
		{
			if (map.containsKey(entry.value().getIdentifier().toString()))
			{
				final List<SourceForm> listItem = new ArrayList<SourceForm>();
				final List<Location> location = entry.value().getRoadShape().getLocations();
				final List<Position> position = new ArrayList<Position>();
				for (final Location lo : location)
				{
					position.add(new Position(lo.getLatitude().asDegrees(),
							lo.getLongitude().asDegrees()));
				}
				final RoadesResponse rode = new RoadesResponse(entry.value().getIdentifierAsLong(),
						position);
				final SourceForm sourcePalmgo = new SourceForm("palmgoFlow",
						Integer.parseInt(map.get(entry.value().getIdentifier().toString())
								.get("palmgoFlow").toString()));
				final SourceForm sourceAuto = new SourceForm("autoNaviFlow",
						Integer.parseInt(map.get(entry.value().getIdentifier().toString())
								.get("autoNaviFlow").toString()));
				listItem.add(sourcePalmgo);
				listItem.add(sourceAuto);
				rode.setListSource(listItem);
				rode.setColor("#FF4933");
				result.add(rode);

			}
		}
		comapreReport.setRoadeslist(result);
		return comapreReport;
	}

	/**
	 * Assign the value of jsonObject to Object by reflection
	 *
	 * @param target
	 * @param jsonObject
	 */
	public void Json2Object(final Object target, final JSONObject jsonObject)
	{
		final Field[] fields = target.getClass().getDeclaredFields();
		for (int i = 0; i < fields.length; i++)
		{
			if (jsonObject.containsKey(fields[i].getName()))
			{
				try
				{
					fields[i].setAccessible(true);
					fields[i].set(target, jsonObject.get(fields[i].getName()));
				}
				catch (IllegalArgumentException | IllegalAccessException e)
				{

					e.printStackTrace();
				}
			}
		}

	}

	/**
	 * String to map {auto:{id:xx,level:hh},pam:{id:xx,level:mm}} ----> {xx:{auto:hh,pam:mm}}
	 * 
	 * @param str
	 * @param comapreReport
	 * @return
	 */
	public Map<String, Map<String, Object>> str2Json(final String str,
			final CompareReport comapreReport)
	{

		final Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();

		final JSONObject jsonObject = JSONObject.parseObject(str);
		Json2Object(comapreReport, jsonObject);
		if (jsonObject.containsKey("differentLevel"))
		{
			JSONObject json = null;
			final List strarr = (List) jsonObject.get("differentLevel");
			for (final Object obj : strarr)
			{
				final Map<String, Object> mapItem = new HashMap<String, Object>();

				json = JSONObject.parseObject(
						JSONObject.parseObject(obj.toString()).get("autoNaviFlow").toString());
				mapItem.put("autoNaviFlow", json.get("trafficLevel"));
				json = JSONObject.parseObject(
						JSONObject.parseObject(obj.toString()).get("palmgoFlow").toString());
				mapItem.put("palmgoFlow", json.get("trafficLevel"));
				map.put(json.getString("id"), mapItem);

			}
		}
		return map;
	}

}
