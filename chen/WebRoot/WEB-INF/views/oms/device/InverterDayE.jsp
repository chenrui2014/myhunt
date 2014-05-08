<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>逆变器发电量对比分析</title>
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
	/*返回数据*/
	$(function() {
		
		var xset = [];
		var invName=[];
		var y1_set = [];
		var y2_set = [];
		var y3_set = [];
		var y4_set = [];
		var y5_set = [];
		var temp = ymdPara.split('-');
		$.getJSON('http://localhost:8080/huntoms/device/displayInverterDayE?organ_id='+organ_id+'&Years_='+temp[0]+'&Months_='+temp[1]+'&Days_='+temp[2]+'&invNum='+invNum,
				function(data) {
					invName[0]=data.invName1;
					invName[1]=data.invName2;
					invName[2]=data.invName3;
					invName[3]=data.invName4;
					invName[4]=data.invName5; 
					var titleName=data.orgName;
					//var stationy=data.list;
					$.each(data.x, function(i, item) {
						if (item == "null") {//查询数据中有空值，图表将无法显示
							xset.push(null);
						} else {
							xset.push(item);
						}
					});//alert(xset);
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
						if (item == "null"||item==null) {//查询数据中有空值，图表将无法显示
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
						if (item == "null"||item==null) {//查询数据中有空值，图表将无法显示
							y5_set.push(0);
						} else {
							y5_set.push(item);
						}
					});
					showChart(titleName,invName,xset, y1_set,y2_set,y3_set,y4_set,y5_set);
					createTable(invName,xset, y1_set,y2_set,y3_set,y4_set,y5_set);
				});
	});
	}
	function showChart(titleName,invName,xset, yset1,yset2,yset3,yset4,yset5) {
		$(function () {
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
            title: {
                text: titleName,
                x: -20 //center
            },
            xAxis: {
                categories: xset
            },
            yAxis: {
                title: {
                    text: '发电量 (度)'//(°C)
                },
                min:0,
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: '度'
            },
            series: [{
                name: invName[0],
                data: yset1
            }, {
                name: invName[1],
                data: yset2
            }, {
                name: invName[2],
                data: yset3
            }, {
                name: invName[3],
                data: yset4
            }, {
                name: invName[4],
                data: yset5
            }]
        });
    });
    
	}
	function createTable(invName,xset, yset1,yset2,yset3,yset4,yset5) {
		$('#table').empty();
		var th="<tr>"
				+ "<th>时间</th><th>"+invName[0]+"</th><th>"+invName[1]+"</th><th>"+invName[2]+"</th><th>"+invName[3]+"</th><th>"+invName[4]+"</th>"
				+"</tr>";
		$('#table').append(th);
		
		var len = xset.length;
		for(var i=0;i<len;i++){
			$('#table').append(		"<tr>"+
					"<td>"+xset[i]+"</td>"+"<td>"+val(yset1[i])+"</td>"+
					"<td>"+val(yset2[i])+"</td>"+
					"<td>"+val(yset3[i])+"</td>"+
					"<td>"+val(yset4[i])+"</td>"+
					"<td>"+val(yset5[i])+"</td>"
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
		</select>
		<label>逆变器</label> <select id="invNum">
			<option value="1">1#逆变器-5#逆变器</option>
			<option value="6">6#逆变器-10#逆变器</option>
			<option value="11">11#逆变器-15#逆变器</option>
			<option value="16">16#逆变器-20#逆变器</option>
			<option value="21">21#逆变器-25#逆变器</option>
			<option value="26">26#逆变器-30#逆变器</option>
			<option value="31">31#逆变器-35#逆变器</option>
			<option value="36">36#逆变器-40#逆变器</option>
		</select>
		<label>日期</label><input id="ymdPara" class="Wdate"
			style="margin-top:8px;" type="text" size="12"
			onfocus="WdatePicker({skin:'default',dateFmt:'yyyy-M-d'})" />
			
		<button onclick="getData(organ_id.value,ymdPara.value,invNum.value)">查询</button>

	</div>
	<div id="container"
		style="min-width: 80px; height:360px; margin: 0 auto"></div>
	<div  style="min-width: 310px; height: 400px; margin: 0 auto">
		<table border="1"  id="table">					
		</table>
	</div>
</body>
</html>