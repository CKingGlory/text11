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

#ss input {
	width: 120px;
}

#ss select {
	width: 120px;
	height: 26px;
}

#main {
	width: 1000px;
	margin: 40px auto;
}

#ss {
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
	$(document)
			.ready(
					function() {
						var selectId = -1;

						$("tr").click(function() {
							$(this).toggleClass("select");

							// selectId.$(this).children.eq(0).text();
							selectId = $(this).data("id");
						})
						$("tr")
								.dblclick(
										function() {
											$(this).unbind("dblclick");
											$(this).addClass("save");
											var td = $(this).find("td").eq(4);
											var value = td.text();
											var input = $("<input type='text' name='value' value='"+value+"'/>");
											td.html(input);
										})
						$("#save")
								.click(
										function() {
											var values = "";
											$(".save")
													.each(
															function(index,
																	element) {
																var id = $(this)
																		.data(
																				"id");
																var value = $(
																		this)
																		.find(
																				"[name=value]")
																		.val();
																values += id
																		+ ","
																		+ value
																		+ "!";
															})
											values = values.substring(0,
													values.length - 1);
											window.location.href = "score?type=save&values="
													+ values;
										})
					})
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
		<form action="score" method="post">
			<!--你光接收不给他参数  -->
			<label>姓名</label><input type="text" name="emp_name" value="${s.emp.name }" /> 
			<label>部门</label><select id="dep" name="dep_id">
				<option></option>
				<c:forEach items="${depList}" var="dep">
					<option value="${dep.id}" <c:if test="${dep.id==s.dep.id}">selected </c:if>>${dep.name}</option>
				</c:forEach>
			</select> 
			<label>项目</label><select name="pro_id">
				<option></option>
				<c:forEach items="${proList}" var="pro">
					<option value="${pro.id}" <c:if test="${pro.id==s.pro.id}">selected </c:if>>${pro.name}</option>
				</c:forEach>
			</select> 
			<label>分数</label><input type="text" name="value" value="${c.value!=-1?c.value:''}" /> 
			<label>等级</label> <select name="grade" value="${s.grade }">
				<option></option>
				<option <c:if test="${s.grade=='优秀'}">selected </c:if>>优秀</option>
				<option <c:if test="${s.grade=='良好'}">selected </c:if>>良好</option>
				<option <c:if test="${s.grade=='一般'}">selected </c:if>>一般</option>
				<option <c:if test="${s.grade=='及格'}">selected </c:if>>及格</option>
				<option <c:if test="${s.grade=='不及格'}">selected </c:if>>不及格</option>
			</select> 
			<input type="submit" value="搜索" />
		</form>
	</div>

	<div id="main">
		<table class="c1" id="sco">
			<tr>
				<th>id</th>
				<th>姓名</th>
				<th>部门</th>
				<th>项目</th>
				<th id="fen">分数</th>
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
			<li><a href="score?ye=1">首页</a></li>
			<li id="pre" <c:if test= "${p.ye<1}" > class="disabled"</c:if>><a
				href="score?ye=${p.ye-1}&emp_name=${s.emp.name}&dep_id=${s.dep.id}&pro_id=${s.pro.id}&value=${s.value!=-1?s.value:''}&grade=${s.grade}">上一页</a></li>
			<c:forEach begin="${p.beginYe}" end="${p.endYe}" varStatus="status">
				<li <c:if test="${p.ye==status.index }">class="active"</c:if>><a
					href="score?ye=${status.index }&emp_name=${s.emp.name}&dep_id=${s.dep.id}&pro_id=${s.pro.id}&value=${s.value!=-1?s.value:''}&grade=${s.grade}">${status.index}</a></li>
			</c:forEach>
			<li id="next"><a
				href="score?ye=${p.ye+1}&emp_name=${s.emp.name}&dep_id=${s.dep.id}&pro_id=${s.pro.id}&value=${s.value!=-1?s.value:''}&grade=${s.grade}">下一页</a></li>
			<li><a href="score?ye=${p.maxYe}">尾页</a></li>
			<input type="text" />
			<input type="submit" value="跳转" />
		</ul>
		<div>
			<button id="save" type="button" class="">保存</button>
		</div>
	</div>

</body>
</html>