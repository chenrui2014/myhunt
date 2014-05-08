<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>集团概览</title>
		<link href="${ctx}/huntoms/static/css/chart.css"  rel="stylesheet"  type="text/css" />
		<link href="${ctx}/huntoms/static/css/table.css"  rel="stylesheet"  type="text/css" />
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script type="text/javascript">
		// 页面加载
		function onLoad() {
		}
		
		// 获取后台数据
		function getData(id, dateType,year,month,day) {
			if (dateType=='year') {
	            $.getJSON('http://localhost:8080/huntoms/group/getBranchOverviewYearData/'+id +'/'+year,function(data) {  
	            	var jsonObj = eval(data);
	            	show(jsonObj);   //显示图表
	            });  
			} else if (dateType=="month") {
	            $.getJSON('http://localhost:8080/huntoms/group/getBranchOverviewMonthData/'+id +'/'+year+'/'+month,function(data) {  
	            	var jsonObj = eval(data);
	            	show(jsonObj);   //显示图表
	            });  
			}
        }  
        
		// 显示图表表格
        function show(jsonObj) {
            // 数据赋值
            document.getElementById("over1").innerHTML = null;
            var organData = eval(jsonObj.organData);
            document.getElementById('capacity').innerHTML = organData.capacity;
            document.getElementById('effice').innerHTML = organData.efficE;
            document.getElementById('e').innerHTML = organData.e;
            document.getElementById('onnete').innerHTML = organData.planE;
            document.getElementById('etoyester').innerHTML = organData.eToYester;
            document.getElementById('onnetetoyester').innerHTML = organData.planEToYester;
            var row1 = document.getElementById('over1').insertRow();
    		$.each(organData.organList, function(j, dataItem) {
        		var dataCell = row1.insertCell(j);
        		dataCell.innerHTML = dataItem;
    		});
    		 var row2 = document.getElementById('over1').insertRow(1);
    		$.each(organData.eList, function(j, dataItem) {
        		var dataCell = row2.insertCell(j);
        		dataCell.innerHTML = dataItem;
    		});
    		 var row3 = document.getElementById('over1').insertRow(2);
    		$.each(organData.onnetEList, function(j, dataItem) {
        		var dataCell = row3.insertCell(j);
        		dataCell.innerHTML = dataItem;
    		});
            var chart1Data = jsonObj.chartData[0];
            var title1 = chart1Data.title;  // 图表标题
            var xAxisCategories1 = [];  // x轴刻度
            xAxisCategories1 = chart1Data.xAxisCategories;
            var dataList1 = eval(chart1Data.dataBeanList);  // 数据列表 
            chart1 = new Highcharts.Chart({  // 创建图表对象
				chart: { 
					renderTo: 'chart1', 
                    type:  'column',  // 柱状图
                    backgroundColor: '#F1FBFB',
                    marginBottom: 80
                },
    			lang: {
    				printChart: '导出',
    			    downloadJPEG: '导出JPEG图片',
    			    downloadPDF: '导出DF文档',
    				downloadPNG: '导出PNG图片',
    				downloadSVG: '导出SVG矢量图'
    			},
                title: {
                	text:title1,
                    x: 0,
                    style: {
                    	fontSize: '18px',
                    	fontWeight: 'bold'
    				}
                },  
                xAxis: {
                	categories:  xAxisCategories1
                },  
                yAxis: {
    				min: 0,
    				lineWidth: 1.0,
    				lineColor: '#CCD4D4',
    				gridLineColor: '#CCD4D4',
    				gridLineDashStyle: 'dash',
                    title: {
                        text: '电量(万度)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    verticalAlign: 'middle'
                },
                legend: {
                	floating:true,
                	y:10
                }
            });
            // 数据赋值
            $(dataList1).each(function(i, item) {
            		var itemData1 = eval(item);
            		var itemDataList1 = [];
            		$.each(itemData1.dataList, function(j, dataItem1) {
            			itemDataList1.push(dataItem1);
            		});
                	chart1.addSeries({
                		name: itemData1.dataName,
                		data: itemDataList1,
                		type: itemData1.dataType,
                		yAxis: itemData1.yAxis
            	});
            });
            
            var chart2Data = jsonObj.chartData[1];
            var title2 = chart2Data.title;  // 图表标题
            var xAxisCategories2 = [];  // x轴刻度
            xAxisCategories2 = chart2Data.xAxisCategories;
            var dataList2 = eval(chart2Data.dataBeanList);  // 数据列表 
            chart2 = new Highcharts.Chart({  // 创建图表对象
				chart: { 
					renderTo: 'chart2', 
                    type:  'column',  // 柱状图
                    backgroundColor: '#F1FBFB',
                    marginBottom: 80
                },
    			lang: {
    				printChart: '导出',
    			    downloadJPEG: '导出JPEG图片',
    			    downloadPDF: '导出DF文档',
    				downloadPNG: '导出PNG图片',
    				downloadSVG: '导出SVG矢量图'
    			},
                title: {
                	text:title2,
                    x: 0,
                    style: {
                    	fontSize: '18px',
                    	fontWeight: 'bold'
    				}
                },  
                xAxis: { 
                	title: {
                		text: '月份' 	
                	},
                	categories:  xAxisCategories2
                },  
                yAxis: {
    				min: 0,
    				lineWidth: 1.0,
    				lineColor: '#CCD4D4',
    				gridLineColor: '#CCD4D4',
    				gridLineDashStyle: 'dash',
                    title: {
                        text: '电量(万度)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }]
                },
                tooltip: {
                    verticalAlign: 'middle'
                },
                legend: {
                	floating:true,
                	y:10
                }
            });
            // 数据赋值
            $(dataList2).each(function(i, item) {
            		var itemData = eval(item);
            		var itemDataList = [];
            		$.each(itemData.dataList, function(j, dataItem) {
            			itemDataList.push(dataItem);
            		});
                	chart2.addSeries({
                		name: itemData.dataName,
                		data: itemDataList,
                		type: itemData.dataType,
                		yAxis: itemData.yAxis
            	});
            });
            
            $(jsonObj.rankingData).each(function(i, item) {
            		var itemData = eval(item);
            		if ('efficE'==itemData.code) {
            			document.getElementById("efficETitle").innerHTML = itemData.title;
            			document.getElementById("efficEList").innerHTML = null;
            			var rowh = document.createElement('tr');
            			var th1 = document.createElement('th');       			
            			var th2 = document.createElement('th');
            			var th3 = document.createElement('th');
            			th1.innerHTML = '排名';
            			th2.innerHTML = '电站';
            			th3.innerHTML = '发电效率(%)';
            			rowh.appendChild(th1);
            			rowh.appendChild(th2);
            			rowh.appendChild(th3);
            			document.getElementById("efficEList").appendChild(rowh);
            			$(itemData.dataList).each(function(j, listItem) {
            				var listItemData = eval(listItem);
            				var li = document.getElementById("efficEList");
            				var row = li.insertRow(j+1);
            				var cell1 = row.insertCell(0);
            				cell1.innerHTML = j+1;
            				var cell2 = row.insertCell(1);
            				cell2.innerHTML = listItemData.name;
            				var cell3 = row.insertCell(2);
            				cell3.innerHTML = listItemData.value;
            			});
            		} else if ('singleMWE'==itemData.code) {
            			document.getElementById("singleMWETitle").innerHTML = itemData.title;
            			document.getElementById("singleMWEList").innerHTML = null;
            			var rowh = document.createElement('tr');
            			var th1 = document.createElement('th');       			
            			var th2 = document.createElement('th');
            			var th3 = document.createElement('th');
            			th1.innerHTML = '排名';
            			th2.innerHTML = '电站';
            			th3.innerHTML = '发电量(万度)';
            			rowh.appendChild(th1);
            			rowh.appendChild(th2);
            			rowh.appendChild(th3);
            			document.getElementById("singleMWEList").appendChild(rowh);
            			$(itemData.dataList).each(function(j, listItem) {
            				var listItemData = eval(listItem);
            				var li = document.getElementById("singleMWEList");
            				var row = li.insertRow(j+1);
            				var cell1 = row.insertCell(0);
            				cell1.innerHTML = j+1;
            				var cell2 = row.insertCell(1);
            				cell2.innerHTML = listItemData.name;
            				var cell3 = row.insertCell(2);
            				cell3.innerHTML = listItemData.value;
            			});
            		} else if ('sameUseHour'==itemData.code) {
            			document.getElementById("sameUseHourTitle").innerHTML = itemData.title;
            			document.getElementById("sameUseHourList").innerHTML = null;
            			var rowh = document.createElement('tr');
            			var th1 = document.createElement('th');       			
            			var th2 = document.createElement('th');
            			var th3 = document.createElement('th');
            			th1.innerHTML = '排名';
            			th2.innerHTML = '电站';
            			th3.innerHTML = '小时数(小时)';
            			rowh.appendChild(th1);
            			rowh.appendChild(th2);
            			rowh.appendChild(th3);
            			document.getElementById("sameUseHourList").appendChild(rowh);
            			$(itemData.dataList).each(function(j, listItem) {
            				var listItemData = eval(listItem);
            				var li = document.getElementById("sameUseHourList");
            				var row = li.insertRow(j+1);
            				var cell1 = row.insertCell(0);
            				cell1.innerHTML = j+1;
            				var cell2 = row.insertCell(1);
            				cell2.innerHTML = listItemData.name;
            				var cell3 = row.insertCell(2);
            				cell3.innerHTML = listItemData.value;
            			});
            		} else if ('e'==itemData.code) {
            			document.getElementById("eTitle").innerHTML = itemData.title;
            			document.getElementById("eList").innerHTML = null;
            			var rowh = document.createElement('tr');
            			var th1 = document.createElement('th');       			
            			var th2 = document.createElement('th');
            			var th3 = document.createElement('th');
            			th1.innerHTML = '排名';
            			th2.innerHTML = '电站';
            			th3.innerHTML = '发电量(万度)';
            			rowh.appendChild(th1);
            			rowh.appendChild(th2);
            			rowh.appendChild(th3);
            			document.getElementById("eList").appendChild(rowh);
            			$(itemData.dataList).each(function(j, listItem) {
            				var listItemData = eval(listItem);
            				var li = document.getElementById("eList");
            				var row = li.insertRow(j+1);
            				var cell1 = row.insertCell(0);
            				cell1.innerHTML = j+1;
            				var cell2 = row.insertCell(1);
            				cell2.innerHTML = listItemData.name;
            				var cell3 = row.insertCell(2);
            				cell3.innerHTML = listItemData.value;
            			});
            		} else if ('eScale'==itemData.code) {
            			document.getElementById("eScaleTitle").innerHTML = itemData.title;
            			document.getElementById("eScaleList").innerHTML = null;
            			var rowh = document.createElement('tr');
            			var th1 = document.createElement('th');       			
            			var th2 = document.createElement('th');
            			var th3 = document.createElement('th');
            			th1.innerHTML = '排名';
            			th2.innerHTML = '电站';
            			th3.innerHTML = '计划完成率(%)';
            			rowh.appendChild(th1);
            			rowh.appendChild(th2);
            			rowh.appendChild(th3);
            			document.getElementById("eScaleList").appendChild(rowh);
            			$(itemData.dataList).each(function(j, listItem) {
            				var listItemData = eval(listItem);
            				var li = document.getElementById("eScaleList");
            				var row = li.insertRow(j+1);
            				var cell1 = row.insertCell(0);
            				cell1.innerHTML = j+1;
            				var cell2 = row.insertCell(1);
            				cell2.innerHTML = listItemData.name;
            				var cell3 = row.insertCell(2);
            				cell3.innerHTML = listItemData.value;
            			});
            		} else if ('factoryEScale'==itemData.code) {
            			document.getElementById("factoryEScaleTitle").innerHTML = itemData.title;
            			document.getElementById("factoryEScaleList").innerHTML = null;
            			var rowh = document.createElement('tr');
            			var th1 = document.createElement('th');       			
            			var th2 = document.createElement('th');
            			var th3 = document.createElement('th');
            			th1.innerHTML = '排名';
            			th2.innerHTML = '电站';
            			th3.innerHTML = '发电效率(%)';
            			rowh.appendChild(th1);
            			rowh.appendChild(th2);
            			rowh.appendChild(th3);
            			document.getElementById("factoryEScaleList").appendChild(rowh);
            			$(itemData.dataList).each(function(j, listItem) {
            				var listItemData = eval(listItem);
            				var li = document.getElementById("factoryEScaleList");
            				var row = li.insertRow(j+1);
            				var cell1 = row.insertCell(0);
            				cell1.innerHTML = j+1;
            				var cell2 = row.insertCell(1);
            				cell2.innerHTML = listItemData.name;
            				var cell3 = row.insertCell(2);
            				cell3.innerHTML = listItemData.value;
            			});
            		} else if ('onNetE'==itemData.code) {
            			document.getElementById("onNetETitle").innerHTML = itemData.title;
            			document.getElementById("onNetEList").innerHTML = null;
            			var rowh = document.createElement('tr');
            			var th1 = document.createElement('th');       			
            			var th2 = document.createElement('th');
            			var th3 = document.createElement('th');
            			th1.innerHTML = '排名';
            			th2.innerHTML = '电站';
            			th3.innerHTML = '发电量(万度)';
            			rowh.appendChild(th1);
            			rowh.appendChild(th2);
            			rowh.appendChild(th3);
            			document.getElementById("onNetEList").appendChild(rowh);
            			$(itemData.dataList).each(function(j, listItem) {
            				var listItemData = eval(listItem);
            				var li = document.getElementById("onNetEList");
            				var row = li.insertRow(j+1);
            				var cell1 = row.insertCell(0);
            				cell1.innerHTML = j+1;
            				var cell2 = row.insertCell(1);
            				cell2.innerHTML = listItemData.name;
            				var cell3 = row.insertCell(2);
            				cell3.innerHTML = listItemData.value;
            			});
            		} else if ('onNetEScale'==itemData.code) {
            			document.getElementById("onNetEScaleTitle").innerHTML = itemData.title;
            			document.getElementById("onNetEScaleList").innerHTML = null;
            			var rowh = document.createElement('tr');
            			var th1 = document.createElement('th');       			
            			var th2 = document.createElement('th');
            			var th3 = document.createElement('th');
            			th1.innerHTML = '排名';
            			th2.innerHTML = '电站';
            			th3.innerHTML = '计划完成率(%)';
            			rowh.appendChild(th1);
            			rowh.appendChild(th2);
            			rowh.appendChild(th3);
            			document.getElementById("onNetEScaleList").appendChild(rowh);
            			$(itemData.dataList).each(function(j, listItem) {
            				var listItemData = eval(listItem);
            				var li = document.getElementById("onNetEScaleList");
            				var row = li.insertRow(j+1);
            				var cell1 = row.insertCell(0);
            				cell1.innerHTML = j+1;
            				var cell2 = row.insertCell(1);
            				cell2.innerHTML = listItemData.name;
            				var cell3 = row.insertCell(2);
            				cell3.innerHTML = listItemData.value;
            			});
            		} else if ('compreFactoryEScale'==itemData.code) {
            			document.getElementById("compreFactoryEScaleTitle").innerHTML = itemData.title;
            			document.getElementById("compreFactoryEScaleList").innerHTML = null;
            			var rowh = document.createElement('tr');
            			var th1 = document.createElement('th');       			
            			var th2 = document.createElement('th');
            			var th3 = document.createElement('th');
            			th1.innerHTML = '排名';
            			th2.innerHTML = '电站';
            			th3.innerHTML = '发电效率(%)';
            			rowh.appendChild(th1);
            			rowh.appendChild(th2);
            			rowh.appendChild(th3);
            			document.getElementById("compreFactoryEScaleList").appendChild(rowh);
            			$(itemData.dataList).each(function(j, listItem) {
            				var listItemData = eval(listItem);
            				var li = document.getElementById("compreFactoryEScaleList");
            				var row = li.insertRow(j+1);
            				var cell1 = row.insertCell(0);
            				cell1.innerHTML = j+1;
            				var cell2 = row.insertCell(1);
            				cell2.innerHTML = listItemData.name;
            				var cell3 = row.insertCell(2);
            				cell3.innerHTML = listItemData.value;
            			});
            		}
            });
        }  
		
		function search() {
			var dateType = document.getElementsByName('dateType');
			for (var i=0;i<dateType.length;i++) {
				if (dateType[i].checked) {
					getData(orgId.value,dateType[i].value,document.getElementById('year').value,document.getElementById('month').value);
				}
			}
		}
 
		onLoad();
		</script>
	</head>
	<body>
	    <div style="text-align:center; font-weight:bold;"><label style="font-size:22;">集团概览</label></div>
	    <div  style="width:100%">
	                <select id ="orgId">
                		<option value="2">德令哈分公司</option>
                		<option value="3">格尔木分公司</option>
                		<option value="4">海北分公司</option>
                		<option value="5">海南分公司</option>
                </select>
	        </div>
	        <label>查询方式</label>
	        <label><input name="dateType" type="radio" value="year" checked="checked"/>年</label> 
	        <label><input name="dateType" type="radio" value="month"/>月 </label>
	    </div>
	    <div  style="width:100%">
	        <label>年份</label>
            <select id="year">
                <option value="2010">2010</option>
                <option value="2011">2011</option>
                <option value="2012">2012</option>
                <option value="2013">2013</option>
            </select>
            <label>月份</label>
            <select id="month">
                <option value="1">1</option>
                <option value="2">2</option>
                <option value="3">3</option>
                <option value="4">4</option>
                <option value="5">5</option>
                <option value="6">6</option>
                <option value="7">7</option>
                <option value="8">8</option>
                <option value="9">9</option>
                <option value="10">10</option>
                <option value="11">11</option>
                <option value="12">12</option>  
            </select>
	        <button onclick="search()">查询</button>
	    </div>
	    <div id="overviewContainer"  style="width:100%;margin-top:20px; margin-left:12px;clear:both">
	        <div style="width:50%;clear:both">
	        <label>集团概览</label>
	         <label>总装机</label> <label id="capacity"></label><label>计划装机</label> <label>0</label>
	         <label>发电效率</label> <label id="effice"></label>
	        <table id="over1"></table>
	        </div>
	        <div style="width:50%;clear:both">
	            <label>集团电量</label>
	            <label>发电量</label> <label id="e"></label><label>上网电量</label> <label id="onnete"></label>
	            <label>较去年</label> <label id="etoyester"></label><label>较去年</label> <label id="onnetetoyester"></label>
	        </div>
	    </div>
	    <script src="${ctx}/huntoms/static/js/highcharts/highcharts.js"></script>
        <script src="${ctx}/huntoms/static/js/highcharts/modules/exporting.js"></script>
	    <div id="chartContainer"  style="margin-top:20px; margin-left:12px;clear:both">
	             <div id="chart1" style="width:50%"></div>
	             <div id="chart2" style="width:50%"></div>
	    </div>
        <div id="tableContainer"  class="table" style="margin-top:20px; margin-left:12px"></div>
        <div style="width:100%;clear:both"></div>
            <div style="width:200px;float:left;text-align:center">
                <label id="efficETitle"></label>
                <table style="width:200px" id="efficEList">
                 </table>
            </div>
            <div style="width:200px;float:left;text-align:center">
                <label id="singleMWETitle"></label>
                <table style="width:200px" id="singleMWEList">
                 </table>
            </div>
            <div style="width:200px;float:left;text-align:center">
                <label id="sameUseHourTitle"></label>
                <table style="width:200px" id="sameUseHourList">
                 </table>
            </div>
            <div style="width:200px;float:left;text-align:center">
                <label id="eScaleTitle"></label>
                <table style="width:200px" id="eScaleList">
                 </table>
            </div>
            <div style="width:200px;float:left;text-align:center">
                <label id="onNetEScaleTitle"></label>
                <table style="width:200px" id="onNetEScaleList">
                 </table>
            </div>
        </div>
	</body>
</html>