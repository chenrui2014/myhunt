package com.huntech.huntoms.oms.dto;

import java.math.BigDecimal;

public class RankingDataBean implements Comparable<RankingDataBean> {
	
	private String name;
	private BigDecimal value = new BigDecimal(0);
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public BigDecimal getValue() {
		return value;
	}
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	@Override
	public int compareTo(RankingDataBean r) {
		return r.getValue().compareTo(value);
	}
	
}
