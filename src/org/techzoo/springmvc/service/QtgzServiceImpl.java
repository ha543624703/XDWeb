package org.techzoo.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.techzoo.springmvc.dao.QtgzDao;
import org.techzoo.springmvc.form.Qtgz;

@Service
public class QtgzServiceImpl implements QtgzService {

	@Autowired 
	private QtgzDao qtgzDao;
	
	@Transactional
	public void addQtgz(Qtgz qtgz){
		qtgzDao.addQtgz(qtgz);
	}
	
	@Transactional
	public void updateQtgz(Qtgz qtgz){
		qtgzDao.updateQtgz(qtgz);
	}
	
	@Transactional
	public List<Qtgz> listQtgzs(){
		return qtgzDao.listQtgzs();
	}
	
	@Transactional
	public List<Qtgz> listQtgzs(String userName,int year,int month){
		return qtgzDao.listQtgzs(userName,year,month);
	}
	
	@Transactional
	public Qtgz getQtgzById(Integer qtgzId){
		return qtgzDao.getQtgzById(qtgzId);
	}
	
	@Transactional
	public void removeQtgz(Integer id){
		qtgzDao.removeQtgz(id);
	}
}
