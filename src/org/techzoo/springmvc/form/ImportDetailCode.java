package org.techzoo.springmvc.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wage_ImportDetailCode", schema = "dbo")
public class ImportDetailCode {
	@Id
	@Column(name = "ID")
	private String id;
	@Column(name = "TitleName")
	private String titleName;
	@Column(name = "TitleValue")
	private String titleValue;
	@Column(name = "Pid")
	private String pid;
	@Column(name = "ImportDetailID")
	private String importDetailID;
	@Column(name = "wOrderby")
	private int wOrderby;
	public int getwOrderby() {
		return wOrderby;
	}

	public void setwOrderby(int wOrderby) {
		this.wOrderby = wOrderby;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}

	public String getTitleValue() {
		return titleValue;
	}

	public void setTitleValue(String titleValue) {
		this.titleValue = titleValue;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getImportDetailID() {
		return importDetailID;
	}

	public void setImportDetailID(String importDetailID) {
		this.importDetailID = importDetailID;
	}
}
