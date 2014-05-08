package com.huntech.huntoms.oms.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.huntech.huntoms.oms.repository.ViewInvDJdbc;

@Repository
public class ViewInvDJdbcImpl implements ViewInvDJdbc {

	@Autowired
	private JdbcOperations JdbcTemplate;

	@Override
	public List<Map<String, Object>> viewInvDJdbc() {
		String sql = "select * from view_inv_day_2 where year_=2014 and month_=3 and day_=31";
		List<Map<String, Object>> list = JdbcTemplate.queryForList(sql);

		return list;
	}

	@Override
	public List<Map<String, Object>> selectInverterHourEByYMD(int organ_ID,
			int Year_, int Month_, int Day_) {
		String sql = "select * from view_inv_day_2 where organ_id=" + organ_ID
				+ " and year_=" + Year_ + " and month_=" + Month_
				+ " and day_=" + Day_;

		List<Map<String, Object>> list = JdbcTemplate.queryForList(sql);

		return list;
	}

	// --select * from b_device where to_number(TRIM(TRANSLATE(DEVICE_NAME,
	// trim(TRANSLATE(DEVICE_NAME, '1234567890', ' ')), ' ')))<5;
	@Override
	public List<Map<String, Object>> selectInvHEByYMDAsc(int organ_ID,
			int Year_, int Month_, int Day_, int min_, int max_) {
		String sql = "select * from view_inv_day_2 where organ_id="
				+ organ_ID
				+ " and year_="
				+ Year_
				+ " and month_="
				+ Month_
				+ " and day_="
				+ Day_
				+ " and  to_number(TRIM(TRANSLATE(DEVICE_NAME, trim(TRANSLATE(DEVICE_NAME, '1234567890', ' ')), ' ')))>="
				+ min_
				+ " and to_number(TRIM(TRANSLATE(DEVICE_NAME, trim(TRANSLATE(DEVICE_NAME, '1234567890', ' ')), ' ')))<="
				+ max_;

		List<Map<String, Object>> list = JdbcTemplate.queryForList(sql);

		return list;
	}

	@Override
	public List<Map<String, Object>> selectInvHEByYMDInv(int organ_ID,
			int Year_, int Month_, int Day_, int Inv) {
		String sql = "select * from view_inv_day_2 where organ_id="
				+ organ_ID
				+ " and year_="
				+ Year_
				+ " and month_="
				+ Month_
				+ " and day_="
				+ Day_
				+ " and  to_number(TRIM(TRANSLATE(DEVICE_NAME, trim(TRANSLATE(DEVICE_NAME, '1234567890', ' ')), ' ')))="
				+ Inv+" order by hour_";

		List<Map<String, Object>> list = JdbcTemplate.queryForList(sql);

		return list;
	}

}
