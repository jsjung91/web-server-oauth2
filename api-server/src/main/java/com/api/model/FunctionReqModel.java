package com.api.model;

import java.io.Serializable;
import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "functionreq")
public class FunctionReqModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "f_id")
	private int f_id;

	@Column(name = "f_title")
	private String f_teamname;

	@Column(name = "f_kind")
	private String f_purpose;

	@Column(name = "f_detail")
	private String f_cpu;

	@Column(name = "f_regdate")
	@CreationTimestamp
	private Date f_regdate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="m_id")
	@JsonBackReference
	private UserModel userModel;

	public int getF_id() {
		return f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	public String getF_teamname() {
		return f_teamname;
	}

	public void setF_teamname(String f_teamname) {
		this.f_teamname = f_teamname;
	}

	public String getF_purpose() {
		return f_purpose;
	}

	public void setF_purpose(String f_purpose) {
		this.f_purpose = f_purpose;
	}

	public String getF_cpu() {
		return f_cpu;
	}

	public void setF_cpu(String f_cpu) {
		this.f_cpu = f_cpu;
	}

	public Date getF_regdate() {
		return f_regdate;
	}

	public void setF_regdate(Date f_regdate) {
		this.f_regdate = f_regdate;
	}

	public UserModel getUserModel() {
		return userModel;
	}

	public void setUserModel(UserModel userModel) {
		this.userModel = userModel;
	}
	
}
