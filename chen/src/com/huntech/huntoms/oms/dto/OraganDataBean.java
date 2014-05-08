package com.huntech.huntoms.oms.dto;

import java.math.BigDecimal;
import java.util.List;

public class OraganDataBean {

	public BigDecimal getCapacity() {
		return capacity;
	}

	public void setCapacity(BigDecimal capacity) {
		this.capacity = capacity;
	}

	public BigDecimal getPlanCapacity() {
		return planCapacity;
	}

	public void setPlanCapacity(BigDecimal planCapacity) {
		this.planCapacity = planCapacity;
	}

	public BigDecimal getEfficE() {
		return efficE;
	}

	public void setEfficE(BigDecimal efficE) {
		this.efficE = efficE;
	}

	public BigDecimal getE() {
		return e;
	}

	public void setE(BigDecimal e) {
		this.e = e;
	}

	public BigDecimal getPlanE() {
		return planE;
	}

	public void setPlanE(BigDecimal planE) {
		this.planE = planE;
	}

	public BigDecimal geteToYester() {
		return eToYester;
	}

	public void seteToYester(BigDecimal eToYester) {
		this.eToYester = eToYester;
	}

	public BigDecimal getPlanEToYester() {
		return planEToYester;
	}

	public void setPlanEToYester(BigDecimal planEToYester) {
		this.planEToYester = planEToYester;
	}

	private BigDecimal capacity;
	
	private BigDecimal planCapacity;
	
	private BigDecimal efficE;
	
	private BigDecimal e;
	
	private BigDecimal planE;
	
	private BigDecimal eToYester;
	
	private BigDecimal planEToYester;
	
	private List<String> organList;
	private List<BigDecimal> eList;
	public List<String> getOrganList() {
		return organList;
	}

	public void setOrganList(List<String> organList) {
		this.organList = organList;
	}

	public List<BigDecimal> geteList() {
		return eList;
	}

	public void seteList(List<BigDecimal> eList) {
		this.eList = eList;
	}

	public List<BigDecimal> getOnnetEList() {
		return onnetEList;
	}

	public void setOnnetEList(List<BigDecimal> onnetEList) {
		this.onnetEList = onnetEList;
	}

	private List<BigDecimal> onnetEList;
}
