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
@Table(name = "instance")
public class ComputeModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "i_id")
	private int i_id;

	@Column(name = "i_teamname")
	private String i_teamname;

	@Column(name = "i_purpose")
	private String i_purpose;

	@Column(name = "i_cpu")
	private String i_cpu;

	@Column(name = "i_storage")
	private String i_storage;

	@Column(name = "i_os")
	private String i_os;

	@Column(name = "i_count")
	private String i_count;
	
	@Column(name = "i_enable")
	private String i_enable;

	@Column(name = "i_regdate")
	@CreationTimestamp
	private Date i_regdate;

	public int getI_id() {
		return i_id;
	}

	public void setI_id(int i_id) {
		this.i_id = i_id;
	}

	public String getI_teamname() {
		return i_teamname;
	}

	public void setI_teamname(String i_teamname) {
		this.i_teamname = i_teamname;
	}

	public String getI_purpose() {
		return i_purpose;
	}

	public void setI_purpose(String i_purpose) {
		this.i_purpose = i_purpose;
	}

	public String getI_cpu() {
		return i_cpu;
	}

	public void setI_cpu(String i_cpu) {
		this.i_cpu = i_cpu;
	}

	public String getI_storage() {
		return i_storage;
	}

	public void setI_storage(String i_storage) {
		this.i_storage = i_storage;
	}

	public String getI_os() {
		return i_os;
	}

	public void setI_os(String i_os) {
		this.i_os = i_os;
	}

	public String getI_count() {
		return i_count;
	}

	public void setI_count(String i_count) {
		this.i_count = i_count;
	}
	
	public String getI_enable() {
		return i_enable;
	}

	public void setI_enable(String i_enable) {
		this.i_enable = i_enable;
	}

	public Date getI_regdate() {
		return i_regdate;
	}

	public void setI_regdate(Date i_regdate) {
		this.i_regdate = i_regdate;
	}
}
