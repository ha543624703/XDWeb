package org.techzoo.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.techzoo.springmvc.form.ImportDetailCode;
@Repository
public class ImportDetailCodeDaoImpl implements ImportDetailCodeDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addImportDetailCode(ImportDetailCode importDetailCode) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		sessionFactory.getCurrentSession().save(importDetailCode);
	}

	@Override
	public ImportDetailCode getImportDetailCodeById(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<ImportDetailCode> list = session.createQuery("from wage_ImportDetailCode b where b.id = :logId")
			.setParameter("logId", id)
			.list();
		return list.size() > 0 ?(ImportDetailCode)list.get(0): null;
	}

	@Override
	public List<ImportDetailCode> listImportDetailCode(String pkid) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession()
		.createQuery("from wage_ImportDetailCode b where ImportDetailID=:pkid")
		.setParameter("pkid", pkid)
		.list();
	}

	@Override
	public void removeImportDetailCode(String id) {
		// TODO Auto-generated method stub
		ImportDetailCode importlog = (ImportDetailCode)sessionFactory.getCurrentSession()
		.load(ImportDetailCode.class, id);
		if(null != importlog) {
			sessionFactory.getCurrentSession().delete(importlog);
		}
	}

	@Override
	public void updateImportDetailCode(ImportDetailCode importDetailCode) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(importDetailCode);
	}
	
}
