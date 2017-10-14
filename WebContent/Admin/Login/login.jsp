<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
  <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
 	    <title>登录界面</title>
  		<base href="<%=basePath%>"> 
		<meta name="renderer" content="webkit">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
		<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="format-detection" content="telephone=no">
    <!-- 引入css样式 -->
	<link rel="stylesheet" type="text/css" href="Admin/Login/css/style1.css" />
	<style>
	body{height:100%;background:#16a085;overflow:hidden;}
	canvas{z-index:-1;position:absolute;}
	</style>
	<!-- 引入js样式 -->
	<script src="Admin/Login/js/jquery.js"></script>
	<script src="Admin/Login/js/jquery-1.8.3.js"></script>
	<script src="Admin/Login/js/verificationNumbers.js"></script>
	<script src="Admin/Login/js/Particleground.js"></script>
	<script src="Admin/Login/js/login.js"></script>
	<script>
	function login() {
		var account=$("#username1").val();
	    var password=$("#password1").val();
	     if(password!=null&&account!=null){
	   	  $.getJSON("./login.jhtm",{
	   		    method: "login",
	   	  		account:account,
	   	  		password:password
	   	  	},function(json){
	   	  		//alert(json.result);
	   	  		if(json.result=="登陆成功"){
	   	  			window.location.href='Admin/Index/index.jsp?user_id='+json.data.user_id+'&&role_id='+json.data.role_id+'';   
	   	  		}else{
	   				alert("用户名或密码错误，重新输入！");
	 
	   			}
	   	  })
	     }else{
	   	  alert("请先输入全部信息！"); 
	     }
	}  
	</script>
</head>
<body>
   <dl class="admin_login">
 <dt>
  <strong style="color:#ffffff">管理系统</strong>
 </dt>
 <dd class="user_icon">
  <input type="text" placeholder="账号" class="login_txtbx" id="username1"/>
 </dd>
 <dd class="pwd_icon">
  <input type="password" placeholder="密码" class="login_txtbx" id="password1"/>
 </dd>
 
 <!-- <dd class="val_icon">
  <div class="checkcode">
    <input type="text" id="J_codetext" placeholder="验证码" maxlength="4" class="login_txtbx">
    <canvas class="J_codeimg" id="myCanvas" onclick="createCode()"></canvas>
   </div>
 </dd> -->
 <dd>
  <input type="button" value="立即登陆" class="submit_btn" onclick="login();"/>
 </dd>
</dl>
</body>
</html>