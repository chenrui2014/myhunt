package com.huntech.huntoms.oms.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/abnormal")
public class SeriesController {
	/**
	 * 组串异常预判结果
	 */
	@RequestMapping(value ="/seriesResPreAbnormal", method = RequestMethod.GET)
	public String seriesRes() throws Exception {
		return "oms/preabnormal/SeriesResPreAbnormal";
	}
	/**
	 * 组串异常预判结果-曲线
	 */
	@RequestMapping(value ="/seriesPreAbnormalCurve", method = RequestMethod.GET)
	public String seriesCurve() throws Exception {
		return "oms/preabnormal/SeriesPreAbnormalCurve";
	}	
	/**
	 * 组串异常预判查询
	 */
	@RequestMapping(value ="/seriesPreAbnormalQuery", method = RequestMethod.GET)
	public String seriesQuery() throws Exception {
		return "oms/preabnormal/SeriesPreAbnormalQuery";
	}	
	/**
	 * 汇流箱异常预判结果
	 */
	@RequestMapping(value ="/boxResPreAbnormal", method = RequestMethod.GET)
	public String boxRes() throws Exception {
		return "oms/preabnormal/BoxResPreAbnormal";
	}
	/**
	 * 汇流箱异常预判结果-曲线
	 */
	@RequestMapping(value ="/boxPreAbnormalCurve", method = RequestMethod.GET)
	public String boxCurve() throws Exception {
		return "oms/preabnormal/BoxPreAbnormalCurve";
	}	
	/**
	 * 汇流箱异常预判结果查询
	 */
	@RequestMapping(value ="/boxPreAbnormalQuery", method = RequestMethod.GET)
	public String boxQuery() throws Exception {
		return "oms/preabnormal/BoxPreAbnormalQuery";
	}	
	
	
	/**
	 *   逆变器异常预判结果
	 */
	@RequestMapping(value ="/inverterPreAbnResult")
	public String inverterPreAbnResult() throws Exception {
		
		return "oms/preabnormal/inverterPreAbnResult";
		
	}	
	
	/**
	 *   逆变器异常预判分析-逆变器曲线
	 */
	@RequestMapping(value ="/inverterPreAbnLine")
	public String inverterPreAbnLine() throws Exception {
		
		return "oms/preabnormal/inverterPreAbnLine";
		
	}
	
	/**
	 *   逆变器异常预判分析查询
	 */
	@RequestMapping(value ="/inverterPreAbnQuery")
	public String inverterPreAbnQuery() throws Exception {
		
		return "oms/preabnormal/inverterPreAbnQuery";
		
	}
	
	/**
	 *   方阵异常预判
	 */
	@RequestMapping(value ="/arrayPreAbnResult")
	public String arrayPreAbnResult() throws Exception {
		
		return "oms/preabnormal/arrayPreAbnResult";
		
	}
	
	/**
	 *   箱变异常预判
	 */
	@RequestMapping(value ="/jboxPreAbnResult")
	public String jboxPreAbnResult() throws Exception {
		
		return "oms/preabnormal/jboxPreAbnResult";
		
	}
	
	/**
	 *   主变异常预判
	 */
	@RequestMapping(value ="/mainPreAbnResult")
	public String mainPreAbnResult() throws Exception {
		
		return "oms/preabnormal/mainPreAbnResult";
		
	}
	
	/**
	 *   保护异常预判
	 */
	@RequestMapping(value ="/protectPreAbnResult")
	public String protectPreAbnResult() throws Exception {
		
		return "oms/preabnormal/protectPreAbnResult";
	}
}
