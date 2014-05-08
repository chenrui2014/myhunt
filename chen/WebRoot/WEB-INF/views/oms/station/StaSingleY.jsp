<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电站间年单兆瓦发电量分析</title>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script src="${ctx}/huntoms/static/js/My97DatePicker/WdatePicker.js"></script>
	<script src="${ctx}/huntoms/static/js/common/comm.js"></script>
	<link href="${ctx}/huntoms/static/css/chart.css"  rel="stylesheet"  type="text/css" />
	<link href="${ctx}/huntoms/static/css/table.css"  rel="stylesheet"  type="text/css" />
<script type="text/javascript">
	//页面加载
	function onLoad() {
		getSearchCondition();
		var Year_ = '${Year_}';
		if (Year_) {
			getData(Year_);
		}
	}
	//获取查询条件
	function getSearchCondition() {
		$
				.getJSON(
						'http://localhost:8080/huntoms/station/GetBetweenStaConditionData',
						function(data) {
							var Year_ = [];
							Year_ = data.Year_;
							$(Year_).each(function(i, item) {
												var option = document.createElement('OPTION');
												option.value = item;
												option.text = item;
												document.getElementById('Year_').options.add(option);
											});
						});
	}
	function getData(Year_) {
		$(function() {
			var xset = [];
			var yset = []; 
			var y1set = [];
			var y2set = [];
			$.getJSON(
					'http://localhost:8080/huntoms/station/displayVSYE?Year_='+Year_
							, function(data) {
						var stationy = data.list;
						$.each(data.x, function(i, item) {
							//xset[i]=eval(item);
							//var jsonObj=eval(item);
							//xset.push(jsonObj);
							//alert(item);
							xset.push(item);
						});
						//alert(xset);
						$.each(data.y1, function(i, item) {
							//var jsonObj = eval(item);
							//yset.push(parseFloat(jsonObj));
							y1set.push(item);
						});
						$.each(data.y2, function(i, item) {
							//var jsonObj = eval(item);
							//yset.push(parseFloat(jsonObj));
							y2set.push(item);
						});
						for ( var i = 0; i < xset.length; i++) {
							yset.push(y2set[i] / y1set[i]);
						}
						showBar(xset, yset);
						createTable(stationy);
					});
		});
	}
	function showBar(xset, yset) {
		$('#container').highcharts({
			chart : {
				zoomType : 'xy',
				backgroundColor : '#F1FBFB'
			},
			lang : {
				printChart : '导出',
				downloadJPEG : '导出JPEG图片',
				downloadPDF : '导出DF文档',
				downloadPNG : '导出PNG图片',
				downloadSVG : '导出SVG矢量图'
			},
			title : {
				text : '电站间年单兆瓦发电量比对分析'//title_name//
			},

			xAxis : [ {
				categories : xset
			} ],
			yAxis : [ { // Primary yAxis
				labels : {
					format : '{value}度',
					style : {
						color : '#89A54E'
					}
				},
				title : {
					text : '单兆瓦发电量',
					style : {
						color : '#89A54E'
					}
				}
			} ],
			tooltip : {
				shared : false
			},
			legend : {
				layout : 'vertical',
				align : 'left',
				x : 10,
				verticalAlign : 'top',
				y : 5,
				floating : true,
				backgroundColor : '#FFFFFF'
			},
			series : [ {
				name : '单兆瓦发电量',
				color : '#4572A7',
				type : 'column',
				yAxis : 0,
				data : yset,
				tooltip : {
					valueSuffix : ' 度'
				}

			} ]
		});
	}
	function createTable(station) {
		$('#table').empty();
		var th = "<tr>"
				+ "<th>电站</th><th>理论发电量(万度)</th><th>发电量(万度)</th><th>发电效率(%)</th><th>单兆瓦发电量(万度)</th><th>等效利用小时(h)</th>"
				+ "</tr>";
		$('#table').append(th);

		var len = station.length;
		for ( var i = 0; i < len; i++) {
			$('#table').append(
					"<tr>" + "<td>" + station[i].organName + "</td>" + "<td>"
							+ val(station[i].theoryE / 10000) + "</td>"
							+ "<td>" + val(station[i].e / 10000) + "</td>"
							+ "<td>"
							+ val(station[i].e / station[i].theoryE * 100)
							+ "</td>" + "<td>"
							+ val(station[i].e / station[i].capacity / 10000)
							+ "</td>" + "<td>"
							+ val(station[i].e / station[i].capacity / 1000)
							+ "</td>" + "</tr>");
		}
	}
	function val(d) {
		if (d == null || d == "null"||!isFinite(d)) {
			d = 0;
		} else {
			d = d;
		}
		return d.toFixed(2);
	}
	//getData(2013);
	onLoad();
</script>
</head>
<body>
	<script src="${ctx}/huntoms/static/js/highcharts/highcharts.js"></script>
	<script src="${ctx}/huntoms/static/js/highcharts//modules/exporting.js"></script>

	<div id="selectParam" class="search">
		<select id="Year_"></select>
		<button onclick="getData(Year_.value)">查询</button>
	</div>
	<div id="container"
		style="min-width: 80px; height:360px; margin: 0 auto"></div>
	<div style="min-width: 310px; height: 400px; margin: 0 auto">
		<table border="1" id="table">
		</table>
	</div>
</body>
</html>