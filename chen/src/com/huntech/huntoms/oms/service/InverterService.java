package com.huntech.huntoms.oms.service;

import java.util.List;

import com.huntech.huntoms.oms.dto.ViewInvModelDto;

public interface InverterService {
	public List<ViewInvModelDto> findByOrgIDYearMonth(int organ_ID,int Year_,int Month_);
}
