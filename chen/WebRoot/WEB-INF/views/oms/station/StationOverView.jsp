<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电站概览</title>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
<script src="${ctx}/huntoms/static/js/common/comm.js"></script>
<script src="${ctx}/huntoms/static/js/My97DatePicker/WdatePicker.js"></script>
<link href="${ctx}/huntoms/static/css/chart.css" rel="stylesheet"
	type="text/css" />
<link href="${ctx}/huntoms/static/css/table_index.css" rel="stylesheet"
	type="text/css" />
<script type="text/javascript">
	function val(d){
		if(d==null||d=="null"||!isFinite(d)){
			d=0;
		}else{
			d=d;
		}
		return d.toFixed(2);
	}
	$(function() {
		var thisYE=[];
		var thisYOnE=[];
		var lastYE=[];
		var lastYOnE=[];
		var yesDE=[];
		var yesOnE=[];
		var staP=[];
		var planE=[];
		var planOnE=[];
		var theoryE=[];
		var x1 = [];
		var y11 = [];//当月发电量
		var y12 = [];//当月上网电量

		var x2 = [];
		var y21 = [];
		var y22 = [];
		var install;//装机容量
		var planInstall;//计划装机容量
		/*获取电站概览日数据*/
		$.getJSON('http://localhost:8080/huntoms/station/displayStaOVME',
			function(data){
				$.each(data.yesDE,function(i,item){
					yesDE.push(val(item));
				});
				document.getElementById("yesDE").innerHTML=yesDE;
				$.each(data.yesOnE,function(i,item){
						yesOnE.push(val(item));
				});
				document.getElementById("yesDOnE").innerHTML=yesOnE;
				
				$.each(data.staP,function(i,item){
						staP.push(val(item));
				});
				document.getElementById("sta_p").innerHTML=staP;
		});	
		/*获取电站概览年数据*/
		$.getJSON('http://localhost:8080/huntoms/station/displayStaOVAYE',
			function(data){
				//年电量
				$.each(data.thisYE,function(i,item){
					thisYE.push(val(item));
				});
				document.getElementById("tYE").innerHTML=thisYE;
				//年上网电量
				$.each(data.thisYOnE,function(i,item){
					thisYOnE.push(val(item));
				});
				document.getElementById("netYE").innerHTML=thisYOnE;
				//去年电量
				$.each(data.lastYE,function(i,item){
					lastYE.push(val(item));
				});
				document.getElementById("cLY").innerHTML=val(thisYE/lastYE*100)+"%";
				//去年上网电量
				$.each(data.lastYOnE,function(i,item){
					lastYOnE.push(val(item));
				});	
				document.getElementById("cLLY").innerHTML=val(thisYOnE/lastYOnE*100)+"%";	
		});	
		/*获取电站概览月数据*/
		$.getJSON('http://localhost:8080/huntoms/station/displayStaOVYE',
				function(data) {
					install=val(data.install*1);
					planInstall=val(data.planInstall*1);
					document.getElementById("install").innerHTML=install;
					document.getElementById("p_install").innerHTML=planInstall;
					$.each(data.x1, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							x1.push(0);
						} else {
							x1.push(item);
						}
					});
					
					//当月计划发电量 ,发电量,理论发电量
					$.each(data.planE, function(i, item) {
						planE.push(val(item));
					});
					$.each(data.y11, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							y11.push(0);
						} else {	
							y11.push(item / 10000);
						}
					});
					$.each(data.theoryE, function(i, item) {
						theoryE.push(val(item/10000));
					});
					document.getElementById("theoryE").innerHTML=theoryE;
					document.getElementById("tME").innerHTML=val(y11*1);
					document.getElementById("planE").innerHTML=planE;
					document.getElementById("planER").innerHTML=val(y11/planE)*100+"%";
					document.getElementById("Effic").innerHTML=val(y11/theoryE)*100+"%";
					document.getElementById("singleMWE").innerHTML=val(y11/install);
					document.getElementById("eUseHours").innerHTML=val(y11/install*10);
					//当月计划上网电量 ,上网电量
					$.each(data.planOnE, function(i, item) {
						planOnE.push(val(item));
					});
					$.each(data.y12, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							y12.push(0);
						} else {
							y12.push(item / 10000);
						}
					});
					document.getElementById("tOnME").innerHTML=val(y12*1);
					document.getElementById("planOnE").innerHTML=planOnE;
					document.getElementById("planOnER").innerHTML=val(y12/planOnE)*100+"%";
					$.each(data.x2, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							x2.push(null);
						} else {
							x2.push(item);
						}
					});
					$.each(data.y21, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							y21.push(0);
						} else {
							y21.push(item / 10000);
						}
					});
					$.each(data.y22, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							y22.push(0);
						} else {
							y22.push(item / 10000);
						}
					});
					showChart("container5", x1, "column", "column", y11, y12);
					showChart("container6", x2, "line", "line", y21, y22);
					//createTable(stationy);
				});
	});
	function showChart(containerID, xset, type1, type2, yset1, yset2) {
		$(function() {
			$('#' + containerID).highcharts({
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
					text : ''
				},
				xAxis : [ {
					categories : xset
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
				} ],
				tooltip : {
					shared : true,
					
				},
				legend : {
					layout : 'vertical',
					align : 'right',
					verticalAlign : 'middle',
					borderWidth : 0
				},
				series : [ {
					name : '发电量',
					color : '#4572A7',
					type : type1,
					yAxis : 0,
					data : yset1,
					tooltip : {
						valueSuffix : ' 万度'
					}

				}, {
					name : '上网电量',
					color : '#4322B7',
					type : type2,
					yAxis : 0,
					data : yset2,
					tooltip : {
						valueSuffix : ' 万度'
					}

				} ]
			});
		});
	}
	function createTable(station) {
		//alert(station[0].Year_);
		var th = "<tr>"
				+ "<th>年份</th><th>计划发电量(万度)</th><th>发电量(万度)</th><th>发电计划完成率(%)</th>"
				+ "</tr>";
		$('#table').append(th);

		var len = station.length;
		for ( var i = 0; i < len; i++) {
			$('#table').append(
					"<tr>"
							+ "<td>"
							+ station[i].year_
							+ "</td>"
							+ "<td>"
							+ (station[i].planE).toFixed(2)
							+ "</td>"
							+ "<td>"
							+ (station[i].e).toFixed(2)
							+ "</td>"
							+ "<td>"
							+ (station[i].e / station[i].planE * 100)
									.toFixed(2) + "</td>" + "</tr>");
		}
	}
	
</script>

<style type="text/css">
.lay1 {
	position: absolute;
	width: 48%;
	height: 220px;
	z-index: 1;
	left: 10px;
	margin-top: 18px;
	border: 0px solid #778899;
}

.lay2 {
	position: absolute;
	width: 48%;
	height: 220px;
	z-index: 2;
	left: 50%;
	margin-top: 20px
}
</style>
</head>
<body>
	<script src="${ctx}/huntoms/static/js/highcharts/highcharts.js"></script>
	<script src="${ctx}/huntoms/static/js/highcharts/modules/exporting.js"></script>
	<div id="container">
			<div id="searchContainer" class="search">
		<label>电站</label> <select id="organ_id" onchange="">
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
			onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-M-d'})" value="2014-3-31" />			
		<!-- <button onclick="getData(organ_id.value,ymdPara.value,invNum.value)">查询</button> -->
	</div>
		<div style="width:100%;height: 200px">
			<div id="container1" class="lay1"
				style="width: 48%; height:195px; margin: 0 auto">
				<table class="table-class" border="1" rules="rows">
					<!-- <caption>电站概览&nbsp;&nbsp;德令哈华炜一期</caption> -->
					<tr>
						<th>电站概览</th>
						<td colspan=3 align=center>德令哈华炜一期</td>
					</tr>
					<tr>
						<th>总装机容量:</th>
						<td id="install"></td>
						<th>计划投入装机:</th>
						<td id="p_install"></td>
					</tr>
					<tr>
						<th rowspan=1 align=center>电站介绍</th>
						
						<th colspan=1 align=center></th>
						<th colspan=1 align=center></th><th colspan=1 align=center></th>
					</tr>
					<tr>
						<td id="sta_desc" colspan=4>德令哈华炜一期很好</td>
					</tr>
				</table>
			</div>
			<div id="container2" class="lay2"
				style="width: 48%; height:195px; margin: 0 auto">
				<table class="table-class" border="1" rules="rows">
					<!-- <caption>电站电量&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;单位:万度</caption> -->
					<tr>
						<th colspan=4>电站电量</th> <th colspan=2 align=center>单位:万度</th>
					</tr>
					<tr>
						<th>年发电量:</th><td id="tYE">12345</td>
						<th>较去年:</th><td id="cLY">100%</td>
						<th>昨日发电量:</th><td id="yesDE">1234567</td>
					</tr>
					<tr>
						<th>年上网电量:</th><td id="netYE">12343</td>
						<th>较去年:</th><td id="cLLY">100%</td>
						<th>昨日上网电量:</th><td id="yesDOnE">1234567</td>
					</tr>
					<tr>
						<th>月发电量:</th><td id="tME">12345</td>
						<th>同比:</th><td>100%</td>
						<th>环比:</th><td>100%</td>
					</tr>
					<tr>
						<th>月上网电量:</th><td id="tOnME">12343</td>
						<th>同比:</th><td>100%</td>
						<th>环比:</th><td>100%</td>
						
					</tr>
				</table>
			</div>
		</div>
		<div style="width:100%;height: 200px">
			
			<div id="container4" class="lay2"
				style="width: 48%; height:195px; margin: 0 auto">
				<table class="table-class" border="1">
					<!-- <caption>电站效率&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</caption> -->
					<tr>
						<th colspan=4>电站效率</th>
					</tr>
					<tr>
						<th>理论发电量:</th><td id="theoryE">10MW</td>
						<th>发电效率:</th><td id="Effic">10MW</td>
					</tr>
					<tr>
						<th>平均输出功率:</th><td id="sta_p">123456KW</td>
						<th>平均负荷率:</th><td id="sta_lp">8.8MW</td>
					</tr>
					<tr>
						<th>单兆瓦发电量:</th><td id="singleMWE">0.67</td>
						<th>等效利用小时:</th><td id="eUseHours">99h</td>
					</tr>
				</table>
			</div>
			
			<div id="container3" class="lay1"
				style="width: 48%; height:195px; margin: 0 auto">
				<table class="table-class" border="1">
					<!-- <caption>电站计划执行情况&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</caption> -->
					<tr>
						<th colspan=4>电站计划执行情况</th>
					</tr>
					<tr>
						<th rowspan=1.5>计划发电量:</th><td id="planE">10MW</td>
						<th rowspan=1.5>计划完成率:</th><td id="planER">123%</td>
					</tr>
					<tr>
						<th>计划上网电量:</th><td id="planOnE">9MW</td>
						<th>计划完成率:</th><td id="planOnER">123%</td>
					</tr>
				</table>
			</div>
		</div>

		<div style="width:100%;height: 200px">
			<div id="container5" class="lay1"
				style="width: 48%; height:200px; margin: 0 auto"></div>
			<div id="container6" class="lay2"
				style="width: 48%; height:200px; margin: 0 auto"></div>
		</div>


	</div>

</body>
</html>