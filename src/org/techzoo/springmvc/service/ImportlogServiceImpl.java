package org.techzoo.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.techzoo.springmvc.dao.ImportlogDao;
import org.techzoo.springmvc.form.Importlog;

@Service
public class ImportlogServiceImpl implements ImportlogService{

	@Autowired 
	private ImportlogDao importlogDao;
	
	@Transactional
	public void addImportlog(Importlog importlog){
		importlogDao.addImportlog(importlog);
	}
	
	@Transactional
	public void updateImportlog(Importlog importlog){
		importlogDao.updateImportlog(importlog);
	}
	
	@Transactional
	public List<Importlog> listImportlogs(int year, int month){
		return importlogDao.listImportlogs(year,month);
	}
	
	@Transactional
	public Importlog getImportlogById(String logId){
		return importlogDao.getImportlogById(logId);
	}
	
	@Transactional
	public void removeImportlog(String logId){
		importlogDao.removeImportlog(logId);
	}
	
	@Transactional
	public void removeImport(String tableName,String logId){
		importlogDao.removeImport(tableName, logId);
	}
}
