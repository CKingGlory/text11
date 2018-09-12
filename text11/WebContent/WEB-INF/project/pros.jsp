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
#pro td {
	width: 150px;
	color:#000;
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
</script>
</head>
<body>

    <div id="ss">
            <form action="project" method="post">
		<!--你光接收不给他参数  -->
		<label>名称</label><input type="text" name="name" placeholder="请输入名称" value="${pr.name }" />
		<input type="submit"value="搜索" class="btn btn-primary" />
	</form>
    </div>

	<div id="main">
		<table class="c1" id="pro">
			<tr>
				<th>id</th>
				<th>名称</th>
			</tr>

			<c:forEach items="${key}" var="pro">
				<tr data-id="${pro.id}">
				    <td id="d1" class="selete">${pro.id}</td>
					<td id="d2" class="selete">${pro.name}</td>
				</tr>
			</c:forEach>
		</table>

		<ul class="pagination">
			<li><a href="project?ye=1">首页</a></li>
			<li id="pre" <c:if test= "${p.ye<1}" > class="disabled"</c:if>><a
				href="project?ye=${p.ye-1}&name=${pr.name}">上一页</a></li>
			<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
				<li <c:if test="${p.ye==status.index }&name=${pr.name}">class="active"</c:if>><a
					href="project?ye=${status.index }">${status.index}</a></li>
			</c:forEach>
			<li id="next"><a
			    href="project?ye=${p.ye+1}&name=${pr.name}">下一页</a></li>
			<li><a href="project?ye=${p.maxYe}">尾页</a></li>
			<input type="text" />
			<input type="submit" value="跳转" />
		</ul>
		<div>
			<button id="showAdd" type="button" class="btn btn-primary">添加</button>
			<button id="showUpdate" type="button" class="btn btn-primary">修改</button>
			<button id="delete" type="button" class="btn btn-primary">删除</button>
		</div>
	</div>

</body>
</html>