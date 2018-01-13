package com.api.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name="member")
public class UserModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="m_id")
	private int m_id;

	@Column(name="m_lastname")
	private String m_lastname;

	@Column(name="m_firstname")
	private String m_firstname;

	@Column(name="m_country")
	private String m_country;

	@Column(name="m_email")
	private String m_email;

	@Column(name="m_pwd")
	private String m_pwd;

	@Column(name="m_regdate")
	@CreationTimestamp
	private Date m_regdate;
	
	@Column(name="m_enabled")
	private boolean enabled;

	public int getM_id() {
		return m_id;
	}

	public void setM_id(int m_id) {
		this.m_id = m_id;
	}

	public String getM_lastname() {
		return m_lastname;
	}

	public void setM_lastname(String m_lastname) {
		this.m_lastname = m_lastname;
	}

	public String getM_firstname() {
		return m_firstname;
	}

	public void setM_firstname(String m_firstname) {
		this.m_firstname = m_firstname;
	}

	public String getM_country() {
		return m_country;
	}

	public void setM_country(String m_country) {
		this.m_country = m_country;
	}

	public String getM_email() {
		return m_email;
	}

	public void setM_email(String m_email) {
		this.m_email = m_email;
	}

	public String getM_pwd() {
		return m_pwd;
	}

	public void setM_pwd(String m_pwd) {
		this.m_pwd = m_pwd;
	}

	public Date getM_regdate() {
		return m_regdate;
	}

	public void setM_regdate(Date m_regdate) {
		this.m_regdate = m_regdate;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
