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

#photos img {
	width: 100px;
	height: 100px;
}
</style>
</head>
<body>
	<form action="employee?type=add2" method="post">
		<!--你光接收不给他参数  -->
		<label>姓名</label><input type="text" name="name" /><br /> <label>性别</label><select
			id="s1" name="select">
			<option value='男'>男</option>
			<option value='女'>女</option>
		</select><br /> <label>年齡</label><input type="text" name="age" /><br /> <label>部门</label>
		<select name="d_name">

			<c:forEach items="${depList}" var="dep">
				<option value="${dep.id}">${dep.name}</option>
			</c:forEach>
		</select><br /> 
		<input type="file" name="photo"><input id="upload" type="button" value="上传" /><br />
		<div id="photos"></div>
		<input type="submit" value="保存" class="btn btn-primary" />
	</form>


	<script type="text/javascript" src="js/jquery.js"></script>
	<script type="text/javascript">
		$().ready(function() {
			$("#upload").click(function() {
				var formData = new FormData();
				formData.append("photo", $("[name=photo]")[0].files[0]);
				$.ajax({
					url:"employee?type=upload",
					type:"post",
					data:formData,
					cache : false,
					processData : false,
					contentType : false,
					dataType:"text",
					success:function(data){
						var str = "<img src='pic/"+data+"' value='"+data+"'/>";
						str+="<input type='hidden' name='picture' value='"+data+"'/>";
						$("#photos").append(str);
					}
				})
			})
			
			$(document).on("click","#photos img",function(){
				var ppp=$(this);
				var removePic=ppp.next().val();
				$.ajax({
					url:"employee?type=remove",
					type:"post",
					data:{removePic:removePic},
					dataType:"text",
					success:function(data){
						ppp.remove();
					}
				})

			})

		})
	</script>



</body>
</html>