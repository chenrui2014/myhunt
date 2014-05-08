package com.huntech.huntoms.oms.repository;

import java.util.List;
import java.util.Map;

public interface BranchCompareMonthJdbc {
	
	public List<Map<String,Object>> selectStaMonth(Integer orgid, Integer year,  Integer month);

}
