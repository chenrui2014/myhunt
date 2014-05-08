package com.huntech.huntoms.oms.dto;

public class GroupEBean extends ChartBean {

	private static final long serialVersionUID = -5005423186970023978L;

	private Long groupId;  // 集团编号
	
	private String year;  // 年份
	
	private String month;   // 月份

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Long getGroupId() {
		return groupId;
	}

	public void setGroupId(Long groupId) {
		this.groupId = groupId;
	}
	
}
