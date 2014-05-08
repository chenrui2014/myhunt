<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电站日负荷曲线</title>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script type="text/javascript">
	/*返回数据*/
	$(function() {

		var xset = [];
		var y1_set = [];
		var y2_set = [];
		var y3_set = [];
		var y4_set = [];
		var y5_set = [];
		$.getJSON('http://localhost:8080/huntoms/station/displaySDayPCurve',
				function(data) {
					var titleName = data.orgName;
					var riqi=data.riqi;
					//alert(titleName);
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
							y1_set.push(item);
						}
					});
					$.each(data.y2, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							y2_set.push(0);
						} else {
							y2_set.push(item);
						}
					});
					$.each(data.y3, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							y3_set.push(0);
						} else {
							y3_set.push(item);
						}
					});
					$.each(data.y4, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							y4_set.push(0);
						} else {
							y4_set.push(item);
						}
					});
					$.each(data.y5, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							y5_set.push(0);
						} else {
							y5_set.push(item);
						}
					});
					showChart(titleName+riqi, xset, y1_set, y2_set, y3_set, y4_set,y5_set);
				});
	});
	function showChart(titleName, xset, yset1, yset2, yset3, yset4,yset5) {
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
					text : titleName + '电站日负荷曲线'
				},
				xAxis : [ {
					categories : xset
				} ],
				yAxis : [ { // Primary yAxis
					labels : {
						format : '{value}MW',
					/* style : {
						color : '#89A54E'
					} */
					},
					title : {
						text : '功率(MW)',
					/* style : {
						color : '#89A54E'
					} */
					}
				}, { // Secondary yAxis
					title : {
						text : '辐射强度(MJ/㎡)',
					/* style : {
						color : '#4572A7'
					} */
					},
					labels : {
						format : '{value} MJ/㎡',
					/* style : {
						color : '#4572A7'
					} */
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
					name : '并网功率',
					//color : '#4572A7',
					type : 'line',
					yAxis : 0,
					data : yset1,
					tooltip : {
						valueSuffix : ' MW'
					}

				}, {
					name : '逆变器输入功率',
					//color : '#4322B7',
					type : 'line',
					yAxis : 0,
					data : yset2,
					tooltip : {
						valueSuffix : ' MW'
					}

				}, {
					name : '逆变器输出功率',
					//color : '#4322B7',
					type : 'line',
					yAxis : 0,
					data : yset3,
					tooltip : {
						valueSuffix : ' MW'
					}

				},{
					name : '理论功率',
					//color : '#4322B7',
					type : 'line',
					yAxis : 0,
					data : yset4,
					tooltip : {
						valueSuffix : ' MW'
					}

				},{
					name : '辐射强度',
					//color : '#89A54E',
					type : 'line',
					yAxis : 1,
					data : yset5,
					tooltip : {
						valueSuffix : 'MJ/㎡'
					}
				} ]
			});
		});
	}
</script>
</head>
<body>
	<script src="${ctx}/huntoms/static/js/highcharts/highcharts.js"></script>
	<script src="${ctx}/huntoms/static/js/highcharts/modules/exporting.js"></script>

	<div id="container"
		style="min-width: 80px; height:360px; margin: 0 auto"></div>
</body>
</html>