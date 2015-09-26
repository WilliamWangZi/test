/**
 * 
 */
function cour(f){
	alert(f);
	if(f!=""){
		document.formname.action="getKnowledgeservlet?cname="+f;
		document.formname.submit();
	}
}
function addcontent(){
	var course=document.formname.selectcourse.value;
	alert(course);
	var content=document.formname1.knowledge.value;
	if(course!=""&&content!=""){
		document.formname.action="getKnowledgeservlet?cname="+course+"&kcontent="+content;
		document.formname.submit();
	}
}
function deletek(f){
	var course=document.formname.selectcourse.value;
	if(f!=null){
		document.formname.action="getKnowledgeservlet?cname="+course+"&kid="+f;
		document.formname.submit();
	}
}