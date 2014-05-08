package com.huntech.huntoms.oms.repository.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import com.huntech.huntoms.oms.repository.BranchYearEJdbc;

@Repository
public class BranchYearEJdbcImpl implements BranchYearEJdbc {
	
	@Autowired
	private JdbcOperations jdbcTemplate;
	
	public List<Map<String,Object>> selectBranchYearE(Integer orgid){
		String sql ="select round(plan.Plan_E/10000,2)  planE , round(plan.Plan_OnE/10000,2) planOne, round(branch.E/10000,2)  e , plan.Year_ year ,  " 
				+" round(branch.theoryE/10000,2)  theorye, round(branch.onNetE/10000,2)  onnete, round(branch.lossE/10000,2) losse ,org.organ_name organName , round(branch.purchasenete/10000,2) purchasenete ," 
				+" Round(branch.E/branch.theoryE,4)*100  effict ,  round(branch.E/org.Capacity/10000,2)  singleMWE  , Round(branch.E/plan.Plan_E,4)*100  planEffict ," 
				+" Round(branch.onNetE/plan.Plan_OnE,4)*100 planOnnetEffict , round(branch.factoryE/10000,2) factorye  , Round((branch.E-onNetE)/branch.E,4)*100 factoryEffict ," 
				+" round((branch.E+branch.purchaseNetE-branch.onNetE)/10000,2) sumFactorye, Round((branch.E+branch.purchaseNetE-branch.onNetE)/branch.E,4)*100 sumFactoryEffict"
				+" from N_Branch_PlanYear plan ,R_Branch_Year  branch ,B_Organization org  " 
				+" where plan.organ_id = org.organ_id and branch.organ_id= org.organ_id and plan.year_ = branch.year_ and org.organ_id ="+orgid;
		List<Map<String,Object>>  list = jdbcTemplate.queryForList(sql);
		return list;
	}
}
