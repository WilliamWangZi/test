<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="localhost/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'BeforeScore.jsp' starting page</title>
    
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
      <script src="./JS/knowledge.js"></script>
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
            <li><a href="HomePage1.jsp">&nbsp;&nbsp;&nbsp;学生信息 </a></li>
            <li><a href="SingleScore.jsp">&nbsp;&nbsp;&nbsp;单科成绩分析</a></li>
            <li><a href="AllScore.jsp">&nbsp;&nbsp;&nbsp;综合成绩分析</a></li>
            <li class="active"><a>&nbsp;&nbsp;&nbsp;课程知识点<span class="sr-only"></span></a></li>
          </ul>
 

        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
        <h1 class="page-header page-head">课程知识点</h1>
        <form action="" name="formname" method="post">
	        <select  onChange="cour(this.value)" class="drop" name="selectcourse">
				    <option id="course">${coursename==null?"请选择课程":coursename}</option>
					<c:forEach items="${clist}" var="course">
		  				<option value="${course.cname==coursename?"":course.cname}">${course.cname==coursename?"请选择课程":course.cname}</option>
		  			</c:forEach>
			</select>
		</form>
		      <div class="table-responsive res">
            <table class="table table-striped">
              <thead>
                <tr>
                  <th>编号</th>
                  <th>知识点内容</th>
                  <th></th>
                </tr>
              </thead>
              <tbody>
              <% int i=0; %>
              <c:forEach items="${lKnowledges}" var="Knowledges">
              		<tr>
              		<td><%=++i %></td>
              		<td>${Knowledges.kcontent}</td>
              		<form name="formname2">
              		<td><button class="btn btn-primary btn1" type="button" onClick="deletek('${Knowledges.kid}')">删除</button></td>
              		</form>
              		</tr>
		  		</c:forEach>
		 	  </tbody>
              </table>
            </div> 
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#exampleModal" >添加知识点</button>
				 <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel">
				  <div class="modal-dialog" role="document">
				    <div class="modal-content">
				      <div class="modal-header">
				        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
				        <h4 class="modal-title" id="exampleModalLabel">请输入知识点内容</h4>
				      </div>
				      <div class="modal-body">
				        <form name="formname1">
				          <div class="form-group">
				            
				            <textarea class="form-control" rows="10" placeholder="限50字以内" id="message-text" name="knowledge"></textarea>
				          </div>
				        </form>
				      </div>
				      <div class="modal-footer">
				        <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				        <button type="button" class="btn btn-primary" onClick="addcontent()">添加</button>
				      </div>
				    </div>
				  </div>
				</div>
          </div>  
        </div>     
      </div>  
			<script src="./JS/jquery-1.11.3.min.js"></script>
			<script src="./JS/bootstrap.min.js"></script>
 </body>
</html>
 


        
    
 

