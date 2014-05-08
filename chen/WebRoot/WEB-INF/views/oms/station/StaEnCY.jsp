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
<link href="${ctx}/huntoms/static/css/chart.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/huntoms/static/css/table.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
//页面加载
	function onLoad() {
		getSearchCondition();
		var organ_id = '${organ_id}';
		var Year_='${Year_}';
		if (organ_id&&Year_) {
			getData(organ_id,Year_);
		}
	}
	//获取查询条件
	function getSearchCondition() {
		$.getJSON(
				'http://localhost:8080/huntoms/station/GetStaConditionDataY',
				function(data) {
					var orgId=[];
					var organName = [];
					var Year_=[];
					orgId=data.orgId
					organName = data.orgName;
					Year_=data.Year_;
					/* $(organName).each(
							function(i, item) {
								var option = document.createElement('OPTION');
								option.value = item;
								option.text = item;
								document.getElementById('organ_id').options
										.add(option);
							}); */
					$(Year_).each(
							function(i, item) {
								var option = document.createElement('OPTION');
								option.value = item;
								option.text = item;
								document.getElementById('Year_').options.add(option);
							});
				});
	}
function getData(organ_id,Years_){
	/*返回数据*/
	$(function() {

		var xset = [];
		var y1 = [];
		var y2 = [];
		var y3 = [];

		var y1_set = [];
		var y2_set = [];
		var y3_set = [];
		var y4_set = [];
		$.getJSON('http://localhost:8080/huntoms/station/displayEnCY?organ_id='+organ_id+'&Years_='+Years_,
				function(data) {
					var titleName = data.orgName;
					var Year_=data.Year_;
					var stationy=data.list;
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
							y1.push(0);
						} else {
							y1.push(item);
						}
					});
					$.each(data.y2, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							y2.push(0);
						} else {
							y2.push(item);
						}
					});
					$.each(data.y3, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							y3.push(0);
						} else {
							y3.push(item);
						}
					});
					for ( var i = 0; i < xset.length; i++) {
						y1_set.push(y2[i]);//厂用电量
						y2_set.push(y2[i] + y3[i]);//综合厂用电量
						//厂用电率
						if (y2[i] / y1[i]) {
							y3_set.push(y2[i] / y1[i] * 100);
						} else {
							y3_set.push(0);
						}
						//综合厂用电率
						if ((y2[i] + y3[i]) / y1[i]) {
							y4_set.push((y2[i] + y3[i]) / y1[i] * 100);
						} else {
							y4_set.push(0);
						}
					}
					showChart(titleName+Year_+"年", xset, y1_set, y2_set, y3_set, y4_set);
					createTable(stationy);
				});
	});
	}
	function showChart(titleName, xset, yset1, yset2, yset3, yset4) {
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
					text : titleName + '电站年能耗指标分析'
				},
				xAxis : [ {
					categories : xset
				} ],
				yAxis : [ { // Primary yAxis
					labels : {
						format : '{value}度',
					},
					title : {
						text : '电量(度)',
					}
				}, { // Secondary yAxis
					title : {
						text : '能耗指标(%)',
					},
					labels : {
						format : '{value} %',
					},
					opposite : true
				} ],
				tooltip : {
					shared : true
				},
				series : [ {
					name : '厂用电量',
					type : 'column',
					yAxis : 0,
					data : yset1,
					tooltip : {
						valueSuffix : ' 度'
					}

				}, {
					name : '综合厂用电量',
					type : 'column',
					yAxis : 0,
					data : yset2,
					tooltip : {
						valueSuffix : ' 度'
					}

				}, {
					name : '厂用电率',
					//color : '#89A54E',
					type : 'spline',
					yAxis : 1,
					data : yset3,
					tooltip : {
						valueSuffix : '%'
					}
				}, {
					name : '综合厂用电率',
					type : 'spline',
					yAxis : 1,
					data : yset4,
					tooltip : {
						valueSuffix : '%'
					}
				} ]
			});
		});
	}
	function createTable(station) {
		$('#table').empty();
		var th="<tr>"
				+ "<th>年份</th><th>发电量(万度)</th><th>厂用电量(万度)</th><th>购网电量(万度)</th><th>综合厂用电量(万度)</th><th>厂用电率(%)</th><th>综合厂用电率(%)</th>"
				+"</tr>";
		$('#table').append(th);
		
		var len = station.length;
		for(var i=0;i<len;i++){
			$('#table').append(		"<tr>"+
					"<td>"+station[i].month_+"</td>"+
					"<td>"+val(station[i].e)+"</td>"+
					"<td>"+val(station[i].factoryE)+"</td>"+
					"<td>"+val(station[i].purchaseNetE)+"</td>"+
					"<td>"+val(station[i].factoryE+station[i].purchaseNetE)+"</td>"+
					"<td>"+val(station[i].factoryE/station[i].e*100)+"</td>"+
					"<td>"+val((station[i].factoryE+station[i].purchaseNetE)/station[i].e)+"</td>"
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
		</select> <label>年份</label> <select id="Year_"></select>
		<button onclick="getData(organ_id.value,Year_.value)">查询</button>
	</div>
	<div id="container"
		style="min-width: 80px; height:360px; margin: 0 auto"></div>
	<div  style="min-width: 310px; height: 400px; margin: 0 auto">
		<table border="1"  id="table">					
		</table>
	</div>
</body>
</html>