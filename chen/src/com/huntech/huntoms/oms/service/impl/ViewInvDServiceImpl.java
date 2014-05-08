package com.huntech.huntoms.oms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntech.huntoms.oms.dto.ViewInvDDto;
import com.huntech.huntoms.oms.dto.ViewStaDDto;
import com.huntech.huntoms.oms.repository.ViewInvDJdbc;
import com.huntech.huntoms.oms.service.ViewInvDService;
@Service
public class ViewInvDServiceImpl implements ViewInvDService{
	@Autowired
	private ViewInvDJdbc viewInvDJdbc;
	@Override
	public List<ViewInvDDto> findAll() {
		List<Map<String,Object>>  list =  viewInvDJdbc.viewInvDJdbc();
		List<ViewInvDDto>  dtoList = ViewInvDDto.DaoToDto(list);
		return dtoList;
	}

	@Override
	public List<ViewInvDDto> findByOrgIDYearMonthDay(int organ_ID, int Year_,
			int Month_, int Day_) {
		List<Map<String,Object>>  list =  viewInvDJdbc.selectInverterHourEByYMD(organ_ID, Year_, Month_, Day_);
		List<ViewInvDDto>  dtoList = ViewInvDDto.DaoToDto(list);
		return dtoList;
	}

	@Override
	public List<ViewInvDDto> findByOrgIDYMDAsc(int organ_ID, int Year_,
			int Month_, int Day_, int min_, int max_) {
		List<Map<String,Object>>  list =  viewInvDJdbc.selectInvHEByYMDAsc(organ_ID, Year_, Month_, Day_, min_, max_);
		List<ViewInvDDto>  dtoList = ViewInvDDto.DaoToDto(list);
		return dtoList;
	}

	@Override
	public List<ViewInvDDto> findByOrgIDYMDInv(int organ_ID, int Year_,
			int Month_, int Day_, int Inv) {
		List<Map<String,Object>>  list =  viewInvDJdbc.selectInvHEByYMDInv(organ_ID, Year_, Month_, Day_, Inv);
		List<ViewInvDDto>  dtoList = ViewInvDDto.DaoToDto(list);
		return dtoList;
	}

}
