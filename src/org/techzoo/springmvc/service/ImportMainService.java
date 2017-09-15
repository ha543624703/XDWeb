package org.techzoo.springmvc.service;

import java.util.List;

import org.techzoo.springmvc.form.ImportMain;

public interface ImportMainService {

	public void addImportMain(ImportMain importMain);
	public void updateImportMain(ImportMain importMain);
	public List<ImportMain> listImportMain(String year,String month,String wageType);
	public ImportMain getImportMainById(String id);
	public void removeImportMain(String id);
	public List SelectWageList(String pid);
	public boolean isKL(String pid);
	public List SelectWageLitKL(String pid);
	public void ImportMianEnable(String id);
	public List SelectWageList(String wageType,String userCode,String pWhre);
	public List SelectWageList(String userCode,String year,String month,String wType);
	public String getImportMainID(String wyear,String wMonth,String wageType);
	
}
