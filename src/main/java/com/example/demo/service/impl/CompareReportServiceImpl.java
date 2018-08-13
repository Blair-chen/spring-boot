package com.example.demo.service.impl;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.example.demo.StartInitCompareEdge;
import com.example.demo.model.BoundRequest;
import com.example.demo.model.CompareReport;
import com.example.demo.model.FunctionClassCount;
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

	public String functionClassList(final int zoom, final String str)
	{
		final List<String> functionClassList = new ArrayList<String>();

		int functionClass = -1;
		final String[] strArr = str.split(",");
		for (int i = 0; i < strArr.length; i++)
		{
			if (StartInitCompareEdge.compareMapEdge.containsKey(strArr[i].toString()))
			{
				functionClass = StartInitCompareEdge.compareMapEdge.get(strArr[i].toString())
						.getRoadFunctionalClass().getIdentifier();
				if (functionClass == zoom)
				{
					functionClassList.add(strArr[i].toString());
				}
			}
		}
		return functionClassList.toString();
	}

	@Override
	public CompareReport getCompareReport()
	{
		try
		{
			final CompareReport comapreReport = new CompareReport();
			final String str = HttpUtil.getDtypeFlow();
			final JSONObject jsonObject = JSONObject.parseObject(str);
			Json2Object(comapreReport, jsonObject);
			final List<FunctionClassCount> list = new ArrayList<FunctionClassCount>();
			if (jsonObject.containsKey("inAutoNaviButNotInPalmgo"))
			{
				final FunctionClassCount functionClassCountAuto = getFunctionClassCount(
						jsonObject.get("inAutoNaviButNotInPalmgo").toString());
				functionClassCountAuto.setName("inAutoNaviButNotInPalmgo");
				list.add(functionClassCountAuto);
			}
			if (jsonObject.containsKey("inPalmgoButNotInAutoNavi"))
			{
				final FunctionClassCount functionClassCountAuto = getFunctionClassCount(
						jsonObject.get("inPalmgoButNotInAutoNavi").toString());
				functionClassCountAuto.setName("inPalmgoButNotInAutoNavi");
				list.add(functionClassCountAuto);
			}
			comapreReport.setFunctionClasslist(list);
			return comapreReport;
		}
		catch (final Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	@Override
	public CompareReport getDifferentRoade(final BoundRequest bound)
	{
		final CompareReport comapreReport = new CompareReport();
		comapreReport.setBound(bound);

		final List<RoadesResponse> result = new ArrayList<RoadesResponse>();

		final List<Entry<Edge, Geometry>> list = EdgeUtil.findCompareEdge(bound.getZoom())
				.search(Geometries.rectangle(bound.getSourthwest().getLat(),
						bound.getSourthwest().getLng(), bound.getNortheast().getLat(),
						bound.getNortheast().getLng()))
				.toList().toBlocking().single();
		String str;
		try
		{
			str = HttpUtil.getDtypeFlow();
			if (str == null || str.length() <= 0)
			{
				return null;
			}

			final JSONObject jsonObject = JSONObject.parseObject(str);
			String InAutoNotInPlamgo = functionClassList(bound.getZoom(),
					jsonObject.getString("inAutoNaviButNotInPalmgo").substring(1,
							jsonObject.getString("inAutoNaviButNotInPalmgo").length() - 1));
			InAutoNotInPlamgo = "," + InAutoNotInPlamgo.substring(1, InAutoNotInPlamgo.length() - 1)
					+ ",";

			String InplamgoNotInAuto = functionClassList(bound.getZoom(),
					jsonObject.getString("inPalmgoButNotInAutoNavi").substring(1,
							jsonObject.getString("inPalmgoButNotInAutoNavi").length() - 1));
			InplamgoNotInAuto = InplamgoNotInAuto.substring(1, InplamgoNotInAuto.length() - 1);

			for (final Entry<Edge, Geometry> entry : list)
			{
				Boolean AutoFlag = false;
				Boolean PlamFlag = false;
				String color = "";

				if (InAutoNotInPlamgo
						.contains(", " + entry.value().getIdentifier().toString() + ","))
				{
					AutoFlag = true;
					color = "#33AD08";
				}
				if (InplamgoNotInAuto
						.contains(", " + entry.value().getIdentifier().toString() + ","))
				{
					PlamFlag = true;
					color = "#0F0CBB";
				}

				if (AutoFlag ^ PlamFlag)
				{

					final List<Location> location = entry.value().getRoadShape().getLocations();
					final List<Position> position = new ArrayList<Position>();
					for (final Location lo : location)
					{
						position.add(new Position(lo.getLatitude().asDegrees(),
								lo.getLongitude().asDegrees()));
					}
					final RoadesResponse rode = new RoadesResponse(
							entry.value().getIdentifierAsLong(), position);
					rode.setColor(color);

					result.add(rode);
				}
				if (AutoFlag && PlamFlag)
				{

					System.out.println(entry.value().getIdentifierAsLong());
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

	public FunctionClassCount getFunctionClassCount(final String str)
	{
		int functionClass;
		final FunctionClassCount functionClassCount = new FunctionClassCount(0, 0, 0, 0, 0);
		final String[] strAutoArr = str.substring(1, str.length() - 1).split(",");
		System.out.println(strAutoArr.length);
		System.out.println(StartInitCompareEdge.compareMapEdge.size());

		for (int i = 0; i < strAutoArr.length; i++)
		{
			if (StartInitCompareEdge.compareMapEdge.containsKey(strAutoArr[i]))
			{
				functionClass = StartInitCompareEdge.compareMapEdge.get(strAutoArr[i])
						.getRoadFunctionalClass().getIdentifier();
				switch (functionClass)
				{
					case 0:

						functionClassCount.setFunctionClassZero(
								functionClassCount.getFunctionClassZero() + 1);
						break;
					case 1:
						functionClassCount
								.setFunctionClassOne(functionClassCount.getFunctionClassOne() + 1);
						break;
					case 2:
						functionClassCount
								.setFunctionClassTwo(functionClassCount.getFunctionClassTwo() + 1);
						break;
					case 3:
						functionClassCount.setFunctionClassThree(
								functionClassCount.getFunctionClassThree() + 1);
						break;
					case 4:
						functionClassCount.setFunctionClassFour(
								functionClassCount.getFunctionClassFour() + 1);

						break;
				}
			}

		}

		return functionClassCount;
	}

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
		if (str == null || str.length() <= 0)
		{
			return null;
		}
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
