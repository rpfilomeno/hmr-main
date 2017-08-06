<!DOCTYPE html>
<%@ page import="hmr.com.bean.User"
		 import="java.util.List"
		 import="java.math.BigDecimal" 
		  
%>
<%String COMPANY_NAME = request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";
	
	  String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
	  String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
	  
	  
	  String firstName = request.getAttribute("firstName")!=null ? (String)request.getAttribute("firstName") : "";
	  String lastName = request.getAttribute("lastName")!=null ? (String)request.getAttribute("lastName") : "";
	  String userId = request.getAttribute("userId")!=null ? (String)request.getAttribute("userId") : "";
	  //BigDecimal mobileNo = request.getAttribute("mobileNo")!=null ? (BigDecimal)request.getAttribute("mobileNo") : null;
	  
	  //IDS
	  Integer user_id = request.getAttribute("user_id")!=null ? (Integer)request.getAttribute("user_id") : null;
	  

	%>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <jsp:include page="includes/header-meta.jsp"></jsp:include>
                
    </head>
    <body data-is-mobile="" id="c" >
    
    <input type="hidden" id="base_url" value="">
        <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->


<jsp:include page="includes/header.jsp"></jsp:include>

	
<div id="ms--main--body">
	<section id="register-account-section" class="page-section">
	<div class="container">
		<div class="row">
			<div data-alerts="alerts" data-titles='{"warning": "<em>Warning!</em>", "error": "<em>Error!</em>"}' data-ids="myid" data-fade="3000"></div>
			
			<div class="col-md-4 col-lg-4 col-md-offset-4 col-lg-offset-4">

				<div class="customer-register-wrap">
					<h2 class="tac">Activate Account</h2>
					<div class="clearfix top20"></div>

					
						<form action="bid" class="form-login" name="frm" method="post" onkeypress="stopEnterSubmitting(window.event)">
                            <div class="row">
                            	
                                <div class="col-md-12 hello-text-wrap">
                                    <span class="hello-text text-thin">Hello <%=firstName%> <%=lastName%>,</span>
                                </div>
                                
                                <div class="col-md-12">
                                    <div class="form-group"><input class="form-control" type="password" name="pw"  placeholder="New Password" maxlength="15" value=""></div>
                                </div>
                                
                                <div class="col-md-12">
                                    <div class="form-group"><input class="form-control" type="password" name="pw2"  placeholder="Confirm New Password" maxlength="15" value=""></div>
                                </div>
                       
                                <div class="col-md-6">
                                    <a class="btn btn-primary btn-theme btn-block btn-theme-dark" href="#" onclick="submitPage()">Sign Up</a>
                                </div>
                                <div class="col-md-6">
                                    <a class="btn btn-primary btn-theme btn-block btn-theme-dark" href="#" onclick="clearActivation()">Clear</a>
                                </div>
                            </div>
                            <input type="hidden" name="manager" id="manager" value="user-manager"/>
                            <input type="hidden" name="action" id="action" value="activate"/>
                            <input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
                            <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
                        </form>
                        </form>				</div>
			</div>
			
		</div>
		
	</div>

	<div class="clearfix top100"></div>
</section></div>


<jsp:include page="includes/footer.jsp"></jsp:include>
<jsp:include page="includes/footer-meta.jsp"></jsp:include>


<!-- JS Local -->
<script type="text/javascript">

function onLoadPage(){
	document.frm.pw.focus();	
}

function validateActivation(){
	var isSignUp = true;
	
	if(document.frm.pw.value==""){
		var msgInfo = "New Password is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.pw.focus();
		isSignUp = false;
	}else if(document.frm.pw2.value==""){
		var msgInfo = "Confirm Password is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.pw2.focus();
		isSignUp = false;
	}else if(document.frm.pw.value.length < 8){
		var msgInfo = "New Password length should be atleast 8.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.pw.focus();
		isSignUp = false;
	}else if(document.frm.pw2.value.length < 8){
		var msgInfo = "Confirm Password length should be atleast 8.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.pw2.focus();
		isSignUp = false;
	}
	
	else
		
	{
		
		if(isSignUp && (document.frm.pw.value == document.frm.pw2.value)){
			isSignUp = true;
		}else{
			var msgInfo = "Password mismatch.";
			var msgbgcol = "red";
			showAlert(msgInfo, msgbgcol);
			document.frm.pw.focus();
			isSignUp = false;
		}
		
	}
	

	
	return isSignUp;
}

function clearActivation(){
	document.frm.pw.value="";
	document.frm.pw2.value="";
	document.frm.pw.focus();
}

function submitPage(){
	if(validateActivation()){
		document.frm.submit();
	}

}


function showAlert(msgInfo, msgbgcol) {
	var priority = null;
	if(msgbgcol=="red") {
		priority = "error";
	}else if(msgbgcol=="green") {
		priority = "success";
	}else {
		priority = "warning";
	}

	$(document).trigger("add-alerts", [
		{
			"message": msgInfo,
	         "priority": priority
	    }
	]);
	
	
}

function stopEnterSubmitting(e) {
    if (e.keyCode == 13) {
        var src = e.srcElement || e.target;
        if (src.tagName.toLowerCase() != "textarea") {
            if (e.preventDefault) {
                e.preventDefault();
            } else {
                e.returnValue = false;
            }
        }
    }
}	

$(document).ready(function(){
	<%if(msgInfo!=null){%>
	var msgInfo = "<%=msgInfo%>";
	var msgbgcol = "<%=msgbgcol%>";
	showAlert(msgInfo, msgbgcol);
	<%}%>
	
});



</script>
<!-- /JS Local -->



</body>
</html>