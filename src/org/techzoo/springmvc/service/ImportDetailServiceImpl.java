package org.techzoo.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.techzoo.springmvc.dao.BookDao;
import org.techzoo.springmvc.dao.ImportDetailDao;
import org.techzoo.springmvc.form.ImportDetail;
@Service
public class ImportDetailServiceImpl implements ImportDetailService{

	
	@Autowired 
	private ImportDetailDao importDetailDao;
	@Transactional
	public void addImportDetail(ImportDetail importDetail) {
		// TODO Auto-generated method stub
		importDetailDao.addImportDetail(importDetail);
		
	}

	@Transactional
	public ImportDetail getImportDetailById(String id) {
		// TODO Auto-generated method stub
		return importDetailDao.getImportDetailById(id);
	}

	@Transactional
	public List<ImportDetail> listImportDetail(String pkid) {
		// TODO Auto-generated method stub
		return importDetailDao.listImportDetail(pkid);
	}

	@Transactional
	public void removeImportDetail(String id) {
		// TODO Auto-generated method stub
		importDetailDao.removeImportDetail(id);
	}

	@Transactional
	public void updateImportDetail(ImportDetail importDetail) {
		// TODO Auto-generated method stub
		importDetailDao.updateImportDetail(importDetail);
		 
		
	}

}
