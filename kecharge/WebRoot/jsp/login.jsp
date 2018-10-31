<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>接口测试界面</title>
    <link rel="stylesheet" href="css/login.css" type="text/css"></link>
    <script type="text/javascript">
    	var basePath='<%=basePath%>'
    </script>
</head>
  <body class="bgColor">
	<div class="login">
		<label class="caption">登陆:</label>
		<select id="partnerSelect">
			<option value="1">合作伙伴</option>
			<option value="2">小蜗科技</option>
			<option value="3">车航</option>
		</select>
		<button class="loginBtn btn btn-primary" id="partnerLogin" onclick="partnerLogin()">登录合作伙伴</button>
	    <button class="loginBtn btn btn-info"id="keLogin" onclick="keLogin()">登录科林接口</button>
	    <span class="caption token">token：<font id="tokenText"></font></span>
	</div>
	<div class="interfaceOpt">
		<label class="caption">选择接口:</label>		
		<select id="partnerInterfa" onchange="partnerSelectChange(this.value)" disabled>
			<option value="0">请选择小蜗接口</option>
			<option id="" value="1">充电开始信息接口</option>
			<option id="" value="2">充电结束信息接口</option>
		</select>
		<select id="keInterfa" onchange="keSelectChange(this.value)" disabled style="display: none">
			<option value="0">请选择科林接口</option>
			<option id="" value="1" >充电站GPS信息</option>
			<option id="" value="2">充电桩状态信息</option>
			<option id="" value="3">充电开始信息接口</option>
			<option id="" value="4">充电结束信息接口</option>
			<option id="" value="5">充电过程数据信息</option>
			<option id="" value="6">获取充电桩GPS</option>
			<option id="" value="7">获取充电记录</option>
			<option id="" value="8">充电过程实时数据</option>
			<option id="" value="9">获取充电桩费率信息</option>
		</select>
		<button class="loginBtn btn btn-success" id="interfaTest" onclick="interfTest()" disabled>接口测试</button>
		<span class="caption token">url: <font id="urlText"></font></span>
		<div>
			<span class="headerInfo">jsonData:</span>
			<textarea id="jsonDataText" class="textareaStyle"></textarea>
		</div>
		<div>
			<span class="headerInfo">返回信息: </span>
			<textarea  id="msgText" class="textareaStyle"></textarea>
		</div>
	</div>
    <script type="text/javascript" src="js/login.js"></script>
  </body>
</html>
