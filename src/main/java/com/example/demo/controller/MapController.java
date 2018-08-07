package com.example.demo.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BoundRequest;
import com.example.demo.model.RoadesResponse;
import com.example.demo.model.SpeedVo;
import com.example.demo.service.MapService;

@RestController
public class MapController
{

	@Autowired
	private MapService mapservice;

	/**
	 * Get the road segment based on wayid
	 *
	 * @param tile
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findByWayId/{tile}", method = RequestMethod.GET)
	public List<RoadesResponse> findByWayId(@PathVariable final long tile) throws Exception
	{
		return this.mapservice.findByWayId(tile);
	}

	/**
	 * Get the road based on tile
	 *
	 * @param bound
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "/findPositions", method = RequestMethod.POST)
	public Map<String, Object> findposition(@RequestBody final BoundRequest bound) throws Exception
	{
		final Map<String, Object> map = new HashMap<String, Object>();
		if (bound.getZoom() > 1)
		{

			final List<RoadesResponse> list = this.mapservice.findposition(bound);
			map.put("listWay", list);

		}
		map.put("bound", bound);
		return map;
	}

	/**
	 * get speed based on wayid
	 *
	 * @param request
	 * @return
	 * @throws Exception
	 * @throws NumberFormatException
	 */
	@RequestMapping(value = "/findSpeed", method = RequestMethod.GET)
	public List<SpeedVo> findSpeed(final HttpServletRequest request) throws Exception
	{
		return this.mapservice.findSpeed(Long.parseLong(request.getParameter("wayid")),
				request.getParameter("date"));

	}

	/**
	 * Get all dates with speed
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/findWayAndDateById/{id}", method = RequestMethod.GET)
	public List<String> findWayAndDateById(@PathVariable final long id)
	{
		return this.mapservice.findWayAndDateById(id);
	}

}
