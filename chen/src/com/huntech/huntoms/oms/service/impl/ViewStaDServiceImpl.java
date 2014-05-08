package com.huntech.huntoms.oms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntech.huntoms.oms.dto.ViewStaDDto;
import com.huntech.huntoms.oms.dto.ViewStaMDto;
import com.huntech.huntoms.oms.repository.ViewStaDJdbc;
import com.huntech.huntoms.oms.service.ViewStaDService;

@Service
public class ViewStaDServiceImpl implements ViewStaDService{

	@Autowired
	private ViewStaDJdbc viewStaDJdbc;
	@Override
	public List<ViewStaDDto> findAll() {
		List<Map<String,Object>>  list =  viewStaDJdbc.viewStaDJdbc();
		List<ViewStaDDto>  dtoList = ViewStaDDto.DaoToDto(list);
		return dtoList;
	}

	@Override
	public List<ViewStaDDto> findByOrgIDYearMonthDay(int organ_ID, int Year_,
			int Month_, int Day_) {
		List<Map<String,Object>> list=viewStaDJdbc.selectStationHourEByYMD(organ_ID, Year_, Month_, Day_);
		List<ViewStaDDto> dtoList=ViewStaDDto.DaoToDto(list);
		
		return dtoList;
	}

}
