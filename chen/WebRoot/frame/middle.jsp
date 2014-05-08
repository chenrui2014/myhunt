<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>NavigationHide</title>

<style type="text/css">
<!--
body {
	margin-left: 0px;
	margin-top: 0px;
	background: #F7FFEF;
	border-right: #ccc 1px solid;
}
-->
</style>

</head>

<script language="JavaScript" type="text/javascript">
	function ChangeVisible() {
		if (parent.document.getElementById('midelSet').cols != "0,8,*")
		{//关
			parent.document.getElementById('midelSet').cols = "0,8,*";
			document.all.menuSwitch.innerHTML = "<a href='#' onclick='ChangeVisible();'><img src='${ctx}/static/images/top/pic24.gif' width='8' height='51' border='0'></a>";
		}
		else
		{//开
			parent.document.getElementById('midelSet').cols = "220,8,*";
			document.all.menuSwitch.innerHTML = "<a href='#' onclick='ChangeVisible();'><img src='${ctx}/static/images/top/pic23.gif' width='8' height='51' border='0'></a>";
		}
	}
</script>

<body height="100%">
	<table width="5" height="100%" border="0" cellpadding="0"
		cellspacing="0" bgcolor="#eeeeee">
		<tr>
			<td valign="middle" id="menuSwitch"><a href="#"
				onClick="ChangeVisible();"><img id="middleImg" name="middleImg" src="${ctx}/static/images/top/pic24.gif" width="8" height="51" border="0"></a></td>
		</tr>
	</table>

</body>

</html>
