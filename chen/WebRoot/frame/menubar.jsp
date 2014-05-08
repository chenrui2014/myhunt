<%@ page language="java" pageEncoding="UTF-8" contentType="text/html;charset=utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<META http-equiv="pragma" content="no-cache">
		<META http-equiv="Cache-Control" content="no-cache">
		<script src="${ctx}/static/js/jquery/jquery.min.js" type="text/javascript"></script>
		<title>top</title>
		<style type="text/css">
			<!--
			* {
				padding: 0px;
				margin: 0px;
			}
			-->
			#tabs1 {
			    position:absolute;
				font-size: 12px;
				line-height: 23px;
			}
			
			#tabs1 ul {
				margin: 0;
				padding: 0px;
				list-style: none;
			}
			
			#tabs1 li {
				display: inline;
				margin: 0px;
				padding: 0px;
			}
			
			#tabs1 a {
				float: left;
				background: url("${ctx }/static/images/tableft1.gif") no-repeat left top;
				padding: 0 0 0 4px;
				text-decoration: none;
			}
			
			#tabs1 a span {
				display: block;
				background: url("${ctx }/static/images/tabright1.gif") no-repeat right top;
				padding: 0px 15px 0px 6px;
				color: #fff;
			}
			
			
			#tabs2 {
			    position:absolute;
				font-size: 12px;
				line-height: 23px;
			}
			
			#tabs2 ul {
				margin: 0;
				padding: 0px;
				list-style: none;
			}
			
			#tabs2 li {
				display: inline;
				margin: 0px;
				padding: 0px;
			}
			
			#tabs2 a {
				float: left;
				background: url("${ctx }/static/images/tableft1.gif") no-repeat left top;
				padding: 0 0 0 4px;
				text-decoration: none;
			}
			
			#tabs2 a span {
				display: block;
				background: url("${ctx }/static/images/tabright1.gif") no-repeat right top;
				padding: 0px 15px 0px 6px;
				color: #fff;
			}
		</style>
		<script type="text/javascript">
		
			$(document).ready(function(){
		      	 $.ajax({
					   type : 'POST',
					   dataType : "json",
					   data : {"pid":1},
					   url : '${ctx}/resource/getMenuBar',
					   success: function(data){
						  var tabs = eval(data.menuBar);
						  var tLen = tabs.length;
					   	  if(tLen!=0){
					   	     var i=0;
						     var htmlStr = "<ul>";
						    	for(var ti=0;ti<tLen;ti++){
						    		htmlStr +="<li style='cursor: hand;'>"+
													"<a target='leftFrame'><span class='white' onclick='clickEvent(\""+tabs[ti].url+"\",this,\""+i+"\",\""+i+"\",\""+tabs[ti].id+"\")'>"+tabs[ti].name+"</span>"+
													"</a>"+
											   "</li>";
						    		i=i+1;		   
						    	}
						    	htmlStr += "</ul>";
						    	$("#tabs1").html(htmlStr);
						    	clickEvent("",this,0,0,2);
						   }	
					   }
				 }); 
			});
		    
			function clickEvent(url,e,i,type,id){
			 	//首页
			 	//var midelImg = parent.midelSet.midelFrame.document.getElementById('middleImg');
				if(type==0){
					if (parent.document.getElementById('midelSet').cols != "0,0,*")//状态为开
					{//关
						parent.midelSet.midelFrame.document.getElementById('middleImg').src=parent.midelSet.midelFrame.document.getElementById('middleImg').src.replace("pic23.gif","pic24.gif");
						parent.document.getElementById('midelSet').cols = "0,0,*";
					}
				}else{//其他按钮
					if (parent.document.getElementById('midelSet').cols == "0,0,*")
					{//关
						parent.document.getElementById('midelSet').cols = "220,8,*";
						parent.midelSet.midelFrame.document.getElementById('middleImg').src=parent.midelSet.midelFrame.document.getElementById('middleImg').src.replace("pic24.gif","pic23.gif");
					}
					parent.midelSet.leftFrame.reloadMenu(id);
				}
			    setSpan();
			    $("ul li a").get(i).style.background="url('${ctx }/static/images/tableft1.gif') no-repeat left bottom";
			    $("ul li a span").get(i).style.background="url('${ctx }/static/images/tabright1.gif') no-repeat right bottom";
			}
			
			function setSpan(){
			    $("ul li a").css("background","url('${ctx }/static/images/tableft1.gif') no-repeat left top");
			    $("ul li a span").css("background","url('${ctx }/static/images/tabright1.gif') no-repeat right top");
			}
			
			function wincls(i){
				if(window.confirm('\u60a8\u786e\u5b9a\u8981\u9000\u51fa\u5417\uff1f') == 1){
					parent.window.close();
				}
			}
			
			function logout(){
				parent.window.location = "<%=path%>/login.jsp";
			}
			
			function editPassword(i){
				var url="";
				window.showModalDialog(url,window,"dialogHeight:270px;dialogWidth:320px;toolbar=no;menubar=no;scrollbars=no;scroll=no;resizeable=no;location=no;status=no" ); 
			}
			function resizeWin(obj){
				if(parent.document.getElementById('allframeset').rows == "70,25,*,40"){//最大化
					parent.allframeset.rows= "0,25,*,0";
					document.images["img"].src=document.images["img"].src.replace("min.gif","max.gif");
				}else{
					parent.allframeset.rows= "70,25,*,40";
					document.images["img"].src=document.images["img"].src.replace("max.gif","min.gif");
				}
				
			
			}
		</script>
	</head>
	<body>
		<div style="width: 100%; height: 25px; background: url(${ctx }/static/images/topdi1.jpg); padding-top:2px;">
			<div style="width: 225px; height: 25px; line-height: 25px; font-size: 12px; padding-left: 0PX; float: left;">
				<img style="width: 225px; height: 25px;"src="${ctx }/static/images/left_d.png"  style="cursor: pointer;"  />
			</div>
			<div id="tabs1" style="position:float;left: 250px;top: 1px;"align="right"></div>
			<div style="position: absolute;right: 10px;top: 5px;" id="menutool" align="right">
				<img id="img" onClick="resizeWin(this);" src="${ctx }/static/images/min.gif"  style="cursor: pointer;"  />
			</div>
		</div>
	</body>
</html>
