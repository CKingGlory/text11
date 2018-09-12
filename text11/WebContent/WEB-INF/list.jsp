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
	background: #337ab7;
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
<script type="text/javascript" src="js/emp.js">
	
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

    <div id="ss">
            姓名<input type="text" class="text1" />
            性别<select class="text2">
            <option>男</option>
            <option>女</option>
            </select>
           年龄<input type="text" class="text3" /> 
    <button id="search" type="button" class="">搜索</button>
    </div>

	<div id="main">
		<table class="c1" id="emp">
			<tr>
				<th>姓名</th>
				<th>性别</th>
				<th>年龄</th>
			</tr>

			<c:forEach items="${paging}" var="emp">
				<tr data-id="${emp.id}">
					<td id="d1" class="selete">${emp.name}</td>
					<td id="d2" class="selete">${emp.sex}</td>
					<td id="d3" class="selete">${emp.age}</td>

				</tr>
			</c:forEach>
		</table>

		<ul class="pagination">
			<li><a href="employee?ye=1">首页</a></li>
			<li id="pre" <c:if test= "${page.currentPage<1}" > class="disabled"</c:if>><a
				href="employee?ye=${page.currentPage-1}">上一页</a></li>
			<c:forEach begin="${page.star}" end="${page.totalPage}" varStatus="status">
				<li <c:if test="${page.currentPage==status.index }">class="active"</c:if>><a
					href="employee?ye=${status.index }">${status.index}</a></li>
			</c:forEach>
			<li id="next"><a href="employee?ye=${page.currentPage+1}">下一页</a></li>
			<input type="text" />
			<input type="submit" value="跳转" />
		</ul>
		<div>
			<button id="showAdd" type="button" class="">添加</button>
			<button id="showUpdate" type="button" class="">修改</button>
			<button id="delete" type="button" class="">删除</button>
			<button id="showUpdateBatch1" type="button" class="">批量修改1</button>
			<button id="showUpdateBatch2" type="button" class="">批量修改2</button>
			<button id="updateBatch3" type="button" class="">批量修改3</button>
			<button id="deleteBatch" type="button" class="">批量删除</button>
		</div>
	</div>

</body>
</html>