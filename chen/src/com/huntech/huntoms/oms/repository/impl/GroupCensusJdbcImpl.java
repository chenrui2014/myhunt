package com.huntech.huntoms.oms.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.huntech.huntoms.oms.repository.GroupCensusJdbc;

@Repository
public class GroupCensusJdbcImpl implements GroupCensusJdbc {

	@Autowired
	private JdbcOperations JdbcTemplate;
	
	@Override
	public List<Map<String, Object>> getGroupYearConditionData() {
		String sql = "SELECT DISTINCT g.year_ AS gyear, p.year_ AS pyear" +
				" FROM r_group_month g LEFT OUTER JOIN b_organization o ON o.organ_id=g.organ_id" +
				" LEFT OUTER JOIN n_group_planmonth p ON o.organ_id=p.organ_id " +
				" WHERE g.year_ IS NOT NULL OR p.year_ IS NOT NULL" + 
				" ORDER BY gyear";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}
	
	public List<Map<String, Object>> getGroupMonthConditionData() {
		String sql = "SELECT DISTINCT g.year_ AS year" +
				" FROM r_group_day g LEFT OUTER JOIN b_organization o ON o.organ_id=g.organ_id" +
				" WHERE g.year_ IS NOT NULL" + 
				" ORDER BY year";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getEOverYearData() {
		String sql = "select o.organ_id AS organ_id, o.organ_name AS organ_name, g.year_ AS year," +
				" TRUNC(g.e/10000,2) AS e, TRUNC(g.theorye/10000,2) AS theorye, TRUNC(g.onnete/10000,2) AS onnete, TRUNC(p.plan_e/10000,2) AS plane" +
				" FROM r_group_year g LEFT OUTER JOIN b_organization o ON o.organ_id=g.organ_id" +
				" LEFT OUTER JOIN n_gruop_planyear p ON o.organ_id=p.organ_id ORDER BY year";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getEYearData(Long year) {
		String sql = "SELECT o.organ_id as organ_id, o.organ_name as organ_name, g.year_ as year, g.month_ as month," +
				" TRUNC(g.e/10000,2) as e, TRUNC(g.theorye/10000,2) as theorye, TRUNC(g.onnete/10000,2) as onnete, TRUNC(p.plan_e/10000,2) as plane" +
				" FROM b_organization o LEFT OUTER JOIN r_group_month g ON o.organ_id=g.organ_id" +
				" LEFT OUTER JOIN n_group_planmonth p ON g.organ_id=p.organ_id AND g.year_ = p.year_ AND g.month_=p.month_" +
				" WHERE g.year_=" + year +
				" ORDER BY year,month";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		System.out.println(list.size());
		return list;
	}

	@Override
	public List<Map<String, Object>> getEMonthData(Long year, Long month) {
		String sql = "SELECT o.organ_id AS organ_id, o.organ_name AS organ_name, g.year_ AS year, g.month_ AS month, g.day_ AS day," +
				" TRUNC(g.e/10000,2) AS e, TRUNC(g.onnete/10000,2) AS onnete" +
				" FROM b_organization o LEFT OUTER JOIN r_group_day g ON o.organ_id=g.organ_id" +
				" WHERE g.year_=" + year + " AND g.month_=" + month +
				" ORDER BY year,month,day";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getBranchEYearData(Long id,Long year) {
		String sql = "SELECT o.organ_id as organ_id, o.organ_name as organ_name, g.year_ as year, g.month_ as month," +
				" TRUNC(g.e/10000,2) as e, TRUNC(g.theorye/10000,2) as theorye, TRUNC(g.onnete/10000,2) as onnete, TRUNC(p.plan_e/10000,2) as plane" +
				" FROM b_organization o LEFT OUTER JOIN r_group_month g ON o.organ_id=g.organ_id" +
				" LEFT OUTER JOIN n_branch_planmonth p ON g.organ_id=p.organ_id AND g.year_ = p.year_ AND g.month_=p.month_" +
				" WHERE g.year_=" + year +"AND o.organ_id="+id+
				" ORDER BY year,month";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		System.out.println(list.size());
		return list;
	}

	@Override
	public List<Map<String, Object>> getBranchEMonthData(Long id,Long year, Long month) {
		String sql = "SELECT o.organ_id AS organ_id, o.organ_name AS organ_name, g.year_ AS year, g.month_ AS month, g.day_ AS day," +
				" TRUNC(g.e/10000,2) AS e, TRUNC(g.onnete/10000,2) AS onnete" +
				" FROM b_organization o LEFT OUTER JOIN r_group_day g ON o.organ_id=g.organ_id" +
				" WHERE g.year_=" + year + " AND g.month_=" + month + "AND o.organ_id="+id+
				" ORDER BY year,month,day";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getEfficEOverYearData() {
		String sql = "SELECT o.organ_name as organ_name, g.year_ AS year, TRUNC(g.e/g.theorye,2) AS effice, TRUNC((g.e/o.capacity)/10000,2) AS singleMWE" +
				" FROM r_group_year g LEFT OUTER JOIN b_organization o ON o.organ_id=g.organ_id" +
				" ORDER BY year";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getEfficEYearData(Long year) {
		String sql = "SELECT o.organ_name as organ_name, g.year_ AS year, g.month_ AS month, TRUNC(g.e/g.theorye,2) AS effice, TRUNC((g.e/o.capacity)/10000,2) AS singleMWE" +
				" FROM b_organization o LEFT OUTER JOIN r_group_month g ON o.organ_id=g.organ_id" +
				" LEFT OUTER JOIN n_group_planmonth p ON g.organ_id=p.organ_id AND g.year_=p.year_ AND g.month_=p.month_" +
				" WHERE g.year_=" + year +
				" ORDER BY year,month";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getPlanEOverYearData() {
		String sql = "SELECT o.organ_name as organ_name, g.year_ AS year, TRUNC((g.e/p.plan_e)*100,2) AS plancomplete, TRUNC(p.plan_e/10000,2) AS plane, TRUNC(g.e/10000,2) AS e" +
				" FROM r_group_year g LEFT OUTER JOIN b_organization o ON o.organ_id=g.organ_id" +
				" LEFT OUTER JOIN n_gruop_planyear p ON g.organ_id=p.organ_id AND g.year_ = p.year_" +
				" ORDER BY year";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getPlanEYearData(Long year) {
		String sql = "SELECT o.organ_name as organ_name, g.year_ AS year, g.month_ AS month, TRUNC((g.e/p.plan_e)*100,2) AS plancomplete, TRUNC(p.plan_e/10000,2) AS plane, TRUNC(g.e/10000,2) AS e" +
				" FROM b_organization o LEFT OUTER JOIN r_group_month g ON o.organ_id=g.organ_id" +
				" LEFT OUTER JOIN n_group_planmonth p ON g.organ_id=p.organ_id AND g.year_=p.year_ AND g.month_=p.month_" +
				" WHERE g.year_=" + year +
				" ORDER BY year,month";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getPlanOnNetEOverYearData() {
		String sql = "SELECT o.organ_name as organ_name, g.year_ AS year, TRUNC((g.onnete/p.plan_one)*100,2) AS plancomplete, TRUNC(p.plan_one/10000,2) AS planonnete, TRUNC(g.onnete/10000,2) AS onnete" +
				" FROM r_group_year g LEFT OUTER JOIN b_organization o ON o.organ_id=g.organ_id" +
				" LEFT OUTER JOIN n_gruop_planyear p ON g.organ_id=p.organ_id AND g.year_ = p.year_" +
				" ORDER BY year";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getPlanOnNetEYearData(Long year) {
		String sql = "SELECT o.organ_name as organ_name, g.year_ AS year, g.month_ AS month, TRUNC((g.onnete/p.plan_one)*100,2) AS plancomplete, TRUNC(p.plan_one/10000,2) AS planonnete, TRUNC(g.onnete/10000,2) AS onnete" +
				" FROM b_organization o LEFT OUTER JOIN r_group_month g ON o.organ_id=g.organ_id" +
				" LEFT OUTER JOIN n_group_planmonth p ON g.organ_id=p.organ_id AND g.year_=p.year_ AND g.month_=p.month_" +
				" WHERE g.year_=" + year +
				" ORDER BY year,month";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getTotalEOverYearData() {
		String sql = "SELECT o.organ_name as organ_name, g.year_ AS year, TRUNC(g.factorye/10000,2) AS factorye, TRUNC((g.factorye+g.purchasenete)/10000,2) AS comprefactorye," +
				" TRUNC((g.factorye/g.e)*100,2) AS factoryescale, TRUNC(((g.factorye+g.purchasenete)/g.e)*100,2) AS comprefactoryescale" +
				" FROM r_group_year g LEFT OUTER JOIN b_organization o ON o.organ_id=g.organ_id" +
				" ORDER BY year";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getTotalEYearData(Long year) {
		String sql = "SELECT o.organ_name as organ_name, g.year_ AS year, g.month_ AS month, TRUNC(g.factorye/10000,2) AS factorye, TRUNC((g.factorye+g.purchasenete)/10000,2) AS comprefactorye," +
				" TRUNC((g.factorye/g.e)*100,2) AS factoryescale, TRUNC(((g.factorye+g.purchasenete)/g.e)*100,2) AS comprefactoryescale" +
				" FROM r_group_month g LEFT OUTER JOIN b_organization o ON o.organ_id=g.organ_id" +
				" WHERE g.year_=" + year +
				" ORDER BY year,month";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getGroupOrganData(Long year) {
		String sql = "SELECT o.organ_name as organ_name, o.capacity AS capacity, TRUNC(g.e/10000,2) AS e, TRUNC(p.plan_e/10000,2) AS plane," +
				" TRUNC((g.e/p.plan_e)*100,2) AS effice, TRUNC(((g.e-g1.e)/g1.e)*100,2) AS etoyester, TRUNC(((p.plan_e-p1.plan_e)/p1.plan_e)*100,2) AS planetoyester" +
				" FROM r_group_year g LEFT OUTER JOIN b_organization o ON o.organ_id=g.organ_id" +
				" LEFT OUTER JOIN n_gruop_planyear p ON g.year_=p.year_" +
				" LEFT OUTER JOIN r_group_year g1 ON g.year_=(g1.year_+1)  LEFT OUTER JOIN n_gruop_planyear p1 ON g1.year_=p1.year_" +
				" WHERE g.year_=" + year;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getGroupOrganData(Long year, Long month) {
		String sql = "SELECT o.organ_name as organ_name, o.capacity AS capacity, TRUNC(g.e/10000,2) AS e, TRUNC(p.plan_e/10000,2) AS plane," +
				" TRUNC((g.e/p.plan_e)*100,2) AS effice, TRUNC(((g.e-g1.e)/g1.e)*100,2) AS etoyester, TRUNC(((p.plan_e-p1.plan_e)/p1.plan_e)*100,2) AS planetoyester" +
				" FROM r_group_month g LEFT OUTER JOIN b_organization o ON o.organ_id=g.organ_id" +
				" LEFT OUTER JOIN n_group_planmonth p ON g.year_=p.year_" +
				" LEFT OUTER JOIN r_group_month g1 ON g.year_=(g1.year_+1)  LEFT OUTER JOIN n_group_planmonth p1 ON g1.year_=p1.year_" +
				" WHERE g.year_=" + year+"AND g.month_="+month;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getBranchOrganData(Long id, Long year) {
		String sql = "SELECT o.organ_name as organ_name, o.capacity AS capacity, TRUNC(g.e/10000,2) AS e, TRUNC(p.plan_e/10000,2) AS plane," +
				" TRUNC((g.e/p.plan_e)*100,2) AS effice, TRUNC(((g.e-g1.e)/g1.e)*100,2) AS etoyester, TRUNC(((p.plan_e-p1.plan_e)/p1.plan_e)*100,2) AS planetoyester" +
				" FROM r_branch_year g LEFT OUTER JOIN b_organization o ON o.organ_id=g.organ_id" +
				" LEFT OUTER JOIN n_branch_planyear p ON g.year_=p.year_" +
				" LEFT OUTER JOIN r_branch_year g1 ON g.year_=(g1.year_+1)  LEFT OUTER JOIN n_gruop_planyear p1 ON g1.year_=p1.year_" +
				" WHERE g.year_=" + year + " AND o.organ_id="+id;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getBranchOrganData(Long id, Long year,
			Long month) {
		String sql = "SELECT o.organ_name as organ_name, o.capacity AS capacity, TRUNC(g.e/10000,2) AS e, TRUNC(p.plan_e/10000,2) AS plane," +
				" TRUNC((g.e/p.plan_e)*100,2) AS effice, TRUNC(((g.e-g1.e)/g1.e)*100,2) AS etoyester, TRUNC(((p.plan_e-p1.plan_e)/p1.plan_e)*100,2) AS planetoyester" +
				" FROM r_branch_month g LEFT OUTER JOIN b_organization o ON o.organ_id=g.organ_id" +
				" LEFT OUTER JOIN n_branch_planmonth p ON g.year_=p.year_" +
				" LEFT OUTER JOIN r_branch_month g1 ON g.year_=(g1.year_+1)  LEFT OUTER JOIN n_group_planmonth p1 ON g1.year_=p1.year_" +
				" WHERE g.year_=" + year+"AND g.month_="+month + " AND o.organ_id="+id;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

}
