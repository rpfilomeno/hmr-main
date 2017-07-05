<!DOCTYPE html>
<%@ page import="hmr.com.bean.User"
		 import="hmr.com.bean.Lov"
		 import="java.util.List" 
		 import="java.math.BigDecimal" 
		 
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
		String COMPANY_NAME = request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";

		String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
		String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
		  
		  
		String firstName = request.getAttribute("firstName")!=null ? (String)request.getAttribute("firstName") : "";
		String lastName = request.getAttribute("lastName")!=null ? (String)request.getAttribute("lastName") : "";
		String userId = request.getAttribute("userId")!=null ? (String)request.getAttribute("userId") : (String)request.getSession().getAttribute("userId");
		BigDecimal mobileNo = request.getAttribute("mobileNo")!=null ? (BigDecimal)request.getAttribute("mobileNo") : null;
		  
		  
		//IDS
		Integer user_id = request.getAttribute("user-id")!=null ? (Integer)request.getAttribute("user-id") : (Integer)request.getSession().getAttribute("user-id");
		  
		
		System.out.println("userId : "+userId);
		System.out.println("user_id : "+user_id);
		/*
		List<User> uList =(ArrayList<User>) request.getAttribute("userList");
		System.out.println("ulList "+uList.size());
		*/
		
		ArrayList<Lov> userRoleList = (ArrayList<Lov>) request.getSession().getAttribute("USER-ROLE-LIST");
		ArrayList<Lov> genderList = (ArrayList<Lov>) request.getSession().getAttribute("GENDER-LIST");
		ArrayList<Lov> userStatusList = (ArrayList<Lov>) request.getSession().getAttribute("USER-STATUS-LIST");
		
		
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
        
        
 	        <form action="bid" name="frm" id="frm" method="post">
		       <input type="hidden" name="manager" id="manager" value="user-manager"/>
		       <input type="hidden" name="action" id="action" value=""/>
		       <input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
		       <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
		       <input type="hidden" name="userId_wip" id="userId_wip" value=""/>
		       
        <section class="page-section color" style="padding:15px;">
        
            <div class="container">
                <div class="row" >

                    <div class="col-sm-12">
                        <h3 class="block-title"><span>Users  <label>create </label></span></h3>
                        <div id="msgDiv"></div>
 					</div>
 					
					<div class="col-sm-4">
		              <div class="form-group">
		                <label><b>First Name : </b></label>
						<input type="text" class="form-control" placeholder="FIRST NAME" value="" id="firstName" name="firstName" onchange="return trim(this)" autocomplete="off" maxlength="50" />
		              </div>
		              <div class="form-group">
		                <label><b>Last Name : </b></label>
						<input type="text" class="form-control" placeholder="LAST NAME" value=""  id="lastName" name="lastName" onchange="return trim(this)" autocomplete="off" maxlength="50"/>
		              </div>
		              <div class="form-group">
		                <label><b>Birth Date : </b></label>
						<input type="text" class="form-control" placeholder="BIRTH DATE" value=""  id="birthDate" name="birthDate" onchange="return trim(this)" autocomplete="off" readonly="readonly"/>
		              </div>
						<div class="form-group">
							<label><b>Gender : </b></label>
								<select class="form-control" id="gender" name="gender">
									<option value="0">GENDER</option>
								<%for(Lov l :genderList){%> 
									<option value="<%=l.getId()%>"><%=l.getValue()%></option>
								<%}%> 
								</select>
						</div>
		              <div class="form-group">
		                <label><b>Company : </b></label>
						<input type="text" class="form-control" placeholder="COMPANY" value=""  id="company" name="company" onchange="return trim(this)" autocomplete="off" maxlength="50"/>
		              </div>
						
						<div class="form-group">
							<label><b>Email Address : </b></label>
							<input type="email" class="form-control" placeholder="EMAIL ADDRESS" value=""  id="emailAddress" name="emailAddress" onchange="return trim(this)" autocomplete="off" maxlength="50"/>
						</div>
						<div class="form-group">
							<label><b>Password : </b></label>
							<input type="password" class="form-control" placeholder="PASSWORD" value=""  id="passWord" name="passWord" onchange="return trim(this)" autocomplete="off" maxlength="15"/>
						</div>
						

						
					</div>
					
					
				
				<div class="col-sm-4">
				
		            <div class="form-group">
		              <label><b>Status : </b></label>
						<select class="userStatusSel form-control" id="userStatus" name="userStatus">
								<option value="0">STATUS</option>
							<%for(Lov l :userStatusList){%> 
								<option value="<%=l.getId()%>"><%=l.getValue()%></option>
							<%}%> 
						</select>
		            </div>
				
					<div class="form-group">			
		             <label><b>Active : </b></label>
						<select class="form-control" id="active" name="active"><option value="">ACTIVE</option><option value="1">Yes</option><option value="0">No</option></select>
		            </div>
		            <div class="form-group">
		              <label><b>Role : </b></label>
						<select class="form-control" id="role" name="role">
								<option value="0">ROLE</option>
							<%for(Lov l :userRoleList){%> 
								<option value="<%=l.getId()%>"><%=l.getValue()%></option>
							<%}%> 
						</select>
		            </div>
					<div class="form-group">			
		             <label><b>News Letter : </b></label>
						<select class="form-control" id="newsLetter" name="newsLetter"><option value="">NEWS LETTER</option><option value="1">Yes</option><option value="0">No</option></select>
		            </div>
					<div class="form-group">			
		             <label><b>News Letter Registration Date : </b></label>
						<input type="text" class="form-control" placeholder="NEWS LETTER REGISTRATION DATE" id="newsLetterRegistrationDate" name="newsLetterRegistrationDate" readonly="readonly"/>
		            </div>
					<div class="form-group">			
		             <label><b>Registration Date : </b></label>
						<input type="text" class="form-control" placeholder="REGISTRATION DATE" id="registrationDate" name="registrationDate" readonly="readonly"/>
		            </div>
		            <div class="form-group">
		             <label><b>Password Change Date : </b></label>
						<input type="text" class="form-control" placeholder="PASSWORD CHANGE DATE" id="passwordChangeDate" name="passwordChangeDate" readonly="readonly"/>
		            </div>
		            

				</div>
		
		
		
				<div class="col-sm-4">
				

				
		              <div class="form-group">
		                <label><b>Mobile No : </b></label>
						<input type="text" class="form-control" placeholder="MOBILE NO" value=""  id="mobileNo1" name="mobileNo1" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              <div class="form-group">
		                <label><b>Alternate Mobile No : </b></label>
						<input type="text" class="form-control" placeholder="ALTERNATE MOBILE NO" value=""  id="mobileNo2" name="mobileNo2" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              <div class="form-group">
		                <label><b>Landline No : </b></label>
						<input type="text" class="form-control" placeholder="LANDLINE NO" value=""  id="landLineNo" name="landLineNo" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              
		              <div class="form-group">
		                <label><b>Bidder No : </b></label>
						<input type="text" class="form-control" placeholder="BIDDER NO" value=""  id="bidderNo" name="bidderNo" autocomplete="off" onchange="return trim(this)" maxlength="11"/>
		              </div>
		              
		              <div class="form-group">
		                <label><b>Reserve Bidder No : </b></label>
						<input type="text" class="form-control" placeholder="RESERVE BIDDER NO" value=""  id="reserveBidderNo" name="reserveBidderNo" onchange="return trim(this)" autocomplete="off" maxlength="11"/>
		              </div>
		              
						<div class="form-group">
							<label><b>Verification Email Key : </b></label>
							<input type="text" class="form-control" placeholder="VERIFICATION EMAIL KEY" value=""  id="verificationEmailKey" name="verificationEmailKey" onchange="return trim(this)" autocomplete="off" maxlength="15"/>
						</div>
		            <div class="form-group">
		             <label><b>Show Change Password Next Login : </b></label>
		             	<select class="form-control" id="showChangePasswordNextLogin" name="showChangePasswordNextLogin"><option value="">SHOW CHANGE PASSWORD NEXT LOGIN</option><option value="1">Yes</option><option value="0">No</option></select>
		            </div>
				</div>

				<div class="col-sm-12">
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="saveUser()">Save</a>
					</div>
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="clearUser()">Clear</a>
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
        </form>
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



function onLoadPage(){
	//document.frm.userId.focus();	
/*	
//	if(document.frm.userId.value!=""){
//		< /%if(userId!=null){ %>
//			document.frm.pw.focus();
//		< /%}%>
//	}
*/
}

function validateSave(){
	var isSave = true;
	
	var firstName = document.getElementById("firstName").value;
	var lastName = document.getElementById("lastName").value;
	var birthDate = document.getElementById("birthDate").value;
	var gender = document.getElementById("gender").value;
	var company = document.getElementById("company").value;
	var emailAddress = document.getElementById("emailAddress").value;
	var passWord = document.getElementById("passWord").value;
	var userStatus = document.getElementById("userStatus").value; 
	var active = document.getElementById("active").value; 
	var role = document.getElementById("role").value; 
	var newsLetter = document.getElementById("newsLetter").value;
	var newsLetterRegistrationDate = document.getElementById("newsLetterRegistrationDate").value;
	var registrationDate = document.getElementById("registrationDate").value; 
	var passwordChangeDate = document.getElementById("passwordChangeDate").value;
	var mobileNo1 = document.getElementById("mobileNo1").value;
	var mobileNo2 = document.getElementById("mobileNo2").value;
	var landLineNo = document.getElementById("landLineNo").value;
	var bidderNo = document.getElementById("bidderNo").value;
	var reserveBidderNo = document.getElementById("reserveBidderNo").value;
	var verificationEmailKey = document.getElementById("verificationEmailKey").value;
	var showChangePasswordNextLogin = document.getElementById("showChangePasswordNextLogin").value;
	
	var msgInfo = "";
	var msgbgcol = "";
	
	if(firstName==""){
		msgInfo = "FIRST NAME IS REQUIRED.";
		msgbgcol = "red";
		document.frm.firstName.focus();
		isSave = false;
	}else if(lastName==""){
		msgInfo = "LAST NAME IS REQUIRED.";
		msgbgcol = "red";
		document.frm.lastName.focus();
		isSave = false;
	}else if(birthDate==""){
		msgInfo = "BIRTH DATE IS REQUIRED.";
		msgbgcol = "red";
		document.frm.birthDate.focus();
		isSave = false;
	}else if(gender=="0"){
		msgInfo = "GENDER IS REQUIRED.";
		msgbgcol = "red";
		document.frm.gender.focus();
		isSave = false;
	}else if(company==""){
		msgInfo = "COMPANY IS REQUIRED.";
		msgbgcol = "red";
		document.frm.company.focus();
		isSave = false;
	}else if(emailAddress==""){
		msgInfo = "EMAIL ADDRESS IS REQUIRED.";
		msgbgcol = "red";
		document.frm.emailAddress.focus();
		isSave = false;
	}else if(passWord==""){
		msgInfo = "PASSWORD IS REQUIRED.";
		msgbgcol = "red";
		document.frm.passWord.focus();
		isSave = false;
	}else if(userStatus=="0"){
		msgInfo = "STATUS IS REQUIRED.";
		msgbgcol = "red";
		document.frm.userStatus.focus();
		isSave = false;
	}else if(active==""){
		msgInfo = "ACTIVE IS REQUIRED.";
		msgbgcol = "red";
		document.frm.active.focus();
		isSave = false;
	}else if(role=="0"){
		msgInfo = "ROLE IS REQUIRED.";
		msgbgcol = "red";
		document.frm.role.focus();
		isSave = false;
	}else if(newsLetter==""){
		msgInfo = "NEWS LETTER IS REQUIRED.";
		msgbgcol = "red";
		document.frm.newsLetter.focus();
		isSave = false;
	}else if(mobileNo1==""){
		msgInfo = "MOBILE NO IS REQUIRED.";
		msgbgcol = "red";
		document.frm.mobileNo1.focus();
		isSave = false;
	}

	if(isSave==false){
		var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
		msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
		msgBoxValue = msgBoxValue + '</div>';
		document.getElementById("msgDiv").innerHTML=msgBoxValue;
	}
	return isSave;
}

function clearUser(){
	document.getElementById("firstName").value="";
	document.getElementById("lastName").value="";
	document.getElementById("birthDate").value="";
	document.getElementById("gender").value=0;
	document.getElementById("company").value="";
	document.getElementById("emailAddress").value="";
	document.getElementById("passWord").value="";
	document.getElementById("userStatus").value=0;
	document.getElementById("active").value="";
	document.getElementById("role").value=0;
	document.getElementById("newsLetter").value="";
	document.getElementById("newsLetterRegistrationDate").value="";
	document.getElementById("registrationDate").value="";
	document.getElementById("passwordChangeDate").value="";
	document.getElementById("mobileNo1").value="";
	document.getElementById("mobileNo2").value="";
	document.getElementById("landLineNo").value="";
	document.getElementById("bidderNo").value="";
	document.getElementById("reserveBidderNo").value="";
	document.getElementById("verificationEmailKey").value="";
	document.getElementById("showChangePasswordNextLogin").value="";

}

function saveUser(){
	if(validateSave()){
		document.frm.action.value="saveUser";
		document.frm.submit();
	}
	setTimeout(function(){document.getElementById("msgDiv").innerHTML="";},5000);
	//	alert('s');
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


<script>
/*
$(function () {
    $("#example1").DataTable({
      	"order": [[ 4, "desc" ]],
      	"lengthMenu": [[5, 25, 50, 100], [5, 25, 50, 100]]
    });
  });
 */ 
 $('#birthDate').datetimepicker({
		formatTime:'H:i',
		formatDate:'d.m.Y',
		//defaultDate:'8.12.1986', // it's my birthday
		defaultDate:'+03.01.1970', // it's my birthday
		defaultTime:'12:00',
		timepickerScrollbar:false
	});
$('#newsLetterRegistrationDate').datetimepicker({
	formatTime:'H:i',
	formatDate:'d.m.Y',
	//defaultDate:'8.12.1986', // it's my birthday
	defaultDate:'+03.01.1970', // it's my birthday
	defaultTime:'12:00',
	timepickerScrollbar:false
});
 
$('#registrationDate').datetimepicker({
	formatTime:'H:i',
	formatDate:'d.m.Y',
	//defaultDate:'8.12.1986', // it's my birthday
	defaultDate:'+03.01.1970', // it's my birthday
	defaultTime:'12:00',
	timepickerScrollbar:false
});
 
$('#passwordChangeDate').datetimepicker({
	formatTime:'H:i',
	formatDate:'d.m.Y',
	//defaultDate:'8.12.1986', // it's my birthday
	defaultDate:'+03.01.1970', // it's my birthday
	defaultTime:'12:00',
	timepickerScrollbar:false
});


$(document).ready(function() {
 	    $("#mobileNo1").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });


 	    $("#mobileNo2").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });


 	    $("#landLineNo").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });


 	    $("#bidderNo").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });
 	    
 	    $("#reserveBidderNo").keydown(function (e) {
 	        // Allow: backspace, delete, tab, escape, enter and .
 	        if ($.inArray(e.keyCode, [46, 8, 9, 27, 13, 110, 190]) !== -1 ||
 	             // Allow: Ctrl+A
 	            (e.keyCode == 65 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+C
 	            (e.keyCode == 67 && e.ctrlKey === true) ||
 	             // Allow: Ctrl+X
 	            (e.keyCode == 88 && e.ctrlKey === true) ||
 	             // Allow: home, end, left, right
 	            (e.keyCode >= 35 && e.keyCode <= 39)) {
 	                 // let it happen, don't do anything
 	                 return;
 	        }
 	        // Ensure that it is a number and stop the keypress
 	        if ((e.shiftKey || (e.keyCode < 48 || e.keyCode > 57)) && (e.keyCode < 96 || e.keyCode > 105)) {
 	            e.preventDefault();
 	        }
 	    });
 	    
 	  
 	});	
 
</script>
</body>
</html>