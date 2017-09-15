package org.techzoo.springmvc.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wage_ImportDetail", schema = "dbo")
public class ImportDetail {
	@Id
	@Column(name = "ID")
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMainDetailID() {
		return MainDetailID;
	}

	public void setMainDetailID(String mainDetailID) {
		MainDetailID = mainDetailID;
	}
	public String getUserCode() {
		return UserCode;
	}

	public void setUserCode(String userCode) {
		UserCode = userCode;
	}

	public String getUserName() {
		return UserName;
	}

	public void setUserName(String userName) {
		UserName = userName;
	}

	public String getCreateMan() {
		return CreateMan;
	}

	public void setCreateMan(String createMan) {
		CreateMan = createMan;
	}

	public Date getCreateDate() {
		return CreateDate;
	}

	public void setCreateDate(Date createDate) {
		CreateDate = createDate;
	}

	@Column(name = "MainDetailID")
	private String MainDetailID;

	@Column(name = "UserCode")
	private String UserCode;

	@Column(name = "UserName")
	private String UserName;

	@Column(name = "CreateMan")
	private String CreateMan;

	@Column(name = "CreateDate")
	private Date CreateDate;
	@Column(name="Snumber")
	private String snumber;
	
	public String getSnumber() {
		return snumber;
	}

	public void setSnumber(String snumber) {
		this.snumber = snumber;
	}

	public String getDepanrment() {
		return depanrment;
	}

	public void setDepanrment(String depanrment) {
		this.depanrment = depanrment;
	}

	@Column(name="Depanrment")
	private String depanrment;
	
}
