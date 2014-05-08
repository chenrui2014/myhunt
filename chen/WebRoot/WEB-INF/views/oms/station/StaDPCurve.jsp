<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电站日负荷曲线</title>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script src="${ctx}/huntoms/static/js/My97DatePicker/WdatePicker.js"></script>
	<script src="${ctx}/huntoms/static/js/common/comm.js"></script>
	<link href="${ctx}/huntoms/static/css/chart.css"  rel="stylesheet"  type="text/css" />
	<link href="${ctx}/huntoms/static/css/table.css"  rel="stylesheet"  type="text/css" />
	<script type="text/javascript">
	//页面加载
	function onLoad() {
		//getSearchCondition();
		var organ_id='${organ_id}';
				
		var ymdPara='${ymdPara}';
		if (ymdPara) {
			getData(ymdPara);
			
		}
	}
	function getData(organ_id,ymdPara){
	/*返回数据*/
	$(function() {

		var xset = [];
		var y1_set = [];
		var y2_set = [];
		var y3_set = [];
		var y4_set = [];
		var y5_set = [];
		var temp = ymdPara.split('-');
		$.getJSON('http://localhost:8080/huntoms/station/displaySDayPCurve?organ_id='+organ_id+'&Years_='+temp[0]+'&Months_='+temp[1]+'&Days_='+temp[2],
				function(data) {
					var stationy = data.list;
					var titleName = data.orgName;
					var riqi = data.riqi;
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
					showChart(titleName + riqi, xset, y1_set, y2_set, y3_set,
							y4_set, y5_set);
					createTable(stationy);
				});
	});
	}
	function showChart(titleName, xset, yset1, yset2, yset3, yset4, yset5) {
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
					},
					title : {
						text : '功率(MW)',
					}
				}, { // Secondary yAxis
					title : {
						text : '辐射强度(MJ/㎡)',
					},
					labels : {
						format : '{value} MJ/㎡',
					},
					opposite : true
				} ],
				tooltip : {
					shared : true
				},
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
					type : 'line',
					yAxis : 0,
					data : yset2,
					tooltip : {
						valueSuffix : ' MW'
					}

				}, {
					name : '逆变器输出功率',
					type : 'line',
					yAxis : 0,
					data : yset3,
					tooltip : {
						valueSuffix : ' MW'
					}

				}, {
					name : '理论功率',
					type : 'line',
					yAxis : 0,
					data : yset4,
					tooltip : {
						valueSuffix : ' MW'
					}

				}, {
					name : '辐射强度',
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
	function createTable(station) {
		$('#table').empty();
		var th = "<tr>"
				+ "<th>时间</th><th>并网功率</th><th>理论功率</th><th>逆变器输入功率</th><th>逆变器输出功率</th><th>辐射强度</th>"
				+ "</tr>";
		$('#table').append(th);

		var len = station.length;
		//alert(val(station[0].invInput));
		for ( var i = 0; i < len; i++) {
			$('#table').append(
					"<tr>" + "<td>" + station[i].hour_ + "</td>" + "<td>"
							+ val(station[i].grdConnectP)+ "</td>"
							+ "<td>" + val(station[i].theoryP)
							+ "</td>" + "<td>" +val(station[0].invInput)
							+ "</td>" + "<td>" + val(station[i].invOutput) + "</td>"
							+ "<td>" + val(station[i].avgIrradiance)
							+ "</td>" + "</tr>");
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
	
	onLoad();
	
</script>
</head>
<body>
	<script src="${ctx}/huntoms/static/js/highcharts/highcharts.js"></script>
	<script src="${ctx}/huntoms/static/js/highcharts/modules/exporting.js"></script>
	<div id="searchContainer" class="search">
		<label>电站</label> <select id="organ_id">
			<option value="6">德令哈华炜一期</option>
			<option value="7">德令哈华炜二期</option>
			<option value="8">德令哈百科一期</option>
			<option value="9">格尔木华广一期</option>
			<option value="10">格尔木华广二期</option>
			<option value="11">格尔木百科一期</option>
			<option value="12">刚察华振一期</option>
			<option value="13">刚察华振二期</option>
			<option value="14">刚察天逸一期</option>
			<option value="15">刚察天逸二期</option>
			<option value="16">共和华诚一期</option>
			<option value="17">共和华诚二期</option>
			<option value="18">共和奔亚一期</option>
			<option value="19">共和奔亚二期</option>
		</select>
		<label>日期</label><input id="ymdPara" class="Wdate"
			style="margin-top:8px;" type="text" size="12"
			onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-M-d'})" />
			
		<button onclick="getData(organ_id.value,ymdPara.value)">查询</button>

	</div>
	<div id="container"
		style="min-width: 80px; height:360px; margin: 0 auto"></div>
	<div style="min-width: 310px; height: 400px; margin: 0 auto">
		<table border="1" id="table">
		</table>
	</div>
</body>
</html>