$(function(){
	$("#startHistory").click(function() {
		request("startHistoryDatabase");
	});
	$("#stopHistory").click(function() {
		request("stopHistoryDatabase");
	});
	$("#startEvent").click(function() {
		request("startAlarmEvent");
	});
	$("#stopEvent").click(function() {
		request("stopAlarmEvent");
	});
})

function request(url){
	$.post(url,"",function(data){
		if(data=="ok"){
			alert("执行成功");
		}else{
			alert("执行失败");
		}
	})
}

 