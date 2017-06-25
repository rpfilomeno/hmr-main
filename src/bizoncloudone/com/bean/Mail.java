package bizoncloudone.com.bean;

import java.util.Date;

public class Mail {

	String id;
	String m_label;
	String m_subject;
	String m_from;
	String m_to;
	String m_content;
	Integer m_attachments;
	String m_date_display;
	Date m_datetime;
	String m_msg;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getM_label() {
		return m_label;
	}
	public void setM_label(String m_label) {
		this.m_label = m_label;
	}
	public String getM_subject() {
		return m_subject;
	}
	public void setM_subject(String m_subject) {
		this.m_subject = m_subject;
	}
	public String getM_from() {
		return m_from;
	}
	public void setM_from(String m_from) {
		this.m_from = m_from;
	}
	public String getM_to() {
		return m_to;
	}
	public void setM_to(String m_to) {
		this.m_to = m_to;
	}
	public String getM_content() {
		return m_content;
	}
	public void setM_content(String m_content) {
		this.m_content = m_content;
	}
	public Integer getM_attachments() {
		return m_attachments;
	}
	public void setM_attachments(Integer m_attachments) {
		this.m_attachments = m_attachments;
	}
	public String getM_date_display() {
		return m_date_display;
	}
	public void setM_date_display(String m_date_display) {
		this.m_date_display = m_date_display;
	}
	public Date getM_datetime() {
		return m_datetime;
	}
	public void setM_datetime(Date m_datetime) {
		this.m_datetime = m_datetime;
	}
	public String getM_msg() {
		return m_msg;
	}
	public void setM_msg(String m_msg) {
		this.m_msg = m_msg;
	}

}
