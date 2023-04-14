package com.eg.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.eg.request.SearchRequest;
import com.eg.response.SearchResponse;

public interface EligibilityService {
	public List<String> getUniquePlanNames();
	public List<String> getUniquePlanStatuses();
	
	public List<SearchResponse> search(SearchRequest request);
	
	public void  generateExcel(HttpServletResponse response);
	//public HttpServletResponse  generateExcel(); we can do in this way also
	
	public void  generatePdf(HttpServletResponse response);
	
	

}
