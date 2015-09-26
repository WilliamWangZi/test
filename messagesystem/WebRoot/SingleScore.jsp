<%@page import="JavaBean.*"%>
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
    
    <title>My JSP 'SingleScore.jsp' starting page</title>
    
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
     <link href="./CSS/simple.css" rel="stylesheet">
     <script src="./JS/simple.js"></script>
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
            <li ><a href="HomePage1.jsp">&nbsp;&nbsp;&nbsp;学生信息 </a></li>
            <li class="active"><a>&nbsp;&nbsp;&nbsp;单科成绩分析<span class="sr-only"></span></a></li>
            <li><a href="AllScore.jsp">&nbsp;&nbsp;&nbsp;综合成绩分析</a></li>
            <li><a href="BeforeScore.jsp">&nbsp;&nbsp;&nbsp;课程知识点</a></li>
          </ul>
        </div>
        <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main d3">
        <h1 class="page-header page-head">单科成绩分析</h1>
        <div class="dropall">
        <form action="" name="formname" method="post">
	        <select  onChange="cour(this.value)" class="drop1" name="selectcourse">
				    <option id="course">${coursename==null?"请选择课程":coursename}</option>
					<c:forEach items="${clist}" var="course">
		  				<option value="${course.cname==coursename?"":course.cname}">${course.cname==coursename?"请选择班级":course.cname}</option>
		  			</c:forEach>
			</select>
		</form>
		<form action="" name="formname1" method="post">
		<select  onChange="clas(this.value)" class="drop2" name="selectbanji">
			    <option id="course">${banname1==null?"请选择班级":banname1}</option>
				<c:forEach items="${balist}" var="cla">
	  				<option value="${cla.bname==banname1?"":cla.bname}">${cla.bname==banname1?"请选择班级":cla.bname}</option>
	  			</c:forEach>
		</select>
		</form>
		</div>
        <div class="table-responsive res">
            <table class="table table-striped">
              <thead>
                <tr>
                  
                  <th>学生姓名</th>
                  <th>学号</th>
                  <%List<question> all=(List)request.getAttribute("qList");
                  	if(all!=null){
                  	List<Integer> qvalue=new ArrayList<Integer>();
                  	
                  	String qtype="";
                  		for(int i=0;i<all.size();i++){
                  			qvalue.add(all.get(i).getQvalue());
                  			qtype=qtype+all.get(i).getQtype()+" ";
                  			 %>
                  		<td><%=all.get(i).getQbeid()%>(<%=all.get(i).getQtype() %>:<%=all.get(i).getQvalue()%>分)</td>
                  	<%}
                  		pageContext.setAttribute("qtypes", qtype);
                  		pageContext.setAttribute("qvalues", qvalue);} %>	
            
                  <th>总分</th>
                </tr>
              </thead>
              <tbody>
          			<%Integer j=(Integer)request.getAttribute("num");
          			List<student> students=(ArrayList)request.getAttribute("slist");
          			List<answer> aList=(ArrayList)request.getAttribute("alist");
          			int n=0;
          			if(students!=null&&aList!=null&&j!=null){
          			int i=0,l=0;
          			for(i=0;i<j.intValue();i++){ %>
          			<tr>
          				<td><%=students.get(i).getSname() %></td>
          				<td><%=students.get(i).getStuid() %></td>
          				<% for(int k=0;k<aList.size();k++){
          				if(aList.get(k).getAstuid().equals(students.get(i).getStuid())){%>
          					<td><%=aList.get(k).getAvalue() %>	</td>
          				 		
          				<%l=l+aList.get(k).getAvalue();
          				} 
          				
          			}%><td><%=l %></td>
          			</tr>
          			<%n=n+l; l=0;}
          			}%>
          			<tr>
          			
          			<%List<Double> doubles=(ArrayList)request.getAttribute("dlDoubles"); 
          			if(doubles!=null){%>
          			<td colspan="2" text-align="center">平均分</td>
          			<% for(int m=0;m<doubles.size();m++){%>
          			<td><%=doubles.get(m) %>	</td>
          			<%}}   if(j!=null){%>
          			<td><%=n/j.intValue() %></td>
          			<%}pageContext.setAttribute("dDoubles", doubles); %>
          			</tr>
          		</tbody>
            </table>
            </div>
                 <script type="text/javascript">
     var tip="${tip}";
     
            	if(tip!=""){
            		alert(tip);
            	}
            	</script>
            <script>
            	
            	var number=${num};
            	var sum=${integers};
            	var qvalue=${qvalues};
            	var qavg=${dDoubles};
            	var sums=0;
            	var ScorePart;
            	var pass=new Array(0,0);
            	var scores=new Array(10);	
            	for(j=0;j<scores.length;j++){
            		scores[j]=0;
            	}
            	for(i=0;i<sum.length;i++){
	            	sums+=sum[i];
	            	if(sum[i]>=60){
	            		pass[0]+=1;
	            	}else{
	            		pass[1]+=1;
	            	}
	            	ScorePart=Math.ceil(sum[i]/10);
	            	scores[ScorePart]+=1;
            	}
            	alert(qvalue);
            	alert(qavg);
            	sums/=number;
            	 var qtype="${qtypes}";
            	var type=qtype.split(" ");
 
            	
            </script>
            <script src="./JS/column.js" charset="utf-8"></script>
            <script src="./JS/arc.js" charset="utf-8"></script>
            <script src="./JS/Histogram.js" charset="utf-8"></script>
            <script src="./JS/AllType.js" charset="utf-8"></script>
        </div>
        </div>
        </div>
  </body>
</html>
