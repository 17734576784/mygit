function jqgrid() {
	this.colNames = [], // 表头名称:['ID','姓名','年龄', '日期', ...]
	this.colModel = [], // 常用属性:
						// {name:'id',index:'id',width:60,editable:false,sorttype:"int",
						// align:"center"},
	this.url = "", // 后台服务地址
	this.gridId = "", // 加载grid表格div的id
	this.pageId = "", // 加载分页内容div的id
	this.multiselect = true, // 是否支持选择框列使用多选
	this.paramJson = {}, // 后台请求参数，参数直接添至url后,
	this.rowNum = 10, // 每页初始显示数据条数
	this.rowList = [ 10, 20, 30 ], // 每页显示条数下拉列表
	this.height = 400, // 表格高度
	this.autowidth = true, // 是否自动分配列宽
	this.gridLoadedAfterDo = null; // 表格加载完毕后的回调函数,加载完毕后执行的一系列方法
	this.onSelectRowDo = null; // 行选中后加载事件
	this.gridTipsFunc = null;
	this.totalNum = null; // 表格中总共有多少行数据（所有页加到一起）
	this.cellName = []; // 需要合并的单元格列标题名称
	this.cellNameId = []; // 需要合并的单元格列标题名称ID
	this.mergerTableHead = []; // 需要合并的单元格表头名称
	this.getJsonData = null; // 获取后台返回的数据jsondata
	this.rowData = []; // 获取后台返回的rows
	this.localData = null; // 后台返回的全部数据
	this.count = 0; // 滚屏加载数据计数
	this.isTitle = false; // 是否有title属性
	this.operationName = []; // 页面加载输入操作的名称；
	this.treeGrid = false
	return this;
};

jqgrid.prototype.setColnames = function(array) {
	this.colNames = array
};
jqgrid.prototype.setColmodel = function(array) {
	this.colModel = array
};
jqgrid.prototype.setGridUrl = function(url) {
	this.url = url
};
jqgrid.prototype.setGridId = function(gridId) {
	this.gridId = gridId
};
jqgrid.prototype.setGridPageId = function(pageId) {
	this.pageId = pageId
};
jqgrid.prototype.setMultiSelect = function(flag) {
	this.multiselect = flag
};
jqgrid.prototype.setParamJson = function(paramJson) {
	this.paramJson = paramJson
};
jqgrid.prototype.setRowList = function(array) {
	this.rowList = array
};
jqgrid.prototype.setRowNum = function(num) {
	this.rowNum = num
};
jqgrid.prototype.setHeight = function(height) {
	this.height = height
};
jqgrid.prototype.setAutowidth = function(autowidth) {
	this.autowidth = autowidth
};
jqgrid.prototype.setCellName = function(array) {
	this.cellName = array
};
jqgrid.prototype.setCellNameId = function(array) {
	this.cellNameId = array
};

jqgrid.prototype.setRowData = function(array) {
	this.rowData = array
};

jqgrid.prototype.setmergeTableHead = function(array) {
	this.mergerTableHead = array
};
jqgrid.prototype.setGridTipsFunc = function(func) {
	this.gridTipsFunc = func
};
jqgrid.prototype.setGridLoadedAfterDo = function(gridLoadedAfterDo) {
	this.gridLoadedAfterDo = gridLoadedAfterDo
};
jqgrid.prototype.setOnSelectRowDo = function(onSelectRowDo) {
	this.onSelectRowDo = onSelectRowDo
};
jqgrid.prototype.getJsonDataFunc = function(func) {
	this.getJsonData = func
};
jqgrid.prototype.setIsTitle = function(flag) {
	this.isTitle = flag
};
jqgrid.prototype.setOperationName = function(operationName) {
	this.operationName = operationName
};

// 加载表格数据
// jqgrid已经将'当前页'(page)和'每页记录数'(rows)封装完毕。在后台中使用request.getParameter("page")获取即可。
// 其他参数可以使用json格式的字符串传递。框架会自动将其添加到url地址后,这里赋值为param,后台中使用request.getParameter获取。
jqgrid.prototype.loadgrid = function() {
	var $this = this;
	var strData = JSON.stringify(this.paramJson);
	var pageNo = 1;
	cookie.del("totalCount");
	if (cookie.get("gridRowId") == "add") { // 添加完到最后一页
		$this.totalNum = cookie.get("totalNum");
		pageNo = $this.toLastPage();
	} else if (cookie.get("gridRowId") != "add"
			&& cookie.get("gridRowId") != "null") {// 编辑完跳到当前页，且选中当前行
		pageNo = cookie.get("pageNo");
	}

	$("#" + $this.gridId)
			.jqGrid(
					{
						url : basePath + $this.url,
						datatype : "json",
						height : $this.height,
						colNames : $this.colNames,
						colModel : $this.colModel,
						viewrecords : true,
						rowNum : $this.rowNum,
						rowList : $this.rowList,
						pager : "#" + $this.pageId,
						page : pageNo,
						altRows : true,
						multiselect : $this.multiselect,
						treeGrid : $this.treeGrid,
						treeGridModel : 'adjacency',
						treedatatype : "local",
						ExpandColumn : 'name',
						autowidth : $this.autowidth,
						postData : {
							'queryJsonStr' : strData
						}, // string类型,后台通过key:param取值
						mtype : "post",
						jsonReader : {
							repeatitems : false,
							userdata : function(obj) {
								$this.setRowData(obj.rows);
								if ($this.getJsonData) {
									var Func = $this.getJsonData;
									Func(obj.jsonData);
								}
							}
						},
						beforeRequest : function() {
							// $("#lui_"+$this.gridId).css("display","block");
							// //遮罩层开启
						},
						loadComplete : function() {
							$("#lui_" + $this.gridId).css("display", "none"); // 遮罩层关闭
							$this.totalNum = $this.allRowNum(); // 得到表格总的多少条数据

							if (!cookie.get("totalCount")) { // 点击搜索的时候，cookie，set("totalCount",totalCount),不改变cookie中totalNum的值
								cookie.set("totalNum", $this.totalNum); // 把表格总的条数存到cookie中
							}
							var nowPage = $this.nowPage();
							document.cookie = "pageNo=" + nowPage;

							if ($this.gridLoadedAfterDo) {
								var executeFunc = $this.gridLoadedAfterDo;
								executeFunc(); // 表格数据加载完毕后执行数据
							}
							var table = this;
							setTimeout(function() {
								$this.updatePagerIcons(table);
							}, 0);
							if ($this.gridTipsFunc) { // tips的触发function
								var Func = $this.gridTipsFunc;
								var lastRowId; // 上一次mouseover的行ID
								$(".jqgrow").mouseover(
										function() {
											if (lastRowId == undefined
													|| lastRowId != this.id) {
												Func(this.id);
												lastRowId = this.id;
											}
										});
							}
							$this.setSelectedRow();

							// $(".ui-jqgrid tr.ui-row-ltr
							// td").removeAttr("title"); //去掉title属性
							if ($this.isTitle != true) {
								$(".ui-jqgrid tr.ui-row-ltr td").removeAttr(
										"title"); // 去掉title属性
							}
							$this.merger(); // 合并单元格
							updatePagerIcons(table);
							updataChecked();
						},
						onSelectRow : function(rowId, status) { // rowId为当前选择的行id

							// if(status == false){
							// $("#" + $this.gridId).jqGrid("setSelection",
							// rowId);
							// }
							if ($this.onSelectRowDo) {
								var executeFunc = $this.onSelectRowDo;
								executeFunc(rowId);
							}
						},
						beforeSelectRow : function(rowid, e) { // 只有点击复选框才能选中
							if ($this.multiselect == false) {
								return true;
							} else {
								if (e.type == 'click') {
									var $myGrid = $(this), i = $.jgrid
											.getCellIndex($(e.target).closest(
													'td')[0]), cm = $myGrid
											.jqGrid('getGridParam', 'colModel');
									return (cm[i].name === 'cb');
								}
								return false;
							}
						},
						// 页面加载删除或是其他操作功能；
						// 参数形式：gridRole.operationName =
						// [{operationOne:[{"deletetest":"删除"},{"save":"保存"}]},
						// {operationTwo:[{"deletetest":"删除"}]}];
						// 在js中操作的colModel中的name加入operationOne和operationTwo；
						gridComplete : function() { // 页面自动加载操作功能；
							var colArray = $this.operationName;
							if (colArray == []) {
								return;
							} else {
								var gridIds = $("#" + $this.gridId).jqGrid(
										"getDataIDs");
								for (var i = 0; i < gridIds.length; i++) {
									for (var j = 0; j < colArray.length; j++) {
										var operHtml = "<div class='grid-oper-button'>";
										var colData = colArray[j];
										for ( var key in colData) {
											var colName = key;
											var colValue = colData[key];
											for (var k = 0; k < colValue.length; k++) {
												var operJson = colValue[k];
												for ( var colkey in operJson) {
													operHtml = operHtml
															+ "<a class='grid-oper-edit' href='javascript:void(0);'onclick='"
															+ colkey + "("
															+ gridIds[i]
															+ ")'>"
															+ operJson[colkey]
															+ "</a>";
												}
											}
										}
										operHtml = operHtml + "</div>";
										var jsondata = {};
										jsondata[key] = operHtml;
										$("#" + $this.gridId).jqGrid(
												"setRowData", gridIds[i],
												jsondata);
									}
								}
							}
						}
					});
	$this.tableNameMerge(); // 二级标题
}

// replace icons with FontAwesome icons like above
function updatePagerIcons(table) {
	var replacement = {
		'ui-icon-seek-first' : 'ace-icon fa fa-angle-double-left bigger-140',
		'ui-icon-seek-prev' : 'ace-icon fa fa-angle-left bigger-140',
		'ui-icon-seek-next' : 'ace-icon fa fa-angle-right bigger-140',
		'ui-icon-seek-end' : 'ace-icon fa fa-angle-double-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
			.each(function() {
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

				if ($class in replacement)
					icon.attr('class', 'ui-icon ' + replacement[$class]);
			})
}

function updataChecked() {
	var rowsDate = $this.rowData;
	if (rowsDate != null && rowsDate.length != 0) {
		if (rowsDate[0].checked != null) {
			for (var i = 0; i < rowsDate.length; i++) {
				if (rowsDate[i].checked == true) {
					$("#" + $this.gridId)
							.jqGrid("setSelection", rowsDate[i].id);
				}
				// $("#jqg_"+$this.gridId+"_"+rowsDate[i].id).attr("checked",rowsDate[i].checked)
			}
		}
	}
}

// 合并单元格
jqgrid.prototype.merger = function() {
	$this = this;
	cellNameArray = $this.cellName;
	cellNameIdArray = $this.cellNameId;

	if (cellNameArray.length == 0)
		return;

	var ids = $this.getDataIDs(); // 所有行ids
	var length = ids.length;

	for (var k = 0; k < cellNameArray.length; k++) {
		var rowSpanTaxCount = 1;
		for (var i = 0; i < length; i += rowSpanTaxCount) {
			// 从当前行开始比对下面的信息
			var before = $this.getCellsText(ids[i], cellNameArray[k]);

			rowSpanTaxCount = 1;
			for (j = i + 1; j < length; j++) {
				// 和上边的信息对比，如果值一样就合并行数+1，然后设置rowspan让当前单元格隐藏
				var end = $this.getCellsText(ids[j], cellNameArray[k]);

				if (before == end && before != "") {
					rowSpanTaxCount++;
					$("#" + $this.gridId).jqGrid("setCell", ids[j],
							cellNameArray[k], '', {
								display : 'none'
							});
				} else {
					break;
				}
			}
			$("#" + cellNameIdArray[k] + ids[i]).attr("rowspan",
					rowSpanTaxCount);
		}
	}
}

// 重新加载加载表格数据,其他参数param
jqgrid.prototype.reload = function(flag, param, pageNo) {

	var $this = this;
	var nowPage;

	if (flag === true) { // 添加,nowPage的值是表格最后一页
		nowPage = $this.toLastPage();
	} else if (flag === false) { // 1.编辑完跳到当前页，并选中编辑行
		nowPage = $this.nowPage();
		// $this.setSelectedRow();
	} else if (flag == "fresh") { // 仅仅刷新当前页面
		nowPage = $this.nowPage();
	} else { // 有检索条件时和删除时

		if (param != "") {
			var strData = JSON.stringify(param);
		} else {
			var strData = JSON.stringify(this.paramJson);
		}
		if (pageNo != undefined) { // 检索，跳到指定页
			nowPage = pageNo;
		} else { // 删除的时候，跳到当前页

			var num = $this.getSelectedRowIds().length;

			if (num) { // 批量删除
				nowPage = $this.nowPage(); // 先获取删除数据的页码
				if (nowPage == $this.toLastPage()) { // 如果删除数据在最后一页，nowPage的值要改变
					nowPage = Math.ceil((new Number($this.totalNum) - num)
							/ new Number($this.rowNum));
				}
			} else { // 单个删除
				// 防止删除时，最后一页只有一条数据，删了之后，跳到最后一页了，但是最后一页为空
				// 这种情况，让表格跳到前一页
				if ((new Number($this.totalNum) - 1)
						% (new Number($this.rowNum)) == 0) {
					nowPage = (new Number($this.totalNum) - 1)
							/ new Number($this.rowNum);
				} else { // 为一般情况的删除
					nowPage = $this.nowPage();
				}
			}

		}
	}

	$("#" + $this.gridId).jqGrid("clearGridData");

	$("#" + $this.gridId).jqGrid('setGridParam', {
		postData : {
			'queryJsonStr' : strData
		},
		page : nowPage
	// 重新加载，当前页数默认为1
	}).trigger('reloadGrid');
}

jqgrid.prototype.updatePagerIcons = function(table) {
	var replacement = {
		'ui-icon-seek-first' : 'icon-double-angle-left bigger-140',
		'ui-icon-seek-prev' : 'icon-angle-left bigger-140',
		'ui-icon-seek-next' : 'icon-angle-right bigger-140',
		'ui-icon-seek-end' : 'icon-double-angle-right bigger-140'
	};
	$('.ui-pg-table:not(.navtable) > tbody > tr > .ui-pg-button > .ui-icon')
			.each(function() {
				var icon = $(this);
				var $class = $.trim(icon.attr('class').replace('ui-icon', ''));

				if ($class in replacement)
					icon.attr('class', 'ui-icon ' + replacement[$class]);
			});
}

// 获取选中行的行id
jqgrid.prototype.getSelectedRowIds = function() {
	var $this = this;
	return $("#" + $this.gridId).jqGrid('getGridParam', 'selarrrow');
}

jqgrid.prototype.setSelectedRow = function(id) {
	var $this = this;

	if (id != undefined) {
		$("#" + $this.gridId).jqGrid("setSelection", id);
	} else {
		var rowId = cookie.get("gridRowId");
		if (rowId != "null" || rowId != "") {
			$("#" + $this.gridId).jqGrid("setSelection", rowId);
		}
	}
}

// 获取当前选中单元格内容
jqgrid.prototype.getCellsText = function(rowId, columnName) {
	var $this = this;
	return $("#" + $this.gridId).jqGrid("getCell", rowId, columnName);
}

// 获取表格的当前页
jqgrid.prototype.nowPage = function() {
	var $this = this;
	return $("#" + $this.gridId).jqGrid("getGridParam", "page");
}

// 获取表格所有页的总条数
jqgrid.prototype.allRowNum = function() {
	var $this = this;
	return $("#" + $this.gridId).jqGrid("getGridParam", "records");
}

// 获取表格最后一页
jqgrid.prototype.toLastPage = function() {
	var $this = this;
	return Math.ceil((new Number($this.totalNum) + 1)
			/ (new Number($this.rowNum)));
}

// 获取所有行的id
jqgrid.prototype.getDataIDs = function() {
	var $this = this;
	return $("#" + $this.gridId).jqGrid("getDataIDs");
}

// 给表格中某个单元格赋值
jqgrid.prototype.setCell = function(id, CellName, value) {
	var $this = this;
	$("#" + $this.gridId).jqGrid("setCell", id, CellName, value);
}

// 实现两级表头
jqgrid.prototype.tableNameMerge = function() {
	var $this = this;
	var list = $this.mergerTableHead;
	if (list.length == 0) {
		return;
	}
	var Headers = [];
	for (var i = 0; i < list.length; i++) {
		var param = {
			startColumnName : list[i].startName,
			numberOfColumns : list[i].mergeNum,
			titleText : list[i].titleText
		}
		Headers.push(param);
	}

	$("#" + $this.gridId).jqGrid("setGroupHeaders", {
		useColSpanStyle : true,
		groupHeaders : Headers
	})
}

// 通过点击左侧CheckBox实现多选
jqgrid.prototype.setCell = function(id, CellName, value) {
	var $this = this;
	$("#" + $this.gridId).jqGrid("setCell", id, CellName, value);
}

// 滚屏加载数据不分页
jqgrid.prototype.loadDataLocal = function() {

	$(window).unbind("scroll");
	var $this = this;
	var strData = JSON.stringify($this.paramJson);

	$
			.post(
					$this.url,
					{
						param : strData

					},
					function(data) {
						$this.localData = data.rows;
						if ($this.getJsonData) {
							var Func = $this.getJsonData;
							Func(data.jsonData);
						}
						$("#" + $this.gridId)
								.jqGrid(
										{
											data : $this.localData.slice(0, 40),
											datatype : "local",
											height : $this.height,
											colNames : $this.colNames,
											colModel : $this.colModel,
											viewrecords : true,
											rowNum : $this.rowNum,
											rowList : $this.rowList,
											altRows : true,
											multiselect : $this.multiselect,
											autowidth : $this.autowidth,
											loadComplete : function() {

												if ($this.gridTipsFunc) { // tips的触发function
													var Func = $this.gridTipsFunc;
													var lastRowId; // 上一次mouseover的行ID
													$(".jqgrow")
															.mouseover(
																	function() {
																		if (lastRowId == undefined
																				|| lastRowId != this.id) {
																			Func(this.id);
																			lastRowId = this.id;
																		}
																	});
												}
												// $(".ui-jqgrid tr.ui-row-ltr
												// td").removeAttr("title");
												// //去掉title属性
												if ($this.isTitles != true) {
													$(
															".ui-jqgrid tr.ui-row-ltr td")
															.removeAttr("title"); // 去掉title属性
												}
												$this.merger(); // 合并单元格

												$(window)
														.scroll(
																function() {
																	var curScrollY = window.scrollY; // 当前滚动条位置
																	var wInnerH = window.innerHeight; // 目前页面视口高度
																	var ScrollY = document.body.scrollHeight; // 页面总高度
																	if (curScrollY
																			+ wInnerH == ScrollY) {
																		$this.count++;
																		for (var i = 0; i < 40; i++) {
																			rowData = $this.localData[$this.count
																					* 40
																					+ i]
																					|| null;
																			rowId = !rowData
																					&& $(
																							window)
																							.unbind(
																									"scroll")
																					|| $this.localData[$this.count
																							* 40
																							+ i].id;
																			if (rowId) {
																				$(
																						"#"
																								+ $this.gridId)
																						.jqGrid(
																								"addRowData",
																								rowId,
																								rowData,
																								"last");
																			} else {
																				$this
																						.merger(); //合并单元格
																				return;
																			}
																		}
																		$this
																				.merger(); //合并单元格
																	}
																});
											},
										});
						$this.tableNameMerge(); //二级标题
					});

}

//重新加载表格数据(滚屏加载/不重新创建对象)
jqgrid.prototype.reloadDataLocal = function(param) {
	var $this = this;
	$this.count = 0;

	if (param) {
		var strData = JSON.stringify(param);
	} else {
		var strData = JSON.stringify($this.paramJson);
	}
	$.post($this.url, {
		param : strData

	}, function(data) {
		$this.localData = data.rows;

		if ($this.getJsonData) {
			var Func = $this.getJsonData;
			Func(data.jsonData);
		}

		$("#" + $this.gridId).jqGrid("clearGridData");

		$("#" + $this.gridId).jqGrid('setGridParam', {
			data : $this.localData.slice(0, 40),
			datatype : "local",
		}).trigger('reloadGrid');
	});

}