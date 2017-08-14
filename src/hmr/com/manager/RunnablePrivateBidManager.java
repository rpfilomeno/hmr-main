package hmr.com.manager;

import hmr.com.util.EmailUtil;

public class RunnablePrivateBidManager implements Runnable {
	   private Thread t;
	   private String threadName;
	   
	   private String to;
	   String cc;
	   String AuctionId;
	   String AuctionName;
	   String AuctionDescription;
	   String BidderId;
	   String BidderFirstName;
	   String BidderLastName;
	   String BidderEmail;
	   String CompanyIdNo;
	   
	   public RunnablePrivateBidManager(
			   String name,
			   String to, 
			   String cc,
			   String AuctionId, 
			   String AuctionName, 
			   String AuctionDescription,
			   String BidderId, 
			   String BidderFirstName, 
			   String BidderLastName, 
			   String BidderEmail,
			   String CompanyIdNo
			   ){
		   
		   threadName = name;
		   this.to =to;
		   this.cc=cc;
		   this.AuctionId=AuctionId; 
		   this.AuctionName=AuctionName; 
		   this.AuctionDescription=AuctionDescription; 
		   this.BidderId=BidderId; 
		   this.BidderFirstName=BidderFirstName; 
		   this.BidderLastName=BidderLastName; 
		   this.BidderEmail=BidderEmail; 
		   this.CompanyIdNo=CompanyIdNo;
		   
	       System.out.println("Creating " +  threadName );
	   }
	   
	   
	   public void run() {	
		      System.out.println("Running " +  threadName );
		      try {
		    	  if("HMR Auctions : Private Bid Accept Admin Notification".equals(threadName)){
		    		  EmailUtil.sendPrivateBidInviteApproveAdmin(to, cc, AuctionId, AuctionName, AuctionDescription, BidderId, BidderFirstName, BidderLastName, BidderEmail, CompanyIdNo);
		    	  }else if("HMR Auctions : Private Bid Accept User Notification".equals(threadName)){
		    		  EmailUtil.sendPrivateBidInviteApproveBidder(to, cc, AuctionId, AuctionName, AuctionDescription, BidderId, BidderFirstName, BidderLastName, BidderEmail);
		    	  }else if("HMR Auctions : Private Bid Reject User Notification".equals(threadName)){
		    		  EmailUtil.sendPrivateBidInviteRejectBidder(to, cc, AuctionId, AuctionName, AuctionDescription, BidderId, BidderFirstName, BidderLastName, BidderEmail);
		    	  } 
		      } catch (RuntimeException e) {
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