package com.huntech.huntoms.oms.dto;

import java.util.List;

public class RankingBean {
	
	private String code;
	private String title;
	private List<RankingDataBean> dataList;
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<RankingDataBean> getDataList() {
		return dataList;
	}
	public void setDataList(List<RankingDataBean> dataList) {
		this.dataList = dataList;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}

}
