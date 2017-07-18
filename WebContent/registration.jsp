<!DOCTYPE html>
<%@ page import="hmr.com.bean.User"
		 import="java.util.List"
		 import="java.math.BigDecimal"
		 
		   
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
	  BigDecimal mobileNo = request.getAttribute("mobileNo")!=null ? (BigDecimal)request.getAttribute("mobileNo") : null;
	  
	  String addressStreetNo = request.getAttribute("addressStreetNo")!=null ? (String)request.getAttribute("addressStreetNo") : "";
	  String addressBaranggay = request.getAttribute("addressBaranggay")!=null ? (String)request.getAttribute("addressBaranggay") : "";
	  String addressCity = request.getAttribute("addressCity")!=null ? (String)request.getAttribute("addressCity") : "";
	  String addressProvince = request.getAttribute("addressProvince")!=null ? (String)request.getAttribute("addressProvince") : "";
	  String addressZipCode = request.getAttribute("addressZipCode")!=null ? (String)request.getAttribute("addressZipCode") : "";
	  String companyName = request.getAttribute("companyName")!=null ? (String)request.getAttribute("companyName") : "";
	  
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
                        <h3 class="block-title"><span>Sign Up</span></h3>
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
				                <div id="msgDiv">
				                </div>
                            	
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
												<% int year = 2017; %>
												<% for(int i = (year-110); i < year ; i++) {  %>
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
                                
                                
                                
                                
                                
                                <%--
                                <div class="col-md-12 col-lg-6">
                                    <div class="checkbox">
                                        <label><input type="checkbox"> Remember me</label>
                                    </div>
                                </div>
                                <div class="col-md-12 col-lg-6 text-right-lg">
                                    <a class="forgot-password" href="#">forgot password?</a>
                                </div>  
                                --%>
                                <div class="col-md-6">
                                    <a class="btn btn-theme btn-block btn-theme-dark" href="#" onclick="submitPage()">Sign Up</a>
                                </div>
                                <div class="col-md-6">
                                    <a class="btn btn-theme btn-block btn-theme-dark" href="#" onclick="clearSignUp()">Clear</a>
                                </div>
                            </div>
                            <input type="hidden" name="action" id="action" value="registration"/>
                            <input type="hidden" name="manager" id="manager" value="user-manager"/>
                        </form>
                    </div>
                    
                    <%--
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
                    --%>
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

function onLoadPage(){
	document.frm.firstName.focus();	
}

function validateSignUp(){
	var isSignUp = true;
	
	if(document.frm.firstName.value==""){
		var msgInfo = "First Name is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.firstName.focus();
		isSignUp = false;
	}else if(document.frm.lastName.value==""){
		var msgInfo = "Last Name is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.lastName.focus();
		isSignUp = false;
	}else if(document.frm.userId.value==""){
		var msgInfo = "Email is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.userId.focus();
		isSignUp = false;
	}else if(document.frm.mobileNo.value==""){
		var msgInfo = "Mobile No is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.mobileNo.focus();
		isSignUp = false;
	}
	
	
	else if(document.frm.gender.options[document.frm.gender.selectedIndex].value==""){
		var msgInfo = "Gender is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.mobileNo.focus();
		isSignUp = false;
	}
	
	else if(document.frm.dobmonth.options[document.frm.dobmonth.selectedIndex].value==""){
		var msgInfo = "Month is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.mobileNo.focus();
		isSignUp = false;
	}
	
	else if(document.frm.dobday.options[document.frm.dobday.selectedIndex].value==""){
		var msgInfo = "Day is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.mobileNo.focus();
		isSignUp = false;
	}
	
	else if(document.frm.dobyear.options[document.frm.dobyear.selectedIndex].value==""){
		var msgInfo = "Year is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.mobileNo.focus();
		isSignUp = false;
	}
	
	else if(document.frm.addressStreetNo.value==""){
		var msgInfo = "Street Number is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.mobileNo.focus();
		isSignUp = false;
	}
	
	else if(document.frm.addressBaranggay.value==""){
		var msgInfo = "Baranggay is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.mobileNo.focus();
		isSignUp = false;
	}
	
	else if(document.frm.addressCity.value==""){
		var msgInfo = "City is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.mobileNo.focus();
		isSignUp = false;
	}
	
	else if(document.frm.addressZipCode.value==""){
		var msgInfo = "ZipCode is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.mobileNo.focus();
		isSignUp = false;
	}
	
	else if(document.frm.addressProvince.value==""){
		var msgInfo = "Province is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.mobileNo.focus();
		isSignUp = false;
	}
	
	
	else if(document.frm.companyName.value==""){
		var msgInfo = "Company is required.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.mobileNo.focus();
		isSignUp = false;
	}
	
	
	
	else if(!ValidateEmail(document.frm.userId.value)){
		var msgInfo = "Email is invalid.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.userId.focus();
		isSignUp = false;
	}else if(ValidateText(document.frm.firstName.value)){
		var msgInfo = "First Name is invalid.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.firstName.focus();
		isSignUp = false;
	}else if(ValidateText(document.frm.lastName.value)){
		var msgInfo = "Last Name is invalid.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.lastName.focus();
		isSignUp = false;
	}
	
	else if(ValidateText(document.frm.addressStreetNo.value)){
		var msgInfo = "Street/Number is invalid.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.lastName.focus();
		isSignUp = false;
	}
	
	else if(ValidateText(document.frm.addressBaranggay.value)){
		var msgInfo = "Baranggay is invalid.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.lastName.focus();
		isSignUp = false;
	}
	
	else if(ValidateText(document.frm.addressCity.value)){
		var msgInfo = "City is invalid.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.lastName.focus();
		isSignUp = false;
	}
	
	else if(ValidateText(document.frm.addressProvince.value)){
		var msgInfo = "Province is invalid.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.lastName.focus();
		isSignUp = false;
	}
	
	else if(ValidateText(document.frm.addressZipCode.value)){
		var msgInfo = "Zip Code is invalid.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
		document.frm.lastName.focus();
		isSignUp = false;
	}
	
	else if(ValidateText(document.frm.companyName.value)){
		var msgInfo = "Company is invalid.";
		var msgbgcol = "red";
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
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

<%if(msgInfo!=null){%>
	
	var msgInfo = "<%=msgInfo%>";
	var msgbgcol = "<%=msgbgcol%>";
	var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
	msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
	msgBoxValue = msgBoxValue + '</div>';
	document.getElementById("msgDiv").innerHTML=msgBoxValue;

<%}%>


setTimeout(function(){document.getElementById("msgDiv").innerHTML="";},5000);

setTimeout(onLoadPage,3000);



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
<!-- <script src="assets/js/theme-config.js"></script> -->
<!--<![endif]-->

</body>
</html>