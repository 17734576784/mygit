var keusername, kepwd, ptusername, ptpwd;
var partnerInfo;

$(function() {
	initSelect();
	$("#partnerSelect").change(function() {
		clearPage();
	})
})

//初始化下拉框
function initSelect() {
	$("#partnerId").empty();
	$.post("manage/listOperator.json", "", function(data) {
		if (data.status == 0) {
			partnerInfo = data.rows;
			var jsonData = data.rows;
			var html = "";
			for ( var k = 0; k < jsonData.length; k++) {
				html += "<option value='" + k + "'>"
						+ jsonData[k].partnerName + "</option>"
			}
			$("#partnerSelect").append(html);
		} else {
			alert(data.error)
		}
	})
}

//登录合作伙伴	  
function partnerLogin() {
	clearPage();
	var partnerSelect = $("#partnerSelect").val();
	$("#keInterfa").css("display", "none");
	$("#partnerInterfa").css("display", "inline-block");
	partnerLoginSub(partnerSelect);
}
//登录科林
function keLogin() {
	clearPage();
	var partnerSelect = $("#partnerSelect").val();
	$("#keInterfa").css("display", "inline-block");
	$("#partnerInterfa").css("display", "none");
	keLoginSub(partnerSelect);
}

function clearPage() {
	$("#tokenText").text("");
	$("#urlText").text("");
	$("#jsonDataText").val("");
	$("#msgText").val("");
}
//实现合作伙伴登录
function partnerLoginSub(param) {
	$("#interfaTest").attr("disabled", true)
	$("#keInterfa").val(0);
	$("#partnerInterfa").val(0);
	$("#partnerInterfa").css("display", "inline-block");
	$("#keInterfa").css("display", "none")
	var paramJson = {};
	paramJson.userName = partnerInfo[param - 1].loginPartneruserName;
	paramJson.passWord = partnerInfo[param - 1].loginPartnerpassWord;

	$.post("pile/login.json", {
		queryJsonStr : jsonString.json2String(paramJson)
	}, function(data) {
		if (data.status == 0) {
			var msgData = jsonString.json2String(data).replace(/,/g, ",\n");
			$("#msgText").val(msgData)
			$("#tokenText").text(data.token);
		} else {
			var msgData = jsonString.json2String(data).replace(/,/g, ",\n");
			$("#msgText").val(msgData)
		}
	})
	//$("#jsonDataText").val(jsonString.json2String(paramJson).replace(/,/g,",\n"));
	$("#urlText").text("login.json");

	$("#partnerInterfa").attr("disabled", false)
}
//实现科林登录
function keLoginSub(param) {
	$("#interfaTest").attr("disabled", true)
	$("#keInterfa").val(0);
	$("#partnerInterfa").val(0);
	$("#partnerInterfa").css("display", "none");
	$("#keInterfa").css("display", "inline-block");
	var paramJson = {};
	paramJson.userName = partnerInfo[param].loginKEuserName;
	paramJson.passWord = partnerInfo[param].loginKEpassWord;
	//$("#jsonDataText").val(jsonString.json2String(paramJson).replace(/,/g,",\n"));
	$("#urlText").text("login.json");

	$.post("login.json", {
		queryJsonStr : jsonString.json2String(paramJson)
	}, function(data) {
		if (data.status == 0) {
			var msgData = jsonString.json2String(data).replace(/,/g, ",\n");
			$("#msgText").val(msgData)
			$("#tokenText").text(data.token);
		} else {
			var msgData = jsonString.json2String(data).replace(/,/g, ",\n");
			$("#msgText").val(msgData)
		}
	})
	$("#keInterfa").attr("disabled", false)
}

function partnerSelectChange(value) {
	$("#interfaTest").attr("disabled", false);
	$("#msgText").val("");
	var partnerInterfa = value;
	if (partnerInterfa == 0) {
		$("#interfaTest").attr("disabled", true)
		$("#urlText").text("");
		$("#jsonDataText").val("");
	} else if (partnerInterfa == 1) {
		interfaFun("pile/chargeStart.json", {
			"serialNumber" : "201601011001",
			"chargeFlag " : "1"
		})
	} else if (partnerInterfa == 2) {
		interfaFun("pile/chargeOver.json", {
			"serialNumber" : "201601011001",
			"pileNo" : "Ke0000000001",
			"gunNo" : "1",
			"startDate" : "2016-01-01 09:10:01",
			"endDate" : "2016-02-01 15:04:02",
			"chargeMoney" : 2.36,
			"totalElectricity" : 10.2,
			"endCause" : "充满"
		})
	} else if (partnerInterfa == 3) {
		interfaFun("pile/chargeRealData.json", {
			"serialNumber" : "2016010110001",
			"remainSecond  " : "0",
			"SOC" : "10"
		})
	}
}

function keSelectChange(value) {
	$("#interfaTest").attr("disabled", false);
	$("#msgText").val("");
	var keInterfa = value;
	if (keInterfa == 0) {
		$("#interfaTest").attr("disabled", true)
		$("#urlText").text("");
		$("#jsonDataText").val("");
	} else if (keInterfa == 1) {
		interfaFun("listStationGPS.json")
	} else if (keInterfa == 2) {
		interfaFun("getPileState.json", {"pileNo" : "KE0000000001"})
	} else if (keInterfa == 3) {
		interfaFun("chargeStart.json", {
			"pileNo" : "KE0000000001",
			"gunNo" : "1",
			"serialNumber" : "9900000001",
			"chargeMoney" : "10"
		})
	} else if (keInterfa == 4) {
		interfaFun("chargeOver.json", {
			"pileNo" : "KE0000000001",
			"gunNo" : "1",
			"serialNumber" : "9900000001"
		})
	} else if (keInterfa == 5) {
		interfaFun("chargeData.json", {"serialNumber" : "9900000001"})
	} else if (keInterfa == 6) {
		interfaFun("getPileGps.json", {"pileNo" : "KE0000000001"})
	} else if (keInterfa == 7) {
		interfaFun("getPileChargeRcd.json", {
			"pileNo" : "KE0000000001",
			"gunNo" : "1",
			"serialNumber" : "9900000001"
		})
	} else if (keInterfa == 8) {
		interfaFun("chargeRealData.json", {"serialNumber" : "9900000001"})
	} else if (keInterfa == 9) {
		interfaFun("getPileRate.json", {"pileNo" : "KE0000000001"})
	} else if (keInterfa == 10) {
		interfaFun("listPileInfo.json", {"stationNo": "KE0000000001"})
	} else if (keInterfa == 11) {
		interfaFun(" listGunInfo.json", {"stationNo": "KE0000000001"})
	} else if (keInterfa == 12) {
		interfaFun("listGunState.json", {"stationNo": "KE0000000001"})
	} else if (keInterfa == 13) {
		interfaFun(" listChargeOrders.json", {
			"stationNo":"00000111",
			"startDate":"20180110",
			"endDate":"20181010" 
		})
	}
}

function interfaFun(url, paramJson) {
	//console.log(paramJson);
	$("#urlText").text(url);
	var jsonData
	if (paramJson != undefined) {
		jsonData = jsonString.json2String(paramJson).replace(/,/g, ",\n");
	} else {
		jsonData = ""
	}
	$("#jsonDataText").val(jsonData);
}

function interfTest() {
	$("#msgText").val("");
	var tokenText = $("#tokenText").text();
	var urlText = $("#urlText").text();
	var jsonDataTextR = $("#jsonDataText").val();
	if (urlText == "" && jsonDataTextR == "") {
		$("#msgText").val("")
	} else {
		var jsonDataText = jsonDataTextR.replace(/,\n/g, ",")
		$.post(urlText, {
			queryJsonStr : jsonDataText,
			token : tokenText
		}, function(data) {
			var msgData = jsonString.json2String(data).replace(/,/g, ",\n");
			$("#msgText").val(msgData)
		})
	}
}
