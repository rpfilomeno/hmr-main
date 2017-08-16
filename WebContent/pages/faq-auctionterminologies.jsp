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
									<h2 id="auctionterminologies">Auction Terminologies</h2>
									<hr>
									
									<p><h4>Buyer's Choice</h4></p>
									<p style="padding-left: 30px;">A method of giving the highest bidder the right 
									to choose similar succeeding lots, each at the same price as the initial lot.</p>
									
									<p><h4>Absentee Bid</h4></p><p style="padding-left: 30px;">A procedure that 
									allows a bidder to participate in an auction without being physically present 
									by completing a form and listing your maximum bid. You will only be charged the 
									highest live bid plus one more increment.</p>
									
									<p><h4>As is, where is</h4></p>
									<p style="padding-left: 30px;">A common term in auction that means items or goods 
									are without warranty in terms of working condition. Bidders are responsible for 
									examining and judging the items during viewing days prior to the auction.</p>
									
									<p><h4>Bid</h4></p>
									<p style="padding-left: 30px;">The price or offer a bidder is willing to pay for 
									desired items or goods.</p>
									
									<p><h4>Open Bid</h4></p>
									<p style="padding-left: 30px;">A minimum bid amount is not set and the starting price 
									is open for any offers.</p>
									
									<p><h4>Bid Increment</h4></p>
									<p style="padding-left: 30px;">The amount an item increases in price after each new bid. 
									The auctioneer sets the increment, which rises according to the present high bid value 
									of an item.</p>
									
									<p><h4>Buyer's Premium</h4></p>
									<p style="padding-left: 30px;">A percentage of the bid added to the amount that must be 
									paid by the bidder. The Buyer's Premium is payable at the same time as the winner's 
									amount and forms part of the total bill. The percentage of the Buyer's Premium will 
									be included in the product catalogue item description for Online Auctions.</p>
									
									<p><h4>Hammer Price</h4></p>
									<p style="padding-left: 30px;">Price established by the last bidder and acknowledged 
									by the auctioneer before dropping the hammer (gavel).</p>
									
									<p><h4>On-site Auction</h4></p>
									<p style="padding-left: 30px;">An auction conducted on the premises where the goods 
									are being sold. This is often a closed restaurant or business.</p>
									
									<p><h4>Reserve Price</h4></p>
									<p style="padding-left: 30px;">The minimum price that a seller is willing to accept 
									for an item to be sold at auction.</p>
									
									<p><h4>Sealed Bid</h4></p>
									<p style="padding-left: 30px;">Method of sale utilized where confidential bids are 
									submitted to be opened at a particular place and time.</p>
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
