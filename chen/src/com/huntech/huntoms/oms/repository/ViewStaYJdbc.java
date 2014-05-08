package com.huntech.huntoms.oms.repository;

import java.util.List;
import java.util.Map;

public interface ViewStaYJdbc {
	public List<Map<String, Object>> viewStaYJdbc();
	//public List<Map<String,Object>> selectStationAllYearE();
	//public List<Map<String,Object>> selectStationYearEByID(String orgName);
	public List<Map<String,Object>> selectStationMonthEByYM(int Year_,int Month_);
	public List<Map<String,Object>> selectStationMonthEByYandOrgID(int organ_ID,int Year_);
	List<Map<String, Object>> selectStationMonthEByYMandOrgID(int organ_ID,
			int Year_, int Month_);
}
