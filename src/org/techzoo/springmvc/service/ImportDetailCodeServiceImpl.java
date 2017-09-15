package org.techzoo.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.techzoo.springmvc.dao.ImportDetailCodeDao;
import org.techzoo.springmvc.dao.ImportDetailDao;
import org.techzoo.springmvc.form.ImportDetail;
import org.techzoo.springmvc.form.ImportDetailCode;
@Service
public class ImportDetailCodeServiceImpl implements ImportDetailCodeService{

	
	@Autowired 
	private ImportDetailCodeDao importDetailCodeDao;
	@Transactional
	public void addImportDetailCode(ImportDetailCode importDetail) {
		// TODO Auto-generated method stub
		importDetailCodeDao.addImportDetailCode(importDetail);
		
	}

	@Transactional
	public ImportDetailCode getImportDetailCodeById(String id) {
		// TODO Auto-generated method stub
		return importDetailCodeDao.getImportDetailCodeById(id);
	}

	@Transactional
	public List<ImportDetailCode> listImportDetailCode(String pkid) {
		// TODO Auto-generated method stub
		return importDetailCodeDao.listImportDetailCode(pkid);
	}

	@Transactional
	public void removeImportDetailCode(String id) {
		// TODO Auto-generated method stub
		importDetailCodeDao.removeImportDetailCode(id);
	}

	@Transactional
	public void updateImportDetailCode(ImportDetailCode importDetailCode) {
		// TODO Auto-generated method stub
		importDetailCodeDao.updateImportDetailCode(importDetailCode);
		 
		
	}


}
