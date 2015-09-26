<%@page import="JavaBean.student"%>
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
    
    <title>My JSP 'AllScore.jsp' starting page</title>
    
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
      <script src="./JS/AllScore.js"></script>
       <script src="./JS/d3.min.js" charset="utf-8"></script>
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
            <li class="active"><a>&nbsp;&nbsp;&nbsp;综合成绩分析<span class="sr-only"></span></a></li>
            <li><a href="BeforeScore.jsp">&nbsp;&nbsp;&nbsp;课程知识点</a></li>
          </ul>
 

        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main d3">
        <h1 class="page-header page-head">学生信息</h1>
        <form action="" method="post" name="formname">
			<select  onChange="allscore(this.value)" class="drop">
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
						                  <th>学生姓名</th>
						                  <th>学号</th>
						                  <c:forEach items="${courList}" var="cour">
						                  <th>	${cour}</th>
	  										</c:forEach>
						              
						                  <!--   <th>班级</th>-->
						                  <th>通过率</th>
						                </tr>
						              </thead>
						              <tbody>
						              <%List<student> slist=(List)request.getAttribute("slList");
						              	List<String> courList=(List)request.getAttribute("courList");
						              	
						              	Map<String, Map<String, Integer>> ascoreMap=(Map)request.getAttribute("ascoreMap"); 
						              	if(slist!=null&&courList!=null&&ascoreMap!=null){
						              	String s="";
						              	int k=0;
						              		for(int i=0;i<slist.size();i++){%>
						              		<tr>
						              			<td><%=slist.get(i).getSname() %></td>
						              			<td><%=slist.get(i).getStuid() %></td>
						              			<%for(int j=0;j<courList.size();j++){
						              				Map<String, Integer> map=ascoreMap.get(courList.get(j));

						              				if(map.get(slist.get(i).getStuid()).intValue()>=60){
						              					k++;
						              				} %>
						              				<td><%=map.get(slist.get(i).getStuid()) %></td>
						              			<%} if(courList.size()!=0){%>
						              			<td><%=k*100/courList.size()%>%</td>
						              		</tr>
						              	 <%}k=0;	}
						              	 for(int a=0;a<courList.size();a++){
											s=s+courList.get(a)+" ";
						              	 }
						              	 pageContext.setAttribute("s", s);
						             	}%>
										 </tbody>
			                </table>
	                </div>
                
                <script>
                	
                	var pass=${integer};
                	alert(pass);
                	var s="${s}";
                	var course=s.split(" ");
                </script>
                <script src="./JS/AllCourse.js" charset="utf-8"></script>
        </div>
        </div>
        </div>
                <script type="text/javascript">
	     			var tip="${tip}";
	     
	            	if(tip!=""){
	            		alert(tip);
	            	}
            	</script>
        <script src="./JS/jquery-1.11.3.min.js"></script>
    <script src="./JS/bootstrap.min.js"></script>
  </body>
</html>
