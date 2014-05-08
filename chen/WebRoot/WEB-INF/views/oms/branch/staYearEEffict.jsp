<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>电站年发电效率对比分析</title>
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	</head>
	<body>
<script src="http://code.highcharts.com/highcharts.js"></script>
<script src="http://code.highcharts.com/modules/exporting.js"></script>
<script type="text/javascript">
function byear(){
	this.name = "";
	this.data = [];
}



$(function () {
	
	$.getJSON('http://localhost:8080/huntoms/branch/displayStaYearEffict',function(data){  // 获取JSON数据
		
		var xAxis_ =[];
		var yAxis_ =[];
		xAxis_ = data.x;
		yAxis_ = data.y;
 		showHighCharts(xAxis_,yAxis_);
	  	// createTable(branchy);
	  });
});


function showHighCharts(xAxis_,yAxis_){

	 $('#container').highcharts({
	        chart: {
	            type: 'column'
	        },
	        title: {
	            text: '电站间年发电效率对比分析'
	        },
	        xAxis: {
	            categories: xAxis_
	        },
	        yAxis: {
	        	min: 0,
				lineWidth: 1.0,
				lineColor: '#CCD4D4',
				gridLineColor: '#CCD4D4',
				gridLineDashStyle: 'dash',
                title: {
                    text: '电量(kWh)'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
	        },
	        lang: {
				printChart: '导出',
			    downloadJPEG: '导出JPEG图片',
			    downloadPDF: '导出DF文档',
				downloadPNG: '导出PNG图片',
				downloadSVG: '导出SVG矢量图'
			},
	        plotOptions: {
	            column: {
	                pointPadding: 0.2,
	                borderWidth: 0
	            }
	        },
	        series: eval(yAxis_)
	    });
}

	function createTable(branch) {
		var th="<tr>"
				+ "<th>年份</th><th>装机容量</th><th>理论发电量</th><th>计划发电量</th><th>实际发电量</th><th>计划上网电量</th><th>上网电量</th><th>弃光电量</th>"
				+"</tr>";
		$('#table').append(th);
		
		var len = branch.length;
		for(var i=0;i<len;i++){
			$('#table').append(		"<tr>"+
					"<td>"+branch[i].year+"</td>"
					+"</tr>");
		}
	}

</script>

    

<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>

	<div  style="min-width: 310px; height: 400px; margin: 0 auto">
		<table border="1"  id="table">

					
		</table>
	</div>

	</body>
</html>