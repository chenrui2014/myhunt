package com.huntech.huntoms.oms.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;

import com.fr.third.com.lowagie.text.pdf.hyphenation.TernaryTree.Iterator;

import edu.emory.mathcs.backport.java.util.LinkedList;

public class ViewStaAYDto implements Serializable {

	private static final long serialVersionUID = 4132883679467510488L;

	// 电站ID
	private BigDecimal organID;
	// 电站PID
	private BigDecimal organPID;
	// 机构名称
	private String organName;
	// 电站装机容量
	private BigDecimal capacity;
	// 年份
	private BigDecimal Year_;
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
	// 计划停运小时
	private BigDecimal plannedOutAgehours;
	// 非计划停运小时
	private BigDecimal unPlannedOutAgehours;

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

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

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

	public BigDecimal getPlannedOutAgehours() {
		return plannedOutAgehours;
	}

	public void setPlannedOutAgehours(BigDecimal plannedOutAgehours) {
		this.plannedOutAgehours = plannedOutAgehours;
	}

	public BigDecimal getUnPlannedOutAgehours() {
		return unPlannedOutAgehours;
	}

	public void setUnPlannedOutAgehours(BigDecimal unPlannedOutAgehours) {
		this.unPlannedOutAgehours = unPlannedOutAgehours;
	}

	/** -------- 业务逻辑 -------- */
	/**
	 * 将实体转换为Dto
	 * 
	 * @param staAllYear
	 * @return staAllYearDto
	 */
	/*
	 * public static ViewStaAYDto domainToDto(ViewStaAY staAY) { ViewStaAYDto
	 * dto = new ViewStaAYDto(); if (staAY != null) {
	 * dto.setOrganName(staAY.getOrganName()); dto.setYEAR_(staAY.getYear_());
	 * dto.setFactInstall(staAY.getFactInstall()); dto.setE(staAY.getE());
	 * dto.setOnNetE(staAY.getOnNetE()); } return dto; }
	 */
	/*
	 * public static ViewStaAYDto DaoToDto(List<Map<String, Object>> list) {
	 * List<ViewStaAYDto> dtos = new ArrayList<ViewStaAYDto>(); for (int i = 0;
	 * i < list.size(); i++) { ViewStaAYDto dto = new ViewStaAYDto();
	 * 
	 * dto.setOrganName((String) list.get(i).get("organ_name"));
	 * dto.setYear_((BigDecimal) list.get(i).get("year_"));
	 * dto.setFactInstall((BigDecimal) list.get(i).get("fact_install"));
	 * dto.setE((BigDecimal) list.get(i).get("e")); dto.setOnNetE((BigDecimal)
	 * list.get(i).get("onnete")); dto.setSingleMWE((BigDecimal)
	 * list.get(i).get("singlemwe")); dto.setTheoryE((BigDecimal)
	 * list.get(i).get("theorye")); dto.setEuseHours((BigDecimal)
	 * list.get(i).get("eusehours")); dto.setFactoryE((BigDecimal)
	 * list.get(i).get("factorye")); dto.setLossE((BigDecimal)
	 * list.get(i).get("losse")); dto.setPlanBuyE((BigDecimal)
	 * list.get(i).get("plan_buye")); dto.setPlanE((BigDecimal)
	 * list.get(i).get("plan_e")); dto.setPlanFactoryE((BigDecimal)
	 * list.get(i).get("plan_factorye")); dto.setPlanInstall((BigDecimal)
	 * list.get(i).get("plan_install")); dto.setPlanOnE((BigDecimal)
	 * list.get(i).get("plan_one")); dto.setPurchaseNetE((BigDecimal)
	 * list.get(i).get("purchasenete"));
	 * 
	 * dtos.add(dto); } return (ViewStaAYDto) dtos; }
	 */

	public static List<ViewStaAYDto> DaoToDto(List<Map<String, Object>> mapList) {
		if (mapList.size() == 0) {
			return null;
		}
		List<ViewStaAYDto> list = new ArrayList<ViewStaAYDto>();
		ViewStaAYDto dto = null;
		for (int i = 0; i < mapList.size(); i++) {
			dto = new ViewStaAYDto();
			dto.setOrganID((BigDecimal) mapList.get(i).get("organ_id"));
			dto.setOrganPID((BigDecimal) mapList.get(i).get("organ_pid"));
			dto.setOrganName((String) mapList.get(i).get("organ_name"));
			dto.setCapacity((BigDecimal) mapList.get(i).get("capacity"));
			dto.setYear_((BigDecimal) mapList.get(i).get("year_"));
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
			dto.setPlannedOutAgehours((BigDecimal) mapList.get(i).get("plannedoutagehours"));
			dto.setUnPlannedOutAgehours((BigDecimal) mapList.get(i).get("unplannedoutagehours"));
			
			list.add(dto);
		}

		return list;
	}

}
