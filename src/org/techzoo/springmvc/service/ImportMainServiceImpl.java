package org.techzoo.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.techzoo.springmvc.dao.ImportMainDao;
import org.techzoo.springmvc.form.ImportMain;
@Service
public class ImportMainServiceImpl  implements ImportMainService{

	@Autowired 
	private ImportMainDao importMainDao;

	@Transactional
	public void addImportMain(ImportMain importMain) {
		// TODO Auto-generated method stub
		importMainDao.addImportMain(importMain);
		
	}

	@Transactional
	public ImportMain getImportMainById(String id) {
		// TODO Auto-generated method stub
		return importMainDao.getImportMainById(id);
	}

	@Transactional
	public List<ImportMain> listImportMain(String year, String month,String wageType) {
		// TODO Auto-generated method stub
		return importMainDao.listImportMain(year, month,wageType);
	}

	@Transactional
	public void removeImportMain(String id) {
		// TODO Auto-generated method stub
		importMainDao.removeImportMain(id);
		
	}

	@Transactional
	public void updateImportMain(ImportMain importMain) {
		// TODO Auto-generated method stub
		importMainDao.updateImportMain(importMain);
		
	}

	@Override
	public List SelectWageList(String pid) {
		// TODO Auto-generated method stub
		return importMainDao.SelectWageList(pid);
	}
	@Override
	public boolean isKL(String pid) {
		// TODO Auto-generated method stub
		return importMainDao.isKL(pid);
	}

	@Override
	public List SelectWageLitKL(String pid) {
		// TODO Auto-generated method stub
		return importMainDao.SelectWageLitKL(pid);
	}

	@Override
	public void ImportMianEnable(String id) {
		// TODO Auto-generated method stub
		importMainDao.ImportMianEnable(id);
	}

	@Override
	public List SelectWageList(String wageType, String userCode, String pWhre) {
		// TODO Auto-generated method stub
		return importMainDao.SelectWageList(wageType,userCode,pWhre);
	}

	@Override
	public List SelectWageList(String userCode, String year, String month,
			String wType) {
		// TODO Auto-generated method stub
		return importMainDao.SelectWageList(userCode,year,month,wType);
	}

	@Override
	public String getImportMainID(String wyear, String wMonth, String wageType) {
		// TODO Auto-generated method stub
		return importMainDao.getImportMainID(wyear,wMonth,wageType);
	}
	
}
