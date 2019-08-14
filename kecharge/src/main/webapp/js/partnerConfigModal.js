var passwordMd5="";
$(function() {
	formCheck();
	initSelect();
})

function formCheck() {
	$('#configForm').validationEngine('attach', {
		promptPosition : 'topRight',
		scroll : false
	});
}

//初始化下拉框
function initSelect() {
	$(".partnerName").empty();
	$.post("manage/listOperator.json", "", function(data) {
		if (data.status == "0") {
			var jsonData = data.rows
			var html = "<option value='-1'>全部</option>";
			for ( var k = 0; k < jsonData.length; k++) {
				html += "<option value='" + jsonData[k].partnerId + "'>"
				+ jsonData[k].partnerName + "</option>";
			}
			$(".partnerName").append(html);
			initPage();
		} else {
			alert(data.error)
		}
	})
}

//初始化界面
function initPage() {
	if (eidtType == "add") {

	} else {
		$.post("manage/getOperatorConfig.json", {
			id : selectId
		}, function(data) {
			if (data.status == "0") {
				var dataJson = data.data;
				$("#chargeOverUrl").val(dataJson.chargeOverUrl);
				$("#DCChargeInfoUrl").val(dataJson.chargeDcInfoUrl);
				$("#chargeStartUrl").val(dataJson.chargeStartUrl);
				$("#prefix").val(dataJson.serialnumberPrefix);
				$("#memberPhone").val(dataJson.memberPhone);
				$("#memberId").val(dataJson.memberId);
				$("#loginUrl").val(dataJson.loginUrl);
				$(".partnerName").val(dataJson.id).attr("disabled",true);
				$("#operator").val(dataJson.id);
				$("#operatorId").val(dataJson.id);
				$("#selectId").val(dataJson.id);
				$("#operatorLoginname").val(dataJson.operatorLoginname);
				$("#password").val(dataJson.password);
				$(".useFlag").val(dataJson.useFlag);
				$("#username").val(dataJson.username);
				$("#token").val(dataJson.token);
				passwordMd5=dataJson.password;
				$("#password").change(function(){
					passwordMd5 = hex_md5($("#password").val());
				})
			} else {
				alert(data.error)
			}
		})
	}
}

//点击确定提交；
function onOk() {
	if (!$('#configForm').validationEngine("validate")) {
		return;
	} else {
		if (eidtType == "add") {
			var password = hex_md5($("#password").val());
			$("#password").val(password);
			$("#operatorId").val($(".partnerName").val());
			updataConfig("manage/insertOperatorConfig.json");
		} else {
			$("#selectId").val(selectId);
			$("#password").val(passwordMd5);
			cookie.set("gridRowId",selectId);
			updataConfig("manage/updateOperatorConfig.json");
		}
	}
}
//添加或是修改数据
function updataConfig(urlPath) {
	var $configForm = $("#configForm");
	$configForm.ajaxSubmit({
        url:urlPath,
        type:'POST',
        dataType:'json',
        success: function(data){         //提交成功后
            if(data.status=="0"){
               modalUtil.hide();
			   gridCommon.reload(false);
            }else{
                alert(data.error)
            }
        },
    });
}








