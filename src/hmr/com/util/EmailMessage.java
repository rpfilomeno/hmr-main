package hmr.com.util;


import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class EmailMessage {
 
    public void sendHtmlEmail(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message) throws AddressException,
            MessagingException, IOException {

        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() {
            public PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(userName, password);
            }
        };
 
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
        
        msg.setFrom(new InternetAddress(userName));
        //InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        //msg.setRecipients(Message.RecipientType.TO,  toAddresses);
        msg.setRecipients(Message.RecipientType.TO,  InternetAddress.parse(toAddress));
        
        
        msg.setSubject(subject);
        msg.setSentDate(new Date());
        // set plain text message
        msg.setContent(message, "text/html");
 
        // sends the e-mail
        Transport.send(msg);
 
    }
 
    /**
     * Test the send html e-mail method
     * @throws IOException 
     *
     */
    public static void main(String[] args) throws IOException {
        // SMTP server information
    	//mainEmailCustomers27Days();
    	
    }
   
    
 
  
    
    
    public static String getVerifyEmailUser(String first_name, String last_name, String login_link){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>HMR Auctions</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"padding:1px; max-width:700px; margin:0 auto; display:block;\">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 2px; \"><img src=\"cid:image1\"></p><br>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\">Dear <strong style=\"color: #03499A\">"+first_name + " "+ last_name +"</strong>, </h4>");
        sb.append(getMainContentVerifyEmail(login_link));
        sb.append("<br>");
        sb.append("<p style=\"font-size: 12px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\"><strong style=\"color: #03499A\">HMR Auctions Team</strong> </h4>");
        sb.append("<br>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        //sb.append("</td>");
        //sb.append("<td></td>");
        //sb.append("</tr>");
        //sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    
    public static String getRegisteredSuccessfulEmailUser(String first_name, String last_name, String login_link){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>HMR Auctions</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"padding:1px; max-width:700px; margin:0 auto; display:block;\">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 2px; \"><img src=\"cid:image1\"></p><br>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\">Dear <strong style=\"color: #03499A\">"+first_name + " "+ last_name +"</strong>, </h4>");
        sb.append(getMainContentRegisteredSuccessfulEmail(login_link));
        sb.append("<br>");
        sb.append("<p style=\"font-size: 12px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\"><strong style=\"color: #03499A\">HMR Auctions Team</strong> </h4>");
        sb.append("<br>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        //sb.append("</td>");
        //sb.append("<td></td>");
        //sb.append("</tr>");
        //sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    public static String getPrivateRegistrationAccepted(String first_name, String last_name){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>HMR Auctions</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"padding:1px; max-width:700px; margin:0 auto; display:block;\">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 2px; \"><img src=\"cid:image1\"></p><br>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\">Dear <strong style=\"color: #03499A\">"+first_name + " "+ last_name +"</strong>, </h4>");
        sb.append(getMainContentPrivateRegistrationAccepted());
        sb.append("<br>");
        sb.append("<p style=\"font-size: 12px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\"><strong style=\"color: #03499A\">HMR Auctions Team</strong> </h4>");
        sb.append("<br>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        //sb.append("</td>");
        //sb.append("<td></td>");
        //sb.append("</tr>");
        //sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    public static String getPrivateRegistrationRejected(String first_name, String last_name){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>HMR Auctions</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"padding:1px; max-width:700px; margin:0 auto; display:block;\">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 2px; \"><img src=\"cid:image1\"></p><br>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\">Dear <strong style=\"color: #03499A\">"+first_name + " "+ last_name +"</strong>, </h4>");
        sb.append(getMainContentPrivateRegistrationRejected());
        sb.append("<br>");
        sb.append("<p style=\"font-size: 12px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\"><strong style=\"color: #03499A\">HMR Auctions Team</strong> </h4>");
        sb.append("<br>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        //sb.append("</td>");
        //sb.append("<td></td>");
        //sb.append("</tr>");
        //sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    public static String getNegotiatedBidEmailAdmin( 
    		String AuctionId, String AuctionName, String AuctionDescription,
    		String LotId, String LotNumber, String LotName, String LotDescription,
    		String BidderId, String BidderFirstName, String BidderLastName, String BidderEmail,
    		String OfferAmount, String OfferNote) {
    	
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>HMR Auctions</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"padding:1px; max-width:700px; margin:0 auto; display:block;\">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 2px; \"><img src=\"cid:image1\"></p><br>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\">Dear <strong style=\"color: #03499A\">HMR Auctions Team</strong>, </h4>");
        sb.append(getMainContentNegotiatedBidEmailAdmin( 
        		AuctionId, AuctionName, AuctionDescription,
        		LotId, LotNumber, LotName, LotDescription,
        		BidderId, BidderFirstName, BidderLastName, BidderEmail,
        		OfferAmount, OfferNote));
        sb.append("<br>");
        sb.append("<p style=\"font-size: 12px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\"><strong style=\"color: #03499A\">HMR Auctions Team</strong> </h4>");
        sb.append("<br>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    	
    }
    
    
    
    public static String getNegotiatedBidEmailBidder( 
    		String AuctionId, String AuctionName, String AuctionDescription,
    		String LotId, String LotNumber, String LotName, String LotDescription,
    		String BidderId, String BidderFirstName, String BidderLastName, String BidderEmail,
    		String OfferAmount, String OfferNote) {
    	
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>HMR Auctions</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"padding:1px; max-width:700px; margin:0 auto; display:block;\">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 2px; \"><img src=\"cid:image1\"></p><br>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\">Dear <strong style=\"color: #03499A\"> "+ BidderFirstName + " " + BidderLastName + ", </h4>");
        sb.append(getMainContentNegotiatedBidEmailBidder( 
        		AuctionId, AuctionName, AuctionDescription,
        		LotId, LotNumber, LotName, LotDescription,
        		OfferAmount, OfferNote));
        sb.append("<br>");
        sb.append("<p style=\"font-size: 12px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\"><strong style=\"color: #03499A\">HMR Auctions Team</strong> </h4>");
        sb.append("<br>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        //sb.append("</td>");
        //sb.append("<td></td>");
        //sb.append("</tr>");
        //sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    	
    }
    
    public static String getPrivateBidInviteEmailAdmin( 
    		String AuctionId, String AuctionName, String AuctionDescription,
    		String BidderId, String BidderFirstName, String BidderLastName, String BidderEmail,
    		String CompanyIdNo
    		) {
    	
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>HMR Auctions</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"padding:1px; max-width:700px; margin:0 auto; display:block;\">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 2px; \"><img src=\"cid:image1\"></p><br>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\">Dear <strong style=\"color: #03499A\">HMR BID Team</strong>, </h4>");
        sb.append(getMainContentPrivateBidApproveEmailAdmin( 
        		AuctionId, AuctionName, AuctionDescription,
        		BidderId, BidderFirstName, BidderLastName, BidderEmail,
        		CompanyIdNo
        		));
        sb.append("<br>");
        sb.append("<p style=\"font-size: 12px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\"><strong style=\"color: #03499A\">HMR BID Team</strong> </h4>");
        sb.append("<br>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    	
    }
    
    public static String getPrivateBidInviteApproveEmailBidder( 
    		String AuctionId, 
    		String AuctionName, 
    		String AuctionDescription,
    		String BidderId, 
    		String BidderFirstName, 
    		String BidderLastName, 
    		String BidderEmail
    		) {
    	
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>HMR Auctions</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"padding:1px; max-width:700px; margin:0 auto; display:block;\">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 2px; \"><img src=\"cid:image1\"></p><br>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\">Dear <strong style=\"color: #03499A\"> "+ BidderFirstName + " " + BidderLastName + ", </h4>");
        sb.append(getMainContentPrivateBidApproveEmailBidder( 
        		AuctionId, AuctionName, AuctionDescription));
        sb.append("<br>");
        sb.append("<p style=\"font-size: 12px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\"><strong style=\"color: #03499A\">HMR BID Team</strong> </h4>");
        sb.append("<br>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    	
    }
    
    public static String getPrivateBidInviteRejectEmailBidder( 
    		String AuctionId, 
    		String AuctionName, 
    		String AuctionDescription,
    		String BidderId, 
    		String BidderFirstName, 
    		String BidderLastName, 
    		String BidderEmail
    		) {
    	
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>HMR Auctions</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"padding:1px; max-width:700px; margin:0 auto; display:block;\">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 2px; \"><img src=\"cid:image1\"></p><br>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\">Dear <strong style=\"color: #03499A\"> "+ BidderFirstName + " " + BidderLastName + ", </h4>");
        sb.append(getMainContentPrivateBidRejectEmailBidder( 
        		AuctionId, AuctionName, AuctionDescription));
        sb.append("<br>");
        sb.append("<p style=\"font-size: 12px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\"><strong style=\"color: #03499A\">HMR BID Team</strong> </h4>");
        sb.append("<br>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    	
    }
    
    public static String getPrivateBidInviteRejectEmailBidder797( 
    		String AuctionId, 
    		String AuctionName, 
    		String AuctionDescription,
    		String BidderId, 
    		String BidderFirstName, 
    		String BidderLastName, 
    		String BidderEmail
    		) {
    	
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>HMR Auctions</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"padding:1px; max-width:700px; margin:0 auto; display:block;\">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 2px; \"><img src=\"cid:image1\"></p><br>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\">Dear <strong style=\"color: #03499A\"> "+ BidderFirstName + " " + BidderLastName + ", </h4>");
        sb.append(getMainContentPrivateBidRejectEmailBidder797( 
        		AuctionId, AuctionName, AuctionDescription));
        sb.append("<br>");
        sb.append("<p style=\"font-size: 12px;\">Thank you,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\"><strong style=\"color: #03499A\">HMR BID Team</strong> </h4>");
        sb.append("<br>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    	
    }
    
    public static String getNewPasswordEmailUser(String first_name, String last_name, String newPassword){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>HMR Auctions</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"padding:1px; max-width:700px; margin:0 auto; display:block;\">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 2px; \"><img src=\"cid:image1\"></p><br>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\">Dear <strong style=\"color: #03499A\">"+first_name + " "+ last_name +"</strong>, </h4>");
        sb.append(getMainContentNewPasswordEmail(newPassword));
        sb.append("<br>");
        sb.append("<p style=\"font-size: 12px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 12px;\"><strong style=\"color: #03499A\">HMR BID Team</strong> </h4>");
        sb.append("<br>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        //sb.append("</td>");
        //sb.append("<td></td>");
        //sb.append("</tr>");
        //sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    
    
    public static String getFooterContent(){
 	   
 	   String content = "";
        content = content +"<table style=\"min-width: 300px;float:left;\" width=\"100%\">";
        content = content +"<tr>";
        content = content +"<td bgcolor=\"#252525\" style=\"color: white;\">";
        content = content +"<p style=\"font-size: xx-small; padding-top: 10px; padding-right: 10px; padding-bottom: 10px; padding-left: 10px;\">The information contained in this message is privileged and intended only for the recipients named.<br>If the reader is not a representative of the intended recipient, any review, dissemination or copying of this message or the information it contains is prohibited.<br>If you have received this message in error, please immediately notify the sender, and delete the original message and attachments.<br>Please consider the environment before printing this email.</p>";
        content = content +"<span style=\"display: block; clear: both;\"></span>";
        content = content +"</td>";
        content = content +"</tr>";
        content = content +"</table>";
 	   
 	   return content;
    }
    
    public static String getMainContent1(String login_link){
 	   
 	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day! Thank you for choosing IP Converge Data Services Inc. It is our pleasure to partner with you on going Google.</p>";
 	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">At this point, we would need you to nominate the members of your company who will attend the end user training session. Please click the button below. The nominees will be receiving email notifications that will help book their training schedule.</p>";
 	   body = body +"<p style=\"margin-bottom: 10px;font-size: 16px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">Nominate</a></p>";
 	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">NOTE: The training nomination button is valid only for six months from the time you received this email. If you have questions or concerns, feel free to reach out to your dedicated account manager.</p>";
 	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you and enjoy your Google journey!</p>";
 	   //body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">NOTE: The training nomination button is valid only for six months from the time you received this email. If you have questions or concerns, feel free to reach out to your dedicated account manager.</p>";
 	   return body;
    }
    
    public static String getMainContent2(String login_link){
  	   
  	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day! Thank you for choosing IP Converge Data Services Inc. It is our pleasure to partner with you on going Google.</p>";
  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">You have been nominated by your company's Google for Work Service Administrator to join an end user training session. To know more about the training service and to book your schedule, please click the button below.</p>";
  	   body = body +"<p style=\"margin-bottom: 10px;font-size: 16px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">Confirm</a></p>";
  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">NOTE: The confirmation button is valid only for six months from the time you received this email. Please book your training as soon as you can to avoid inconvenience.</p>";
  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">If you have questions or concerns, feel free to reach out to your Google for Work Service Administrator.</p>";
  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you and enjoy your Google journey!</p>";
  	   //body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">NOTE: The training nomination button is valid only for six months from the time you received this email. If you have questions or concerns, feel free to reach out to your dedicated account manager.</p>";
  	   return body;
     }
    
    //Confirmation email to admin that nomination is successful
    public static String getMainContent3(String a_first_name, String a_last_name, String login_link){
   	   
   	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Congratulations! You have successfully nominated "+a_first_name+" " + a_last_name +" of your organization who will attend the Google for Work end user training session.</p>";
   	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Should you need to manage the details of the nominees, please click the button below.</p>";
   	   body = body +"<p style=\"margin-bottom: 10px;font-size: 16px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">Nominate</a></p>";
   	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Please feel free to reach out to your dedicated account manager for questions or concerns.</p>";
   	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you and enjoy your Google journey!</p>";
   	   //body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">NOTE: The training nomination button is valid only for six months from the time you received this email. If you have questions or concerns, feel free to reach out to your dedicated account manager.</p>";
   	   return body;
      }
    
    public static String getMainContent4(String login_link){
   	   
   	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day! Thank you for choosing IP Converge Data Services Inc. It is our pleasure to partner with you on going Google.</p>";
   	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">You have confirmed in the nomination by your company's Google for Work Service Administrator to join an end user training session. To know more about the training service and to book your schedule, please click the button below.</p>";
   	   body = body +"<p style=\"margin-bottom: 10px;font-size: 16px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">Book Now</a></p>";
   	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">NOTE: The book now button is valid only for six months from the time you received this email. Please book your training as soon as you can to avoid inconvenience.</p>";
   	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">If you have questions or concerns, feel free to reach out to your Google for Work Service Administrator.</p>";
   	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you and enjoy your Google journey!</p>";
   	   //body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">NOTE: The training nomination button is valid only for six months from the time you received this email. If you have questions or concerns, feel free to reach out to your dedicated account manager.</p>";
   	   return body;
      }
    
    public static String getMainContent5(String a_first_name, String a_last_name, String login_link){
    	   
    	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Congratulations! "+a_first_name+" " + a_last_name +" of your organization confirmed to attend the Google for Work end user training session.</p>";
    	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Should you need to manage the details of the nominees, please click the button below.</p>";
    	   body = body +"<p style=\"margin-bottom: 10px;font-size: 16px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">Nominate</a></p>";
    	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Please feel free to reach out to your dedicated account manager for questions or concerns.</p>";
    	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you and enjoy your Google journey!</p>";
    	   //body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">NOTE: The training nomination button is valid only for six months from the time you received this email. If you have questions or concerns, feel free to reach out to your dedicated account manager.</p>";
    	   return body;
       }
    
    public static String getMainContentScheduledTraining(String login_link, String access_key, Integer calendar_id){
    	   
    	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Congratulations! You have successfully booked your Google for Work end-user training session.</p>";
    	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Your booking reference number is "+access_key+"-"+calendar_id+".</p>";
    	   
    	   body = body +"<p style=\"margin-bottom: 10px;font-size: 16px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">View Booking Calendar</a></p>";
    	   
    	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Should you need to cancel or move your confirmed training schedule, please click on the button below. Kindly note that changes on the schedule and/or cancellations are only allowed  five (5) working days before the initially confirmed training schedule. Changes or cancellations of the initially confirmed training schedule is only allowed once.</p>";
    	   
    	   
    	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">May we request that you fill out the pre-training survey form linked below. </p>";
    	   
    	   login_link = "https://docs.google.com/a/ipc.ph/forms/d/1zCoFXqrVB39gxWiMjIZ5A_ebppq1BRvDaV6MoiGEfiY";
    	   
    	   body = body +"<p style=\"margin-bottom: 10px;font-size: 16px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">Pre-Training Survey</a></p>";
    	   
    	   
    	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Your responses will definitely help in providing you a great learning experience.</p>";
    	   
    	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Please feel free to reach out to your Google for Work Service Administrator for questions or concerns. </p>";
    	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you and enjoy your Google journey!</p>";

    	   return body;
       }
    
    public static String getMainContentReScheduledTraining(String login_link, String access_key, Integer calendar_id){
 	   
    	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Congratulations! You have successfully re-booked your Google for Work end-user training session. Your booking reference number is "+access_key+"-"+calendar_id+".</p>";
       	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Please feel free to reach out to your Google for Work Service Administrator for questions or concerns.</p>";
       	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you and enjoy your Google journey!</p>";

 	   return body;
    }
    
    
    public static String getMainContentCancelTraining(String login_link, String access_key, Integer calendar_id){
  	   
 	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day! We are sending this email to inform you that the cancellation of your initial training schedule is now completed.</p>";
    	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">To know more about the training service and to book your new schedule, please click the button below. After booking your new schedule, you will no longer be allowed to make changes or cancel.</p>";
    	   body = body +"<p style=\"margin-bottom: 10px;font-size: 16px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">Book Now</a></p>";
    	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Please feel free to reach out to your Google for Work Service Administrator for questions or concerns.</p>";
    	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you for choosing IP Converge Data Services Inc,.</p>";

	   return body;
 }
    
    public static String getMainContentCancelTraining_Admin(String first_name, String last_name){
  	   
       String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day! We are sending this email to inform you that "+first_name+" "+last_name+" has successfully cancelled the booking of his/her initial end user training schedule.</p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Please feel free to reach out to your dedicated account manager for questions or concerns.</p>";
	  	   body = body +"<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you for choosing IP Converge Data Services Inc,.</p>";

  	   return body;
     }
    
    
    public static String getMainContentScheduledTraining_Admin(String first_name, String last_name){
 	   
 	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day! We are sending this email to inform you that "+first_name+" "+last_name+" has successfully completed the booking of his/her end user training schedule.</p>";
	 	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Please feel free to reach out to your dedicated account manager for questions or concerns.</p>";
	 	   body = body +"<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you for choosing IP Converge Data Services Inc,.</p>";

 	   return body;
    }
    
    public static String getMainContentReScheduledTraining_Admin(String first_name, String last_name){
  	   
  	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day! We are sending this email to inform you that "+first_name+" "+last_name+" has successfully completed the re-booking of his/her end user training schedule.</p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Please feel free to reach out to your dedicated account manager for questions or concerns.</p>";
	  	   body = body +"<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you for choosing IP Converge Data Services Inc,.</p>";

  	   return body;
     }
    
    public static String getMainContentUser(String login_link){
  	   
  	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day! </p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Please click the Veriy Email button below.</p>";
	  	   body = body +"<p style=\"margin-bottom: 10px;font-size: 16px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">Verify Email</a></p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Please feel free to reach out to online-auctions@hmrbid.com for questions or concerns.</p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you for choosing HMR Auctions.</p>"; 
  	   return body;
     }
    
    public static String getMainContentVerifyEmail(String login_link){
   	   
   	   String body = "<p style=\"margin-bottom: 10px;font-size: 12px;\">Good day!</p>";
 	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">You are one step from bidding. Click the button below to continue.</p>";
 	  	   body = body +"<p style=\"margin-bottom: 10px;font-size: 12px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">Verify Email</a></p>";
 	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Please feel free to reach out to online-auctions@hmrbid.com for questions or concerns.</p>";
 	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Thank you for choosing HMR Auctions.</p>"; 
   	   return body;
      }
    
    
    public static String getMainContentRegisteredSuccessfulEmail(String login_link){
    	   
    	   String body = "<p style=\"margin-bottom: 10px;font-size: 12px;\">Good day!</p>";
  	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">You have successfully registered. Click button below to login.</p>";
  	  	   body = body +"<p style=\"margin-bottom: 10px;font-size: 12px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">Login</a></p>";
  	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Please feel free to reach out to online-auctions@hmrbid.com for questions or concerns.</p>";
  	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Thank you for choosing HMR Auctions.</p>"; 
    	   return body;
       }
    
    public static String getMainContentPrivateRegistrationAccepted(){
 	   
 	   String body = "<p style=\"margin-bottom: 10px;font-size: 12px;\">Good day!</p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Your private bid request have been accepted.";
	  	  // body = body +"<p style=\"margin-bottom: 10px;font-size: 12px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">Login</a></p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Please feel free to reach out to online-auctions@hmrbid.com for questions or concerns.</p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Thank you for choosing HMR Auctions.</p>"; 
 	   return body;
    }
    
    public static String getMainContentPrivateRegistrationRejected(){
  	   
  	   String body = "<p style=\"margin-bottom: 10px;font-size: 12px;\">Good day!</p>";
 	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Your private bid request have been rejected.";
 	  	  // body = body +"<p style=\"margin-bottom: 10px;font-size: 12px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">Login</a></p>";
 	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Please feel free to reach out to online-auctions@hmrbid.com for questions or concerns.</p>";
 	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Thank you for choosing HMR Auctions.</p>"; 
  	   return body;
     }

    public static String getMainContentNewPasswordEmail(String newPassword){
 	   
 	   String body = "<p style=\"margin-bottom: 10px;font-size: 12px;\">Good day!</p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Please see your new password below.</p>";
	  	   body = body +"<p style=\"margin-bottom: 10px;font-size: 12px;\">New Password : "+newPassword+"</p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Please feel free to reach out to online-auctions@hmrbid.com for questions or concerns.</p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Thank you for choosing HMR Auctions.</p>"; 
 	   return body;
    }
    
    
    public static String getMainContentRequest(String login_link){
   	   
   	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day!</p>";
   	       body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">We would like to request the change of password.</p>";
 	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Would you allow the process of this request?</p>";
 	  	   body = body +"<p style=\"margin-bottom: 10px;font-size: 16px;\">";
 	  	   body = body + "<a href=\""+login_link+"&r=y\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\"> &nbsp;&nbsp; Yes &nbsp;&nbsp;  </a>";
 	  	   body = body + "<a href=\""+login_link+"&r=n"+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">  &nbsp;&nbsp; No &nbsp;&nbsp; </a>";
 	  	   body = body + "</p>";
 	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">NOTE: The Yes and No buttons are valid only for 24 hours from the time you received this email.</p>";
 	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">If you have questions or concerns, feel free to reach out to your Google Administrator..</p>";
 	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you for choosing Marcventures.</p>";
   	   return body;
      }
    

    public static String getMainContentRequest(String login_link, String pass_word){
    	   
    	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day!</p>";
    	       body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">We would like to request the change of password.</p>";
  	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Would you allow the process of this request?</p>";
  	  	   body = body +"<p style=\"margin-bottom: 10px;font-size: 16px;\">";
  	  	   body = body + "<a href=\""+login_link+"&r=y\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\"> &nbsp;&nbsp; Yes &nbsp;&nbsp;  </a>";
  	  	   body = body + "<a href=\""+login_link+"&r=n"+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">  &nbsp;&nbsp; No &nbsp;&nbsp; </a>";
  	  	   body = body + "</p>";
  	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">NOTE: The Yes and No buttons are valid only for 24 hours from the time you received this email.</p>";
  	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">If you have questions or concerns, feel free to reach out to your Google Administrator..</p>";
  	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you for choosing Marcventures.</p>";
    	   return body;
       }
    
    public static String getMainContentChangePassword(String login_link, String first_name, String last_name){
 	   //login_link = "https://bizoncloudone.com/MV";
 	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day!</p>";
 	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">We would like to inform you that the change of password of "+first_name+ " "+last_name +" is successful.</p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you for choosing Marcventures.</p>";
 	   return body;
    }
    
    
    public static String getMainContentChangePasswordAdmin(String login_link, String first_name, String last_name){
  	   //login_link = "https://bizoncloudone.com/MV";
  	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day!</p>";
  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">We would like to inform you that the change of password of "+first_name+ " "+last_name +" is successful.</p>";
  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">You may see the details of this request by clicking the button below.</p>";
  	   body = body + "<a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">  &nbsp;&nbsp; MV Google Admin Console &nbsp;&nbsp; </a>";
 	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you for choosing Marcventures.</p>";
  	   return body;
     }
    
    public static String getMainContentChangePassword(String login_link, String first_name, String last_name, String pass_word){
    	   //login_link = "https://bizoncloudone.com/MV";
    	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day!</p>";
    	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">We would like to inform you that the change of password of "+first_name+ " "+last_name +" is successful.</p>";
    	   
    	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">The new password is "+pass_word+".</p>";
    	   
  	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you for choosing Marcventures.</p>";
    	   return body;
       }
    
    public static String getMainContentChangePasswordwithPassword(String login_link, String first_name, String last_name, String pass_word){
 	   String login_link_google = "https://accounts.google.com";
 	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day!</p>";
 	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">We would like to inform you that the change of password of "+first_name+ " "+last_name +" is successful.</p>";
 	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">The new password is "+pass_word+".</p>";
 	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">You may see the details of this request by clicking the button below.</p>";
 	   body = body + "<a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">  &nbsp;&nbsp; MV Google Admin Console &nbsp;&nbsp; </a> &nbsp;&nbsp;&nbsp;&nbsp; ";
 	   body = body + "<a href=\""+login_link_google+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">  &nbsp;&nbsp; Google Sign In &nbsp;&nbsp; </a>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you for choosing Marcventures.</p>";
 	   return body;
    }
    

    
    public static String getMainContentNegotiatedBidEmailAdmin(
    		String AuctionId, String AuctionName, String AuctionDescription,
    		String LotId, String LotNumber, String LotName, String LotDescription,
    		String BidderId, String BidderFirstName, String BidderLastName, String BidderEmail,
    		String OfferAmount, String OfferNote
    		) {
    	String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day! This is a negotited bid notification.</p>";
     	body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">";
     	body = body + "<label>AUCTION ID:<label> " + AuctionId + "<br/>";
     	body = body + "<label>AUCTION NAME:<label> " + AuctionName + "<br/>";
     	body = body + "<label>AUCTION DESCRIPTION:<label> " + AuctionDescription + "<br/>";
     	body = body + "<label>LOT ID:<label> " + LotId + "<br/>";
     	body = body + "<label>LOT NO:<label> " + LotNumber + "<br/>";
     	body = body + "<label>LOT NAME:<label> " + LotName + "<br/>";
     	body = body + "<label>LOT DESCRIPTION:<label> " + LotDescription + "<br/>";   	
     	body = body + "</p>";
     	body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">";
     	body = body + "<label>BIDDER ID:<label> " + BidderId + "<br/>";
     	body = body + "<label>BIDDER NAME:<label> " + BidderFirstName + " " + BidderLastName + "<br/>";
     	body = body + "<label>BIDDER EMAIL:<label> " + BidderEmail + "<br/>";
     	body = body + "</p>";
     	body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">";
     	body = body + "<label>OFFER AMOUNT:<label> " + OfferAmount + "<br/>";
     	body = body + "<label>NOTE:<label> " + OfferNote + "<br/>";
     	body = body + "</p>";
     	return body;
    }
    
    public static String getMainContentNegotiatedBidEmailBidder(
    		String AuctionId, String AuctionName, String AuctionDescription,
    		String LotId, String LotNumber, String LotName, String LotDescription,
    		String OfferAmount, String OfferNote
    		) {
    	String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day! We have received your negotiated bid offer with the following details.</p>";
     	body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">";
     	body = body + "<label>AUCTION ID:<label> " + AuctionId + "<br/>";
     	body = body + "<label>AUCTION NAME:<label> " + AuctionName + "<br/>";
     	body = body + "<label>AUCTION DESCRIPTION:<label> " + AuctionDescription + "<br/>";
     	body = body + "<label>LOT ID:<label> " + LotId + "<br/>";
     	body = body + "<label>LOT NO:<label> " + LotNumber + "<br/>";
     	body = body + "<label>LOT NAME:<label> " + LotName + "<br/>";
     	body = body + "<label>LOT DESCRIPTION:<label> " + LotDescription + "<br/>";   	
     	body = body + "</p>";
     	body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">";
     	body = body + "<label>OFFER AMOUNT:<label> " + OfferAmount + "<br/>";
     	body = body + "<label>NOTE:<label> " + OfferNote + "<br/>";
     	body = body + "</p>";
     	return body;
    }
    
    public static String getMainContentPrivateBidApproveEmailAdmin(
    		String AuctionId, String AuctionName, String AuctionDescription,
    		String BidderId, String BidderFirstName, String BidderLastName, String BidderEmail,
    		String CompanyIdNo
    		) {
    	String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">An invite for Private Bidding has been approved.</p>";
     	body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">";
     	body = body + "<label>AUCTION ID:<label> " + AuctionId + "<br/>";
     	body = body + "<label>AUCTION NAME:<label> " + AuctionName + "<br/>";
     	body = body + "<label>AUCTION DESCRIPTION:<label> " + AuctionDescription + "<br/>";
     	body = body + "</p>";
     	body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">";
     	body = body + "<label>BIDDER ID:<label> " + BidderId + "<br/>";
     	body = body + "<label>BIDDER NAME:<label> " + BidderFirstName + " " + BidderLastName + "<br/>";
     	body = body + "<label>BIDDER EMAIL:<label> " + BidderEmail + "<br/>";
     	body = body + "<label>BIDDER COMPANY ID NO:<label> " + CompanyIdNo + "<br/>";
     	body = body + "</p>";
     	return body;
    }
    
    public static String getMainContentPrivateBidApproveEmailBidder(
    		String AuctionId, String AuctionName, String AuctionDescription
    		) {
    	String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day! Your invite for Private Bidding has been approved.</p>";
     	body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">";
     	body = body + "<label>AUCTION ID:<label> " + AuctionId + "<br/>";
     	body = body + "<label>AUCTION NAME:<label> " + AuctionName + "<br/>";
     	body = body + "<label>AUCTION DESCRIPTION:<label> " + AuctionDescription + "<br/>";
     	body = body + "</p>";
     	return body;
    }
    
    public static String getMainContentPrivateBidRejectEmailBidder(
    		String AuctionId, String AuctionName, String AuctionDescription
    		) {
    	String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day! Your invite for Private Bidding has been rejected.</p>";
     	body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">";
     	body = body + "<label>AUCTION ID:<label> " + AuctionId + "<br/>";
     	body = body + "<label>AUCTION NAME:<label> " + AuctionName + "<br/>";
     	body = body + "<label>AUCTION DESCRIPTION:<label> " + AuctionDescription + "<br/>";
     	body = body + "</p>";
     	return body;
    }
    
    public static String getMainContentPrivateBidRejectEmailBidder797(
    		String AuctionId, String AuctionName, String AuctionDescription
    		) {
    	String body = "<p style=\"margin-bottom: 10px;font-size: 12px;\">Good day!</p>";
     	body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Your registration of join the private bidding has not been approved due or may of the following criteria;</p>";

     	body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">";
     	body = body + "Who are eligible to join?<br/>";
     	body = body + "This Company Vehicle Purchase Program (\"Program\") is open to employees of PMFTC, Inc or Philip Morris Manufacturing Inc (\"Company\") provided such employees meet the following criteria below at the date of offering:<br/>";
     	body = body + "1. Should have at least one (1) year tenure;<br/>";
     	body = body + "1. Should have at least one (1) year tenure;<br/>";
     	body = body + "2. No disciplinary leave for the last two (2) years; and<br/>";
     	body = body + "3. No other company vehicle purchased (Except Benefit Vehicle) for the last two (2) years.<br/><br/>";
     	
     	body = body + "Who are not qualified from joining the bidding?<br/>";
     	body = body + "All employees under Fleet Department, employees under agency and those who have won vehicle on previous Employee Bidding.<br/><br/>";
     	
     	body = body + "Furthermore, aside from required base eligibility, those employees with won units who cancelled or withdraw from their original bids last cycle are not also allowed or qualified to join in this cycle.<br/>";
     	body = body + "</p>";
     	
     	
     	body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">For further assistance please call our Customer Service at 0945-3211-495 or 0945-3211-496</p>";

     	
     	return body;
    }
    
}