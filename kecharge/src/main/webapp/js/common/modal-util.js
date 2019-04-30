var modalUtil = {

	modalWidth : 360,
	modalHeight : 200,
	headerText : "",
	bodyUrl : "",
	clearBtnFlag : false,

	setModalWidth : function(width) {
		this.modalWidth = width;
	}, //配置模态框长度
	setModalHeight : function(height) {
		this.modalHeight = height;
	}, //配置模态框高度
	setHeaderText : function(text) {
		this.headerText = text;
	}, //配置模态框头部显示信息
	setBodyUrl : function(url) {
		this.bodyUrl = url;
	}, //配置模态框包含的页
	setBtnFlag : function(flag) {
		this.clearBtnFlag = flag;
	}, //配置模态框清空按钮的显示 	

	setClickListener : function(e) {
		$("#Modal_OK").unbind();
		$("#Modal_OK").bind("click", e);
	},
	setClearListener : function(e) {
		$("#Modal_Clear").unbind();
		$("#Modal_Clear").bind("click", e);
	},
	setCancelListener : function(e) {
		$("#Modal_Cancel").unbind();
		$("#Modal_Cancel").bind("click", e);
	},

	show : function() {

		var width = ($(window).width() - this.modalWidth) / 2;
		var height = ($(window).height() - this.modalHeight) / 2;
		var bodyHeight = this.modalHeight - 50 - 50;

		if (this.clearBtnFlag) {
			var btnClass = "show-active";
		} else {
			var btnClass = "hide-active";
		}

		var innerHTML = "<div id='Modal_Util' class='modal fade modal-util'>"
				+ "<div class='modal-header'>" + "<span>"
				+ this.headerText
				+ "</span>"
				+ "<button type='button' class='close' data-dismiss='modal'>&times;</button>"
				+ "</div>"
				+ "<div class='modal-body'></div>"
				+ "<div class='modal-footer'>"
				+ "<button id='Modal_OK' class='btn btn-success btn-sm' type='button'>"
				+ "确定"
				+ "<i class='icon-ok icon-on-right bigger-110'></i>"
				+ "</button>"
				+ "<button class='btn btn-info btn-sm "
				+ btnClass
				+ "' data-last='Clear' id='Modal_Clear'>"
				+ "清空"
				+ "<i class='icon-remove icon-on-right bigger-110'></i>"
				+ "</button>"
				+ "<button id='Modal_Cancel' class='btn btn-danger btn-sm' type='button' data-dismiss='modal'>"
				+ "取消"
				+ "<i class='icon-undo icon-on-right bigger-110'></i>"
				+ "</button>" + "</div>" + "</div>"

		$(document.body).append(innerHTML);

		$("#Modal_Util").css("width", this.modalWidth + "px");
		$("#Modal_Util").css("height", this.modalHeight + "px");
		$("#Modal_Util .modal-body").css("height", bodyHeight + "px");
		$("#Modal_Util").css("margin-left", width + "px");
		$("#Modal_Util").css("margin-top", height + "px");

		$("#Modal_Util .modal-body").load(this.bodyUrl);

		$("#Modal_Util").modal("show");

		$("#Modal_Util").on('hide.bs.modal', function() {
			$(document.body).removeClass("modal-open");
			$("#Modal_Util").remove();
		});
	},
	hide : function() {
		$("#Modal_Util").modal("hide");
//		$("#Modal_Util").modal("hidde");
		//$("#Modal_Util").remove();
	}
};
