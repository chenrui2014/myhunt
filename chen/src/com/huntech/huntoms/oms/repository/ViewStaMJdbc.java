package com.huntech.huntoms.oms.repository;

import java.util.List;
import java.util.Map;

public interface ViewStaMJdbc {
	public List<Map<String, Object>> viewStaMJdbc();
	//public List<Map<String,Object>> selectStationAllYearE();
	//public List<Map<String,Object>> selectStationYearEByID(String orgName);
	public List<Map<String,Object>> selectStationDayEByYMD(int Year_,int Month_,int Day_);
	public List<Map<String,Object>> selectStationDayEByOrgIDYM(int organ_ID,int Year_,int Month_);
	List<Map<String, Object>> selectStationDayEByOrgIDYMD(int organ_ID,
			int Year_, int Month_, int Day_);//电站概览数据查询

}
