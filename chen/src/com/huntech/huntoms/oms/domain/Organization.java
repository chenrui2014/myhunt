package com.huntech.huntoms.oms.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;




@Entity
@Table(name="B_ORGANIZATION")
public class Organization implements Serializable {

	private static final long serialVersionUID = 8559370821771335052L;
	
	@Id
	@Column(name="ORGAN_ID")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ORGAN")
	@SequenceGenerator(name = "SEQ_ORGAN", sequenceName = "SEQ_ORGAN", allocationSize = 1)
	private Long organId;  // 机构编号
	
	@ManyToOne
	@JoinColumn(name = "ORGAN_PID")
	private Organization organParent;  // 上级机构
	
	@OneToMany(mappedBy = "organParent", cascade = { CascadeType.REFRESH, CascadeType.PERSIST, CascadeType.MERGE })
	private List<Organization> children = new ArrayList<Organization>();
	
	@Column(name="ORGAN_CODE", length=10)
	private String organCode;  // 机构编码
	
	@Column(name="ORGAN_Name", length=60)
	private String organName;  // 集团名称
	
	@Column(name="Capacity")
	private BigDecimal capacity;  // 装机容量
	
	@Column(name="IMAGE_URL")
	private String imageUrl;  // 图片地址
	
	@Column(name="LONGITUDE", length=126)
	private Float longitude;  // 精度

	@Column(name="LATITUDE", length=126)
	private Float latitude;  // 纬度
	
	@Column(name="TELLPHONE", length=60)
	private String tellPhone;  // 联系电话
	
	@Column(name="MOBILEPHONE", length=30)
	private String mobilePhone;  // 联系人手机
	
	@Column(name="DUTYMAN", length=60)
	private String dutyMan;  // 监控联系人
	
	//@Column(name="ORGAN_TYPE")
	//private Float ="organType";
	
	@Column(name="ORGAN_TYPE")
	private Integer organType;  // 组织类型
	
public Integer getOrganType() {
		return organType;
	}

	public void setOrganType(Integer organType) {
		this.organType = organType;
	}

	public Float getDatapoint() {
		return datapoint;
	}

	public void setDatapoint(Float datapoint) {
		this.datapoint = datapoint;
	}

	public String getOrganParent1() {
		return organParent1;
	}

	public void setOrganParent1(String organParent1) {
		this.organParent1 = organParent1;
	}

	//	@OneToOne(cascade={CascadeType.ALL})
	@JoinColumn(name="DATAPOINT_ID")
	private Float datapoint;  // 数据点号
	
	@Column(name="ORGAN_PARENT")
	private String organParent1;  // 组织类型
	
	
	
	@Column(name="FDESC", length=255)
	private String fdesc;  // 备注 
	

	

	
	/*-------- getter & setter --------*/
	public Long getOrganId() {
		
		return organId;
	}

	public void setOrganId(Long organId) {
		this.organId = organId;
	}

	public Organization getOrganParent() {
		return organParent;
	}

	public void setOrganParent(Organization organParent) {
		this.organParent = organParent;
	}

	public String getOrganCode() {
		return organCode;
	}

	public void setOrganCode(String organCode) {
		this.organCode = organCode;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public String getTellPhone() {
		return tellPhone;
	}

	public void setTellPhone(String tellPhone) {
		this.tellPhone = tellPhone;
	}

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getDutyMan() {
		return dutyMan;
	}

	public void setDutyMan(String dutyMan) {
		this.dutyMan = dutyMan;
	}

//	public Datapoint getDatapoint() {
//		return datapoint;
//	}
//
//	public void setDatapoint(Datapoint datapoint) {
//		this.datapoint = datapoint;
//	}

	public String getFdesc() {
		return fdesc;
	}

	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
	}

	public List<Organization> getChildren() {
		return children;
	}

	public void setChildren(List<Organization> children) {
		this.children = children;
	}
	

	//bi-directional many-to-one association to RBranchYear
	@OneToMany(mappedBy="organization")
	private List<BranchYear> branchYears;

	//bi-directional many-to-one association to NBranchPlanmonth
	@OneToMany(mappedBy="organization")
	private List<BranchPlanmonth> branchPlanmonths;

	//bi-directional many-to-one association to NBranchPlanyear
	@OneToMany(mappedBy="organization")
	private List<BranchPlanyear> branchPlanyears;

	//bi-directional many-to-one association to RBranchDay
	@OneToMany(mappedBy="organization")
	private List<BranchDay> branchDays;

	//bi-directional many-to-one association to RBranchHour
	@OneToMany(mappedBy="organization")
	private List<BranchHour> branchHours;

	//bi-directional many-to-one association to RBranchMonth
	@OneToMany(mappedBy="organization")
	private List<BranchMonth> branchMonths;
	
	public List<BranchYear> getBranchYears() {
		return branchYears;
	}

	public void setBranchYears(List<BranchYear> branchYears) {
		this.branchYears = branchYears;
	}

	public List<BranchPlanmonth> getBranchPlanmonths() {
		return branchPlanmonths;
	}

	public void setBranchPlanmonths(List<BranchPlanmonth> branchPlanmonths) {
		this.branchPlanmonths = branchPlanmonths;
	}

	public List<BranchPlanyear> getBranchPlanyears() {
		return branchPlanyears;
	}

	public void setBranchPlanyears(List<BranchPlanyear> branchPlanyears) {
		this.branchPlanyears = branchPlanyears;
	}

	public List<BranchDay> getBranchDays() {
		return branchDays;
	}

	public void setBranchDays(List<BranchDay> branchDays) {
		this.branchDays = branchDays;
	}

	public List<BranchHour> getBranchHours() {
		return branchHours;
	}

	public void setBranchHours(List<BranchHour> branchHours) {
		this.branchHours = branchHours;
	}

	public List<BranchMonth> getBranchMonths() {
		return branchMonths;
	}

	public void setBranchMonths(List<BranchMonth> branchMonths) {
		this.branchMonths = branchMonths;
	}


}
