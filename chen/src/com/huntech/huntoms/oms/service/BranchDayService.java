package com.huntech.huntoms.oms.service;

import java.util.List;

import com.huntech.huntoms.oms.dto.BranchDayDto;


public interface BranchDayService {
	
	public List<BranchDayDto> getBranchDayE(Integer orgid,Integer year,Integer month);

}
