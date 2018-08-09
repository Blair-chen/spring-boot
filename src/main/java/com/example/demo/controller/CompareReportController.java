package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.BoundRequest;
import com.example.demo.model.CompareReport;
import com.example.demo.service.CompareReportService;

@RestController
public class CompareReportController
{
	@Autowired
	CompareReportService compareReportService;

	@RequestMapping(value = "/getCompareRepotrByTile", method = RequestMethod.POST)
	public CompareReport getCompareRepotrByTile(@RequestBody final BoundRequest bound)
			throws Exception
	{
		return this.compareReportService.getReport(bound);
	}
}
