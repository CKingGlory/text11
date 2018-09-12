<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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
	<form action="department" method="post">
		<input type="hidden" name="type" value="addDep"/>
		<!--你光接收不给他参数  -->
		<label>名称</label><input type="text" name="name" /><br />
		<input type="submit"value="确认" />
	</form>
</body>
</html>