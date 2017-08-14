package hmr.com.util;

import java.sql.SQLException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class EmailUtil {
	//C:\Work\workspace\HMR\WebContent\hmr\images\email\header_1.png
	private static String SERVER_DIRECTORY_LOCAL = "C:\\Work\\workspace\\HMR\\WebContent\\hmr\\images\\email\\"; 
	//private static String SERVER_DIRECTORY_ONLINE = "/opt/apache-tomcat-7.0.54/webapps/IPCGS/images/";
	private static String SERVER_DIRECTORY_ONLINE = "";
	
	static String host_online = "https://bizoncloudone.com/MV";
	//static String host_online = "reg.cloud.com.ph";
	static String host_local = "http://localhost:8080";
	static String host = host_online;
	
	//private static String SERVER_DIRECTORY = SERVER_DIRECTORY_LOCAL;
	private static String SERVER_DIRECTORY = "";
	private static String PROTOCOL = "";
			
	public static void main(String[] args) throws SQLException {

			  setSERVER_DIRECTORY(SERVER_DIRECTORY_LOCAL);
			  String to = "noreplyhmrauctions@gmail.com";
			  String cc = "noreplyhmrauctions@gmail.com";
			  String comp_name = "Administrator";
			  String first_name = "";
			  String last_name = "";

			  to = "noreplyhmrauctions@gmail.com";
			  first_name = "Andre";
			  last_name = "Dacanay";
			  String access_key = "123";

			  sendVerifyEmailUser(to,cc,first_name,last_name,"asdfasd");
			  
			  //sendNegotiatedBidEmailAdmin("noreplyhmrauctions@gmail.com", "sadf", "123", "12312", "AuctionDescription", "12312", "123123", "LotName", "LotDescription", "123123", "BidderFirstName", "BidderLastName", "noreplyhmrauctions@gmail.com", "1000", "asdfasd");

		   }
	

	   public static void sendVerifyEmailUser(String to, String cc, String first_name, String last_name, String login_link){
			 
		      String from = "noreplyhmrauctions@gmail.com";
		      final String username = "noreplyhmrauctions@gmail.com";
		      final String password = "hmrAuctions";
		      String host = "smtp.gmail.com";
		      String port = "587";

		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", port);

		      Session session = Session.getInstance(props,
		         new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		               return new PasswordAuthentication(username, password);
		            }
		         });

		      try {

		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);
		         
		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse(to));
		         /*
		         message.setRecipients(Message.RecipientType.CC,
		 	            InternetAddress.parse(cc));
		         */
		         // Set Subject: header field
		         message.setSubject("HMR Auctions : Verify Email");

		         // This mail has 2 part, the BODY and the embedded image
		         MimeMultipart multipart = new MimeMultipart("related");

		         // first part (the html)
		         BodyPart messageBodyPart = new MimeBodyPart();
		         
		         String htmlText = EmailMessage.getVerifyEmailUser(first_name, last_name,login_link);
		         
		         System.out.println("html "+htmlText);

		         messageBodyPart.setContent(htmlText, "text/html");
		         // add it
		         multipart.addBodyPart(messageBodyPart);

		         
		         /*
		         // second part (the image)
		         messageBodyPart = new MimeBodyPart();
		         DataSource fds1 = new FileDataSource(
		        		 getSERVER_DIRECTORY()+"mhi-logo.png");
		        // DataSource fds1 = new FileDataSource(
		 	     //       "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/GoogleAppsEmailHeader.png");
		         
		         
		         messageBodyPart.setDataHandler(new DataHandler(fds1));
		         messageBodyPart.setHeader("Content-ID", "<image1>");
		         multipart.addBodyPart(messageBodyPart);
		         
		         messageBodyPart = new MimeBodyPart();
		        DataSource fds2 = new FileDataSource(
		        		getSERVER_DIRECTORY()+"google-work-logo-white.png");
		        // DataSource fds2 = new FileDataSource(
		        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/cloud_com_ph.png");
		         messageBodyPart = new MimeBodyPart();
		         messageBodyPart.setDataHandler(new DataHandler(fds2));
		         messageBodyPart.setHeader("Content-ID", "<image2>");
		         multipart.addBodyPart(messageBodyPart);
		         */
		         
		         messageBodyPart = new MimeBodyPart();
		        DataSource fds3 = new FileDataSource(
		        		getSERVER_DIRECTORY()+"header_2.png");
		        // DataSource fds3 = new FileDataSource(
		        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
		         messageBodyPart.setDataHandler(new DataHandler(fds3));
		         messageBodyPart.setHeader("Content-ID", "<image1>");
		         multipart.addBodyPart(messageBodyPart);

		         // put everything together
		         message.setContent(multipart);
		         // Send message
		         //message.setContent(htmlText, "text/html");
		         Transport.send(message);

		         System.out.println("Sent message successfully....");

		      } catch (MessagingException e) {
		         throw new RuntimeException(e);
		      }
	   }

	   
	   
	   
	   public static void sendRegisteredSuccessfulEmailUser(String to, String cc, String first_name, String last_name, String login_link){
			 
		      String from = "noreplyhmrauctions@gmail.com";
		      final String username = "noreplyhmrauctions@gmail.com";
		      final String password = "hmrAuctions";
		      String host = "smtp.gmail.com";
		      String port = "587";

		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", port);

		      Session session = Session.getInstance(props,
		         new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		               return new PasswordAuthentication(username, password);
		            }
		         });

		      try {

		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);
		         
		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse(to));
		         /*
		         message.setRecipients(Message.RecipientType.CC,
		 	            InternetAddress.parse(cc));
		         */
		         // Set Subject: header field
		         message.setSubject("HMR Auctions : Registration Successful");

		         // This mail has 2 part, the BODY and the embedded image
		         MimeMultipart multipart = new MimeMultipart("related");

		         // first part (the html)
		         BodyPart messageBodyPart = new MimeBodyPart();
		         
		         String htmlText = EmailMessage.getRegisteredSuccessfulEmailUser(first_name, last_name,login_link);
		         
		         System.out.println("html "+htmlText);

		         messageBodyPart.setContent(htmlText, "text/html");
		         // add it
		         multipart.addBodyPart(messageBodyPart);

		         
		         /*
		         // second part (the image)
		         messageBodyPart = new MimeBodyPart();
		         DataSource fds1 = new FileDataSource(
		        		 getSERVER_DIRECTORY()+"mhi-logo.png");
		        // DataSource fds1 = new FileDataSource(
		 	     //       "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/GoogleAppsEmailHeader.png");
		         
		         
		         messageBodyPart.setDataHandler(new DataHandler(fds1));
		         messageBodyPart.setHeader("Content-ID", "<image1>");
		         multipart.addBodyPart(messageBodyPart);
		         
		         messageBodyPart = new MimeBodyPart();
		        DataSource fds2 = new FileDataSource(
		        		getSERVER_DIRECTORY()+"google-work-logo-white.png");
		        // DataSource fds2 = new FileDataSource(
		        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/cloud_com_ph.png");
		         messageBodyPart = new MimeBodyPart();
		         messageBodyPart.setDataHandler(new DataHandler(fds2));
		         messageBodyPart.setHeader("Content-ID", "<image2>");
		         multipart.addBodyPart(messageBodyPart);
		         */
		         
		         messageBodyPart = new MimeBodyPart();
		        DataSource fds3 = new FileDataSource(
		        		getSERVER_DIRECTORY()+"header_3.png");
		        // DataSource fds3 = new FileDataSource(
		        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
		         messageBodyPart.setDataHandler(new DataHandler(fds3));
		         messageBodyPart.setHeader("Content-ID", "<image1>");
		         multipart.addBodyPart(messageBodyPart);

		         // put everything together
		         message.setContent(multipart);
		         // Send message
		         //message.setContent(htmlText, "text/html");
		         Transport.send(message);

		         System.out.println("Sent message successfully....");

		      } catch (MessagingException e) {
		         throw new RuntimeException(e);
		      }
	   }
	   
	   public static void sendPrivateBidInviteApproveAdmin(
			   String to, String cc,
			   String AuctionId, String AuctionName, String AuctionDescription,
			   String BidderId, String BidderFirstName, String BidderLastName, String BidderEmail,
			   String CompanyIdNo
			   ) {
		   
		   	  String from = "noreplyhmrauctions@gmail.com";
		      final String username = "noreplyhmrauctions@gmail.com";
		      final String password = "hmrAuctions";
		      String host = "smtp.gmail.com";
		      String port = "587";

		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", port);

		      Session session = Session.getInstance(props,
		         new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		               return new PasswordAuthentication(username, password);
		            }
		         });

		      try {

		         Message message = new MimeMessage(session);
		         message.setFrom(new InternetAddress(from));
		         message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse(to));
		         message.setSubject("HMR Auctions : Private Bid Admin Notification");

		         MimeMultipart multipart = new MimeMultipart("related");
		         BodyPart messageBodyPart = new MimeBodyPart();
		         
		         String htmlText = EmailMessage.getPrivateBidInviteEmailAdmin( 
		         		AuctionId, AuctionName, AuctionDescription,
		        		BidderId, BidderFirstName, BidderLastName, BidderEmail,
		        		CompanyIdNo
		        		);
		         
		         System.out.println("html "+htmlText);

		         messageBodyPart.setContent(htmlText, "text/html");
		         // add it
		         multipart.addBodyPart(messageBodyPart);
		         messageBodyPart = new MimeBodyPart();
		         DataSource fds3 = new FileDataSource(
		        		getSERVER_DIRECTORY()+"header_5.png");
		         messageBodyPart.setDataHandler(new DataHandler(fds3));
		         messageBodyPart.setHeader("Content-ID", "<image1>");

		         message.setContent(multipart);
		         Transport.send(message);

		         System.out.println("Sent message successfully....");

		      } catch (Exception e) {
		    	  System.out.println("Exception on Email "+e.getMessage());
		         throw new RuntimeException(e);
		      }

	   }
	   
	   public static void sendPrivateBidInviteApproveBidder(
			   String to, String cc,
			   String AuctionId, String AuctionName, String AuctionDescription,
			   String BidderId, String BidderFirstName, String BidderLastName, String BidderEmail
			   ) {
		   
		   	  String from = "noreplyhmrauctions@gmail.com";
		      final String username = "noreplyhmrauctions@gmail.com";
		      final String password = "hmrAuctions";
		      String host = "smtp.gmail.com";
		      String port = "587";

		      System.out.println("to : "+to);
		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", port);

		      Session session = Session.getInstance(props,
		         new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		               return new PasswordAuthentication(username, password);
		            }
		         });

		      try {

		         Message message = new MimeMessage(session);
		         message.setFrom(new InternetAddress(from));
		         message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse(to));
		         message.setSubject("HMR Auctions : Your Private bid invite was approved");

		         MimeMultipart multipart = new MimeMultipart("related");
		         BodyPart messageBodyPart = new MimeBodyPart();
		         
		         String htmlText = EmailMessage.getPrivateBidInviteApproveEmailBidder( 
		         		AuctionId, AuctionName, AuctionDescription,
		        		BidderId, BidderFirstName, BidderLastName, BidderEmail
		        		);
		         
		         System.out.println("html "+htmlText);

		         messageBodyPart.setContent(htmlText, "text/html");
		         // add it
		         multipart.addBodyPart(messageBodyPart);
		         messageBodyPart = new MimeBodyPart();
		         DataSource fds3 = new FileDataSource(
		        		getSERVER_DIRECTORY()+"header_5.png");
		         messageBodyPart.setDataHandler(new DataHandler(fds3));
		         messageBodyPart.setHeader("Content-ID", "<image1>");

		         message.setContent(multipart);
		         Transport.send(message);

		         System.out.println("Sent message successfully....");

		      } catch (Exception e) {
		    	  System.out.println("Exception on Email "+e.getMessage());
		    	  e.printStackTrace();
		         throw new RuntimeException(e);
		      }
		   
	   }
	   
	   
	   public static void sendPrivateBidInviteRejectBidder(
			   String to, String cc,
			   String AuctionId, String AuctionName, String AuctionDescription,
			   String BidderId, String BidderFirstName, String BidderLastName, String BidderEmail
			   ) {
		   
		   	  String from = "noreplyhmrauctions@gmail.com";
		      final String username = "noreplyhmrauctions@gmail.com";
		      final String password = "hmrAuctions";
		      String host = "smtp.gmail.com";
		      String port = "587";

		      System.out.println("to : "+to);
		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", port);

		      Session session = Session.getInstance(props,
		         new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		               return new PasswordAuthentication(username, password);
		            }
		         });

		      try {

		         Message message = new MimeMessage(session);
		         message.setFrom(new InternetAddress(from));
		         message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse(to));
		         message.setSubject("HMR Auctions : Your Private bid invite was rejected");

		         MimeMultipart multipart = new MimeMultipart("related");
		         BodyPart messageBodyPart = new MimeBodyPart();
		         
		         String htmlText = EmailMessage.getPrivateBidInviteRejectEmailBidder( 
		         		AuctionId, AuctionName, AuctionDescription,
		        		BidderId, BidderFirstName, BidderLastName, BidderEmail
		        		);
		         
		         System.out.println("html "+htmlText);

		         messageBodyPart.setContent(htmlText, "text/html");
		         // add it
		         multipart.addBodyPart(messageBodyPart);
		         messageBodyPart = new MimeBodyPart();
		         DataSource fds3 = new FileDataSource(
		        		getSERVER_DIRECTORY()+"header_5.png");
		         messageBodyPart.setDataHandler(new DataHandler(fds3));
		         messageBodyPart.setHeader("Content-ID", "<image1>");

		         message.setContent(multipart);
		         Transport.send(message);

		         System.out.println("Sent message successfully....");

		      } catch (Exception e) {
		    	  System.out.println("Exception on Email "+e.getMessage());
		    	  e.printStackTrace();
		         throw new RuntimeException(e);
		      }
		   
	   }
	   
	   public static void sendNegotiatedBidEmailAdmin(
			   String to, String cc,
			   String AuctionId, String AuctionName, String AuctionDescription,
			   String LotId, String LotNumber, String LotName, String LotDescription,
			   String BidderId, String BidderFirstName, String BidderLastName, String BidderEmail,
			   String OfferAmount, String OfferNote) {
		   
		   	  String from = "noreplyhmrauctions@gmail.com";
		      final String username = "noreplyhmrauctions@gmail.com";
		      final String password = "hmrAuctions";
		      String host = "smtp.gmail.com";
		      String port = "587";

		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", port);

		      Session session = Session.getInstance(props,
		         new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		               return new PasswordAuthentication(username, password);
		            }
		         });

		      try {

		         Message message = new MimeMessage(session);
		         message.setFrom(new InternetAddress(from));
		         message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse(to));
		         message.setSubject("HMR Auctions : Negotiated Bid Admin Notification");

		         MimeMultipart multipart = new MimeMultipart("related");
		         BodyPart messageBodyPart = new MimeBodyPart();
		         
		         String htmlText = EmailMessage.getNegotiatedBidEmailAdmin( 
		         		AuctionId, AuctionName, AuctionDescription,
		        		LotId, LotNumber, LotName, LotDescription,
		        		BidderId, BidderFirstName, BidderLastName, BidderEmail,
		        		OfferAmount, OfferNote);
		         
		         System.out.println("html "+htmlText);

		         messageBodyPart.setContent(htmlText, "text/html");
		         // add it
		         multipart.addBodyPart(messageBodyPart);
		         messageBodyPart = new MimeBodyPart();
		         DataSource fds3 = new FileDataSource(
		        		getSERVER_DIRECTORY()+"header_5.png");
		         messageBodyPart.setDataHandler(new DataHandler(fds3));
		         messageBodyPart.setHeader("Content-ID", "<image1>");

		         message.setContent(multipart);
		         Transport.send(message);

		         System.out.println("Sent message successfully....");

		      } catch (Exception e) {
		    	  System.out.println("Exception on Email "+e.getMessage());
		         throw new RuntimeException(e);
		      }

	   }
	   
	   public static void sendNegotiatedBidEmailBidder(
			   String to, String cc,
			   String AuctionId, String AuctionName, String AuctionDescription,
			   String LotId, String LotNumber, String LotName, String LotDescription,
			   String BidderId, String BidderFirstName, String BidderLastName, String BidderEmail,
			   String OfferAmount, String OfferNote) {
		   
		   	  String from = "noreplyhmrauctions@gmail.com";
		      final String username = "noreplyhmrauctions@gmail.com";
		      final String password = "hmrAuctions";
		      String host = "smtp.gmail.com";
		      String port = "587";

		      System.out.println("to : "+to);
		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", port);

		      Session session = Session.getInstance(props,
		         new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		               return new PasswordAuthentication(username, password);
		            }
		         });

		      try {

		         Message message = new MimeMessage(session);
		         message.setFrom(new InternetAddress(from));
		         message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse(to));
		         message.setSubject("HMR Auctions : Negotiated Bid Bidder Notification");

		         MimeMultipart multipart = new MimeMultipart("related");
		         BodyPart messageBodyPart = new MimeBodyPart();
		         
		         String htmlText = EmailMessage.getNegotiatedBidEmailBidder( 
		         		AuctionId, AuctionName, AuctionDescription,
		        		LotId, LotNumber, LotName, LotDescription,
		        		BidderId, BidderFirstName, BidderLastName, BidderEmail,
		        		OfferAmount, OfferNote);
		         
		         System.out.println("html "+htmlText);

		         messageBodyPart.setContent(htmlText, "text/html");
		         // add it
		         multipart.addBodyPart(messageBodyPart);
		         messageBodyPart = new MimeBodyPart();
		         DataSource fds3 = new FileDataSource(
		        		getSERVER_DIRECTORY()+"header_5.png");
		         messageBodyPart.setDataHandler(new DataHandler(fds3));
		         messageBodyPart.setHeader("Content-ID", "<image1>");

		         message.setContent(multipart);
		         Transport.send(message);

		         System.out.println("Sent message successfully....");

		      } catch (Exception e) {
		    	  System.out.println("Exception on Email "+e.getMessage());
		    	  e.printStackTrace();
		         throw new RuntimeException(e);
		      }
		   
	   }
	   
	   
	   
	   
	   public static void sendNewPasswordEmailUser(String to, String cc, String first_name, String last_name, String newPassword){
			 
		      String from = "noreplyhmrauctions@gmail.com";
		      final String username = "noreplyhmrauctions@gmail.com";
		      final String password = "hmrAuctions";
		      String host = "smtp.gmail.com";
		      String port = "587";

		      Properties props = new Properties();
		      props.put("mail.smtp.auth", "true");
		      props.put("mail.smtp.starttls.enable", "true");
		      props.put("mail.smtp.host", host);
		      props.put("mail.smtp.port", port);

		      Session session = Session.getInstance(props,
		         new javax.mail.Authenticator() {
		            protected PasswordAuthentication getPasswordAuthentication() {
		               return new PasswordAuthentication(username, password);
		            }
		         });

		      try {

		         // Create a default MimeMessage object.
		         Message message = new MimeMessage(session);
		         
		         // Set From: header field of the header.
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         message.setRecipients(Message.RecipientType.TO,
		            InternetAddress.parse(to));
		         /*
		         message.setRecipients(Message.RecipientType.CC,
		 	            InternetAddress.parse(cc));
		         */
		         // Set Subject: header field
		         message.setSubject("HMR Auctions : Password Reset");

		         // This mail has 2 part, the BODY and the embedded image
		         MimeMultipart multipart = new MimeMultipart("related");

		         // first part (the html)
		         BodyPart messageBodyPart = new MimeBodyPart();
		         
		         String htmlText = EmailMessage.getNewPasswordEmailUser(first_name, last_name, newPassword);
		         
		         System.out.println("html "+htmlText);

		         messageBodyPart.setContent(htmlText, "text/html");
		         // add it
		         multipart.addBodyPart(messageBodyPart);

		         
		         /*
		         // second part (the image)
		         messageBodyPart = new MimeBodyPart();
		         DataSource fds1 = new FileDataSource(
		        		 getSERVER_DIRECTORY()+"mhi-logo.png");
		        // DataSource fds1 = new FileDataSource(
		 	     //       "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/GoogleAppsEmailHeader.png");
		         
		         
		         messageBodyPart.setDataHandler(new DataHandler(fds1));
		         messageBodyPart.setHeader("Content-ID", "<image1>");
		         multipart.addBodyPart(messageBodyPart);
		         
		         messageBodyPart = new MimeBodyPart();
		        DataSource fds2 = new FileDataSource(
		        		getSERVER_DIRECTORY()+"google-work-logo-white.png");
		        // DataSource fds2 = new FileDataSource(
		        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/cloud_com_ph.png");
		         messageBodyPart = new MimeBodyPart();
		         messageBodyPart.setDataHandler(new DataHandler(fds2));
		         messageBodyPart.setHeader("Content-ID", "<image2>");
		         multipart.addBodyPart(messageBodyPart);
		         */
		         
		         messageBodyPart = new MimeBodyPart();
		        DataSource fds3 = new FileDataSource(
		        		getSERVER_DIRECTORY()+"header_5.png");
		        // DataSource fds3 = new FileDataSource(
		        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
		         messageBodyPart.setDataHandler(new DataHandler(fds3));
		         messageBodyPart.setHeader("Content-ID", "<image1>");
		         multipart.addBodyPart(messageBodyPart);

		         // put everything together
		         message.setContent(multipart);
		         // Send message
		         //message.setContent(htmlText, "text/html");
		         Transport.send(message);

		         System.out.println("Sent message successfully....");

		      } catch (MessagingException e) {
		         throw new RuntimeException(e);
		      }
	   }
	   
	   
	   
	   
	public static String getSERVER_DIRECTORY() {
		return SERVER_DIRECTORY;
	}

	public static void setSERVER_DIRECTORY(String sERVER_DIRECTORY) {
		SERVER_DIRECTORY = sERVER_DIRECTORY;
	}

	public static String getPROTOCOL() {
		return PROTOCOL;
	}

	public static void setPROTOCOL(String pROTOCOL) {
		PROTOCOL = pROTOCOL;
	}
	
}
