<%@page import="java.util.List"%>
<%@ page import="entity.Employee"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
#main {
	width: 400px;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$().ready(function() {
		$("#save").click(function() {
			var emps = "";
			$(".emp").each(function(index, element) {
				var id = $(this).find("[name=id]").val();
				var name = $(this).find("[name=name]").val();
				var sex = $(this).find("[name=sex]:checked").val();
				var age = $(this).find("[name=age]").val();
				var dName = $(this).find("[name=d_name]").val();
				emps += id + "," + name + "," + sex + "," + age + ","+dName+"!";

			})
			emps = emps.substring(0, emps.length - 1);
			window.location.href = "employee?type=updateBatch2&emps=" + emps;
		})
	})
</script>
</head>
<body>
	<div id="main">
	<c:forEach items="${list}" var="emp">
		<form action="employee" method="post" class="emp">
			<input type="hidden" name="type" value="updateBatch2" /> <input
				type="hidden" name="id" value="${emp.id}" /> <label>姓名</label><input
				type="text" name="name" value="${emp.name}" /><br /> <label>性别</label><input
				type="radio" name="sex" <c:if test="${emp.sex=='男'}"> checked
				</c:if> value="男" />男 <input type="radio" name="sex"
				<c:if test="${emp.sex=='女'}"> checked </c:if> value="女" />女 <br />
			<label>年齡</label><input type="text" name="age"
				value="${emp.age}" /><br />
				<label>部门</label><select name="d_name">
		
		<c:forEach items="${depList}" var="dep">
		<option value="${dep.id}">${dep.name}</option>
		</c:forEach>
		</select><br />
		</form>
		</c:forEach>
		<input type="submit" value="保存" id="save" />
	</div>

</body>
</html>