<!DOCTYPE html>
<%@ page import="hmr.com.bean.User"
		 import="java.util.List"  
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge"><![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<%String COMPANY_NAME = request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";
	
	  String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
	  String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
	  
	  
	  String firstName = request.getAttribute("firstName")!=null ? (String)request.getAttribute("firstName") : "";
	  String lastName = request.getAttribute("lastName")!=null ? (String)request.getAttribute("lastName") : "";
	  String userId = request.getAttribute("userId")!=null ? (String)request.getAttribute("userId") : "";
	  //Integer mobileNo = request.getAttribute("mobileNo")!=null ? (Integer)request.getAttribute("mobileNo") : null;
	  
	  
	  //IDS
	  Integer user_id = request.getAttribute("user_id")!=null ? (Integer)request.getAttribute("user_id") : null;
	
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

    <!-- Popup: Shopping cart items -->
    <div class="modal fade popup-cart" id="popup-cart" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-dialog">
            <div class="container">
                <div class="cart-items">
                    <div class="cart-items-inner">
                        <div class="media">
                            <a class="pull-left" href="#"><img class="media-object item-image" src="assets/img/preview/shop/order-1s.jpg" alt=""></a>
                            <p class="pull-right item-price">$450.00</p>
                            <div class="media-body">
                                <h4 class="media-heading item-title"><a href="#">1x Standard Product</a></h4>
                                <p class="item-desc">Lorem ipsum dolor</p>
                            </div>
                        </div>
                        <div class="media">
                            <p class="pull-right item-price">$450.00</p>
                            <div class="media-body">
                                <h4 class="media-heading item-title summary">Subtotal</h4>
                            </div>
                        </div>
                        <div class="media">
                            <div class="media-body">
                                <div>
                                    <a href="#" class="btn btn-theme btn-theme-dark" data-dismiss="modal">Close</a><!--
                                    --><a href="shopping-cart.html" class="btn btn-theme btn-theme-transparent btn-call-checkout">Checkout</a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /Popup: Shopping cart items -->

    <!-- HEADER -->
	<jsp:include page="hmr-header.jsp" />
    <!-- /HEADER -->

    <!-- CONTENT AREA -->
    <div class="content-area">



        <!-- PAGE -->
        <section class="page-section color" style="padding:15px;">
            <div class="container">
                <div class="row">
                    <div class="col-sm-6">
                        <h3 class="block-title"><span>Login</span></h3>
                        <form action="bid" class="form-login" name="frm" method="post">
                            <div class="row">
                            	<!-- 
                            	<div class="message-box" style="background-color: light-red">
				                    <div class="message-box-inner2" >
				                        <h2 style="font-size: 12px; background-color: red">Login Failed</h2>
				                    </div>
				                </div> 
				                
			                    <div class="message-box" style="font-size: 12px; background-color: red">
			                        <h2 style="font-size: 12px; background-color: red">Login Failed</h2>
			                    </div>
				                
				                -->
				                <div id="msgDiv"></div>
                            	
                                <div class="col-md-12 hello-text-wrap">
                                    <span class="hello-text text-thin">Hello, welcome to HMR Auctions</span>
                                </div>
                                <!-- 
                                <div class="col-md-12 col-lg-6">
                                    <a class="btn btn-theme btn-block btn-icon-left facebook" href="#"><i class="fa fa-facebook"></i>Sign in with Facebook</a>
                                </div>
                                <div class="col-md-12 col-lg-6">
                                    <a class="btn btn-theme btn-block btn-icon-left twitter" href="#"><i class="fa fa-twitter"></i>Sign in with Twitter</a>
                                </div> -->
                                <div class="col-md-12">
                                    <div class="form-group"><input class="form-control" type="text" id="userId" name="userId" placeholder="Email" maxlength="50" value="<%if(userId!=null){ %><%=userId%><%}else{%><%}%>"></div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group"><input class="form-control" id="pw" name="pw" type="password" placeholder="Password" maxlength="15" value=""></div>
                                </div>
                                <!--
                                <div class="col-md-12 col-lg-6">
                                    <div class="checkbox">
                                        <label><input type="checkbox"> Remember me</label>
                                    </div>
                                </div> 
                                -->
                                <%-- 
								<div class="col-md-6 col-lg-6 text-left-lg">
                                    	<!-- <a class="forgot-password" href="bid?mngr=get&a=changePassword">&nbsp;</a> -->
                                        <a class="forgot-password" href="#">&nbsp;</a>
                                    
                                </div> --%>
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
                    <!-- 
                    <div class="col-sm-6">
                        <h3 class="block-title"><span>Create New Account</span></h3>
                        <form action="#" class="create-account">
                            <div class="row">
                                <div class="col-md-12 hello-text-wrap">
                                    <span class="hello-text text-thin">Create Your Account on Bellashop</span>
                                </div>
                                <div class="col-md-12">
                                    <h3 class="block-title">Signup Today and You'll be able to</h3>
                                    <ul class="list-check">
                                        <li>Online Order Status</li>
                                        <li>See Ready Hot Deals</li>
                                        <li>Love List</li>
                                        <li>Sign up to receive exclusive news and private sales</li>
                                        <li>Quick Buy Stuffs</li>
                                    </ul>
                                </div>
                                <div class="col-md-6">
                                    <a class="btn btn-block btn-theme btn-theme-dark btn-create" href="#">Create Account</a>
                                </div>
                            </div>
                        </form>
                    </div> 
                    -->
                </div>
            </div>
        </section>
        <!-- /PAGE -->

        <!-- PAGE -->
        <!-- 
        <section class="page-section">
            <div class="container">
                <div class="row blocks shop-info-banners">
                    <div class="col-md-4">
                        <div class="block">
                            <div class="media">
                                <div class="pull-right"><i class="fa fa-gift"></i></div>
                                <div class="media-body">
                                    <h4 class="media-heading">Buy 1 Get 1</h4>
                                    Proin dictum elementum velit. Fusce euismod consequat ante.
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="block">
                            <div class="media">
                                <div class="pull-right"><i class="fa fa-comments"></i></div>
                                <div class="media-body">
                                    <h4 class="media-heading">Call to Free</h4>
                                    Proin dictum elementum velit. Fusce euismod consequat ante.
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="col-md-4">
                        <div class="block">
                            <div class="media">
                                <div class="pull-right"><i class="fa fa-trophy"></i></div>
                                <div class="media-body">
                                    <h4 class="media-heading">Money Back!</h4>
                                    Proin dictum elementum velit. Fusce euismod consequat ante.
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        -->
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
	document.frm.userId.value ="administrator@hmrauctions.com.ph";
	document.frm.pw.value = "administrator";
	document.frm.userId.focus();
	document.frm.submit();
	
	if(document.frm.userId.value!=""){
		<%if(userId!=null){ %>
			document.frm.pw.focus();
		<%}%>
	}

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