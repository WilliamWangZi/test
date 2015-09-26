/**
 * 
 */
	function createXMLHttp(){
		if(window.XMLHttpRequest){
			xmlHttp=new XMLHttpRequest();
		}else{
			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	function stud(f){
		if(f!=""){
			document.formname.action="servletshowstu?bname="+f;
			document.formname.submit();
		}
	}	
function stu(f){
	createXMLHttp();
	alert(f);
	xmlHttp.open("POST", "showstuservlet?bid="+f);
	xmlHttp.onreadystatechange=checkunameCallback;
	xmlHttp.send(null);
}
function checkunameCallback(){
	if(xmlHttp.readyState==4){
		if(xmlHttp.status==200){
			
			var text=xmlHttp.responseText;
			if(text=="true"){
				document.getElementById("msg").innerHTML="用户名已存在";
				flag=false;
			}else{
						document.getElementById("msg").innerHTML="用户名可用";
						flag=true;
			}
		}
	}
}
