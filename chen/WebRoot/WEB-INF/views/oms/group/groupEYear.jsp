<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>集团年电量统计分析</title>
		<link href="${ctx}/huntoms/static/css/chart.css"  rel="stylesheet"  type="text/css" />
		<link href="${ctx}/huntoms/static/css/table.css"  rel="stylesheet"  type="text/css" />
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script defer type="text/javascript">
		// 页面加载
		function onLoad() {
			getSearchCondition();
			// 查询条件（年份）
			var year = '${year}'; 
			// 判断是否包含查询条件
			if (year) {
				// 包含查询条件则获取后台数据
				getData(year);
			}
		}
		
		// 获取查询条件
		function getSearchCondition() {
			  $.getJSON('http://localhost:8080/huntoms/group/getGroupYearConditionData',function(data) { 
				  var yearList = [];
				   yearList = eval(data);
				   $(yearList).each(function(i, item) {
					   var option = document.createElement('OPTION');
					   option.value = item;
					   option.text = item;
					   document.getElementById('years').options.add(option);
				   });
			  });
		}
		
		// 获取后台数据
		function getData(year) {
			if (typeof(tableContainer) != 'undefined') {
				document.getElementById('tableContainer').innerHTML = "";	
			}
			// 获取
            $.getJSON('http://localhost:8080/huntoms/group/getGroupEYearData/'+year,function(data) {  
            	var jsonObj = eval(data);
                var title = jsonObj.title;  // 图表标题
                var year = jsonObj.year;  // 年份
                var xAxisCategories = [];  // x轴刻度
                xAxisCategories = jsonObj.xAxisCategories;
                var dataList = eval(jsonObj.dataBeanList);  // 数据列表               
                show(title, year, xAxisCategories, dataList);   //显示图表
            });  
        }  
        
		// 显示图表表格
        function show(title, year, xAxisCategories, dataList) {
			
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
                		text: '月份' 	
                	},
                	categories:  xAxisCategories
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
                plotOptions: {
                    series: {
                        cursor: 'pointer',
                        point: {
                            events: {
                                click: function(e) {
                                    location.href = 'http://localhost:8080/huntoms/group/groupEMonth/'+year+'/'+e.point.category;
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
			cell.innerHTML = '月份';
			document.getElementById('tableContainer').appendChild(table);
			
            // 数据赋值
            $(dataList).each(function(i, item) {
            	var itemData = eval(item);
        		var itemDataList = [];
        		var nameCell = table.rows[0].insertCell(i+1);
        		if (itemData.dataName == '计划发电量') {
        			nameCell.innerHTML = itemData.dataName + '(万度)';
        		} else if (itemData.dataName == '理论发电量') {
        			nameCell.innerHTML = itemData.dataName + '(万度)';
        		} else if (itemData.dataName == '实际发电量') {
        			nameCell.innerHTML = itemData.dataName + '(万度)';
        		} else if (itemData.dataName == '上网电量') {
        			nameCell.innerHTML = itemData.dataName + '(万度)';
        		} else {
        			nameCell.innerHTML = itemData.dataName;
        		}
            		$.each(itemData.dataList, function(j, dataItem) {
            			itemDataList.push(dataItem);
                		var dataCell = table.rows[j+1].insertCell(i+1);
                		dataCell.innerHTML = dataItem;
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
        <div id="searchContainer" class="search">
            <label>年份</label>
            <select id="years"></select>
            <button onclick="getData(years.value)">查询</button>  
        </div>   
        <div id="chartContainer"  style="width: 98%; height:360px; margin:auto"></div>
        <div id="tableContainer"  class="table" style="margin-top:20px; margin-left:12px"></div>
	</body>
</html>