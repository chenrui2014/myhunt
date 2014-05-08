<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>电站间日单兆瓦发电量分析</title>
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
		var Year_ = '${Year_}';
		var Month_= '${Month_}';
		
		var ymPara='${ymdPara}';
		if (organ_id&&ymdPara) {
			getData(ymdPara);
			
		}
	}
	function getData(ymdPara){
	$(function() {
		var xset = [];
		var yset = [];
		var y1set = [];
		var y2set = [];
		var temp = ymdPara.split('-'); 
		$.getJSON('http://localhost:8080/huntoms/station/displayVSDE?Year_='+temp[0]+'&Month_='+temp[1]+'&Day_='+temp[2],
				function(data) {
					var stationy=data.list;
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
					//alert(y1set);
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
		$('#barcontainer').highcharts({
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
				text : '电站间日单兆瓦发电量分析'//title_name//
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
				x : 100,
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
		var th="<tr>"
				+ "<th>电站</th><th>装机容量</th><th>发电量(度)</th><th>单兆瓦发电量(度)</th><th>等效利用小时(h)</th>"
				+"</tr>";
		$('#table').append(th);
		
		var len = station.length;
		for(var i=0;i<len;i++){
			$('#table').append(		"<tr>"+
					"<td>"+station[i].organName+"</td>"+"<td>"+val(station[i].capacity)+"</td>"+
					"<td>"+val(station[i].e)+"</td>"+"<td>"+val(station[i].e/station[i].capacity)+"</td>"+
					"<td>"+val(station[i].e/station[i].capacity/1000)+"</td>"
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
	//getData(6,2014,3);
	onLoad();
</script>
</head>
<body>
	<script src="${ctx}/huntoms/static/js/highcharts/highcharts.js"></script>
	<script src="${ctx}/huntoms/static/js/highcharts//modules/exporting.js"></script>
	<div id="searchContainer" class="search">
		
		</select> <label>时间</label><input id="ymdPara" class="Wdate"
			style="margin-top:8px;" type="text" size="12"
			onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-M-d'})" />
		<button onclick="getData(ymdPara.value)">查询</button>

	</div>
	<div id="barcontainer"
		style="min-width: 310px; height: 450px; margin: 0 auto"></div>
	<div  style="min-width: 310px; height: 400px; margin: 0 auto">
		<table border="1"  id="table">					
		</table>
	</div>
</body>
</html>