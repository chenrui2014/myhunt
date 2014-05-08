<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>HUNTERMAIL</title>
<link rel="stylesheet" href="${ctx }/static/css/style.css"
	type="text/css" />
<style type="text/css">
<!--
* {
	padding: 0px;
	margin: 0px;
}

-->
ul li a img {
	border: none;
}

#tabs2 {
	position: absolute;
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
	border: 0px;
}

#tabs2 a {
	float: left;
	padding: 0 0 0 0px;
	text-decoration: none;
	border: 0px;
}

#tabs2 a span {
	display: block;
	padding: 0px 0px 0px 0px;
	color: #fff;
}

a:link {
	color: #fff;
	text-decoration: none;
	border: 0px;
}

.next {
	margin-top: 10px;
}

.border {
	border: #88c4ee 1px solid;
	font-size: 12px;
	line-height: 20px;
	padding: 10px;
	background: url(${ctx }/static/images/top/di22.jpg);
	background-position: top;
	background-repeat: repeat-x;
}

.border_left {
	border: #88c4ee 1px solid;
	font-size: 12px;
	line-height: 20px;
	padding: 10px;
	background-color: #e2f3ff;
	background-image: url(${ctx }/static/images/top/di44.jpg);
	background-position: top;
	background-repeat: repeat-x;
}

.border span {
	color: red;
	text-decoration: none;
}

.border span a {
	color: red;
	text-decoration: underline;
}

.border span a:hover {
	color: #3399FF;
	text-decoration: underline;
}

img {
	border: none;
}

.back_img {
	height: 25px;
	background: url(${ctx }/static/images/top/di11.jpg);
	background-position: left;
	background-repeat: no-repeat;
	font-size: 12px;
	color: #FFFFFF;
	line-height: 25px;
	padding-left: 10px;
	font-weight: bold
}

.back_img01 {
	height: 25px;
	background-image: url(${ctx }/static/images/top/di33.jpg);
	background-position: left;
	background-repeat: no-repeat;
	font-size: 12px;
	color: #FFFFFF;
	line-height: 25px;
	padding-left: 10px;
	font-weight: bold;
}

.user1a {
	width:120px;
	padding-left:0px;	
	padding-top: 15px;
	margin-left:3px !important;
	*margin-left:10px;
	line-height:25px;
	white-space:nowrap;
	background:url(../images/png1.png) no-repeat left center;
	float:left;
	overflow:hidden;
	}
	
</style>
</head>
<script type="text/javascript">
	/* function logout(){
		location.href = "${ctx}/login/logout";
	} */
</script>
<body>
	<%-- <div class="top">
	<div class="logo1">
    </div>
    <div class="logoright">
    	<div class="user1">
        	<div class="user1a" >
            	当前用户：admin</div><div class="user1b">
           		<a href="${ctx }/login/logout" target="_top">退出</a>
            </div>
        </div>
        <div class="user2">
        	<div class="user2a">
            	<a href="#" target="mainFrame">修改密码</a>
            </div>
        </div>
    </div>
</div> --%>
	<table width="100%" border="0" cellpadding="0" cellspacing="0"
		background="${ctx}/static/images/top1/top.jpg">
		<tr>
			<td height="70"><img src="${ctx }/static/images/top1/top_zi.jpg"
				width="744" height="70" /></td>
			<td width="300" align="right" valign="middle"
				background="${ctx}/static/images/top1/top_right.jpg">
				<div class="dock" id="dock">
					<span class="user1a">欢迎您：${loginUser.userName}</span>
						<a href="javascript:editPassword();"><span>修改密码</span><img
							style="padding-top: 16px; margin-right: 5" height="13" width="15"
							src="${ctx }/static/images/top1/modpass.gif" alt="修改密码" /></a> <a
							href="#" onclick="#"><span>安全退出</span><img
							src="${ctx }/static/images/top1/colse.gif" alt="安全退出" height="13"
							width="15" /></a>
					</div>
			</td>
		</tr>
	</table>
</body>
</html>
