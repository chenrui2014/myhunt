<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电站历年发电量分析</title>
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
	function getData(organ_id) {
		function byear() {
			this.name = "";
			this.data = [];
		}

		var orgName;
		var xAxis_ = [];

		$(function() {
			// 获取数据
			$.getJSON(
					'http://localhost:8080/huntoms/station/displaySYearE?organ_id='
							+ organ_id, function(data) { // 获取JSON数据
						var stationy = data.list;
						orgName = data.orgName;
						xAxis_ = data.year;
						var len = data.datasum.length;
						var y = data.datasum;
						var yAxis_ = new Array();
						$.each(data.datasum, function(i, item) {
							var jsonObj = eval(item);
							//alert(item);
							var b = new byear();
							b.name = jsonObj.name;

							for ( var j = 0; j < jsonObj.data.length; j++) {

								if (jsonObj.data[j] == "null") {//查询数据中有空值，图表将无法显示
									b.data.push(0);
								} else {
									b.data.push(parseFloat(jsonObj.data[j]));
								}
							}

							yAxis_.push(b);

						});

						showHighCharts(orgName, xAxis_, yAxis_);
						createTable(stationy);
					});
		});
	}
	function showHighCharts(orgName_, xAxis_, yAxis_) {

		$('#container').highcharts({
			chart : {
				type : 'column',
				backgroundColor : '#F1FBFB'
			},
			credits : {
				enabled : false
			},
			lang : {
				printChart : '导出',
				downloadJPEG : '导出JPEG图片',
				downloadPDF : '导出DF文档',
				downloadPNG : '导出PNG图片',
				downloadSVG : '导出SVG矢量图'
			},
			title : {
				text : orgName_
			},
			tooltip : {
				valueSuffix : '万度',
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
					text : '发电量 (万度)',
				//layout: 'vertical'
				}
			},
			tooltip : {
				valueSuffix : '',
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
	function createTable(station) {
		$('#table').empty();
		var th = "<tr>"
				+ "<th>年份</th><th>装机容量</th><th>理论发电量</th><th>计划发电量</th><th>实际发电量</th><th>计划上网电量</th><th>上网电量</th><th>弃光电量</th>"
				+ "</tr>";
		$('#table').append(th);

		var len = station.length;
		for ( var i = 0; i < len; i++) {
			$('#table').append(
					"<tr>" + "<td>" + station[i].year_ + "</td>" + "<td>"
							+ val(station[i].capacity) + "</td>" + "<td>"
							+ val(station[i].theoryE) + "</td>" + "<td>"
							+ val(station[i].planE) + "</td>" + "<td>"
							+ val(station[i].e) + "</td>" + "<td>"
							+ val(station[i].planOnE) + "</td>" + "<td>"
							+ val(station[i].onNetE) + "</td>" + "<td>"
							+ val(station[i].lossE) + "</td>" + "</tr>");
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
	/* getSearchCondition();
	getData('德令哈华炜一期'); */
	onLoad();
</script>
</head>
<body>

	<script src="${ctx}/huntoms/static/js/highcharts/highcharts.js"></script>
	<script src="${ctx}/huntoms/static/js/highcharts//modules/exporting.js"></script>
	<div id="selectParam" class="search">
		<select id="organ_id"></select>
		<button onclick="getData(organ_id.value)">查询</button>
	</div>
	<div id="container"
		style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	<div id="tableContainer"
		style="min-width: 310px; height: 400px; margin: 0 auto">
		<table border="1" id="table">
		</table>
	</div>

</body>
</html>