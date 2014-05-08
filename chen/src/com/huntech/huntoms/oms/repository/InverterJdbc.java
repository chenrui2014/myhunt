package com.huntech.huntoms.oms.repository;

import java.util.List;
import java.util.Map;

public interface InverterJdbc {
	public List<Map<String,Object>> selectInverterSUMEByYM(int organ_ID,int Year_,int Month_);
}
