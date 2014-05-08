package com.huntech.huntoms.oms.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.huntech.huntoms.oms.repository.BranchCompareMonthJdbc;

@Repository
public class BranchCompareMonthJdbcImpl implements BranchCompareMonthJdbc {

	@Autowired
	private JdbcOperations jdbcTemplate;
	
	public List<Map<String, Object>> selectStaMonth(Integer orgid, Integer year,  Integer month) {
		String sql ="select views.ORGAN_NAME organName , round(views.E/ views.CAPACITY/10000,2) singlemwe ,  views.CAPACITY  capacity ," +
				"    round(views.E/views.theoryE,4)*100  effict , views.YEAR_ year  , round(views.eUseHours,2) eusehours , views.month_ bmonth " +
				"  , round(views.E/10000,2) e , round(views.PLAN_E/10000,2) planE , round(views.E/views.PLAN_E,4)*100 planEffict "+
				"  , round(views.ONNETE/10000,2) onnete , round(views.PLAN_ONE/10000,2) planOne , round(views.ONNETE/views.PLAN_ONE,4)*100 planOnnetEffict "+
				"  , round(views.FACTORYE/10000,2) factorye , round(views.FACTORYE/views.E,4)*100 factoryEffict "+
				"  , round((views.E+views.purchaseNetE-views.onNetE)/10000,2) sumFactorye, round((views.E+views.purchaseNetE-views.onNetE)/views.E,4)*100 sumFactoryEffict"+
				"   from VIEW_STA_YEAR views  where views.organ_pid = "+orgid;
		if(year>0){
			sql += " and  views.YEAR_ = "+year;
		}
		if(month>0){
			sql += " and  views.MONTH_ = "+month;
		}
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
}
