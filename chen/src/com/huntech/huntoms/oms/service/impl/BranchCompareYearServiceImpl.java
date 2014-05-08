package com.huntech.huntoms.oms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntech.huntoms.oms.dto.BrachYearDto;
import com.huntech.huntoms.oms.repository.BranchCompareYearJdbc;
import com.huntech.huntoms.oms.service.BranchCompareYearService;

@Service
public class BranchCompareYearServiceImpl implements BranchCompareYearService {

	@Autowired
	private BranchCompareYearJdbc branchCompareYearJdbc;
	
	public List<BrachYearDto> getStaYear(Integer orgid ,Integer year) {
		List<Map<String, Object>> selectStaYear = branchCompareYearJdbc.selectStaYear(orgid,year);
		List<BrachYearDto> transferToBrachYearDto = BrachYearDto.transferToBrachYearDto(selectStaYear);
		return transferToBrachYearDto;
	}

}
