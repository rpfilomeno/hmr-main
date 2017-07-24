<!DOCTYPE html>
<%@ page import="hmr.com.bean.User"
		 import="java.util.List"
		 import="hmr.com.bean.Auction"
		 import="java.text.DecimalFormat"
		 import="java.text.SimpleDateFormat"
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge"><![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<%String COMPANY_NAME = request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";
	
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
    <title><%=COMPANY_NAME%></title>

    <!-- Favicon -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="shortcut icon" href="ico/hmr-favicon.ico">

    <!-- CSS Global -->
    <link href="assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/plugins/bootstrap-select/css/bootstrap-select.min.css" rel="stylesheet">
    <link href="assets/plugins/fontawesome/css/font-awesome.min.css" rel="stylesheet">
    <link href="assets/plugins/prettyphoto/css/prettyPhoto.css" rel="stylesheet">
    <link href="assets/plugins/owl-carousel2/assets/owl.carousel.min.css" rel="stylesheet">
    <link href="assets/plugins/owl-carousel2/assets/owl.theme.default.min.css" rel="stylesheet">
    <link href="assets/plugins/animate/animate.min.css" rel="stylesheet">

    <!-- Theme CSS -->
    <link href="assets/css/theme.css" rel="stylesheet">
<link href="assets/css/theme-hmr.css" rel="stylesheet" id="theme-config-link">

    <!-- Head Libs -->
    <script src="assets/plugins/modernizr.custom.js"></script>

    <!--[if lt IE 9]>
    <script src="assets/plugins/iesupport/html5shiv.js"></script>
    <script src="assets/plugins/iesupport/respond.min.js"></script>
    <![endif]-->
</head>
<body id="home" class="wide">
<!-- PRELOADER -->
<jsp:include page="hmr-preloader.jsp" />
<!-- /PRELOADER -->


<!-- WRAPPER -->
<div class="wrapper">
    <!-- HEADER -->
	<jsp:include page="hmr-header.jsp" />
    <!-- /HEADER -->

    <!-- CONTENT AREA -->
    <div class="content-area">
    
    	<!-- PAGE -->
        <section class="page-section">
            <div class="container">
            	<div class="row ">
            		<div class="col-md-12">
		                <div class="message-box">
		                    <div class="message-box-inner">
		                        <h2><%=auction.getAuction_name()%></h2>
		                    </div>
		                </div>
		                
		                <div id="msgDiv"></div>
		             </div>
               </div>
            </div>
        </section>
        <!-- /PAGE -->



        <!-- PAGE -->
        <section class="page-section color" style="padding:15px;">
            <div class="container">
                <div class="row">
                	<div class="col-sm-6">
                		<div class="row">
                				
                                
                                <div class="col-md-12">

							    	<div class="media" style="height: 210px;">
							        	<a class="pull-left media-link" href="#" >
							            	<img  class="media-object" style="height: 200px; size: 200px;" src="image?id=<%=auction.getAuction_id()%>&t=at" alt="">
								        </a>
							            <div class="media-body">
											<h4 class="media-heading"><a href="#" style="font-size: 14px; font-weight: bold; color: red"><%=auction.getAuction_desc()%></a></h4>
							                <div><label>LOCATION : </label> &nbsp;&nbsp; <label><%=auction.getLocation()%></label></div>
							           		<div><label>START : </label> &nbsp;&nbsp; <label><%=sdf.format(auction.getStart_date_time()) %></label></div>
                                    		<div><label>CLOSING : </label>&nbsp;&nbsp; <label><%=sdf.format(auction.getEnd_date_time()) %></label></div>

							        	</div>
							    	</div>
							    </div>
                		</div>
                	</div>
                    <div class="col-sm-6">
                        <form action="bid" class="form-login" name="frm" method="post">
                            <div class="row">

                                
                                
                                <div class="col-md-12 hello-text-wrap">
                                    <span class="hello-text text-thin">Hello, you have been invited to bid on a Private Auction.</span>
                                </div>
                                <div class="col-md-12 hello-text-wrap">
                                    <span class="hello-text text-thin">You must provide the following information required to join:</span>
                                </div>
                                
                                
                                 <div class="col-md-12">
                                    <div class="form-group"><input class="form-control" type="text" id="companyIdNo" name="companyIdNo" placeholder="Company ID Number" maxlength="50" value="<%if(companyIdNo!=null){ %><%=companyIdNo%><%}%>"></div>
                                </div>
                                
                                <input class="form-control" type="hidden" id="privateInviteId" name="privateInviteId" value="<%=auction.getToken()%>">
                                
                                
                                <div class="col-md-12 hello-text-wrap">
                                    <span class="hello-text text-thin">Continue by logging into your account now.</span>
                                </div>
                                
                               
                                <div class="col-md-12">
                                    <div class="form-group"><input class="form-control" type="text" id="userId" name="userId" placeholder="Email" maxlength="50" value="<%if(userId!=null){ %><%=userId%><%}else{%><%}%>"></div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group"><input class="form-control" id="pw" name="pw" type="password" placeholder="Password" maxlength="15" value=""></div>
                                </div>
                                
                                <div class="col-md-12 col-lg-12 text-left-lg">
                                    <a class="forgot-password" href="bid?mngr=get&a=forgotPassword">forgot password?</a>
                                </div>
                                <div class="col-md-6">
                                    <a class="btn btn-theme btn-block btn-theme-dark" href="#" onclick="submitPage()">Login</a>
                                </div>
                                <div class="col-md-6">
                                    <a class="btn btn-theme btn-block btn-theme-dark" href="#" onclick="clearLogin()">Clear</a>
                                </div>
                            </div>
                            <input type="hidden" name="manager" id="manager" value="login-manager"/>
                            <input type="hidden" name="action" id="action" value="login"/>
                            <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
                        </form>
                    </div>
                    
                </div>
            </div>
        </section>
        <!-- /PAGE -->

        


    </div>
    <!-- /CONTENT AREA -->

    <!-- FOOTER -->
	<jsp:include page="hmr-footer.jsp" />
    <!-- /FOOTER -->

    <div id="to-top" class="to-top" style="background-color: #93bcff"><i class="fa fa-angle-up"></i></div>

</div>
<!-- /WRAPPER -->

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

function onLoadPage(){

	


}

function validateLogin(){
	var isLogin = true;
	if(document.frm.userId.value==""){
		var msgInfo = "Email is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.userId.focus();
		isLogin = false;
	}else if(document.frm.pw.value==""){
		var msgInfo = "Password is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.pw.focus();
		isLogin = false;
	}else if(document.frm.pw.value.length < 8){
		var msgInfo = "Password length should be atleast 8.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.pw.focus();
		isLogin = false;
		
	}else if(!ValidateEmail(document.frm.userId.value)){
		var msgInfo = "Email is invalid.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
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

<%if(msgInfo!=null){%>
	
	var msgInfo = "<%=msgInfo%>";
	var msgbgcol = "<%=msgbgcol%>";
	var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
	msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
	msgBoxValue = msgBoxValue + '</div>';
	document.getElementById("msgDiv").innerHTML=msgBoxValue;

<%}%>


setTimeout(function(){document.getElementById("msgDiv").innerHTML="";},5000);

setTimeout(onLoadPage,4000);



</script>
<!-- /JS Local -->


<!-- JS Global -->
<script src="assets/plugins/jquery/jquery-1.11.1.min.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/plugins/bootstrap-select/js/bootstrap-select.min.js"></script>
<script src="assets/plugins/superfish/js/superfish.min.js"></script>
<script src="assets/plugins/prettyphoto/js/jquery.prettyPhoto.js"></script>
<script src="assets/plugins/owl-carousel2/owl.carousel.min.js"></script>
<script src="assets/plugins/jquery.sticky.min.js"></script>
<script src="assets/plugins/jquery.easing.min.js"></script>
<script src="assets/plugins/jquery.smoothscroll.min.js"></script>
<script src="assets/plugins/smooth-scrollbar.min.js"></script>

<!-- JS Page Level -->
<script src="assets/js/theme.js"></script>

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/plugins/jquery.cookie.js"></script>
<!--  <script src="assets/js/theme-config.js"></script> -->
<!--<![endif]-->

</body>
</html>