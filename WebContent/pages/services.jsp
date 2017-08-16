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
									<h2 id="services">Services</h2>
									<hr>
									
									<p>HMR Auction provides comprehensive auction, valuation, and logistics services. 
									As the Philippines' largest full-service auction and valuation company, HMR Auction 
									has helped many businesses and individuals maximize the return on their redundant 
									assets. The company is committed to addressing their clients' needs, tailor-fitting 
									services to provide the following:</p>
									
									<p><h4>Asset Disposal Solution: Valuation and Assessment</h4></p>
									<p>We at HMR Auctions strongly believe that the auction sets the standard for the 
									equitable appraisal of assets. Buyers participate in governing how the price of 
									each property available are set and as such, there is a wealth of valuable 
									information at hand that gives a more accurate picture of what the assets are worth. 
									We uphold this standard in how we conduct and market our auctions to regular and 
									potential buyers.</p>
									
									<p>For over two decades, HMR Auctions has been providing our clients with efficient 
									and accurate valuations of the highest quality. We provide valuations for small, 
									medium and large corporations, restaurants, vehicles, from small to large collections 
									as well as industrial machinery. With multiple sales channels, HMR will advise you 
									of the best option for all your asset disposal requirements and their realizable 
									market value.</p><p>We have serviced various companies from many industries:</p>
									
									<ul>
										<li>Hotels and Restaurants</li>
										<li>Banks and Financial Institutions</li>
										<li>Electronics and Semiconductor Companies</li>
										<li>Educational Institutions</li>
										<li>Appliance Manufacturers and Dealers</li>
										<li>Retail outlets</li>
										<li>General Manufacturing</li>
										<li>Construction</li>
										<li>Fleet</li>
										<li>Agriculture</li>
									</ul>
									
									<p><h4>Removal, recovery, and logistics services</h4></p>
									<p>At HMR Auctions we train our personnel to handle goods efficiently and safely, 
									maximizing convenience for our asset consignors. From our logistics services, 
									we can provide trucking for pick-up and delivery. Our professional materials 
									handling ensures your items are retained in the same condition throughout the 
									entire auction process.</p>
									
									<p><h4>Sale by bidding or auction</h4></p>
									<p>Selling through an auction or sealed bidding is the quickest way to realize a 
									return on your assets, providing the auction company is well established with an 
									extensive database of active bidders. With approximately 20 auctions conducted 
									every month, HMR Auctions assures you the maximum exposure of your assets in the 
									shortest period of time.</p>
									
									<p><h4>Comprehensive documentation and reports</h4></p>
									<p>Our relationship with both bidders and consignors is built on trust. 
									We provide accurate documentation and reports after the conclusion of 
									each auction.</p>
									
									<p><h4>Consignment</h4></p>
									<p>Consigning your assets to us will expose them to the ever-increasing number 
									of bidders on our extensive data base. HMR Auctions is supported by expert 
									Business Development Officers, all of whom are well-versed in auctioneering, 
									bringing a new dimension of service to the client. Whether clients have a 
									few items or an entire building full, HMR Auctions is ready to provide their 
									expertise, personnel, equipment, and facilities: a "one-stop" solution.</p>
									
									<p>Through experience, efficiency, and expanded network of local and international 
									business partners, HMR Auctions maintains a prominent position in the Philippine 
									auction industry.</p>
									
									<p>Contact us so we can visit you on site.</p>
									
									
									
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
