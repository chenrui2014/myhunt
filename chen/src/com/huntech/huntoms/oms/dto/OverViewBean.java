package com.huntech.huntoms.oms.dto;

import java.util.List;

public class OverViewBean {
	
	private OraganDataBean organData;
	
	private List<ChartBean> chartData;
	
	private List<RankingBean> rankingData;

	public OraganDataBean getOrganData() {
		return organData;
	}

	public void setOrganData(OraganDataBean organData) {
		this.organData = organData;
	}

	public List<ChartBean> getChartData() {
		return chartData;
	}

	public void setChartData(List<ChartBean> chartData) {
		this.chartData = chartData;
	}

	public List<RankingBean> getRankingData() {
		return rankingData;
	}

	public void setRankingData(List<RankingBean> rankingData) {
		this.rankingData = rankingData;
	}

}
