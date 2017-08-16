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
									<h2 id="basicauctionfaqs">Basic Auction FAQs</h2>
									<hr>
									
									<p><h4>What is an auction?</h4></p>
									<p style="padding-left: 30px;">An Auction is a service that offers buying or selling 
									items through bidding, live auction or online auction. The highest bid wins the item.</p>
									
									<p><h4>What is the benefit of buying items through an auction?</h4></p>
									<p style="padding-left: 30px;">You determine the price of the item you purchase, 
									unlike retail where there is a fixed price tag for all merchandise.</p><p>
									
									<h4>How can I participate in an auction?</h4></p>
									<p style="padding-left: 30px;">HMR Auctions conducts weekly auctions at three 
									auction centres. Click here to see the exact locations. On-site auctions and 
									sealed biddings are announced by email, mobile and telemarketing communication 
									channels and published in recognized national newspapers. Register and pay the 
									refundable bid deposit. You will then collect your bid number which should be 
									raised when you agree with the bid request of the auctioneer. The auctioneer 
									will call your number out if you are the first bid number that he spots. When 
									there are no more bids the last (highest) bidder is declared the winner by the 
									auctioneer.</p>
									
									<p><h4>Why is it best to sell items through auction?</h4></p>
									<p style="padding-left: 30px;">Auction is the quickest way to turn possessions 
									or inventory into cash. Sellers are also assured that they will get the fair market 
									value for their goods. Auction also provides an aggressive, advanced marketing 
									program that increases buyers' interest.</p><p>
									
									<h4>How can I find out more about when HMR Auctions will be held and what 
									items they will be auctioning or have for bids?</h4></p>
									<p style="padding-left: 30px;">Simply sign up to receive our newsletters. 
									Choose the category of items you are interested in and we will be sure to inform 
									you of when our Auctions are and the items we will have up for bids.</p>
									
									<p><h4>Can I inspect the goods before I join the auction?</h4></p>
									<p style="padding-left: 30px;">Yes, all bidders who wish to participate in any 
									HMR auction or bidding are encouraged to inspect the items on the 
									advertised dates for viewing or the day before the auction.</p>
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
