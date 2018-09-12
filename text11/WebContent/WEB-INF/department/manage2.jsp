<%@page import="util.Pagination"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" type="text/css" href="./css/style.css">
<link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
<style>
#pro .select {
	background:	#33FFFF;
}

#main {
	width: 700px;
	margin: 40px auto;
}

#ss {
	width: 700px;
	margin: 40px auto;
}

.text1 {
	width: 100px;
}

#t1 {
	display: none;
}

#t2 {
	display: none;
}

#t3 {
	display: none;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var selectId = -1;
	var i=0;

	$("#showProAdd2").click(function() {
        var proId= $("#selectPro").val();
		//location.href = "department?type=showProAdd&depId=${dep.id}&proId="+proId;
	$.ajax({
		url:"department",
		type:"post",
		data:{type:"showProAdd2",depId:${dep.id},proId:proId},
		dataType:"text",
		success:function(data){
			if(data=="true"){
				var proName="";
				$("#selectPro").children().each(function(index,element){
					if($(this).val()==proId){
						proName=$(this).text();
						i=index;
					}
				})
				var tr="<tr><td>"+proId+"</td><td>"+proName+"</td></tr>";
				$("#pro").append(tr);
				$("#selectPro").children().eq(i).remove();

			}
		}
	})
	
	
	})
	
	if($("#selectPro").children().length==0){
		$("#showProAdd2").unbind("click");
		$("#showProAdd2").addClass("disabled")
	}
	
	$("#showProDelete2").click(function() {
		if (selectId > -1) {
			//location.href = "department?type=showProDelete2&depId=${dep.id}&proId=" + selectId;
	   var i=0;
			$.ajax({
		url:"department",
		type:"post",
		data:{type:"showProDelete2",depId:${dep.id},proId:selectId},
		dataType:"text",
		success:function(data){
			if(data=="true"){
				var proName="";
				$("tr").each(function(index,element){
					if($(this).data("id")==selectId){
						proName=$(this).children().eq(1).text();
						i=index;
					}
				})
				var option="<option value='"+selectId+"'>"+proName+"</option>";
				$("#selectPro").append(option);
				$("tr").eq(i).remove();

			}
		}
	    })	
		} else {
			alert("请选中一条数据！")
		}
	})
     $(document).on("click","tr",function() {
		$(this).toggleClass("select");
		selectId = $(this).data("id");
	})
})
</script>
</head>
<body>

	<div id="main">
		<h2>${dep.name}</h2>
		<table class="c1" id="pro">
			<tr>
				<th>id</th>
				<th>名称</th>
			</tr>

			<c:forEach items="${list}" var="pro">
				<tr data-id="${pro.id}">
					<td id="d1">${pro.id}</td>
					<td id="d2">${pro.name}</td>
				</tr>
			</c:forEach>
		</table>
		<div>
			</select> <label>项目</label><select name="pro_name" id="selectPro">
				<c:forEach items="${noList}" var="pro">
					<option value="${pro.id}">${pro.name}</option>
				</c:forEach>
			</select>
			<button id="showProAdd2" type="button" class="btn btn-primary">添加</button>
			<button id="showProDelete2" type="button" class="btn btn-primary">删除</button>
		</div>
	</div>

</body>
</html>