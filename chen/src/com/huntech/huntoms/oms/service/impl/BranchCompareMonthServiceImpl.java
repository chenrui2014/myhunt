package com.huntech.huntoms.oms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntech.huntoms.oms.dto.BranchMonthDto;
import com.huntech.huntoms.oms.repository.BranchCompareMonthJdbc;
import com.huntech.huntoms.oms.service.BranchCompareMonthService;


@Service
public class BranchCompareMonthServiceImpl implements BranchCompareMonthService {

	@Autowired 
	private BranchCompareMonthJdbc branchCompareMonthJdbc;
	
	public List<BranchMonthDto> getStaMonth(Integer orgid, Integer year,  Integer month ) {
		//List<Map<String,Object>> list = branchMonthJdbc.selectBranchMonth(orgid,year,month);
		List<Map<String,Object>> list =branchCompareMonthJdbc.selectStaMonth(orgid,year,month);
		List<BranchMonthDto> listDto = BranchMonthDto.transferToBrachYearDto(list);
		return listDto;
	}
}
