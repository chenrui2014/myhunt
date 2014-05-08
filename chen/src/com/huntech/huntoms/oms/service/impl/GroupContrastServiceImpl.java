package com.huntech.huntoms.oms.service.impl;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.huntech.huntoms.oms.dto.DataBean;
import com.huntech.huntoms.oms.dto.GroupEBean;
import com.huntech.huntoms.oms.dto.RankingBean;
import com.huntech.huntoms.oms.dto.RankingDataBean;
import com.huntech.huntoms.oms.repository.GroupContrastJdbc;
import com.huntech.huntoms.oms.service.GroupContrastService;

@Service
@Transactional(readOnly = true)
public class GroupContrastServiceImpl implements GroupContrastService {

	@Autowired
	private GroupContrastJdbc groupContrastJdbc; 

	@Override
	public List<Long> getStationYearConditionData() {
		ArrayList<Long> result = new ArrayList<Long>();
		List<Map<String, Object>> queryList = groupContrastJdbc.getStationYearConditionData();  // 获取查询结果
		Iterator<Map<String, Object>> ite = queryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("SYEAR") && item.get(key)!=null) {
					if (!result.contains(Long.valueOf((item.get(key).toString())))) {
						result.add(Long.valueOf(item.get(key).toString()));
					}
				} else if (key.equals("PYEAR") && item.get(key)!=null) {
					if (!result.contains(Long.valueOf((item.get(key).toString())))) {
						result.add(Long.valueOf(item.get(key).toString()));
					}
				}
			}
		}
		Collections.sort(result);
		return result;
	}
	
	public List<Long> getStationMonthConditionData() {
		ArrayList<Long> result = new ArrayList<Long>();
		List<Map<String, Object>> queryList = groupContrastJdbc.getStationMonthConditionData();  // 获取查询结果
		Iterator<Map<String, Object>> ite = queryList.iterator(); 
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("YEAR") && item.get(key)!=null) {
					if (!result.contains(Long.valueOf((item.get(key).toString())))) {
						result.add(Long.valueOf(item.get(key).toString()));
					}
				} 
			}
		}
		Collections.sort(result);
		return result;
	}
	
	@Override
	public GroupEBean getStationDevoteEYearData(Long year) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getStationDevoteEYearData(year);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean capacityBean = new DataBean();  // 计划完成率Bean
		capacityBean.setDataType("column");
		capacityBean.setyAxis(1);
		capacityBean.setDataName("装机容量");
		DataBean onNetEBean = new DataBean();  // 计划发电量Bean
		onNetEBean.setDataType("line");
		onNetEBean.setyAxis(0);
		onNetEBean.setDataName("上网电量");
		DataBean eBean = new DataBean();  // 发电量Bean
		eBean.setDataType("line");
		eBean.setyAxis(0);
		eBean.setDataName("发电量");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		result.setTitle(year + "年电站间厂用电量对比分析");
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					String name = item.get(key).toString(); 
					xAxisCategories.add(name);
				} else if (key.equals("CAPACITY")) {
					if (item.get(key) != null) {
						BigDecimal capacity = new BigDecimal(df.format(item.get(key))); 
						capacityBean.setyAxis(1);
						capacityBean.addData(capacity);	
					} else {
						capacityBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item.get(key) != null) {
						BigDecimal onNetE = new BigDecimal(df.format(item.get(key))); 
						onNetEBean.setyAxis(0);
						onNetEBean.addData(onNetE);	
					} else {
						onNetEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						eBean.setyAxis(0);
						eBean.addData(e);	
					} else {
						eBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(capacityBean);
		dataBeanList.add(onNetEBean);
		dataBeanList.add(eBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}

	@Override
	public GroupEBean getStationDevoteEMonthData(Long year, Long month) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getStationDevoteEMonthData(year, month);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean capacityBean = new DataBean();  // 计划完成率Bean
		capacityBean.setDataType("column");
		capacityBean.setyAxis(1);
		capacityBean.setDataName("装机容量");
		DataBean onNetEBean = new DataBean();  // 计划发电量Bean
		onNetEBean.setDataType("line");
		onNetEBean.setyAxis(0);
		onNetEBean.setDataName("上网电量");
		DataBean eBean = new DataBean();  // 发电量Bean
		eBean.setDataType("line");
		eBean.setyAxis(0);
		eBean.setDataName("发电量");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		result.setTitle(year + "年" + month +  "月电站间厂用电量对比分析");
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					String name = item.get(key).toString(); 
					xAxisCategories.add(name);
				} else if (key.equals("CAPACITY")) {
					if (item.get(key) != null) {
						BigDecimal capacity = new BigDecimal(df.format(item.get(key))); 
						capacityBean.setyAxis(1);
						capacityBean.addData(capacity);	
					} else {
						capacityBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item.get(key) != null) {
						BigDecimal onNetE = new BigDecimal(df.format(item.get(key))); 
						onNetEBean.setyAxis(0);
						onNetEBean.addData(onNetE);	
					} else {
						onNetEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						eBean.setyAxis(0);
						eBean.addData(e);	
					} else {
						eBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(capacityBean);
		dataBeanList.add(onNetEBean);
		dataBeanList.add(eBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}
	
	@Override
	public GroupEBean getStationFactoryEYearData(Long year) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getStationFactoryEYearData(year);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean factorytEBean = new DataBean();  // 计划发电量Bean
		factorytEBean.setDataType("column");
		factorytEBean.setyAxis(0);
		factorytEBean.setDataName("厂用电量");
		DataBean eBean = new DataBean();  // 发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("发电量");
		DataBean factoryscaleBean = new DataBean();  // 计划完成率Bean
		factoryscaleBean.setDataType("line");
		factoryscaleBean.setyAxis(1);
		factoryscaleBean.setDataName("厂用电率");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		result.setTitle(year + "年电站间厂用电量对比分析");
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					String name = item.get(key).toString(); 
					xAxisCategories.add(name);
				} else if (key.equals("FACTORYE")) {
					if (item.get(key) != null) {
						BigDecimal planE = new BigDecimal(df.format(item.get(key))); 
						factorytEBean.setyAxis(0);
						factorytEBean.addData(planE);	
					} else {
						factorytEBean.addData(new BigDecimal(0));	
					}
				}  else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						eBean.setyAxis(0);
						eBean.addData(e);	
					} else {
						eBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("FACTORYSCALE")) {
					if (item.get(key) != null) {
						BigDecimal planeComplete = new BigDecimal(df.format(item.get(key))); 
						factoryscaleBean.setyAxis(1);
						factoryscaleBean.addData(planeComplete);	
					} else {
						factoryscaleBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(factorytEBean);
		dataBeanList.add(eBean);
		dataBeanList.add(factoryscaleBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}

	@Override
	public GroupEBean getStationFactoryEMonthData(Long year, Long month) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getStationFactoryEMonthData(year, month);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean factorytEBean = new DataBean();  // 计划发电量Bean
		factorytEBean.setDataType("column");
		factorytEBean.setyAxis(0);
		factorytEBean.setDataName("厂用电量");
		DataBean eBean = new DataBean();  // 发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("发电量");
		DataBean factoryscaleBean = new DataBean();  // 计划完成率Bean
		factoryscaleBean.setDataType("line");
		factoryscaleBean.setyAxis(1);
		factoryscaleBean.setDataName("厂用电率");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		result.setTitle(year + "年" + month + "月电站间厂用电量对比分析");
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					String name = item.get(key).toString(); 
					xAxisCategories.add(name);
				} else if (key.equals("FACTORYE")) {
					if (item.get(key) != null) {
						BigDecimal planE = new BigDecimal(df.format(item.get(key))); 
						factorytEBean.setyAxis(0);
						factorytEBean.addData(planE);	
					} else {
						factorytEBean.addData(new BigDecimal(0));	
					}
				}  else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						eBean.setyAxis(0);
						eBean.addData(e);	
					} else {
						eBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("FACTORYSCALE")) {
					if (item.get(key) != null) {
						BigDecimal planeComplete = new BigDecimal(df.format(item.get(key))); 
						factoryscaleBean.setyAxis(1);
						factoryscaleBean.addData(planeComplete);	
					} else {
						factoryscaleBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(factorytEBean);
		dataBeanList.add(eBean);
		dataBeanList.add(factoryscaleBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}

	@Override
	public GroupEBean getStationCompreFactoryEYearData(Long year) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getStationCompreFactoryEYearData(year);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean compreFactoryEBean = new DataBean();  // 计划发电量Bean
		compreFactoryEBean.setDataType("column");
		compreFactoryEBean.setyAxis(0);
		compreFactoryEBean.setDataName("综合厂用电量");
		DataBean eBean = new DataBean();  // 发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("发电量");
		DataBean compreFactoryscaleBean = new DataBean();  // 计划完成率Bean
		compreFactoryscaleBean.setDataType("line");
		compreFactoryscaleBean.setyAxis(1);
		compreFactoryscaleBean.setDataName("综合厂用电率");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		result.setTitle(year + "年电站间综合厂用电量对比分析");
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					String name = item.get(key).toString(); 
					xAxisCategories.add(name);
				} else if (key.equals("COMPREFACTORYE")) {
					if (item.get(key) != null) {
						BigDecimal planE = new BigDecimal(df.format(item.get(key))); 
						compreFactoryEBean.setyAxis(0);
						compreFactoryEBean.addData(planE);	
					} else {
						compreFactoryEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						eBean.setyAxis(0);
						eBean.addData(e);	
					} else {
						eBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("COMPREFACTORYSCALE")) {
					if (item.get(key) != null) {
						BigDecimal planeComplete = new BigDecimal(df.format(item.get(key))); 
						compreFactoryscaleBean.setyAxis(1);
						compreFactoryscaleBean.addData(planeComplete);	
					} else {
						compreFactoryscaleBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(compreFactoryEBean);
		dataBeanList.add(eBean);
		dataBeanList.add(compreFactoryscaleBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}

	@Override
	public GroupEBean getStationCompreFactoryEMonthData(Long year, Long month) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getStationCompreFactoryEMonthData(year, month);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean compreFactoryEBean = new DataBean();  // 计划发电量Bean
		compreFactoryEBean.setDataType("column");
		compreFactoryEBean.setyAxis(0);
		compreFactoryEBean.setDataName("综合厂用电量");
		DataBean eBean = new DataBean();  // 发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("发电量");
		DataBean compreFactoryscaleBean = new DataBean();  // 计划完成率Bean
		compreFactoryscaleBean.setDataType("line");
		compreFactoryscaleBean.setyAxis(1);
		compreFactoryscaleBean.setDataName("综合厂用电率");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		result.setTitle(year + "年" + month + "月电站间综合厂用电量对比分析");
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					String name = item.get(key).toString(); 
					xAxisCategories.add(name);
				} else if (key.equals("COMPREFACTORYE")) {
					if (item.get(key) != null) {
						BigDecimal planE = new BigDecimal(df.format(item.get(key))); 
						compreFactoryEBean.setyAxis(0);
						compreFactoryEBean.addData(planE);	
					} else {
						compreFactoryEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						eBean.setyAxis(0);
						eBean.addData(e);	
					} else {
						eBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("COMPREFACTORYSCALE")) {
					if (item.get(key) != null) {
						BigDecimal planeComplete = new BigDecimal(df.format(item.get(key))); 
						compreFactoryscaleBean.setyAxis(1);
						compreFactoryscaleBean.addData(planeComplete);	
					} else {
						compreFactoryscaleBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(compreFactoryEBean);
		dataBeanList.add(eBean);
		dataBeanList.add(compreFactoryscaleBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}

	@Override
	public GroupEBean getStationPlanEYearData(Long year) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getStationPlanEYearData(year);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean planEBean = new DataBean();  // 发电量Bean
		planEBean.setDataType("column");
		planEBean.setyAxis(0);
		planEBean.setDataName("计划发电量");
		DataBean eBean = new DataBean();  // 计划发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("发电量");
		result.setDataBeanList(dataBeanList);
		DataBean eScaleBean = new DataBean();  // 计划完成率Bean
		eScaleBean.setDataType("line");
		eScaleBean.setyAxis(1);
		eScaleBean.setDataName("计划完成率");
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		result.setTitle(year + "年电站间发电计划执行分析");
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					String name = item.get(key).toString(); 
					xAxisCategories.add(name);
				} else if (key.equals("PLANE")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						planEBean.setyAxis(0);
						planEBean.addData(e);	
					} else {
						planEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal planE = new BigDecimal(df.format(item.get(key))); 
						eBean.setyAxis(0);
						eBean.addData(planE);	
					} else {
						eBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ESCALE")) {
					if (item.get(key) != null) {
						BigDecimal planeComplete = new BigDecimal(df.format(item.get(key))); 
						eScaleBean.setyAxis(1);
						eScaleBean.addData(planeComplete);	
					} else {
						eScaleBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(planEBean);
		dataBeanList.add(eBean);
		dataBeanList.add(eScaleBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}

	@Override
	public GroupEBean getStationPlanEMonthData(Long year, Long month) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getStationPlanEMonthData(year, month);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean planEBean = new DataBean();  // 发电量Bean
		planEBean.setDataType("column");
		planEBean.setyAxis(0);
		planEBean.setDataName("计划发电量");
		DataBean eBean = new DataBean();  // 计划发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("发电量");
		result.setDataBeanList(dataBeanList);
		DataBean eScaleBean = new DataBean();  // 计划完成率Bean
		eScaleBean.setDataType("line");
		eScaleBean.setyAxis(1);
		eScaleBean.setDataName("计划完成率");
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		result.setTitle(year + "年" + month + "月电站间发电计划执行分析");
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					String name = item.get(key).toString(); 
					xAxisCategories.add(name);
				} else if (key.equals("PLANE")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						planEBean.setyAxis(0);
						planEBean.addData(e);	
					} else {
						planEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal planE = new BigDecimal(df.format(item.get(key))); 
						eBean.setyAxis(0);
						eBean.addData(planE);	
					} else {
						eBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ESCALE")) {
					if (item.get(key) != null) {
						BigDecimal planeComplete = new BigDecimal(df.format(item.get(key))); 
						eScaleBean.setyAxis(1);
						eScaleBean.addData(planeComplete);	
					} else {
						eScaleBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(planEBean);
		dataBeanList.add(eBean);
		dataBeanList.add(eScaleBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}

	@Override
	public GroupEBean getStationPlanOnNetEYearData(Long year) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getStationPlanOnNetEYearData(year);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean onNetEScaleBean = new DataBean();  // 计划完成率Bean
		DataBean planOnNetEBean = new DataBean();  // 发电量Bean
		planOnNetEBean.setDataType("column");
		planOnNetEBean.setyAxis(0);
		planOnNetEBean.setDataName("计划上网电量");
		DataBean onNetEBean = new DataBean();  // 计划发电量Bean
		onNetEBean.setDataType("column");
		onNetEBean.setyAxis(0);
		onNetEBean.setDataName("上网电量");
		onNetEScaleBean.setDataType("line");
		onNetEScaleBean.setyAxis(1);
		onNetEScaleBean.setDataName("计划完成率");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		result.setTitle(year + "年电站间上网计划执行分析");
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					String name = item.get(key).toString(); 
					xAxisCategories.add(name);
				} else if (key.equals("PLANONNETE")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						planOnNetEBean.setyAxis(0);
						planOnNetEBean.addData(e);	
					} else {
						planOnNetEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item.get(key) != null) {
						BigDecimal planE = new BigDecimal(df.format(item.get(key))); 
						onNetEBean.setyAxis(0);
						onNetEBean.addData(planE);	
					} else {
						onNetEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETESCALE")) {
					if (item.get(key) != null) {
						BigDecimal planeComplete = new BigDecimal(df.format(item.get(key))); 
						onNetEScaleBean.setyAxis(1);
						onNetEScaleBean.addData(planeComplete);	
					} else {
						onNetEScaleBean.addData(new BigDecimal(0));	
					}
				} 
			}
		}		
		dataBeanList.add(planOnNetEBean);
		dataBeanList.add(onNetEBean);
		dataBeanList.add(onNetEScaleBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}

	@Override
	public GroupEBean getStationPlanOnNetEMonthData(Long year, Long month) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getStationPlanOnNetEMonthData(year, month);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean onNetEScaleBean = new DataBean();  // 计划完成率Bean
		DataBean planOnNetEBean = new DataBean();  // 发电量Bean
		planOnNetEBean.setDataType("column");
		planOnNetEBean.setyAxis(0);
		planOnNetEBean.setDataName("计划上网电量");
		DataBean onNetEBean = new DataBean();  // 计划发电量Bean
		onNetEBean.setDataType("column");
		onNetEBean.setyAxis(0);
		onNetEBean.setDataName("上网电量");
		onNetEScaleBean.setDataType("line");
		onNetEScaleBean.setyAxis(1);
		onNetEScaleBean.setDataName("计划完成率");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		result.setTitle(year + "年" + month +"月电站间上网执行分析");
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					String name = item.get(key).toString(); 
					xAxisCategories.add(name);
				} else if (key.equals("PLANONNETE")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						planOnNetEBean.setyAxis(0);
						planOnNetEBean.addData(e);	
					} else {
						planOnNetEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item.get(key) != null) {
						BigDecimal planE = new BigDecimal(df.format(item.get(key))); 
						onNetEBean.setyAxis(0);
						onNetEBean.addData(planE);	
					} else {
						onNetEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETESCALE")) {
					if (item.get(key) != null) {
						BigDecimal planeComplete = new BigDecimal(df.format(item.get(key))); 
						onNetEScaleBean.setyAxis(1);
						onNetEScaleBean.addData(planeComplete);	
					} else {
						onNetEScaleBean.addData(new BigDecimal(0));	
					}
				} 
			}
		}		
		dataBeanList.add(planOnNetEBean);
		dataBeanList.add(onNetEBean);
		dataBeanList.add(onNetEScaleBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}

	@Override
	public List<RankingBean> getStationRankingYearData(Long year) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getStationRankingYearData(year);  // 获取查询结果
		List<RankingBean> result = new ArrayList<RankingBean>();
	    RankingBean efficEBean = new RankingBean();
	    efficEBean.setCode("efficE");
	    efficEBean.setTitle("电站发电效率排行");
	    List<RankingDataBean> efficEList = new ArrayList<RankingDataBean>();
	    efficEBean.setDataList(efficEList);
	    RankingBean singleMWEBean = new RankingBean();
	    singleMWEBean.setCode("singleMWE");
	    singleMWEBean.setTitle("电站单兆瓦发电量排行");
	    List<RankingDataBean> singleMWEList = new ArrayList<RankingDataBean>();
	    singleMWEBean.setDataList(singleMWEList);
	    RankingBean sameUseHourBean = new RankingBean();
	    sameUseHourBean.setCode("sameUseHour");
	    sameUseHourBean.setTitle("电站等效利用小时排行");
	    List<RankingDataBean> sameUseHourList = new ArrayList<RankingDataBean>();
	    sameUseHourBean.setDataList(sameUseHourList);
	    RankingBean eBean = new RankingBean();
	    eBean.setCode("e");
	    eBean.setTitle("电站发电量排行");
	    List<RankingDataBean> eList = new ArrayList<RankingDataBean>();
	    eBean.setDataList(eList);
	    RankingBean eScaleBean = new RankingBean();
	    eScaleBean.setCode("eScale");
	    eScaleBean.setTitle("电站发电量计划完成率排行");
	    List<RankingDataBean> eScaleList = new ArrayList<RankingDataBean>();
	    eScaleBean.setDataList(eScaleList);
	    RankingBean factoryEScaleBean = new RankingBean();
	    factoryEScaleBean.setCode("factoryEScale");
	    factoryEScaleBean.setTitle("电站厂用电率排行");
	    List<RankingDataBean> factoryEScaleList = new ArrayList<RankingDataBean>();
	    factoryEScaleBean.setDataList(factoryEScaleList);
	    RankingBean onNetEBean = new RankingBean();
	    onNetEBean.setCode("onNetE");
	    onNetEBean.setTitle("电站上网电量排行");
	    List<RankingDataBean> onNetEList = new ArrayList<RankingDataBean>();
	    onNetEBean.setDataList(onNetEList);
	    RankingBean onNetEScaleBean = new RankingBean();
	    onNetEScaleBean.setCode("onNetEScale");
	    onNetEScaleBean.setTitle("电站上网电量计划完成率排行");
	    List<RankingDataBean> onNetEScaleList = new ArrayList<RankingDataBean>();
	    onNetEScaleBean.setDataList(onNetEScaleList);
	    RankingBean compreFactoryEScaleBean = new RankingBean();
	    compreFactoryEScaleBean.setCode("compreFactoryEScale");
	    compreFactoryEScaleBean.setTitle("电站综合厂用电率排行");
	    List<RankingDataBean> compreFactoryEScaleList = new ArrayList<RankingDataBean>();
	    compreFactoryEScaleBean.setDataList(compreFactoryEScaleList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		while (ite.hasNext()) {
			Map<String, Object> item = ite.next();
			RankingDataBean efficData = new RankingDataBean();
			RankingDataBean singleMWEData = new RankingDataBean();
			RankingDataBean sameUseHourData = new RankingDataBean();
			RankingDataBean eData = new RankingDataBean();
			RankingDataBean eScaleData = new RankingDataBean();
			RankingDataBean factoryEScaleData = new RankingDataBean();
			RankingDataBean onNetEData = new RankingDataBean();
			RankingDataBean onNetEScaleData = new RankingDataBean();
			RankingDataBean compreFactoryEScaleData = new RankingDataBean();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					efficData.setName(item.get(key).toString());
					singleMWEData.setName(item.get(key).toString());
					sameUseHourData.setName(item.get(key).toString());
					eData.setName(item.get(key).toString());
					eScaleData.setName(item.get(key).toString());
					factoryEScaleData.setName(item.get(key).toString());
					onNetEData.setName(item.get(key).toString());
					onNetEScaleData.setName(item.get(key).toString());
					compreFactoryEScaleData.setName(item.get(key).toString());
				} else if (key.equals("EFFICE")) {
					if (item.get(key) != null) {
						efficData.setValue(new BigDecimal(df.format(item.get(key))));	
					} else {
						efficData.setValue(new BigDecimal(0));	
					}
				}  else if (key.equals("SINGLEMWE")) {
					if (item.get(key) != null) {
						singleMWEData.setValue(new BigDecimal(df.format(item.get(key))));	
					} else {
						singleMWEData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("SAMEUSEHOUR")) {
					if (item.get(key) != null) {
						sameUseHourData.setValue(new BigDecimal(df.format(item.get(key))));	
					} else {
						sameUseHourData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						eData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						eData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ESCALE")) {
					if (item.get(key) != null) {
						eScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						eScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("FACTORYESCALE")) {
					if (item.get(key) != null) {
						factoryEScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						factoryEScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item.get(key) != null) {
						onNetEData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						onNetEData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETESCALE")) {
					if (item.get(key) != null) {
						onNetEScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						onNetEScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("COMPREFACTORYESCALE")) {
					if (item.get(key) != null) {
						compreFactoryEScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						compreFactoryEScaleData.setValue(new BigDecimal(0));	
					}
				}
			}
			efficEBean.getDataList().add(efficData);
			singleMWEBean.getDataList().add(singleMWEData);
			sameUseHourBean.getDataList().add(sameUseHourData);
			eBean.getDataList().add(eData);
			eScaleBean.getDataList().add(eScaleData);
			factoryEScaleBean.getDataList().add(factoryEScaleData);
			onNetEBean.getDataList().add(onNetEData);
			onNetEScaleBean.getDataList().add(onNetEScaleData);
			compreFactoryEScaleBean.getDataList().add(compreFactoryEScaleData);
		}
		if (!efficEBean.getDataList().isEmpty()) {
			Collections.sort(efficEBean.getDataList());	
		}
		if (!singleMWEBean.getDataList().isEmpty()) {
			Collections.sort(singleMWEBean.getDataList());	
		}
		if (!sameUseHourBean.getDataList().isEmpty()) {
			Collections.sort(sameUseHourBean.getDataList());	
		}
		if (!eBean.getDataList().isEmpty()) {
			Collections.sort(eBean.getDataList());	
		}
		if (!eScaleBean.getDataList().isEmpty()) {
			Collections.sort(eScaleBean.getDataList());	
		}
		if (!factoryEScaleBean.getDataList().isEmpty()) {
			Collections.sort(factoryEScaleBean.getDataList());	
		}
		if (!onNetEBean.getDataList().isEmpty()) {
			Collections.sort(onNetEBean.getDataList());	
		}
		if (!onNetEScaleBean.getDataList().isEmpty()) {
			Collections.sort(onNetEScaleBean.getDataList());	
		}
		if (!compreFactoryEScaleBean.getDataList().isEmpty()) {
			Collections.sort(compreFactoryEScaleBean.getDataList());	
		}
		result.add(efficEBean);
		result.add(singleMWEBean);
		result.add(sameUseHourBean);
		result.add(eBean);
		result.add(eScaleBean);
		result.add(factoryEScaleBean);
		result.add(onNetEBean);
		result.add(onNetEScaleBean);
		result.add(compreFactoryEScaleBean);
		return result;
	}

	@Override
	public List<RankingBean> getStationRankingMonthData(Long year, Long month) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getStationRankingMonthData(year,month);  // 获取查询结果
		List<RankingBean> result = new ArrayList<RankingBean>();
	    RankingBean efficEBean = new RankingBean();
	    efficEBean.setCode("efficE");
	    efficEBean.setTitle("电站发电效率排行");
	    List<RankingDataBean> efficEList = new ArrayList<RankingDataBean>();
	    efficEBean.setDataList(efficEList);
	    RankingBean singleMWEBean = new RankingBean();
	    singleMWEBean.setCode("singleMWE");
	    singleMWEBean.setTitle("电站单兆瓦发电量排行");
	    List<RankingDataBean> singleMWEList = new ArrayList<RankingDataBean>();
	    singleMWEBean.setDataList(singleMWEList);
	    RankingBean sameUseHourBean = new RankingBean();
	    sameUseHourBean.setCode("sameUseHour");
	    sameUseHourBean.setTitle("电站等效利用小时排行");
	    List<RankingDataBean> sameUseHourList = new ArrayList<RankingDataBean>();
	    sameUseHourBean.setDataList(sameUseHourList);
	    RankingBean eBean = new RankingBean();
	    eBean.setCode("e");
	    eBean.setTitle("电站发电量排行");
	    List<RankingDataBean> eList = new ArrayList<RankingDataBean>();
	    eBean.setDataList(eList);
	    RankingBean eScaleBean = new RankingBean();
	    eScaleBean.setCode("eScale");
	    eScaleBean.setTitle("电站发电量计划完成率排行");
	    List<RankingDataBean> eScaleList = new ArrayList<RankingDataBean>();
	    eScaleBean.setDataList(eScaleList);
	    RankingBean factoryEScaleBean = new RankingBean();
	    factoryEScaleBean.setCode("factoryEScale");
	    factoryEScaleBean.setTitle("电站厂用电率排行");
	    List<RankingDataBean> factoryEScaleList = new ArrayList<RankingDataBean>();
	    factoryEScaleBean.setDataList(factoryEScaleList);
	    RankingBean onNetEBean = new RankingBean();
	    onNetEBean.setCode("onNetE");
	    onNetEBean.setTitle("电站上网电量排行");
	    List<RankingDataBean> onNetEList = new ArrayList<RankingDataBean>();
	    onNetEBean.setDataList(onNetEList);
	    RankingBean onNetEScaleBean = new RankingBean();
	    onNetEScaleBean.setCode("onNetEScale");
	    onNetEScaleBean.setTitle("电站上网电量计划完成率排行");
	    List<RankingDataBean> onNetEScaleList = new ArrayList<RankingDataBean>();
	    onNetEScaleBean.setDataList(onNetEScaleList);
	    RankingBean compreFactoryEScaleBean = new RankingBean();
	    compreFactoryEScaleBean.setCode("compreFactoryEScale");
	    compreFactoryEScaleBean.setTitle("电站综合厂用电率排行");
	    List<RankingDataBean> compreFactoryEScaleList = new ArrayList<RankingDataBean>();
	    compreFactoryEScaleBean.setDataList(compreFactoryEScaleList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		while (ite.hasNext()) {
			Map<String, Object> item = ite.next();
			RankingDataBean efficData = new RankingDataBean();
			RankingDataBean singleMWEData = new RankingDataBean();
			RankingDataBean sameUseHourData = new RankingDataBean();
			RankingDataBean eData = new RankingDataBean();
			RankingDataBean eScaleData = new RankingDataBean();
			RankingDataBean factoryEScaleData = new RankingDataBean();
			RankingDataBean onNetEData = new RankingDataBean();
			RankingDataBean onNetEScaleData = new RankingDataBean();
			RankingDataBean compreFactoryEScaleData = new RankingDataBean();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					efficData.setName(item.get(key).toString());
					singleMWEData.setName(item.get(key).toString());
					sameUseHourData.setName(item.get(key).toString());
					eData.setName(item.get(key).toString());
					eScaleData.setName(item.get(key).toString());
					factoryEScaleData.setName(item.get(key).toString());
					onNetEData.setName(item.get(key).toString());
					onNetEScaleData.setName(item.get(key).toString());
					compreFactoryEScaleData.setName(item.get(key).toString());
				} else if (key.equals("EFFICE")) {
					if (item.get(key) != null) {
						efficData.setValue(new BigDecimal(df.format(item.get(key))));	
					} else {
						efficData.setValue(new BigDecimal(0));	
					}
				}  else if (key.equals("SINGLEMWE")) {
					if (item.get(key) != null) {
						singleMWEData.setValue(new BigDecimal(df.format(item.get(key))));	
					} else {
						singleMWEData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("SAMEUSEHOUR")) {
					if (item.get(key) != null) {
						sameUseHourData.setValue(new BigDecimal(df.format(item.get(key))));	
					} else {
						sameUseHourData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						eData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						eData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ESCALE")) {
					if (item.get(key) != null) {
						eScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						eScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("FACTORYESCALE")) {
					if (item.get(key) != null) {
						factoryEScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						factoryEScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item.get(key) != null) {
						onNetEData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						onNetEData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETESCALE")) {
					if (item.get(key) != null) {
						onNetEScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						onNetEScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("COMPREFACTORYESCALE")) {
					if (item.get(key) != null) {
						compreFactoryEScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						compreFactoryEScaleData.setValue(new BigDecimal(0));	
					}
				}
			}
			efficEBean.getDataList().add(efficData);
			singleMWEBean.getDataList().add(singleMWEData);
			sameUseHourBean.getDataList().add(sameUseHourData);
			eBean.getDataList().add(eData);
			eScaleBean.getDataList().add(eScaleData);
			factoryEScaleBean.getDataList().add(factoryEScaleData);
			onNetEBean.getDataList().add(onNetEData);
			onNetEScaleBean.getDataList().add(onNetEScaleData);
			compreFactoryEScaleBean.getDataList().add(compreFactoryEScaleData);
		}
		if (!efficEBean.getDataList().isEmpty()) {
			Collections.sort(efficEBean.getDataList());	
		}
		if (!singleMWEBean.getDataList().isEmpty()) {
			Collections.sort(singleMWEBean.getDataList());	
		}
		if (!sameUseHourBean.getDataList().isEmpty()) {
			Collections.sort(sameUseHourBean.getDataList());	
		}
		if (!eBean.getDataList().isEmpty()) {
			Collections.sort(eBean.getDataList());	
		}
		if (!eScaleBean.getDataList().isEmpty()) {
			Collections.sort(eScaleBean.getDataList());	
		}
		if (!factoryEScaleBean.getDataList().isEmpty()) {
			Collections.sort(factoryEScaleBean.getDataList());	
		}
		if (!onNetEBean.getDataList().isEmpty()) {
			Collections.sort(onNetEBean.getDataList());	
		}
		if (!onNetEScaleBean.getDataList().isEmpty()) {
			Collections.sort(onNetEScaleBean.getDataList());	
		}
		if (!compreFactoryEScaleBean.getDataList().isEmpty()) {
			Collections.sort(compreFactoryEScaleBean.getDataList());	
		}
		result.add(efficEBean);
		result.add(singleMWEBean);
		result.add(sameUseHourBean);
		result.add(eBean);
		result.add(eScaleBean);
		result.add(factoryEScaleBean);
		result.add(onNetEBean);
		result.add(onNetEScaleBean);
		result.add(compreFactoryEScaleBean);
		return result;
	}

	@Override
	public List<RankingBean> getBranchStationRankingYearData(Long id, Long year) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getBranchStationRankingYearData(id,year);  // 获取查询结果
		List<RankingBean> result = new ArrayList<RankingBean>();
	    RankingBean efficEBean = new RankingBean();
	    efficEBean.setCode("efficE");
	    efficEBean.setTitle("电站发电效率排行");
	    List<RankingDataBean> efficEList = new ArrayList<RankingDataBean>();
	    efficEBean.setDataList(efficEList);
	    RankingBean singleMWEBean = new RankingBean();
	    singleMWEBean.setCode("singleMWE");
	    singleMWEBean.setTitle("电站单兆瓦发电量排行");
	    List<RankingDataBean> singleMWEList = new ArrayList<RankingDataBean>();
	    singleMWEBean.setDataList(singleMWEList);
	    RankingBean sameUseHourBean = new RankingBean();
	    sameUseHourBean.setCode("sameUseHour");
	    sameUseHourBean.setTitle("电站等效利用小时排行");
	    List<RankingDataBean> sameUseHourList = new ArrayList<RankingDataBean>();
	    sameUseHourBean.setDataList(sameUseHourList);
	    RankingBean eBean = new RankingBean();
	    eBean.setCode("e");
	    eBean.setTitle("电站发电量排行");
	    List<RankingDataBean> eList = new ArrayList<RankingDataBean>();
	    eBean.setDataList(eList);
	    RankingBean eScaleBean = new RankingBean();
	    eScaleBean.setCode("eScale");
	    eScaleBean.setTitle("电站发电量计划完成率排行");
	    List<RankingDataBean> eScaleList = new ArrayList<RankingDataBean>();
	    eScaleBean.setDataList(eScaleList);
	    RankingBean factoryEScaleBean = new RankingBean();
	    factoryEScaleBean.setCode("factoryEScale");
	    factoryEScaleBean.setTitle("电站厂用电率排行");
	    List<RankingDataBean> factoryEScaleList = new ArrayList<RankingDataBean>();
	    factoryEScaleBean.setDataList(factoryEScaleList);
	    RankingBean onNetEBean = new RankingBean();
	    onNetEBean.setCode("onNetE");
	    onNetEBean.setTitle("电站上网电量排行");
	    List<RankingDataBean> onNetEList = new ArrayList<RankingDataBean>();
	    onNetEBean.setDataList(onNetEList);
	    RankingBean onNetEScaleBean = new RankingBean();
	    onNetEScaleBean.setCode("onNetEScale");
	    onNetEScaleBean.setTitle("电站上网电量计划完成率排行");
	    List<RankingDataBean> onNetEScaleList = new ArrayList<RankingDataBean>();
	    onNetEScaleBean.setDataList(onNetEScaleList);
	    RankingBean compreFactoryEScaleBean = new RankingBean();
	    compreFactoryEScaleBean.setCode("compreFactoryEScale");
	    compreFactoryEScaleBean.setTitle("电站综合厂用电率排行");
	    List<RankingDataBean> compreFactoryEScaleList = new ArrayList<RankingDataBean>();
	    compreFactoryEScaleBean.setDataList(compreFactoryEScaleList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		while (ite.hasNext()) {
			Map<String, Object> item = ite.next();
			RankingDataBean efficData = new RankingDataBean();
			RankingDataBean singleMWEData = new RankingDataBean();
			RankingDataBean sameUseHourData = new RankingDataBean();
			RankingDataBean eData = new RankingDataBean();
			RankingDataBean eScaleData = new RankingDataBean();
			RankingDataBean factoryEScaleData = new RankingDataBean();
			RankingDataBean onNetEData = new RankingDataBean();
			RankingDataBean onNetEScaleData = new RankingDataBean();
			RankingDataBean compreFactoryEScaleData = new RankingDataBean();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					efficData.setName(item.get(key).toString());
					singleMWEData.setName(item.get(key).toString());
					sameUseHourData.setName(item.get(key).toString());
					eData.setName(item.get(key).toString());
					eScaleData.setName(item.get(key).toString());
					factoryEScaleData.setName(item.get(key).toString());
					onNetEData.setName(item.get(key).toString());
					onNetEScaleData.setName(item.get(key).toString());
					compreFactoryEScaleData.setName(item.get(key).toString());
				} else if (key.equals("EFFICE")) {
					if (item.get(key) != null) {
						efficData.setValue(new BigDecimal(df.format(item.get(key))));	
					} else {
						efficData.setValue(new BigDecimal(0));	
					}
				}  else if (key.equals("SINGLEMWE")) {
					if (item.get(key) != null) {
						singleMWEData.setValue(new BigDecimal(df.format(item.get(key))));	
					} else {
						singleMWEData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("SAMEUSEHOUR")) {
					if (item.get(key) != null) {
						sameUseHourData.setValue(new BigDecimal(df.format(item.get(key))));	
					} else {
						sameUseHourData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						eData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						eData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ESCALE")) {
					if (item.get(key) != null) {
						eScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						eScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("FACTORYESCALE")) {
					if (item.get(key) != null) {
						factoryEScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						factoryEScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item.get(key) != null) {
						onNetEData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						onNetEData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETESCALE")) {
					if (item.get(key) != null) {
						onNetEScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						onNetEScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("COMPREFACTORYESCALE")) {
					if (item.get(key) != null) {
						compreFactoryEScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						compreFactoryEScaleData.setValue(new BigDecimal(0));	
					}
				}
			}
			efficEBean.getDataList().add(efficData);
			singleMWEBean.getDataList().add(singleMWEData);
			sameUseHourBean.getDataList().add(sameUseHourData);
			eBean.getDataList().add(eData);
			eScaleBean.getDataList().add(eScaleData);
			factoryEScaleBean.getDataList().add(factoryEScaleData);
			onNetEBean.getDataList().add(onNetEData);
			onNetEScaleBean.getDataList().add(onNetEScaleData);
			compreFactoryEScaleBean.getDataList().add(compreFactoryEScaleData);
		}
		if (!efficEBean.getDataList().isEmpty()) {
			Collections.sort(efficEBean.getDataList());	
		}
		if (!singleMWEBean.getDataList().isEmpty()) {
			Collections.sort(singleMWEBean.getDataList());	
		}
		if (!sameUseHourBean.getDataList().isEmpty()) {
			Collections.sort(sameUseHourBean.getDataList());	
		}
		if (!eBean.getDataList().isEmpty()) {
			Collections.sort(eBean.getDataList());	
		}
		if (!eScaleBean.getDataList().isEmpty()) {
			Collections.sort(eScaleBean.getDataList());	
		}
		if (!factoryEScaleBean.getDataList().isEmpty()) {
			Collections.sort(factoryEScaleBean.getDataList());	
		}
		if (!onNetEBean.getDataList().isEmpty()) {
			Collections.sort(onNetEBean.getDataList());	
		}
		if (!onNetEScaleBean.getDataList().isEmpty()) {
			Collections.sort(onNetEScaleBean.getDataList());	
		}
		if (!compreFactoryEScaleBean.getDataList().isEmpty()) {
			Collections.sort(compreFactoryEScaleBean.getDataList());	
		}
		result.add(efficEBean);
		result.add(singleMWEBean);
		result.add(sameUseHourBean);
		result.add(eBean);
		result.add(eScaleBean);
		result.add(factoryEScaleBean);
		result.add(onNetEBean);
		result.add(onNetEScaleBean);
		result.add(compreFactoryEScaleBean);
		return result;
	}

	@Override
	public List<RankingBean> getBranchStationRankingMonthData(Long id,
			Long year, Long month) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getBranchStationRankingMonthData(id, year,month);  // 获取查询结果
		List<RankingBean> result = new ArrayList<RankingBean>();
	    RankingBean efficEBean = new RankingBean();
	    efficEBean.setCode("efficE");
	    efficEBean.setTitle("电站发电效率排行");
	    List<RankingDataBean> efficEList = new ArrayList<RankingDataBean>();
	    efficEBean.setDataList(efficEList);
	    RankingBean singleMWEBean = new RankingBean();
	    singleMWEBean.setCode("singleMWE");
	    singleMWEBean.setTitle("电站单兆瓦发电量排行");
	    List<RankingDataBean> singleMWEList = new ArrayList<RankingDataBean>();
	    singleMWEBean.setDataList(singleMWEList);
	    RankingBean sameUseHourBean = new RankingBean();
	    sameUseHourBean.setCode("sameUseHour");
	    sameUseHourBean.setTitle("电站等效利用小时排行");
	    List<RankingDataBean> sameUseHourList = new ArrayList<RankingDataBean>();
	    sameUseHourBean.setDataList(sameUseHourList);
	    RankingBean eBean = new RankingBean();
	    eBean.setCode("e");
	    eBean.setTitle("电站发电量排行");
	    List<RankingDataBean> eList = new ArrayList<RankingDataBean>();
	    eBean.setDataList(eList);
	    RankingBean eScaleBean = new RankingBean();
	    eScaleBean.setCode("eScale");
	    eScaleBean.setTitle("电站发电量计划完成率排行");
	    List<RankingDataBean> eScaleList = new ArrayList<RankingDataBean>();
	    eScaleBean.setDataList(eScaleList);
	    RankingBean factoryEScaleBean = new RankingBean();
	    factoryEScaleBean.setCode("factoryEScale");
	    factoryEScaleBean.setTitle("电站厂用电率排行");
	    List<RankingDataBean> factoryEScaleList = new ArrayList<RankingDataBean>();
	    factoryEScaleBean.setDataList(factoryEScaleList);
	    RankingBean onNetEBean = new RankingBean();
	    onNetEBean.setCode("onNetE");
	    onNetEBean.setTitle("电站上网电量排行");
	    List<RankingDataBean> onNetEList = new ArrayList<RankingDataBean>();
	    onNetEBean.setDataList(onNetEList);
	    RankingBean onNetEScaleBean = new RankingBean();
	    onNetEScaleBean.setCode("onNetEScale");
	    onNetEScaleBean.setTitle("电站上网电量计划完成率排行");
	    List<RankingDataBean> onNetEScaleList = new ArrayList<RankingDataBean>();
	    onNetEScaleBean.setDataList(onNetEScaleList);
	    RankingBean compreFactoryEScaleBean = new RankingBean();
	    compreFactoryEScaleBean.setCode("compreFactoryEScale");
	    compreFactoryEScaleBean.setTitle("电站综合厂用电率排行");
	    List<RankingDataBean> compreFactoryEScaleList = new ArrayList<RankingDataBean>();
	    compreFactoryEScaleBean.setDataList(compreFactoryEScaleList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		while (ite.hasNext()) {
			Map<String, Object> item = ite.next();
			RankingDataBean efficData = new RankingDataBean();
			RankingDataBean singleMWEData = new RankingDataBean();
			RankingDataBean sameUseHourData = new RankingDataBean();
			RankingDataBean eData = new RankingDataBean();
			RankingDataBean eScaleData = new RankingDataBean();
			RankingDataBean factoryEScaleData = new RankingDataBean();
			RankingDataBean onNetEData = new RankingDataBean();
			RankingDataBean onNetEScaleData = new RankingDataBean();
			RankingDataBean compreFactoryEScaleData = new RankingDataBean();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					efficData.setName(item.get(key).toString());
					singleMWEData.setName(item.get(key).toString());
					sameUseHourData.setName(item.get(key).toString());
					eData.setName(item.get(key).toString());
					eScaleData.setName(item.get(key).toString());
					factoryEScaleData.setName(item.get(key).toString());
					onNetEData.setName(item.get(key).toString());
					onNetEScaleData.setName(item.get(key).toString());
					compreFactoryEScaleData.setName(item.get(key).toString());
				} else if (key.equals("EFFICE")) {
					if (item.get(key) != null) {
						efficData.setValue(new BigDecimal(df.format(item.get(key))));	
					} else {
						efficData.setValue(new BigDecimal(0));	
					}
				}  else if (key.equals("SINGLEMWE")) {
					if (item.get(key) != null) {
						singleMWEData.setValue(new BigDecimal(df.format(item.get(key))));	
					} else {
						singleMWEData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("SAMEUSEHOUR")) {
					if (item.get(key) != null) {
						sameUseHourData.setValue(new BigDecimal(df.format(item.get(key))));	
					} else {
						sameUseHourData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						eData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						eData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ESCALE")) {
					if (item.get(key) != null) {
						eScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						eScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("FACTORYESCALE")) {
					if (item.get(key) != null) {
						factoryEScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						factoryEScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETE")) {
					if (item.get(key) != null) {
						onNetEData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						onNetEData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("ONNETESCALE")) {
					if (item.get(key) != null) {
						onNetEScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						onNetEScaleData.setValue(new BigDecimal(0));	
					}
				} else if (key.equals("COMPREFACTORYESCALE")) {
					if (item.get(key) != null) {
						compreFactoryEScaleData.setValue(new BigDecimal(df.format(item.get(key))));
					} else {
						compreFactoryEScaleData.setValue(new BigDecimal(0));	
					}
				}
			}
			efficEBean.getDataList().add(efficData);
			singleMWEBean.getDataList().add(singleMWEData);
			sameUseHourBean.getDataList().add(sameUseHourData);
			eBean.getDataList().add(eData);
			eScaleBean.getDataList().add(eScaleData);
			factoryEScaleBean.getDataList().add(factoryEScaleData);
			onNetEBean.getDataList().add(onNetEData);
			onNetEScaleBean.getDataList().add(onNetEScaleData);
			compreFactoryEScaleBean.getDataList().add(compreFactoryEScaleData);
		}
		if (!efficEBean.getDataList().isEmpty()) {
			Collections.sort(efficEBean.getDataList());	
		}
		if (!singleMWEBean.getDataList().isEmpty()) {
			Collections.sort(singleMWEBean.getDataList());	
		}
		if (!sameUseHourBean.getDataList().isEmpty()) {
			Collections.sort(sameUseHourBean.getDataList());	
		}
		if (!eBean.getDataList().isEmpty()) {
			Collections.sort(eBean.getDataList());	
		}
		if (!eScaleBean.getDataList().isEmpty()) {
			Collections.sort(eScaleBean.getDataList());	
		}
		if (!factoryEScaleBean.getDataList().isEmpty()) {
			Collections.sort(factoryEScaleBean.getDataList());	
		}
		if (!onNetEBean.getDataList().isEmpty()) {
			Collections.sort(onNetEBean.getDataList());	
		}
		if (!onNetEScaleBean.getDataList().isEmpty()) {
			Collections.sort(onNetEScaleBean.getDataList());	
		}
		if (!compreFactoryEScaleBean.getDataList().isEmpty()) {
			Collections.sort(compreFactoryEScaleBean.getDataList());	
		}
		result.add(efficEBean);
		result.add(singleMWEBean);
		result.add(sameUseHourBean);
		result.add(eBean);
		result.add(eScaleBean);
		result.add(factoryEScaleBean);
		result.add(onNetEBean);
		result.add(onNetEScaleBean);
		result.add(compreFactoryEScaleBean);
		return result;
	}

	@Override
	public GroupEBean getBranchOnNetEYearData(Long year) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getBranchOnNetEYearData(year);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean onNetEBean = new DataBean();  // 计划发电量Bean
		onNetEBean.setDataType("column");
		onNetEBean.setyAxis(0);
		onNetEBean.setDataName("上网电量");
		DataBean eBean = new DataBean();  // 发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("发电量");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					String name = item.get(key).toString(); 
					xAxisCategories.add(name);
				} else if (key.equals("ONNETE")) {
					if (item.get(key) != null) {
						BigDecimal onNetE = new BigDecimal(df.format(item.get(key))); 
						onNetEBean.setyAxis(0);
						onNetEBean.addData(onNetE);	
					} else {
						onNetEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						eBean.setyAxis(0);
						eBean.addData(e);	
					} else {
						eBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(onNetEBean);
		dataBeanList.add(eBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}

	@Override
	public GroupEBean getBranchOnNetEMonthData(Long year, Long month) {
		List<Map<String, Object>> queryList = groupContrastJdbc.getBranchOnNetEMonthData(year,month);  // 获取查询结果
		GroupEBean result = new GroupEBean();  // 图表Bean
		List<String> xAxisCategories = new ArrayList<String>();  // x刻度列表
		List<DataBean> dataBeanList = new ArrayList<DataBean>();  // 数据Bean列表
		DataBean onNetEBean = new DataBean();  // 计划发电量Bean
		onNetEBean.setDataType("column");
		onNetEBean.setyAxis(0);
		onNetEBean.setDataName("上网电量");
		DataBean eBean = new DataBean();  // 发电量Bean
		eBean.setDataType("column");
		eBean.setyAxis(0);
		eBean.setDataName("发电量");
		result.setDataBeanList(dataBeanList);
		DecimalFormat df = new DecimalFormat("0.00");
		Iterator<Map<String, Object>> ite = queryList.iterator();
		while (ite.hasNext()) { 
			Map<String, Object> item = ite.next();
			for (String key : item.keySet()) {
				if (key.equals("NAME")) {
					String name = item.get(key).toString(); 
					xAxisCategories.add(name);
				} else if (key.equals("ONNETE")) {
					if (item.get(key) != null) {
						BigDecimal onNetE = new BigDecimal(df.format(item.get(key))); 
						onNetEBean.setyAxis(0);
						onNetEBean.addData(onNetE);	
					} else {
						onNetEBean.addData(new BigDecimal(0));	
					}
				} else if (key.equals("E")) {
					if (item.get(key) != null) {
						BigDecimal e = new BigDecimal(df.format(item.get(key))); 
						eBean.setyAxis(0);
						eBean.addData(e);	
					} else {
						eBean.addData(new BigDecimal(0));	
					}
				}
			}
		}
		dataBeanList.add(onNetEBean);
		dataBeanList.add(eBean);
		result.setxAxisCateGories(xAxisCategories);
		result.setDataBeanList(dataBeanList);
		result.setYear(year.toString());
		return result;
	}

}
