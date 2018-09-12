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
}

#image {
	width: 100px;
	height: 30px;
}
#mes {
	position: absolute;
	width: 100px;
	height: 30px;
	top: 200px
}
</style>
</head>
<body>
	<div class="from">
		<form action="user" id="f" method="post">
			<input type="hidden" name="type" value="doRegister" />
			<div id="d1">
				<label>账号</label><input id="username" type="text" name="username" />
			</div>
			<div id="d2">
				<label>密码</label><input type="password" name="password" />
			</div>
			<div id="d5">
				<label>确认</label><input type="password" name="password2" />
			</div>
			<div id="d3">
				<input type="submit" value="注册" class="btn" />
			</div>
			<div id="mes"> ${mes}</div>
		</form>
	</div>

	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
	/*$(document).ready(function() {
		$("#username").blur(function(){    
			var user= $("#username");
			var username=user.val();
			$.ajax({
				url:"user",
				type:"post",
				data:{type:"check",username:username},
				dataType:"text",
				success:function(data){
				
					if(data=="true"){
						$("#mes").html("用户名已存在");
						setTimeout(function(){
							 $("#mes").html("");
						},1500)
					}
				}
			})
		})
	})*/
	</script>

</body>
</html>