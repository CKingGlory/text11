$(document).ready(function() {
	var selectId = -1;

	$("#showAdd").click(function() {
		location.href = "project?type=showAdd";
	})

	$("#showUpdate").click(function() {
		if (selectId > -1) {
			location.href = "project?type=showUpdate&id=" + selectId;
		} else {
			alert("请选中一条数据！")
		}
	})
	$("#delete").click(function() {
		if (selectId > -1) {
			location.href = "project?type=delete&id=" + selectId;
		} else {
			alert("请选中一条数据！")
		}
	})
	
	$("tr").click(function() {
		$(this).toggleClass("select");
		selectId = $(this).data("id");
	})
})