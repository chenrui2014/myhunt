<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<title>区域年电量统计分析</title>
</head>
<body>
		<script type="text/javascript" src="${ctx}/huntoms/static/js/jquery/jquery.js"></script>
		<script src="${ctx}/huntoms/static/js/highcharts/highcharts.js"></script>
        <script src="${ctx}/huntoms/static/js/highcharts/modules/exporting.js"></script>
	<script src="${ctx}/huntoms/static/js/My97DatePicker/WdatePicker.js"></script>
	<script src="${ctx}/huntoms/static/js/common/comm.js"></script>
	<link href="${ctx}/huntoms/static/css/chart.css"  rel="stylesheet"  type="text/css" />
	<link href="${ctx}/huntoms/static/css/table.css"  rel="stylesheet"  type="text/css" />
<script type="text/javascript">
function byear(){
	this.name = "";
	this.data = [];
}

var orgName;
var xAxis_ =[];

$(function () {
	

});


function showHighCharts(orgName_,xAxis_,yAxis_){
	
	
	 var chart = new Highcharts.Chart(
			{
		        chart: {
		            type: 'column',
		            renderTo: 'container',
		            backgroundColor: '#F1FBFB'
		        },
		        title: {
		            text: orgName_+'年电量统计分析'
		        },
		        xAxis:{
		        	title:{
		        		text:'月份'
		        	},
		            categories: xAxis_
		        },
			    credits: {
		             enabled: false
		         } ,
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
	                ,
		             labels: {
		                 formatter: function() {
		                     return this.value ;
		                 },
		                 style: {
		                     color: '#4572A7'
		                 }
		             }
		        },
		        lang: {
					printChart: '导出',
				    downloadJPEG: '导出JPEG图片',
				    downloadPDF: '导出DF文档',
					downloadPNG: '导出PNG图片',
					downloadSVG: '导出SVG矢量图'
				},
		        plotOptions: {
		            column: {
		                pointPadding: 0.2,
		                borderWidth: 0
		            }
		        }
		    }		
	);
	
	var len = yAxis_.length;
	var visiable =[true,true,false,false,false,false];
	for(var i=0;i<len;i++){
		//alert(yAxis_[i].name);
		//alert(yAxis_[i].data)
		chart.addSeries({
	        name: yAxis_[i].name,
	       // color: '#4572A7',
	      //  yAxis: 1,
	        visible:visiable[i],
	        data:  yAxis_[i].data,
	        tooltip: {
	            valueSuffix: ' 万度'
	        }
	    });
	} 
}

function createTable(branch) {
	$('#table').empty();
	var th="<tr>"
		+ "<th>日期</th><th>装机容量(万度)</th><th>理论发电量(万度)</th><th>计划发电量(万度)</th><th>实际发电量(万度)</th><th>计划上网电量(万度)</th><th>上网电量(万度)</th><th>弃光电量(万度)</th>"
		+"</tr>";
	$('#table').append(th);
	
	var len = branch.length;
	for(var i=0;i<len;i++){
		$('#table').append(		"<tr>"+
				"<td>"+branch[i].year+"-"+branch[i].bmonth+"</td>"+"<td>"+branch[i].capacity+"</td>"+"<td>"+branch[i].theorye+"</td>"+"<td>"+branch[i].planE+"</td>"+"<td>"+branch[i].e+"</td>"+"<td>"+branch[i].planOne+"</td>"+"<td>"+branch[i].onnete+"</td>"+"<td>"+branch[i].losse+"</td>"
				+"</tr>");
	}
}

function query(orgid,para){
	
	var  url ='<%=request.getContextPath()%>/branch/displayBranchMonthE?orgid='+orgid+'&parayear='+para;
	$.getJSON(url,function(data){  // 获取JSON数据
		if(!$.isEmptyObject(data)){
			var orgName = data.orgName;
			var xAxis_ = data.month;
			var yAxis_ = new Array();	    		
	 	     $.each(data.datasum,function(i,item) { 
		            var jsonObj = eval(item);
		        	var  b = new byear();
	    			b.name=jsonObj.name;
	    			for(var j = 0; j < jsonObj.data.length; j++){
	    				b.data.push(jsonObj.data[j]);
	    			}
	    			yAxis_.push(b);
		         });   
		     showHighCharts(orgName,xAxis_,yAxis_);
		    var branchy = data.table;
		  	 createTable(branchy);
		}else{
			nodataHightChart();
		}
	  	//createTable(branchy);
	  });

}

</script>


    

<div id="searchContainer" class="search">
            <label>组织</label>
            <select id ="orgId">
                		<option value="2">德令哈分公司</option>
                		<option value="3">格尔木分公司</option>
                		<option value="4">海北分公司</option>
                		<option value="5">海南分公司</option>
                </select>
                <label>时间</label><input id="para" class="Wdate" style="margin-top:8px;" type="text" size="12" onfocus="WdatePicker({skin:'default',dateFmt:'yyyy'})"/>
                <button onclick="query($('#orgId').val(),$('#para').val())">查询</button>  
     </div>
	<div id="container" style="width: 98%; height:360px; margin:auto"></div>	
	 <div id="tableContainer"  class="table" style="margin-top:20px; margin-left:12px">
	 	<table  id="table">
										
		</table>
</div>

	</body>
</html>