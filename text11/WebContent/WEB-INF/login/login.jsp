<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<style type="text/css">
.from {
	width: 200px;
	margin: 300px auto;
}

/*.btn {
	margin-left: 80px;
}*/
#f {
	position: absolute;
	width: 300px;
	height: 500px;
}

body {
	background-image: url(img/l.jpg);
}

#d1 {
	position: absolute;
	width: 300px;
	height: 60px;
	font-weight: bold;
	font-size: 25px;
}

#d2 {
	position: absolute;
	width: 300px;
	height: 60px;
	font-weight: bold;
	font-size: 25px;
	top: 70px
}

#d4 {
	position: absolute;
	width: 300px;
	height: 20px;
	top: 130px
}

#d3 {
	position: absolute;
	width: 300px;
	height: 60px;
	bottom: 140px
}

#d5 {
	position: absolute;
	width: 300px;
	height: 60px;
	font-weight: bold;
	font-size: 25px;
	top: 140px
}

#f input {
	width: 220px;
	height: 30px;
	border-radius: 4px;
}

#d4 input {
	width: 250px;
	margin-left: 30px;
	bottom: 0;
	font-weight: bold;
	font-size: 20px;
	background: rgba(0, 0, 0, 0);
}

#d5 input {
	width: 220px;
	height: 30px;
	border-radius: 4px;
	position: absolute;
}

#image {
	width: 90px;
	height: 30px;
	margin-left: 290px;
	margin-top: -30px;
}

#mes {
	position: absolute;
	width: 100px;
	height: 30px;
	top: 200px
}

#d6 {
	position: absolute;
	width: 100px;
	height: 30px;
	top: 260px
}

#btn2 {
	width: 220px;
	height: 30px;
	border-radius: 4px;
}
</style>
</head>
<body>
	<div class="from">
		<form action="user" id="f" method="post">
			<input type="hidden" name="type" value="doLogin" />
			<div id="d1">
				<label>账号</label><input type="text" name="username" value="${name }" />
			</div>
			<div id="d2">
				<label>密码</label><input type="password" name="password" />
			</div>
			<div id="d5">
				<label>验证</label><input type="text" name="random" /> <img
					id="image" src="user?type=randomImage" />
			</div>
			<div id="mes">${mes}</div>
			<div id="d6">
				<button id="btn2" type="button">注册</button>
			</div>
			<div id="d3">
				<input type="submit" value="登录" class="btn" />
			</div>
		</form>
	</div>

	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		if (self != top) {
			top.location = "user?type=showLogin"
		}
		$().ready(function() {
			$("#image").click(function() {
				$(this).attr("src", "user?type=randomImage&" + Math.random());
			})
			$("#btn2").click(function() {
				location.href = "user?type=showRegister";
			})
		})
	</script>








</body>
</html>