package com.example.demo.service;

import java.util.Map;

import com.example.demo.model.BoundRequest;

public interface AnalyFunctionClassService
{
	public Map<String, Object> findRouteCountByFunctionClass(BoundRequest bounds);
}
