$(document).ready(function() {
	var selectId = -1;

	$("#showAdd").click(function() {
		location.href = "department?type=showAdd";
	})

	$("#showUpdate").click(function() {
		if (selectId > -1) {
			location.href = "department?type=showUpdate&id=" + selectId;
		} else {
			alert("请选中一条数据！")
		}
	})
	$("#delete").click(function() {
		if (selectId > -1) {
			location.href = "department?type=delete&id=" + selectId;
		} else {
			alert("请选中一条数据！")
		}
	})

	$("tr").click(function() {
		$(this).toggleClass("select");
		selectId = $(this).data("id");
	})
	$("#manage2").click(function() {
		if (selectId > -1) {
			location.href = "department?type=manage2&depId=" + selectId;
		} else {
			alert("请选中一条数据！")
		}
	})
	$("#manage3").click(function() {
		if (selectId > -1) {
			location.href = "department?type=manage3&depId=" + selectId;
		} else {
			alert("请选中一条数据！")
		}
	})
	$("#manage4").click(function() {
		if (selectId > -1) {
			location.href = "department?type=manage4&depId=" + selectId;
		} else {
			alert("请选中一条数据！")
		}
	})
	$("#manage5").click(function() {
		if (selectId > -1) {
			$("#myModal").html("");
			var url="department?type=manage5&depId="+selectId;
			$("#myModal").load(url);
			$("#myModal").modal("show");
		} else {
			alert("请选中一条数据！")
		}
	})
})