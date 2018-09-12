$(document).ready(function() {
	var selectId = -1;

	$("#showAdd").click(function() {
		location.href = "employee?type=showAdd";
	})
	
	$("#showAdd2").click(function() {
		location.href = "employee?type=showAdd2";
	})

	$("#showUpdate").click(function() {
		if (selectId > -1) {
			location.href = "employee?type=showUpdate&id=" + selectId;
		} else {
			alert("请选中一条数据！")
		}
	})
	$("#delete").click(function() {
		if (selectId > -1) {
			location.href = "employee?type=delete&id=" + selectId;
		} else {
			alert("请选中一条数据！")
		}
	})
	$("#deleteBatch").click(function() {
		var length = $("#emp .select").length;
		// var ids="";
		var ids = new Array();
		if (length > 0) {
			$("#emp .select").each(function(index, element) {
				// ids+=$(this).data("id")+",";
				ids.push($(this).data("id"))
			})
			// ids=ids.substring(0,ids.length-1);
			location.href = "employee?type=deleteBatch&id=" + ids;
		} else {
			alert("请选中一条数据！")
		}
	})
	$("#showUpdateBatch1").click(function() {
		var length = $("#emp .select").length;
		// var ids="";
		var ids = new Array();
		if (length > 0) {
			$("#emp .select").each(function(index, element) {
				// ids+=$(this).data("id")+",";
				ids.push($(this).data("id"))
			})
			// ids=ids.substring(0,ids.length-1);
			location.href = "employee?type=showUpdateBatch1&ids=" + ids;
		} else {
			alert("请选中一条数据！")
		}
	})
	$("#showUpdateBatch2").click(function() {
		var length = $("#emp .select").length;
		// var ids="";
		var ids = new Array();
		if (length > 0) {
			$("#emp .select").each(function(index, element) {
				// ids+=$(this).data("id")+",";
				ids.push($(this).data("id"))
			})
			// ids=ids.substring(0,ids.length-1);
			location.href = "employee?type=showUpdateBatch2&ids=" + ids;
		} else {
			alert("请选中一条数据！")
		}
	})

	$("tr").click(function() {
		$(this).toggleClass("select");

		// selectId.$(this).children.eq(0).text();
		selectId = $(this).data("id");
	})

	// var tr=$("");

	$("tr").dblclick(function() {

		$(this).unbind("dblclick");
		$(this).unbind("click");
		$(this).addClass("updateEmp");
		var td1 = $(this).find("td").eq(0);
		var td2 = $(this).find("td").eq(1);
		var td3 = $(this).find("td").eq(2);
		var td4 = $(this).find("td").eq(3);
		var txt1 = td1.text();
		var txt2 = td2.text();
		var txt3 = td3.text();
		var txt4 = td4.text();

		var input1 = $("<input type='text' name='name' value='" + txt1 + "'/>");
		var input2 = $("<input type='text' name='sex' value='" + txt2 + "'/>");
		var input3 = $("<input type='text' name='age' value='" + txt3 + "'/>");
		var input4 = $("<input type='text' name='d_name' value='" + txt4 + "'/>");
		td1.html(input1);
		td2.html(input2);
		td3.html(input3);
		td4.html(input4);

// $(this).addClass("updateEmp");
//
// var name=$(this).children().eq(0).text();
// $(this).children().eq(0).html("<input type='text' name='name'
// value='"+name+"'/>")
		
// var sex=$(this).children().eq(1).text();
// var select="";
// if(sex=="男"){
// select="<select name='sex'><option selected value='男'>男</option><option
// value='女'>女</option></select>";
// }else{
// select="<select name='sex'><option value='男'>男</option><option selected
// value='女'>女</option></select>";
// }
// $(this).children().eq(1).html(select);
		
// var sex=$(this).children().eq(1).text();
// $(this).children().eq(1).html("<input type='text' name='sex'
// value='"+sex+"'/>")
//		
// var age=$(this).children().eq(2).text();
// $(this).children().eq(2).html("<input type='text' name='age'
// value='"+age+"'/>")
	})
	
	$("#updateBatch3").click(function() {
			var emps = "";
			$(".updateEmp").each(function(index, element) {
				var id = $(this).data("id");
				var name = $(this).find("[name=name]").val();
				var sex = $(this).find("[name=sex]").val();
				var age = $(this).find("[name=age]").val();
				var dName = $(this).find("[name=d_name]").val();
				emps += id + "," + name + "," + sex + "," + age + "," + dName + "!";

			})
			emps = emps.substring(0, emps.length - 1);
			window.location.href = "employee?type=updateBatch2&emps=" + emps;
	})
	
	$("#emp img").hover(function(event){
		var photo=$(this).attr("src");
		$("#bigPhoto img").attr("src",photo);
		$("#bigPhoto").show();
		$("#bigPhoto").css({left:event.pageX+10,top:event.pageY+10});
	},function(){
		$("#bigPhoto").hide();
	})
	
	
	
	
})