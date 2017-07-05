<!DOCTYPE html>
<%@page import="java.util.HashMap"%>
<%@ page import="hmr.com.bean.User"
		 import="hmr.com.bean.Lov"
		 import="java.math.BigDecimal" 
		 import="java.util.List" 
		 import="java.util.HashMap" 
		 
		 import="java.util.ArrayList"  
		 import="javax.servlet.RequestDispatcher"
		 import="java.text.SimpleDateFormat"
%>
<html lang="en">
<head>
    <meta charset="utf-8">
    <!--[if IE]><meta http-equiv="X-UA-Compatible" content="IE=edge"><![endif]-->
    <meta name="viewport" content="width=device-width, initial-scale=1">
	<%

	System.out.println("PAGE user.jsp START");
	
		String COMPANY_NAME = request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";

		String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
		String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
		  
		  
		String firstName = request.getAttribute("firstName")!=null ? (String)request.getAttribute("firstName") : "";
		String lastName = request.getAttribute("lastName")!=null ? (String)request.getAttribute("lastName") : "";
		String userId = request.getAttribute("userId")!=null ? (String)request.getAttribute("userId") : (String)request.getSession().getAttribute("userId");
		BigDecimal mobileNo = request.getAttribute("mobileNo")!=null ? (BigDecimal)request.getAttribute("mobileNo") : null;
		  
		  
		//IDS
		Integer user_id = request.getAttribute("user-id")!=null ? (Integer)request.getAttribute("user-id") : (Integer)request.getSession().getAttribute("user-id");
		  
		User user = request.getAttribute("user")!=null ? (User)request.getAttribute("user") : null;
		  
		
		
		System.out.println("userId : "+userId);
		System.out.println("user_id : "+user_id);
		
		System.out.println("user : "+user);
		/*
		List<User> uList =(ArrayList<User>) request.getAttribute("userList");
		System.out.println("ulList "+uList.size());
		*/
		
		ArrayList<Lov> userRoleList = (ArrayList<Lov>) request.getSession().getAttribute("USER-ROLE-LIST");
		ArrayList<Lov> genderList = (ArrayList<Lov>) request.getSession().getAttribute("GENDER-LIST");
		ArrayList<Lov> userStatusList = (ArrayList<Lov>) request.getSession().getAttribute("USER-STATUS-LIST");
		
		HashMap<Integer,Lov> userRoleHM = (HashMap<Integer,Lov>) request.getSession().getAttribute("USER-ROLE-HM");
		HashMap<Integer,Lov> genderHM = (HashMap<Integer,Lov>) request.getSession().getAttribute("GENDER-HM");
		HashMap<Integer,Lov> userStatusHM = (HashMap<Integer,Lov>) request.getSession().getAttribute("USER-STATUS-HM");
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
			
	%>
    <title><%=COMPANY_NAME%></title>



    <!-- DataTables -->
    <link rel="stylesheet" href="plugins/datatables/dataTables.bootstrap.css">

	<!-- Local CSS -->
    <link rel="stylesheet" type="text/css" href="css/jquery.datetimepicker.css"/>
    <link href="css/select2.min.css" type="text/css" rel="stylesheet" />

    <!-- Favicon -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="shortcut icon" href="ico/hmr-favicon.ico">

    <!-- CSS Global -->
    <link href="assets/plugins/bootstrap/css/bootstrap.css" rel="stylesheet">
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

	<script>
	   function trim (el) {
		    el.value = el.value.
		       replace (/(^\s*)|(\s*$)/gi, ""). // removes leading and trailing spaces
		       replace (/[ ]{2,}/gi," ").       // replaces multiple spaces with one space 
		       replace (/\n +/,"\n");           // Removes spaces after newlines
		    return;
		}
	</script>
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
                <div class="row" >

                    <div class="col-sm-12">
                        <h3 class="block-title"><span>Users  <label>Details </label></span></h3>
                        <div id="msgDiv"></div>
 					</div>
 					
					<div class="col-sm-4">
		              <div class="form-group">
		                <label><b>First Name : </b> <%=user.getFirst_name()%></label>
		              </div>
		              <div class="form-group">
		                <label><b>Last Name : </b> <%=user.getLast_name()%></label>
		              </div>
		              <div class="form-group">
		            <%
		            	String birthDate = "";
		            	if(user.getBirth_date()!=null){
		            		birthDate = sdf.format(user.getBirth_date());
		            	}
		            %>	
		                <label><b>Birth Date : </b> <%=birthDate%></label>
		             
		              </div>
						<div class="form-group">
		            <%
		            	String gender = "";
		            	if(user.getGender() > 0){
		            		gender = genderHM.get(user.getGender()).getValue();
		            	}
		            
		            %>
							<label><b>Gender : </b> <%=gender%></label>
						</div>
		              <div class="form-group">
		                <label><b>Company : </b><%if(user.getCompany()!=null){ %> <%=user.getCompany()%> <%}%></label>
		              </div>
						
						<div class="form-group">
							<label><b>Email Address : </b> <%=user.getEmail_address()%></label>
						</div>
						<!-- 
						<div class="form-group">
							<label><b>Password : </b> <%=user.getPass_word()%></label>
						</div>
						-->

						
					</div>
					
					
				
				<div class="col-sm-4">
				
		            <div class="form-group">
		            <%
		            	String userStatus = "";
		            	if(user.getStatus() > 0){
		            		userStatus = userStatusHM.get(user.getStatus()).getValue();
		            	}
		            
		            %>
		              <label><b>Status : </b> <%=userStatus%></label>
		            </div>
				
					<div class="form-group">
		            <%
		            	String active = "";
		            	if(user.getActive() == 1){
		            		active = "Yes";
		            	}else if(user.getActive() == 0){
		            		active = "No";
		            	}
		            %>	
		
		             <label><b>Active : </b> <%=active%></label>
						
		            </div>
		            <div class="form-group">
		            <%
		            	String userRole = "";
		            	if(user.getRole() > 0){
		            		userRole = userRoleHM.get(user.getRole()).getValue();
		            	}
		            %>
		              <label><b>Role : </b> <%=userRole%></label>
						
		            </div>
					<div class="form-group">
		            <%
		            	String newsLetter = "";
		            	if(user.getNews_letter() == 1){
		            		newsLetter = "Yes";
		            	}else if(user.getNews_letter() == 0){
		            		newsLetter = "No";
		            	}
		            %>		
		             <label><b>News Letter : </b> <%=newsLetter%></label>
		            </div>
					<div class="form-group">	
		            <%
		            	String newsLetterRegistrationDate = "";
		            	if(user.getNews_letter_registration_date()!=null){
		            		newsLetterRegistrationDate = sdf.format(user.getNews_letter_registration_date());
		            	}
		            %>	
		             <label><b>News Letter Registration Date : </b> <%=newsLetterRegistrationDate%></label>
		            </div>
					<div class="form-group">	
		            <%
		            	String dateRegistration = "";
		            	if(user.getDate_registration()!=null){
		            		dateRegistration = sdf.format(user.getDate_registration());
		            	}
		            %>	
		             <label><b>Registration Date : </b> <%=dateRegistration%></label>
		            </div>
		            <div class="form-group">
		            <%
		            	String datePasswordChange = "";
		            	if(user.getDate_password_change()!=null){
		            		datePasswordChange = sdf.format(user.getDate_password_change());
		            	}
		            %>	
						
		             <label><b>Password Change Date : </b> <%=datePasswordChange%></label>
		            </div>
		            

				</div>
		
		
		
				<div class="col-sm-4">
				

				
		              <div class="form-group">
		                <label><b>Mobile No : </b> <%=user.getMobile_no_1()%></label>
		              </div>
		              <div class="form-group">
		                <label><b>Alternate Mobile No : </b><%if(user.getMobile_no_2()!=null){ %><%=user.getMobile_no_2()%><%}%></label>
		              </div>
		              <div class="form-group">
		                <label><b>Landline No : </b><%if(user.getLandline_no()!=null){ %><%=user.getLandline_no()%><%}%></label>
		              </div>
		              
		              <div class="form-group">
		                <label><b>Bidder No : </b> <%=user.getBidder_no()%></label>
		              </div>
		              
		              <div class="form-group">
		                <label><b>Reserve Bidder No : </b> <%=user.getReserve_bidder_no()%></label>
		              </div>
		              
						<div class="form-group">
							<label><b>Verification Email Key : </b> <%=user.getVerification_email_key()%></label>
						</div>
		            <div class="form-group">
		            <%
		            	String showChangePasswordNextLogin = "";
		            	if(user.getShowChangePasswordNextLogin() == 1){
		            		showChangePasswordNextLogin = "Yes";
		            	}else if(user.getShowChangePasswordNextLogin() == 0){
		            		showChangePasswordNextLogin = "No";
		            	}
		            %>		
		            
		             <label><b>Show Change Password Next Login : </b> <%=showChangePasswordNextLogin%></label>
		            </div>
				</div>

				<div class="col-sm-12">
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="createUser()">Create</a>
					</div>
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="updateUser()">Update</a>
					</div>
				</div>
					

                    <!-- 
                    <div class="col-sm-2">
                        <a class="btn btn-theme btn-block " href="#" onclick="clearLogin()">Clear</a>
                    </div>
        --->
                    
	                
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
function createUser(){
	document.frm.action.value="createUser";
	document.frm.submit();
}

function updateUser(){
	document.frm.action.value="updateUser";
	document.frm.submit();
}

function onLoadPage(){
	//document.frm.userId.focus();	
/*	
//	if(document.frm.userId.value!=""){
//		< /%if(userId!=null){ %>
//			document.frm.pw.focus();
//		< /%}%>
//	}
*/


//alert("</%=auction.getTerms_and_conditions()%>");
//document.getElementById("terms_and_conditions").innerHTML="</%=auction.getTerms_and_conditions()%>";



//$('#terms_and_conditions').val('sadfsd');

	//document.frm..value=;
	
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
<!--  <script src="assets/js/theme-config.js"></script>  -->
<!--<![endif]-->

<!-- DataTables -->
<script src="plugins/datatables/jquery.dataTables.js"></script>
<script src="plugins/datatables/dataTables.bootstrap.js"></script>

<script src="js/jquery.datetimepicker.full.js"></script>
<script type="text/javascript" src="js/select2.full.js"></script>

	<form action="bid" name="frm" id="frm" method="post">
		<input type="hidden" name="manager" id="manager" value="user-manager"/>
		<input type="hidden" name="action" id="action" value=""/>
		<input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
		<input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
		<input type="hidden" name="userId_wip" id="userId_wip" value="<%=user.getId()%>"/>
	</form>
</body>
</html>