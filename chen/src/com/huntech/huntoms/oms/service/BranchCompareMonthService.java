package com.huntech.huntoms.oms.service;

import java.util.List;

import com.huntech.huntoms.oms.dto.BranchMonthDto;

public interface BranchCompareMonthService {
	
	public List<BranchMonthDto> getStaMonth(Integer orgid, Integer year,  Integer month);

}
