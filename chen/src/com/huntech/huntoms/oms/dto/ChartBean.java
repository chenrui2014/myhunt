package com.huntech.huntoms.oms.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 柱状图、折线图图表Bean
 * @author 张晨 
 */
public class ChartBean implements Serializable {

	private static final long serialVersionUID = -1152559116458388246L;

	/* 图表标题 */
	private String title; 
	private List<String> xAxisCategories;  // x轴刻度列表
	private List<DataBean> dataBeanList;  // 数据Bean列表
	
	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}

	public List<String> getxAxisCategories() {
		return xAxisCategories;
	}
	
	public void setxAxisCateGories(List<String> xAxisCategories) {
		this.xAxisCategories = xAxisCategories;
	}
	
	public List<DataBean> getDataBeanList() {
		return dataBeanList;
	}
	
	public void setDataBeanList(List<DataBean> dataBeanList) {
		this.dataBeanList = dataBeanList;
	}

}
