$(function(){
	$("#mainContent").load("jsp/login.jsp")
	changePage();
})

function changePage(){
	$("#navbar li a").click(function(){
		var url=$(this).attr("data")
		$("#mainContent").load(url);
		$(this).parent().addClass("active");
		$(this).parent().siblings().removeClass("active")
	})
}