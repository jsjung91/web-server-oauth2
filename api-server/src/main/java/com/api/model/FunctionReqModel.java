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
	private String f_title;

	@Column(name = "f_kind")
	private String f_kind;

	@Column(name = "f_detail")
	private String f_detail;

	@Column(name = "f_regdate")
	@CreationTimestamp
	private Date f_regdate;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="m_id")
	@JsonBackReference
	private UserModel reqUserModel;

	public int getF_id() {
		return f_id;
	}

	public void setF_id(int f_id) {
		this.f_id = f_id;
	}

	public String getF_title() {
		return f_title;
	}

	public void setF_title(String f_title) {
		this.f_title = f_title;
	}

	public String getF_kind() {
		return f_kind;
	}

	public void setF_kind(String f_kind) {
		this.f_kind = f_kind;
	}

	public String getF_detail() {
		return f_detail;
	}

	public void setF_detail(String f_detail) {
		this.f_detail = f_detail;
	}

	public Date getF_regdate() {
		return f_regdate;
	}

	public void setF_regdate(Date f_regdate) {
		this.f_regdate = f_regdate;
	}

	public UserModel getReqUserModel() {
		return reqUserModel;
	}

	public void setReqUserModel(UserModel reqUserModel) {
		this.reqUserModel = reqUserModel;
	}

}
