package com.huntech.huntoms.oms.dto;

import java.awt.List;
import java.io.Serializable;

public class InverterBean implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7303452325402982995L;
	private String deviceModel;
	private List dataList;
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public List getDataList() {
		return dataList;
	}
	public void setDataList(List dataList) {
		this.dataList = dataList;
	}
	
	
	
}
