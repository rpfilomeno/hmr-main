<!DOCTYPE html>
<%@ page import="hmr.com.bean.User"
		 import="hmr.com.bean.Lot"
		 import="hmr.com.bean.LotRange"
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
	System.out.println("PAGE lot-range.jsp");
	
		String COMPANY_NAME = request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";

		String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
		String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
		  
		  
		String firstName = request.getAttribute("firstName")!=null ? (String)request.getAttribute("firstName") : "";
		String lastName = request.getAttribute("lastName")!=null ? (String)request.getAttribute("lastName") : "";
		String userId = request.getAttribute("userId")!=null ? (String)request.getAttribute("userId") : (String)request.getSession().getAttribute("userId");
		//Integer mobileNo = request.getAttribute("mobileNo")!=null ? (Integer)request.getAttribute("mobileNo") : null;
		  
		  
		//IDS
		Integer user_id = request.getAttribute("user-id")!=null ? (Integer)request.getAttribute("user-id") : (Integer)request.getSession().getAttribute("user-id");
		BigDecimal lotId_wip = request.getAttribute("lotId_wip")!=null && !request.getAttribute("lotId_wip").equals("") ? new BigDecimal(request.getAttribute("lotId_wip").toString()) : new BigDecimal(request.getSession().getAttribute("lotId_wip").toString());

		System.out.println("userId : "+userId);
		System.out.println("user_id : "+user_id);
		System.out.println("lotId_wip : "+lotId_wip);
		
		/*
		List<User> uList =(ArrayList<User>) request.getAttribute("userList");
		System.out.println("ulList "+uList.size());
		*/
		
		ArrayList<Lov> userRoleList = (ArrayList<Lov>) request.getSession().getAttribute("USER-ROLE-LIST");
		ArrayList<Lov> genderList = (ArrayList<Lov>) request.getSession().getAttribute("GENDER-LIST");
		ArrayList<Lov> userStatusList = (ArrayList<Lov>) request.getSession().getAttribute("USER-STATUS-LIST");
		
		
		//ArrayList<User> userCoordinatorList = (ArrayList<User>) request.getSession().getAttribute("USER-COORDINATOR-LIST");
		ArrayList<User> userCoordinatorList = (ArrayList<User>) request.getAttribute("userCoordinatorList");
		
		//Lot lot = request.getAttribute("lot")!=null ? (Lot)request.getAttribute("lot") : (Lot)request.getSession().getAttribute("lot");
		LotRange lotRange = request.getAttribute("lotRange")!=null ? (LotRange)request.getAttribute("lotRange") : (LotRange)request.getSession().getAttribute("lotRange");
		
		
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
	
	<!-- CK Editor -->
	<!-- 
	<script src="cdn.ckeditor.com/standard-4-4-3/ckeditor.js"></script> -->
	<link rel="stylesheet" href="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css">

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
		       <input type="hidden" name="manager" id="manager" value="lot-range-manager"/>
		       <input type="hidden" name="action" id="action" value=""/>
		       <input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
		       <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
		       <input type="hidden" name="lotId_wip" id="lotId_wip" value="<%=lotId_wip%>"/>
		       <input type="hidden" name="lot_id" id="lot_id" value="<%=lotId_wip%>"/>
		       <input type="hidden" name="lotRangeId_wip" id="lotRangeId_wip" value="<%=lotRange.getId()%>"/>
		       
        <section class="page-section color" style="padding:15px;">
        
            <div class="container">
                <div class="row" >

                    <div class="col-sm-12">
                        <h3 class="block-title"><span>Lot Ranges  <label>details </label></span></h3>
                        <div id="msgDiv"></div>
 					</div>
 					
 					
 					

              
              <%-- 	
            <div class="col-md-12">
              <div class="box box-info">
                <div class="box-header">
                  <h3 class="box-title"><label><b>Terms and Conditions : </b></label></h3>
                </div><!-- /.box-header -->
                <div class="box-body pad">
                  
                    <textarea id="editor-2" name="editor-2" rows="10" cols="80">
                                            
                    </textarea>
                  
                </div>
              </div><!-- /.box -->
              </div>		
              


              
 					--%>
					<div class="col-sm-4">
		              <div class="form-group">
		                <label><b>Range Start : </b><%=lotRange.getRange_start() %></label>
						
		              </div>
		              <div class="form-group">
		                <label><b>Range End : </b><%=lotRange.getRange_end() %></label>
						
		              </div>
		              <div class="form-group">
		                <label><b>Increment Amount : </b><%=lotRange.getIncrement_amount() %></label>
						
		              </div>
		             </div>
		              
					
				
				<div class="col-sm-12">
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="createLotRange()">Create</a>
					</div>
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="updateLotRange()">Update</a>
					</div>
					<%if(lotId_wip!=null && lotId_wip.doubleValue() > 0){%>
					<div class="col-sm-2">
					     <a class="btn btn-theme btn-block " href="#" onclick="viewLot(<%=lotId_wip%>)">Lot</a>
					</div>
					<%} %>
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
function createLotRange(){
	document.frm.action.value="createLotRange";
	document.frm.submit();
}

function updateLotRange(){
	document.frm.action.value="updateLotRange";
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
}

function validateSave(){
	var isSave = true;
	

	var range_start = document.getElementById("range_start").value;
	var range_end = document.getElementById("range_end").value;
	var increment_amount = document.getElementById("increment_amount").value;

	var msgInfo = "";
	var msgbgcol = "";

	if(range_start==""){
		msgInfo = "RANGE START IS REQUIRED.";
		msgbgcol = "red";
		document.frm.range_start.focus();
		isSave = false;
	}else if(range_end==""){
		msgInfo = "RANGE END IS REQUIRED.";
		msgbgcol = "red";
		document.frm.range_end.focus();
		isSave = false;
	}else if(increment_amount==""){
		msgInfo = "INCREMENT AMOUNT IS REQUIRED.";
		msgbgcol = "red";
		document.frm.increment_amount.focus();
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

function clearLotRange(){
	document.getElementById("range_start").value="";
	document.getElementById("range_end").value="";
	document.getElementById("increment_amount").value="";
}

function saveLotRange(){
	if(validateSave()){
		document.frm.action.value="saveLotRange";
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

function viewLot(id){
	document.getElementById("action").value="viewLot";
	document.getElementById("manager").value="lot-manager";
	document.getElementById("lotId_wip").value=id;
	document.frm.submit();
}

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

<%--
    <!-- jQuery 2.1.4 -->
    <script src="plugins/jQuery/jQuery-2.1.4.min.js"></script>
    <!-- Bootstrap 3.3.5 -->
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <!-- FastClick -->
    <script src="plugins/fastclick/fastclick.min.js"></script>
    --%>
	<%--
	<script src="https://cdn.ckeditor.com/4.4.3/standard/ckeditor.js"></script>
	--%>
	<!-- Bootstrap WYSIHTML5 -->
	    <!-- bootstrap wysihtml5 - text editor -->
    <%--
    <script src="https://cdn.ckeditor.com/4.4.3/standard/ckeditor.js"></script>
	<script src="plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
--%>
<script>

var range_start = document.getElementById("range_start").value;
var range_end = document.getElementById("range_end").value;
var increment_amount = document.getElementById("increment_amount").value;

$(document).ready(function() {
 	    $("#range_start").keydown(function (e) {
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


 	    $("#range_end").keydown(function (e) {
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


 	    $("#increment_amount").keydown(function (e) {
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

 
</script>
</body>
</html>