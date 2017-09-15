package org.techzoo.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.techzoo.springmvc.form.ImportDetail;
import org.techzoo.springmvc.form.ImportMain;
@Repository
public class ImportDetailDaoImpl implements ImportDetailDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addImportDetail(ImportDetail importDetail) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		sessionFactory.getCurrentSession().save(importDetail);
	}

	@Override
	public ImportDetail getImportDetailById(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<ImportDetail> list = session.createQuery("from wage_ImportDetail b where b.id = :logId")
			.setParameter("logId", id)
			.list();
		return list.size() > 0 ?(ImportDetail)list.get(0): null;
	}

	@Override
	public List<ImportDetail> listImportDetail(String pkid) {
		// TODO Auto-generated method stub
		return sessionFactory.getCurrentSession()
		.createQuery("from wage_ImportDetail b where MainDetailID=:pkid")
		.setParameter("pkid", pkid)
		.list();
	}

	@Override
	public void removeImportDetail(String id) {
		// TODO Auto-generated method stub
		ImportDetail importlog = (ImportDetail)sessionFactory.getCurrentSession()
		.load(ImportDetail.class, id);
		if(null != importlog) {
			sessionFactory.getCurrentSession().delete(importlog);
		}
	}

	@Override
	public void updateImportDetail(ImportDetail importDetail) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(importDetail);
	}
	

}
