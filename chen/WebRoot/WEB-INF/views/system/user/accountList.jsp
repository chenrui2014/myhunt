<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="${ctx}/static/js/jquery/jquery.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/My97DatePicker/WdatePicker.js"></script>
<script type="text/javascript" src="${ctx}/static/js/page.js"></script>
<script type="text/javascript" src="${ctx}/static/js/userAccount/account.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript">
$(document).ready(function() {
	//获取每页显示条数
	var pageNum = 1;//$("#");//获取页号
	loadData(pageNum );//加载数据
	
});
function loadData(pageNum ){
	var pageSize = $("#pageSize").val();
 	$.ajax({   
		type:"POST",
		url:"${ctx}/user/accountList",
		data:{page:pageNum,pagesize:pageSize},
		dataType:"json",
		success:function(json){
			//$("#totalNum").val(json.total);
			
			var str = "<table border= 1 cellpadding=0 cellspacing=0 style='width: 1000px;'><tr><th>Id</th><th>用户Code</th><th>姓名</th><th>登录名</th><th>email</th><th>电话</th><th>管理</th><th>管理</th></tr>";
			$.each(json.rows, function(i,item){
				str += "<tr align='center' > <td> "
					+ item.userID + "</b>" + "</td> <td> "
					+ item.userCode + " </td>" + " <td> "
					+ item.userName + " </td><td> "
					+ item.loginName + " </td><td> "
					+ item.email + " </td><td> "
					+ item.phone + " </td> <td>"+
					"<a href='${ctx}/user/update/"+item.userID+"'>编辑</a></td>"+
					"<td><a href='${ctx}/user/delete/"+item.userID+"'>删除</a></td></tr>";
			});
			str += "</table>";
			$("#tableData").html(str);
			var allPageNum = Math.ceil(json.total/pageSize);
			//加载页码
			setPage({
				pageDivId:'page',
				showPageNum:pageSize, //显示的个数
				allPageNum:allPageNum, //总页数
				curpageNum:pageNum //当前页数
			});	
    	},
    	error : function(data) {   
    		alert("请求出错");
      }   
  });
}	
</script>
<style type="text/css">
	a{
		margin:0 10px 0 0;		
	}
</style>
<title>用户列表</title>
</head>
<body>
<input type= "hidden" id="totalNum" />
<div id="container">
	<form action="#" id="accountQueryForm" name="accountQueryForm" method="post">
        	<input type="hidden" name="userId" id="userId" />
        	<div  class="search" style="background-color: #FFF;margin:0">
        		<div id="userSearchParam">
                	<p>用户名 <input type="text" id="LIKE_userName" name="LIKE_userName"/></p>
				</div>               
                <div class="button01" style="margin-left: 0;">
                    <ul>
                        <li style="float: left"><a href="${ctx}/user/create">新增用户</a></li>
                        <li style="float: right;margin-right: 150px"><a href="#" id="doQueryTemList">查询</a></li>
                    </ul>
                </div>
            </div>
            </form>
</div>
<div id="tableData">
</div>
<div>
	<div id="page" style="float:left"></div>
	<div style="float:left;">
		<select id="pageSize" onchange="loadData(1)" >
			<option value="5" selected="selected">5</option>
			<option value="10">10</option>
		</select>
	</div>
</div>


</body>
</html>