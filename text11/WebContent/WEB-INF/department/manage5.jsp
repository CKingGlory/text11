<%@page import="util.Pagination"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<style>
#project {
	width: 700px;
	margin: 40px auto;
	background: #fff;
}

#project #pro,#project #noPro {
	width: 700px;
	height: 300px;
	border: 1px solid #337ab7;
	border-radius: 3px;
}

#b {
	width: 250px;
	margin: 20px auto;
}

#project #add,#project #delete,#project #addBatch,#project #deleteBatch {
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
<script type="text/javascript">
	$(document).ready(function() {

		$(".yi").click(function(){
			$(this).addClass("ccc");
			$(this).toggleClass("select");
			
		})
		$("#project #add").click(function(){
			if($("#project #noPro").find(".select").length>0){
				var proId=$("#project #noPro").find(".select").data("id");
				$.ajax({
					url:"department",
					type:"post",
					data:{type:"showProAdd2",depId:${dep.id},proId:proId},
					dataType:"text",
					success:function(data){
					
						if(data=="true"){
							var div=$("#project #noPro").find(".select");
							div.removeClass("select");
								$("#project #pro").append(div);
						}
					}
				})
			}else{
				alert("请选择数据！");
			}
		})
		
		
		$("#project #delete").click(function(){
			if($("#project #pro").find(".select").length>0){
				var proId=$("#project #pro").find(".select").data("id");
				$.ajax({
					url:"department",
					type:"post",
					data:{type:"showProDelete2",depId:${dep.id},proId:proId},
					dataType:"text",
					success:function(data){
					
						if(data=="true"){
							var div=$("#project #pro").find(".select");
							div.removeClass("select");
								$("#project #noPro").append(div);
						}
					}
				})
			}else{
				alert("请选择数据！");
			}
		})
	})
		
</script>

	<div id="project">
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