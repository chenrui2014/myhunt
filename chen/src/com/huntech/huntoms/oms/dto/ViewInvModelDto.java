package com.huntech.huntoms.oms.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ViewInvModelDto implements Serializable{
   /**
	 * 
	 */
	private static final long serialVersionUID = 4414871191378492638L;
	private BigDecimal organID;
	private String organName;
	private String deviceModel;
	private BigDecimal Year_;
	private BigDecimal Month_;
	private BigDecimal Day_;
	private BigDecimal EE;
	public BigDecimal getOrganID() {
		return organID;
	}
	public void setOrganID(BigDecimal organID) {
		this.organID = organID;
	}
	public String getOrganName() {
		return organName;
	}
	public void setOrganName(String organName) {
		this.organName = organName;
	}
	public String getDeviceModel() {
		return deviceModel;
	}
	public void setDeviceModel(String deviceModel) {
		this.deviceModel = deviceModel;
	}
	public BigDecimal getYear_() {
		return Year_;
	}
	public void setYear_(BigDecimal year_) {
		Year_ = year_;
	}
	public BigDecimal getMonth_() {
		return Month_;
	}
	public void setMonth_(BigDecimal month_) {
		Month_ = month_;
	}
	public BigDecimal getDay_() {
		return Day_;
	}
	public void setDay_(BigDecimal day_) {
		Day_ = day_;
	}
	public BigDecimal getEE() {
		return EE;
	}
	public void setEE(BigDecimal eE) {
		EE = eE;
	}
	
	/** -------- 业务逻辑 -------- */
	/**
	 * 将实体转换为Dto
	 */
	public static List<ViewInvModelDto> DaoToDto(List<Map<String, Object>> mapList) {
		if (mapList.size() == 0) {
			return null;
		}
		List<ViewInvModelDto> dtos=new LinkedList<ViewInvModelDto>();
		
		ViewInvModelDto dto=null;
		for(int i=0;i<mapList.size();i++){
			dto=new ViewInvModelDto();
			dto.setOrganID((BigDecimal) mapList.get(i).get("organ_id"));
			dto.setOrganName((String) mapList.get(i).get("organ_name"));
			dto.setDeviceModel((String) mapList.get(i).get("device_model"));
			dto.setYear_((BigDecimal) mapList.get(i).get("year_"));
			dto.setMonth_((BigDecimal) mapList.get(i).get("month_"));
			dto.setDay_((BigDecimal) mapList.get(i).get("day_"));
			dto.setEE((BigDecimal) mapList.get(i).get("EE"));
			
			dtos.add(dto);
		}
		return dtos;
	}
}
