<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'Login.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">

	<link rel="stylesheet" type="text/css" href="./CSS/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="./CSS/cover.css">
	<script language="JavaScript" src="./JS/loginjs.js"></script>

  </head>
  
  <body>
  	<% session.invalidate();%>
  <div class="site-wrapper">
	<div class="site-wrapper-inner">
		<form class="form-horizontal" action="<%=request.getContextPath()%>/loginservlet" name="myform" method="post" onSubmit="return vaildate(this)">
			<div class="form-group">
				<label for="inputEmail3" class="col-sm-2 control-label"><font color="black">用户名</font></label>
				<div class="col-sm-4">
					<input type="text" class="form-control" id="inputEmail3"
						placeholder="用户名不能空" name="username"/>
				</div>
			</div>
			<div class="form-group">
				<label for="inputPassword3" class="col-sm-2 control-label"><font color="black">密码</font></label>
				<div class="col-sm-4">
					<input type="password" class="form-control" id="inputPassword3"
						placeholder="密码不能为空" name="password"/>
				</div>
			</div>
	<!--  	<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4">
					<div class="checkbox">
						<label> <input type="checkbox"> <font color="black">记住密码</font>
						</label>
					</div>
				</div>
			</div>-->
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-4">
				<% String i=null;
					i=request.getParameter("pan");
				if(i!=null){
					if(i.equals("1")){%>
				<font color="black" name="tip">用户名或密码错误</font>
				<% }else{%>
				<font color="black" name="tip"></font>
				<%}} %>
				<%String servlet=(String)request.getServletPath();%>
	<%=servlet%>
	<br>
	<!--取得上下文资源路径  /test-->
	<%String context=(String)request.getContextPath();%>
	<%=context%>
				<% String path1=application.getRealPath("/");%>
	<%=path1%>
				</div>
			</div>
			<div class="form-group">
				<div class="col-sm-offset-2 col-sm-2">
					<input type="submit" class="btn btn-info" value="登陆"/>
					
				</div>
				<div class="col-sm-2">
					<a class="btn btn-info" href="register.jsp" role="button">注册</a>
				</div>
			</div>
		</form>
	</div>
	</div>

</body>
</html>
