<!DOCTYPE html>
<%@ page import="hmr.com.bean.User"
		 import="hmr.com.bean.Auction"
		 import="hmr.com.bean.AuctionUser"
		 import="hmr.com.bean.Lov"
		 import="hmr.com.bean.Lot"
		 import="hmr.com.bean.Item"
		 import="hmr.com.bean.Image"
		 import="hmr.com.manager.LotManager"
		 
		 import="java.util.HashMap" 
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
		System.out.println("asdf");
	
		String COMPANY_NAME = request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";

		String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
		String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
		  
		  
		String firstName = request.getAttribute("firstName")!=null ? (String)request.getAttribute("firstName") : "";
		String lastName = request.getAttribute("lastName")!=null ? (String)request.getAttribute("lastName") : "";
		String userId = request.getAttribute("userId")!=null ? (String)request.getAttribute("userId") : (String)request.getSession().getAttribute("userId");
		BigDecimal mobileNo = request.getAttribute("mobileNo")!=null ? (BigDecimal)request.getAttribute("mobileNo") : null;
		  
		  
		//IDS
		Integer user_id = request.getAttribute("user-id")!=null ? (Integer)request.getAttribute("user-id") : (Integer)request.getSession().getAttribute("user-id");
		  
		Auction auction = request.getAttribute("auction")!=null ? (Auction)request.getAttribute("auction") : (Auction)request.getSession().getAttribute("auction");
		HashMap<Integer,User> coordinatorUserHM = (HashMap<Integer,User>)request.getSession().getAttribute("COORDINATOR-USER-HM");
		


		ArrayList<Lov> userRoleList = (ArrayList<Lov>) request.getSession().getAttribute("USER-ROLE-LIST");
		ArrayList<Lov> genderList = (ArrayList<Lov>) request.getSession().getAttribute("GENDER-LIST");
		ArrayList<Lov> userStatusList = (ArrayList<Lov>) request.getSession().getAttribute("USER-STATUS-LIST");
		
		
		ArrayList<User> userCoordinatorList = (ArrayList<User>) request.getAttribute("userCoordinatorList");
		HashMap<Integer,Lov> catLev1LovHM =  (HashMap<Integer,Lov>)request.getSession().getAttribute("ITEM-CATEGORY-LEVEL-1-HM");
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm");
		
		String action = request.getAttribute("action")!=null ? (String)request.getAttribute("action") : "";
		String action_id = request.getAttribute("action_id")!=null ? (String)request.getAttribute("action_id") : "";
		List<Image> images = request.getAttribute("images")!=null ? (ArrayList<Image>)request.getAttribute("images") : null;
		String t = "a";
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
    <link href="assets/plugins/jquery-ui/themes/smoothness/jquery-ui.min.css" rel="stylesheet">

    <!-- Head Libs -->
    
    <script src="assets/plugins/modernizr.custom.js"></script>
	
	<!-- CK Editor -->
	<!-- 
	<script src="cdn.ckeditor.com/standard-4-4-3/ckeditor.js"></script> -->
	<!-- Bootstrap WYSIHTML5 -->
	    <!-- bootstrap wysihtml5 - text editor -->
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
    
    <link href="assets/plugins/dropzone.css" rel="stylesheet">
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
        <section class="page-section color" style="padding:15px;">
            <div class="container">
            	<div class="row" >
						<%
							if(action.equals("doAuctionImageUpload")) {
								t="a";
						%>
						<div class="col-sm-10">
							<h3 class="block-title">
								<span>Auction <label>Upload Images</label></span>
							</h3>
						</div>
						<div class="col-sm-10">
							<label><b>Auction Id : </b><%=action_id%></label>
						</div>
						<%
							} else if (action.equals("doLotImageUpload")) {
								t="l";
						%>
						<div class="col-sm-10">
							<h3 class="block-title">
								<span>Lot <label>Upload Images</label></span>
							</h3>
						</div>
						<div class="col-sm-10">
							<label><b>Lot Id : </b><%=action_id%></label>
						</div>
						<%
							} else if (action.equals("doItemImageUpload")) {
								t="i";
						%>
						<div class="col-sm-10">
							<h3 class="block-title">
								<span>Item <label>Upload Images</label></span>
							</h3>
						</div>
						<div class="col-sm-10">
							<label><b>Item Id : </b><%=action_id%></label>
						</div>
						<%
							}
						%>
						<div class="col-md-12">
	                	<div class="tabs-wrapper content-tabs">
	                    	<ul class="nav nav-tabs">
	                    		<li class="active"><a href="#image-list" data-toggle="tab">Gallery</a></li>
	                        	<li><a href="#image-upload" data-toggle="tab">Upload</a></li>
                            </ul>
                            <div class="tab-content">
                            	<div class="tab-pane fade in active" id="image-list">
									<section class="page-section">
								    	<div class="container">
								        	<div class="row">
								        		<div class="col-sm-10">
								        			<% for(Image im : images){ %>
								        			<div class="col-sm-4">
								        				<div class="media" style="height: 210px;">
								        					<%
															if(action.equals("doAuctionImageUpload")) {
															%>
															<a class="media-link" href="#" onclick="deleteImage(<%=im.getId().toString()%>,'auctionImageDelete',<%=action_id%>)">
																<img  class="media-object" style="height: 200px; size: 200px;" src="image?id=<%=im.getId().toString()%>&t=t" alt="">
							                                </a>
															<%
															}else if(action.equals("doLotImageUpload")) {
															%>
															<a class="media-link" href="#" onclick="deleteImage(<%=im.getId().toString()%>,'lotImageDelete',<%=action_id%>)">
																<img  class="media-object" style="height: 200px; size: 200px;" src="image?id=<%=im.getId().toString()%>&t=t" alt="">
							                                </a>
															<%
															}else if(action.equals("doItemImageUpload")) {
															%>
															<a class="media-link" href="#" onclick="deleteImage(<%=im.getId().toString()%>,'itemImageDelete',<%=action_id%>)">
																<img  class="media-object" style="height: 200px; size: 200px;" src="image?id=<%=im.getId().toString()%>&t=t" alt="">
							                                </a>
															<% } %>

							                                <div class="media-body">
																<h4 class="media-heading"><a href="#" style="font-size: 14px; font-weight: bold;">Image #<%=im.getId().toString() %></a></h4>
							                                	
							                                	
							                                </div>
							                            </div>
								        			</div>
								        			<% } %>
								        		</div>
								        	</div>
								        </div>
								    </section>
								</div>
	                        	<div class="tab-pane fade" id="image-upload">
									<section class="page-section">
								    	<div class="container">
								        	<div class="row">
								            	
								 				<div class="col-sm-12 dz-max-files-reached"></div>
								                <div id="image-uploader" class="col-sm-12 dropzone" style="height:350px;"></div>
								        	</div>
								    	</div>
									</section>
								</div>
                            </div>
						</div>
						<% if(action.equals("doAuctionImageUpload")) {	%>
						<div class="col-sm-2">
					     	<a class="btn btn-theme btn-block " href="#" onclick="backToAuction()">Auction</a>
						</div>
						<% }else if(action.equals("doLotImageUpload")) { %>
						<div class="col-sm-2">
					     	<a class="btn btn-theme btn-block " href="#" onclick="backToLot()">Lot</a>
						</div>
						<% }else if(action.equals("doItemImageUpload")) { %>
						<div class="col-sm-2">
					     	<a class="btn btn-theme btn-block " href="#" onclick="backToItem()">Item</a>
						</div>
						<% } %>
					</div>
                </div>
            </div>
        </section>
    </div>
    <!-- /CONTENT AREA -->

    <!-- FOOTER -->
	<jsp:include page="hmr-footer.jsp" />
    <!-- /FOOTER -->

    <div id="to-top" class="to-top" style="background-color: #93bcff"><i class="fa fa-angle-up"></i></div>

</div>
<!-- /WRAPPER -->

<form action="bid" name="frm" id="frm" method="post">
       <input type="hidden" name="manager" id="manager" value="image-manager"/>
       <input type="hidden" name="action" id="action" value="auctionImageDelete"/>
       <input type="hidden" name="userId" id="userId" value="<%=userId%>"/>
       <input type="hidden" name="user-id" id="user-id" value="<%=user_id%>"/>
       <input type="hidden" name="action_id" id="action_id" value=""/>
       <input type="hidden" name="wip_id" id="wip_id" value=""/>
       <!-- For reloading -->
       <input type="hidden" name="auction_id" id="auction_id" value=""/>
       <input type="hidden" name="lot_id" id="lot_id" value=""/>
       <input type="hidden" name="itemId_wip" id="itemId_wip" value=""/>
       <!--  For back button -->
       <input type="hidden" name="auctionId_wip" id="auctionId_wip" value=""/>
       <input type="hidden" name="lotId_wip" id="lotId_wip" value=""/>
       <input type="hidden" name="itemId_wip" id="itemId_wip" value=""/>
</form>


<!-- JS Global -->
<script src="assets/plugins/jquery/jquery-2.0.0.min.js"></script>
<script src="assets/plugins/jquery-ui/jquery-ui-1.11.1.min.js"></script>
<script src="assets/plugins/bootstrap/js/bootstrap.min.js"></script>
<script src="assets/plugins/bootstrap-select/js/bootstrap-select.min.js"></script>
<script src="assets/plugins/superfish/js/superfish.min.js"></script>
<script src="assets/plugins/prettyphoto/js/jquery.prettyPhoto.js"></script>
<script src="assets/plugins/owl-carousel2/owl.carousel.min.js"></script>
<script src="assets/plugins/jquery.sticky.min.js"></script>
<script src="assets/plugins/jquery.easing.min.js"></script>
<script src="assets/plugins/jquery.smoothscroll.min.js"></script>
<script src="assets/plugins/smooth-scrollbar.min.js"></script>

<!-- JS Local -->
<script type="text/javascript">
<%if(msgInfo!=null){%>
var msgInfo = "<%=msgInfo%>";
var msgbgcol = "<%=msgbgcol%>";
var msgBoxValue = '<div class=\"message-box\" style=\"font-size: 12px; background-color: '+msgbgcol+'\">';
msgBoxValue = msgBoxValue + '<h2 style=\"font-size: 12px; background-color: '+msgbgcol+';\">'+msgInfo+'</h2>';
msgBoxValue = msgBoxValue + '</div>';
document.getElementById("msgDiv").innerHTML=msgBoxValue;
setTimeout(function(){document.getElementById("msgDiv").innerHTML="";},5000);
<%}%>
</script>
<!-- /JS Local -->

<!-- JS Page Level -->
<script src="assets/plugins/dropzone.js"></script>
<script src="assets/js/theme.js"></script>

<!--[if (gte IE 9)|!(IE)]><!-->
<script src="assets/plugins/jquery.cookie.js"></script>
<!--  <script src="assets/js/theme-config.js"></script>  -->
<!--<![endif]-->


<script>

function backToAuction(){
	$('#frm input[name="manager"]').val("auction-manager");
	$('#frm input[name="action"]').val("viewAuction");
	$('#frm input[name="auctionId_wip"]').val("<%=action_id%>");
	$( "#frm" ).submit();
}

function backToLot(){

	$('#frm input[name="manager"]').val("lot-manager");
	$('#frm input[name="action"]').val("viewLot");
	$('#frm input[name="lotId_wip"]').val("<%=action_id%>");
	$( "#frm" ).submit();
}

function backToItem(){
	$('#frm input[name="manager"]').val("item-manager");
	$('#frm input[name="action"]').val("viewItem");
	$('#frm input[name="itemId_wip"]').val("<%=action_id%>");
	$( "#frm" ).submit();
}



function deleteImage(image_id,action,  wip_id){
	$('#frm input[name="action_id"]').val(image_id);
	$('#frm input[name="action"]').val(action);
	$('#frm input[name="wip_id"]').val(wip_id);
	
	
	$('<div id="dialog-confirm"></div>').dialog({
		resizable: false,
	    height: "auto",
	    width: 400,
	    modal: true,
	    title: "Confirmation",
	    closeOnEscape: false,
        open: function (event, ui) {
        	var dialog_html = '<p><span class="ui-icon ui-icon-alert" style="float:left; margin:12px 12px 20px 0;"></span>This will delete the Image #'+image_id+'. Are you sure?</p>';       	
        	$(".ui-dialog-titlebar-close", ui.dialog | ui).hide();
            $(this).html(dialog_html);
        },
        buttons: {
        	"Yes": function() {
  	          $( this ).dialog( "close" );
  	          $( "#frm" ).submit();
  	        },
            Cancel: function () {
                $(this).dialog("close");
            }
        },
		close: function() {
			$("#dialog-confirm").remove();
		}
    }); //end confirm dialog
}


 $(document).ready(function() {
	Dropzone.autoDiscover = false;
	var myDropzone = new Dropzone("#image-uploader", { 
		url: 'bid',
		acceptedFiles: 'image/*',
		resizeWidth: 400,
		resizeHeight: 400,
		resizeMethod: 'crop',
		maxFilesize: 2,
		init: function() {
	    	this.on("sending", function(file, xhr, formData){
	    		formData.append("manager", "image-manager");
	        	formData.append("action", "<%=action%>");
	        	formData.append("action_id", "<%=action_id%>");
	        	formData.append("user-id", "<%=user_id%>");
	        	formData.append("userId", "<%=userId%>");
	    	});
	    }
	});
 });
</script>

</body>
</html>