package hmr.com.bean;

import java.math.BigDecimal;
import java.sql.Timestamp;

public class AuctionStaging extends SystemBean {

	BigDecimal auction_id;
	BigDecimal auction_no;
	Timestamp auction_date;
	String location;
	BigDecimal default_premium;
	Timestamp last_date_sync;
	
	public BigDecimal getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(BigDecimal auction_id) {
		this.auction_id = auction_id;
	}
	public BigDecimal getAuction_no() {
		return auction_no;
	}
	public void setAuction_no(BigDecimal auction_no) {
		this.auction_no = auction_no;
	}
	public Timestamp getAuction_date() {
		return auction_date;
	}
	public void setAuction_date(Timestamp auction_date) {
		this.auction_date = auction_date;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public BigDecimal getDefault_premium() {
		return default_premium;
	}
	public void setDefault_premium(BigDecimal default_premium) {
		this.default_premium = default_premium;
	}
	public Timestamp getLast_date_sync() {
		return last_date_sync;
	}
	public void setLast_date_sync(Timestamp last_date_sync) {
		this.last_date_sync = last_date_sync;
	}

}
