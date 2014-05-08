package com.huntech.huntoms.oms.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * 集团年数据表（集团历年数据统计模块）  
 */
@Entity
@Table(name = "R_GROUP_YEAR")
public class GroupYear implements Serializable {

	private static final long serialVersionUID = -614985659417261513L;

	@Id
	@Column(name="GROUPYEAR_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_GROUPYEAR")
	@SequenceGenerator(name = "SEQ_GROUPYEAR", sequenceName = "SEQ_GROUPYEAR", allocationSize = 1)
	private Long groupYearId;  // 编号
	
	@ManyToOne(cascade=CascadeType.REFRESH)
	@JoinColumn(name = "ORGAN_ID")
	private Organization organ;  // 机构
	
	@Column(name = "YEAR_")
	private Long Year;  // 年份
	
	@Column(name = "E")
	private BigDecimal E;  // 发电量
	
	@Column(name = "theoryE")
	private BigDecimal theoryE;  // 理论发电量
	
	@Column(name = "factoryE")
	private BigDecimal factoryE;  // 厂用电量
	
	@Column(name = "purchaseNetE")
	private BigDecimal purchaseNetE;  // 购网电量

	@Column(name = "onNetE")
	private BigDecimal onNetE;  // 上网电量
	
	@Column(name = "singleMWE")
	private BigDecimal singleMWE;  // 单兆瓦发电量

	@Column(name = "loseE")
	private BigDecimal loseE;  // 弃光电量
	
	
	/* --------  getter && setter -------- */

	public Long getGroupYearId() {
		return groupYearId;
	}

	public void setGroupYearId(Long groupYearId) {
		this.groupYearId = groupYearId;
	}

	public Organization getOrgan() {
		return organ;
	}

	public void setOrgan(Organization organ) {
		this.organ = organ;
	}

	public Long getYear() {
		return Year;
	}

	public void setYear(Long year) {
		Year = year;
	}

	public BigDecimal getE() {
		return E;
	}

	public void setE(BigDecimal e) {
		E = e;
	}

	public BigDecimal getTheoryE() {
		return theoryE;
	}

	public void setTheoryE(BigDecimal theoryE) {
		this.theoryE = theoryE;
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

	public BigDecimal getLoseE() {
		return loseE;
	}

	public void setLoseE(BigDecimal loseE) {
		this.loseE = loseE;
	}

}
