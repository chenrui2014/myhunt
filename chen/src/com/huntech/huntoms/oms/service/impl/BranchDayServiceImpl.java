package com.huntech.huntoms.oms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntech.huntoms.oms.dto.BranchDayDto;
import com.huntech.huntoms.oms.repository.BranchDayJdbc;
import com.huntech.huntoms.oms.service.BranchDayService;

@Service
public class BranchDayServiceImpl implements BranchDayService {

	@Autowired
	private BranchDayJdbc  branchDayJdbc;
	
	public List<BranchDayDto> getBranchDayE(Integer orgid,Integer year,Integer month){
		List<Map<String,Object>> list =  branchDayJdbc.selectBranchDayE(orgid,year,month);
		List<BranchDayDto> listBranch = (List<BranchDayDto>)BranchDayDto.transferBranchDayDto(list);
		return listBranch;
	}
}
