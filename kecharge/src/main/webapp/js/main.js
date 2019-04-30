$(function(){
	$("#mainContent").load("jsp/InterfaceTest.jsp")
	changePage();
})

function changePage(){
	$("#navbar li a").click(function(){
		var url=$(this).attr("data");
		if(url!="") {
			$("#mainContent").load(url);
			$(this).parent().addClass("active");
			$(this).parent().siblings().removeClass("active")
		}else{
			window.location.href="jsp/login.jsp"
		}
	})
}