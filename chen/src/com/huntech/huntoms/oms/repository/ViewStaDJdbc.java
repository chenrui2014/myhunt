package com.huntech.huntoms.oms.repository;

import java.util.List;
import java.util.Map;

public interface ViewStaDJdbc {
	public List<Map<String, Object>> viewStaDJdbc();
	//public List<Map<String,Object>> selectStationAllYearE();
	//public List<Map<String,Object>> selectStationYearEByID(String orgName);
	public List<Map<String,Object>> selectStationHourEByYMD(int organ_ID,int Year_,int Month_,int Day_);
	
}
