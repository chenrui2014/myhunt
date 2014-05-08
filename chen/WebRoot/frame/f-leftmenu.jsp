<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>HUNTERMAIL</title>
<link rel="stylesheet" href="${ctx}/static/js/zTree/css/demo.css" type="text/css" />
<link rel="stylesheet" href="${ctx}/static/js/zTree/css/zTreeStyle/zTreeStyle.css" type="text/css" />
<script type="text/javascript" src="${ctx}/static/js/zTree/js/jquery-1.4.4.min.js"></script>
<script type="text/javascript" src="${ctx}/static/js/zTree/js/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript">
<!--
	var curMenu = null, zTree_Menu = null;
	var setting = {
		view : {
			showLine : false,
			selectedMulti : false,
			dblClickExpand : false
		},
		data : {
			simpleData : {
				enable : true
			}
		},
		callback : {
			onNodeCreated : this.onNodeCreated,
			beforeClick : this.beforeClick,
			onClick : this.onClick
		}
	};

	function beforeClick(treeId, node) {
		if (node.isParent) {
			if (node.level === 0) {
				var pNode = curMenu;
				while (pNode && pNode.level !== 0) {
					pNode = pNode.getParentNode();
				}
				if (pNode !== node) {
					var a = $("#" + pNode.tId + "_a");
					a.removeClass("cur");
					zTree_Menu.expandNode(pNode, false);
				}
				
				a = $("#" + node.tId + "_a");
				a.addClass("cur");

				var isOpen = false;
				for ( var i = 0, l = node.children.length; i < l; i++) {
					if (node.children[i].open) {
						isOpen = true;
						break;
					}
				}
				if (isOpen) {
					zTree_Menu.expandNode(node, true);
					curMenu = node;
				} else {
					zTree_Menu.expandNode(node.children[0].isParent ? node.children[0] : node, true);
					curMenu = node.children[0];
				}
			} else {
				zTree_Menu.expandNode(node);
			}
		}
		return !node.isParent;
	}

	function onClick(e, treeId, node) {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo");
		zTree.expandNode(node);

		//alert("id:" + node.id + ",pid：" + node.pId+ ",url：" + node.userUrl+",resparam："+node.resparam);
		var href = node.userUrl;
		if(node.resparam!=null&&node.resparam.length>0){
			window.open(href+"/"+node.id, 'mainFrame');
		}else{
			window.open(href, 'mainFrame');
		}
	}
	var zNodes;
	function reloadMenu(pid) {
		$.ajax({
			type : 'POST',
			dataType : "json",
			data:{"pid":pid},
			url : '${ctx}/resource/getChildMenu',
			success : function(data) {
				zNodes = JSON.parse (data.childMenu);
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				zTree_Menu = $.fn.zTree.getZTreeObj("treeDemo");
				curMenu = zTree_Menu.getNodes()[0];//.children[0].children[0]
				zTree_Menu.selectNode(curMenu);
				var a = $("#" + zTree_Menu.getNodes()[0].tId + "_a");
				a.addClass("cur");
			},
			error : function() {
				alert("请求失败");
			}
		});
	}

//; background-color: #b5e61d-- background-color: #dcf789;>
</script>
<style type="text/css">
	/*.ztree li a.level0 {width:200px;height: 20px; text-align: left; display:block;background:url(${ctx}/static/images/img/afterclick.jpg); border:0px silver solid;}
	.ztree li a.level0.cur { background:url(${ctx}/static/images/img/beforclick.jpg);}
	.ztree li a.level0 span.button {	float:right; margin-left: 10px; visibility: visible;display:none;}
	.ztree li a.level0 span {display: block; color: white; padding-top:3px; font-size:12px; font-weight: bold;word-spacing: 2px;}
	.ztree li span.button.switch.level0 {display:none;}
	 .ztree li ul{ margin:0; padding:0}
	 */
	
	.ztree{margin:0; padding:0;height:100%}
	.ztree li a:hover {text-decoration:none;}
	/*调整菜单项样式  width 调整菜单图片的宽度*/
	.ztree li a.level0 {width:201px;height: 23px; text-align: center; display:block; background:url(${ctx}/static/images/img/icon04.gif); border:0px silver solid;}
	.ztree li a.level0.cur {background:url(${ctx}/static/images/img/icon05.gif); }
	.ztree li a.level0 span {display: block; color: black; padding-top:0px; font-size:12px; font-weight: bold;word-spacing: 2px;}
	.ztree li a.level0 span.button {	float:right; margin-left: 0px; visibility: visible;display:none;}
	.ztree li span.button.switch.level0 {display:none;}
	
</style>
</head>

<body >
 	<div style="scrolling='no';margin-top: 0px;margin-left: 0px"><!-- class="zTreeDemoBackground left" style="width:240px;background-color:#f0f6e4"    style="height:100%;"-->
		<ul id="treeDemo"  class="ztree" style="margin-top: 0px"></ul>
	</div>
</body>
</html>
