package com.huntech.huntoms.oms.repository;

import java.util.List;
import java.util.Map;

public interface BranchCompareDayJdbc {
	
	public List<Map<String,Object>> selectStaDay(Integer orgid,Integer parayear,Integer paramonth,Integer day);

}
