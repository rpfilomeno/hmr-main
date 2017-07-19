package hmr.com.manager;

import hmr.com.util.EmailUtil;

public class RunnableNegotiatedBidManager implements Runnable {
	   private Thread t;
	   private String threadName;
	   
	   private String to;
	   String cc;
	   String AuctionId;
	   String AuctionName;
	   String AuctionDescription;
	   String LotId;
	   String LotNumber;
	   String LotName;
	   String LotDescription;
	   String BidderId;
	   String BidderFirstName;
	   String BidderLastName;
	   String BidderEmail;
	   String OfferAmount;
	   String OfferNote;
	   String Qty;
	   
	   public RunnableNegotiatedBidManager(
			   String name,
			   String to, String cc,
			   String AuctionId, String AuctionName, String AuctionDescription,
			   String LotId, String LotNumber, String LotName, String LotDescription,
			   String BidderId, String BidderFirstName, String BidderLastName, String BidderEmail,
			   String OfferAmount, String OfferNote, String Qty){
		   
		   threadName = name;
		   this.to =to;
		   this.cc=cc;
		   this.AuctionId=AuctionId; 
		   this.AuctionName=AuctionName; 
		   this.AuctionDescription=AuctionDescription; 
		   this.LotId=LotId; 
		   this.LotNumber=LotNumber;
		   this.LotName=LotName; 
		   this.LotDescription=LotDescription; 
		   this.BidderId=BidderId; 
		   this.BidderFirstName=BidderFirstName; 
		   this.BidderLastName=BidderLastName; 
		   this.BidderEmail=BidderEmail; 
		   this.OfferAmount=OfferAmount; 
		   this.OfferNote=OfferNote;
		   
	       System.out.println("Creating " +  threadName );
	   }
	   
	   
	   public void run() {	
		      System.out.println("Running " +  threadName );
		      try {
		    	  if("HMR Auctions : Negotiated Bid Admin Notification".equals(threadName)){
		    		  EmailUtil.sendNegotiatedBidEmailAdmin(to, cc, AuctionId, AuctionName, AuctionDescription, LotId, LotNumber, LotName, LotDescription, BidderId, BidderFirstName, BidderLastName, BidderEmail, OfferAmount, OfferNote);
		    	  }else if("HMR Auctions : Negotiated Bid User Notification".equals(threadName)){
		    		  EmailUtil.sendNegotiatedBidEmailBidder(to, cc, AuctionId, AuctionName, AuctionDescription, LotId, LotNumber, LotName, LotDescription, BidderId, BidderFirstName, BidderLastName, BidderEmail, OfferAmount, OfferNote);
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
