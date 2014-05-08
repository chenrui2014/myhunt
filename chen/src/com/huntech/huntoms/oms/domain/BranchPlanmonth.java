package com.huntech.huntoms.oms.domain;

import java.io.Serializable;
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
 * The persistent class for the N_BRANCH_PLANMONTH database table.
 * 
 */
@Entity
@Table(name="N_BRANCH_PLANMONTH")
public class BranchPlanmonth implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_BRANCH_PLANMONTH", sequenceName="SEQ_BRANCH_PLANMONTH" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_BRANCH_PLANMONTH" )
	@Column(name="BRAN_PLANMONTH_ID", unique=true, nullable=false, precision=22)
	private long branPlanmonthId;

	@Column(name="APPROVAL_STATUS", length=10)
	private String approvalStatus;

	private String fdesc;

    @Temporal( TemporalType.DATE)
	@Column(name="MONTH_")
	private Date month;

	@Column(name="PLAN_BUYE")
	private Double planBuye;

	@Column(name="PLAN_E")
	private Double planE;

	@Column(name="PLAN_FACTORYE")
	private Double planFactorye;

	@Column(name="PLAN_HOUR")
	private Double planHour;

	@Column(name="PLAN_ONE")
	private Double planOne;

	@Column(name="PLAN_STATUS", length=10)
	private String planStatus;

	@Column(name="THEORY_E")
	private Double theoryE;

    @Temporal( TemporalType.DATE)
	@Column(name="YEAR_")
	private Date year;

	//bi-directional many-to-one association to BOrganization
    @ManyToOne
	@JoinColumn(name="ORGAN_ID")
	private Organization organization;

    public BranchPlanmonth() {
    }
    
    public long getBranPlanmonthId() {
		return branPlanmonthId;
	}

	public void setBranPlanmonthId(long branPlanmonthId) {
		this.branPlanmonthId = branPlanmonthId;
	}

	public String getApprovalStatus() {
		return approvalStatus;
	}

	public void setApprovalStatus(String approvalStatus) {
		this.approvalStatus = approvalStatus;
	}

	public String getFdesc() {
		return fdesc;
	}

	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
	}

	public Date getMonth() {
		return month;
	}

	public void setMonth(Date month) {
		this.month = month;
	}

	public Double getPlanBuye() {
		return planBuye;
	}

	public void setPlanBuye(Double planBuye) {
		this.planBuye = planBuye;
	}

	public Double getPlanE() {
		return planE;
	}

	public void setPlanE(Double planE) {
		this.planE = planE;
	}

	public Double getPlanFactorye() {
		return planFactorye;
	}

	public void setPlanFactorye(Double planFactorye) {
		this.planFactorye = planFactorye;
	}

	public Double getPlanHour() {
		return planHour;
	}

	public void setPlanHour(Double planHour) {
		this.planHour = planHour;
	}

	public Double getPlanOne() {
		return planOne;
	}

	public void setPlanOne(Double planOne) {
		this.planOne = planOne;
	}

	public String getPlanStatus() {
		return planStatus;
	}

	public void setPlanStatus(String planStatus) {
		this.planStatus = planStatus;
	}

	public Double getTheoryE() {
		return theoryE;
	}

	public void setTheoryE(Double theoryE) {
		this.theoryE = theoryE;
	}

	public Date getYear() {
		return year;
	}

	public void setYear(Date year) {
		this.year = year;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}


}