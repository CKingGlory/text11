<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
#s1{
width: 160px;
}

</style>
</head>
<body>
	<form action="employee?type=add" method="post" enctype="multipart/form-data">
		<!--你光接收不给他参数  -->
		<label>姓名</label><input type="text" name="name" /><br />
	    <label>性别</label><select id="s1" name="select">
	    <option value='男'>男</option>
	    <option value='女'>女</option>
	    </select><br />
		<label>年齡</label><input type="text" name="age" /><br />
		<label>部门</label>
		<select name="d_name">
		
		<c:forEach items="${depList}" var="dep">
		<option value="${dep.id}">${dep.name}</option>
		</c:forEach>
		</select><br />
		<input type="file" name="myFile"><br />
		<input type="submit"value="确认" class="btn btn-primary" />
	</form>
</body>
</html>