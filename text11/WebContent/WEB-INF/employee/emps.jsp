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
#emp .select {
	background: #33FFFF;
}

#emp td {
	width: 180px;
}

#main {
	width: 900px;
	margin: 40px auto;
}

#ss {
	width: 900px;
	margin: 40px auto;
}

#ss input {
	width: 120px;
}

#ss select {
	width: 120px;
	height: 26px;
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

#emp img {
	width: 30px;
	height: 30px;
}

/*.img:hover {
	transform: scale(8);
}*/

#bigPhoto{
display:none;
position:absolute;
}
#bigPhoto img{
width:100px;
height:100px;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript" src="js/emp.js">
	
</script>
<script type="text/javascript">

if(${p.ye} <=1){
	$("#pre").addClass("disabled");
	$("#pre").find("a").attr("onclick","return false");
	}
if(${p.ye}>=${p.maxYe}){
$("#next").addClass("disabled");
$("#next").find("a").attr("onclick","return false");
 }
 
 
 
 
 
</script>

</head>
<body>

	<div id="ss">
		<form action="employee" method="post">
			<!--你光接收不给他参数  -->
			<label>姓名</label><input type="text" name="name" value="${e.name }" />
			<label>性别</label><select id="s1" name="sex">
				<option></option>
				<option value="男" <c:if test="${e.sex=='男'}">selected </c:if>>男</option>
				<option value="女" <c:if test="${e.sex=='女'}">selected </c:if>>女</option>
			</select> <label>年龄</label><input type="text" name="age"
				value="${e.age!=-1?e.age:''}" /> <label>部门</label><select
				name="d_name">
				<option></option>
				<c:forEach items="${depList}" var="dep">
					<option value="${dep.id}"
						<c:if test="${dep.id==e.dep.id}">selected </c:if>>${dep.name}</option>
				</c:forEach>
			</select> <input type="submit" value="搜索" class="btn btn-primary" />
		</form>
	</div>

	<div id="main">
		<table class="c1" id="emp">
			<tr>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
				<th>部门</th>
				<th>头像</th>
			</tr>

			<c:forEach items="${key}" var="emp">
				<tr data-id="${emp.id}">
					<td id="d1" class="selete">${emp.name}</td>
					<td id="d2" class="selete">${emp.sex}</td>
					<td id="d3" class="selete">${emp.age}</td>
					<td id="d4" class="selete">${emp.dep.name}</td>
					<td id="d5" class="selete"><c:if test="${not empty emp.picture }"><img src="pic/${emp.picture}" /></c:if></td>

				</tr>
			</c:forEach>
		</table>

		<ul class="pagination">
			<li><a href="employee?ye=1">首页</a></li>
			<li id="pre" <c:if test= "${p.ye<1}" > class="disabled"</c:if>><a
				href="employee?ye=${p.ye-1}&name=${e.name}&sex=${e.sex}&age=${e.age!=-1?e.age:''}&d_name=${e.dep.name }">上一页</a></li>
			<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
				<li <c:if test="${p.ye==status.index }">class="active"</c:if>><a
					href="employee?ye=${status.index }&name=${e.name}&sex=${e.sex}&age=${e.age!=-1?e.age:''}&d_name=${e.dep.name }">${status.index}</a></li>
			</c:forEach>
			<li id="next"><a
				href="employee?ye=${p.ye+1}&name=${e.name}&sex=${e.sex}&age=${e.age!=-1?e.age:''}&d_name=${e.dep.name }">下一页</a></li>
			<li><a href="employee?ye=${p.maxYe}">尾页</a></li>
			<input type="text" />
			<input type="submit" value="跳转" class="btn btn-primary" />
		</ul>
		<div>
			<button id="showAdd" type="button" class="btn btn-primary">添加1</button>
			<button id="showAdd2" type="button" class="btn btn-primary">添加2</button>
			<button id="showUpdate" type="button" class="btn btn-primary">修改</button>
			<button id="delete" type="button" class="btn btn-primary">删除</button>
			<button id="showUpdateBatch1" type="button" class="btn btn-primary">批量修改1</button>
			<button id="showUpdateBatch2" type="button" class="btn btn-primary">批量修改2</button>
			<button id="updateBatch3" type="button" class="btn btn-primary">批量修改3</button>
			<button id="deleteBatch" type="button" class="btn btn-primary">批量删除</button>
		</div>
	</div>
	<div id="bigPhoto">ssss<img src=""/></div>

</body>
</html>