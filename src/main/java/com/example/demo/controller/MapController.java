package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BoundRequest;
import com.example.demo.model.RoadesResponse;
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

	@RequestMapping(value = "/findWayAndDateById/{id}", method = RequestMethod.GET)
	public List<String> findWayAndDateById(@PathVariable final long id)
	{
		return this.mapservice.findWayAndDateById(id);
	}
}
