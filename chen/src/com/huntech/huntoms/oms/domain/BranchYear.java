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


@Entity
@Table(name="R_BRANCH_YEAR")
public class BranchYear implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="BRANCHYEAR_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_BRANCH_YEAR")
	@SequenceGenerator(name = "SEQ_BRANCH_YEAR", sequenceName = "SEQ_BRANCH_YEAR", allocationSize = 1)
	private Long branchyearId;  //主键ID 
	
	//private Integer organId = null; // 机构编号
	
	@Column(name="YEAR_") //年份     
	private Integer year ;
	
	@Column(name="E")
	private Double  e ;//发电量 
	
	@Column(name="THEORYE")
	private Double theorye; //理论发电量
	
	@Column(name="FACTORYE")
	private Double factorye; //厂用电量
	
	@Column(name="PURCHASENETE")
	private Double purchasenete; // 购网电量 
	
	@Column(name="ONNETE")
	private Double onnete; //上网电量 

	@Column(name="SINGLEMWE")
	private Double singlemwe; //单兆瓦发电量 
	
	@Column(name="EUSEHOURS")
	private Double eusehours; // 等效利用小时
	
	@Column(name="LOSSE")//弃光电量 
	private Double losse;

	
	public Long getBranchyearId() {
		return branchyearId;
	}

	public void setBranchyearId(Long branchyearId) {
		this.branchyearId = branchyearId;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getE() {
		return e;
	}

	public void setE(Double e) {
		this.e = e;
	}

	public Double getTheorye() {
		return theorye;
	}

	public void setTheorye(Double theorye) {
		this.theorye = theorye;
	}

	public Double getFactorye() {
		return factorye;
	}

	public void setFactorye(Double factorye) {
		this.factorye = factorye;
	}

	public Double getPurchasenete() {
		return purchasenete;
	}

	public void setPurchasenete(Double purchasenete) {
		this.purchasenete = purchasenete;
	}

	public Double getOnnete() {
		return onnete;
	}

	public void setOnnete(Double onnete) {
		this.onnete = onnete;
	}

	public Double getSinglemwe() {
		return singlemwe;
	}

	public void setSinglemwe(Double singlemwe) {
		this.singlemwe = singlemwe;
	}

	public Double getEusehours() {
		return eusehours;
	}

	public void setEusehours(Double eusehours) {
		this.eusehours = eusehours;
	}

	public Double getLosse() {
		return losse;
	}

	public void setLosse(Double losse) {
		this.losse = losse;
	}

	//bi-directional many-to-one association to BOrganization
    @ManyToOne
	@JoinColumn(name="ORGAN_ID")
	private Organization  organization;


	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}
