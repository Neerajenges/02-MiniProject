package com.eg.request;

import java.time.LocalDate;

import lombok.Data;

@Data
public class SearchRequest {
	//by whch field we want to search the in the table one or multiple constrants 
	private String PlanName;
	private String planStatus;
	private LocalDate planStartDate;
	private LocalDate planEndDate;

}
