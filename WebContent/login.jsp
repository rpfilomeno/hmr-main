<!DOCTYPE html>
<%@ page import="hmr.com.bean.User"
		 import="java.util.List"  
%>
<%
	String COMPANY_NAME = request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";
	String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
	String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
	  
	String firstName = request.getAttribute("firstName") != null ? (String) request.getAttribute("firstName")
			: "";
	String lastName = request.getAttribute("lastName") != null ? (String) request.getAttribute("lastName") : "";
	String userId = request.getAttribute("userId") != null ? (String) request.getAttribute("userId") : "";
	//Integer mobileNo = request.getAttribute("mobileNo")!=null ? (Integer)request.getAttribute("mobileNo") : null;

	//IDS
	Integer user_id = request.getAttribute("user_id") != null ? (Integer) request.getAttribute("user_id")
			: null;
%>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

        <title><%=COMPANY_NAME%></title>
        
        <!-- Favicons -->
        <link rel="apple-touch-icon" sizes="180x180" href="assets/themes/hmr/apple-touch-icon.png">
        <link rel="icon" type="image/png" href="assets/themes/hmr/favicon-32x32.png" sizes="32x32">
        <link rel="icon" type="image/png" href="assets/themes/hmr/favicon-16x16.png" sizes="16x16">
        <link rel="manifest" href="assets/themes/hmr/manifest.json">
        <link rel="mask-icon" href="assets/themes/hmr/safari-pinned-tab.svg" color="#222222">
        <link rel="shortcut icon" href="assets/themes/hmr/favicon.ico">
        <meta name="theme-color" content="#ffffff">

    
        <meta property="og:site_name" content="HMR Auctions"/>
        <meta property="og:title" content="Product View — HMR Auctions"/>
        <meta property="og:url" content="product-view.html"/>
                <meta property="og:type" content="website" />
        
        <meta property="og:description" content=""/>
        
        <meta property="og:image" content=""/>

        <meta itemprop="name" content="Product View"/>
        <meta itemprop="url" content="product-view.html"/>
        <meta itemprop="description" content=""/>
        <meta itemprop="thumbnailUrl" content=""/>
        
        <meta name="twitter:card" content="summary_large_image"/>
        <meta name="twitter:title" content="Product View"/>
        <meta name="twitter:image" content=""/>
        <meta name="twitter:url" content="product-view.html"/>
        <meta name="twitter:text" content=""/>
        <meta name="twitter:domain" content="">

        <meta name="description" content="" />

        <link rel="stylesheet" href="assets/themes/hmr/css/bootstrap.css">
		<link rel="stylesheet" href="assets/themes/hmr/css/ionicons.min.css">
		<link rel="stylesheet" href="assets/themes/hmr/css/main.css?v=36502498">
		
		<link rel="stylesheet" href="assets/css/gridder.css" />
		<link rel="stylesheet" href="assets/plugins/jquery-ui/themes/smoothness/jquery-ui.min.css">
		
		<script src=assets/themes/hmr/js/vendor/modernizr.js></script>
		<script src=assets/themes/hmr/js/vendor/jquery-1.11.3.js></script>
		<script src=assets/themes/hmr/js/vendor/jquery-migrate-1.2.1.min.js></script>
		<script src=assets/themes/hmr/js/vendor/social.js></script>
		<script src=assets/themes/hmr/js/vendor/masonry.pkgd.min.js></script>
		<script src=assets/themes/hmr/js/vendor/owl.carousel.min.js?v=2></script>
		<script src=assets/themes/hmr/js/vendor/jquery.form.js></script>
		<script src=assets/themes/hmr/js/vendor/jquery.easing.1.3.js></script>
		<script src=assets/themes/hmr/js/bootstrap.js></script>
		<script src=assets/themes/hmr/js/vendor/bootbox.min.js></script>
		<script src=assets/themes/hmr/js/vendor/url.min.js></script>
		<script src=assets/themes/hmr/js/vendor/msis.js></script>
		<script src=assets/themes/hmr/js/vendor/jquery.lazyload.min.js></script>
		<script src=assets/themes/hmr/js/main.js?v=79186204></script>
		
		<script src="assets/plugins/jquery-ui/jquery-ui-1.11.1.min.js"></script>
		<script src="assets/plugins/gridder/jquery.gridder.min.js"></script>
		<script src="assets/plugins/jquery.bsAlerts.min.js"></script>
                
    </head>
    <body data-is-mobile="" id="c" >
    
    <input type="hidden" id="base_url" value="">
        <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <strong>outdated</strong> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->
 
<div id="ms--main--nav">
	<nav id="main-navigation" class="navbar navbar-fixed-top">
  <div id="top-nav">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <div class="top-nav-items nav-white visible-md visible-lg">
            
            <ul class="top-navbar">
              <li>(02)548-6962</li>
              <li>0917 548 3603</li>
              <li>
                  <a href="mailto:online-auction@hmrbid.com">online-auction@hmrbid.com</a>
              </li>
              <li>
                <a href="">Home</a>
              </li>
              <li>
                <a href="bid?mngr=get&a=login">Login</a>
              </li>
              <li>
                <a href="bid?mngr=get&a=registration">Register</a>
              </li>             
            </ul>
    
            
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container">
    <div class="row gutter-0">
      <div class="col-md-2">
        <div class="site--logo">
                      <a class="logo-default" href="bid"><img src="assets/themes/hmr/img/HMR-logo-white.png" alt="HMR Auctions" class="img-responsive"></a>
                  </div>
      </div>

      <div class="col-md-10">
        <div class="row gutter-0">
          <div class="col-md-8">
            
              
          </div>
          <div class="col-md-4">
            <div id="main-nav-items" class="main-nav-items nav-white visible-md visible-lg">

              <ul class="nav navbar-nav navbar-right navbar-icons">
                <li>
                  <a href="cart">
                    <span id="bag-count"></span>
                    <span class="navbar-icon ion-bag"></span>
                  </a>
                </li>
                
                
              </ul>
      
              
            </div>
          </div>
        </div>

        

      </div>
    </div>
  </div>

  <div id="sub-navigation">
    <div class="container">
      <div class="row">
        <div class="col-md-12">
          <ul class="navbar-subnav">
            <li>
              <a href="">Online Bidding</a>
            </li>
            <li>
              <a href="">Negotiated Bids</a>
            </li>
            <li>
              <a href="">Live Auctions</a>
            </li>
            <li>
              <a href="">Services</a>
            </li>
            <li>
              <a href="">Gallery</a>
            </li>
            <li>
              <a href="">Contact Us</a>
            </li>
          </ul>    
        </div>
      </div>
    </div>
  </div>
</nav>




<div id="mobile-nav-trigger" class="visible-sm visible-xs">
  <div id="nav-trigger">
      <span></span>
      <span></span>
      <span></span>
  </div>
</div>

<div id="mobile-nav-wrapper">
  <div id="mobile-logo" class="site--logo">
    <a class="logo-default" href=""><img src="assets/themes/hmr/img/HMR-logo-white.png" alt="HMR Auctions" class="img-responsive"></a>
  </div>
  <div id="mobile-nav-inject"></div>
</div>




<div id="search-dialog" class="ms-dialog hide">
  <div class="ms-dialog-header">
    <a href="#" class="ms-dialog-close-btn"><span class="ion-ios-close-empty"></span></a>
  </div>
  <div class="container">
    <div class="row">
      <div class="col-md-8 col-md-offset-2">
        
        <form action="" id="search-form" method="post" accept-charset="utf-8" onkeypress="stopEnterSubmitting(window.event)">
          
        <div class="search-input-wrap">
          <input type="text" class="form-control" id="search-input" placeholder="Search">
        </div>

        </form>
      </div>
    </div>
  </div>
</div>
	
</div>

	
<div id="ms--main--body">
	<section id="account-login-section" class="page-section">
	<div class="container">
		<div class="row">
			<div data-alerts="alerts" data-titles='{"warning": "<em>Warning!</em>", "error": "<em>Error!</em>"}' data-ids="myid" data-fade="3000"></div>
			
			<div class="col-md-4 col-lg-4 col-md-offset-4 col-lg-offset-4">
				
				<div class="customer-register-wrap">
					<h2 class="tac">Account Login</h2>
					<div class="clearfix top20"></div>


				
					<form action="bid" class="form-login" name="frm" method="post" onkeypress="stopEnterSubmitting(window.event)">
					
												
												
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
									<a class="btn btn-primary pull-right" onclick="submitPage()">Login</a>
									<a class="btn btn-primary pull-right" onclick="clearPage()">Clear</a>
								</div>		
								<div class="clearfix"></div>
							</div>
						</div>
						

						<div class="clearfix top20"></div>
						<div class="tac">
							<div><a href="bid?mngr=get&a=registration">Don't have an account? Register here.</a></div>
						</div>
						
						<input type="hidden" name="manager" id="manager" value="login-manager"/>
                        <input type="hidden" name="action" id="action" value="login"/>
                        <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
					</form>				

				</div>
			</div>
			
		</div>
		
	</div>

	<div class="clearfix top100"></div>
</section></div>


	<section class="instagram-section">
	<div class="container-fluid">
		<!-- <div id="instagram" class="row no-gutter"></div> -->
	</div>
</section>




<div id="footer">
	<div class="container">
		<div class="footer-lv1">
			<div class="row">
				<div class="col-md-8">
					<div class="row">
						<div class="col-md-12">
							<div class="row">
								<div class="col-md-3">
									<h3 class="top10"><a href="">HMR</a></h3>
								</div>
								<div class="col-md-9">
									<div class="row">
																				<div class="col-sm-6">
											<h4 class="footer-header">Purchase</h4>
											<ul class="list-block">
														<li>
													<a href="terms-and-conditions">Terms and Conditions</a>
												</li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
				<div class="col-md-4">
					<div class="tarasd">
						<h4 class="footer-header">Subscribe to our Weekly Newsletter</h4>
						<form action="q/validate_email_subscribers" id="newsletter-form" method="post" accept-charset="utf-8" onkeypress="stopEnterSubmitting(window.event)">
						<div class="form-group">
							<div class="input-group">
								<input type="text" name="email" id="email" class="form-control" placeholder="Your email address">
								<span class="input-group-btn">
									<input type="submit" class="btn btn-primary" value="Join" style="width: 90px;" />
								</span>
							</div><!-- /input-group -->
							<div class="help-block"></div>
						</div>
						</form>					</div>

					<ul class="social-media-links social-media-links-inline">
						<li>
							<a target="_BLANK" href="#">
								<span class="social-icon"><img src="assets/themes/hmr/img/facebook-logo-white.png" class="img-responsive"></span>
							</a>
						</li>
						<li>
							<a target="_BLANK" href="#">
								<span class="social-icon"><img src="assets/themes/hmr/img/youtube-logo-white.png" class="img-responsive"></span>
							</a>
						</li>
						<li>
							<a target="_BLANK" href="#">
								<span class="social-icon"><img src="assets/themes/hmr/img/twitter-logo-white.png" class="img-responsive"></span>
							</a>
						</li>
						
					</ul>

					<div class="clearfix"></div>
					<div class="all-rights-reserved">
						HMR Auctions © 2017. All Rights Reserved.
					</div>

				</div>
			</div>
		</div>
	</div>
</div>

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
	if(document.frm.userId.value==""){
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

function clearPage(){
	document.frm.userId.value="";
	document.frm.pw.value="";
	document.frm.userId.focus();
	document.frm.userId.value="administrator@hmrauctions.com.ph";
	document.frm.pw.value="administrator";
}

function submitPage(){

	
	if(validateLogin()){
		document.frm.submit();
	}
	return false;
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