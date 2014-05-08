<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>集团历年上网计划执行分析</title>
		<link href="${ctx}/huntoms/static/css/chart.css"  rel="stylesheet"  type="text/css" />
		<link href="${ctx}/huntoms/static/css/table.css"  rel="stylesheet"  type="text/css" />
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script type="text/javascript">
		// 页面加载
		function onLoad() {
			getData();
		}
		
		// 获取后台数据
		function getData() {
			// 获取
            $.getJSON('http://localhost:8080/huntoms/group/getGroupPlanOnNetEOverYearData',function(data) {  
            	var jsonObj = eval(data);
                var title = jsonObj.title;  // 图表标题
                var xAxisCategories = [];  // x轴刻度
                xAxisCategories = jsonObj.xAxisCategories;
                var dataList = eval(jsonObj.dataBeanList);  // 数据列表               
                show(title, xAxisCategories, dataList);   //显示图表
            });  
        }  
        
		// 显示图表表格
        function show(title, xAxisCategories, dataList) {
			
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
                	title: {
                		text: '年份' 	
                	},
                	categories:  xAxisCategories
                },  
                yAxis: [{
    				min: 0,
    				lineWidth: 1.0,
    				lineColor: '#CCD4D4',
    				gridLineColor: '#CCD4D4',
    				gridLineDashStyle: 'dash',
                    title: {
                        text: '电量(万度)'
                    }},
                    {
        				min: 0,
        				lineWidth: 1.0,
        				lineColor: '#CCD4D4',
        				gridLineColor: '#CCD4D4',
        				gridLineDashStyle: 'dash',
        				labels:{
        					format:'{value}%'	
        				},
        				title: {
                            text: '计划完成率'
                        },
                        opposite:true
                        }],
                plotOptions: {
                    series: {
                        cursor: 'pointer',
                        point: {
                            events: {
                                click: function(e) {
                                    location.href = 'http://localhost:8080/huntoms/group/groupPlanOnNetEYear/'+e.point.category;
                                }
                            }
                        }
                    }
                },
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
			cell.innerHTML = '年份';
			document.getElementById('tableContainer').appendChild(table);
			
            // 数据赋值
            $(dataList).each(function(i, item) {
            		var itemData = eval(item);
            		var itemDataList = [];
            		var nameCell = table.rows[0].insertCell(i+1);
            		if (itemData.dataName == '上网电量') {
            			nameCell.innerHTML = itemData.dataName + '(万度)';
            		} else if (itemData.dataName == '计划上网电量') {
            			nameCell.innerHTML = itemData.dataName + '(万度)';
            		} else {
            			nameCell.innerHTML = itemData.dataName;
            		}
            		$.each(itemData.dataList, function(j, dataItem) {
            			itemDataList.push(dataItem);
                		var dataCell = table.rows[j+1].insertCell(i+1);
                		if (itemData.dataName =='计划完成率') {
                			dataCell.innerHTML = dataItem+'%';
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
 
		onLoad();
		</script>
	</head>
	<body>
	    <script src="${ctx}/huntoms/static/js/highcharts/highcharts.js"></script>
        <script src="${ctx}/huntoms/static/js/highcharts/modules/exporting.js"></script>
        <div id="chartContainer"  style="width: 98%; height:360px; margin:auto"></div>
        <div id="tableContainer"  class="table" style="margin-top:20px; margin-left:12px"></div>
	</body>
</html>