package com.example.demo.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BoundRequest;
import com.example.demo.model.RoadesResponse;
import com.example.demo.model.Speed;
import com.example.demo.service.MapService;

@RestController
public class MapController
{

	@Autowired
	private MapService mapservice;

	@RequestMapping(value = "/findByWayId/{tile}", method = RequestMethod.GET)
	public List<RoadesResponse> findByWayId(@PathVariable final long tile) throws Exception
	{

		return this.mapservice.findByWayId(tile);

	}

	@RequestMapping(value = "/findPositions", method = RequestMethod.POST)
	public List<RoadesResponse> findposition(@RequestBody final BoundRequest bound) throws Exception
	{
		if (bound.getZoom() > 1)
		{
			final List<RoadesResponse> list = this.mapservice.findposition(bound);
			return list;
		}
		return null;
	}

	@RequestMapping(value = "/findSpeed", method = RequestMethod.GET)
	public List<Speed> findSpeed(final HttpServletRequest request)
	{

		final long wayid = Long.parseLong(request.getParameter("wayid"));
		final String date = request.getParameter("date");
		return this.mapservice.findSpeed(wayid, date);

	}

	@RequestMapping(value = "/findWayAndDateById/{id}", method = RequestMethod.GET)
	public List<String> findWayAndDateById(@PathVariable final long id)
	{
		return this.mapservice.findWayAndDateById(id);
	}
}
