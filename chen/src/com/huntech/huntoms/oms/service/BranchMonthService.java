package com.huntech.huntoms.oms.service;

import java.util.List;

import com.huntech.huntoms.oms.dto.BranchMonthDto;

public interface BranchMonthService {
	
	public List<BranchMonthDto> getBranchMonthE(Integer orgid,Integer year ,Integer month); 

}
