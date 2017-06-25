package bizoncloudone.com.manager;


public class RunnableItemManager implements Runnable {
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

	   
	   RunnableItemManager( String name){
	       threadName = name;
	       System.out.println("Creating " +  threadName );
	   }
	   
	   
	   public void run() {	
	      System.out.println("Running " +  threadName );
	      try {

	    	  
				

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


