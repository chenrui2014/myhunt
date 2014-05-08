package com.huntech.huntoms.oms.repository;

import java.util.List;
import java.util.Map;

public interface BranchMonthJdbc {
	
	public List<Map<String,Object>> selectBranchMonth(Integer orgid, Integer year,  Integer month);

}
