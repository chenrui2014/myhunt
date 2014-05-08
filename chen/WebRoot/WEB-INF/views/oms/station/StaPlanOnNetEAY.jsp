<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电站历年发电效率</title>
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
		var organ_id = '${organ_id}';
		if (organ_id) {
			getData(organ_id);
		}
	}
	//获取查询条件
	function getSearchCondition() {
		$.getJSON(
				'http://localhost:8080/huntoms/station/GetStaConditionDataAY',
				function(data) {
					var organName = [];
					organName = data.orgName;
					$(organName).each(
							function(i, item) {
								var option = document.createElement('OPTION');
								option.value = item;
								option.text = item;
								document.getElementById('organ_id').options
										.add(option);
							});
				});
	}
	/*返回数据*/
	function getData(organ_id) {
		$(function() {

			var xset = [];
			var y1_set = [];
			var y2_set = [];
			var y3_set = [];
			$.getJSON(
					'http://localhost:8080/huntoms/station/displayPlanOnNetEAY?organ_id='
							+ organ_id, function(data) {
						var titleName = data.orgName;
						//alert(titleName);
						var stationy = data.list;
						$.each(data.x, function(i, item) {
							if (item == "null") {//查询数据中有空值，图表将无法显示
								xset.push(null);
							} else {
								xset.push(item);
							}
						});
						$.each(data.y1, function(i, item) {
							if (item == "null") {//查询数据中有空值，图表将无法显示
								y1_set.push(0);
							} else {
								y1_set.push(item / 10000);
							}
						});
						$.each(data.y2, function(i, item) {
							if (item == "null") {//查询数据中有空值，图表将无法显示
								y2_set.push(0);
							} else {
								y2_set.push(item / 10000);
							}
						});
						for ( var i = 0; i < xset.length; i++) {
							if (y1_set[i] / y2_set[i]) {
								y3_set.push(y1_set[i] / y2_set[i] * 100);
							} else {
								y3_set.push(0);
							}
						}
						showChart(titleName, xset, y1_set, y2_set, y3_set);
						createTable(stationy);
					});
		});
	}
	function showChart(titleName, xset, yset1, yset2, yset3) {
		$(function() {
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
					text : titleName + '电站历年上网计划执行分析'
				},
				xAxis : [ {
					categories : xset
				//['Jan', 'Feb', 'Mar', 'Apr']
				} ],
				yAxis : [ { // Primary yAxis
					labels : {
						format : '{value}万度',
						style : {
							color : '#89A54E'
						}
					},
					title : {
						text : '电量(万度)',
						style : {
							color : '#89A54E'
						}
					}
				}, { // Secondary yAxis
					title : {
						text : '计划完成率(%)',
						style : {
							color : '#4572A7'
						}
					},
					labels : {
						format : '{value} %',
						style : {
							color : '#4572A7'
						}
					},
					opposite : true
				} ],
				tooltip : {
					shared : true
				},
				/* legend : {
					layout : 'vertical',
					align : 'left',
					x : 100,
					verticalAlign : 'top',
					y : 5,
					floating : true,
					backgroundColor : '#FFFFFF'
				}, */
				series : [ {
					name : '计划上网电量',
					color : '#4572A7',
					type : 'column',
					yAxis : 0,
					data : yset2,
					tooltip : {
						valueSuffix : ' 万度'
					}

				}, {
					name : '实际上网电量',
					color : '#4322B7',
					type : 'column',
					yAxis : 0,
					data : yset1,
					tooltip : {
						valueSuffix : ' 万度'
					}

				}, {
					name : '计划完成率',
					color : '#89A54E',
					type : 'spline',
					yAxis : 1,
					data : yset3,
					tooltip : {
						valueSuffix : '%'
					}
				} ]
			});
		});
	}
	function createTable(station) {
		$('#table').empty();
		var th = "<tr>"
				+ "<th>年份</th><th>计划上网电量(万度)</th><th>上网电量(万度)</th><th>上网计划完成率(%)</th>"
				+ "</tr>";
		$('#table').append(th);

		var len = station.length;
		for ( var i = 0; i < len; i++) {
			$('#table').append(
					"<tr>" + "<td>" + station[i].year_ + "</td>" + "<td>"
							+ val(station[i].planOnE) + "</td>" + "<td>"
							+ val(station[i].onNetE) + "</td>" + "<td>"
							+ val(station[i].onNetE / station[i].planOnE * 100)
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

	onLoad();
</script>
</head>
<body>
	<script src="${ctx}/huntoms/static/js/highcharts/highcharts.js"></script>
	<script src="${ctx}/huntoms/static/js/highcharts/modules/exporting.js"></script>

	<div id="selectParam" class="search">
		<select id="organ_id"></select>
		<button onclick="getData(organ_id.value)">查询</button>
	</div>
	<div id="container"
		style="min-width: 80px; height:360px; margin: 0 auto"></div>
	<div style="min-width: 310px; height: 400px; margin: 0 auto">
		<table border="1" id="table">
		</table>
	</div>
</body>
</html>