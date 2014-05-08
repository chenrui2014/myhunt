function nodataHightChart(){
	var chart = new Highcharts.Chart(
			{
			   chart: {
				     renderTo: 'container',
		             backgroundColor: '#F1FBFB'
		         },
			   
		        title: {  
		            text: '没有相关数据显示'  
		        },  
	 			exporting:{
	 				enabled :false
	 			},
		        credits: {
		             enabled: false
		         } ,
		         xAxis: {
			            categories: []
			        },
			     yAxis: {
			        	min: 0,
						lineWidth: 1.0,
						lineColor: '#CCD4D4',
						gridLineColor: '#CCD4D4',
						gridLineDashStyle: 'dash',
		                title: {
		                    text: '无数据'
		                },
		                labels: {
		                    formatter: function() {
		                        return this.value ;
		                    }
		                },
		                plotLines: [{
		                    value: 0,
		                    width: 1,
		                    color: '#808080'
		                }]
			        },
		        series: [{  
		            name: '无显示数据',    
		            data: []       
		        }] 
			}
	
	);
}


function commObject(name_,data_){
	this.name = name_;
	this.data = data_;
}
