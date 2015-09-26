<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%> 
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'register.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="./CSS/signin.css">
	<script language="JavaScript" src="./JS/check.js"></script>
	<link href="./css/bootstrap.min.css" type="text/css" rel="stylesheet">	
  </head>
  
  <body>
	
	
		<form class="form-signin form-horizontal" action="<%=request.getContextPath()%>/servletregistered" name="zhuceform" onSubmit="return checkForm()" method="post">
			<h2 class="form-signin-heading">请输入注册信息</h2>
			<div class="form-group" name="zhucediv">
			<label class="control-label col-sm-3">&nbsp;&nbsp;用户：&nbsp;</label> 
			
			<input type="text"  class="form-control col-sm-4 col-sm-offset-3" placeholder="Username" name="zhuceuname" onfocus="format1()" onBlur="checkuname(this.value)"> 
			
			<font size="2px"><span id="msg"></span></font>
			</div>	
			<div class="form-group">
				<label for="inputPassword" class="sr-only col-sm-3">&nbsp;&nbsp;密码：&nbsp;</label> 
				<input type="password" id="inputPassword" class="form-control col-sm-4 col-sm-offset-3" placeholder="Password" name="pw1" onfocus="format2()" onBlur="checkpw1(this.value)">
					<font size="2px"><span id="matchpw1"></span></font>
				</div>	
					<div class="form-group">
				<label for="inputPassword3" class="sr-only col-sm-3 ">再输一次密码</label> 
				<input type="password" id="inputPassword3" class="form-control col-sm-4 col-sm-offset-3" placeholder="Password"name="pw2" onBlur="checkpw2(this.value)">
				<font size="2px"><span id="matchpw2"></span></font>
				</div>
				<div class="form-group">	
			<input class="btn btn-lg btn-primary btn-block col-sm-offset-5" type="submit"/>
			<a class="btn btn-info" href="Login.jsp" role="button">返回</a>
				</div>
		</form>

	
  </body>
</html>
