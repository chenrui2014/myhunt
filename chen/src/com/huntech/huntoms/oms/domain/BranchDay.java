package com.huntech.huntoms.oms.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;



/**
 * The persistent class for the R_BRANCH_DAY database table.
 * 
 */
@Entity
@Table(name="R_BRANCH_DAY")
public class BranchDay implements Serializable {
	
	public BranchDay() {
	    }
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_BRACH_DAY", sequenceName="SEQ_BRACH_DAY" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_BRACH_DAY")
	@Column(name="BRANCHDAY_ID", unique=true, nullable=false, precision=22)
	private long branchdayId;
	
	@Column(name="AVG_P")
	private BigDecimal avgP;

    @Temporal( TemporalType.DATE)
	@Column(name="DATE_")
	private Date date;

	@Column(name="DAY_")
	private BigDecimal day;

	private BigDecimal e;

	private BigDecimal eusehours;

	private BigDecimal factorye;

	private BigDecimal losse;

	@Column(name="MAX_P")
	private BigDecimal maxP;

	@Column(name="MONTH_")
	private BigDecimal month;

	private BigDecimal onnete;

	private BigDecimal purchasenete;

	private BigDecimal singlemwe;

	private BigDecimal theorye;

	@Column(name="YEAR_")
	private BigDecimal year;
	
	public BigDecimal getAvgP() {
		return avgP;
	}

	public void setAvgP(BigDecimal avgP) {
		this.avgP = avgP;
	}

	public BigDecimal getDay() {
		return day;
	}

	public void setDay(BigDecimal day) {
		this.day = day;
	}

	public BigDecimal getE() {
		return e;
	}

	public void setE(BigDecimal e) {
		this.e = e;
	}

	public BigDecimal getEusehours() {
		return eusehours;
	}

	public void setEusehours(BigDecimal eusehours) {
		this.eusehours = eusehours;
	}

	public BigDecimal getFactorye() {
		return factorye;
	}

	public void setFactorye(BigDecimal factorye) {
		this.factorye = factorye;
	}

	public BigDecimal getLosse() {
		return losse;
	}

	public void setLosse(BigDecimal losse) {
		this.losse = losse;
	}

	public BigDecimal getMaxP() {
		return maxP;
	}

	public void setMaxP(BigDecimal maxP) {
		this.maxP = maxP;
	}

	public BigDecimal getMonth() {
		return month;
	}

	public void setMonth(BigDecimal month) {
		this.month = month;
	}

	public BigDecimal getOnnete() {
		return onnete;
	}

	public void setOnnete(BigDecimal onnete) {
		this.onnete = onnete;
	}

	public BigDecimal getPurchasenete() {
		return purchasenete;
	}

	public void setPurchasenete(BigDecimal purchasenete) {
		this.purchasenete = purchasenete;
	}

	public BigDecimal getSinglemwe() {
		return singlemwe;
	}

	public void setSinglemwe(BigDecimal singlemwe) {
		this.singlemwe = singlemwe;
	}

	public BigDecimal getTheorye() {
		return theorye;
	}

	public void setTheorye(BigDecimal theorye) {
		this.theorye = theorye;
	}

	public BigDecimal getYear() {
		return year;
	}

	public void setYear(BigDecimal year) {
		this.year = year;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
	

	//bi-directional many-to-one association to BOrganization
    @ManyToOne
	@JoinColumn(name="ORGAN_ID")
	private Organization organization;

   

	public long getBranchdayId() {
		return this.branchdayId;
	}

	public void setBranchdayId(long branchdayId) {
		this.branchdayId = branchdayId;
	}

	public Date getDate() {
		return this.date;
	}

	public void setDate(Date date) {
		this.date = date;
	}



	
	
}