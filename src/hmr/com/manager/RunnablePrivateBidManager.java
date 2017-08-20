package hmr.com.manager;

import java.math.BigDecimal;

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
		      System.out.println("Running " +  threadName+ " "+AuctionId );
		      try {
		    	  
				   if((AuctionId.equals("797") || AuctionId.equals("804")) && "HMR Auctions : Private Bid Reject User Notification".equals(threadName)){
					threadName = "HMR Auctions : Private Bid Reject User Notification - Auction 260";
					EmailUtil.sendPrivateBidInviteRejectBidder797(to, cc, AuctionId, AuctionName, AuctionDescription, BidderId, BidderFirstName, BidderLastName, BidderEmail);
				  } else if("HMR Auctions : Private Bid Accept Admin Notification".equals(threadName)){
		    		  EmailUtil.sendPrivateBidInviteApproveAdmin(to, cc, AuctionId, AuctionName, AuctionDescription, BidderId, BidderFirstName, BidderLastName, BidderEmail, CompanyIdNo);
				  }else if("HMR Auctions : Private Bid Accept User Notification".equals(threadName)){
		    		  //EmailUtil.sendPrivateBidInviteApproveBidder(to, cc, AuctionId, AuctionName, AuctionDescription, BidderId, BidderFirstName, BidderLastName, BidderEmail);
		    		  EmailUtil.sendPrivateRegistrationAccepted(to, cc, BidderFirstName, BidderLastName);
		    	  }else if("HMR Auctions : Private Bid Reject User Notification".equals(threadName)){
		    		  //EmailUtil.sendPrivateBidInviteRejectBidder(to, cc, AuctionId, AuctionName, AuctionDescription, BidderId, BidderFirstName, BidderLastName, BidderEmail);
		    		  EmailUtil.sendPrivateRegistrationRejected(to, cc, BidderFirstName, BidderLastName);
		    	  }else if("HMR Auctions : Private Bid Reject User Notification - Auction 260".equals(threadName)){
		    		  
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