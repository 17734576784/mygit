<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>NbWater</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="components/bootstrap-datepicker/css/bootstrap.min.css">
	
	
	<script type="text/javascript" src="js/common/jquery-2.0.3.min.js"></script>
	<script type="text/javascript" src="js/common/jquery.form.js"></script>
	<script type="text/javascript" src="js/login.js"></script>
	<script src="js/common/md5.js"></script>

  </head>
  <body>
    <div class="page-container" style="background-image:url(images/bigBackground.jpg)">
		<div class="main_box">
			<div class="login_box">
				<div class="login_logo">
					<img src="images/ke_logo.png" >
				</div>
			
				<div class="login_form">
					<form  id="login_form" onsubmit="return login();">
						<div class="form-group">
							<label for="j_username" class="t">用户名：</label> 
							<input id="userName" value="" name="userName" type="text" class="form-control x319 in" 
							autocomplete="off">
						</div>
						<div class="form-group">
							<label for="j_password" class="t">密　码：</label> 
							<input id="password" value="" name="password" type="password" 
							class="password form-control x319 in">
						</div>
						<div class="form-group">
							<label class="t"></label>
							<label for="j_remember" class="m">
							<input id="j_remember" type="checkbox" value="true">&nbsp;记住登陆账号!</label>
						</div>
						<div class="form-group space" align="left">
							<label class="t"></label>　　　
							<button type="button"  id="submit_btn" class="btn btn-primary btn-lg">&nbsp;登&nbsp;录&nbsp </button>
							<input type="reset" value="&nbsp;重&nbsp;置&nbsp;" class="btn btn-default btn-lg">
						</div>
					</form>
				</div>
			</div>
			<div class="bottom">Copyright &copy; 2018-2020 <a href="#">系统登陆</a></div>
		</div>
	</div>
	<link rel="stylesheet" href="css/login.css">
	<script src="js/login.js"></script>
  </body>
</html>
