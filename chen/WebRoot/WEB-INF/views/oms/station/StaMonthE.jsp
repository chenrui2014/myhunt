<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电站年发电量分析</title>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<script src="${ctx}/huntoms/static/js/My97DatePicker/WdatePicker.js"></script>
	<link href="${ctx}/huntoms/static/css/chart.css"  rel="stylesheet"  type="text/css" />
	<link href="${ctx}/huntoms/static/css/table.css"  rel="stylesheet"  type="text/css" />
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
					/* for(var i=0;i<data.orgId.length;i++){
						var option =document.createElement('OPTION');
						option.value=orgId[i];
						option.text=orgName[i];
						document.getElementById('organ_id').options.add(option);
					} */
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
function getData(organ_id,Year_){
	function byear() {
		this.name = "";
		this.data = [];
	}

	var orgName;
	var xAxis_ = [];

	$(function() {

		$.getJSON('http://localhost:8080/huntoms/station/displaySMonthE?organ_id='+organ_id+'&Year_='+Year_,
				function(data) { // 获取JSON数据
					var stationm=data.list;
					orgName = data.orgName;
					xAxis_ = data.month; 
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
					createTable(stationm);
					showHighCharts(orgName, xAxis_, yAxis_);
				});
	});
}
	function showHighCharts(orgName_, xAxis_, yAxis_) {

		$('#container').highcharts({
			chart : {
				type : 'column',
				backgroundColor : '#F1FBFB'
			//marginRight: 130,  
			//marginBottom: 25  
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
				valueSuffix : '万度',
				verticalAlign : 'middle'
			},
			legend : {
				//layout: 'vertical',
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
			/* legend : {
				floating : true,
				//y : 5
			}, */
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
		var th="<tr>"
				+ "<th>月份</th><th>装机容量</th><th>理论发电量</th><th>计划发电量</th><th>实际发电量</th><th>计划上网电量</th><th>上网电量</th><th>弃光电量</th>"
				+"</tr>";
		$('#table').append(th);
		
		var len = station.length;
		for(var i=0;i<len;i++){
			$('#table').append(		"<tr>"+
					"<td>"+station[i].month_+"</td>"+"<td>"+val(station[i].capacity)+"</td>"+
					"<td>"+val(station[i].theoryE)+"</td>"+"<td>"+val(station[i].planE)+"</td>"+
					"<td>"+val(station[i].e)+"</td>"+"<td>"+val(station[i].planOnE)+"</td>"+
					"<td>"+val(station[i].onNetE)+"</td>"+"<td>"+val(station[i].lossE)+"</td>"
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
	<script src="${ctx}/huntoms/static/js/highcharts//modules/exporting.js"></script>
	<div id="searchContainer" class="search">
            <label>电站</label>
            <select id="organ_id">
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
            <label>年份</label>            
            <select id="Year_"></select>           
			 <button onclick="getData(organ_id.value,Year_.value)">查询</button>  
	<div id="container"
		style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	<div  style="min-width: 310px; height: 400px; margin: 0 auto">
		<table border="1"  id="table">					
		</table>
	</div>
</body>
</html>