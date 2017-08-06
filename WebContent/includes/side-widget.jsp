<%
String COMPANY_NAME = (String)request.getSession().getAttribute("COMPANY_NAME")!=null ? (String)request.getSession().getAttribute("COMPANY_NAME") :"HMR Auctions";
String COMPANY_NAME_ACRONYM = (String)request.getSession().getAttribute("COMPANY_NAME_ACRONYM");

String msgInfo = request.getAttribute("msgInfo")!=null ? (String)request.getAttribute("msgInfo") : null;
String msgbgcol = request.getAttribute("msgbgcol")!=null ? (String)request.getAttribute("msgbgcol") : "";

//TODO: messages displayed on page should be session based only to avoid XSS
if(msgInfo==null) {
	msgInfo = (String)request.getSession().getAttribute("msgInfo")!=null ? (String)request.getSession().getAttribute("msgInfo") :null;
	msgbgcol = (String)request.getSession().getAttribute("msgbgcol")!=null ? (String)request.getSession().getAttribute("msgbgcol") :"";
}
request.getSession().removeAttribute("msgInfo");
request.getSession().removeAttribute("msgbgcol");


String firstName = (String)request.getSession().getAttribute("firstName");
String lastName = (String)request.getSession().getAttribute("lastName");
String fullName = (String)request.getSession().getAttribute("fullName");
String userId = (String)request.getSession().getAttribute("userId");
String aid = (String)request.getSession().getAttribute("auctionId");
String currency = "PHP";

// IDs
Integer user_id = request.getSession().getAttribute("user-id")!=null ? (Integer)request.getSession().getAttribute("user-id") : null;
Integer user_role_id = request.getSession().getAttribute("user-role-id")!=null ? (Integer)request.getSession().getAttribute("user-role-id") : 0;


%>
<div class="side-widget">
	<div id="category-wrap">
		<div class="main-category-header">Categories</div>
		<ul class="main-category-list">

			<li><a href="electronics-and-gadgets.html">Electronics and
					Gadgets</a>
				<ul class="sub-category-list simple-nav-list">
					<li><a href="electronics-and-gadgets.html">Cameras</a></li>
					<li><a href="electronics-and-gadgets.html">Computer and
							Accessories</a></li>
					<li><a href="electronics-and-gadgets.html">Mobile Phone</a></li>
					<li><a href="electronics-and-gadgets.html">TV, Video,
							Audio</a></li>
					<li><a href="electronics-and-gadgets.html">Video Games</a></li>
				</ul></li>
			<li><a href="electronics-and-gadgets.html">Furniture</a>
				<ul class="sub-category-list simple-nav-list">
					<li><a href="electronics-and-gadgets.html">Chairs</a></li>
					<li><a href="electronics-and-gadgets.html">Desks &amp; Home Office Furniture</a></li>
					<li><a href="electronics-and-gadgets.html">Dining Sets</a></li>
					<li><a href="electronics-and-gadgets.html">Dressers</a></li>
					<li><a href="electronics-and-gadgets.html">Entertainment
							Units</a></li>
					<li><a href="electronics-and-gadgets.html">Frames &amp;	Covers</a></li>
					<li><a href="electronics-and-gadgets.html">Futons</a></li>
					<li><a href="electronics-and-gadgets.html">Home Decor</a></li>
					<li><a href="electronics-and-gadgets.html">Sofas</a></li>
					<li><a href="electronics-and-gadgets.html">Tables</a></li>
					<li><a href="electronics-and-gadgets.html">TV Stands</a></li>
				</ul></li>
			<li><a href="electronics-and-gadgets.html">Industrial
					Equipment</a>
				<ul class="sub-category-list simple-nav-list">
					<li><a href="electronics-and-gadgets.html">Construction</a></li>
					<li><a href="electronics-and-gadgets.html">Electrical</a></li>
					<li><a href="electronics-and-gadgets.html">Machines</a></li>
					<li><a href="electronics-and-gadgets.html">Office
							Equipment</a></li>
					<li><a href="electronics-and-gadgets.html">Plumbing</a></li>
					<li><a href="electronics-and-gadgets.html">Power Tools</a></li>
					<li><a href="electronics-and-gadgets.html">Restaurant
							Equipment</a></li>
				</ul></li>
			<li><a href="electronics-and-gadgets.html">Real Estate</a>
				<ul class="sub-category-list simple-nav-list">
					<li><a href="electronics-and-gadgets.html">Agricultural</a></li>
					<li><a href="electronics-and-gadgets.html">Commercial</a></li>
					<li><a href="electronics-and-gadgets.html">Industrial</a></li>
					<li><a href="electronics-and-gadgets.html">Residential</a></li>
				</ul></li>
			<li><a href="electronics-and-gadgets.html">Vehicles</a>
				<ul class="sub-category-list simple-nav-list">
					<li><a href="electronics-and-gadgets.html">Bus</a></li>
					<li><a href="electronics-and-gadgets.html">Cars</a></li>
					<li><a href="electronics-and-gadgets.html">Commercial
							Trucks</a></li>
					<li><a href="electronics-and-gadgets.html">Motorcycle</a></li>
					<li><a href="electronics-and-gadgets.html">SUV</a></li>
					<li><a href="electronics-and-gadgets.html">Van and Mini	Van</a></li>
				</ul></li>
		</ul>
	</div>
</div>

<div class="side-widget">
	<h4 class="text-primary">Quick Links</h4>
	<ul class="simple-nav-list">
		<% if(user_id!=null){ %>
		<li><a href="bid?mngr=get&a=my-bids">My Bids</a></li>
		<% } %>
		<li><a href="#">Services <span
				class="icon-right ion-chevron-right"></span></a></li>
		<li><a href="#">Contact Us <span
				class="icon-right ion-chevron-right"></span></a></li>
	</ul>
</div>


<div class="side-widget">
	<h4 class="text-primary">FAQ</h4>
	<ul class="simple-nav-list">
		<li><a href="#">Auction Tips <span
				class="icon-right ion-chevron-right"></span></a></li>
		<li><a href="#">Basic Auction FAQs <span
				class="icon-right ion-chevron-right"></span></a></li>
		<li><a href="#">Auction Terminologies <span
				class="icon-right ion-chevron-right"></span></a></li>
		<li><a href="#">Online Auction FAQs <span
				class="icon-right ion-chevron-right"></span></a></li>
	</ul>
</div>