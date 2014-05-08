package com.huntech.huntoms.oms.service;

import java.util.List;

import com.huntech.huntoms.oms.dto.ViewStaAYDto;

public interface ViewStaAYService {
	/**
	 * 获取全部信息
	 * @return List<viewStaAY>
	 */
	public List<ViewStaAYDto> findAll();
	public List<ViewStaAYDto> findByYear(int Year_);
	public List<ViewStaAYDto> findByOrgID(String orgName);
	public List<ViewStaAYDto> findByOrgIDAndYear_(int orgID,int Year_);
}
