package com.huntech.huntoms.oms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntech.huntoms.oms.dto.ViewInvModelDto;
import com.huntech.huntoms.oms.repository.InverterJdbc;
import com.huntech.huntoms.oms.service.InverterService;

@Service
public class InverterServiceImpl implements InverterService{
	@Autowired
	private InverterJdbc inverterJdbc;
	@Override
	public List<ViewInvModelDto> findByOrgIDYearMonth(int organ_ID, int Year_,
			int Month_) {
		List<Map<String,Object>>  list =  inverterJdbc.selectInverterSUMEByYM(organ_ID, Year_, Month_);
		List<ViewInvModelDto>  dtoList = ViewInvModelDto.DaoToDto(list);
		return dtoList;
	}

}
