package com.huntech.huntoms.oms.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.huntech.huntoms.oms.repository.BranchDayJdbc;

@Repository
public class BranchDayJdbcImpl implements BranchDayJdbc {

	@Autowired
	private JdbcOperations jdbcTemplate;
	
	public List<Map<String, Object>> selectBranchDayE(Integer orgid,Integer year,Integer month) {
		String sql =" select round(day.e/10000,2) e, round(day.onnete/10000,2)  onnete  , day.month_ month ,  round(day.LOSSE/10000,2)  losse , day.day_ day  ,  day.YEAR_ year ,org.Organ_Name organName" +
				" from  R_Branch_Day day  ,B_Organization org where org.organ_id = day.organ_id  and  org.organ_id ="+orgid +
				" and day.year_ = "+year + " and day.month_= "+month;
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
}
