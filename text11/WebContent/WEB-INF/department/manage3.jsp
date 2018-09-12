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
#main {
	width: 700px;
	margin: 40px auto;
}

#pro, #noPro {
	width: 700px;
	height: 300px;
	border: 1px solid #337ab7;
	border-radius: 3px;
}

#b {
	width: 250px;
	margin: 20px auto;
}

#add, #delete,#addBatch,#deleteBatch {
	width: 50px;
	height: 30px;
	background: #BBFFEE;
	margin-rigth: 80px;
}

.yi {
	background: #337ab7;
	height: 40px;
	line-height: 40px;
	float: left;
	margin-left: 10px;
	color: #fff;
	padding: 0 10px;
	margin-top: 10px;
	border-radius: 3px;
}

.select {
	background: #d9534f;
}
</style>
<script type="text/javascript" src="js/jquery.js"></script>
<script type="text/javascript">
	$(document).ready(function() {

		$(".yi").click(function(){
			$(this).addClass("ccc");
			$(this).toggleClass("select");
			
		})
		$("#add").click(function(){
			if($("#noPro").find(".select").length>0){
				var proId=$("#noPro").find(".select").data("id");
				$.ajax({
					url:"department",
					type:"post",
					data:{type:"showProAdd2",depId:${dep.id},proId:proId},
					dataType:"text",
					success:function(data){
					
						if(data=="true"){
							var div=$("#noPro").find(".select");
							div.removeClass("select");
								$("#pro").append(div);
						}
					}
				})
			}else{
				alert("请选择数据！");
			}
		})
		$("#addBatch").click(function(){
			
			if($("#noPro").find(".select").length>0){
				var str="";
				$(".ccc").each(function(index, element) {
				var proId=$(this).data("id");
				
				str+="("+${dep.id}+","+proId+")"+","
				})
				str=str.substring(0,str.length-1);
				$.ajax({
					url:"department",
					type:"post",
					data:{type:"showProAddBatch2",depId:${dep.id},str:str},
					dataType:"text",
					success:function(data){
					
						if(data=="true"){
							var div=$("#noPro").find(".select");
							div.removeClass("select");
								$("#pro").append(div);
						}
					}
				})
			}else{
				alert("请选择数据！");
			}
		})
		
		$("#delete").click(function(){
			if($("#pro").find(".select").length>0){
				var proId=$("#pro").find(".select").data("id");
				$.ajax({
					url:"department",
					type:"post",
					data:{type:"showProDelete2",depId:${dep.id},proId:proId},
					dataType:"text",
					success:function(data){
					
						if(data=="true"){
							var div=$("#pro").find(".select");
							div.removeClass("select");
								$("#noPro").append(div);
						}
					}
				})
			}else{
				alert("请选择数据！");
			}
		})
		
		$("#deleteBatch").click(function(){
			if($("#pro").find(".select").length>0){
				var proIds="";
				$(".ccc").each(function(index, element) {
				var proId=$(this).data("id");
				
				proIds+=proId+","
				})
				proIds=proIds.substring(0,proIds.length-1);
				$.ajax({
					url:"department",
					type:"post",
					data:{type:"showProDeleteBatch2",depId:${dep.id},proIds:proIds},
					dataType:"text",
					success:function(data){
					
						if(data=="true"){
							var div=$("#pro").find(".select");
							div.removeClass("select");
								$("#noPro").append(div);
						}
					}
				})
			}else{
				alert("请选择数据！");
			}
		})
		
		
	})
</script>
</head>
<body>

	<div id="main">
		<div id="pro">
			<c:forEach items="${list }" var="pro">
				<div value="${pro.id}" class="yi" data-id="${pro.id }">${pro.name }</div>
			</c:forEach>
		</div>
		<div id="b">
			<button id="addBatch" type="button" class="">↑↑</button>
			<button id="add" type="button" class="">↑</button>
			<button id="delete" type="button" class="">↓</button>
			<button id="deleteBatch" type="button" class="">↓↓</button>
		</div>

		<div id="noPro">
			<c:forEach items="${noList }" var="pro">
				<div value="${pro.id}" class="yi" data-id="${pro.id}">${pro.name }</div>
			</c:forEach>
		</div>
	</div>

</body>
</html>