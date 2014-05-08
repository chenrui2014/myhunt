package com.huntech.huntoms.oms.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewStaDDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4491931322448628832L;

	private BigDecimal organ_ID;
	private BigDecimal organ_PID;
	private String organName;
	private BigDecimal capacity;
	private BigDecimal Year_;
	private BigDecimal Month_;
	private BigDecimal Day_;
	private BigDecimal Hour_;
	private String SS;
	private BigDecimal avgIrradiance;
	private BigDecimal temp;
	private BigDecimal componentTemp;
	private BigDecimal max_P;
	private BigDecimal avg_P;
	private BigDecimal E;
	private BigDecimal onNetE;
	private BigDecimal lossE;
	private BigDecimal grdConnectP;
	private BigDecimal theoryP;
	private BigDecimal invInput;
	private BigDecimal invOutput;

	public BigDecimal getOrgan_ID() {
		return organ_ID;
	}

	public void setOrgan_ID(BigDecimal organ_ID) {
		this.organ_ID = organ_ID;
	}

	public BigDecimal getOrgan_PID() {
		return organ_PID;
	}

	public void setOrgan_PID(BigDecimal organ_PID) {
		this.organ_PID = organ_PID;
	}

	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
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

	public BigDecimal getHour_() {
		return Hour_;
	}

	public void setHour_(BigDecimal hour_) {
		Hour_ = hour_;
	}

	public String getSS() {
		return SS;
	}

	public void setSS(String sS) {
		SS = sS;
	}

	public BigDecimal getAvgIrradiance() {
		return avgIrradiance;
	}

	public void setAvgIrradiance(BigDecimal avgIrradiance) {
		this.avgIrradiance = avgIrradiance;
	}

	public BigDecimal getTemp() {
		return temp;
	}

	public void setTemp(BigDecimal temp) {
		this.temp = temp;
	}

	public BigDecimal getComponentTemp() {
		return componentTemp;
	}

	public void setComponentTemp(BigDecimal componentTemp) {
		this.componentTemp = componentTemp;
	}

	public BigDecimal getMax_P() {
		return max_P;
	}

	public void setMax_P(BigDecimal max_P) {
		this.max_P = max_P;
	}

	public BigDecimal getAvg_P() {
		return avg_P;
	}

	public void setAvg_P(BigDecimal avg_P) {
		this.avg_P = avg_P;
	}

	public BigDecimal getE() {
		return E;
	}

	public void setE(BigDecimal e) {
		E = e;
	}

	public BigDecimal getOnNetE() {
		return onNetE;
	}

	public void setOnNetE(BigDecimal onNetE) {
		this.onNetE = onNetE;
	}

	public BigDecimal getLossE() {
		return lossE;
	}

	public void setLossE(BigDecimal lossE) {
		this.lossE = lossE;
	}

	public BigDecimal getGrdConnectP() {
		return grdConnectP;
	}

	public void setGrdConnectP(BigDecimal grdConnectP) {
		this.grdConnectP = grdConnectP;
	}

	public BigDecimal getTheoryP() {
		return theoryP;
	}

	public void setTheoryP(BigDecimal theoryP) {
		this.theoryP = theoryP;
	}

	public BigDecimal getInvInput() {
		return invInput;
	}

	public void setInvInput(BigDecimal invInput) {
		this.invInput = invInput;
	}

	public BigDecimal getInvOutput() {
		return invOutput;
	}

	public void setInvOutput(BigDecimal invOutput) {
		this.invOutput = invOutput;
	}

	/** -------- 业务逻辑 -------- */
	/**
	 * 将实体转换为Dto
	 */
	public static List<ViewStaDDto> DaoToDto(List<Map<String, Object>> mapList) {
		if (mapList.size() == 0) {
			return null;
		}
		List<ViewStaDDto> dtos = new ArrayList<ViewStaDDto>();
		ViewStaDDto dto = null;
		for (int i = 0; i < mapList.size(); i++) {
			dto = new ViewStaDDto();

			dto.setOrgan_ID((BigDecimal) mapList.get(i).get("organ_id"));
			dto.setOrgan_PID((BigDecimal) mapList.get(i).get("organ_pid"));
			dto.setOrganName((String) mapList.get(i).get("organ_name"));
			dto.setCapacity((BigDecimal) mapList.get(i).get("capacity"));
			dto.setYear_((BigDecimal) mapList.get(i).get("year_"));
			dto.setMonth_((BigDecimal) mapList.get(i).get("month_"));
			dto.setDay_((BigDecimal) mapList.get(i).get("day_"));
			dto.setHour_((BigDecimal) mapList.get(i).get("hour_"));
			dto.setSS((String) mapList.get(i).get("ss"));
			dto.setAvgIrradiance((BigDecimal) mapList.get(i).get(
					"avg_irradiance"));
			dto.setTemp((BigDecimal) mapList.get(i).get("temp"));
			dto.setMax_P((BigDecimal) mapList.get(i).get("max_p"));
			dto.setAvg_P((BigDecimal) mapList.get(i).get("avg_p"));
			dto.setE((BigDecimal) mapList.get(i).get("e"));
			dto.setOnNetE((BigDecimal) mapList.get(i).get("onnete"));
			dto.setLossE((BigDecimal) mapList.get(i).get("losse"));
			dto.setGrdConnectP((BigDecimal) mapList.get(i).get("grdconnectp"));
			dto.setTheoryP((BigDecimal) mapList.get(i).get("theoryp"));
			dto.setInvInput((BigDecimal) mapList.get(i).get("inv_input"));
			dto.setInvOutput((BigDecimal) mapList.get(i).get("inv_output"));

			dtos.add(dto);
		}
		return dtos;
	}
}
