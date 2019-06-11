<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>NbWater</title>
    <link rel="stylesheet" href="css/main.css" type="text/css"></link>
    <link rel="stylesheet" href="components/bootstrap-datepicker/css/bootstrap.css" type="text/css"></link>
	<link rel="stylesheet" href="components/jqgrid/css/jquery-ui-1.8.16.custom.css" type="text/css"></link>
	<link rel="stylesheet" href="components/jqgrid/css/ui.jqgrid.min.css" type="text/css"></link>
	<link rel="stylesheet" href="components/jqgrid/css/style_jqgrid.css" type="text/css"></link>
	<link rel="stylesheet" href="components/bootstrap-datepicker/css/bootstrap-datepicker3.css" type="text/css"></link>
    
    <script type="text/javascript" src="js/common/jquery-2.0.3.min.js"></script>  
    <script type="text/javascript" src="js/common/jsonString.js"></script>
    <script type="text/javascript" src="components/bootstrap-datepicker/js/bootstrap.js"></script>
      
    <script type="text/javascript" src="js/common/cookie.js"></script>
    <script type="text/javascript" src="js/common/modal-util.js"></script>
    <script type="text/javascript" src="js/common/messagebox.js"></script>  
    <script type="text/javascript" src="js/common/DateFun.js"></script>
	<script type="text/javascript">
    	var basePath='<%=basePath%>'
    </script>
</head>
  <body class="bgColor">
	<div class="main">
		<div class="topHeader">
			<ul id="navbar">
				<li class="active">线程管理</li>
			</ul>
		</div>
		<div class="content" id="mainContent">
			<h5 class="page-header"></h5>
			<button type="button"  class="btn btn-primary btn-lg" style="width:200px" id="startHistory">开始执行历史库线程</button> 
			<button type="button" class="btn btn-primary" style="width:200px" id="stopHistory">暂停执行历史库线程!</button>
			<h5 class="page-header"></h5>
			<button type="button" class="btn btn-primary" style="width:200px" id="startEvent">开始执行告警事项线程!</button> 
			<button type="button" class="btn btn-primary" style="width:200px" id="stopEvent">暂停执行告警事项线程!</button>
		</div>
	</div>
    <script type="text/javascript" src="js/main.js"></script>
  </body>
</html>
