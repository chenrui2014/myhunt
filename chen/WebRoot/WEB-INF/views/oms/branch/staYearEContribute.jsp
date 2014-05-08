<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
	<title>电站年发电贡献统计</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script type="text/javascript" src="${ctx}/huntoms/static/js/jquery/jquery.js"></script>
		<script src="${ctx}/huntoms/static/js/highcharts/highcharts.js"></script>
        <script src="${ctx}/huntoms/static/js/highcharts/modules/exporting.js"></script>
		<script src="${ctx}/huntoms/static/js/My97DatePicker/WdatePicker.js"></script>
		<link href="${ctx}/huntoms/static/css/chart.css"  rel="stylesheet"  type="text/css" />
		<link href="${ctx}/huntoms/static/css/table.css"  rel="stylesheet"  type="text/css" />
	<script type="text/javascript">
	
function commObject(name_,data_){
		this.name = name_;
		this.data = data_;
	}

function showHighCharts(xAxis_,ylAxis_,yrAxis_){
	
	var chart = new Highcharts.Chart(
			{
		         chart: {
		             zoomType: 'xy',
		             renderTo: 'container',
		             backgroundColor: '#F1FBFB'
		         },
		         lang: {
	    				printChart: '导出',
	    			    downloadJPEG: '导出JPEG图片',
	    			    downloadPDF: '导出DF文档',
	    				downloadPNG: '导出PNG图片',
	    				downloadSVG: '导出SVG矢量图'
	    		},
		        credits: {
		             enabled: false
		         } ,
		         title: {
		             text: '电站年发电贡献统计'
		         },
		         xAxis: [{
		                	 categories:xAxis_ }] ,
		         yAxis: [{ // Primary yAxis
		             labels: {
		                 formatter: function() {
		                     return this.value ;
		                 },
		                 style: {
		                     color: '#89A54E'
		                 }
		             },
		             title: {
		                 text: '装机容量(MW)',
		                 style: {
		                     color: '#89A54E'
		                 },
		                 margin:30
		             },
		             opposite: true
		 
		         }, { // Secondary yAxis
		             gridLineWidth: 0,
		             title: {
		                 text: '电量（万度）',
		                 style: {
		                     color: '#4572A7'
		                 },
		                 margin:30
		             },
		             labels: {
		                 formatter: function() {
		                     return this.value ;
		                 },
		                 style: {
		                     color: '#4572A7'
		                 }
		             }
		         }],
		         tooltip: {
		             shared: true
		         },
		         legend: {
		    			
		         },
		         series: [
		        ]
		     }
	);
	
	var len1 = yrAxis_.length;
	for(var i=0;i<len1;i++){
		chart.addSeries(		 {
            name: yrAxis_[i].name,
            color: '#89A54E',
            type:  'column',
            data: yrAxis_[i].data,
            tooltip: {
                valueSuffix: 'MW'
            }
        });
	}
	//var color =['#4572A7','#4572B7'];
 	var len = ylAxis_.length;
		for(var i=0;i<len;i++){
			chart.addSeries({
		        name: ylAxis_[i].name,
		       // color: color[i],
		        type:  'spline',
		        yAxis: 1,
		        data:  ylAxis_[i].data,
		        tooltip: {
		            valueSuffix: ' 万度'
		        }
		    });
		} 

	
	// $('#container').highcharts();	 
}

	function createTable(branch) {
		$('#table').empty();
		var th="<tr>"
				+ "<th>电站</th><th>装机容量(MW)</th><th>发电量(万度)</th><th>上网电量（万度）</th>"
				+"</tr>";
		$('#table').append(th);
		
		var len = branch.length;
		for(var i=0;i<len;i++){
			$('#table').append("<tr>"+
					"<td>"+branch[i].organName+"</td>"+"<td>"+branch[i].capacity+"</td>"+"<td>"+branch[i].e+"</td>"+"<td>"+branch[i].onnete+"</td>"
					+"</tr>");
		}
	}
	
	function query(orgid,para){
		var url = '<%=request.getContextPath()%>/branch/displayStaYearEContribute?orgid='+orgid+'&parayear='+para;
		//alert(url);
		$.getJSON(url,function(data){  // 获取JSON数据
			var xAxis_ =[];
			var ylAxis_ = new Array();
			var yrAxis_ = new Array();
			//var orgName = data.orgName;
			xAxis_ = data.x;
			var temp_ry = data.ry;
		     $.each(temp_ry,function(i,item) { 
		            var jsonObj = eval(item);
		            var b = new commObject(jsonObj.name,jsonObj.data);
		            yrAxis_.push(b);
		         }); 
		     
			var temp_ly = data.ly;
		     $.each(temp_ly,function(i,item) { 
		            var jsonObj = eval(item);
		            var b = new commObject(jsonObj.name,jsonObj.data);
		            ylAxis_.push(b);
		         });  
			
		   showHighCharts( xAxis_,ylAxis_,yrAxis_);
		   var branchy = data.table;
		   createTable(branchy);
		  });
		}
</script>
</head>
<body>
	
    <div id="searchContainer" class="search">
            <label>组织</label>
            <select id ="orgId">
                		<option value="2">德令哈分公司</option>
                		<option value="3">格尔木分公司</option>
                		<option value="4">海北分公司</option>
                		<option value="5">海南分公司</option>
                </select>
             <label>时间</label><input id="year" class="Wdate" style="margin-top:8px;" type="text" size="12" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy'})"/>
            <button onclick="query($('#orgId').val(),$('#year').val())">查询</button>  
     </div>   
	<div id="container" style="width: 98%; height:360px; margin:auto"></div>	
	 <div id="tableContainer"  class="table" style="margin-top:20px; margin-left:12px">
	 	<table  id="table">
										
		</table>
	 </div>	
</body>
</html>