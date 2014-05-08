package com.huntech.huntoms.oms.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.huntech.huntoms.oms.repository.BranchCompareDayJdbc;

@Repository
public class BranchCompareDayJdbcImpl implements BranchCompareDayJdbc {
	
	@Autowired
	private JdbcOperations jdbcTemplate;

	public List<Map<String, Object>> selectStaDay(Integer orgid,Integer parayear,Integer paramonth,Integer day) {
		String sql ="select views.ORGAN_NAME organName , round(views.E/ views.CAPACITY/10000,2) singlemwe , round(views.eUseHours,2) eusehours ,views.YEAR_ year , " +
				" views.DAY_ day , views.MONTH_ month , views.eusehours  eusehours from VIEW_STA_MONTH views" +
				" where views.organ_id ="+orgid +" and   views.YEAR_ = "+parayear +  " and views.MONTH_ ="+paramonth +" and views.DAY_= "+day;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}

}
