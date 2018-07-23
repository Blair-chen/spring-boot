package com.example.demo.service;

import java.util.List;

import com.example.demo.model.BoundRequest;
import com.example.demo.model.RoadesResponse;
import com.example.demo.model.Speed;

public interface MapService
{

	public List<RoadesResponse> findByWayId(long wayId) throws Exception;

	public List<RoadesResponse> findposition(BoundRequest bound) throws Exception;

	public List<Speed> findSpeed(long wayid, String date);

	public List<String> findWayAndDateById(long id);

}