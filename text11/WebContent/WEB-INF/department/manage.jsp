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
#ss{
	width: 700px;
	margin: 40px auto;
}

.text1{
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

	$("#showProAdd").click(function() {
        var proId= $("#selectPro").val();
		location.href = "department?type=showProAdd&depId=${dep.id}&proId="+proId;
	})
	
	if($("#selectPro").children().length==0){
		$("#showProAdd").unbind("click");
		$("#showProAdd").addClass("disabled")
	}
	
	$("#showProDelete").click(function() {
		if (selectId > -1) {
			location.href = "department?type=showProDelete&depId=${dep.id}&proId=" + selectId;
		} else {
			alert("请选中一条数据！")
		}
	})
	
	
	$("tr").click(function() {
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
				    <td id="d1" class="selete">${pro.id}</td>
					<td id="d2" class="selete">${pro.name}</td>
				</tr>
			</c:forEach>
		</table>
		<div>
		</select>
		<label>项目</label><select name="pro_name" id="selectPro">
		<c:forEach items="${proList}" var="pro">
		<option value="${pro.id}">${pro.name}</option>
		</c:forEach>
		</select>
			<button id="showProAdd" type="button" class="btn btn-primary">添加</button>
			<button id="showProDelete" type="button" class="btn btn-primary">删除</button>
		</div>
	</div>

</body>
</html>