<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>用户登录</title>
<link href="${ctx}/static/css/login.css" type="text/css"
	rel="stylesheet" />
<script type="text/javascript">
	function formSubmit() {
		document.forms[0].submit();
	}
	function butOnClick() {
		if (event.keyCode == 13) {
			document.forms[0].submit();
		}
	}
</script>
</head>

<body>
	<form id="loginForm" name="loginForm" action="${ctx}/login"
		method="post">
		<div class="loginbody">
			<div class="loginbox">
				<div class="username">
					<span>用户名:</span> <span><input onfocus="clearErrorInfo()"
						type="text" id="username" name="username" value="${loginName}"
						class="input-medium required" /></span> <span class="red12"
						id="loginNameError"> </span>
				</div>
				<div class="password">
					<span>密&nbsp;&nbsp;码:</span> <span><input type="password" id="password" 
						name="password" class="input-medium required" /></span> <span
						class="red12" id="loginPwdError"> </span>
				</div>

				<div class="loginbotton">
					<span><input id="submit_btn" class="btn btn-primary"
						type="submit" value="登录" /></span> <span id="errorInfo"
						style="color: red; font-size: 18px;"> ${errorMsg} </span>
				</div>
			</div>
			<!-- <div class="copyright">
		    	<span>CopyRight2011  版权所有  北京金鸿泰科技有限公司</span>
		    </div> -->
		</div>
	</form>
</html>
