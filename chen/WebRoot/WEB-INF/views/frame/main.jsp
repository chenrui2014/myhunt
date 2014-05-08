<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Frameset//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-frameset.dtd">
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
<title>光伏电站群综合监控运营管理系统平台</title>
<title>huntoms</title>

</head>
<frameset id="allframeset" name="allframeset" rows="70,25,*,40" cols="*" frameborder="no" border="0" framespacing="0">
  <frame src="${ctx}/frame/f-top.jsp" name="topFrame" scrolling="no" noresize="noresize" id="topFrame" title="topFrame" />
  <frame src="${ctx }/frame/menubar.jsp" name="menuFrame" scrolling="no" noresize="noresize" id="menuFrame" />
  <frameset cols="0,0,*" frameborder="no" border="0" framespacing="0" name="midelSet" id="midelSet">
    <frame src="${ctx }/frame/f-leftmenu.jsp" name="leftFrame" scrolling="no" noresize="noresize" id="leftFrame" title="leftFrame" />
  	<frame src="${ctx }/frame/middle.jsp" name="midelFrame" id="midelFrame" />
  	<frame src="${ctx }/home/homePage" name="mainFrame" id="mainFrame" title="mainFrame" />
  </frameset>
  <frame src="${ctx }/frame/f-footer.jsp" name="bottomFrame" scrolling="no" noresize="noresize" id="bottomFrame" />
</frameset>
<noframes>
<body>
</body>
</noframes>
</html>
