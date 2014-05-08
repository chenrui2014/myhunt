package com.huntech.huntoms.oms.service;

import java.util.List;

import com.huntech.huntoms.oms.dto.ViewStaMDto;

public interface ViewStaMService {
	public List<ViewStaMDto> findAll();
	public List<ViewStaMDto> findByYearMonthDay(int Year_,int Month_,int Day_);
	public List<ViewStaMDto> findByOrgIDYearMonth(int organ_ID,int Year_,int Month_);
	List<ViewStaMDto> findByOrgIDYMD(int organ_ID, int Year_, int Month_,
			int Day_);
}
