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
    <link rel="stylesheet" href="css/dataQuery.css" type="text/css"></link>
    <script type="text/javascript">
    	var basePath='<%=basePath%>'
    </script>
</head>
  <body class="bgColor">
	<div class="searchDiv">
		<label>运营商：</label>
		<select id="partnerId">
		</select>
	</div>
	<div>
		<div class="numSearch">
			<label>查询条件：</label>
			<input type="text" class="date" placeholder="查询条件" id="serialnumber"/>	
		</div>	
		<div class="input-daterange input-group">
			<label>开始时间：</label>
			<input type="text" class="input-sm form-control" name="start"  id="beginDate"/>
			<label>结束时间：</label>
			<input type="text" class="input-sm form-control" name="end" id="endDate" />
			
		</div>
		<div class="searchBtn">
			<button  class="searchData btn btn-success" type="button" onClick="searchData()">搜索</button>
		</div>
	</div>
	<div style="clear:both"></div>
	<table id="gridTable"></table> 
    <script type="text/javascript" src="js/dataQuery.js"></script>
  </body>
</html>
