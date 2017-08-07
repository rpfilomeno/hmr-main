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
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport"
	content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">

<title><%=COMPANY_NAME%></title>

<!-- Favicons -->
<link rel="apple-touch-icon" sizes="180x180"
	href="assets/themes/hmr/apple-touch-icon.png">
<link rel="icon" type="image/png"
	href="assets/themes/hmr/favicon-32x32.png" sizes="32x32">
<link rel="icon" type="image/png"
	href="assets/themes/hmr/favicon-16x16.png" sizes="16x16">
<link rel="manifest" href="assets/themes/hmr/manifest.json">
<link rel="mask-icon" href="assets/themes/hmr/safari-pinned-tab.svg"
	color="#222222">
<link rel="shortcut icon" href="assets/themes/hmr/favicon.ico">

<meta name="theme-color" content="#ffffff">
<meta property="og:site_name" content="HMR Auctions" />
<meta property="og:title" content="Product View - HMR Auctions" />
<meta property="og:url" content="product-view.html" />
<meta property="og:type" content="website" />

<meta property="og:description" content="" />

<meta property="og:image" content="" />

<meta itemprop="name" content="" />
<meta itemprop="url" content="" />
<meta itemprop="description" content="" />
<meta itemprop="thumbnailUrl" content="" />

<meta name="twitter:card" content="summary_large_image" />
<meta name="twitter:title" content="" />
<meta name="twitter:image" content="" />
<meta name="twitter:url" content="" />
<meta name="twitter:text" content="" />
<meta name="twitter:domain" content="">
<meta name="description" content="" />


<link rel="stylesheet" href="assets/themes/hmr/css/bootstrap.css">
<link rel="stylesheet" href="assets/themes/hmr/css/ionicons.min.css">
<link rel="stylesheet" href="assets/themes/hmr/css/main.css?v=36502498">

<link rel="stylesheet" href="assets/themes/hmr/css/ribbon.css?v=28374683" />
<link rel="stylesheet" href="assets/themes/hmr/css/favorite.css?v=28374683" />
<link rel="stylesheet"
	href="assets/plugins/jquery-ui/themes/smoothness/jquery-ui.min.css">
<link rel="stylesheet"
	href="assets/plugins/gallery/css/blueimp-gallery.min.css">


