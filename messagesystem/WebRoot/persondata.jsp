<%@page import="JavaBean.teacher"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'persondata.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="./CSS/bootstrap.min.css" rel="stylesheet">
     <link href="./CSS/home.css" rel="stylesheet">
     <link href="./CSS/persondata.css" rel="stylesheet">
     <script language="JavaScript" src="./JS/alterperson.js"></script>
  </head>
  
  <body>
    <nav class="navbar navbar-inverse navbar-fixed-top">
      <div class="container-fluid">
        <div class="navbar-header">
          
          <a class="navbar-brand" href="HomePage1.jsp">首页</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
          <ul class="nav navbar-nav navbar-right">
            <li class="un"><font color="#9d9d9d">欢迎${sessionScope.username}用户</font></li>
            <li class="un"><font color="#9d9d9d">个人资料</font></li>
            <li><a href="Login.jsp">注销</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <form class="form-horizontal" action="" name="alterform">
    
    <label for="name" class="sp">姓名</label>
  <div class="form-group">
    <input type="text" class="form-control col-sm-8" id="name" name="altername" placeholder="name" value="${tea.tname}">
  </div>
  
  <label for="Password1">密码修改</label>
  <div class="form-group">
    <input type="password" class="form-control col-sm-8" id="Password1" name="alterpw1" placeholder="Password" onBlur="checkpw1(this.value)" value="${tea.password}">
  	 <span class="col-sm-4 sp" id="matchpw1"></span>
  </div>
  
  <label for="Password2">密码修改确认</label>
    <div class="form-group">  
    <input type="password" class="form-control col-sm-8" id="Password2" placeholder="Password" name="alterpw2" onBlur="checkpw2(this.value)">
    <span class="col-sm-4 sp" id="matchpw2"></span>
  </div>
  
  <label for="phone">联系方式</label>
  <div class="form-group">
    <input type="text" id="phone"  name="alterphone" class="form-control col-sm-8" placeholder="phone" value="${tea.phone}">
  </div>
  
<label for="school">学校</label>
  <div class="form-group"> 
    <input type="text" id="school" name="alterschool" class="form-control col-sm-8" placeholder="school" value="${tea.school}">
  </div>
  
  <label for="Academy">学院</label>
  <div class="form-group">
    <input type="text" id="Academy" name="alteracademy" class="form-control col-sm-8" placeholder="Academy" value="${tea.academy}">
  </div>
  
  <label for="Department">专业</label>
  <div class="form-group">
    <input type="text" id="Department" name="alterdepartment" class="form-control col-sm-8" placeholder="Department" value="${tea.department}">
  </div>
  
  <button type="button" class="btn btn-info col-sm-2" onClick="alter()">保存资料</button>
  <span class="col-sm-offset-2 col-sm-4 sp" id="msg"></span>
  
</form>
</body>
</html>
