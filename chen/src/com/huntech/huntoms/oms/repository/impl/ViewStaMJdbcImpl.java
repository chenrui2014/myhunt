package com.huntech.huntoms.oms.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.huntech.huntoms.oms.repository.ViewStaMJdbc;
@Repository
public class ViewStaMJdbcImpl implements ViewStaMJdbc{
	@Autowired
	private JdbcOperations JdbcTemplate;
	
	
	@Override
	public List<Map<String, Object>> viewStaMJdbc() {
		String sql = "select * from view_sta_month";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		
		return list;
	}


	@Override
	public List<Map<String, Object>> selectStationDayEByYMD(int Year_,
			int Month_, int Day_) {
		String sql = "select * from view_sta_month where year_="
				+Year_+" and month_="+Month_+" and day_="+Day_;
		
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		
		//System.out.println(list);
		return list;
	}


	@Override
	public List<Map<String, Object>> selectStationDayEByOrgIDYM(int organ_ID,
			int Year_, int Month_) {
		
		String sql = "select * from view_sta_month where organ_id="
				+organ_ID+" and year_="+Year_+" and month_="+Month_;
		
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		
		System.out.println(list);
		return list;
	}
	
	
	
	/**
	 * 电站概览数据查询
	 */

	@Override
	public List<Map<String, Object>> selectStationDayEByOrgIDYMD(int organ_ID,
			int Year_, int Month_, int Day_) {
		String sql = "select * from view_sta_month where organ_id="
				+organ_ID+" and year_="+Year_+" and month_="+Month_+" and day_="+Day_;
		
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		
		System.out.println(list);
		return list;
	}

}
