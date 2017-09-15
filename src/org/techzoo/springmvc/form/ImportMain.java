package org.techzoo.springmvc.form;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "wage_ImportMain", schema = "dbo")
public class ImportMain {

	@Id
	@Column(name = "ID")
	private String id;

	@Column(name = "WYear")
	private int WYear;

	@Column(name = "WMonth")
	private int WMonth;

	@Column(name = "WageType")
	private int WageType;

	@Column(name = "CreateMan")
	private String CreateMan;

	@Column(name = "CreateDate")
	private Date CreateDate;

	@Column(name = "IsEnable")
	private int IsEnable;

	@Column(name = "isDelete")
	private int IsDelete;

	@Column(name = "ImportCount")
	private int ImportCount;

	@Column(name = "WeekStart")
	private int WeekStart;

	@Column(name = "WeekEnd")
	private int WeekEnd;

	@Column(name = "YearTerm")
	private String YearTerm;

	@Column(name = "Remark")
	private String Remark;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getWYear() {
		return WYear;
	}

	public void setWYear(int wYear) {
		WYear = wYear;
	}

	public int getWMonth() {
		return WMonth;
	}

	public void setWMonth(int wMonth) {
		WMonth = wMonth;
	}

	public int getWageType() {
		return WageType;
	}

	public void setWageType(int wageType) {
		WageType = wageType;
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

	public int getIsEnable() {
		return IsEnable;
	}

	public void setIsEnable(int isEnable) {
		IsEnable = isEnable;
	}

	public int getIsDelete() {
		return IsDelete;
	}

	public void setIsDelete(int isDelete) {
		this.IsDelete = isDelete;
	}

	public int getImportCount() {
		return ImportCount;
	}

	public void setImportCount(int importCount) {
		ImportCount = importCount;
	}

	public int getWeekStart() {
		return WeekStart;
	}

	public void setWeekStart(int weekStart) {
		WeekStart = weekStart;
	}

	public int getWeekEnd() {
		return WeekEnd;
	}

	public void setWeekEnd(int weekEnd) {
		WeekEnd = weekEnd;
	}

	public String getYearTerm() {
		return YearTerm;
	}

	public void setYearTerm(String yearTerm) {
		YearTerm = yearTerm;
	}

	public String getRemark() {
		return Remark;
	}

	public void setRemark(String remark) {
		Remark = remark;
	}

}
