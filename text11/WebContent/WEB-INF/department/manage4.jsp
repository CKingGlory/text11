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
<script type="text/javascript" src="js/jquery-ui.js"></script>
<script type="text/javascript">
/*$(function() {
	// 设置ul中li自动排列，可拖拽
	$(".div").sortable({
		revert : true
	});

	// 设置拖放目的地
	$("#pro div").draggable({
		connectToSortable : ".div",
		revert : "invalid",
		start : function() {
			$(this).addClass("Dselect");
		},
		stop : function() {

			var proId = $(this).data("id");
			$.ajax({
				url : "department",
				type : "post",
				data:{type:"showProDelete4",depId:${dep.id},proId:proId},
				dataType : "text",
				success : function(data) {
					if(data=="true"){
						var noPro=$("#pro").find(".Dselect");
						noPro.removeClass("Dselect");
						$("#noPro").append(noPro);
						$(this).attr("class","noPro")
					}
				}
			})
		}
	});
	
	
	$("#noPro div").draggable({
		connectToSortable : ".div",
		revert : "invalid",
		start : function() {
			$(this).addClass("Dselect");
		},
		stop : function() {

			var proId = $(this).data("id");
			$.ajax({
				url : "department",
				type : "post",
				data:{type:"showProAdd4",depId:${dep.id},proId:proId},
				dataType : "text",
				success : function(data) {
					if (data == "true") {
						var pro = $("#noPro").find(".Dselect");
						pro.removeClass("Dselect");
						$("#pro").append(pro);
						$(this).attr("class", "pro");
					}
				}
			})
		}
	});
});*/



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
	
	var proLeft=$("#pro").offset().left;
	var proTop=$("#pro").offset().top;
	var proWidth=parseFloat($("#pro").css("width"));
	var proHeight=parseFloat($("#pro").css("height"));
	var noProLeft=$("#noPro").offset().left;
	var noProTop=$("#noPro").offset().top;
	var noProWidth=parseFloat($("#noPro").css("width"));
	var noProHeight=parseFloat($("#noPro").css("height"));
	var startLeft;
	var startTop;
	 $( ".yi" ).draggable({
		  start: function() {
	        startLeft=$(this).offset().left;
	        startTop=$(this).offset().top;
	      },
	      stop: function() {
	    	  var stopLeft=$(this).offset().left;
	    	  var stopTop=$(this).offset().top;
	        if(stopLeft>=proLeft&&stopLeft<=proLeft+proWidth&&stopTop>=proTop&&stopTop<=proTop+proHeight){
	        	var pro=$(this);
	        	var proId=$(this).data("id");
	        	$.ajax({
					url:"department",
					type:"post",
					data:{type:"showProAdd2",depId:${dep.id},proId:proId},
					dataType:"text",
					success:function(data){
					
						if(data=="true"){
							pro.css("position","static");
							$("#pro").append(pro);
							pro.css("position","relative");
							pro.css("left","0");
							pro.css("top","0");
							pro.removeClass("select");
						}
					}
				})
	        }else{
	        	$(this).offset({left:startLeft,top:startTop});
	        }
	        if(stopLeft>=noProLeft&&stopLeft<=noProLeft+noProWidth&&stopTop>=noProTop&&stopTop<=noProTop+noProHeight) {
	        	var noPro=$(this);
	        	var proId=$(this).data("id");
	        	$.ajax({
					url:"department",
					type:"post",
					data:{type:"showProDelete2",depId:${dep.id},proId:proId},
					dataType:"text",
					success:function(data){
					
						if(data=="true"){
							noPro.css("position","static");
							$("#noPro").append(noPro);
							noPro.css("position","relative");
							noPro.css("left","0");
							noPro.css("top","0");
							noPro.removeClass("select");
						}
					}
				})
	        }else{
	        	$(this).offset({left:startLeft,top:startTop});
	        }
	      }
	      });

})



</script>
</head>
<body>

	<div id="main">
		<div id="pro" class="div">
			<c:forEach items="${list }" var="pro">
				<div value="${pro.id}" class="yi pro" data-id="${pro.id }">${pro.name }</div>
			</c:forEach>
		</div>
		<div id="b">
			<button id="addBatch" type="button" class="">↑↑</button>
			<button id="add" type="button" class="">↑</button>
			<button id="delete" type="button" class="">↓</button>
			<button id="deleteBatch" type="button" class="">↓↓</button>
		</div>

		<div id="noPro" class="div">
			<c:forEach items="${noList }" var="pro">
				<div value="${pro.id}" class="yi noPro" data-id="${pro.id}">${pro.name }</div>
			</c:forEach>
		</div>
	</div>

</body>
</html>