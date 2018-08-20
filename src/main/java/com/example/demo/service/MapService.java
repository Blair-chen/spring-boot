package com.example.demo.service;

import java.util.List;

import com.example.demo.model.BoundRequest;
import com.example.demo.model.RoadesResponse;
import com.example.demo.model.SpeedVo;

public interface MapService
{
	/**
	 * Get the road according to the bound box
	 *
	 * @param bound
	 * @return List<RoadesResponse>
	 * @throws Exception
	 */
	public List<RoadesResponse> findByTile(BoundRequest bound) throws Exception;

	/**
	 * Get the rode according to the wayid
	 *
	 * @param wayId
	 * @return List<RoadesResponse>
	 * @throws Exception
	 */
	public List<RoadesResponse> findByWayId(long wayId) throws Exception;

	/**
	 * get speed according to wayid and date
	 *
	 * @param wayid
	 * @param date
	 * @return List<SpeedVo>
	 */
	public List<SpeedVo> findSpeed(long wayid, String date);

	/**
	 * get date to String according wayid
	 * 
	 * @param id
	 * @return
	 */
	public List<String> findWayAndDateById(long id);

}
