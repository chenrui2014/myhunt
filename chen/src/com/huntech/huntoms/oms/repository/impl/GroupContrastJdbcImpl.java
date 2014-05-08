package com.huntech.huntoms.oms.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.huntech.huntoms.oms.repository.GroupContrastJdbc;

@Repository
public class GroupContrastJdbcImpl implements GroupContrastJdbc {

	@Autowired
	private JdbcOperations JdbcTemplate;
	
	@Override
	public List<Map<String, Object>> getBranchStationRankingYearData(Long id, Long year) {
		String sql = "SELECT o.organ_name AS name, o.capacity AS capacity, TRUNC((s.e/s.theorye)*100,2) AS effice, TRUNC((s.e/o.capacity)/10000,2) AS singleMWE, TRUNC((s.e/o.capacity),2) AS sameusehour," +
	            " TRUNC(s.e/10000,2) AS e, TRUNC((s.e/p.plan_e)*100,2) AS escale, TRUNC((s.factorye/s.e)*100,2) AS factoryscale, " +
				" TRUNC((s.onnete/p.plan_one)*100,2) AS onnetescale, TRUNC(s.onnete/10000,2) AS onnete, TRUNC(((s.factorye+s.purchasenete)/s.e)*100,2) AS comprefactoryescale" +
				" FROM r_station_year s LEFT OUTER JOIN b_organization o ON o.organ_id=s.organ_id" +
				" LEFT OUTER JOIN n_station_planyear p ON o.organ_id=p.organ_id AND s.year_=p.year_" +
				" WHERE s.year_=" + year + "AND o.organ_pid=" + id;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getBranchStationRankingMonthData(Long id, Long year,
			Long month) {
		String sql = "SELECT o.organ_name AS name, TRUNC((s.e/s.theorye),2) AS effice, TRUNC((s.e/o.capacity)/10000,2) AS singleMWE, TRUNC((s.e/o.capacity),2) AS sameusehour," +
	            " TRUNC(s.e/10000,2) AS e, TRUNC(s.e/p.plan_e,2) AS escale, TRUNC(s.factorye/s.e,2) AS factoryscale, " +
				" TRUNC((s.onnete/p.plan_one)*100,2) AS onnetescale, TRUNC(s.onnete/10000,2) AS onnete, TRUNC(((s.factorye+s.purchasenete)/s.e)*100,2) AS comprefactoryescale" +
				" FROM r_station_month s LEFT OUTER JOIN b_organization o ON o.organ_id=s.organ_id" +
				" LEFT OUTER JOIN n_station_planmonth p ON o.organ_id=p.organ_id AND s.year_=p.year_ AND s.month_=p.month_" +
				" WHERE s.year_=" + year + "AND s.month_=" + month+ "AND o.organ_pid=" + id;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getStationRankingYearData(Long year) {
		String sql = "SELECT o.organ_name AS name, TRUNC((s.e/s.theorye),2) AS effice, TRUNC((s.e/o.capacity)/10000,2) AS singleMWE, TRUNC((s.e/o.capacity),2) AS sameusehour," +
	            " TRUNC(s.e/10000,2) AS e, TRUNC((s.e/p.plan_e)*100,2) AS escale, TRUNC(s.factorye/s.e,2) AS factoryscale, " +
				" TRUNC((s.onnete/p.plan_one)*100,2) AS onnetescale, TRUNC(s.onnete/10000,2) AS onnete, TRUNC(((s.factorye+s.purchasenete)/s.e)*100,2) AS comprefactoryescale" +
				" FROM r_station_year s LEFT OUTER JOIN b_organization o ON o.organ_id=s.organ_id" +
				" LEFT OUTER JOIN n_station_planyear p ON o.organ_id=p.organ_id AND s.year_=p.year_" +
				" WHERE s.year_=" + year;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getStationRankingMonthData(Long year,
			Long month) {
		String sql = "SELECT o.organ_name AS name, TRUNC(((s.e/s.theorye))*100,2) AS effice, TRUNC((s.e/o.capacity)/10000,2) AS singleMWE, TRUNC((s.e/o.capacity),2) AS sameusehour," +
	            " TRUNC(s.e/10000,2) AS e, TRUNC(s.e/p.plan_e,2) AS escale, TRUNC(s.factorye/s.e,2) AS factoryscale, " +
				" TRUNC((s.onnete/p.plan_one)*100,2) AS onnetescale, TRUNC(s.onnete/10000,2) AS onnete, TRUNC(((s.factorye+s.purchasenete)/s.e)*100,2) AS comprefactoryescale" +
				" FROM r_station_month s LEFT OUTER JOIN b_organization o ON o.organ_id=s.organ_id" +
				" LEFT OUTER JOIN n_station_planmonth p ON o.organ_id=p.organ_id AND s.year_=p.year_ AND s.month_=p.month_" +
				" WHERE s.year_=" + year + "AND s.month_=" + month;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getStationYearConditionData() {
		String sql = "SELECT DISTINCT s.year_ AS syear, p.year_ AS pyear" +
				" FROM r_station_month s LEFT OUTER JOIN b_organization o ON o.organ_id=s.organ_id" +
				" LEFT OUTER JOIN n_station_planmonth p ON o.organ_id=p.organ_id " +
				" WHERE s.year_ IS NOT NULL OR p.year_ IS NOT NULL" + 
				" ORDER BY syear";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}
	
	public List<Map<String, Object>> getStationMonthConditionData() {
		String sql = "SELECT DISTINCT s.year_ AS year" +
				" FROM r_group_day s LEFT OUTER JOIN b_organization o ON o.organ_id=s.organ_id" +
				" WHERE s.year_ IS NOT NULL" + 
				" ORDER BY year";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getStationDevoteEYearData(Long year) {
		String sql = "SELECT o.organ_name as name,  TRUNC(o.capacity,2) AS capacity, TRUNC(s.e/10000,2) AS e, TRUNC(s.onnete/10000,2) AS onnete" +
				" FROM b_organization o LEFT OUTER JOIN r_station_year s ON o.organ_id=s.organ_id" +
				" WHERE s.year_=" + year;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getStationDevoteEMonthData(Long year,
			Long month) {
		String sql = "SELECT o.organ_name as name,  TRUNC(o.capacity,2) AS capacity, TRUNC(s.e/10000,2) AS e, TRUNC(s.onnete/10000,2) AS onnete" +
				" FROM b_organization o LEFT OUTER JOIN r_station_month s ON o.organ_id=s.organ_id" +
				" WHERE s.year_=" + year + "AND s.month_=" + month;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getStationFactoryEYearData(Long year) {
		String sql = "SELECT o.organ_name as name,  TRUNC((s.factorye/s.e)*100,2) AS factoryscale, TRUNC(s.e/10000,2) AS e, TRUNC(s.factorye/10000,2) AS factorye" +
				" FROM b_organization o LEFT OUTER JOIN r_station_year s ON o.organ_id=s.organ_id" +
				" WHERE s.year_=" + year;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getStationFactoryEMonthData(Long year,
			Long month) {
		String sql = "SELECT o.organ_name as name,  TRUNC((s.factorye/s.e)*100,2) AS factoryscale, TRUNC(s.e/10000,2) AS e, TRUNC(s.factorye/10000,2) AS factorye" +
				" FROM b_organization o LEFT OUTER JOIN r_station_month s ON o.organ_id=s.organ_id" +
				" WHERE s.year_=" + year + " AND s.month_=" + month;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	
	@Override
	public List<Map<String, Object>> getStationCompreFactoryEYearData(Long year) {
		String sql = "SELECT o.organ_name as name,  TRUNC(((s.factorye+s.purchasenete)/s.e)*100,2) AS comprefactoryscale, TRUNC(s.e/10000,2) AS e, TRUNC((s.factorye+s.purchasenete)/10000,2) AS comprefactorye" +
				" FROM b_organization o LEFT OUTER JOIN r_station_year s ON o.organ_id=s.organ_id" +
				" WHERE s.year_=" + year;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getStationCompreFactoryEMonthData(Long year,
			Long month) {
		String sql = "SELECT o.organ_name as name,  TRUNC((s.factorye/s.e)*100,2) AS comprefactoryscale, TRUNC(s.e/10000,2) AS e, TRUNC((s.factorye+s.purchasenete)/10000,2) AS comprefactorye" +
				" FROM b_organization o LEFT OUTER JOIN r_station_month s ON o.organ_id=s.organ_id" +
				" WHERE s.year_=" + year + " AND s.month_=" + month;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getStationPlanEYearData(Long year) {
		String sql = "SELECT o.organ_name as name,  TRUNC((s.e/p.plan_e)*100,2) AS escale, TRUNC(s.e/10000,2) AS e, TRUNC(p.plan_e/10000,2) AS plane" +
				" FROM b_organization o LEFT OUTER JOIN r_station_year s ON o.organ_id=s.organ_id"+
				" LEFT OUTER JOIN n_station_planyear p ON s.organ_id=p.organ_id AND s.year_=p.year_" +
				" WHERE s.year_=" + year;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getStationPlanEMonthData(Long year,
			Long month) {
		String sql = "SELECT o.organ_name as name,  TRUNC((s.e/p.plan_e)*100,2) AS escale, TRUNC(s.e/10000,2) AS e, TRUNC(p.plan_e/10000,2) AS plane" +
				" FROM b_organization o LEFT OUTER JOIN r_station_month s ON o.organ_id=s.organ_id"+
				" LEFT OUTER JOIN n_station_planmonth p ON s.organ_id=p.organ_id AND s.year_=p.year_" +
				" WHERE s.year_=" + year + " AND s.month_=" + month;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getStationPlanOnNetEYearData(Long year) {
		String sql = "SELECT o.organ_name as name,  TRUNC((s.onnete/p.plan_one)*100,2) AS onnetescale, TRUNC(s.onnete/10000,2) AS onnete, TRUNC(p.plan_one/10000,2) AS planonnete" +
				" FROM b_organization o LEFT OUTER JOIN r_station_year s ON o.organ_id=s.organ_id"+
				" LEFT OUTER JOIN n_station_planyear p ON s.organ_id=p.organ_id AND s.year_=p.year_" +
				" WHERE s.year_=" + year;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getStationPlanOnNetEMonthData(Long year,
			Long month) {
		String sql = "SELECT o.organ_name as name,  TRUNC((s.onnete/p.plan_one)*100,2) AS onnetescale, TRUNC(s.onnete/10000,2) AS onnete, TRUNC(p.plan_one/10000,2) AS planonnete" +
				" FROM b_organization o LEFT OUTER JOIN r_station_month s ON o.organ_id=s.organ_id"+
				" LEFT OUTER JOIN n_station_planmonth p ON s.organ_id=p.organ_id AND s.year_=p.year_" +
				" WHERE s.year_=" + year + " AND s.month_=" + month;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getStationEOnetYearData(Long year) {
		String sql = "SELECT o.organ_name as name,  TRUNC(s.e/10000,2) AS e, TRUNC(s.onnete/10000,2) AS onnete" +
				" FROM b_organization o LEFT OUTER JOIN r_station_year s ON o.organ_id=s.organ_id" +
				" WHERE s.year_=" + year;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}
	
	public List<Map<String, Object>> getBranchOnNetEYearData(Long year) {
		String sql = "SELECT o.organ_name as name,  TRUNC(s.e/10000,2) AS e, TRUNC(s.onnete/10000,2) AS onnete" +
				" FROM b_organization o LEFT OUTER JOIN r_branch_year s ON o.organ_id=s.organ_id" +
				" WHERE s.year_=" + year + "AND o.pid=1";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getBranchStationEOnetYearData(Long id,Long year) {
		String sql = "SELECT o.organ_name as name,  TRUNC(s.e/10000,2) AS e, TRUNC(s.onnete/10000,2) AS onnete" +
				" FROM b_organization o LEFT OUTER JOIN r_station_year s ON o.organ_id=s.organ_id" +
				" WHERE s.year_=" + year +" AND o.organ_pid=" +id;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}

	@Override
	public List<Map<String, Object>> getStationEOnetMonthData(Long year,
			Long month) {
		String sql = "SELECT o.organ_name as name,  TRUNC(s.e/10000,2) AS e, TRUNC(s.onnete/10000,2) AS onnete" +
				" FROM b_organization o LEFT OUTER JOIN r_station_month s ON o.organ_id=s.organ_id" +
				" WHERE s.year_=" + year + "AND s.month_="+month;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getBranchOnNetEMonthData(Long year,
			Long month) {
		String sql = "SELECT o.organ_name as name,  TRUNC(s.e/10000,2) AS e, TRUNC(s.onnete/10000,2) AS onnete" +
				" FROM b_organization o LEFT OUTER JOIN r_branch_month s ON o.organ_id=s.organ_id" +
				" WHERE s.year_=" + year + "AND s.month_="+month + "AND o.pid=1";
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}
	
	@Override
	public List<Map<String, Object>> getBranchStationEOnetMonthData(Long id, Long year,
			Long month) {
		String sql = "SELECT o.organ_name as name,  TRUNC(s.e/10000,2) AS e, TRUNC(s.onnete/10000,2) AS onnete" +
				" FROM b_organization o LEFT OUTER JOIN r_station_month s ON o.organ_id=s.organ_id" +
				" WHERE s.year_=" + year + "AND s.month_="+month+" AND o.organ_pid=" +id;
		List<Map<String ,Object>> list =  JdbcTemplate.queryForList(sql);
		return list;
	}
	
}
