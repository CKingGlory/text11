<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<link rel="stylesheet" href="iconfont/iconfont.css" />
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	var websocket = null;

	//判断当前浏览器是否支持WebSocket
	if ('WebSocket' in window) {
		websocket = new WebSocket("ws://192.168.0.162:8080/text11/websocket");
	} else {
		alert('没有建立websocket连接')
	}

	//连接发生错误的回调方法
	websocket.onerror = function() {
		//setMessage("错误");
	};

	//连接成功建立的回调方法
	websocket.onopen = function(event) {
		//setMessage("建立连接");
	}

	//接收到消息的回调方法
	websocket.onmessage = function(event) {
		$("#top").html(event.data);
	}

	//连接关闭的回调方法
	websocket.onclose = function() {
		//setMessage("close");
	}

	//监听窗口关闭事件，当窗口关闭时，主动去关闭websocket连接，防止连接还没断开就关闭窗口。
	window.onbeforeunload = function() {
		websocket.close();
	}
	//关闭连接
	function closeWebSocket() {
		websocket.close();
	}

	$().ready(function() {
		$(".yi").click(function() {
			$(this).next().slideToggle(500);
		})
	})
</script>
</head>


<style>
* {
	margin: 0;
	padding: 0;
}

#from {
	margin-left: 50px;
}

#right {
	margin-left: 250px;
	width: 900px;
	height: 600px;
	float: left;
}

#left {
	width: 250px;
	height: 600px;
	float: left;
	background: #200000;
	border-radius: 3px;
}

#top, #bottom {
	background: #000000;
	height: 100px;
	color: #fff;
}

#main {
	height: 600px;
}

.yi {
	margin-left: 30px;
	width: 170px;
	height: 40px;
	text-align: center;
	line-height: 40px;
	color: #000;
	margin-top: 10px;
	background: #66FFFF;
	border-radius: 3px;
	background: #66FFFF;
}

#er {
	list-style: none;
	width: 200px;
}

#er li {
	margin-top: 10px;
	text-align: center;
	background: #DDFF77;
	color: #fff;
	height: 40px;
	line-height: 40px;
	font-size: 14px;
	margin-left: 30px;
	border-radius: 3px;
}

#er a {
	color: #000;
}

a {
	text-decoration: none;
	color: #fff;
}
</style>
<body>
	<div id="con">
		<div id="top">人数${applicationScope.num}</div>
		<div id="main">
			<div id="left">
				<div class="yi">
					<i class="iconfont">&#xe61c;</i>员工管理
				</div>
				<ul id="er">
					<li><a href="employee" target="right">员工管理</a></li>
					<li><a href="employee?type=showAdd" target="right">添加员工</a></li>
				</ul>
				<div class="yi">
					<i class="iconfont">&#xe602;</i>部门管理
				</div>
				<ul id="er">
					<li><a href="department" target="right">部门管理</a></li>
					<li><a href="department?type=showAdd" target="right">添加部门</a></li>
				</ul>
				<div class="yi">
					<i class="iconfont">&#xe60e;</i>项目管理
				</div>
				<ul id="er">
					<li><a href="project" target="right">项目管理</a></li>
					<li><a href="project?type=showAdd" target="right">添加项目</a></li>
				</ul>
				<div class="yi">
					<i class="iconfont">&#xe735;</i>绩效管理
				</div>
				<ul id="er">
					<li><a href="score?type=search1" target="right">绩效查看</a></li>
					<li><a href="score" target="right">绩效管理</a></li>
				</ul>

			</div>
			<iframe id="right" name="right" scrolling="no" frameborder="0"
				src="employee"></iframe>
		</div>
		<div id="bottom"></div>

	</div>
</body>
</html>

<!-- 	<%//int i = 0;
			//application.removeAttribute("i");
			//if (application.getAttribute("i") != null) {
			//	i = (Integer) application.getAttribute("i");
			//}
			//if (session.isNew()) {

			//	i++;
			//}
			//application.setAttribute("i", i);

			//int num=0;
			//String ip=request.getRemoteAddr();
			//Set<String> set =new HashSet<>();
			//if(application.getAttribute("set")!=null){
			//	set=(Set<String>)application.getAttribute("set");
			//}
			//set.add(ip);
			//num=set.size();
			//application.setAttribute("set", set);%> -->