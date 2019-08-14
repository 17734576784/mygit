<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>数据查询界面</title>
    <link rel="stylesheet" href="css/partnerConfig.css" type="text/css"></link>
    <script type="text/javascript">
    	var basePath='<%=basePath%>'
    </script>
</head>
  <body class="bgColor">
	<div class="searchDiv">
		<label>合作伙伴：</label>
		<input type="text" class="date" placeholder="请输入合作伙伴名称..." id="partnerNameSearch"/>	
		<label>使用标志：</label>
		<select id="useFlagSearch">
			<option value="-1">全部</option>
			<option value="1">使用</option>
			<option value="0">不使用</option>
		</select>
	</div>
	<div class="searchBtn">
		<button  class="addConfig btn btn-success" type="button" onClick="addConfig()">添加</button>
		<button  class="editConfig btn btn-success" type="button" onClick="editConfig()">修改</button>
		<button  class="deleteConfig btn btn-success" type="button" onClick="deleteConfig()">删除</button>
		<button  class="searchConfig btn btn-success" type="button" onClick="searchConfig()">查询</button>
	</div>
	<div style="clear:both;"></div>
	<table id="gridTable"></table> 
<!--			<div id="gridPager"></div>-->
    <script type="text/javascript" src="js/partnerConfig.js"></script>
  </body>
</html>
