package org.techzoo.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.techzoo.springmvc.dao.JxgzDao;
import org.techzoo.springmvc.form.Jtgz;
import org.techzoo.springmvc.form.Jxgz;

@Service
public class JxgzServiceImpl implements JxgzService {

	@Autowired 
	private JxgzDao jxgzDao;
	
	@Transactional
	public void addJxgz(Jxgz jxgz){
		jxgzDao.addJxgz(jxgz);
	}
	
	@Transactional
	public void updateJxgz(Jxgz jxgz){
		jxgzDao.updateJxgz(jxgz);
	}
	
	@Transactional
	public List<Jxgz> listJxgzs(){
		return jxgzDao.listJxgzs();
	}
	
	@Transactional
	public List<Jxgz> listJxgzs(String userName,int year,int month){
		return jxgzDao.listJxgzs(userName,year,month);
	}
	
	@Transactional
	public Jxgz getJxgzById(Integer jxgzId){
		return jxgzDao.getJxgzById(jxgzId);
	}
	
	@Transactional
	public void removeJxgz(Integer id){
		jxgzDao.removeJxgz(id);
	}
}
