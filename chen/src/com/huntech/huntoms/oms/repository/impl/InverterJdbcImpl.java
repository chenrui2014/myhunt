package com.huntech.huntoms.oms.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.huntech.huntoms.oms.repository.InverterJdbc;

@Repository
public class InverterJdbcImpl implements InverterJdbc {

	@Autowired
	private JdbcOperations jdbcTemplate;
	@Override
	public List<Map<String, Object>> selectInverterSUMEByYM(int organ_ID,
			int Year_, int Month_) {	
		
		/*
		 * select organ_id,organ_name,device_model,year_,month_,day_,sum(e) EE
		 * from view_inv_month_1 group by
		 * organ_id,organ_name,device_model,year_,month_,day_ having
		 * device_model is not null order by
		 * organ_id,organ_name,year_,month_,day_;
		 */
		String sql="select organ_id,organ_name,device_model,year_,month_,day_,sum(e) EE from view_inv_month_1"+
		 " where organ_id="+organ_ID+" and year_="+Year_+" and month_="+Month_+
		 		"group by organ_id,organ_name,device_model,year_,month_,day_ "+
		 			"having device_model is not null "+
		 				"order by organ_id,organ_name,year_,month_,device_model,day_";
		/*String sql="select organ_id,organ_name,device_model,year_,month_,day_,sum(e) EE from view_inv_month_1"+
				 " where organ_id="+organ_ID+" and year_="+Year_+" and month_="+Month_+
				 		"group by organ_id,organ_name,device_model,year_,month_,day_ "+
				 			"having device_model is not null "+
				 				"order by organ_id,organ_name,year_,month_,day_,device_model";*/
		List<Map<String,Object>> list=jdbcTemplate.queryForList(sql);
		return list;
	}

}
