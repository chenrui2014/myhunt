package com.huntech.huntoms.oms.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.huntech.huntoms.oms.repository.BranchMonthJdbc;

@Repository
public class BranchMonthJdbcImpl implements BranchMonthJdbc {

	@Autowired
	private JdbcOperations jdbcTemplate;
	
	public List<Map<String, Object>> selectBranchMonth(Integer orgid, Integer year,  Integer month) {
		String  sql = "select round(plan.Plan_E/10000,2) planE , round(plan.Plan_OnE/10000,2) planOne ,round(branch.E/10000,2) e, round(branch.theoryE/10000,2) theorye "
				    +" , round(branch.onNetE/10000,2) onnete ,branch.lossE/10000 losse ,org.Organ_Name organName , plan.year_  year  , branch.Month_ bmonth , branch.purchasenete/10000 purchasenete ," 
				    +" Round(branch.E/branch.theoryE,4)*100  effict ,  round(branch.E/org.Capacity/10000,2)   singleMWE ,  Round(branch.E/plan.Plan_E,4)*100 planEffict ,Round(branch.onNetE/plan.Plan_OnE,4)*100 planOnnetEffict , "
				    +" round(branch.factoryE/10000,2) factorye  , Round((branch.E-onNetE)/branch.E,4)*100 factoryEffict  , branch.Eusehours eusehours   ,"
				    +" round((branch.E+branch.purchaseNetE-branch.onNetE)/10000,2) sumFactorye, Round((branch.E+branch.purchaseNetE-branch.onNetE)/branch.E,4)*100 sumFactoryEffict"
				    +" from  N_Branch_PlanMonth plan , R_Branch_Month branch ,B_Organization org" 
					+" where org.Organ_ID = plan.Organ_ID and  org.Organ_ID = branch.Organ_ID and plan.year_ = branch.year_ and plan.month_=branch.month_" 
					+" and org.organ_id ="+orgid +" and branch.year_ ="+year ;
					if(month>0){
						sql= sql + " and branch.month_ ="+month;
					}
		List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
		return list;
	}
}
