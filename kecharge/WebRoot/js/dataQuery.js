var gridCommon=null
$(function(){
	//初始化日期
	initDate();
})

//初始化日期
function initDate(){
	var curDate=new Date();
	curDate=curDate.Format("yyyy-MM-DD");
	$(".input-daterange").datepicker({
		format : "yyyy-mm-dd",
		language: 'zh-CN',
		todayHighlight: true,
		startDate : "2013-01-01",
		endDate : curDate,
		changeMonth : true,
		changeYear : true,
		onSelect:gotoDate
	}).on("changeDate",gotoDate)
	$("#beginDate").datepicker("setDate" ,curDate);
	initSelect()
}

function gotoDate(ev){
	var curDate=new Date();
	curDate=curDate.Format("yyyy-MM-DD");
	var beginDate=$("#beginDate").val().replace(/-/g,"").substring(0,4);
	var endDate=$("#endDate").val().replace(/-/g,"").substring(0,4);
	var desYear=parseInt(endDate)-parseInt(beginDate);
	if(desYear>1){
		$("#beginDate").datepicker("setDate" ,curDate);
		alert("最多可查询一年的数据！请重新选择查询时间！")
	}
}
//初始化下拉框
function initSelect(){
//	$("#partnerId").empty();
//	$.post("","",function(data){
//		if(data.result=="success"){
//			var jsonData=data.jsonData
//			var html="<option value='-1'>全部<option>";
//			for(var k in jsonData){
//				html+="<option value='"+k+"'>"+jsonData[k]+"<option>"
//			}
//			$("#partnerId").append(html);
//		}else{
//			alert(data.error)
//		}
//	})
//	console.log($("#partnerId").val())
	initGrid();
}
//初始化表格
function initGrid() {
	gridCommon = new jqgrid();
	var beginDate=$("#beginDate").val().replace(/-/g,"");
	var endDate=$("#endDate").val().replace(/-/g,"");
	var queryJsonStr = {};
	queryJsonStr.beginDate = beginDate;
	queryJsonStr.endDate= endDate;
	queryJsonStr.serialnumber = $("#serialnumber").val();
	queryJsonStr.partnerId=$("#partnerId").val();

	gridCommon.colNames = ['流水号', '充电金额 (分)','充电桩编号','充电枪','请求开始充电日期', '前置机推送充电开始时间','充电开始推送结果', '推送标志','推送充电开始时间','请求结束充电日期','前置机推送充电结束时间','推送标志','推送充电结束时间']; // 列名
	gridCommon.url = "listChargeRecord.json"; // 请求地址
	gridCommon.gridId = "gridTable"; // 表格加载div的id
	gridCommon.pageId = "girdPager"; // 分页加载div的id
	gridCommon.rowNum="100000";
	gridCommon.paramJson = queryJsonStr; // 查询传递参数
	gridCommon.autowidth = false; // 自动分配宽度
	gridCommon.height = "calc(100vh - 270px)"; // 表格div的高度
	gridCommon.colModel = [
//							{name:'id',	   	       		width:60,  editable:false, sortable:false, align:'center',	key:true,hidden:true},
			                {name:'serialnumber',  		width:100,  editable:false, sortable:false, align:'center',key:true},
			                {name:'charge_money',  		width:100,  editable:false, sortable:false, align:'center'},
			                {name:'pile_code',     		width:120,  editable:false, sortable:false, align:'center'},
			                {name:'gun_id', 	  		width:100,  editable:false, sortable:false, align:'center'},
			                {name:'start_date',     	width:150,  editable:false, sortable:false, align:'center'},
			                {name:'start_receive_time', width:200,  editable:false, sortable:false, align:'center'},
			                {name:'start_flag',         width:150,  editable:false, sortable:false, align:'center'},
			                {name:'start_push',         width:100,  editable:false, sortable:false, align:'center'},
			                {name:'start_push_time',  	width:200,  editable:false, sortable:false, align:'center'},
			                {name:'end_date',			width:150,  editable:false, sortable:false, align:'center'},
			                {name:'end_receive_time',   width:220,  editable:false, sortable:false, align:'center'},
			                {name:'end_push',			width:100,  editable:false, sortable:false, align:'center'},
			                {name:'end_push_time',   	width:200,  editable:false, sortable:false, align:'center'}
			               ];   //列参数
	gridCommon.setMultiSelect(false); // 批量删除的全选功能
	gridCommon.loadgrid();
}

//点击查询按钮进行查询
function searchData(){
	var beginDate=$("#beginDate").val().replace(/-/g,"");
	var endDate=$("#endDate").val().replace(/-/g,"");
	var queryJsonStr = {};
	queryJsonStr.beginDate = beginDate;
	queryJsonStr.endDate= endDate;
	queryJsonStr.serialnumber = $("#serialnumber").val();
	queryJsonStr.partnerId=$("#partnerId").val();

	gridCommon.reload(null,queryJsonStr,1)
}

