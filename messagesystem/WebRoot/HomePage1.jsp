<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ page import="JavaBean.*"%>
<%@ taglib uri="localhost/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'HomePage1.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<!-- Bootstrap core CSS -->
    <link href="./CSS/bootstrap.min.css" rel="stylesheet">
     <link href="./CSS/home.css" rel="stylesheet">
     <link href="./CSS/stumessage.css" rel="stylesheet">
      <script src="./JS/showstumessage.js"></script>
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
            <li><a href="<%=request.getContextPath()%>/servletperson">个人资料</a></li>
            <li><a href="Login.jsp">注销</a></li>
          </ul>
        </div>
      </div>
    </nav>
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li class="active"><a>&nbsp;&nbsp;&nbsp;学生信息 <span class="sr-only"></span></a></li>
            <li><a href="SingleScore.jsp">&nbsp;&nbsp;&nbsp;单科成绩分析</a></li>
            <li><a href="AllScore.jsp">&nbsp;&nbsp;&nbsp;综合成绩分析</a></li>
            <li><a href="BeforeScore.jsp">&nbsp;&nbsp;&nbsp;课程知识点</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main ma">
        <h1 class="page-header page-head">学生信息</h1>
        <form action="<%=request.getContextPath()%>/servletshowstu" method="post" name="formname">
			<select  onChange="stud(this.value)" class="drop">
			    <option id="ban">${banname==null?"请选择班级":banname}</option>
				<c:forEach items="${blist}" var="banji">
	  				<option value="${banji.bname==banname?"":banji.bname}">${banji.bname==banname?"请选择班级":banji.bname}</option>
	  			</c:forEach>
			</select>
		</form>
        <div class="table-responsive res">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>编号</th>
                  <th>学生姓名</th>
                  <th>学号</th>
                  <!--   <th>班级</th>-->
                  <th>排名</th>
                </tr>
              </thead>
              <tbody>
					<% List<student> lStudents=(ArrayList)request.getAttribute("lStudents"); 
                  		 Map<String, String> sMap=(Map)request.getAttribute("sMap"); 
             		if(lStudents!=null&&sMap!=null){
                  		Iterator iter1=lStudents.iterator();
  					while(iter1.hasNext()){
  					student stud=(student)iter1.next();
  			 			pageContext.setAttribute("stu", stud);
  			 			 %>
                  		<tr>
                  			<td>${stu.sid}</td>
                  			<td>${stu.sname}</td>
                  			<td>${stu.stuid}</td>	
                  			<td><% if(sMap.get(stud.getStuid())==null){%>
                  				暂无排名
                  			<%}else{%>
                  				<%=sMap.get(stud.getStuid()) %>
                  			<%} %></td>
                  		</tr>
                  		<%}
                  	} %>
                
              </tbody>
            </table>
            </div>
        </div>
        </div>
        </div>
        <script src="./JS/jquery-1.11.3.min.js"></script>
    <script src="./JS/bootstrap.min.js"></script>
   
  </body>
</html>
