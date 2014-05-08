package com.huntech.huntoms.oms.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.huntech.huntoms.oms.dto.ViewStaAYDto;
import com.huntech.huntoms.oms.dto.ViewStaYDto;
import com.huntech.huntoms.oms.repository.ViewStaYJdbc;
import com.huntech.huntoms.oms.service.ViewStaYService;
@Service
public class ViewStaYServiceImpl implements ViewStaYService {
	@Autowired
	private ViewStaYJdbc viewStaYJdbc;
	@Override
	public List<ViewStaYDto> findAll() {
		// TODO Auto-generated method stub
		List<Map<String,Object>>  list =  viewStaYJdbc.viewStaYJdbc();
		List<ViewStaYDto>  dtoList = ViewStaYDto.DaoToDto(list);
		return dtoList;
	}
	@Override
	public List<ViewStaYDto> findByYearMonth(int Year_, int Month_) {
		List<Map<String,Object>>  list =  viewStaYJdbc.selectStationMonthEByYM(Year_, Month_);
		List<ViewStaYDto>  dtoList = ViewStaYDto.DaoToDto(list);
		return dtoList;
	}
	@Override
	public List<ViewStaYDto> findByOrgIDandYear(int organ_ID, int Year_) {
		List<Map<String,Object>>  list =  viewStaYJdbc.selectStationMonthEByYandOrgID(organ_ID, Year_);
		List<ViewStaYDto>  dtoList = ViewStaYDto.DaoToDto(list);
		return dtoList;
	}
	@Override
	public List<ViewStaYDto> findByOrgIDandYM(int organ_ID, int Year_,int Month_) {
		List<Map<String,Object>>  list =  viewStaYJdbc.selectStationMonthEByYMandOrgID(organ_ID, Year_, Month_);
		List<ViewStaYDto>  dtoList = ViewStaYDto.DaoToDto(list);
		return dtoList;
	}
	
}
