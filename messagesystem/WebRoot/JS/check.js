/**
 * 
 */
var xmlHttp;
var flag=false;
var flag1=false;
var tip=false;
function createXMLHttp(){
	if(window.XMLHttpRequest){
		xmlHttp=new XMLHttpRequest();
	}else{
		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
}
function checkuname(user){
	if(user!=""){
		if(/[A-Za-z0-9_]{6,15}$/.test(user)){
			createXMLHttp();
			xmlHttp.open("POST", "registerservlet?username="+user);
			xmlHttp.onreadystatechange=checkunameCallback;
			xmlHttp.send(null);
		}else{
			document.getElementById("msg").innerHTML="用户名格式不正确";
		}
	}else{
		document.getElementById("msg").innerHTML="用户名不能为空";
		flag=false;
	}
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
function checkForm(){
	if(flag&&flag1){
		alert("true");
		return true;
	}
	alert("false");
	return false;
}

function checkpw1(pw1){
	if(pw1==""){
		flag1=false;
		document.getElementById("matchpw1").innerHTML="密码不能为空";
	}else{
		if(!/[A-Za-z0-9_]{6,15}$/.test(pw1)){
			document.getElementById("matchpw1").innerHTML="密码格式不准确";
		}else{
			document.getElementById("matchpw1").innerHTML="";
		}
	}
}
function checkpw2(pw2){
	var pw1=document.zhuceform.pw1.value;
	
	if(pw1!=pw2){
		document.getElementById("matchpw2").innerHTML="两次密码不一致";
		flag1=false;
	}else{
		document.getElementById("matchpw2").innerHTML="";
		flag1=true;
	}
}
function format1(){
		document.getElementById("msg").innerHTML="请输入6-15位的字母、数字或下划线";
	
}
function format2(){
	document.getElementById("matchpw1").innerHTML="请输入6-15位的字母、数字或下划线";

}
