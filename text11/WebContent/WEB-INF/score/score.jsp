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
#sco .select {
	background: #33FFFF;
}

#sco td {
	width: 150px;
}


#main {
	width: 1000px;
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

</script>
<script type="text/javascript">
	/*if(${p.ye} <=1){
	 $("#pre").addClass("disabled");
	 $("#pre").find("a").attr("onclick","return false");
	 }
	 if(${p.ye}>=${p.maxYe}){
	 $("#next").addClass("disabled");
	 $("#next").find("a").attr("onclick","return false");
	 }*/
</script>

</head>
<body>

	<div id="main">
		<table class="c1" id="sco">
			<tr>
				<th>id</th>
				<th>姓名</th>
				<th>部门</th>
				<th>项目</th>
				<th>分数</th>
				<th>等级</th>

			</tr>

			<c:forEach items="${key}" var="sco">
				<tr data-id="${sco.id}">
					<td id="d1" class="selete">${sco.emp.id}</td>
					<td id="d2" class="selete">${sco.emp.name}</td>
					<td id="d3" class="selete">${sco.dep.name}</td>
					<td id="d4" class="selete">${sco.pro.name}</td>
					<td id="d5" class="selete">${sco.value}</td>
					<td id="d6" class="selete">${sco.grade}</td>
				</tr>
			</c:forEach>
		</table>

		<ul class="pagination">
			<li><a href="score?type=search1&&ye=1">首页</a></li>
			<li id="pre" <c:if test= "${p.ye<1}" > class="disabled"</c:if>><a
				href="score?type=search1&&ye=${p.ye-1}">上一页</a></li>
			<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
				<li <c:if test="${p.ye==status.index }">class="active"</c:if>><a
					href="score?type=search1&&ye=${status.index }">${status.index}</a></li>
			</c:forEach>
			<li id="next"><a
				href="score?type=search1&&ye=${p.ye+1}">下一页</a></li>
			<li><a href="score?type=search1&&ye=${p.maxYe}">尾页</a></li>
			<input type="text" />
			<input type="submit" value="跳转" />
		</ul>
	</div>

</body>
</html>