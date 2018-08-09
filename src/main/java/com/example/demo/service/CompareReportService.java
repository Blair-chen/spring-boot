package com.example.demo.service;

import com.example.demo.model.BoundRequest;
import com.example.demo.model.CompareReport;

public interface CompareReportService
{

	public CompareReport getReport(BoundRequest bound) throws Exception;

}
