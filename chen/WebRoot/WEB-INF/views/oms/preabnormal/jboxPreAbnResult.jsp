<%@ page language="java" pageEncoding="UTF-8"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link href="${ctx}/huntoms/static/css/chart.css"  rel="stylesheet"  type="text/css" />
<link href="${ctx}/huntoms/static/css/table.css"  rel="stylesheet"  type="text/css" />
<title>箱变异常预判</title>
<body>
<div id="device" style="width: 98%; height:500px ;margin-left :2%  ; overflow:scroll">
	<img src="${ctx}/huntoms/static/images/jboxPreAbnResult.png"  align="center"/>
</div>
</body>
</html>