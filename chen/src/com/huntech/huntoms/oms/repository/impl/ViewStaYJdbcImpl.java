package com.huntech.huntoms.oms.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.huntech.huntoms.oms.repository.ViewStaYJdbc;

@Repository
public class ViewStaYJdbcImpl implements ViewStaYJdbc{
	@Autowired	
	private JdbcOperations JdbcTemplate;

	public List<Map<String, Object>> viewStaYJdbc(){
		String sql = "select * from view_sta_year";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		
		return list;
	}

	@Override
	public List<Map<String, Object>> selectStationMonthEByYM(int Year_,
			int Month_) {
		String sql = "select * from view_sta_year where year_="+Year_+" and month_="+Month_;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> selectStationMonthEByYandOrgID(
			int organ_ID, int Year_) {
		String sql = "select * from view_sta_year where organ_id="+organ_ID+" and year_="+Year_;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> selectStationMonthEByYMandOrgID(
			int organ_ID, int Year_, int Month_) {
		String sql = "select * from view_sta_year where organ_id="+	organ_ID+" and year_="+Year_+" and month_="+Month_;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}
	
	

}
