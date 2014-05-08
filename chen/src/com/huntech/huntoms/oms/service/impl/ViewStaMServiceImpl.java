package com.huntech.huntoms.oms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntech.huntoms.oms.dto.ViewStaMDto;
import com.huntech.huntoms.oms.repository.ViewStaMJdbc;
import com.huntech.huntoms.oms.service.ViewStaMService;

@Service
public class ViewStaMServiceImpl implements ViewStaMService{

	@Autowired
	private ViewStaMJdbc viewStaMJdbc;
	
	public List<ViewStaMDto> findAll() {
		// TODO Auto-generated method stub
		List<Map<String,Object>>  list =  viewStaMJdbc.viewStaMJdbc();
		List<ViewStaMDto>  dtoList = ViewStaMDto.DaoToDto(list);
		return dtoList;
	}
	@Override
	public List<ViewStaMDto> findByYearMonthDay(int Year_, int Month_, int Day_) {
		List<Map<String,Object>>  list =  viewStaMJdbc.selectStationDayEByYMD(Year_, Month_, Day_);
		List<ViewStaMDto>  dtoList = ViewStaMDto.DaoToDto(list);
		return dtoList;
	}
	@Override
	public List<ViewStaMDto> findByOrgIDYearMonth(int organ_ID, int Year_,
			int Month_) {
		List<Map<String,Object>>  list =  viewStaMJdbc.selectStationDayEByOrgIDYM(organ_ID, Year_, Month_);
		List<ViewStaMDto>  dtoList = ViewStaMDto.DaoToDto(list);
		return dtoList;
	}
	@Override
	public List<ViewStaMDto> findByOrgIDYMD(int organ_ID, int Year_,
			int Month_,int Day_) {
		List<Map<String,Object>>  list =  viewStaMJdbc.selectStationDayEByOrgIDYMD(organ_ID, Year_, Month_, Day_);
		List<ViewStaMDto>  dtoList = ViewStaMDto.DaoToDto(list);
		return dtoList;
	}
}
