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
								document.getElementById('Year_').options
										.add(option);
							});
				});
	}
function getData(organ_id,Years_){
	/*返回数据*/
	$(function() {
		
		var xset = [];
		var y1_set = [];
		var y2_set = [];

		var yEset = [];
		var yTset = [];
		var yCset = [];
		$.getJSON('http://localhost:8080/huntoms/station/displayYEffic?organ_id='+organ_id+'&Years_='+Years_,
				function(data) {
					var titleName=data.orgName;
					var disYear_=data.disYear_;
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
							yEset.push(0);
						} else {
							yEset.push(item);
						}
					});
					$.each(data.y2, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							yTset.push(0);
						} else {
							yTset.push(item);
						}
					});
					$.each(data.y3, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							yCset.push(0);
						} else {
							yCset.push(item);
						}
					});
					for ( var i = 0; i < xset.length; i++) {
						var y1 = (yEset[i] / yCset[i]/10000);
						var y2 = (yEset[i] / yTset[i]) * 100;
						y1_set.push(y1);
						y2_set.push(y2);
					}
					showChart(titleName+disYear_+"年",xset, y1_set, y2_set);
					createTable(stationy);
				});
	});
	}
	function showChart(titleName,xset, yset1, yset2) {
		$(function() {
			$('#container').highcharts({
				chart : {
					zoomType : 'xy',
					backgroundColor : '#F1FBFB'
				},
    			lang: {
    				printChart: '导出',
    			    downloadJPEG: '导出JPEG图片',
    			    downloadPDF: '导出DF文档',
    				downloadPNG: '导出PNG图片',
    				downloadSVG: '导出SVG矢量图'
    			},
				title : {
					text : titleName+'电站发电效率'
				},
				xAxis :  [{
                categories: xset//['Jan', 'Feb', 'Mar', 'Apr']
            }],
				yAxis : [ { // Primary yAxis
					labels : {
						format : '{value}万度',
						style : {
							color : '#89A54E'
						}
					},
					title : {
						text : '单兆瓦发电量(万度)',
						style : {
							color : '#89A54E'
						}
					}
				}, { // Secondary yAxis
					title : {
						text : '发电效率(%)',
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
					name : '单兆瓦电量',
					color : '#4572A7',
					type : 'column',
					yAxis : 0,
					data : yset1,
					tooltip : {
						valueSuffix : ' 万度'
					}

				}, {
					name : '发电效率',
					color : '#89A54E',
					type : 'spline',
					yAxis : 1,
					data : yset2,
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
				+ "<th>月份</th><th>理论发电量(万度)</th><th>发电量(万度)</th><th>发电效率(%)</th><th>单兆瓦发电量(万度)</th><th>等效利用小时(h)</th>"
				+"</tr>";
		$('#table').append(th);
		
		var len = station.length;
		for(var i=0;i<len;i++){
			$('#table').append(		"<tr>"+
					"<td>"+station[i].month_+"</td>"+"<td>"+val(station[i].theoryE)+"</td>"+
					"<td>"+val(station[i].e)+"</td>"+"<td>"+val(station[i].e/station[i].theoryE*100)+"</td>"+
					"<td>"+val(station[i].e/station[i].capacity)+"</td>"+"<td>"+val(station[i].e/station[i].capacity/1000)+"</td>"
					+"</tr>");
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
	<div style="min-width: 310px; height: 400px; margin: 0 auto">
		<table border="1" id="table">
		</table>
	</div>
</body>
</html>