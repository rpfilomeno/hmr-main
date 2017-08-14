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
   
    
 
  
    
    public static String getMainEmail1(String first_name, String last_name, String login_link){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>IPC Google</title>");
    	sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://reg.cloud.com.ph/IPCGoogle/css/email.css\" />");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#03499A\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 15px; background-color: #0A5098; display:block!important;	max-width:600px!important;	margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:10px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#03499A\">");
    	sb.append("<table bgcolor=\"#03499A\" style=\"width: 100%;\">");
    	sb.append("<tr><td bgcolor=\"#03499A\"><img src=\"cid:image1\"> </td><td align=\"right\" bgcolor=\"#03499A\"><img src=\"cid:image2\"></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\">"+first_name + " "+ last_name +"</strong>, </h4>");
        sb.append(getMainContent1(login_link));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">IPC Google Solutions Team</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    
    public static String getMainEmail2(String first_name, String last_name, String login_link){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>IPC Google</title>");
    	sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://reg.cloud.com.ph/IPCGoogle/css/email.css\" />");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#03499A\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 15px; background-color: #0A5098; display:block!important;	max-width:600px!important;	margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:10px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#03499A\">");
    	sb.append("<table bgcolor=\"#03499A\" style=\"width: 100%;\">");
    	sb.append("<tr><td bgcolor=\"#03499A\"><img src=\"cid:image1\"> </td><td align=\"right\" bgcolor=\"#03499A\"><img src=\"cid:image2\"></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\">"+first_name + " "+ last_name +"</strong>, </h4>");
        sb.append(getMainContent2(login_link));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">IPC Google Solutions Team</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    public static String getMainEmail3(String c_first_name, String c_last_name, String a_first_name, String a_last_name, String login_link){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>IPC Google</title>");
    	sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://reg.cloud.com.ph/IPCGoogle/css/email.css\" />");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#03499A\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 15px; background-color: #0A5098; display:block!important;	max-width:600px!important;	margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:10px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#03499A\">");
    	sb.append("<table bgcolor=\"#03499A\" style=\"width: 100%;\">");
    	sb.append("<tr><td bgcolor=\"#03499A\"><img src=\"cid:image1\"> </td><td align=\"right\" bgcolor=\"#03499A\"><img src=\"cid:image2\"></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\">"+c_first_name + " "+ c_last_name +"</strong>, </h4>");
        sb.append(getMainContent3(a_first_name, a_last_name, login_link));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">IPC Google Solutions Team</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    
    public static String getMainEmail4(String first_name, String last_name, String login_link){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>IPC Google</title>");
    	sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://reg.cloud.com.ph/IPCGoogle/css/email.css\" />");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#03499A\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 15px; background-color: #0A5098; display:block!important;	max-width:600px!important;	margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:10px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#03499A\">");
    	sb.append("<table bgcolor=\"#03499A\" style=\"width: 100%;\">");
    	sb.append("<tr><td bgcolor=\"#03499A\"><img src=\"cid:image1\"> </td><td align=\"right\" bgcolor=\"#03499A\"><img src=\"cid:image2\"></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\">"+first_name + " "+ last_name +"</strong>, </h4>");
        sb.append(getMainContent4(login_link));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">IPC Google Solutions Team</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    public static String getMainEmail5(String c_first_name, String c_last_name, String a_first_name, String a_last_name, String login_link){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>IPC Google</title>");
    	sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://reg.cloud.com.ph/IPCGoogle/css/email.css\" />");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#03499A\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 15px; background-color: #0A5098; display:block!important;	max-width:600px!important;	margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:10px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#03499A\">");
    	sb.append("<table bgcolor=\"#03499A\" style=\"width: 100%;\">");
    	sb.append("<tr><td bgcolor=\"#03499A\"><img src=\"cid:image1\"> </td><td align=\"right\" bgcolor=\"#03499A\"><img src=\"cid:image2\"></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\">"+c_first_name + " "+ c_last_name +"</strong>, </h4>");
        sb.append(getMainContent5(a_first_name, a_last_name, login_link));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">IPC Google Solutions Team</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    public static String getMainEmailScheduledTraining(String first_name, String last_name, String login_link, String access_key, Integer calendar_id){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>IPC Google</title>");
    	sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://reg.cloud.com.ph/IPCGoogle/css/email.css\" />");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#03499A\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 15px; background-color: #0A5098; display:block!important;	max-width:600px!important;	margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:10px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#03499A\">");
    	sb.append("<table bgcolor=\"#03499A\" style=\"width: 100%;\">");
    	sb.append("<tr><td bgcolor=\"#03499A\"><img src=\"cid:image1\"> </td><td align=\"right\" bgcolor=\"#03499A\"><img src=\"cid:image2\"></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\">"+first_name + " "+ last_name +"</strong>, </h4>");
        sb.append(getMainContentScheduledTraining(login_link, access_key, calendar_id));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">IPC Google Solutions Team</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    
    public static String getMainEmailReScheduledTraining(String first_name, String last_name, String login_link, String access_key, Integer calendar_id){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>IPC Google</title>");
    	sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://reg.cloud.com.ph/IPCGoogle/css/email.css\" />");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#03499A\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 15px; background-color: #0A5098; display:block!important;	max-width:600px!important;	margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:10px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#03499A\">");
    	sb.append("<table bgcolor=\"#03499A\" style=\"width: 100%;\">");
    	sb.append("<tr><td bgcolor=\"#03499A\"><img src=\"cid:image1\"> </td><td align=\"right\" bgcolor=\"#03499A\"><img src=\"cid:image2\"></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\">"+first_name + " "+ last_name +"</strong>, </h4>");
        sb.append(getMainContentReScheduledTraining(login_link, access_key, calendar_id));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">IPC Google Solutions Team</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    
    public static String getMainEmailCancelTraining(String first_name, String last_name, String login_link, String access_key, Integer calendar_id){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>IPC Google</title>");
    	sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://reg.cloud.com.ph/IPCGoogle/css/email.css\" />");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#03499A\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 15px; background-color: #0A5098; display:block!important;	max-width:600px!important;	margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:10px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#03499A\">");
    	sb.append("<table bgcolor=\"#03499A\" style=\"width: 100%;\">");
    	sb.append("<tr><td bgcolor=\"#03499A\"><img src=\"cid:image1\"> </td><td align=\"right\" bgcolor=\"#03499A\"><img src=\"cid:image2\"></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\">"+first_name + " "+ last_name +"</strong>, </h4>");
        sb.append(getMainContentCancelTraining(login_link, access_key, calendar_id));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">IPC Google Solutions Team</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }


    public static String getMainEmailScheduledTraining_Admin(String first_name, String last_name, String comp_name){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>IPC Google</title>");
    	sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://reg.cloud.com.ph/IPCGoogle/css/email.css\" />");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#03499A\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 15px; background-color: #0A5098; display:block!important;	max-width:600px!important;	margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:10px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#03499A\">");
    	sb.append("<table bgcolor=\"#03499A\" style=\"width: 100%;\">");
    	sb.append("<tr><td bgcolor=\"#03499A\"><img src=\"cid:image1\"> </td><td align=\"right\" bgcolor=\"#03499A\"><img src=\"cid:image2\"></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\">"+comp_name +"</strong>, </h4>");
        sb.append(getMainContentScheduledTraining_Admin(first_name, last_name));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">IPC Google Solutions Team</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    
    
    public static String getMainEmailCancelTraining_Admin(String first_name, String last_name, String comp_name){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>IPC Google</title>");
    	sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://reg.cloud.com.ph/IPCGoogle/css/email.css\" />");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#03499A\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 15px; background-color: #0A5098; display:block!important;	max-width:600px!important;	margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:10px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#03499A\">");
    	sb.append("<table bgcolor=\"#03499A\" style=\"width: 100%;\">");
    	sb.append("<tr><td bgcolor=\"#03499A\"><img src=\"cid:image1\"> </td><td align=\"right\" bgcolor=\"#03499A\"><img src=\"cid:image2\"></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\">"+comp_name +"</strong>, </h4>");
        sb.append(getMainContentCancelTraining_Admin(first_name, last_name));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">IPC Google Solutions Team</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    
    public static String getMainEmailRescheduledTraining_Admin(String first_name, String last_name, String comp_name){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>IPC Google</title>");
    	sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://reg.cloud.com.ph/IPCGoogle/css/email.css\" />");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#03499A\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 15px; background-color: #0A5098; display:block!important;	max-width:600px!important;	margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:10px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#03499A\">");
    	sb.append("<table bgcolor=\"#03499A\" style=\"width: 100%;\">");
    	sb.append("<tr><td bgcolor=\"#03499A\"><img src=\"cid:image1\"> </td><td align=\"right\" bgcolor=\"#03499A\"><img src=\"cid:image2\"></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\">"+comp_name +"</strong>, </h4>");
        sb.append(getMainContentReScheduledTraining_Admin(first_name, last_name));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">IPC Google Solutions Team</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
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
        //sb.append("</td>");
        //sb.append("<td></td>");
        //sb.append("</tr>");
        //sb.append("</table>");
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
    
    
    
    
    public static String getMainEmailRequest(String first_name, String last_name, String login_link){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>MARCVENTURES</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#FFFFFF\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 0px; background-color: #FFFFFF; display:block!important; max-width:600px!important; margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:0px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#FFFFFF\">");
    	sb.append("<table bgcolor=\"#FFFFFF\" style=\"width: 100%;\">");
    	sb.append("<tr><td align=\"center\" bgcolor=\"#FFFFFF\"><img align=\"center\" src=\"cid:image1\" style=\"height:20%\" /> <img align=\"center\" src=\"cid:image2\" style=\"width:30%\" /></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\">"+first_name + " "+ last_name +"</strong>, </h4>");
        sb.append(getMainContentRequest(login_link));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">Marcventures Google Admin</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }

    public static String getMainEmailRequest(String first_name, String last_name, String login_link, String pass_word){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>MARCVENTURES</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#FFFFFF\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 0px; background-color: #FFFFFF; display:block!important; max-width:600px!important; margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:0px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#FFFFFF\">");
    	sb.append("<table bgcolor=\"#FFFFFF\" style=\"width: 100%;\">");
    	sb.append("<tr><td align=\"center\" bgcolor=\"#FFFFFF\"><img align=\"center\" src=\"cid:image1\" style=\"height:20%\" /> <img align=\"center\" src=\"cid:image2\" style=\"width:30%\" /></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\">"+first_name + " "+ last_name +"</strong>, </h4>");
        sb.append(getMainContentRequest(login_link));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">Marcventures Google Admin</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    
    
    public static String getMainEmailChangePassword(String first_name, String last_name, String login_link){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>MARCVENTURES</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#FFFFFF\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 0px; background-color: #FFFFFF; display:block!important; max-width:600px!important; margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:0px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#FFFFFF\">");
    	sb.append("<table bgcolor=\"#FFFFFF\" style=\"width: 100%;\">");
    	sb.append("<tr><td align=\"center\" bgcolor=\"#FFFFFF\"><img align=\"center\" src=\"cid:image1\" style=\"height:20%\" /> <img align=\"center\" src=\"cid:image2\" style=\"width:30%\" /></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\"> "+first_name +" "+ last_name+" </strong>, </h4>");
        sb.append(getMainContentChangePassword(login_link, first_name, last_name));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">Marcventures Google Admin</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    public static String getMainEmailChangePasswordAdmin(String first_name, String last_name, String login_link){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>MARCVENTURES</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#FFFFFF\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 0px; background-color: #FFFFFF; display:block!important; max-width:600px!important; margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:0px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#FFFFFF\">");
    	sb.append("<table bgcolor=\"#FFFFFF\" style=\"width: 100%;\">");
    	sb.append("<tr><td align=\"center\" bgcolor=\"#FFFFFF\"><img align=\"center\" src=\"cid:image1\" style=\"height:20%\" /> <img align=\"center\" src=\"cid:image2\" style=\"width:30%\" /></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\"> Google Admin </strong>, </h4>");
        sb.append(getMainContentChangePasswordAdmin(login_link, first_name, last_name));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">Marcventures Google Admin</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    public static String getMainEmailChangePassword(String first_name, String last_name, String login_link, String pass_word){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>MARCVENTURES</title>");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#FFFFFF\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 0px; background-color: #FFFFFF; display:block!important; max-width:600px!important; margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:0px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#FFFFFF\">");
    	sb.append("<table bgcolor=\"#FFFFFF\" style=\"width: 100%;\">");
    	sb.append("<tr><td align=\"center\" bgcolor=\"#FFFFFF\"><img align=\"center\" src=\"cid:image1\" style=\"height:20%\" /> <img align=\"center\" src=\"cid:image2\" style=\"width:30%\" /></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\"> "+first_name+ " "+last_name+" </strong>, </h4>");
        sb.append(getMainContentChangePassword(login_link, first_name, last_name, pass_word));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">Marcventures Google Admin</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }
    
    
    public static String getMainEmailTransferTraining_Admin(String comp_name, String old_first_name,  String old_last_name , String first_name, String last_name){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>IPC Google</title>");
    	sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://reg.cloud.com.ph/IPCGoogle/css/email.css\" />");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#03499A\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 15px; background-color: #0A5098; display:block!important;	max-width:600px!important;	margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:10px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#03499A\">");
    	sb.append("<table bgcolor=\"#03499A\" style=\"width: 100%;\">");
    	sb.append("<tr><td bgcolor=\"#03499A\"><img src=\"cid:image1\"> </td><td align=\"right\" bgcolor=\"#03499A\"><img src=\"cid:image2\"></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\">"+comp_name +"</strong>, </h4>");
        sb.append(getMainContentTransferTraining_Admin(old_first_name, old_last_name, first_name, last_name));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">IPC Google Solutions Team</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
        sb.append("</body>");
        sb.append("</html>");
        
    	return sb.toString();
    }

    

    
    public static String getMainEmailTransferTraining_OldAttendee(String old_a_first_name, String old_a_last_name, String a_first_name, String a_last_name, String comp_name){
    	StringBuilder sb = new StringBuilder();

    	sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
    	sb.append("<head>");
    	sb.append("<meta name=\"viewport\" content=\"width=device-width\" />");
    	sb.append("<meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\" />");
    	sb.append("<title>IPC Google</title>");
    	sb.append("<link rel=\"stylesheet\" type=\"text/css\" href=\"https://reg.cloud.com.ph/IPCGoogle/css/email.css\" />");
    	sb.append("</head>");
    	sb.append("<body bgcolor=\"#FFFFFF\">");
    	sb.append("<table style=\"width: 100%;\" bgcolor=\"#03499A\">");
    	sb.append("<tr><td></td>");
    	sb.append("<td style=\"padding: 15px; background-color: #0A5098; display:block!important;	max-width:600px!important;	margin:0 auto!important;clear:both!important\">");
    	sb.append("<div style=\"padding:10px;max-width:500px;margin:0 auto;display:block;\" bgcolor=\"#03499A\">");
    	sb.append("<table bgcolor=\"#03499A\" style=\"width: 100%;\">");
    	sb.append("<tr><td bgcolor=\"#03499A\"><img src=\"cid:image1\"> </td><td align=\"right\" bgcolor=\"#03499A\"><img src=\"cid:image2\"></td></tr>");
    	sb.append("</table>");
    	sb.append("</div>");
    	sb.append("</td>");
    	sb.append("<td></td></tr></table>");
        sb.append("<table style=\"width: 100%;\">");
        sb.append("<tr><td></td>");
        sb.append("<td style=\"	display:block!important;	max-width:600px!important;	margin:0 auto!important; clear:both!important;\" bgcolor=\"#FFFFFF\">");
        sb.append("<div style=\"	padding:15px;	max-width:600px;	margin:0 auto;	display:block; \">");
        sb.append("<table><tr><td style=\"font-family: 'Helvetica Neue', 'Helvetica', Helvetica, Arial, sans-serif;\">");
        sb.append("<p style=\"margin-bottom: 10px; \"><img src=\"cid:image3\"></p><br/>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\">Dear <strong style=\"color: #03499A\">"+comp_name +"</strong>, </h4>");
        sb.append(getMainContentTransferTraining_OldAttendee(a_first_name, a_last_name));
        sb.append("<br/><br/>");
        sb.append("<p style=\"font-size: 16px;\">Sincerely,</p>");
        sb.append("<h4 style=\"font-weight:500; font-size: 23px;\"><strong style=\"color: #03499A\">IPC Google Solutions Team</strong> </h4>");
        sb.append("<br/>");
        sb.append(getFooterContent());
        sb.append("</td></tr></table>");
        sb.append("</div>");
        sb.append("</td>");
        sb.append("<td></td>");
        sb.append("</tr>");
        sb.append("</table>");
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
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Please feel free to reach out to online-auction@hmrbid.com for questions or concerns.</p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you for choosing HMR Auctions.</p>"; 
  	   return body;
     }
    
    public static String getMainContentVerifyEmail(String login_link){
   	   
   	   String body = "<p style=\"margin-bottom: 10px;font-size: 12px;\">Good day!</p>";
 	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">You are one step from bidding. Click the button below to continue.</p>";
 	  	   body = body +"<p style=\"margin-bottom: 10px;font-size: 12px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">Verify Email</a></p>";
 	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Please feel free to reach out to online-auction@hmrbid.com for questions or concerns.</p>";
 	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Thank you for choosing HMR Auctions.</p>"; 
   	   return body;
      }
    
    
    public static String getMainContentRegisteredSuccessfulEmail(String login_link){
    	   
    	   String body = "<p style=\"margin-bottom: 10px;font-size: 12px;\">Good day!</p>";
  	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">You have successfully registered. Click button below to login.</p>";
  	  	   body = body +"<p style=\"margin-bottom: 10px;font-size: 12px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">Login</a></p>";
  	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Please feel free to reach out to online-auction@hmrbid.com for questions or concerns.</p>";
  	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Thank you for choosing HMR Auctions.</p>"; 
    	   return body;
       }
    
    public static String getMainContentPrivateRegistrationAccepted(){
 	   
 	   String body = "<p style=\"margin-bottom: 10px;font-size: 12px;\">Good day!</p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Your private bid request have been accepted.";
	  	  // body = body +"<p style=\"margin-bottom: 10px;font-size: 12px;\"><a href=\""+login_link+"\" style=\"	text-decoration:none;	color: #FFF;	background-color: #03499A;	padding:10px 16px;	font-weight:bold;	margin-right:10px;	text-align:center;	cursor:pointer;	display: inline-block;\">Login</a></p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Please feel free to reach out to online-auction@hmrbid.com for questions or concerns.</p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Thank you for choosing HMR Auctions.</p>"; 
 	   return body;
    }

    public static String getMainContentNewPasswordEmail(String newPassword){
 	   
 	   String body = "<p style=\"margin-bottom: 10px;font-size: 12px;\">Good day!</p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Please see your new password below.</p>";
	  	   body = body +"<p style=\"margin-bottom: 10px;font-size: 12px;\">New Password : "+newPassword+"</p>";
	  	   body = body + "<p style=\"margin-bottom: 10px;font-size: 12px;\">Please feel free to reach out to online-auction@hmrbid.com for questions or concerns.</p>";
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
    
    //Confirmation email to admin that transfer is successful
    public static String getMainContentTransferTraining_Admin(String old_a_first_name, String old_a_last_name, String a_first_name, String a_last_name){
   	   
   	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day! We are sending this email to inform you that you have successfully transferred "+old_a_first_name+" "+old_a_last_name+" training slot to "+a_first_name+" "+a_last_name+". The new nominee will be receiving an email notification to book his/her training schedule.</p>";
   	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Please feel free to reach out to your dedicated account manager for questions or concerns.</p>";
   	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you for choosing IP Converge Data Services Inc,.</p>";
   	   return body;
      }
    
    //Confirmation email to old attendee that transfer is successful
    public static String getMainContentTransferTraining_OldAttendee(String a_first_name, String a_last_name){
   	   
   	   String body = "<p style=\"margin-bottom: 10px;font-size: 16px;\">Good day! We are sending this email to inform you that your Google for Work Service Administrator transferred your training slot to "+a_first_name+" "+a_last_name+".</p>";
   	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Please feel free to reach out to your Google for Work Service Administrator for questions or concerns.</p>";
   	   body = body + "<p style=\"margin-bottom: 10px;font-size: 16px;\">Thank you for choosing IP Converge Data Services Inc,.</p>";
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
}