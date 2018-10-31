<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>KE Charge</title>
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
	<!--  jqgrid插件    -->
    <script type="text/javascript" src="components/jqgrid/jquery.jqGrid.js"></script>
    <script type="text/javascript" src="components/jqgrid/i18n/grid.locale-en.js"></script>
    <script type="text/javascript" src="js/common/jqgrid-util.js"></script>
	<!--  不带时分秒的时间插件  -->
    <script type="text/javascript" src="components/bootstrap-datepicker/js/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="components/bootstrap-datepicker/js/bootstrap-datepicker.zh-CN.min.js"></script>
    
    
    <script type="text/javascript">
    	var basePath='<%=basePath%>'
    </script>
</head>
  <body class="bgColor">
	<div class="main">
		<div class="topHeader">
			<ul id="navbar">
				<li class="active"><a href="#" data="jsp/login.jsp">接口测试</a></li>
				<li><a href="#" data="jsp/dataQuery.jsp">数据查询</a></li>
				<li><a href="#" data="jsp/partnerConfig.jsp">档案配置</a></li>
			</ul>
		</div>
		<div class="content" id="mainContent">
		</div>
	</div>
    <script type="text/javascript" src="js/main.js"></script>
  </body>
</html>
