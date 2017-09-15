package org.techzoo.springmvc.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jxjs_import_log", schema = "dbo")
public class Importlog {

	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="TableName")
	private String tableName;

	@Column(name="DRSJ")
	private Date dRSJ;
	
	@Column(name="XN")
	private String xN;
	
	@Column(name="Year")
	private int year;
	
	@Column(name="Month")
	private int month;
	
	@Column(name="SM")
	private String sM;
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Date getdRSJ() {
		return dRSJ;
	}

	public void setdRSJ(Date dRSJ) {
		this.dRSJ = dRSJ;
	}

	public String getxN() {
		return xN;
	}

	public void setxN(String xN) {
		this.xN = xN;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}

	public String getsM() {
		return sM;
	}

	public void setsM(String sM) {
		this.sM = sM;
	}

}
