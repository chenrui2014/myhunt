package com.huntech.huntoms.oms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntech.huntoms.oms.dto.BranchDayDto;
import com.huntech.huntoms.oms.repository.BranchCompareDayJdbc;
import com.huntech.huntoms.oms.service.BranchCompareDayService;

@Service
public class BranchCompareDayServiceImpl implements BranchCompareDayService{
	
	@Autowired
	private BranchCompareDayJdbc branchCompareDayJdbc;
	
	public List<BranchDayDto> getStaDay(Integer orgid,Integer parayear,Integer paramonth,Integer day) {
		List<Map<String, Object>> list = branchCompareDayJdbc.selectStaDay(orgid,parayear,paramonth,day);
		List<BranchDayDto> listDto = BranchDayDto.transferBranchDayDto(list);
		return listDto;
	}
}
