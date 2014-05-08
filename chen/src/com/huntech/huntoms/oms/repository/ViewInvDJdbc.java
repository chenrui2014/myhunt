package com.huntech.huntoms.oms.repository;

import java.util.List;
import java.util.Map;

public interface ViewInvDJdbc {
	public List<Map<String, Object>> viewInvDJdbc();
	//public List<Map<String,Object>> selectStationAllYearE();
	//public List<Map<String,Object>> selectStationYearEByID(String orgName);
	public List<Map<String,Object>> selectInverterHourEByYMD(int organ_ID,int Year_,int Month_,int Day_);
	public List<Map<String,Object>> selectInvHEByYMDAsc(int organ_ID,int Year_,int Month_,int Day_,int min_,int max_);
	public List<Map<String,Object>> selectInvHEByYMDInv(int organ_ID,int Year_,int Month_,int Day_,int Inv);
	
}
