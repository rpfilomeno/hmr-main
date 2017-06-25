package hmr.com.bean;

import java.math.BigDecimal;

public class AuctionUser extends SystemBean {

	BigDecimal id;
	BigDecimal auction_id;
	Integer user_id;
	Integer status;
	Integer active;
	
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getAuction_id() {
		return auction_id;
	}
	public void setAuction_id(BigDecimal auction_id) {
		this.auction_id = auction_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getActive() {
		return active;
	}
	public void setActive(Integer active) {
		this.active = active;
	}
	
}
