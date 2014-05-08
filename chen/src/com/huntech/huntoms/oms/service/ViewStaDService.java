package com.huntech.huntoms.oms.service;

import java.util.List;

import com.huntech.huntoms.oms.dto.ViewStaDDto;

public interface ViewStaDService {
	public List<ViewStaDDto> findAll();
	public List<ViewStaDDto> findByOrgIDYearMonthDay(int organ_ID,int Year_,int Month_,int Day_);
}
