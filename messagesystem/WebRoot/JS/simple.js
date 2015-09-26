/**
 * 
 */
function cour(f){
	if(f!=""){
		document.formname.action="getclassservlet?cname="+f;
		document.formname.submit();
	}
}
function clas(t){
	if(t!=""){
		alert(t+coursename);
		var coursename=document.formname.selectcourse.value;
		document.formname1.action="SimpleDataservlet?bname="+t+"&coursename="+coursename;
		document.formname1.submit();
	}
}
function createXMLHttp(){
		if(window.XMLHttpRequest){
			xmlHttp=new XMLHttpRequest();
		}else{
			xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
function cou(f){
	createXMLHttp();
	alert(f);
	xmlHttp.open("POST", "getclassservlet?cname="+f);
	xmlHttp.onreadystatechange=getclassCallback;
	xmlHttp.send(null);
}
function getclassCallback(){
	if(xmlHttp.readyState==4){
		if(xmlHttp.status==200){
			
			var text=xmlHttp.responseText;
			var banji=new Array();
			banji=text.split(" ");
			alert(banji[0]);
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