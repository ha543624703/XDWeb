package org.techzoo.springmvc.form;

/**
 * @description:报修实体类
 * @projectName:jxhlwx
 * @className:RepairPresentation.java
 * @author:duzl
 * @createTime:2017年6月7日 下午2:24:19
 * @version 1.0
 */
public class RepairPresentation
{
	private long id;						// 主键
	private String classno = "";			// 报修人班级或教职工部门
	private String userid = "";				// 报修人登录的账号学号
	private String name = "";				// 报修人姓名
	private String phone = "";				// 报修人电话
	private String place = "";				// 报修场所
	private String buildingno = "";			// 楼栋号
	private String buildingdescribe = "";	// 楼栋描述
	private String hydropower = "";			// 水电类
	private String furniture = "";			// 家具类
	private String other = "";				// 其他类
	private String explaindetail = "";		// 故障说明
	private String picture = "";			// 图片
	private String repairtime = "";			// 报修日期
	private String createtime;				// 提交日期
	private String repairacceptance = "";	// 报修人签名
	private String repairedacceptance = "";	// 修好后工作人员签名
	private int status = 0;					// 报修状态：0-提交审核，1-审核通过，2-审核不通过

	public long getId()
	{
		return id;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public String getClassno()
	{
		return classno;
	}

	public void setClassno(String classno)
	{
		this.classno = classno;
	}

	public String getName()
	{
		return name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

	public String getPhone()
	{
		return phone;
	}

	public void setPhone(String phone)
	{
		this.phone = phone;
	}

	public String getPlace()
	{
		return place;
	}

	public void setPlace(String place)
	{
		this.place = place;
	}

	public String getBuildingno()
	{
		return buildingno;
	}

	public void setBuildingno(String buildingno)
	{
		this.buildingno = buildingno;
	}

	public String getBuildingdescribe()
	{
		return buildingdescribe;
	}

	public void setBuildingdescribe(String buildingdescribe)
	{
		this.buildingdescribe = buildingdescribe;
	}

	public String getHydropower()
	{
		return hydropower;
	}

	public void setHydropower(String hydropower)
	{
		this.hydropower = hydropower;
	}

	public String getFurniture()
	{
		return furniture;
	}

	public void setFurniture(String furniture)
	{
		this.furniture = furniture;
	}

	public String getOther()
	{
		return other;
	}

	public void setOther(String other)
	{
		this.other = other;
	}

	public String getPicture()
	{
		return picture;
	}

	public void setPicture(String picture)
	{
		this.picture = picture;
	}

	public String getRepairtime()
	{
		return repairtime;
	}

	public void setRepairtime(String repairtime)
	{
		this.repairtime = repairtime;
	}

	public String getRepairacceptance()
	{
		return repairacceptance;
	}

	public void setRepairacceptance(String repairacceptance)
	{
		this.repairacceptance = repairacceptance;
	}

	public String getRepairedacceptance()
	{
		return repairedacceptance;
	}

	public void setRepairedacceptance(String repairedacceptance)
	{
		this.repairedacceptance = repairedacceptance;
	}

	public String getUserid()
	{
		return userid;
	}

	public void setUserid(String userid)
	{
		this.userid = userid;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getCreatetime()
	{
		return createtime;
	}

	public void setCreatetime(String createtime)
	{
		this.createtime = createtime;
	}

	public String getExplaindetail()
	{
		return explaindetail;
	}

	public void setExplaindetail(String explaindetail)
	{
		this.explaindetail = explaindetail;
	}
}
