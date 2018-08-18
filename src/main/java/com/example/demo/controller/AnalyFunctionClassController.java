package com.example.demo.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BoundRequest;
import com.example.demo.service.AnalyFunctionClassService;

@RestController
public class AnalyFunctionClassController
{
	@Autowired
	AnalyFunctionClassService analy;

	@RequestMapping(value = "/getFunctionClassCountByTile", method = RequestMethod.POST)
	public Map<String, Object> getCompareRepotrByTile(@RequestBody final BoundRequest bound)
			throws Exception
	{
		return this.analy.findRouteCountByFunctionClass(bound);
	}
}
