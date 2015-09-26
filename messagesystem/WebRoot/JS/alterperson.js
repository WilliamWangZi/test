/**
 * 
 */
var xmlHttp;
var flag=true;
var flag1=false;

function checkpw1(pw1){
	if(pw1==""){
		flag=false;
		document.getElementById("matchpw1").innerHTML="密码不能为空";
	}else{
		if(!/[A-Za-z0-9_]{6,15}$/.test(pw1)){
			document.getElementById("matchpw1").innerHTML="密码格式不准确";
			flag=false;
		}else{
			document.getElementById("matchpw1").innerHTML="";
			flag=true;
		}
	}
}
function checkpw2(pw2){

	var pw1=document.alterform.alterpw1.value;
	if(pw1!=pw2){
		document.getElementById("matchpw2").innerHTML="两次密码不一致";
		flag1=false;
	}else{
		document.getElementById("matchpw2").innerHTML="";
		flag1=true;
	}
}

function createXMLHttp(){
	if(window.XMLHttpRequest){
		xmlHttp=new XMLHttpRequest();
	}else{
		xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	}
}
function alter(){
	var uname=document.alterform.altername.value;
	var pw=document.alterform.alterpw1.value;
	var phone=document.alterform.alterphone.value;
	var school=document.alterform.alterschool.value;
	var academy=document.alterform.alteracademy.value;
	var department=document.alterform.alterdepartment.value;
	alert(department);

			createXMLHttp();
			var url="alterservlet?uname="+uname+"&pw="+pw+"&phone="+phone+"&school="+school+"&academy="+academy+"&department="+department;
			alert(url);
			xmlHttp.open("GET", encodeURI(url));
			xmlHttp.onreadystatechange=alterCallback;
			if(flag&&flag1){
				xmlHttp.send(null);
			}else{
				document.getElementById("msg").innerHTML="失败";
	}	
}
function alterCallback(){
	if(xmlHttp.readyState==4){
		if(xmlHttp.status==200){
			
			var text=xmlHttp.responseText;
			if(text=="true"){
				document.getElementById("msg").innerHTML="保存成功";
			}else{
						document.getElementById("msg").innerHTML="保存失败222";
			}
		}
	}
}