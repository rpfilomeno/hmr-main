<!DOCTYPE html>
<%@ page import="hmr.com.bean.User"
		 import="java.util.List"
		 import="hmr.com.bean.Auction"
		 import="java.text.DecimalFormat"
		 import="java.text.SimpleDateFormat"
%>
<%	
String COMPANY_NAME = request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";
	
String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
	  
	  
	  String companyIdNo = request.getAttribute("companyIdNo")!=null ? (String)request.getAttribute("companyIdNo") : "";
	  String privateInviteId = request.getAttribute("privateInviteId")!=null ? (String)request.getAttribute("privateInviteId") : "";
	  Auction auction = (Auction) request.getAttribute("auction");
	  
	  String lastName = request.getAttribute("lastName")!=null ? (String)request.getAttribute("lastName") : "";
	  String firstName = request.getAttribute("firstName")!=null ? (String)request.getAttribute("firstName") : "";
	  String userId = request.getAttribute("userId")!=null ? (String)request.getAttribute("userId") : "";
	  //Integer mobileNo = request.getAttribute("mobileNo")!=null ? (Integer)request.getAttribute("mobileNo") : null;
	  
	  
	  //IDS
	  Integer user_id = request.getAttribute("user_id")!=null ? (Integer)request.getAttribute("user_id") : null;
	
	  DecimalFormat df = new DecimalFormat("#,###,##0");
	  SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy  HH:mm");
		
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
			
			<div class="col-md-12">
				<div class="product-full-view-wrap">
					<div class="row">
						<div class="col-sm-7">
							<div class="full-product-picture">
								<div class="image feature-fade-in" style="background-image: url('image?id=<%=auction.getAuction_id()%>&t=at')" ></div>
							</div>
						</div>
						<div class="col-sm-5">
							<h3 class="full-product-name"><%=auction.getAuction_name()%></h3>
							<div class="product-details">
								<div class="product-detail">Location: <%=auction.getLocation()%></div>
								<div class="product-detail">Start: <%=sdf.format(auction.getStart_date_time()) %></div>
								<div class="product-detail">Closing: <%=sdf.format(auction.getEnd_date_time()) %></div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		
		<div class="row">
			<div class="col-md-4 col-lg-4 col-md-offset-4 col-lg-offset-4">
				
				<div class="customer-register-wrap">
					<h2 class="tac">Welcome to HMR BID Private Bidding </h2>
					<div class="clearfix top20"></div>
					
					<form action="bid" class="form-login" name="frm" method="post" onkeypress="stopEnterSubmitting(window.event)">
					
						<div class="form-group ">
							
							<div class="">
								<input class="form-control" type="text" id="companyIdNo" name="companyIdNo" placeholder="Company ID Number" maxlength="50" value="<%if(companyIdNo!=null){ %><%=companyIdNo%><%}%>">
							 	<span class="help-block">  </span>
							</div>
						</div>
						
						<div class="form-group ">
							
							<div class="">
								<input type="text" name="userId" value="<%if(userId!=null){ %><%=userId%><%}%>" id="userId" class="email form-control" placeholder="Email" autofocus="autofocus"   id="email" />
							 	<span class="help-block">  </span>
							</div>
						</div>
											
																	
						<div class="form-group ">
							
							<div class="">
								<input type="password" name="pw" value="" id="pw" class="password form-control" placeholder="Password"   id="password" />
							 	<span class="help-block">  </span>
							</div>
						</div>
		
						
						<div class="row">
							<div class="col-md-6">
								<div class="form-group">
									<a href="bid?mngr=get&a=forgotPassword">Forgot your password?</a>
								</div>
							</div>
							<div class="col-md-6">
								<div class="form-group">
									<a class="btn btn-primary pull-right" onclick="submitPage()">Accept</a>
								</div>		
								<div class="clearfix"></div>
							</div>
						</div>
						

						<div class="clearfix top20"></div>
						<div class="tac">
							<div><a href="bid?mngr=get&a=registration">Don't have an account? REGISTER.</a></div>
						</div>
						
						<input class="form-control" type="hidden" id="privateInviteId" name="privateInviteId" value="<%=auction.getToken()%>">
						<input type="hidden" name="manager" id="manager" value="login-manager"/>
                        <input type="hidden" name="action" id="action" value="login"/>
                        <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
					</form>
					
					
				</div>
			</div>
		</div>
	
	

	</div>

	<div class="clearfix top100"></div>
</section>

<section id="account-login-section" class="page-section">
	<div class="container">
		
	</div>
</section>

</div>

<jsp:include page="includes/footer.jsp"></jsp:include>
<jsp:include page="includes/footer-meta.jsp"></jsp:include>


<!-- JS Local -->
<script type="text/javascript">
function ValidateEmail(mail)   
{  
 if (/^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/.test(mail))  
  {  
    return true;  
  }  
    //alert("You have entered an invalid email address!")  
    return false;  
}  



function validateLogin(){
	var isLogin = true;
	if(document.frm.companyIdNo.value==""){
		var msgInfo = "Company ID number is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		isLogin = false;
	}else if(document.frm.userId.value==""){
		var msgInfo = "Email is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.userId.focus();
		isLogin = false;
	}else if(document.frm.pw.value==""){
		var msgInfo = "Password is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.pw.focus();
		isLogin = false;
	}else if(document.frm.pw.value.length < 8){
		var msgInfo = "Password length should be atleast 8.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.pw.focus();
		isLogin = false;
		
	}else if(!ValidateEmail(document.frm.userId.value)){
		var msgInfo = "Email is invalid.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.userId.focus();
		isLogin = false;
	}
	
	
	
	return isLogin;
}

function clearLogin(){
	document.frm.userId.value="";
	document.frm.pw.value="";
	document.frm.userId.focus();
}

function submitPage(){
	if(validateLogin()){
		document.frm.submit();
	}
	setTimeout(function(){document.getElementById("msgDiv").innerHTML="";},5000);
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