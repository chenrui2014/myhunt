package com.huntech.huntoms.oms.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ViewStaYDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -750901326469035443L;

	// 机构ID
	private BigDecimal organID;
	// 机构PID
	private BigDecimal organPID;
	// 机构名称
	private String organName;
	// 装机容量
	private BigDecimal capacity;
	// 年份
	private BigDecimal Year_;
	// 年份
	private BigDecimal Month_;
	// 理论发电量
	private BigDecimal theoryE;
	// 实际发电量
	private BigDecimal E;
	// 厂用电量
	private BigDecimal factoryE;
	// 购买电量
	private BigDecimal purchaseNetE;
	// 上网电量
	private BigDecimal onNetE;
	// 单兆瓦电量
	private BigDecimal singleMWE;
	// 等效利用小时
	private BigDecimal euseHours;
	// 弃光电量
	private BigDecimal lossE;
	// 计划装机容量
	private BigDecimal planInstall;
	// 实际装机容量
	private BigDecimal factInstall;
	// 计划发电量
	private BigDecimal planE;
	// 计划上网电量
	private BigDecimal planOnE;
	// 计划厂用电量
	private BigDecimal planFactoryE;
	// 计划购买电量
	private BigDecimal planBuyE;
	// 计划停运时间
	private BigDecimal plannedOutAgeHours;
	// 非计划停运时间
	private BigDecimal unPlannedOutAgeHours;

	public BigDecimal getOrganID() {
		return organID;
	}

	public void setOrganID(BigDecimal organID) {
		this.organID = organID;
	}

	public BigDecimal getOrganPID() {
		return organPID;
	}

	public void setOrganPID(BigDecimal organPID) {
		this.organPID = organPID;
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

	public BigDecimal getTheoryE() {
		return theoryE;
	}

	public void setTheoryE(BigDecimal theoryE) {
		this.theoryE = theoryE;
	}

	public BigDecimal getE() {
		return E;
	}

	public void setE(BigDecimal e) {
		E = e;
	}

	public BigDecimal getFactoryE() {
		return factoryE;
	}

	public void setFactoryE(BigDecimal factoryE) {
		this.factoryE = factoryE;
	}

	public BigDecimal getPurchaseNetE() {
		return purchaseNetE;
	}

	public void setPurchaseNetE(BigDecimal purchaseNetE) {
		this.purchaseNetE = purchaseNetE;
	}

	public BigDecimal getOnNetE() {
		return onNetE;
	}

	public void setOnNetE(BigDecimal onNetE) {
		this.onNetE = onNetE;
	}

	public BigDecimal getSingleMWE() {
		return singleMWE;
	}

	public void setSingleMWE(BigDecimal singleMWE) {
		this.singleMWE = singleMWE;
	}

	public BigDecimal getEuseHours() {
		return euseHours;
	}

	public void setEuseHours(BigDecimal euseHours) {
		this.euseHours = euseHours;
	}

	public BigDecimal getLossE() {
		return lossE;
	}

	public void setLossE(BigDecimal lossE) {
		this.lossE = lossE;
	}

	public BigDecimal getPlanInstall() {
		return planInstall;
	}

	public void setPlanInstall(BigDecimal planInstall) {
		this.planInstall = planInstall;
	}

	public BigDecimal getFactInstall() {
		return factInstall;
	}

	public void setFactInstall(BigDecimal factInstall) {
		this.factInstall = factInstall;
	}

	public BigDecimal getPlanE() {
		return planE;
	}

	public void setPlanE(BigDecimal planE) {
		this.planE = planE;
	}

	public BigDecimal getPlanOnE() {
		return planOnE;
	}

	public void setPlanOnE(BigDecimal planOnE) {
		this.planOnE = planOnE;
	}

	public BigDecimal getPlanFactoryE() {
		return planFactoryE;
	}

	public void setPlanFactoryE(BigDecimal planFactoryE) {
		this.planFactoryE = planFactoryE;
	}

	public BigDecimal getPlanBuyE() {
		return planBuyE;
	}

	public void setPlanBuyE(BigDecimal planBuyE) {
		this.planBuyE = planBuyE;
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
	 * 
	 * @param staYear
	 * @return staYearDto
	 */
	public static List<ViewStaYDto> DaoToDto(List<Map<String, Object>> mapList) {
		if (mapList.size() == 0) {
			return null;
		}
		List<ViewStaYDto> dtos = new ArrayList<ViewStaYDto>();
		ViewStaYDto dto = null;
		for (int i = 0; i < mapList.size(); i++) {
			dto = new ViewStaYDto();
			dto.setOrganID((BigDecimal) mapList.get(i).get("organ_id"));
			dto.setOrganPID((BigDecimal) mapList.get(i).get("organ_pid"));
			dto.setOrganName((String) mapList.get(i).get("organ_name"));
			dto.setCapacity((BigDecimal) mapList.get(i).get("capacity"));
			dto.setYear_((BigDecimal) mapList.get(i).get("year_"));
			dto.setMonth_((BigDecimal) mapList.get(i).get("month_"));
			dto.setFactInstall((BigDecimal) mapList.get(i).get("fact_install"));
			dto.setE((BigDecimal) mapList.get(i).get("e"));
			dto.setOnNetE((BigDecimal) mapList.get(i).get("onnete"));
			dto.setSingleMWE((BigDecimal) mapList.get(i).get("singlemwe"));
			dto.setTheoryE((BigDecimal) mapList.get(i).get("theorye"));
			dto.setEuseHours((BigDecimal) mapList.get(i).get("eusehours"));
			dto.setFactoryE((BigDecimal) mapList.get(i).get("factorye"));
			dto.setLossE((BigDecimal) mapList.get(i).get("losse"));
			dto.setPlanBuyE((BigDecimal) mapList.get(i).get("plan_buye"));
			dto.setPlanE((BigDecimal) mapList.get(i).get("plan_e"));
			dto.setPlanFactoryE((BigDecimal) mapList.get(i).get("plan_factorye"));
			dto.setPlanInstall((BigDecimal) mapList.get(i).get("plan_install"));
			dto.setPlanOnE((BigDecimal) mapList.get(i).get("plan_one"));
			dto.setPurchaseNetE((BigDecimal) mapList.get(i).get("purchasenete"));
			dto.setPlannedOutAgeHours((BigDecimal) mapList.get(i).get("plannedoutagehours"));
			dto.setUnPlannedOutAgeHours((BigDecimal) mapList.get(i).get("unplannedoutagehours"));
			
			dtos.add(dto);
		}
		return dtos;
	}
}
