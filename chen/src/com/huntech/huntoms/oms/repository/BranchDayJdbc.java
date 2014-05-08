package com.huntech.huntoms.oms.repository;

import java.util.List;
import java.util.Map;

public interface BranchDayJdbc {
	
	List<Map<String,Object>> selectBranchDayE(Integer orgid,Integer year,Integer month);

}
