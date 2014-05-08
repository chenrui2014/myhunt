package com.huntech.huntoms.oms.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="B_DATAPOINT")
public class Datapoint implements Serializable {

	private static final long serialVersionUID = -7338778839923835122L;

	@Id
	@Column(name="DATAPOINT_ID")
	private Long datapointId;  // 点表编号
	
	@Column(name="DATAPOINT_NAME")
	private String datapointName;  // 点表名称
	
	@Column(name="DATAPOINT", length=20)
	private String datapoint;  // 数据点码
	
	@Column(name="DATAPOINT_CZ", length=20)
	private String datapointCz;  // 场站编码
	
	@Column(name="DATAPOINT_TYPE", length=60)
	private String datapointType;  // 点码类型
	
	@Column(name="LINK_ID")
	private BigDecimal linkId;  // 关联ID
	
	@Column(name="ENTITY_ID")
	private BigDecimal entityId;  // 存储数据表
	
	@Column(name="FDESC", length=60)
	private String fdesc;  // 备注

	
	/*-------- getter & setter --------*/
	public Long getDatapointId() {
		return datapointId;
	}

	public void setDatapointId(Long datapointId) {
		this.datapointId = datapointId;
	}

	public String getDatapointName() {
		return datapointName;
	}

	public void setDatapointName(String datapointName) {
		this.datapointName = datapointName;
	}

	public String getDatapoint() {
		return datapoint;
	}

	public void setDatapoint(String datapoint) {
		this.datapoint = datapoint;
	}

	public String getDatapointCz() {
		return datapointCz;
	}

	public void setDatapointCz(String datapointCz) {
		this.datapointCz = datapointCz;
	}

	public String getDatapointType() {
		return datapointType;
	}

	public void setDatapointType(String datapointType) {
		this.datapointType = datapointType;
	}

	public BigDecimal getLinkId() {
		return linkId;
	}

	public void setLinkId(BigDecimal linkId) {
		this.linkId = linkId;
	}

	public BigDecimal getEntityId() {
		return entityId;
	}

	public void setEntityId(BigDecimal entityId) {
		this.entityId = entityId;
	}

	public String getFdesc() {
		return fdesc;
	}

	public void setFdesc(String fdesc) {
		this.fdesc = fdesc;
	}
	
}
