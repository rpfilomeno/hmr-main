<!DOCTYPE html>
<%@ page import="java.text.SimpleDateFormat" %>
<%

	String COMPANY_NAME = (String)request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";
	String COMPANY_NAME_ACRONYM = (String)request.getSession().getAttribute("COMPANY_NAME_ACRONYM");

	String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
	String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";
	  
	String firstName = (String)request.getSession().getAttribute("firstName");
	String lastName = (String)request.getSession().getAttribute("lastName");
	String fullName = (String)request.getSession().getAttribute("fullName");
	String userId = (String)request.getSession().getAttribute("userId");
	
	
	// IDs
	Integer user_id = request.getSession().getAttribute("user-id")!=null ? (Integer)request.getSession().getAttribute("user-id") : null;
	Integer user_role_id = request.getSession().getAttribute("user-role-id")!=null ? (Integer)request.getSession().getAttribute("user-role-id") : 0;

	SimpleDateFormat sdf = new SimpleDateFormat("MMM dd yyyy  HH:mm");
	
%>
<!--[if lt IE 7]>      <html class="no-js lt-ie9 lt-ie8 lt-ie7"> <![endif]-->
<!--[if IE 7]>         <html class="no-js lt-ie9 lt-ie8"> <![endif]-->
<!--[if IE 8]>         <html class="no-js lt-ie9"> <![endif]-->
<!--[if gt IE 8]><!--> <html class="no-js"> <!--<![endif]-->
    <head>
        <jsp:include page="../includes/header-meta.jsp"></jsp:include>
                
    </head>
    <body data-is-mobile="" id="c" >
    
    <input type="hidden" id="base_url" value="">
        <!--[if lt IE 7]>
            <p class="browsehappy">You are using an <h4>outdated</h4> browser. Please <a href="http://browsehappy.com/">upgrade your browser</a> to improve your experience.</p>
        <![endif]-->

<jsp:include page="../includes/header.jsp"></jsp:include>

	
<div id="ms--main--body">
	

	
	<section id="hmr-main-container">
		<div class="container">
			<div class="row gutter-10">
				<div data-alerts="alerts" data-titles='{"warning": "<em>Warning!</em>", "error": "<em>Error!</em>"}' data-ids="myid" data-fade="3000"></div>
				
					<div class="col-md-4 col-lg-3">
					<jsp:include page="../includes/side-widget.jsp"></jsp:include>
					</div>
					
					
					<div class="col-md-8 col-lg-9">
						<jsp:include page="../includes/index-slider.jsp"></jsp:include>
						<div class="clearfix"></div>
						
						<section id="hmr-main-body">
							<div class="row section-row">
								<div class="col-md-12">
									<h2 id="onlineauctionfaqs">Online Auction FAQs</h2>
									<hr>
									
									<p><h4>Contact HMR Online Auction</h4></p>
									<p style="padding-left: 30px;">To contact our Online Auction Administrator 
									you can call +632 367 3034.</p>
									
									<p><h4>How will I know if I have won the bid?</h4></p>
									<p style="padding-left: 30px;">Once the Online Bidding has finished 
									the successful bidder will be informed by e-mail. HMR Online Auction 
									Administrator will contact you by telephone shortly after the email is sent.</p>
									
									<p><h4>How do I pay?</h4></p>
									<p style="padding-left: 30px;">Invoices will be sent once the auction has closed 
									to the email address provided upon registration. Payments can be made via bank 
									transfer or at any one of our HMR Auction centres. Account details will be 
									provided in the invoice sent to your email. All payments should be made to 
									reach us within 3 working days from the day auction closes. Items won will only 
									be released once payment has been cleared and confirmed.</p>
									
									<p><h4>Does HMR provide shipping services?</h4></p>
									<p style="padding-left: 30px;">HMR Auctions do not facilitate the shipment of items. 
									Pick up of goods is the buyer's responsibility however, our affiliate, HMR Logistics 
									is available to help you organize trucking or shipping to your requested destination. 
									The payment transactions made with HMR Logistics will be separate from HMR Auctions. 
									Won items must be paid first with HMR Auction before HMR Logistics or any other 
									shipping company will be authorized to collect the item/s.</p>
									
									<p><h4>Can I see the physical item before I bid online?</h4></p>
									<p style="padding-left: 30px;">Yes, the item can be viewed but only upon request. 
									You can contact the HMR Auction Administrator or the Business Development Officer 
									listed on the item's catalogue description.</p>
									
									<p><h4>Can I cancel a bid made online?</h4></p>
									<p style="padding-left: 30px;">Bids cannot be cancelled once made online. 
									A confirmation pop-up will appear once you place your bid. As the bidder you must 
									be sure that you want to place the bid.</p>
								</div>
							</div>
						</section>
					</div>
				</div>
			</div>
	</section>
	<div class="clearfix top50"></div>

</div>

<jsp:include page="../includes/footer.jsp"></jsp:include>
<jsp:include page="../includes/footer-meta.jsp"></jsp:include>


<script>
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
</body>
</html>
