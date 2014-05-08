package com.huntech.huntoms.oms.repository;

import java.util.List;
import java.util.Map;

public interface BranchYearEJdbc {
	public List<Map<String,Object>> selectBranchYearE(Integer orgid);
}
