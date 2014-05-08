package com.huntech.huntoms.oms.repository;

import java.util.List;
import java.util.Map;

public interface BranchCompareYearJdbc {
	
	
	public List<Map<String,Object>> selectStaYear(Integer orgid,Integer year);

}
