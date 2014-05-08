package com.huntech.huntoms.oms.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

//import com.huntech.huntoms.oms.domain.ViewStaAY;
import com.huntech.huntoms.oms.repository.ViewStaAYJdbc;

@Repository
public class ViewStaAYJdbcImpl  implements ViewStaAYJdbc{
	@Autowired
	//private JdbcOperations jdbcTemplate;
	private JdbcTemplate jdbcTemplate;
	//List<Map<String ,Object>> list;
	public List<Map<String, Object>> viewStaAYJdbc(){
		String sql = "select * from view_sta_allyear where year_=2013";
		List<Map<String ,Object>> list =  jdbcTemplate.queryForList(sql);
		
		//System.out.println(list);
		
		return list;
	}
	
	public List<Map<String,Object>> selectStationAllYearE(){
		String sql = "select * from view_sta_allyear";
		List<Map<String,Object>>  list = jdbcTemplate.queryForList(sql);
		return list;
	}
	public List<Map<String,Object>> selectStationYearEByYear(int Year_){
		String sql = "select * from view_sta_allyear where year_="+Year_;
		List<Map<String,Object>>  list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	public List<Map<String,Object>> selectStationYearEByID(String orgName){
		
			String sql = "select * from view_sta_allyear where organ_name='"+orgName+"'";
			//"select people from 表名 where people like '"+name+"%'"
			List<Map<String,Object>>  list = jdbcTemplate.queryForList(sql);
			return list;		
	}
	public List<Map<String,Object>> selectStationYearEByIDandYear_(int orgID,int Year_){
		
		String sql = "select * from view_sta_allyear where organ_id="+orgID+" and Year_="+Year_;
		//"select people from 表名 where people like '"+name+"%'"
		List<Map<String,Object>>  list = jdbcTemplate.queryForList(sql);
		return list;
	
	
}
}
