/**
 * 
 */
function allscore(f){
	alert(f);
	if(f!=""){
		document.formname.action="allscoreservlet?bname="+f;
		document.formname.submit();
	}
}