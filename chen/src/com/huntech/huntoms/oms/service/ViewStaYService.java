package com.huntech.huntoms.oms.service;

import java.util.List;

import com.huntech.huntoms.oms.dto.ViewStaYDto;

public interface ViewStaYService {
	public List<ViewStaYDto> findAll();
	public List<ViewStaYDto> findByYearMonth(int Year_,int Month_);
	public List<ViewStaYDto> findByOrgIDandYear(int organ_ID,int Year_);
	//public List<ViewStaAYDto> findByOrgID(String orgName);
	List<ViewStaYDto> findByOrgIDandYM(int organ_ID, int Year_, int Month_);
}
