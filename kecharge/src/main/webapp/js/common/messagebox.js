var messageBox = {

	propertyCaption : "",
	propertyContent : "",
	

    //-------------------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------------------
	//-------------------------------------函数处理部分------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------------------
	//-------------------------------------------------------------------------------------------------------------
	
	setPropertyCaption : function(caption) {
		this.propertyCaption = caption;
	},
	setPropertyContent : function(content) {
		this.propertyContent = content;
	},

	setOnClickListener : function(e) {
		$("#msgOK").unbind();
		$("#msgOK").bind("click", e);
	},

	show:function() {
        var Width = ($(window).width()-360)/2;
        var Height=($(window).height()-200)/2;
		var innerHTML = "<div id='Configure_Modal' class='modal fade' style='width:360px;height:200px;padding-right:0 !important;background:#fff;overflow:hidden'>"+
	         				"<div class='modal-header'style='padding:10px'>"+
	         					"<img src='./images/model_head.png' width='24' height='24' style='margin:-5px 5px 0 0;'/><span style='color:#c9302c;font-size:16px;'>" + this.propertyCaption + "</span>"+
	             				"<button type='button' class='close' data-dismiss='modal'>&times;</button>"+
	           				 "</div>"+ 
	            			"<div class='modal-body' style='height:110px;overflow:hidden;padding:15px;text-align:left'>"+
	            				"<span style='font-size:16px;position: relative;top: 25%;'>" + this.propertyContent + "</span>"+
	            			"</div>"+
	          				"<div class='modal-footer' style='text-align:right;padding:5px 10px;margin:0;'>"+
								"<button type='button' class='btn btn-info btn-sm' id='msgOK' data-dismiss='modal'>"+
									"确定"+
									"<i class='icon-ok icon-on-right bigger-110'></i>"+
								"</button>"+
								"<button type='button' class='btn btn-danger btn-sm' id='msgCancle' data-dismiss='modal'>"+
									"取消"+
									"<i class='icon-undo icon-on-right bigger-110'></i>"+
								"</button>"+
	           			 	"</div>"+
        				"</div>"
		
		$(document.body).append(innerHTML);
		$("#Configure_Modal").css({"margin-left":Width + "px"});
		$("#Configure_Modal").css({"margin-top":Height + "px"});
		$("#Configure_Modal").modal("show");	
		
		$("#Configure_Modal").on('hide.bs.modal', function () {
			$(document.body).removeClass("modal-open");
      		$("#Configure_Modal").remove();
		});   		
	},
	hide : function() {
		$("#Configure_Modal").modal("hide");
	}
};


