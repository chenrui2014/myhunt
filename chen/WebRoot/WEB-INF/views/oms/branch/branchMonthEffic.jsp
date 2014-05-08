<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
		<title>区域历年发电效率统计分析</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script type="text/javascript" src="${ctx}/huntoms/static/js/jquery/jquery.js"></script>
		<script src="${ctx}/huntoms/static/js/highcharts/highcharts.js"></script>
        <script src="${ctx}/huntoms/static/js/highcharts/modules/exporting.js"></script>
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


function showHighCharts(orgName_,xAxis_,ylAxis_,yrAxis_){
	 $('#container').highcharts({
         chart: {
             zoomType: 'xy',
             backgroundColor: '#F1FBFB'
         },
         title: {
             text: orgName_+'年发电效率'
         } ,
	     credits: {
	             enabled: false
	         },
		 lang: {
					printChart: '导出',
				    downloadJPEG: '导出JPEG图片',
				    downloadPDF: '导出DF文档',
					downloadPNG: '导出PNG图片',
					downloadSVG: '导出SVG矢量图'
		 },
         xAxis: [{
        	 title :{text :'月份'},
        	 categories:xAxis_
        	 }] ,
         yAxis: [{ // Primary yAxis
             labels: {
                 formatter: function() {
                     return this.value +'%';
                 },
                 style: {
                     color: '#89A54E'
                 }
             },
             title: {
                 text: '发电效率',
                 style: {
                     color: '#89A54E'
                 }
             },
             opposite: true
 
         }, { // Secondary yAxis
             gridLineWidth: 0,
             title: {
                 text: '单位兆瓦发电量（万度）',
                 style: {
                     color: '#4572A7'
                 }
             },
             labels: {
                 formatter: function() {
                     return this.value ;
                 },
                 style: {
                     color: '#4572A7'
                 }
             }
 
         }],
         tooltip: {
             shared: true
         },
         legend: {
    			
         },
         series: [{
             name: '单位兆瓦发电量',
            // color: '#4572A7',
             type: 'column',
             yAxis: 1,
             data: ylAxis_,
             tooltip: {
                 valueSuffix: ' 万度'
             }
 
         }, 
         {
             name: '发电效率',
           //  color: '#89A54E',
             type: 'spline',
             data: yrAxis_,
             tooltip: {
                 valueSuffix: '%'
             }
         }]
     });

}

	function createTable(branch) {
		$('#table').empty();
		var th="<tr>"
				+ "<th>年份</th><th>理论发电量(万度)</th><th>发电量(万度)</th><th>发电效率(%)</th><th>单位瓦发电量(万度)</th><th>等效利用时间(h)</th>"
				+"</tr>";
		$('#table').append(th);
		var len = branch.length;
		for(var i=0;i<len;i++){
			$('#table').append(		"<tr>"+
					"<td>"+branch[i].year+"-"+branch[i].bmonth+"</td>"+"<td>"+branch[i].theorye+"</td>"+"<td>"+branch[i].e+"</td>"+"<td>"+branch[i].effict+"%</td>"+"<td>"+branch[i].singlemwe+"</td>"+"<td>"+branch[i].eusehours+"</td>"
					+"</tr>");
		}
	}

	
	function query(orgid,para){
		
		var  url ='<%=request.getContextPath()%>/branch/displayBranchMonthEffic?orgid='+orgid+'&parayear='+para;
		$.getJSON(url,function(data){  // 获取JSON数据
			if(!$.isEmptyObject(data)){
				var orgName;
				var xAxis_ =[];
				var ylAxis_ = new Array();
				var yrAxis_ = new Array();
				orgName = data.orgName;
				xAxis_ = data.x;
				ylAxis_ = data.ly;
				yrAxis_ = data.ry;
			    showHighCharts(orgName,xAxis_,ylAxis_,yrAxis_);
			     var branchy = data.table;
			  	 createTable(branchy);
			}else{
				nodataHightChart();
			}
		  });
	}
</script>
</head>
<body>
<div id="searchContainer" class="search">
            <label>组织</label>
            <select id ="orgId">
                		<option value="2">德令哈分公司</option>
                		<option value="3">格尔木分公司</option>
                		<option value="4">海北分公司</option>
                		<option value="5">海南分公司</option>
                </select>
                <label>时间</label><input id="para" class="Wdate" style="margin-top:8px;" type="text" size="12" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy'})"/>
                <button onclick="query($('#orgId').val(),$('#para').val())">查询</button>  
     </div>
	<div id="container" style="width: 98%; height:360px; margin:auto"></div>	
	 <div id="tableContainer"  class="table" style="margin-top:20px; margin-left:12px">
	 	<table  id="table">
										
		</table>
</div>
</body>
</html>