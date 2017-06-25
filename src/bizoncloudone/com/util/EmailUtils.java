package bizoncloudone.com.util;


//import ipc.ph.bean.User;
//import ipc.ph.dao.UserDao;

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

import bizoncloudone.com.bean.User;
import bizoncloudone.com.bean.UserLogin;
import bizoncloudone.com.dao.UserDao;
import bizoncloudone.com.dao.UserLoginDao;





public class EmailUtils {
	
	//private static String SERVER_DIRECTORY_LOCAL = "D:\\eclipse_workspace_IPC_GOOGLE\\IPCGS\\WebContent\\images\\"; 
	private static String SERVER_DIRECTORY_LOCAL = "D:\\eclipse_workspace_BIZ\\MV\\WebContent\\img\\"; 
	//private static String SERVER_DIRECTORY_ONLINE = "/opt/apache-tomcat-7.0.54/webapps/IPCGS/images/";
	private static String SERVER_DIRECTORY_ONLINE = "jvm//apache-tomcat-8.0.20//domains//bizoncloudone.com//MV//img//";
	
	/*
	static final String from_g = "andre.dacanay@bizoncloudone.com";
	static final String username_g = "andre.dacanay@bizoncloudone.com";//change accordingly
	static final String password_g = "andr3z3l";
	*/
	
	/*
	static final String from_g = "dakkiboy17@gmail.com";
	static final String username_g = "dakkiboy17@gmail.com";//change accordingly
	static final String password_g = "Andrezel6";
	*/
	/*
	static final String from_g = "acdacanay3@gmail.com";
	static final String username_g = "acdacanay3@gmail.com";//change accordingly
	static final String password_g = "Andrezel5";
	*/
/*
	 	static final String from_g = "test-superadmin@marcventures.com.ph";
	static final String username_g = "test-superadmin@marcventures.com.ph";//change accordingly
	static final String password_g = "Welcome1074";
	*/
 	static final String from_g = "customgoogleapplication@marcventures.com.ph";
static final String username_g = "customgoogleapplication@marcventures.com.ph";//change accordingly
static final String password_g = "Kungpow789123";
	
	
	
	 
	
	static String host_online = "https://bizoncloudone.com/MV";
	//static String host_online = "reg.cloud.com.ph";
	static String host_local = "http://localhost:8010";
	static String host = host_online;
	
	//private static String SERVER_DIRECTORY = SERVER_DIRECTORY_LOCAL;
	private static String SERVER_DIRECTORY = "";
	private static String PROTOCOL = "";
	
	
	  public static void main(String[] args) throws SQLException {
		  
		  
		  
		  //UserLoginDao uld = new UserLoginDao();
		  
		  //UserLogin u = uld.getUserLogin(1);
		  
		  setSERVER_DIRECTORY(SERVER_DIRECTORY_LOCAL);
		  String to = "dakkiboy17@gmail.com";
		  String cc = "dakkiboy17@gmail.com";
		  String comp_name = "Administrator";
		  String first_name = "";
		  String last_name = "ln";
		  
		  //to = u.getEmail_add();
		  
		  to = "dakkiboy17@gmail.com";
		  first_name = "Andre";
		  last_name = "Dacanay";
		  String access_key = "123";
		  /*
		  if(role_id.equals(admin)){
			  comp_name = "Administrator";
		  }else if(role_id.equals(am)){
			  comp_name = "Account Manager";
		  }else if(role_id.equals(cs)){
			  comp_name = "Customer Service";
		  }
		  
		    */
		  
		  //String am_first_name = "Andre AM";
		  //String am_last_name = "Dacanay AM";
		  
		  /*
		  
		  String login_link = host+"/controller?a=cp&ak="+access_key;
		  
		  System.out.println("login_link : "+login_link);
		  
		  sendEmailRequest(to,cc,comp_name,first_name,last_name,login_link);
		  
		  
		  */
		  
		  
		  
		  sendEmailUser(to,cc,comp_name,first_name,last_name,"asdfasd");
		  

	   }
	  
   public static void sendEmail1(String to, String cc, String comp_name, String first_name, String last_name, String am_first_name, String am_last_name, String login_link){
	 
	      String from = from_g;
	      final String username = username_g;
	      final String password = password_g;
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
	         
	         // Set To: header field of the header.
	         message.setRecipients(Message.RecipientType.CC,
	            InternetAddress.parse(cc));

	         // Set Subject: header field
	         message.setSubject("IPC Google : "+comp_name+" Training Nomination");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmail1(first_name, last_name,login_link);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"ipc-logo-white.png");
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
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image-1.png");
	        // DataSource fds3 = new FileDataSource(
	        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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
	
   public static void sendEmail2(String to, String comp_name, String first_name, String last_name, String login_link){
		 
	      String from = from_g;
	      final String username = username_g;
	      final String password = password_g;
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

	         // Set Subject: header field
	         message.setSubject("IPC Google : "+comp_name+" Nomination Confirmation");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmail2(first_name, last_name, login_link);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"ipc-logo-white.png");
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
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image-2.png");
	        // DataSource fds3 = new FileDataSource(
	        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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
   
   
   public static void sendEmail3(String to, String comp_name, String c_first_name, String c_last_name, String a_first_name, String a_last_name, String login_link){
		 
	      String from = "dakkiboy17@gmail.com";
	      final String username = "dakkiboy17@gmail.com";//change accordingly
	      final String password = "Andrezel6";
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

	         // Set Subject: header field
	         message.setSubject("IPC Google : "+comp_name+" "+a_first_name+" "+a_last_name+" Nomination Confirmation");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmail3(c_first_name, c_last_name, a_first_name, a_last_name, login_link);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"ipc-logo-white.png");
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
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image-3.png");
	        // DataSource fds3 = new FileDataSource(
	        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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
   
   public static void sendEmail4(String to, String comp_name, String first_name, String last_name, String login_link){
		 
	      String from = "dakkiboy17@gmail.com";
	      final String username = "dakkiboy17@gmail.com";//change accordingly
	      final String password = "Andrezel6";
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

	         // Set Subject: header field
	         message.setSubject("IPC Google : "+comp_name+" Booking Confirmation");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmail4(first_name, last_name, login_link);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"ipc-logo-white.png");
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
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image-4.png");
	        // DataSource fds3 = new FileDataSource(
	        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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
   
   public static void sendEmail5(String to, String comp_name, String c_first_name, String c_last_name, String a_first_name, String a_last_name, String login_link){
		 
	      String from = "dakkiboy17@gmail.com";
	      final String username = "dakkiboy17@gmail.com";//change accordingly
	      final String password = "Andrezel6";
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

	         // Set Subject: header field
	         message.setSubject("IPC Google : "+comp_name+" "+a_first_name+" "+a_last_name+" Booking Confirmation");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmail5(c_first_name, c_last_name, a_first_name, a_last_name, login_link);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"ipc-logo-white.png");
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
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image-5.png");
	        // DataSource fds3 = new FileDataSource(
	        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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
   
   
   public static void sendEmailScheduledTraining(String to, String comp_name, String first_name, String last_name, String login_link, String access_key, Integer calendar_id){
		 
	      String from = "dakkiboy17@gmail.com";
	      final String username = "dakkiboy17@gmail.com";//change accordingly
	      final String password = "Andrezel6";
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

	         // Set Subject: header field
	         message.setSubject("IPC Google : "+first_name+ " "+last_name+" of "+comp_name+" Scheduled Training");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmailScheduledTraining(first_name, last_name, login_link, access_key, calendar_id);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"ipc-logo-white.png");
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
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image-4.png");
	        // DataSource fds3 = new FileDataSource(
	        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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
   
   public static void sendEmailScheduledTraining_Admin(String to, String comp_name, String first_name, String last_name){
		 
	      String from = "dakkiboy17@gmail.com";
	      final String username = "dakkiboy17@gmail.com";//change accordingly
	      final String password = "Andrezel6";
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

	         // Set Subject: header field
	         message.setSubject("IPC Google : "+first_name+ " "+last_name+" of "+comp_name+" Scheduled Training");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmailScheduledTraining_Admin(first_name, last_name, comp_name);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"ipc-logo-white.png");
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
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image-4.png");
	        // DataSource fds3 = new FileDataSource(
	        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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


   
   public static void sendEmailReScheduledTraining_Admin(String to, String comp_name, String first_name, String last_name){
		 
	      String from = "dakkiboy17@gmail.com";
	      final String username = "dakkiboy17@gmail.com";//change accordingly
	      final String password = "Andrezel6";
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

	         // Set Subject: header field
	         message.setSubject("IPC Google : "+first_name+ " "+last_name+" of "+comp_name+" Rescheduled Training");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmailRescheduledTraining_Admin(first_name, last_name, comp_name);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"ipc-logo-white.png");
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
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image-4.png");
	        // DataSource fds3 = new FileDataSource(
	        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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

   
   public static void sendEmailCancelTraining_Admin(String to, String comp_name, String first_name, String last_name){
		 
	      String from = "dakkiboy17@gmail.com";
	      final String username = "dakkiboy17@gmail.com";//change accordingly
	      final String password = "Andrezel6";
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

	         // Set Subject: header field
	         message.setSubject("IPC Google : "+first_name+ " "+last_name+" of "+comp_name+" Cancelled Training");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmailCancelTraining_Admin(first_name, last_name, comp_name);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"ipc-logo-white.png");
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
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image-4.png");
	        // DataSource fds3 = new FileDataSource(
	        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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
   
   
   public static void sendEmailReScheduledTraining(String to, String comp_name, String first_name, String last_name, String login_link, String access_key, Integer calendar_id){
		 
	      String from = "dakkiboy17@gmail.com";
	      final String username = "dakkiboy17@gmail.com";//change accordingly
	      final String password = "Andrezel6";
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

	         // Set Subject: header field
	         message.setSubject("IPC Google : "+first_name+ " "+last_name+" of "+comp_name+" Scheduled Training");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmailReScheduledTraining(first_name, last_name, login_link, access_key, calendar_id);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"ipc-logo-white.png");
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
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image-4.png");
	        // DataSource fds3 = new FileDataSource(
	        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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
   
   
   public static void sendEmailTransferTraining_Admin(String to, String old_a_first_name, String old_a_last_name, String a_first_name, String a_last_name, String comp_name){
		 
	      String from = "dakkiboy17@gmail.com";
	      final String username = "dakkiboy17@gmail.com";//change accordingly
	      final String password = "Andrezel6";
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

	         // Set Subject: header field
	         message.setSubject("IPC Google : "+old_a_first_name+ " "+old_a_last_name+" of "+comp_name+" Transfer Training");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmailTransferTraining_Admin(comp_name, old_a_first_name, old_a_last_name, a_first_name, a_last_name);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"ipc-logo-white.png");
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
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image-4.png");
	        // DataSource fds3 = new FileDataSource(
	        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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
   
   
   public static void sendEmailTransferTraining_OldAttendee(String to, String old_a_first_name, String old_a_last_name, String a_first_name, String a_last_name, String comp_name){
		 
	      String from = "dakkiboy17@gmail.com";
	      final String username = "dakkiboy17@gmail.com";//change accordingly
	      final String password = "Andrezel6";
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

	         // Set Subject: header field
	         message.setSubject("IPC Google : "+old_a_first_name+ " "+old_a_last_name+" of "+comp_name+" Transfer Training");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmailTransferTraining_OldAttendee(old_a_first_name, old_a_last_name, a_first_name, a_last_name, comp_name);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"ipc-logo-white.png");
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
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image-4.png");
	        // DataSource fds3 = new FileDataSource(
	        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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

   
   
   
   public static void sendEmailCancelTraining(String to, String comp_name, String first_name, String last_name, String login_link, String access_key, Integer calendar_id){
		 
	      String from = "dakkiboy17@gmail.com";
	      final String username = "dakkiboy17@gmail.com";//change accordingly
	      final String password = "Andrezel6";
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

	         // Set Subject: header field
	         message.setSubject("IPC Google : "+first_name+ " "+last_name+" of "+comp_name+" Cancelled Training");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmailCancelTraining(first_name, last_name, login_link, access_key, calendar_id);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"ipc-logo-white.png");
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
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image-4.png");
	        // DataSource fds3 = new FileDataSource(
	        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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
   
   
   public static void sendEmailUser(String to, String cc, String comp_name, String first_name, String last_name, String login_link){
		 
	      String from = "dakkiboy17@gmail.com";
	      final String username = "dakkiboy17@gmail.com";//change accordingly
	      final String password = "Andrezel6";
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
	         
	         message.setRecipients(Message.RecipientType.CC,
	 	            InternetAddress.parse(cc));

	         // Set Subject: header field
	         message.setSubject("IPC Google : "+comp_name+" User Login Credentials");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmailUser(first_name, last_name,login_link);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

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
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image.png");
	        // DataSource fds3 = new FileDataSource(
	        //		 "/opt/apache-tomcat-7.0.54/webapps/google/WEB-INF/img/ipc.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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

   
   
   
   
   
   
   
   public static void sendEmailRequest(String to, String cc, String comp_name, String first_name, String last_name, String login_link){
		 
	      String from = from_g;
	      final String username = username_g;
	      final String password = password_g;
	      String host = "smtp.gmail.com";
	      //String host = "198.38.82.94";
	      	String port = "587";
	      //String port = "2525";
	      
	      System.out.println("username "+username);
	      System.out.println("password "+password);
	      
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", port);
	      
	      
	      /*
	      Properties props = new Properties();
	      props.put("mail.smtp.host", "smtp.gmail.com");
	      props.put("mail.smtp.socketFactory.port", "465");
	      props.put("mail.smtp.socketFactory.class",
	              "javax.net.ssl.SSLSocketFactory");
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.port", "465");
	      props.put("mail.transport.protocol", "smtp");
*/


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
	         
	         message.setRecipients(Message.RecipientType.CC,
	 	            InternetAddress.parse(cc));

	         // Set Subject: header field
	         message.setSubject("Marcventures Google Admin : Request to change password.");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmailRequest(first_name, last_name,login_link);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"mhi-logo.png");
	         
	        // DataSource fds1 = new FileDataSource(
	 	    //        "/home/bizadmin/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/MV/img/mhi-logo.png");
	         
	         messageBodyPart.setDataHandler(new DataHandler(fds1));
	         messageBodyPart.setHeader("Content-ID", "<image1>");
	         multipart.addBodyPart(messageBodyPart);
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds2 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"google-work-logo-white.png");
	        // DataSource fds2 = new FileDataSource(
	        //		 "/home/bizadmin/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/MV/img/google-work-logo-white.png");
	         messageBodyPart = new MimeBodyPart();
	         messageBodyPart.setDataHandler(new DataHandler(fds2));
	         messageBodyPart.setHeader("Content-ID", "<image2>");
	         multipart.addBodyPart(messageBodyPart);
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image.png");
	         /*
	         DataSource fds3 = new FileDataSource(
	        		 
	        		 "/home/bizadmin/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/MV/img/header-image.png");
	         
	         */
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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
   
   public static void sendEmailRequest(String to, String cc, String comp_name, String first_name, String last_name, String login_link, String pass_word){
		 
	      String from = from_g;
	      final String username = username_g;
	      final String password = password_g;
	      String host = "smtp.gmail.com";
	      //String host = "198.38.82.94";
	      	String port = "587";
	      //String port = "2525";
	      
	      System.out.println("username "+username);
	      System.out.println("password "+password);
	      
	      Properties props = new Properties();
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.starttls.enable", "true");
	      props.put("mail.smtp.host", host);
	      props.put("mail.smtp.port", port);
	      
	      
	      /*
	      Properties props = new Properties();
	      props.put("mail.smtp.host", "smtp.gmail.com");
	      props.put("mail.smtp.socketFactory.port", "465");
	      props.put("mail.smtp.socketFactory.class",
	              "javax.net.ssl.SSLSocketFactory");
	      props.put("mail.smtp.auth", "true");
	      props.put("mail.smtp.port", "465");
	      props.put("mail.transport.protocol", "smtp");
*/


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
	         
	         message.setRecipients(Message.RecipientType.CC,
	 	            InternetAddress.parse(cc));

	         // Set Subject: header field
	         message.setSubject("Marcventures Google Admin : Request to change password.");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmailRequest(first_name, last_name,login_link);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"mhi-logo.png");
	         
	        // DataSource fds1 = new FileDataSource(
	 	    //        "/home/bizadmin/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/MV/img/mhi-logo.png");
	         
	         messageBodyPart.setDataHandler(new DataHandler(fds1));
	         messageBodyPart.setHeader("Content-ID", "<image1>");
	         multipart.addBodyPart(messageBodyPart);
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds2 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"google-work-logo-white.png");
	        // DataSource fds2 = new FileDataSource(
	        //		 "/home/bizadmin/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/MV/img/google-work-logo-white.png");
	         messageBodyPart = new MimeBodyPart();
	         messageBodyPart.setDataHandler(new DataHandler(fds2));
	         messageBodyPart.setHeader("Content-ID", "<image2>");
	         multipart.addBodyPart(messageBodyPart);
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image.png");
	         /*
	         DataSource fds3 = new FileDataSource(
	        		 
	        		 "/home/bizadmin/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/MV/img/header-image.png");
	         
	         */
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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

   
   
   public static void sendEmailChangePassword(String to, String cc, String param, String first_name, String last_name, String login_link){
		 
	      String from = from_g;
	      final String username = username_g;
	      final String password = password_g;
	      String host = "smtp.gmail.com";
	      //String host = "198.38.82.94";
	      	String port = "587";
	      //String port = "2525";
	      
	      System.out.println("username "+username);
	      System.out.println("password "+password);
	      
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
	         
	         message.setRecipients(Message.RecipientType.CC,
	 	            InternetAddress.parse(cc));

	         // Set Subject: header field
	         message.setSubject("Marcventures Google Admin : Change Password Details for Request ID "+param+".");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmailChangePassword(first_name, last_name, login_link);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	      // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"mhi-logo.png");
	         
	         //DataSource fds1 = new FileDataSource(
	 	     //       "/home/bizadmin/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/MV/img/mhi-logo.png");
	         
	         messageBodyPart.setDataHandler(new DataHandler(fds1));
	         messageBodyPart.setHeader("Content-ID", "<image1>");
	         multipart.addBodyPart(messageBodyPart);
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds2 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"google-work-logo-white.png");
	         //DataSource fds2 = new FileDataSource(
	        //		 "/home/bizadmin/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/MV/img/google-work-logo-white.png");
	         messageBodyPart = new MimeBodyPart();
	         messageBodyPart.setDataHandler(new DataHandler(fds2));
	         messageBodyPart.setHeader("Content-ID", "<image2>");
	         multipart.addBodyPart(messageBodyPart);
	         
	         messageBodyPart = new MimeBodyPart();
	        DataSource fds3 = new FileDataSource(
	        		getSERVER_DIRECTORY()+"header-image.png");
	         //DataSource fds3 = new FileDataSource(
	        		 
	        //		 "/home/bizadmin/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/MV/img/header-image.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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
   
   
   //static String serverDirImg = "D:\\eclipse_workspace_BIZ\\MV\\img\\";
   
   public static void sendEmailChangePasswordwithPassword(String to, String cc, String param, String first_name, String last_name, String login_link, String pass_word){
		 
	      String from = from_g;
	      final String username = username_g;
	      final String password = password_g;
	      String host = "smtp.gmail.com";
	      //String host = "198.38.82.94";
	      	String port = "587";
	      //String port = "2525";
	      
	      System.out.println("username "+username);
	      System.out.println("password "+password);
	      
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
	         
	         message.setRecipients(Message.RecipientType.CC,
	 	            InternetAddress.parse(cc));

	         // Set Subject: header field
	         message.setSubject("Marcventures Google Admin : Change Password Details for Request ID "+param+".");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmailChangePassword(first_name, last_name, login_link, pass_word);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	      // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         //DataSource fds1 = new FileDataSource(
	        //		 getSERVER_DIRECTORY()+"mhi-logo.png");
	         /*
	         DataSource fds1 = new FileDataSource(
	 	            "/home/bizadmin/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/MV/img/mhi-logo.png");
	         */
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"mhi-logo.png");
	         
	         
	         messageBodyPart.setDataHandler(new DataHandler(fds1));
	         messageBodyPart.setHeader("Content-ID", "<image1>");
	         multipart.addBodyPart(messageBodyPart);
	         messageBodyPart = new MimeBodyPart();
	        //DataSource fds2 = new FileDataSource(
	        //		getSERVER_DIRECTORY()+"google-work-logo-white.png");
	         DataSource fds2 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"google-work-logo-white.png");
	         messageBodyPart = new MimeBodyPart();
	         messageBodyPart.setDataHandler(new DataHandler(fds2));
	         messageBodyPart.setHeader("Content-ID", "<image2>");
	         multipart.addBodyPart(messageBodyPart);
	         
	         messageBodyPart = new MimeBodyPart();
	        //DataSource fds3 = new FileDataSource(
	        //		getSERVER_DIRECTORY()+"header-image.png");
	         DataSource fds3 = new FileDataSource(
	        		 
	        		 getSERVER_DIRECTORY()+"header-image.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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
   
   public static void sendEmailChangePasswordwithPasswordAdmin(String to, String cc, String param, String first_name, String last_name, String login_link, String pass_word){
		 
	      String from = from_g;
	      final String username = username_g;
	      final String password = password_g;
	      String host = "smtp.gmail.com";
	      //String host = "198.38.82.94";
	      	String port = "587";
	      //String port = "2525";
	      
	      System.out.println("username "+username);
	      System.out.println("password "+password);
	      
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
	         
	         message.setRecipients(Message.RecipientType.CC,
	 	            InternetAddress.parse(cc));

	         // Set Subject: header field
	         message.setSubject("Marcventures Google Admin : Change Password Details for Request ID "+param+".");

	         // This mail has 2 part, the BODY and the embedded image
	         MimeMultipart multipart = new MimeMultipart("related");

	         // first part (the html)
	         BodyPart messageBodyPart = new MimeBodyPart();
	         
	         String htmlText = EmailMessages.getMainEmailChangePasswordAdmin(first_name, last_name, login_link);
	         
	         System.out.println("html "+htmlText);

	         messageBodyPart.setContent(htmlText, "text/html");
	         // add it
	         multipart.addBodyPart(messageBodyPart);

	         // second part (the image)
	      // second part (the image)
	         messageBodyPart = new MimeBodyPart();
	         //DataSource fds1 = new FileDataSource(
	        //		 getSERVER_DIRECTORY()+"mhi-logo.png");
	         /*
	         DataSource fds1 = new FileDataSource(
	 	            "/home/bizadmin/jvm/apache-tomcat-8.0.20/domains/bizoncloudone.com/MV/img/mhi-logo.png");
	         */
	         DataSource fds1 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"mhi-logo.png");
	         
	         
	         messageBodyPart.setDataHandler(new DataHandler(fds1));
	         messageBodyPart.setHeader("Content-ID", "<image1>");
	         multipart.addBodyPart(messageBodyPart);
	         messageBodyPart = new MimeBodyPart();
	        //DataSource fds2 = new FileDataSource(
	        //		getSERVER_DIRECTORY()+"google-work-logo-white.png");
	         DataSource fds2 = new FileDataSource(
	        		 getSERVER_DIRECTORY()+"google-work-logo-white.png");
	         messageBodyPart = new MimeBodyPart();
	         messageBodyPart.setDataHandler(new DataHandler(fds2));
	         messageBodyPart.setHeader("Content-ID", "<image2>");
	         multipart.addBodyPart(messageBodyPart);
	         
	         messageBodyPart = new MimeBodyPart();
	        //DataSource fds3 = new FileDataSource(
	        //		getSERVER_DIRECTORY()+"header-image.png");
	         DataSource fds3 = new FileDataSource(
	        		 
	        		 getSERVER_DIRECTORY()+"header-image.png");
	         messageBodyPart.setDataHandler(new DataHandler(fds3));
	         messageBodyPart.setHeader("Content-ID", "<image3>");
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