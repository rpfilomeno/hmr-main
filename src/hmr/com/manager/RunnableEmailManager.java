package hmr.com.manager;

import hmr.com.util.EmailUtils;
import hmr.com.util.EmailUtil;

public class RunnableEmailManager implements Runnable {
	   private Thread t;
	   private String threadName;
	   
	   String SERVER_DIRECTORY;
	   String email_add_to; 
	   String email_add_cc; 
	   String comp_name; 
	   String first_name; 
	   String last_name;
	   String login_link;
	   String pass_word;
	   String mobile_no;
	   String access_key;
	   String sMsg;
	   Integer verify_code;
	   String admin_mobile_no;
	   Integer id;

	   public RunnableEmailManager( String name, String SERVER_DIRECTORY, String email_add_to, String email_add_cc, String comp_name, String first_name, String last_name, String login_link){
	       threadName = name;
	       this.SERVER_DIRECTORY = SERVER_DIRECTORY;
	       this.email_add_to = email_add_to; 
	       this.email_add_cc = email_add_cc; 
	       this.comp_name = comp_name; 
	       this.first_name = first_name; 
	       this.last_name = last_name;
		   this.login_link = login_link;
	       
	       System.out.println("Creating " +  threadName );
	   }
	   

	   
	   public RunnableEmailManager( String name, String SERVER_DIRECTORY, String email_add_to, String email_add_cc, String comp_name, String first_name, String last_name, String login_link, String mobile_no, String sMsg){
	       threadName = name;
	       this.SERVER_DIRECTORY = SERVER_DIRECTORY;
	       this.email_add_to = email_add_to; 
	       this.email_add_cc = email_add_cc; 
	       this.comp_name = comp_name; 
	       this.first_name = first_name; 
	       this.last_name = last_name;
		   this.login_link = login_link;
		   this.mobile_no = mobile_no;
		   this.sMsg = sMsg;

	       System.out.println("Creating " +  threadName );
	   }
	   
	   public RunnableEmailManager( String name, String SERVER_DIRECTORY, String email_add_to, String email_add_cc, String comp_name, String first_name, String last_name, String login_link, String mobile_no, String sMsg, Integer verify_code, String admin_mobile_no){
	       threadName = name;
	       this.SERVER_DIRECTORY = SERVER_DIRECTORY;
	       this.email_add_to = email_add_to; 
	       this.email_add_cc = email_add_cc; 
	       this.comp_name = comp_name; 
	       this.first_name = first_name; 
	       this.last_name = last_name;
		   this.login_link = login_link;
		   this.mobile_no = mobile_no;
		   this.sMsg = sMsg;
		   this.verify_code = verify_code;
		   this.admin_mobile_no = admin_mobile_no;

	       System.out.println("Creating " +  threadName );
	   }
	   
	   public RunnableEmailManager( String name, String SERVER_DIRECTORY, String email_add_to, String email_add_cc, String comp_name, String first_name, String last_name, String login_link, String mobile_no, String sMsg, Integer verify_code, String admin_mobile_no, Integer id){
	       threadName = name;
	       this.SERVER_DIRECTORY = SERVER_DIRECTORY;
	       this.email_add_to = email_add_to; 
	       this.email_add_cc = email_add_cc; 
	       this.comp_name = comp_name; 
	       this.first_name = first_name; 
	       this.last_name = last_name;
		   this.login_link = login_link;
		   this.mobile_no = mobile_no;
		   this.sMsg = sMsg;
		   this.verify_code = verify_code;
		   this.admin_mobile_no = admin_mobile_no;
		   this.id = id;

	       System.out.println("Creating " +  threadName );
	   }
	   
	   public RunnableEmailManager( String name, String SERVER_DIRECTORY, String email_add_to, String email_add_cc, String comp_name, String first_name, String last_name, String login_link, String pass_word, Integer id){
	       threadName = name;
	       this.SERVER_DIRECTORY = SERVER_DIRECTORY;
	       this.email_add_to = email_add_to; 
	       this.email_add_cc = email_add_cc; 
	       this.comp_name = comp_name; 
	       this.first_name = first_name; 
	       this.last_name = last_name;
		   this.login_link = login_link;
		   this.pass_word = pass_word;
		   this.id = id;
	       
	       System.out.println("Creating " +  threadName );
	   }
	   

	   public RunnableEmailManager( String name, String SERVER_DIRECTORY, String email_add_to, String email_add_cc, String comp_name, String first_name, String last_name, String login_link, String pass_word){
	       threadName = name;
	       this.SERVER_DIRECTORY = SERVER_DIRECTORY;
	       this.email_add_to = email_add_to; 
	       this.email_add_cc = email_add_cc; 
	       this.comp_name = comp_name; 
	       this.first_name = first_name; 
	       this.last_name = last_name;
		   this.login_link = login_link;
		   this.pass_word = pass_word;
		   this.id = id;
	       
	       System.out.println("Creating " +  threadName );
	   }
	   
	   
	   RunnableEmailManager( String name){
	       threadName = name;
	       System.out.println("Creating " +  threadName );
	   }
	   
	   
	   public RunnableEmailManager(String name, String SERVER_DIRECTORY, String userId, String ADMIN_EMAIL_ADD_CC, String firstName, String lastName, String login_link){
	       threadName = name;
	       this.SERVER_DIRECTORY = SERVER_DIRECTORY;
	       this.email_add_to = userId; 
	       this.email_add_cc = ADMIN_EMAIL_ADD_CC;  
	       this.first_name = firstName; 
	       this.last_name = lastName;
		   this.login_link = login_link;

	       System.out.println("Creating " +  threadName );
	   }
	   /*
	   public RunnableEmailManager(String name, String SERVER_DIRECTORY, String userId, String ADMIN_EMAIL_ADD_CC, String firstName, String lastName, String newPassword){
	       threadName = name;
	       this.SERVER_DIRECTORY = SERVER_DIRECTORY;
	       this.email_add_to = userId; 
	       this.email_add_cc = ADMIN_EMAIL_ADD_CC;  
	       this.first_name = firstName; 
	       this.last_name = lastName;
		   this.login_link = login_link;

	       System.out.println("Creating " +  threadName );
	   }
	   */
	   public void run() {	
	      System.out.println("Running " +  threadName );
	      try {

	    	  
				EmailUtils.setSERVER_DIRECTORY(SERVER_DIRECTORY);
				
				if("HMR Auctions : Private Registration Accepted".equals(threadName)){
					
					EmailUtil.sendPrivateRegistrationAccepted(email_add_to, email_add_cc, first_name, last_name);
				
				}else if("HMR Auctions : Activation".equals(threadName)){
					
					EmailUtil.sendRegisteredSuccessfulEmailUser(email_add_to, email_add_cc, first_name, last_name, login_link);

				}else if("HMR Auctions : Password Reset Successful".equals(threadName)){
					
					EmailUtil.sendNewPasswordEmailUser(email_add_to, email_add_cc, first_name, last_name, login_link);
				
				}else if("HMR Auctions : Registration Successful".equals(threadName)){
					
					EmailUtil.sendVerifyEmailUser(email_add_to, email_add_cc, first_name, last_name, login_link);

				}else if ("sendEmailChangePassword".equals(threadName)){
					
					sMsg = "MARCVENTURES Google Admin successfully changed your Google Password.";

					
					EmailUtils.sendEmailChangePassword(email_add_to, email_add_cc,comp_name, first_name, last_name, login_link);
					
					sMsg = "MARCVENTURES Google Admin successfully changed your Google Password.";
					
					//samplecode sms = new samplecode();
					
					//sms.sendSMSOW(mobile_no, sMsg);
					
				}else if ("sendEmailChangePasswordwithPassword".equals(threadName)){
					
					EmailUtils.sendEmailChangePasswordwithPassword(email_add_to, email_add_cc,comp_name, first_name, last_name, login_link, pass_word);
					
					sMsg = "MARCVENTURES Google Admin successfully changed your Google Password to "+pass_word;
					
					System.out.println("sMsg "+sMsg);
					
					//RequestManager rMngr = new RequestManager();
					
					///rMngr.updateRequestSMS(id, sMsg, "sms_msg");
					
					//sms.sendSMSOW(mobile_no, sMsg);
				}else if ("sendEmailChangePasswordwithPasswordAdmin".equals(threadName)){
					
					EmailUtils.sendEmailChangePasswordwithPasswordAdmin(email_add_to, email_add_cc,comp_name, first_name, last_name, login_link, pass_word);
					
					sMsg = "MARCVENTURES Google Admin successfully changed your Google Password to "+pass_word;
					
					//RequestManager rMngr = new RequestManager();
					
					//rMngr.updateRequestSMS(id, sMsg, "sms_msg");
				}
				

	            Thread.sleep(1);
	            
	     } catch (InterruptedException e) {
	         System.out.println("Thread " +  threadName + " interrupted.");
	     }
	     System.out.println("Thread " +  threadName + " exiting.");
	   }
	   
	   public void start ()
	   {
	      System.out.println("Starting " +  threadName );
	      if (t == null)
	      {
	         t = new Thread (this, threadName);
	         t.start ();
	      }
	   }

	}


