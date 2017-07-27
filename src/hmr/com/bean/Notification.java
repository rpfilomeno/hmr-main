package hmr.com.bean;

import java.math.BigDecimal;

public class Notification extends SystemBean {

	BigDecimal id;
	String subject;
	String body;
	Boolean is_auction;
	Boolean is_lot;
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	public Boolean getIs_auction() {
		return is_auction;
	}
	public void setIs_auction(Boolean is_auction) {
		this.is_auction = is_auction;
	}
	public Boolean getIs_lot() {
		return is_lot;
	}
	public void setIs_lot(Boolean is_lot) {
		this.is_lot = is_lot;
	}
}
