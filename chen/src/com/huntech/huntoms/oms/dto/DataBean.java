package com.huntech.huntoms.oms.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *  柱状图、折线图数据Bean 
 *  @author 张晨
 */
public class DataBean implements Serializable {

	private static final long serialVersionUID = -8156652362121196765L;
	
	private String dataName;  // 数据名称
	private Long dataId;  // 数据编号
	private List<BigDecimal> dataList = new ArrayList<BigDecimal>();  // 数据集合
	private String dataType;   // 数据显示类型
	private Integer yAxis;   // 使用的y坐标轴号
	private Boolean visible;  // 数据默认是否显示

	public String getDataName() {
		return dataName;
	}

	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	public Long getDataId() {
		return dataId;
	}

	public void setDataId(Long dataId) {
		this.dataId = dataId;
	}
	
	public List<BigDecimal> getDataList() {
		return dataList;
	}

	public void setDataList(List<BigDecimal> dataList) {
		this.dataList = dataList;
	}
	
	public void addData(BigDecimal data) {
		dataList.add(data);
	}

	public String getDataType() {
		return dataType;
	}

	public void setDataType(String dataType) {
		this.dataType = dataType;
	}

	public Integer getyAxis() {
		return yAxis;
	}

	public void setyAxis(Integer yAxis) {
		this.yAxis = yAxis;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}
	
}
