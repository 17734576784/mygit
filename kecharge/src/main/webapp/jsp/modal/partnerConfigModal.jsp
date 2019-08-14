<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>添加/修改参数配置</title>
    <link rel="stylesheet" href="css/partnerConfigModal.css" type="text/css"></link>
    <link rel="stylesheet" href="components/jQuery-Validation-Engine-2.6.2-ciaoca/css/validationEngine.jquery.css">
<!--	<script type="text/javascript" src="js/common/jquery-1.11.3.js"></script>-->
	<script src="components/jQuery-Validation-Engine-2.6.2-ciaoca/js/jquery.validationEngine.js"></script>
	<script src="components/jQuery-Validation-Engine-2.6.2-ciaoca/js/jquery.validationEngine-zh_CN.js"></script>
	
	<script type="text/javascript" src="js/common/jquery.form.js"></script>
<!--	<script type="text/javascript" src="js/common/jquery-1.11.3.js"></script>-->
	<script type="text/javascript" src="js/common/md5.js"></script>
    
    <script type="text/javascript">
		 var eidtType='<%=request.getParameter("eidtType")%>';
		 var selectId='<%=request.getParameter("selectId")%>';
  	</script>
  	<style type="text/css">
  		.modal-util .modal-body{overflow-y:auto;}
  	</style>
</head>
  <body class="bgColor" style='overflow-y:auto;'>
	<div class="mainConfig" style='overflow-y:auto;'>
		<form id="configForm" onsubmit="return false;">
			<input name="id" id="selectId" type="hidden">
			<input name="token" id="token" type="hidden"/>
			<div class="formInput">
				<label for="partnerName"><font>*</font>运营商：</label>
				<select  class="partnerName" name="operator">
				</select>
				<input type="hidden" id="operatorId" name="operatorId">
			</div>
			<div class="formInput">
				<label for=operatorLoginname><font>*</font>运营商登录用户名：</label>
				<input id="operatorLoginname" name="operatorLoginname" type="text" class="validate[required]">
			</div>
			<div  class="formInput">
				<label for="useFlag"><font>*</font>使用标志：</label>
				<select name="useFlag"  class="useFlag">
					<option value="-1">全部</option>
					<option value="1">使用</option>
					<option value="0">不使用</option>
				</select>
			</div>
			<div class="formInput">
				<label for=username><font>*</font>用户名：</label>
				<input id="username" name="username" type="text" class="validate[required]">
			</div>
			<div class="formInput">
				<label for="password"><font>*</font>密码：</label>
				<input id="password" name="password" type="password" class="validate[required]">
			</div>
			<div class="formInput">
				<label for="prefix"><font>*</font>流水号前缀：</label>
				<input id="prefix" name="serialnumberPrefix" type="text" class="validate[required] validate[custom[integer]]]">
			</div>
			<div class="formInput">
				<label for="memberPhone"><font>*</font>会员手机号：</label>
				<input id="memberPhone" name="memberPhone" type="text" class="validate[required] validate[custom[phone]]]">
				<input id="memberId" name="memberId" type="hidden">
			</div>
			<div class="formInput">
				<label for="loginUrl"><font>*</font>回调登录url：</label>
				<textarea id="loginUrl" name="loginUrl"  class="validate[custom[url]]"></textarea>
			</div>
			<div class="formInput">
				<label for="chargeStartUrl"><font>*</font>回调推送充电开始地址：</label>
				<textarea id="chargeStartUrl" name="chargeStartUrl"  class="validate[custom[url]]"></textarea>
			</div>
			<div class="formInput">
				<label for="chargeOverUrl"><font>*</font>回调推送充电结束地址：</label>
				<textarea id="chargeOverUrl" name="chargeOverUrl"  class="validate[custom[url]]"></textarea>
			</div>
			<div class="formInput">
				<label for="DCChargeInfoUrl"><font>*</font>回调推送直流首次充电信息地址：</label>
				<textarea id="DCChargeInfoUrl" name="chargeDcInfoUrl"  class="validate[custom[url]]"></textarea>
			</div>
			<div class="formInput">
				<label for="chargeHeartUrl"><font>*</font>回调推送心跳地址：</label>
				<textarea id="chargeHeartUrl" name="chargeHeartUrl"  class="validate[custom[url]]"></textarea>
			</div>
			<div class="formInput">
				<label for="chargeAlarmUrl"><font>*</font>回调推送告警地址：</label>
				<textarea id="chargeAlarmUrl" name="chargeAlarmUrl"  class="validate[custom[url]]"></textarea>
			</div>
		</form>
	</div>
    <script type="text/javascript" src="js/partnerConfigModal.js"></script>
  </body>
</html>
