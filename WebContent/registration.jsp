<!DOCTYPE html>
<%@ page import="hmr.com.bean.User"
		 import="java.util.List"
		 import="java.math.BigDecimal"
%>
<%
	String COMPANY_NAME = request.getSession().getAttribute("COMPANY_NAME") != null
			? (String) request.getSession().getAttribute("COMPANY_NAME") : "HMR Auctions";

	String msgInfo = request.getAttribute("msgInfo") != null ? (String) request.getAttribute("msgInfo") : null;
	String msgbgcol = request.getAttribute("msgbgcol") != null ? (String) request.getAttribute("msgbgcol") : "";

	String firstName = request.getAttribute("firstName") != null ? (String) request.getAttribute("firstName")
			: "";
	String lastName = request.getAttribute("lastName") != null ? (String) request.getAttribute("lastName") : "";
	String userId = request.getAttribute("userId") != null ? (String) request.getAttribute("userId") : "";
	BigDecimal mobileNo = request.getAttribute("mobileNo") != null
			? (BigDecimal) request.getAttribute("mobileNo") : null;

	String addressStreetNo = request.getAttribute("addressStreetNo") != null
			? (String) request.getAttribute("addressStreetNo") : "";
	String addressBaranggay = request.getAttribute("addressBaranggay") != null
			? (String) request.getAttribute("addressBaranggay") : "";
	String addressCity = request.getAttribute("addressCity") != null
			? (String) request.getAttribute("addressCity") : "";
	String addressProvince = request.getAttribute("addressProvince") != null
			? (String) request.getAttribute("addressProvince") : "";
	String addressZipCode = request.getAttribute("addressZipCode") != null
			? (String) request.getAttribute("addressZipCode") : "";
	String companyName = request.getAttribute("companyName") != null
			? (String) request.getAttribute("companyName") : "";
	Integer currentYear = request.getAttribute("currentYear") != null
			? (Integer) request.getAttribute("currentYear") : 2017;

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
                  <a href="mailto:auction@hmrphils.com">auction@hmrphils.com</a>
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
                      <a class="logo-default" href=""><img src="assets/themes/hmr/img/HMR-logo-white.png" alt="HMR Auctions" class="img-responsive"></a>
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
	<section id="register-account-section" class="page-section">
	<div class="container">
		<div class="row">
			<div data-alerts="alerts" data-titles='{"warning": "<em>Warning!</em>", "error": "<em>Error!</em>"}' data-ids="myid" data-fade="3000"></div>
			
			<div class="col-md-4 col-lg-4 col-md-offset-4 col-lg-offset-4">

				<div class="customer-register-wrap">
					<h2 class="tac">Account Register</h2>
					<div class="clearfix top20"></div>

					<div class="alert  alert-dismissible hide ">
	<button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
  	<strong></strong>
	</div>
					
						<form action="bid" class="form-login" name="frm" method="post" onkeypress="stopEnterSubmitting(window.event)">
                            <div class="row">
                            	
                                <div class="col-md-12 hello-text-wrap">
                                    <span class="hello-text text-thin">Hello, Sign Up Your New Account on HMR Auctions</span>
                                </div>
                                <!-- 
                                <div class="col-md-12 col-lg-6">
                                    <a class="btn btn-theme btn-block btn-icon-left facebook" href="#"><i class="fa fa-facebook"></i>Sign in with Facebook</a>
                                </div>
                                <div class="col-md-12 col-lg-6">
                                    <a class="btn btn-theme btn-block btn-icon-left twitter" href="#"><i class="fa fa-twitter"></i>Sign in with Twitter</a>
                                </div> -->
                                
                                <div class="col-md-12">
                                    <div class="form-group"><input class="form-control" type="text" name="firstName" placeholder="First Name" maxlength="50" value="<%=firstName%>"></div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group"><input class="form-control" type="text" name="lastName" placeholder="Last Name" maxlength="50" value="<%=lastName%>"></div>
                                </div>
                                <div class="col-md-12">
                                    <div class="form-group"><input  class="form-control" type="email" name="userId" placeholder="Email" maxlength="50" value="<%=userId%>"></div>
                                </div>
                                <div class="col-md-12">
                                    
                                    <div class="form-group"><input class="form-control" type="text" name="mobileNo"  placeholder="Mobile No" maxlength="15" value="<%if(mobileNo!=null){ %><%=mobileNo%><%}%>"  onkeypress="return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 13; submitOnEnter(event)" /></div>
                                </div>
                                
                                <div class="col-md-12">
										<div class="form-group">
												<select class="form-control" id="gender" name="gender">
													<option value="" disabled selected>Your Gender?</option>
													<option value="13">I'm Male</option>
													<option value="14">I'm Female</option>
												</select>
										</div>
								</div>
								
								<div class="col-md-12">
									<div class="form-group">
										<div class="input-group">
    										<span class="input-group-addon" title="* Birth Date" id="priceLabel">Birth Date</span>
    										<select class="form-control" id="dobmonth" name="dobmonth">
												<option value="" disabled selected>Month?</option>
												<option value="1">January</option>
												<option value="2">February</option>
												<option value="3">March</option>
												<option value="4">April</option>
												<option value="5">May</option>
												<option value="6">June</option>
												<option value="7">July</option>
												<option value="8">August</option>
												<option value="9">September</option>
												<option value="10">October</option>
												<option value="11">November</option>
												<option value="12">December</option>
											</select>
											<span class="input-group-addon" style="width:0px; padding-left:0px; padding-right:0px; border:none;"></span>
											<select class="form-control" id="dobday" name="dobday">
												<option value="" disabled selected>Day?</option>
												
												<% for(int i = 1; i < 32 ; i++) {  %>
												<option value="<%=i %>"><%=i %></option>
												<% } %>
											</select>
											<span class="input-group-addon" style="width:0px; padding-left:0px; padding-right:0px; border:none;"></span>
											<select class="form-control" id="dobyear" name="dobyear">
												<option value="" disabled selected>Year?</option>

												<% for(int i = (currentYear-110); i < currentYear ; i++) {  %>
												<option value="<%=i %>"><%=i %></option>
												<% } %>
											</select>
    									</div>
									</div>
								</div>
							
								<div class="col-md-12">
                                    
                                    <div class="form-group"><input class="form-control" type="text" name="addressStreetNo"  placeholder="Street/Number" maxlength="50" value="<%if(addressStreetNo!=null){ %><%=addressStreetNo%><%}%>"  onkeypress="return event.charCode >= 48 && event.charCode <= 57 || event.charCode == 13; submitOnEnter(event)" /></div>
                                </div>
                                
                                <div class="col-md-12">
                                    
                                    <div class="form-group"><input class="form-control" type="text" name="addressBaranggay"  placeholder="Baranggay" maxlength="50" value="<%if(addressBaranggay!=null){ %><%=addressBaranggay%><%}%>"   /></div>
                                </div>
                                
                                <div class="col-md-12">
                                    
                                    <div class="form-group"><input class="form-control" type="text" name="addressCity"  placeholder="City/Town" maxlength="50" value="<%if(addressCity!=null){ %><%=addressCity%><%}%>"   /></div>
                                </div>
                                
                                <div class="col-md-12">
                                    
                                    <div class="form-group"><input class="form-control" type="text" name="addressProvince"  placeholder="Province" maxlength="50" value="<%if(addressProvince!=null){ %><%=addressProvince%><%}%>"   /></div>
                                </div>
                                
                                <div class="col-md-12">
										<div class="form-group">
												<select class="form-control" id="addressCountry" name="addressCountry">
													<option selected>Philippines</option>
													<option>United States</option>
													<option value="" disabled>---------------------</option>
													<option>Afghanistan</option>
													<option>Albania</option>
													<option>Algeria</option>
													<option>Andorra</option>
													<option>Angola</option>
													<option>Antigua &amp; Deps</option>
													<option>Argentina</option>
													<option>Armenia</option>
													<option>Australia</option>
													<option>Austria</option>
													<option>Azerbaijan</option>
													<option>Bahamas</option>
													<option>Bahrain</option>
													<option>Bangladesh</option>
													<option>Barbados</option>
													<option>Belarus</option>
													<option>Belgium</option>
													<option>Belize</option>
													<option>Benin</option>
													<option>Bhutan</option>
													<option>Bolivia</option>
													<option>Bosnia Herzegovina</option>
													<option>Botswana</option>
													<option>Brazil</option>
													<option>Brunei</option>
													<option>Bulgaria</option>
													<option>Burkina</option>
													<option>Burundi</option>
													<option>Cambodia</option>
													<option>Cameroon</option>
													<option>Canada</option>
													<option>Cape Verde</option>
													<option>Central African Rep</option>
													<option>Chad</option>
													<option>Chile</option>
													<option>China</option>
													<option>Colombia</option>
													<option>Comoros</option>
													<option>Congo</option>
													<option>Congo {Democratic Rep}</option>
													<option>Costa Rica</option>
													<option>Croatia</option>
													<option>Cuba</option>
													<option>Cyprus</option>
													<option>Czech Republic</option>
													<option>Denmark</option>
													<option>Djibouti</option>
													<option>Dominica</option>
													<option>Dominican Republic</option>
													<option>East Timor</option>
													<option>Ecuador</option>
													<option>Egypt</option>
													<option>El Salvador</option>
													<option>Equatorial Guinea</option>
													<option>Eritrea</option>
													<option>Estonia</option>
													<option>Ethiopia</option>
													<option>Fiji</option>
													<option>Finland</option>
													<option>France</option>
													<option>Gabon</option>
													<option>Gambia</option>
													<option>Georgia</option>
													<option>Germany</option>
													<option>Ghana</option>
													<option>Greece</option>
													<option>Grenada</option>
													<option>Guatemala</option>
													<option>Guinea</option>
													<option>Guinea-Bissau</option>
													<option>Guyana</option>
													<option>Haiti</option>
													<option>Honduras</option>
													<option>Hungary</option>
													<option>Iceland</option>
													<option>India</option>
													<option>Indonesia</option>
													<option>Iran</option>
													<option>Iraq</option>
													<option>Ireland {Republic}</option>
													<option>Israel</option>
													<option>Italy</option>
													<option>Ivory Coast</option>
													<option>Jamaica</option>
													<option>Japan</option>
													<option>Jordan</option>
													<option>Kazakhstan</option>
													<option>Kenya</option>
													<option>Kiribati</option>
													<option>Korea North</option>
													<option>Korea South</option>
													<option>Kosovo</option>
													<option>Kuwait</option>
													<option>Kyrgyzstan</option>
													<option>Laos</option>
													<option>Latvia</option>
													<option>Lebanon</option>
													<option>Lesotho</option>
													<option>Liberia</option>
													<option>Libya</option>
													<option>Liechtenstein</option>
													<option>Lithuania</option>
													<option>Luxembourg</option>
													<option>Macedonia</option>
													<option>Madagascar</option>
													<option>Malawi</option>
													<option>Malaysia</option>
													<option>Maldives</option>
													<option>Mali</option>
													<option>Malta</option>
													<option>Marshall Islands</option>
													<option>Mauritania</option>
													<option>Mauritius</option>
													<option>Mexico</option>
													<option>Micronesia</option>
													<option>Moldova</option>
													<option>Monaco</option>
													<option>Mongolia</option>
													<option>Montenegro</option>
													<option>Morocco</option>
													<option>Mozambique</option>
													<option>Myanmar, {Burma}</option>
													<option>Namibia</option>
													<option>Nauru</option>
													<option>Nepal</option>
													<option>Netherlands</option>
													<option>New Zealand</option>
													<option>Nicaragua</option>
													<option>Niger</option>
													<option>Nigeria</option>
													<option>Norway</option>
													<option>Oman</option>
													<option>Pakistan</option>
													<option>Palau</option>
													<option>Panama</option>
													<option>Papua New Guinea</option>
													<option>Paraguay</option>
													<option>Peru</option>
													<option>Philippines</option>
													<option>Poland</option>
													<option>Portugal</option>
													<option>Qatar</option>
													<option>Romania</option>
													<option>Russian Federation</option>
													<option>Rwanda</option>
													<option>St Kitts &amp; Nevis</option>
													<option>St Lucia</option>
													<option>Saint Vincent &amp; the Grenadines</option>
													<option>Samoa</option>
													<option>San Marino</option>
													<option>Sao Tome &amp; Principe</option>
													<option>Saudi Arabia</option>
													<option>Senegal</option>
													<option>Serbia</option>
													<option>Seychelles</option>
													<option>Sierra Leone</option>
													<option>Singapore</option>
													<option>Slovakia</option>
													<option>Slovenia</option>
													<option>Solomon Islands</option>
													<option>Somalia</option>
													<option>South Africa</option>
													<option>South Sudan</option>
													<option>Spain</option>
													<option>Sri Lanka</option>
													<option>Sudan</option>
													<option>Suriname</option>
													<option>Swaziland</option>
													<option>Sweden</option>
													<option>Switzerland</option>
													<option>Syria</option>
													<option>Taiwan</option>
													<option>Tajikistan</option>
													<option>Tanzania</option>
													<option>Thailand</option>
													<option>Togo</option>
													<option>Tonga</option>
													<option>Trinidad &amp; Tobago</option>
													<option>Tunisia</option>
													<option>Turkey</option>
													<option>Turkmenistan</option>
													<option>Tuvalu</option>
													<option>Uganda</option>
													<option>Ukraine</option>
													<option>United Arab Emirates</option>
													<option>United Kingdom</option>
													<option>United States</option>
													<option>Uruguay</option>
													<option>Uzbekistan</option>
													<option>Vanuatu</option>
													<option>Vatican City</option>
													<option>Venezuela</option>
													<option>Vietnam</option>
													<option>Yemen</option>
													<option>Zambia</option>
													<option>Zimbabwe</option>
												</select>
										</div>
								</div>
                                
                                
                                
                                <div class="col-md-12">
                                    
                                    <div class="form-group"><input class="form-control" type="text" name="addressZipCode"  placeholder="ZipCode" maxlength="50" value="<%if(addressZipCode!=null){ %><%=addressZipCode%><%}%>"   /></div>
                                </div>
                                
                                <div class="col-md-12">
                                    
                                    <div class="form-group"><input class="form-control" type="text" name="companyName"  placeholder="Company" maxlength="160" value="<%if(companyName!=null){ %><%=companyName%><%}%>"   /></div>
                                </div>
                                
                                <div class="col-md-12">
                                    
                                    <input type="checkbox" name="receiveNotification" id="receiveNotification" autocomplete="off" checked />
							        Receive Notification
                                </div>
                                
                                
                                
                                

                                <div class="col-md-6">
                                    <a class="btn btn-primary btn-theme btn-block btn-theme-dark" href="#" onclick="submitPage()">Sign Up</a>
                                </div>
                                <div class="col-md-6">
                                    <a class="btn btn-primary btn-theme btn-block btn-theme-dark" href="#" onclick="clearSignUp()">Clear</a>
                                </div>
                            </div>
                            <input type="hidden" name="action" id="action" value="registration"/>
                            <input type="hidden" name="manager" id="manager" value="user-manager"/>
                        </form>				</div>
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

function ValidateText(txt)   
{  
	if(/^[a-zA-Z0-9- ]*$/.test(txt) == false) {
	    //alert('Your search string contains illegal characters.');
		return true; 
	}
	return false;
      
}





function submitOnEnter(e){
    if(e.keyCode === 13){
    	submitPage();
        e.preventDefault();
    }else{
    	return false;
    }
}

function numbersOnly(e){
    return e.charCode >= 48 && e.charCode <= 57 && e.charCode==13;
}



function validateSignUp(){
	var isSignUp = true;
	
	if(document.frm.firstName.value==""){
		var msgInfo = "First Name is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		isSignUp = false;
	}else if(document.frm.lastName.value==""){
		var msgInfo = "Last Name is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.lastName.focus();
		isSignUp = false;
	}else if(document.frm.userId.value==""){
		var msgInfo = "Email is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.userId.focus();
		isSignUp = false;
	}else if(document.frm.mobileNo.value==""){
		var msgInfo = "Mobile No is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.mobileNo.focus();
		isSignUp = false;
	}
	
	
	else if(document.frm.gender.options[document.frm.gender.selectedIndex].value==""){
		var msgInfo = "Gender is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		isSignUp = false;
	}
	
	else if(document.frm.dobmonth.options[document.frm.dobmonth.selectedIndex].value==""){
		var msgInfo = "Month is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		isSignUp = false;
	}
	
	else if(document.frm.dobday.options[document.frm.dobday.selectedIndex].value==""){
		var msgInfo = "Day is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		isSignUp = false;
	}
	
	else if(document.frm.dobyear.options[document.frm.dobyear.selectedIndex].value==""){
		var msgInfo = "Year is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		isSignUp = false;
	}
	
	else if(document.frm.addressStreetNo.value==""){
		var msgInfo = "Street Number is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		isSignUp = false;
	}
	
	else if(document.frm.addressBaranggay.value==""){
		var msgInfo = "Baranggay is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		isSignUp = false;
	}
	
	else if(document.frm.addressCity.value==""){
		var msgInfo = "City is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		isSignUp = false;
	}
	
	else if(document.frm.addressZipCode.value==""){
		var msgInfo = "ZipCode is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		isSignUp = false;
	}
	
	else if(document.frm.addressProvince.value==""){
		var msgInfo = "Province is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		isSignUp = false;
	}
	
	
	else if(document.frm.companyName.value==""){
		var msgInfo = "Company is required.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		isSignUp = false;
	}
	
	
	
	else if(!ValidateEmail(document.frm.userId.value)){
		var msgInfo = "Email is invalid.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.userId.focus();
		isSignUp = false;
	}else if(ValidateText(document.frm.firstName.value)){
		var msgInfo = "First Name is invalid.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.firstName.focus();
		isSignUp = false;
	}else if(ValidateText(document.frm.lastName.value)){
		var msgInfo = "Last Name is invalid.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.lastName.focus();
		isSignUp = false;
	}
	
	else if(ValidateText(document.frm.addressStreetNo.value)){
		var msgInfo = "Street/Number is invalid.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.lastName.focus();
		isSignUp = false;
	}
	
	else if(ValidateText(document.frm.addressBaranggay.value)){
		var msgInfo = "Baranggay is invalid.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.lastName.focus();
		isSignUp = false;
	}
	
	else if(ValidateText(document.frm.addressCity.value)){
		var msgInfo = "City is invalid.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.lastName.focus();
		isSignUp = false;
	}
	
	else if(ValidateText(document.frm.addressProvince.value)){
		var msgInfo = "Province is invalid.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.lastName.focus();
		isSignUp = false;
	}
	
	else if(ValidateText(document.frm.addressZipCode.value)){
		var msgInfo = "Zip Code is invalid.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.lastName.focus();
		isSignUp = false;
	}
	
	else if(ValidateText(document.frm.companyName.value)){
		var msgInfo = "Company is invalid.";
		var msgbgcol = "red";
		showAlert(msgInfo, msgbgcol);
		document.frm.lastName.focus();
		isSignUp = false;
	}
	
	
	
	
	
	
	return isSignUp;
}

function clearSignUp(){
	document.frm.firstName.value="";
	document.frm.lastName.value="";
	document.frm.userId.value="";
	document.frm.mobileNo.value="";
	document.frm.firstName.focus();
}

function submitPage(){
	if(validateSignUp()){
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