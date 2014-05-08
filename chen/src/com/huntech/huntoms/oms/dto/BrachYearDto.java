package com.huntech.huntoms.oms.dto;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.math.BigDecimal;

import com.huntech.huntoms.oms.domain.BranchYear;


public class BrachYearDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	private Long branchyearId;  //主键ID 
	
	//private Integer organId = null; // 机构编号
	
	private Integer year ;
	
	private Double  e ;//发电量 (实际)

	private Double theorye; //理论发电量
	
	private Double factorye; //厂用电量
	
	private Double factoryEffict;//厂用电率
	
	private Double sumFactorye;// 综合厂用电量
	
	private Double sumFactoryEffict;//综合厂用电率
	
	private Double purchasenete; // 购网电量 
	
	private Double onnete; //上网电量 

	private Double singlemwe; //单兆瓦发电量 
	
	private Double eusehours; // 等效利用小时
	
	private Double losse;//弃光电量 
	
	private Double planE; //计划发电量
	
	private Double planOne;//计划上网电量
	
	private String organName; //组织名称
	
	private Double effict ; //发电效率
	
	private Double  planEffict; //; 计划电量完成率
	
	private Double planOnnetEffict; // 计划上网电量完成率
	
	private Double capacity; //装机容量

	public Double getFactoryEffict() {
		return factoryEffict;
	}

	public void setFactoryEffict(Double factoryEffict) {
		this.factoryEffict = factoryEffict;
	}

	public Double getSumFactorye() {
		return sumFactorye;
	}

	public void setSumFactorye(Double sumFactorye) {
		this.sumFactorye = sumFactorye;
	}

	public Double getSumFactoryEffict() {
		return sumFactoryEffict;
	}

	public void setSumFactoryEffict(Double sumFactoryEffict) {
		this.sumFactoryEffict = sumFactoryEffict;
	}


	public Double getPlanOne() {
		return planOne;
	}

	public void setPlanOne(Double planOne) {
		this.planOne = planOne;
	}

	public Double getPlanE() {
		return planE;
	}

	public void setPlanE(Double planE) {
		this.planE = planE;
	}

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
	
	public String getOrganName() {
		return organName;
	}

	public void setOrganName(String organName) {
		this.organName = organName;
	}
	
	public Double getEffict() {
		return effict;
	}

	public void setEffict(Double effict) {
		this.effict = effict;
	}
	
	
	public Double getPlanEffict() {
		return planEffict;
	}

	public void setPlanEffict(Double planEffict) {
		this.planEffict = planEffict;
	}
	
	public Double getPlanOnnetEffict() {
		return planOnnetEffict;
	}

	public void setPlanOnnetEffict(Double planOnnetEffict) {
		this.planOnnetEffict = planOnnetEffict;
	}
	
	
	public Double getCapacity() {
		return capacity;
	}

	public void setCapacity(Double capacity) {
		this.capacity = capacity;
	}
	
	public static BrachYearDto  transfer(BranchYear branchyear){
		BrachYearDto dto = new BrachYearDto();
		dto.setBranchyearId(branchyear.getBranchyearId());
		dto.setE(branchyear.getE());
		dto.setEusehours(branchyear.getEusehours());
		dto.setFactorye(branchyear.getFactorye());
		dto.setLosse(branchyear.getLosse());
		dto.setOnnete(branchyear.getOnnete());
		dto.setPurchasenete(branchyear.getPurchasenete());
		dto.setSinglemwe(branchyear.getSinglemwe());
		dto.setTheorye(branchyear.getTheorye());
		dto.setYear(branchyear.getYear());
		return dto;
	}
	
	
	public static List<BrachYearDto> transferToBrachYearDto(List<Map<String, Object>> mapList ){
		if(mapList.size()==0){
			return null;
		}
		List<BrachYearDto>  list = new LinkedList<BrachYearDto>();
		BrachYearDto dto = null;
		for(int i=0;i<mapList.size();i++){
			dto = new BrachYearDto();
			BigDecimal planE = (BigDecimal)mapList.get(i).get("planE");
			if(null!=planE){
				dto.setPlanE(planE.doubleValue());
			}else{
				dto.setPlanE(0.00);
			}
			BigDecimal planOne = (BigDecimal)mapList.get(i).get("planOne");
			if(planOne!=null){
				dto.setPlanOne(planOne.doubleValue());
			}else{
				dto.setPlanOne(0.00);
			}
			BigDecimal e = (BigDecimal)mapList.get(i).get("e");
			if(e!=null){
				dto.setE(e.doubleValue());
			}else{
				dto.setE(0.00);
			}
			BigDecimal year = (BigDecimal)mapList.get(i).get("year");
			if(year!=null){
				dto.setYear(year.intValue());
			}else{
				dto.setYear(0);
			}
			BigDecimal theorye = (BigDecimal)mapList.get(i).get("theorye");
			if(null!=theorye){
				dto.setTheorye(theorye.doubleValue());
			}else{
				dto.setTheorye(0.00);
			}
			BigDecimal onnete = (BigDecimal)mapList.get(i).get("onnete");
			if(null!=onnete){
				dto.setOnnete(onnete.doubleValue());
			}else{
				dto.setOnnete(0.00);
			}
			BigDecimal losse = (BigDecimal)mapList.get(i).get("losse");
			if(losse!=null){
				dto.setLosse(losse.doubleValue());
			}else{
				dto.setLosse(0.00);
			}
			dto.setOrganName((String)mapList.get(i).get("organName"));
			BigDecimal effict = (BigDecimal)mapList.get(i).get("effict");
			if(null!=effict){
				dto.setEffict(effict.doubleValue());
			}else{
				dto.setEffict(0.00);
			}
			BigDecimal singlemwe = (BigDecimal)mapList.get(i).get("singleMWE");
			if(null!=singlemwe){
				dto.setSinglemwe(singlemwe.doubleValue());
			}else{
				dto.setSinglemwe(0.00);
			}
			BigDecimal planEffict = (BigDecimal)mapList.get(i).get("planEffict");
			if(null!=planEffict){
				dto.setPlanEffict(planEffict.doubleValue());
			}else{
				dto.setPlanEffict(0.00);
			}
			BigDecimal planOnnetEffict = (BigDecimal)mapList.get(i).get("planOnnetEffict");
			if(null!=planOnnetEffict){
				dto.setPlanOnnetEffict(planOnnetEffict.doubleValue());
			}else{
				dto.setPlanOnnetEffict(0.00);
			}
			BigDecimal factorye = (BigDecimal)mapList.get(i).get("factorye");
			if(null!=factorye){
				dto.setFactorye(factorye.doubleValue());
			}else{
				dto.setFactorye(0.00);
			}
			BigDecimal factoryEffict = (BigDecimal)mapList.get(i).get("factoryEffict");
			if(null!=factoryEffict){
				dto.setFactoryEffict(factoryEffict.doubleValue());
			}else{
				dto.setFactoryEffict(0.00);
			}
			BigDecimal sumFactorye = (BigDecimal)mapList.get(i).get("sumFactorye");
			if(sumFactorye!=null){
				dto.setSumFactorye(sumFactorye.doubleValue());
			}else{
				dto.setSumFactorye(0.00);
			}
			BigDecimal sumFactoryEffict = (BigDecimal)mapList.get(i).get("sumFactoryEffict");
			if(null!=sumFactoryEffict){
				dto.setSumFactoryEffict(sumFactoryEffict.doubleValue());
			}else{
				dto.setSumFactoryEffict(0.00);
			}
			
			BigDecimal eusehours = (BigDecimal)mapList.get(i).get("eusehours");
			if(null!=eusehours){
				dto.setEusehours(eusehours.doubleValue());
			}else{
				dto.setEusehours(0.0);
			}
			
			
			BigDecimal capacity = (BigDecimal)mapList.get(i).get("capacity");
			if(null!=capacity){
				dto.setCapacity(capacity.doubleValue());
			}else{
				dto.setCapacity(0.00);
			}
			
			BigDecimal purchasenete = (BigDecimal)mapList.get(i).get("purchasenete");
			if(null!=purchasenete){
				dto.setPurchasenete(purchasenete.doubleValue());
			}else{
				dto.setPurchasenete(0.00);
			}
			list.add(dto);
		}
		return list;
	}
}
