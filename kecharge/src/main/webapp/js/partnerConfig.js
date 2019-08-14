var gridCommon = null;
var eidtType = "";
$(function() {
	initGrid();
})

function initGrid() {
	gridCommon = new jqgrid();
	var queryJsonStr = {};
	queryJsonStr.partnerName = $("#partnerNameSearch").val();
	queryJsonStr.useFlag = $("#useFlagSearch").val();

	gridCommon.colNames = [ '', '运营商', '运营商登录用户名','使用标志', '科林登录用户名','会员手机号','流水号前缀',
			'回调登录url', '回调推送充电开始地址', '回调推送充电结束地址','回调推送直流首次充电信息地址','心跳推送地址','告警推送地址' ]; // 列名
	gridCommon.url = "/manage/listOperatorConfig.json"; // 请求地址
	gridCommon.gridId = "gridTable"; // 表格加载div的id
	gridCommon.pageId = "girdPager"; // 分页加载div的id
	gridCommon.rowNum="100000";
	gridCommon.paramJson = queryJsonStr; // 查询传递参数
	gridCommon.autowidth = false; // 自动分配宽度
	gridCommon.height = "calc(100vh - 220px)"; // 表格div的高度
	gridCommon.colModel = [ 
			{name : 'id',                width : 60, editable : false,sortable : false,align : 'center',key : true,hidden : true}, 
			{name : 'operatorName',      width : 100,editable : false,sortable : false,align : 'center'}, 
			{name : 'operatorLoginname', width : 140,editable : false,sortable : false,align : 'center'}, 
			{name : 'useFlag',			 width : 100,editable : false,sortable : false,align : 'center'}, 
			{name : 'userName',			 width : 150,editable : false,sortable : false,align : 'left'}, 
			{name : 'memberPhone', 		 width : 150,editable : false,sortable : false,align : 'center'}, 
			{name : 'serialnumberPrefix', width : 150,editable : false,sortable : false,align : 'center'}, 
			{name : 'loginUrl',         width : 400,editable : false,sortable : false,align : 'left'}, 
			{name : 'chargeStartUrl',  width : 400,editable : false,sortable : false,align : 'left'}, 
			{name : 'chargeOverUrl',   width : 400,editable : false,sortable : false,align : 'left'},
			{name : 'chargeDcInfoUrl',	width : 400,editable : false,sortable : false,align : 'left'},
			{name : 'chargeHeartUrl',   width : 400,editable : false,sortable : false,align : 'left'},
			{name : 'chargeAlarmUrl',   width : 400,editable : false,sortable : false,align : 'left'}

			
		]; //列参数
	gridCommon.setMultiSelect(false); // 批量删除的全选功能
	gridCommon.loadgrid();
}
//查询
function searchConfig() {
	var queryJsonStr = {};
	queryJsonStr.partnerName = $("#partnerNameSearch").val();
	queryJsonStr.useFlag = $("#useFlagSearch").val();

	gridCommon.reload(null, queryJsonStr, 1)
}

//添加				
function addConfig() {
	var bodyUrl = "jsp/modal/partnerConfigModal.jsp?eidtType=add";
	modalPage(bodyUrl);
}
//修改			
function editConfig() {
	var rowId = $("#gridTable").jqGrid("getGridParam", "selrow");

	if (rowId != "") {
		var url = "jsp/modal/partnerConfigModal.jsp?eidtType=edit";
		var bodyUrl = url + "&selectId=" + rowId;
		modalPage(bodyUrl);
	} else {
		alert("请至少选择一行！")
	}

}
//删除				
function deleteConfig() {
	var rowId = $("#gridTable").jqGrid("getGridParam", "selrow");

	if (rowId != "") {
		var partner_name = gridCommon.getCellsText(rowId, "partner_name");
		messageBox.setPropertyCaption("警告");
		messageBox.setPropertyContent("确定要删除" + partner_name + "的信息吗?");

		messageBox.show();
		messageBox.setOnClickListener(function() {
			$.post("manage/deleteOperatorConfig.json", {
				id : rowId
			}, function(data) {
				if (data.status == "0") {
					gridCommon.reload();
				} else {
					alert(data.error)
				}
			});
		});
	} else {
		alert("请至少选择一行！")
	}
}

function modalPage(bodyUrl) {
	modalUtil.setModalWidth(650);
	modalUtil.setModalHeight(600);
	modalUtil.setHeaderText("添加/修改参数配置");
	modalUtil.setBodyUrl(bodyUrl);

	modalUtil.show();

	modalUtil.setClickListener(function() {
		onOk()
	});
}
