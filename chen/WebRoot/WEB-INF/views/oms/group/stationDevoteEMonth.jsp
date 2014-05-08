<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>电站间月发电贡献对比分析</title>
		<link href="${ctx}/huntoms/static/css/chart.css"  rel="stylesheet"  type="text/css" />
		<link href="${ctx}/huntoms/static/css/table.css"  rel="stylesheet"  type="text/css" />
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script type="text/javascript">
		function getData() {  
        	// 获取后台数据
            $.getJSON('http://localhost:8080/huntoms/group/getStationDevoteEMonthData/2014/3',function(data) {  
            	var jsonObj = eval(data);
                var title = jsonObj.title;  // 图表标题
                var groupId = jsonObj.groupId;  // 集团id（钻取条件）
                var xAxisCategories = [];  // x轴刻度
                xAxisCategories = jsonObj.xAxisCategories;
                var dataList = eval(jsonObj.dataBeanList);  // 数据列表               
                show(title, groupId, xAxisCategories, dataList);   //显示图表
            });  
        }  
        
		// 显示图表
        function show(title, groupId, xAxisCategories, dataList) {
			chart = new Highcharts.Chart({  // 创建图表对象
				chart: { 
					renderTo: 'chartContainer', 
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
                	text: title,
                    x: 0,
                    style: {
                    	fontSize: '18px',
                    	fontWeight: 'bold'
    				}
                },  
                xAxis: {
                	categories:  xAxisCategories,
                	labels:{ 
                		rotation:30, 
                		style:{             
                		fontWeight:'bold'
                		}
                	}
                },  
                yAxis: [{
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
                {
    				min: 0,
    				lineWidth: 1.0,
    				lineColor: '#CCD4D4',
    				gridLineColor: '#CCD4D4',
    				gridLineDashStyle: 'dash',
                    title: {
                        text: '装机容量(MW)'
                    },
                    plotLines: [{
                        value: 0,
                        width: 1,
                        color: '#808080'
                    }],
                    opposite: true
                }],
                tooltip: {
                    verticalAlign: 'middle'
                },
                legend: {
                	floating:true,
                	y:10
                }
            });
			
			// 创建表格
			var table = document.createElement('table');
			for (var i=xAxisCategories.length-1; i>-1; i--) {
				var row1 = table.insertRow();
				var cell1 = row1.insertCell();
				cell1.innerHTML = xAxisCategories[i];
			}
			var row = table.insertRow();
			var cell = row.insertCell();
			cell.innerHTML = '电站';
			document.getElementById('tableContainer').appendChild(table);
			
            // 数据赋值
            $(dataList).each(function(i, item) {
            		var itemData = eval(item);
            		var itemDataList = [];
            		var nameCell = table.rows[0].insertCell(i+1);
            		nameCell.innerHTML = itemData.dataName;
            		$.each(itemData.dataList, function(j, dataItem) {
            			itemDataList.push(dataItem);
                		var dataCell = table.rows[j+1].insertCell(i+1);
                		if (dataItem==null) {
                			dataCell.innerHTML = '0';
                		} else {
                			dataCell.innerHTML = dataItem;	
                		}
            		});
                	chart.addSeries({
                		name: itemData.dataName,
                		data: itemDataList,
                		type: itemData.dataType,
                		visible: itemData.visible,
                		yAxis: itemData.yAxis
            	});
            });
        }  
 
        getData(); 
		</script>
		<style type="text/css">
		table{border:1px solid #ccc;border-width:1px 0 0 1px;margin:2px 0 2px 0;text-align:center;border-collapse:collapse;}
		td,th{border:1px solid #ccc;border-width:0 1px 1px 0;margin:2px 0 2px 0;text-align:left;}
		th{text-align:center;font-weight:600;font-size:12px;background-color:#F4F4F4;}
		</style>
	</head>
	<body>
	    <script src="${ctx}/huntoms/static/js/highcharts/highcharts.js"></script>
        <script src="${ctx}/huntoms/static/js/highcharts/modules/exporting.js"></script>
        <div id="chartContainer" style="width: 1080px; height:420px; margin: 0 auto"></div>
        <div style="height:30px"></div>
        <div id="tableContainer"></div>
	</body>
</html>