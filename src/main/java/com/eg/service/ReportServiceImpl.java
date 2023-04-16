package com.eg.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.eg.entity.EligibilityDetails;
import com.eg.repo.EligibilityDetailsRepo;
import com.eg.request.SearchRequest;
import com.eg.response.SearchResponse;


@Service
public class ReportServiceImpl implements ReportService {
	
	@Autowired
	private EligibilityDetailsRepo eligRepo;

	@Override
	public List<String> getUniquePlanNames() {
		return eligRepo.findPlanNames();
	}

	@Override
	public List<String> getUniquePlanStatuses() {
		return eligRepo.findPlanStatuses();
	}

	@Override
	public List<SearchResponse> search(SearchRequest request) {
		
		List<SearchResponse> response=new ArrayList<>();
		
		EligibilityDetails queryBuilder=new EligibilityDetails();
		
		String planName=request.getPlanName();
		if(planName!=null && !planName.equals("")) {
			queryBuilder.setPlanName(planName);
		}
		String planStatus=request.getPlanStatus();
		if(planStatus!=null && !planStatus.equals(planStatus)) {
			queryBuilder.setPlanStatus(planStatus);
		}
		LocalDate planStartDate =request.getPlanStartDate();
		if(planStartDate!=null) {
			queryBuilder.setPlanStartDate(planStartDate);
		}
		LocalDate planEndDate=request.getPlanEndDate();
		if(planEndDate!=null) {
			queryBuilder.setPlanEndDate(planEndDate);
		}
		Example<EligibilityDetails> example=Example.of(queryBuilder);
		
		
		List<EligibilityDetails> entities = eligRepo.findAll(example);
		for(EligibilityDetails entity:entities) {
			SearchResponse sr=new SearchResponse();
			BeanUtils.copyProperties(entity, sr);
			response.add(sr);
		}
		
		return response;
	}

	@Override
	public void generateExcel(HttpServletResponse response) {
		List<EligibilityDetails> entities = eligRepo.findAll();
		
		HSSFWorkbook workBook=new HSSFWorkbook();
		HSSFSheet sheet = workBook.createSheet();
		HSSFRow headerRow = sheet.createRow(0);
		
		headerRow.createCell(0).setCellValue("Name");
		headerRow.createCell(1).setCellValue("Mobile");
		headerRow.createCell(2).setCellValue("Gender");
		headerRow.createCell(3).setCellValue("SSN");
		
		int i=1;
		entities.forEach(entity->{
			HSSFRow dataRow = sheet.createRow(i);
			dataRow.createCell(0).setCellValue(entity.getName());
			dataRow.createCell(1).setCellValue(entity.getMobile());
			dataRow.createCell(2).setCellValue(entity.getGender());
			dataRow.createCell(3).setCellValue(entity.getSsn());
		});


		
		
		
		
		
		
		
		
	}

	@Override
	public void generatePdf(HttpServletResponse response) {

	}

}
