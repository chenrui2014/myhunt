<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link href="${ctx}/static/css/frame.css" rel="stylesheet" media="all" type="text/css" />
<link href="${ctx}/static/css/reset.css" rel="stylesheet" media="all" type="text/css" />
<link href="${ctx}/static/js/jquery-validation/1.11.1/validate.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" href="${ctx}/static/js/zTree/css/demo.css" type="text/css"/>
<link rel="stylesheet" href="${ctx}/static/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
<script type="text/javascript" src="${ctx}/static/js/zTree/js/jquery-1.4.4.min.js"></script>

<script src="${ctx}/static/js/jquery-validation/1.11.1/jquery.validate.min.js" type="text/javascript"></script>
<script src="${ctx}/static/js/jquery-validation/1.11.1/messages_bs_zh.js" type="text/javascript"></script>
<script type="text/javascript" src="${ctx}/static/js/zTree/js/jquery.ztree.core-3.5.js"></script>

	
<script type="text/javascript">
var setting = {
		view: {
			dblClickExpand: false
		},
		data: {
			simpleData: {
				enable: true
			}
		},
		callback: {
			beforeClick: beforeClick,
			onClick: onClick
		}
	};

	var zNodes;

	function beforeClick(treeId, treeNode) {
		/* var check = (treeNode && !treeNode.isParent);
		if (!check) alert("只能选择城市...");
		return check; */
	}
	
	function onClick(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		nodes = zTree.getSelectedNodes(),
		v = "";
		var pid = "";
		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			pid  += nodes[i].id + ",";
		}
		if (v.length > 0 ) {
			v = v.substring(0, v.length-1);
		}
		if (pid.length > 0 ) {
			pid = pid.substring(0, pid.length-1);
		}
		var cityObj = $("#pmenuSel");
		cityObj.attr("value", v);
		$("#pId").val(pid);
	}

	function showMenu() {
		var cityObj = $("#pmenuSel");
		var cityOffset = $("#pmenuSel").offset();
		$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

		$("body").bind("mousedown", onBodyDown);
	}
	function hideMenu() {
		$("#menuContent").fadeOut("fast");
		$("body").unbind("mousedown", onBodyDown);
	}
	function onBodyDown(event) {
		if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
			hideMenu();
		}
	}

	$(document).ready(function(){
		$.ajax({
			type : 'POST',
			dataType : "json",
			url : '${ctx}/resource/getAllMenu',
			success : function(data) {
				zNodes = JSON.parse (data.tree);
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
			},
			error : function() {
				alert("请求失败");
			}
		});
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
	});
</script>
<title>菜单管理</title>

</head>
<body>
<div style="margin-left: 200px;margin-top: 20px;">
	<form id="inputForm" action="${ctx}/resource/${action}" method="post"
		class="form-horizontal">
		<input type="hidden" name="id" value="${resource.id}" />
		<fieldset>
			<legend>
				<small>菜单管理</small>
			</legend>
			<div class="control-group">
				
				<div class="controls">
					<label class="control-label">菜单名:</label>
					<input type="text" name="name" value="${resource.name}" class="input-large required" />
				</div>
			</div>
			<div class="control-group">
				
				<div class="controls">
				<label class="control-label">菜单链接:</label>
					<input type="text" id="url" name="url" value="${resource.menuUrl}" />
				</div>
			</div>
			<div class="control-group">
				
				<div class="controls">
				<label class="control-label">菜单图标:</label>
					<input type="text" id="icon" name="icon" value="${resource.icon}" />
				</div>
			</div>
			<div class="control-group">
				
				<div class="control-group">
				<label for="open" class="control-label">是否展开:</label>
					<select  id="open" name="open" value="${resource.open}">
					  <option value='1'>是</option>
 					  <option value='0' selected="selected">否</option>
				    </select>
				</div>
			</div>
			<div class="control-group">
				<input id="pId" name="parent.id" type="hidden"  value="${resource.pId}"/>
				<div class="controls">
					<label for="pmenuSel" class="control-label">父节点:</label>
					<input id="pmenuSel" type="text" readonly value="${resource.parentName}" style="width:120px;"/>
					&nbsp;<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a>
				</div>
			</div>
			
			<div id="menuContent" class="menuContent" style="display:none; position: absolute;">
				<ul id="treeDemo" class="ztree" style="margin-top:0; width:160px;"></ul>
			</div>
			<div class="form-actions">
				<input id="submit_btn" class="btn btn-primary" type="submit" value="提交" />&nbsp; 
				<input id="cancel_btn" class="btn" type="button" value="返回" onclick="history.back()" />
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
