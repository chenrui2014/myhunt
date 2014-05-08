package com.huntech.huntoms.oms.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewStaMDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1121338727155470045L;

	private BigDecimal organID;
	private BigDecimal organPID;
	private String organName;
	private BigDecimal capacity;
	private BigDecimal Year_;
	private BigDecimal Month_;
	private BigDecimal Day_;
	private String SS;
	private BigDecimal AVG_IRRADIANCE;
	private BigDecimal max_p;
	private BigDecimal avg_p;
	private BigDecimal e;
	private BigDecimal netE;
	private BigDecimal lossE;
	private BigDecimal euseHours;
	private BigDecimal plannedOutAgeHours;
	private BigDecimal unPlannedOutAgeHours;

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
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

	public String getSS() {
		return SS;
	}

	public void setSS(String sS) {
		SS = sS;
	}

	public BigDecimal getAVG_IRRADIANCE() {
		return AVG_IRRADIANCE;
	}

	public void setAVG_IRRADIANCE(BigDecimal aVG_IRRADIANCE) {
		AVG_IRRADIANCE = aVG_IRRADIANCE;
	}

	public BigDecimal getMax_p() {
		return max_p;
	}

	public void setMax_p(BigDecimal max_p) {
		this.max_p = max_p;
	}

	public BigDecimal getAvg_p() {
		return avg_p;
	}

	public void setAvg_p(BigDecimal avg_p) {
		this.avg_p = avg_p;
	}

	public BigDecimal getE() {
		return e;
	}

	public void setE(BigDecimal e) {
		this.e = e;
	}

	public BigDecimal getNetE() {
		return netE;
	}

	public void setNetE(BigDecimal netE) {
		this.netE = netE;
	}

	public BigDecimal getLossE() {
		return lossE;
	}

	public void setLossE(BigDecimal lossE) {
		this.lossE = lossE;
	}

	public BigDecimal getEuseHours() {
		return euseHours;
	}

	public void setEuseHours(BigDecimal euseHours) {
		this.euseHours = euseHours;
	}

	public BigDecimal getOrganPID() {
		return organPID;
	}

	public void setOrganPID(BigDecimal organPID) {
		this.organPID = organPID;
	}

	public BigDecimal getOrganID() {
		return organID;
	}

	public void setOrganID(BigDecimal organID) {
		this.organID = organID;
	}

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public BigDecimal getPlannedOutAgeHours() {
		return plannedOutAgeHours;
	}

	public void setPlannedOutAgeHours(BigDecimal plannedOutAgeHours) {
		this.plannedOutAgeHours = plannedOutAgeHours;
	}

	public BigDecimal getUnPlannedOutAgeHours() {
		return unPlannedOutAgeHours;
	}

	public void setUnPlannedOutAgeHours(BigDecimal unPlannedOutAgeHours) {
		this.unPlannedOutAgeHours = unPlannedOutAgeHours;
	}

	/** -------- 业务逻辑 -------- */
	/**
	 * 将实体转换为Dto
	 */
	public static List<ViewStaMDto> DaoToDto(List<Map<String, Object>> mapList) {
		if (mapList.size() == 0) {
			return null;
		}
		List<ViewStaMDto> dtos = new ArrayList<ViewStaMDto>();
		ViewStaMDto dto = null;
		for (int i = 0; i < mapList.size(); i++) {
			dto = new ViewStaMDto();

			dto.setOrganID((BigDecimal) mapList.get(i).get("organ_id"));
			dto.setOrganPID((BigDecimal) mapList.get(i).get("organ_pid"));
			dto.setOrganName((String) mapList.get(i).get("organ_name"));
			dto.setCapacity((BigDecimal) mapList.get(i).get("capacity"));
			dto.setYear_((BigDecimal) mapList.get(i).get("year_"));
			dto.setMonth_((BigDecimal) mapList.get(i).get("month_"));
			dto.setDay_((BigDecimal) mapList.get(i).get("day_"));
			dto.setSS((String) mapList.get(i).get("ss"));
			dto.setAVG_IRRADIANCE((BigDecimal) mapList.get(i).get("avg_irradiance"));
			dto.setMax_p((BigDecimal) mapList.get(i).get("max_p"));
			dto.setAvg_p((BigDecimal) mapList.get(i).get("avg_p"));
			dto.setE((BigDecimal) mapList.get(i).get("e"));
			dto.setNetE((BigDecimal) mapList.get(i).get("losse"));
			dto.setEuseHours((BigDecimal) mapList.get(i).get("eusehours"));
			dto.setPlannedOutAgeHours((BigDecimal) mapList.get(i).get("plannedoutagehours"));
			dto.setUnPlannedOutAgeHours((BigDecimal) mapList.get(i).get("unplannedoutagehours"));

			dtos.add(dto);
		}
		return dtos;
	}

}
