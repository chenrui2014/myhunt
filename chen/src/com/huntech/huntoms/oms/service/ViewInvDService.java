package com.huntech.huntoms.oms.service;

import java.util.List;

import com.huntech.huntoms.oms.dto.ViewInvDDto;

public interface ViewInvDService {
	public List<ViewInvDDto> findAll();
	public List<ViewInvDDto> findByOrgIDYearMonthDay(int organ_ID,int Year_,int Month_,int Day_);
	public List<ViewInvDDto> findByOrgIDYMDAsc(int organ_ID,int Year_,int Month_,int Day_,int min_,int max_);
	public List<ViewInvDDto> findByOrgIDYMDInv(int organ_ID,int Year_,int Month_,int Day_,int Inv);
}
