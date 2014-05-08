package com.huntech.huntoms.oms.service;

import java.util.List;

import com.huntech.huntoms.oms.dto.BranchDayDto;

public interface BranchCompareDayService {
	
	
	public List<BranchDayDto>  getStaDay(Integer orgid,Integer parayear,Integer paramonth,Integer day); 

}
