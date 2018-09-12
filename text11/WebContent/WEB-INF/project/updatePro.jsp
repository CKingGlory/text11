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
#s1 {
	width: 160px;
}
</style>
</head>
<body>
	<form action="project" method="post">
	<input type="hidden" name="type" value="updatePro" />
	<input type="hidden" name="id" value="${pro.id}" />
		<label>名称</label><input type="text" name="name" value="${pro.name}" /><br />  
		<input type="submit" value="确认" />
	</form>
</body>
</html>