package com.huntech.huntoms.oms.repository;
import java.util.List;
import java.util.Map;

//import com.huntech.huntoms.oms.domain.ViewStaAY;
public interface ViewStaAYJdbc {
	
	
	public List<Map<String, Object>> viewStaAYJdbc();
	public List<Map<String,Object>> selectStationYearEByYear(int Year_);
	public List<Map<String,Object>> selectStationAllYearE();
	public List<Map<String,Object>> selectStationYearEByID(String orgName);
	public List<Map<String,Object>> selectStationYearEByIDandYear_(int orgID,int Year_);
}
