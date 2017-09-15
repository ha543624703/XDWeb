package org.techzoo.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.techzoo.springmvc.dao.JtgzDao;
import org.techzoo.springmvc.form.Jtgz;

@Service
public class JtgzServiceImpl implements JtgzService {

	@Autowired 
	private JtgzDao jtgzDao;
	
	@Transactional
	public void addJtgz(Jtgz jtgz){
		jtgzDao.addJtgz(jtgz);
	}
	
	@Transactional
	public void updateJtgz(Jtgz jtgz){
		jtgzDao.updateJtgz(jtgz);
	}
	
	@Transactional
	public List<Jtgz> listJtgzs(){
		return jtgzDao.listJtgzs();
	}
	
	@Transactional
	public List<Jtgz> listJtgzs(String userName,int year,int month){
		return jtgzDao.listJtgzs(userName,year,month);
	}
	
	@Transactional
	public Jtgz getJtgzById(Integer jtgzId){
		return jtgzDao.getJtgzById(jtgzId);
	}
	
	@Transactional
	public void removeJtgz(Integer id){
		jtgzDao.removeJtgz(id);
	}
}
