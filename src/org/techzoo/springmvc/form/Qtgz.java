package org.techzoo.springmvc.form;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "jxjs_qtgz", schema = "dbo")
public class Qtgz {
	
	@Id
	@Column(name="ID")
	private String id;
	
	@Column(name="ImportId")
	private String importId;
	
	@Column(name="XH")
	private String xH;
	

	@Column(name="GH")
	private String gH;
	
	@Column(name="BM")
	private String bM;
	
	@Column(name="XM")
	private String xM;
	
	@Column(name="S1")
	private String s1;
	
	@Column(name="S2")
	private String s2;
	
	@Column(name="S3")
	private String s3;
	
	@Column(name="S4")
	private String s4;
	
	@Column(name="S5")
	private String s5;
	
	@Column(name="S6")
	private String s6;
	
	
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getImportId() {
		return importId;
	}

	public void setImportId(String importId) {
		this.importId = importId;
	}

	public String getXH() {
		return xH;
	}

	public void setXH(String xH) {
		this.xH = xH;
	}



	public String getGH() {
		return gH;
	}

	public void setGH(String gH) {
		this.gH = gH;
	}

	public String getBM() {
		return bM;
	}

	public void setBM(String bM) {
		this.bM = bM;
	}

	public String getXM() {
		return xM;
	}

	public void setXM(String xM) {
		this.xM = xM;
	}

	public String getS1() {
		return s1;
	}

	public void setS1(String s1) {
		this.s1 = s1;
	}

	public String getS2() {
		return s2;
	}

	public void setS2(String s2) {
		this.s2 = s2;
	}

	public String getS3() {
		return s3;
	}

	public void setS3(String s3) {
		this.s3 = s3;
	}

	public String getS4() {
		return s4;
	}

	public void setS4(String s4) {
		this.s4 = s4;
	}

	public String getS5() {
		return s5;
	}

	public void setS5(String s5) {
		this.s5 = s5;
	}

	public String getS6() {
		return s6;
	}

	public void setS6(String s6) {
		this.s6 = s6;
	}

	
	
}
