<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<head>
		<title>区域电量月统计分析</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
	<script src="${ctx}/huntoms/static/js/My97DatePicker/WdatePicker.js"></script>
	<script src="${ctx}/huntoms/static/js/common/comm.js"></script>
	<link href="${ctx}/huntoms/static/css/chart.css"  rel="stylesheet"  type="text/css" />
	<link href="${ctx}/huntoms/static/css/table.css"  rel="stylesheet"  type="text/css" />
<script type="text/javascript">
function commObject(name_,data_){
	this.name = name_;
	this.data = data_;
}

$(function () {

});


function showHighCharts(orgName_,xAxis_,yAxis_){
	 $('#container').highcharts({
		 chart: {
	            type: 'line',
	            backgroundColor: '#F1FBFB'
	        },
         title: {
             text: orgName_+'月电量分析',
             x: -20 //center
         }, credits: {
	             enabled: false
	     },
         xAxis: {
        	 title :{
        		text : '日期' 
        	 },
             categories: xAxis_
         },
         yAxis: {
        	 min: 0,
				lineWidth: 1.0,
				lineColor: '#CCD4D4',
				gridLineColor: '#CCD4D4',
				gridLineDashStyle: 'dash',
             title: {
                 text: '电量(万度)'
             },
             plotLines: [{
                 value: 0,
                 width: 1,
                 color: '#808080'
             }]
             ,
             labels: {
                 formatter: function() {
                     return this.value ;
                 },
                 style: {
                     color: '#4572A7'
                 }
             }
         },
         tooltip: {
        	   valueSuffix: '万度',
               verticalAlign: 'middle'
         },
         lang: {
				printChart: '导出',
			    downloadJPEG: '导出JPEG图片',
			    downloadPDF: '导出DF文档',
				downloadPNG: '导出PNG图片',
				downloadSVG: '导出SVG矢量图'
			},
         legend: {

         },
         series: yAxis_
     });
}

	function createTable(branch) {
		$('#table').empty();
		var th="<tr>"
				+ "<th>日期</th><th>发电量(万度)</th><th>上网电量(万度)</th><th>弃光电量(万度)</th>"
				+"</tr>";
		$('#table').append(th);
		
		var len = branch.length;
		for(var i=0;i<len;i++){
			$('#table').append("<tr>"+
					"<td>"+branch[i].year+"-"+branch[i].month+"-"+branch[i].day+"</td>"+"<td>"+branch[i].e+"</td>"+"<td>"+branch[i].onnete+"</td>"+"<td>"+branch[i].losse+"</td>"
					+"</tr>");
		}
	}
	function query(orgid,para){
		var temp = para.split('-'); 
		var url = '<%=request.getContextPath()%>/branch/displayBranchDayE?orgid='+orgid+'&parayear='+temp[0]+'&paramonth='+temp[1];
		$.getJSON(url,function(data){  // 获取JSON数据
			if(!$.isEmptyObject(data)){
				var orgName;
				var xAxis_ =[];
				var yAxis_ = new Array();
				orgName = data.orgName;
				xAxis_ = data.day;
				yAxis_ = data.list;
			   showHighCharts(orgName,xAxis_,yAxis_);
			   var branchy = data.table;
			  	createTable(branchy)
			}else{
				nodataHightChart();
			}
		  });
	}
</script>
<body>
<div id="searchContainer" class="search">
            <label>组织</label>
            <select id ="orgId">
                		<option value="2">德令哈分公司</option>
                		<option value="3">格尔木分公司</option>
                		<option value="4">海北分公司</option>
                		<option value="5">海南分公司</option>
                </select>
             <label>时间</label><input id="para" class="Wdate" style="margin-top:8px;" type="text" size="12" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-M'})"/>
            <button onclick="query($('#orgId').val(),$('#para').val())">查询</button>  
     </div>
	<div id="container" style="width: 98%; height:360px; margin:auto"></div>	
	 <div id="tableContainer"  class="table" style="margin-top:20px; margin-left:12px">
	 	<table  id="table">
										
		</table>
	 </div>	
</body>
</html>