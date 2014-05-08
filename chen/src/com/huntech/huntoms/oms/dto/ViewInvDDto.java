package com.huntech.huntoms.oms.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewInvDDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4864106991851320239L;
	
	
	private BigDecimal organ_ID;
	private BigDecimal organ_PID;
	private String organName;
	private BigDecimal capacity;
	private String deviceName;
	private BigDecimal Year_;
	private BigDecimal Month_;
	private BigDecimal Day_;
	private BigDecimal Hour_;
	//private String date_;
	private String SS;
	private BigDecimal avgIrradiance;
	private BigDecimal HIrradiance;
	private BigDecimal slopeIrradiance;
	private BigDecimal DIrradiance;
	private BigDecimal scaIrradiance;
	private BigDecimal temp;
	private BigDecimal RHA;
	private BigDecimal TDP;
	private BigDecimal SW;
	private BigDecimal componentTemp;
	private BigDecimal maxDCP;
	private BigDecimal avg_P;
	private BigDecimal E;
	private BigDecimal totale;
	private BigDecimal pec;
	private BigDecimal avgTransferEffect;
	private BigDecimal inverterTam;
	
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
	public String getDeviceName() {
		return deviceName;
	}
	public void setDeviceName(String deviceName) {
		this.deviceName = deviceName;
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
	/*public String getDate_() {
		return date_;
	}
	public void setDate_(String date_) {
		this.date_ = date_;
	}*/
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
	public BigDecimal getMaxDCP() {
		return maxDCP;
	}
	public void setMaxDCP(BigDecimal maxDCP) {
		this.maxDCP = maxDCP;
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
	
	/** -------- 业务逻辑 -------- */
	/**
	 * 将实体转换为Dto
	 */
	public static List<ViewInvDDto> DaoToDto(List<Map<String, Object>> mapList) {
		if (mapList.size() == 0) {
			return null;
		}
		List<ViewInvDDto> dtos = new ArrayList<ViewInvDDto>();
		ViewInvDDto dto = null;
		for (int i = 0; i < mapList.size(); i++) {
			dto = new ViewInvDDto();
			
			dto.setOrgan_ID((BigDecimal) mapList.get(i).get("organ_id"));
			dto.setOrgan_PID( (BigDecimal) mapList.get(i).get("organ_pid"));
			dto.setOrganName((String) mapList.get(i).get("organ_name"));
			dto.setCapacity((BigDecimal) mapList.get(i).get("capacity"));
			dto.setDeviceName((String) mapList.get(i).get("device_name"));
			dto.setYear_((BigDecimal) mapList.get(i).get("year_"));
			dto.setMonth_((BigDecimal) mapList.get(i).get("month_"));
			dto.setDay_((BigDecimal) mapList.get(i).get("day_"));
			dto.setHour_((BigDecimal) mapList.get(i).get("hour_"));			
			dto.setSS((String) mapList.get(i).get("ss"));			
			dto.setAvgIrradiance( (BigDecimal) mapList.get(i).get(
					"avg_irradiance"));
			dto.setHIrradiance( (BigDecimal) mapList.get(i).get(
					"horizontal_irradiance"));
			dto.setSlopeIrradiance( (BigDecimal) mapList.get(i).get(
					"slope_irradiance"));
			dto.setDIrradiance( (BigDecimal) mapList.get(i).get(
					"direct_irradiance"));
			dto.setScaIrradiance( (BigDecimal) mapList.get(i).get(
					"scattering_irradiance"));
			dto.setTemp((BigDecimal) mapList.get(i).get("temp"));
			dto.setTDP((BigDecimal) mapList.get(i).get("tdp"));
			dto.setRHA((BigDecimal) mapList.get(i).get("rha"));
			dto.setSW((BigDecimal) mapList.get(i).get("sw"));
			dto.setComponentTemp((BigDecimal) mapList.get(i).get("component"));
			dto.setMaxDCP((BigDecimal) mapList.get(i).get("maxdcp"));
			dto.setAvg_P((BigDecimal) mapList.get(i).get("avg_p"));			
			dto.setE((BigDecimal) mapList.get(i).get("e"));
			dto.setTotale((BigDecimal) mapList.get(i).get("totale"));
			dto.setAvgTransferEffect((BigDecimal) mapList.get(i).get("avgtransfereffect"));
			dto.setInverterTam((BigDecimal) mapList.get(i).get("invertertam"));
			dto.setPec((BigDecimal) mapList.get(i).get("pec"));
			//dto.setDate_((String) mapList.get(i).get("date_"));		
			
			dtos.add(dto);
		}
		return dtos;
	}
	public BigDecimal getCapacity() {
		return capacity;
	}
	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}
	public BigDecimal getHIrradiance() {
		return HIrradiance;
	}
	public void setHIrradiance(BigDecimal hIrradiance) {
		HIrradiance = hIrradiance;
	}
	public BigDecimal getSlopeIrradiance() {
		return slopeIrradiance;
	}
	public void setSlopeIrradiance(BigDecimal slopeIrradiance) {
		this.slopeIrradiance = slopeIrradiance;
	}
	public BigDecimal getDIrradiance() {
		return DIrradiance;
	}
	public void setDIrradiance(BigDecimal dIrradiance) {
		DIrradiance = dIrradiance;
	}
	public BigDecimal getScaIrradiance() {
		return scaIrradiance;
	}
	public void setScaIrradiance(BigDecimal scaIrradiance) {
		this.scaIrradiance = scaIrradiance;
	}
	public BigDecimal getTemp() {
		return temp;
	}
	public void setTemp(BigDecimal temp) {
		this.temp = temp;
	}
	public BigDecimal getRHA() {
		return RHA;
	}
	public void setRHA(BigDecimal rHA) {
		RHA = rHA;
	}
	public BigDecimal getTDP() {
		return TDP;
	}
	public void setTDP(BigDecimal tDP) {
		TDP = tDP;
	}
	public BigDecimal getSW() {
		return SW;
	}
	public void setSW(BigDecimal sW) {
		SW = sW;
	}
	public BigDecimal getComponentTemp() {
		return componentTemp;
	}
	public void setComponentTemp(BigDecimal componentTemp) {
		this.componentTemp = componentTemp;
	}
	public BigDecimal getTotale() {
		return totale;
	}
	public void setTotale(BigDecimal totale) {
		this.totale = totale;
	}
	public BigDecimal getAvgTransferEffect() {
		return avgTransferEffect;
	}
	public void setAvgTransferEffect(BigDecimal avgTransferEffect) {
		this.avgTransferEffect = avgTransferEffect;
	}
	public BigDecimal getPec() {
		return pec;
	}
	public void setPec(BigDecimal pec) {
		this.pec = pec;
	}
	public BigDecimal getInverterTam() {
		return inverterTam;
	}
	public void setInverterTam(BigDecimal inverterTam) {
		this.inverterTam = inverterTam;
	}
	
}
