package hmr.com.bean;

import java.math.BigDecimal;

public class NotificationUser extends SystemBean {

	BigDecimal id;
	BigDecimal notification_id;
	BigDecimal user_id;
	String email_add;
	Integer status;
	public BigDecimal getId() {
		return id;
	}
	public void setId(BigDecimal id) {
		this.id = id;
	}
	public BigDecimal getNotification_id() {
		return notification_id;
	}
	public void setNotification_id(BigDecimal notification_id) {
		this.notification_id = notification_id;
	}
	public BigDecimal getUser_id() {
		return user_id;
	}
	public void setUser_id(BigDecimal user_id) {
		this.user_id = user_id;
	}
	public String getEmail_add() {
		return email_add;
	}
	public void setEmail_add(String email_add) {
		this.email_add = email_add;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
}
