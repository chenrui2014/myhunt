package com.huntech.huntoms.oms.domain;

import java.io.Serializable;

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
 * The persistent class for the R_BRANCH_MONTH database table.
 * 
 */
@Entity
@Table(name="R_BRANCH_MONTH")
public class BranchMonth implements Serializable {
	
    public BranchMonth() {
    }

	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_BRANCH_MONTH", sequenceName="SEQ_BRANCH_MONTH" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_BRANCH_MONTH")
	@Column(name="BRANCHMONTH_ID", unique=true, nullable=false, precision=22)
	private long branchmonthId;

	private Double e;

	private Double eusehours;

	private Double factorye;

	private Double losse;

	@Column(name="MONTH_")
	private Double month;

	private Double onnete;

	private Double purchasenete;

	private Double singlemwe;

	private Double theorye;

	@Column(name="YEAR_")
	private Double year;

	//bi-directional many-to-one association to BOrganization
    @ManyToOne
	@JoinColumn(name="ORGAN_ID")
	private Organization organization;


	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	public long getBranchmonthId() {
		return this.branchmonthId;
	}

	public void setBranchmonthId(long branchmonthId) {
		this.branchmonthId = branchmonthId;
	}

	public Double getE() {
		return this.e;
	}

	public void setE(Double e) {
		this.e = e;
	}

	public Double getEusehours() {
		return this.eusehours;
	}

	public void setEusehours(Double eusehours) {
		this.eusehours = eusehours;
	}

	public Double getFactorye() {
		return this.factorye;
	}

	public void setFactorye(Double factorye) {
		this.factorye = factorye;
	}

	public Double getLosse() {
		return this.losse;
	}

	public void setLosse(Double losse) {
		this.losse = losse;
	}

	public Double getMonth() {
		return this.month;
	}

	public void setMonth(Double month) {
		this.month = month;
	}

	public Double getOnnete() {
		return this.onnete;
	}

	public void setOnnete(Double onnete) {
		this.onnete = onnete;
	}

	public Double getPurchasenete() {
		return this.purchasenete;
	}

	public void setPurchasenete(Double purchasenete) {
		this.purchasenete = purchasenete;
	}

	public Double getSinglemwe() {
		return this.singlemwe;
	}

	public void setSinglemwe(Double singlemwe) {
		this.singlemwe = singlemwe;
	}

	public Double getTheorye() {
		return this.theorye;
	}

	public void setTheorye(Double theorye) {
		this.theorye = theorye;
	}

	public Double getYear() {
		return this.year;
	}

	public void setYear(Double year) {
		this.year = year;
	}

	
	
}