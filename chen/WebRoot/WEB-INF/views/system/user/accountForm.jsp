<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${ctx}/static/css/CSSstyle.css" rel="stylesheet" media="all" type="text/css" />
<link href="${ctx}/static/css/reset.css" rel="stylesheet" media="all" type="text/css" />
<link href="${ctx}/static/css/htmil.css" type="text/css" rel="stylesheet" />

<link href="${ctx}/static/js/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<script src="${ctx}/static/js/jquery/jquery.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<title>用户管理</title>

</head>
<body>
<div style="margin-left: 200px;margin-top: 20px">
	<form id="inputForm" action="${ctx}/account/${action}" method="post" class="form-horizontal">
		<input type="hidden" name="userID" value="${user.userID}" />
		<fieldset>
			<legend>
				<small>用户管理</small>
			</legend>
			<div class="control-group">
				<label class="control-label">登录名:</label>
				<div class="controls">
					<input type="text" name="loginName" value="${user.loginName}"
						class="input-large required" />
				</div>
			</div>
			<div class="control-group">
				<label class="control-label">用户名:</label>
				<div class="controls">
					<input type="text" id="userName" name="userName"
						value="${user.userName}" class="input-large required" />
				</div>
			</div>
			<div class="control-group">
				<label for="password" class="control-label">密码:</label>
				<div class="controls">
					<input type="password" id="password" name="password"
						class="input-large" placeholder="...Leave it blank if no change" />
				</div>
			</div>
			<div class="control-group">
				<label for="confirmPassword" class="control-label">确认密码:</label>
				<div class="controls">
					<input type="password" id="confirmPassword" name="confirmPassword"
						class="input-large" equalTo="#plainPassword" />
				</div>
			</div>
			<div class="control-group">
				<label for="userCode" class="control-label">用户编码:</label>
				<div class="controls">
					<input id="userCode" name="userCode" value="${user.userCode}"
						type="text" value="" class="userCode" />
				</div>
			</div>
			<div class="control-group">
				<label for="phone" class="control-label">电话:</label>
				<div class="controls">
					<input type="text" id="phone" name="phone" value="${user.phone}"
						class="input-large" />
				</div>
			</div>
			<div class="control-group">
				<label for="phone" class="control-label">Email:</label>
				<div class="controls">
					<input id="email" name="email" value="${user.email}" type="text"
						class="email" />
				</div>
			</div>

			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit"
					value="提交" />&nbsp; <input id="cancel_btn" class="btn"
					type="button" value="返回" onclick="history.back()" />
			</div>
		</fieldset>
	</form>
	</div>
	<script>
		$(document).ready(
				function() {
					jQuery.validator.addMethod("isPhone", function(value,
							element) {
						var length = value.length;
						var mobile = /^(((13[0-9]{1})|(15[0-9]{1}))+\d{8})$/;
						var tel = /^\d{3,4}-?\d{7,9}$/;
						return this.optional(element)
								|| (tel.test(value) || mobile.test(value));

					}, "请正确填写您的联系电话");

					jQuery.validator.addMethod("isLoginNameSame", function(value, element) { //用jquery ajax的方法验证登录名是不是已存在  
						var flag = 1;
						$.ajax({
							type : "POST",
							url : '${ctx}/account/checkLoginName',
							async : false, //同步方法，如果用异步的话，flag永远为1  
							dataType : "html",
							data : {
								'loginName' : value
							},
							success : function(msg) {
								if (msg == 'false') {
									flag = 0;
								}
							}
						});

						if (flag == 0) {
							return false;
						} else {
							return true;
						}

					}, "登录名已存在");
					jQuery.validator.addMethod("checkUserCode", function(value, element) { //用jquery ajax的方法验证登录名是不是已存在  
						var flag = 1;
						$.ajax({
							type : "POST",
							url : '${ctx}/account/checkUserCode',
							async : false, //同步方法，如果用异步的话，flag永远为1  
							dataType : "html",
							data : {
								'userCode' : value
							},
							success : function(msg) {
								if (msg == 'false') {
									flag = 0;
								}
							}
						});

						if (flag == 0) {
							return false;
						} else {
							return true;
						}

					}, "用户编号已存在");
					
					$("#inputForm").validate({
						rules : {
							loginName : {required : true,
								isLoginNameSame:true
							},
							userName : "required",
							email : {
								required : true,
								email : true
							},
							phone : {
								required : true,
								isPhone : true
							},
							password : {
								required : true,
								minlength : 5
							},
							confirmPassword : {
								required : true,
								minlength : 5,
								equalTo : "#password"
							},
							userCode : {
								required : true,
								rangelength : [10,10]  ,
								isUserCodeSame:true
							}
						},
						messages : {
							loginName : {required : "请输入登陆名",
								isLoginNameSame : "登陆名重复"
							},
							userName : "请输入姓名",
							email : {
								required : "请输入Email地址",
								email : "请输入正确的email地址"
							},
							phone : {
								required : "请输入您的联系电话",
								isPhone : "请输入一个有效的联系电话"
							},
							password : {
								required : "请输入密码",
								minlength : jQuery.format("密码不能小于{0}个字 符")
							},
							confirm_password : {
								required : "请输入确认密码",
								minlength : "确认密码不能小于5个字符",
								equalTo : "两次输入密码不一致不一致"
							},
							userCode : {required : "请输入用户编号",
								rangelength :"长度为10",
								isUserCodeSame : "用户编号重复"
							}
						}
					});
				});
	</script>
</body>
</html>
