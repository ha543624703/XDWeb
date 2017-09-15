package org.techzoo.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.techzoo.springmvc.dao.ArticleDao;

@Service
public class ActicleServiceImpl implements ActicleService {

	@Autowired
	private ArticleDao acticleDao;
	
	@Transactional
	public List listCount(String deptCode, String begDate, String endDate){
		return acticleDao.listCount(deptCode, begDate, endDate);
	}
	
	@Transactional
	public List listNewsAccount(){
		return acticleDao.listNewsAccount();
	}
}
