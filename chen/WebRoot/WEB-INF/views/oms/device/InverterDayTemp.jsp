<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>逆变器功率-温度对照分析</title>
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
		var invNum='${invNum}';
		if (ymdPara) {
			getData(organ_id,ymdPara,invNum);
			
		}
	}
	function getData(organ_id,ymdPara,invNum){
	function byear() {
		this.name = "";
		this.data = [];
	}

	var orgName;
	var xAxis_ = [];
	var yAxis_1 = [];
	var yAxis_2 = [];
	var temp = ymdPara.split('-');
	$(function() {

		$.getJSON('http://localhost:8080/huntoms/device/displayInverterDayTemp?organ_id='+organ_id+'&Years_='+temp[0]+'&Months_='+temp[1]+'&Days_='+temp[2]+'&invNum='+invNum,
				function(data) { // 获取JSON数据
					var inverter=data.list;
					orgName = data.orgName;
					$.each(data.hour_, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							xAxis_.push(null);
						} else {
							xAxis_.push(item);
						}
					});
					$.each(data.p, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							yAxis_1.push(null);
						} else {
							yAxis_1.push(item);
						}
					});
					$.each(data.t, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							yAxis_2.push(null);
						} else {
							yAxis_2.push(item);
						}
					});
					
					showHighCharts(orgName, xAxis_, yAxis_1,yAxis_2);
					createTable(inverter);
				});
	});
}
	function showHighCharts(orgName_, xAxis_, yAxis_1,yAxis_2) {

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
				text : orgName_
			},
			tooltip : {
				valueSuffix : 'kW',
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
			yAxis : [ { // Primary yAxis
					labels : {
						format : '{value}KW',
						style : {
							color : '#89A54E'
						}
					},
					title : {
						text : '功率(KW)',
						style : {
							color : '#89A54E'
						}
					}
				}, { // Secondary yAxis
					title : {
						text : '温度(℃)',
						style : {
							color : '#4572A7'
						}
					},
					labels : {
						format : '{value} ℃',
						style : {
							color : '#4572A7'
						}
					},
					opposite : true
				} ],
				tooltip : {
					shared : true
				},
				series : [ {
					name : '功率',
					color : '#4572A7',
					type : 'line',
					yAxis : 0,
					data : yAxis_1,
					tooltip : {
						valueSuffix : ' KW'
					}

				}, {
					name : '温度',
					color : '#89A54E',
					type : 'line',
					yAxis : 1,
					data : yAxis_2,
					tooltip : {
						valueSuffix : '℃'
					}
				} ]
		});

	}
	
	function createTable(inverter) {
		$('#table').empty();
		var th="<tr>"
				+ "<th>时间</th><th>功率</th><th>温度</th>"
				+"</tr>";
		$('#table').append(th);
		
		var len = inverter.length;
		for(var i=0;i<len;i++){
			$('#table').append(		"<tr>"+
					"<td>"+inverter[i].hour_+"</td>"+
					"<td>"+val(inverter[i].avg_P)+"</td>"+
					"<td>"+val(inverter[i].temp)+"</td>"
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
		<label>逆变器</label> <select id="invNum">
			<option value="1">1#逆变器</option><option value="2">2#逆变器</option><option value="3">3#逆变器</option><option value="4">4#逆变器</option>
			<option value="5">5#逆变器</option><option value="6">6#逆变器</option><option value="7">7#逆变器</option><option value="8">8#逆变器</option>
			<option value="9">9#逆变器</option><option value="10">10#逆变器</option><option value="11">11#逆变器</option><option value="12">12#逆变器</option>
			<option value="13">13#逆变器</option><option value="14">14#逆变器</option><option value="15">15#逆变器</option><option value="16">16#逆变器</option>
			<option value="17">17#逆变器</option><option value="18">18#逆变器</option><option value="19">19#逆变器</option><option value="20">20#逆变器</option>
			<option value="21">21#逆变器</option><option value="22">22#逆变器</option><option value="23">23#逆变器</option><option value="24">24#逆变器</option>
			<option value="25">25#逆变器</option><option value="26">26#逆变器</option><option value="27">27#逆变器</option><option value="28">28#逆变器</option>
			<option value="29">29#逆变器</option><option value="30">30#逆变器</option><option value="31">31#逆变器</option><option value="32">32#逆变器</option>
			<option value="33">33#逆变器</option><option value="34">34#逆变器</option><option value="35">35#逆变器</option><option value="36">36#逆变器</option>
			<option value="37">37#逆变器</option><option value="38">38#逆变器</option><option value="39">39#逆变器</option><option value="40">40#逆变器</option>
		</select>
		<label>日期</label><input id="ymdPara" class="Wdate"
			style="margin-top:8px;" type="text" size="12"
			onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-M-d'})" />
			
		<button onclick="getData(organ_id.value,ymdPara.value,invNum.value)">查询</button>

	</div>
	<div id="container"	style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	<div id="showtable"	style="min-width: 310px; height: 400px; margin: 0 auto">
		<table border="1"  id="table">

		</table>
	</div>
</body>
</html>