package com.huntech.huntoms.oms.service;

import java.util.List;

import com.huntech.huntoms.oms.dto.BrachYearDto;

public interface BranchCompareYearService {
	
	public List<BrachYearDto> getStaYear(Integer orgid ,Integer year );

}
