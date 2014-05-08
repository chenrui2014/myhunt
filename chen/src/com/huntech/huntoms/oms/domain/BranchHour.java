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
 * The persistent class for the R_BRANCH_HOUR database table.
 * 
 */
@Entity
@Table(name="R_BRANCH_HOUR")
public class BranchHour implements Serializable {
	
    public BranchHour() {
    }
	
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="SEQ_BRANCH_HOUR", sequenceName="SEQ_BRANCH_HOUR" , allocationSize = 1)
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_BRANCH_HOUR")
	@Column(name="BRANCHHOUR_ID", unique=true, nullable=false, precision=22)
	private long branchhourId;

    

	@Temporal( TemporalType.DATE)
	@Column(name="DATE_")
	private Date date;

	private Double e;

    @Temporal( TemporalType.DATE)
	@Column(name="HOUR_")
	private Date hour;

	private Double losse;

	@Column(precision=18, scale=4)
	private Double onnete;

	//bi-directional many-to-one association to BOrganization
    @ManyToOne
	@JoinColumn(name="ORGAN_ID")
	private Organization organization;

    public long getBranchhourId() {
		return branchhourId;
	}

	public void setBranchhourId(long branchhourId) {
		this.branchhourId = branchhourId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Double getE() {
		return e;
	}

	public void setE(Double e) {
		this.e = e;
	}

	public Date getHour() {
		return hour;
	}

	public void setHour(Date hour) {
		this.hour = hour;
	}

	public Double getLosse() {
		return losse;
	}

	public void setLosse(Double losse) {
		this.losse = losse;
	}

	public Double getOnnete() {
		return onnete;
	}

	public void setOnnete(Double onnete) {
		this.onnete = onnete;
	}

	public Organization getOrganization() {
		return organization;
	}

	public void setOrganization(Organization organization) {
		this.organization = organization;
	}
}