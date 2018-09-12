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
</style>
</head>
<body>
	<form action="employee" method="post">
	<input type="hidden" name="type" value="updateBatch1" />
		<input type="hidden" name="ids" value="${ids}" />
		
		<label>姓名</label><input type="text" name="name" value="${emp.name}" /><br /> 
		<label>性别</label><input type="radio" name="sex" <c:if test="${emp.sex=='男'}"> checked </c:if> value="男" />男
		<input type="radio" name="sex" <c:if test="${emp.sex=='女'}"> checked </c:if> value="女" />女 <br />
		<label>年齡</label><input type="text" name="age" value="${emp.age}" /><br /> 
		<label>部门</label><select name="d_name">
		
		<c:forEach items="${depList}" var="dep">
		<option value="${dep.id}">${dep.name}</option>
		</c:forEach>
		</select><br /> 
		<input type="submit" value="确认" />
	</form>
</body>
</html>