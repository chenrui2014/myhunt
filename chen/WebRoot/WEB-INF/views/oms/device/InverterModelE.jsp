<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电站同型号逆变器发电量分析</title>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script src="${ctx}/huntoms/static/js/My97DatePicker/WdatePicker.js"></script>
	<script src="${ctx}/huntoms/static/js/common/comm.js"></script>
	<link href="${ctx}/huntoms/static/css/chart.css"  rel="stylesheet"  type="text/css" />
	<link href="${ctx}/huntoms/static/css/table.css"  rel="stylesheet"  type="text/css" />
<script type="text/javascript">
	function byear() {
		this.name = "";
		this.data = [];
	}

	var orgName;
	var xAxis_ = [];
	var rowName=[];
	$(function() {

		$.getJSON('http://localhost:8080/huntoms/device/displayInverterMondelE',
				function(data) { // 获取JSON数据
					//var stationd=data.list;
					orgName = data.orgName;
					xAxis_ = data.x;
					rowName=data.rowName;
					var len = data.y.length;
					var y = data.y;
					var yAxis_ = new Array();
					$.each(data.y, function(i, item) {
						var jsonObj = eval(item);
						var b = new byear();
						b.name = jsonObj.name;

						for ( var j = 0; j < jsonObj.data.length; j++) {

							if (jsonObj.data[j] == "null"||jsonObj.data[j]==null) {//查询数据中有空值，图表将无法显示
								b.data.push(0);
							} else {
								b.data.push(parseFloat(jsonObj.data[j]));
							}
						}

						yAxis_.push(b);

					});

					showHighCharts(orgName,xAxis_, yAxis_);
					createTable(xAxis_,rowName,data.y);
				});
	});

	function showHighCharts(orgName,xAxis_, yAxis_) {

		$('#container').highcharts({
			chart : {
				type : 'line',
				backgroundColor : '#F1FBFB'
			},
			credits:{
				enabled:false
			},
			lang : {
				printChart : '导出',
				downloadJPEG : '导出JPEG图片',
				downloadPDF : '导出DF文档',
				downloadPNG : '导出PNG图片',
				downloadSVG : '导出SVG矢量图'
			},
			title : {
				text : orgName+'同型号逆变器发电量分析'
			},
			tooltip : {
				valueSuffix : '度',
				verticalAlign : 'middle'
			},
			legend : {
				align : 'center',
				verticalAlign : 'bottom',
				borderWidth : 0,
				itemMarginTop : 10.0
			},
			xAxis : {
				categories : xAxis_
			},
			yAxis : {
				min : 0,
				lineWidth : 1.0,
				lineColor : '#CCD4D4',
				gridLineColor : '#CCD4D4',
				gridLineDashStyle : 'dash',
				title : {
					text : '发电量 (度)',
				}
			},
			tooltip : {
				valueSuffix : '度',
				verticalAlign : 'middle'
			},
			plotOptions : {
				column : {
					pointPadding : 0.2,
					borderWidth : 0
				}
			},
			series : eval(yAxis_)
		});

	}
	
	function createTable(x,rowName,dt) {
		$('#table').empty();
		var rowStr="";
		for(var i=0;i<rowName.length;i++){
			rowStr+="<th>"+rowName[i]+"发电量</th>";
		}
		var th="<tr>"
				+ "<th>日期</th>"+rowStr; 
				+"</tr>";
		$('#table').append(th);
		
		var len = x.length;
		for(var i=0;i<len;i++){
			$('#table').append(		"<tr>"+
					"<td>"+x[i]+"</td>"
					
					+"</tr>");
		}

	}
	function val(d){
		if(d==null||d=="null"||!isFinite(d)){
			d=0;
		}else{
			d=d;
		}
		return d.toFixed(2);
	}
</script>
</head>
<body>

	<script src="${ctx}/huntoms/static/js/highcharts/highcharts.js"></script>
	<script src="${ctx}/huntoms/static/js/highcharts//modules/exporting.js"></script>
	
	<div id="container"	style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	<div id="showtable"	style="min-width: 310px; height: 400px; margin: 0 auto">
		<!--
		<table border="1"  id="table">

		</table>
		-->
	</div>
	
</body>
</html>