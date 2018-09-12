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
<script src="https://cdn.bootcss.com/jquery/2.1.1/jquery.min.js"></script>
<script
	src="https://cdn.bootcss.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<style>
#main {
	width: 600px;
	margin: 40px auto;
}

#pro, #noPro {
	width: 600px;
	height: 300px;
	border: 1px solid #337ab7;
	border-radius: 3px;
}

#b {
	width: 250px;
	margin: 20px auto;
}

.yi {
	background: #337ab7;
	height: 40px;
	line-height: 40px;
	float: left;
	margin-left: 10px;
	color: #fff;
	padding: 0 10px;
	margin-top: 10px;
	border-radius: 3px;
}

.select {
	background: #d9534f;
}

#dep .select {
	background: #33FFFF;
}

#dep td {
	width: 150px;
	color: #000;
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
<script type="text/javascript" src="js/dep.js"></script>
</head>
<body>

	<div id="ss">
		<form action="department" method="post">
			<!--你光接收不给他参数  -->
			<label>名称</label><input type="text" name="name" placeholder="请输入名称"
				value="${d.name }" /> <label>人数</label><input type="text"
				name="emp_count" placeholder="请输入人数"
				value="${d.emp_count!=-1?d.emp_count:''}" /> <input type="submit"
				value="搜索" class="btn btn-primary" />
		</form>
	</div>


	<div id="main">
		<table class="c1" id="dep">
			<tr>
				<th>id</th>
				<th>名称</th>
				<th>人数</th>
			</tr>

			<c:forEach items="${key}" var="dep">
				<tr data-id="${dep.id}">
					<td id="d1" class="selete">${dep.id}</td>
					<td id="d2" class="selete"><a
						href="department?type=showManage&name=${dep.name}&id=${dep.id}">${dep.name}</a></td>
					<td id="d3" class="selete">${dep.emp_count}</td>
				</tr>
			</c:forEach>
		</table>

		<ul class="pagination">
			<li><a href="department?ye=1">首页</a></li>
			<li id="pre" <c:if test= "${p.ye<1}" > class="disabled"</c:if>><a
				href="department?ye=${p.ye-1}&name=${d.name}&emp_count=${d.emp_count!=-1?d.emp_count:''}">上一页</a></li>
			<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
				<li
					<c:if test="${p.ye==status.index }&name=${d.name}&emp_count=${d.emp_count!=-1?d.emp_count:''}">class="active"</c:if>><a
					href="department?ye=${status.index }">${status.index}</a></li>
			</c:forEach>
			<li id="next"><a
				href="department?ye=${p.ye+1}&name=${d.name}&emp_count=${d.emp_count!=-1?d.emp_count:''}">下一页</a></li>
			<li><a href="department?ye=${p.maxYe}">尾页</a></li>
			<input type="text" />
			<input type="submit" value="跳转" />
		</ul>

		<div>
			<button id="showAdd" type="button" class="btn btn-primary">添加</button>
			<button id="showUpdate" type="button" class="btn btn-primary">修改</button>
			<button id="delete" type="button" class="btn btn-primary">删除</button>
			<button id="manage2" type="button" class="btn btn-primary">管理</button>
			<button id="manage3" type="button" class="btn btn-primary">管理1</button>
			<button id="manage4" type="button" class="btn btn-primary">管理2</button>
			<button id="manage5" type="button" class="btn btn-primary">管理3</button>

		</div>

	</div>
	<!-- 模态框内容 -->
	<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			
			
		</div>
	</div>
</div>

</body>
</html>