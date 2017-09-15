package org.techzoo.springmvc.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wage_userid", schema = "dbo")
public class WageUserId
{
	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "USERID")
	private String userid;

	@Column(name = "WAGESID")
	private String wagesid;

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public String getWagesid()
	{
		return wagesid;
	}

	public void setWagesid(String wagesid)
	{
		this.wagesid = wagesid;
	}

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}
}
