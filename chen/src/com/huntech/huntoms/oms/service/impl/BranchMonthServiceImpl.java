package com.huntech.huntoms.oms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntech.huntoms.oms.dto.BranchMonthDto;
import com.huntech.huntoms.oms.repository.BranchMonthJdbc;
import com.huntech.huntoms.oms.service.BranchMonthService;

@Service
public class BranchMonthServiceImpl implements BranchMonthService {

	@Autowired 
	private BranchMonthJdbc branchMonthJdbc;
	
	public List<BranchMonthDto> getBranchMonthE(Integer orgid,Integer year ,Integer month) {
		List<Map<String, Object>> list = branchMonthJdbc.selectBranchMonth(orgid,year,month);
		List<BranchMonthDto>  listDto = BranchMonthDto.transferToBrachYearDto(list);
		return listDto;
	}
}
