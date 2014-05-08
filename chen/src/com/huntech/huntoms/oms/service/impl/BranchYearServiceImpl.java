package com.huntech.huntoms.oms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huntech.huntoms.oms.dto.BrachYearDto;
import com.huntech.huntoms.oms.repository.BranchYearEJdbc;
import com.huntech.huntoms.oms.service.BrachYearService;

@Service
@Transactional(readOnly = true)
public class BranchYearServiceImpl implements BrachYearService {
	
	
	@Autowired
	private BranchYearEJdbc branchYearEJdbc;


	
	public List<BrachYearDto> getBranchYearE(Integer orgid) {
		List<Map<String,Object>>  list =  branchYearEJdbc.selectBranchYearE(orgid);
		List<BrachYearDto>  listBranch = BrachYearDto.transferToBrachYearDto(list);
		return listBranch;
	}
}
