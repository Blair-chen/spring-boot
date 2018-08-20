package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.constant.ColorConstant;
import com.example.demo.model.BoundRequest;
import com.example.demo.model.CompareReport;
import com.example.demo.model.FunctionClassCount;
import com.example.demo.model.Position;
import com.example.demo.model.RoadesResponse;
import com.example.demo.model.SourceForm;
import com.example.demo.service.CompareReportService;
import com.example.demo.utils.BeanUtil;
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
	@Autowired
	BeanUtil beanUtil;

	@Override
	public CompareReport getCompareReportOfTwoResource()
	{
		try
		{
			final CompareReport comapreReport = new CompareReport();
			final String str = HttpUtil.getDtypeFlow();
			final JSONObject jsonObject = JSONObject.parseObject(str);
			this.beanUtil.Json2Object(comapreReport, jsonObject);
			final FunctionClassCount[] list = new FunctionClassCount[2];

			if (jsonObject.containsKey("inAutoNaviButNotInPalmgo"))
			{
				final FunctionClassCount functionClassCountAuto = this.beanUtil
						.countTheFunctionClass(
								jsonObject.get("inAutoNaviButNotInPalmgo").toString());
				functionClassCountAuto.setName("inAutoNaviButNotInPalmgo");
				list[0] = functionClassCountAuto;

			}
			if (jsonObject.containsKey("inPalmgoButNotInAutoNavi"))
			{
				final FunctionClassCount functionClassCountAuto = this.beanUtil
						.countTheFunctionClass(
								jsonObject.get("inPalmgoButNotInAutoNavi").toString());
				functionClassCountAuto.setName("inPalmgoButNotInAutoNavi");
				list[1] = functionClassCountAuto;

			}
			comapreReport.setFunctionClasslist(list);
			return comapreReport;
		}
		catch (final Exception e)
		{

			e.printStackTrace();
		}
		return null;

	}

	@Override
	public CompareReport getDifferentlevel(final BoundRequest bound) throws Exception
	{
		// result name
		final CompareReport comapreReport = new CompareReport();
		comapreReport.setBound(bound);

		final List<RoadesResponse> result = new ArrayList<RoadesResponse>();

		final List<Entry<Edge, Geometry>> list = EdgeUtil.findCompareEdge(bound.getZoom())
				.search(Geometries.rectangle(bound.getSourthwest().getLat(),
						bound.getSourthwest().getLng(), bound.getNortheast().getLat(),
						bound.getNortheast().getLng()))
				.toList().toBlocking().single();
		final String str = HttpUtil.getDtypeFlow();
		if (str == null || str.length() == 0)
		{
			return null;
		}

		final Map<String, Map<String, Object>> map = str2Json(str, comapreReport);
		Entry<Edge, Geometry> entry = null;
		List<Location> location = null;
		for (int i = 0, len = list.size(); i < len; i++)
		{
			entry = list.get(i);

			if (map.containsKey(entry.value().getIdentifier().toString()))
			{
				final List<SourceForm> listItem = new ArrayList<SourceForm>();
				location = entry.value().getRoadShape().getLocations();
				List<Position> position = new ArrayList<Position>();
				for (final Location lo : location)
				{
					position.add(new Position(lo.getLatitude().asDegrees(),
							lo.getLongitude().asDegrees()));
				}
				final RoadesResponse rode = new RoadesResponse(entry.value().getIdentifierAsLong(),
						position);
				position = null;
				final SourceForm sourcePalmgo = new SourceForm("palmgoFlow",
						Integer.parseInt(map.get(entry.value().getIdentifier().toString())
								.get("palmgoFlow").toString()));
				final SourceForm sourceAuto = new SourceForm("autoNaviFlow",
						Integer.parseInt(map.get(entry.value().getIdentifier().toString())
								.get("autoNaviFlow").toString()));
				listItem.add(sourcePalmgo);
				listItem.add(sourceAuto);
				rode.setListSource(listItem);
				rode.setColor(ColorConstant.red);
				result.add(rode);

			}
		}
		comapreReport.setRoadeslist(result);
		return comapreReport;
	}

	@Override
	public CompareReport inOneResourceNotInOther(final BoundRequest bound)
	{
		final CompareReport comapreReport = new CompareReport();
		comapreReport.setBound(bound);

		final List<Entry<Edge, Geometry>> list = EdgeUtil.findCompareEdge(bound.getZoom())
				.search(Geometries.rectangle(bound.getSourthwest().getLat(),
						bound.getSourthwest().getLng(), bound.getNortheast().getLat(),
						bound.getNortheast().getLng()))
				.toList().toBlocking().single();

		try
		{
			final String str = HttpUtil.getDtypeFlow();
			if (str == null || str.length() == 0)
			{
				return null;
			}

			final List<RoadesResponse> result = new ArrayList<RoadesResponse>();
			final JSONObject jsonObject = JSONObject.parseObject(str);
			String InAutoNotInPlamgo = this.beanUtil.functionClassList(bound.getZoom(),
					jsonObject.getString("inAutoNaviButNotInPalmgo").substring(1,
							jsonObject.getString("inAutoNaviButNotInPalmgo").length() - 1));
			InAutoNotInPlamgo = "," + InAutoNotInPlamgo.substring(1, InAutoNotInPlamgo.length() - 1)
					+ ",";

			String InplamgoNotInAuto = this.beanUtil.functionClassList(bound.getZoom(),
					jsonObject.getString("inPalmgoButNotInAutoNavi").substring(1,
							jsonObject.getString("inPalmgoButNotInAutoNavi").length() - 1));
			InplamgoNotInAuto = InplamgoNotInAuto.substring(1, InplamgoNotInAuto.length() - 1);

			Entry<Edge, Geometry> entry = null;
			List<Location> location = null;
			List<Position> position = null;
			for (int i = 0, len = list.size(); i < len; i++)
			{
				entry = list.get(i);
				Boolean AutoFlag = false;
				Boolean PlamFlag = false;
				String color = "";

				if (InAutoNotInPlamgo
						.contains(", " + entry.value().getIdentifier().toString() + ","))
				{
					AutoFlag = true;
					color = ColorConstant.green;
				}
				if (InplamgoNotInAuto
						.contains(", " + entry.value().getIdentifier().toString() + ","))
				{
					PlamFlag = true;
					color = ColorConstant.blue;
				}

				if (AutoFlag ^ PlamFlag)
				{

					location = entry.value().getRoadShape().getLocations();
					position = new ArrayList<Position>();
					for (final Location lo : location)
					{
						position.add(new Position(lo.getLatitude().asDegrees(),
								lo.getLongitude().asDegrees()));
					}
					final RoadesResponse rode = new RoadesResponse(
							entry.value().getIdentifierAsLong(), position);
					position = null;
					rode.setColor(color);
					result.add(rode);
				}

			}
			comapreReport.setRoadeslist(result);
			return comapreReport;
		}
		catch (final Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * String to map {auto:{id:xx,level:hh},pam:{id:xx,level:mm}} ----> {xx:{auto:hh,pam:mm}}
	 *
	 * @param str
	 * @param comapreReport
	 * @return Map
	 */
	public Map<String, Map<String, Object>> str2Json(final String str,
			final CompareReport comapreReport)
	{

		final Map<String, Map<String, Object>> map = new HashMap<String, Map<String, Object>>();

		final JSONObject jsonObject = JSONObject.parseObject(str);

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
