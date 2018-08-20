package com.example.demo.service;

import com.example.demo.model.BoundRequest;
import com.example.demo.model.CompareReport;

public interface CompareReportService
{
	/**
	 * Get a comparison report of two data sources
	 *
	 * @return CompareReport
	 */
	public CompareReport getCompareReportOfTwoResource();

	/**
	 * Get different traffic_levels of the road
	 *
	 * @param bound
	 * @return
	 * @throws Exception
	 */
	public CompareReport getDifferentlevel(BoundRequest bound) throws Exception;

	/**
	 * Obtain a section where one party does not have
	 *
	 * @param bound
	 * @return CompareReport
	 */
	public CompareReport inOneResourceNotInOther(BoundRequest bound);

}
