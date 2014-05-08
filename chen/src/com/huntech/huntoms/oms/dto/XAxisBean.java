package com.huntech.huntoms.oms.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class XAxisBean implements Serializable {

	private static final long serialVersionUID = 8741320894501749145L;

	private  String axisName;  // 坐标轴名称
	
	private List<String> axisList = new ArrayList<String>();  // 坐标轴集合

	public String getAxisName() {
		return axisName;
	}

	public void setAxisName(String axisName) {
		this.axisName = axisName;
	}

	public List<String> getAxisList() {
		return axisList;
	}

	public void setAxisList(List<String> axisLis) {
		this.axisList = axisLis;
	}
	
	public void addData(String data) {
		axisList.add(data);
	}
	
}
