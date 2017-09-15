package org.techzoo.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.techzoo.springmvc.form.Importlog;

@Repository
public class ImportlogDaoImpl implements ImportlogDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addImportlog(Importlog importlog){
		Session session = sessionFactory.getCurrentSession();
		sessionFactory.getCurrentSession().save(importlog);
		session.createSQLQuery("delete from  "+importlog.getTableName()+
				" where ImportId in (select id from jxjs_import_log where year="+importlog.getYear()+" and month="+importlog.getMonth()+")").executeUpdate();
	}
	
	@Override
	public void updateImportlog(Importlog importlog){
		sessionFactory.getCurrentSession().update(importlog);
	}
	
	@Override
	public List<Importlog> listImportlogs(int year, int month){
		return sessionFactory.getCurrentSession()
		.createQuery("from Importlog b where b.year=:logyear and b.month=:logmonth")
		.setParameter("logyear", year).setParameter("logmonth", month)
		.list();
	}
	
	@Override
	public Importlog getImportlogById(String logId){
		Session session = sessionFactory.getCurrentSession();
		List<Importlog> list = session.createQuery("from Importlog b where b.id = :logId")
			.setParameter("logId", logId)
			.list();
		return list.size() > 0 ?(Importlog)list.get(0): null;
	}
	
	@Override
	public void removeImportlog(String logId){
		Importlog importlog = (Importlog)sessionFactory.getCurrentSession()
		.load(Importlog.class, logId);
		if(null != importlog) {
			sessionFactory.getCurrentSession().delete(importlog);
		}
	}
	
	@Override
	public void removeImport(String tableName,String logId){
		Session session = sessionFactory.getCurrentSession();
		session.createSQLQuery("delete from  "+tableName+" where ImportId='"+logId
				+"';update jxjs_import_log set XN='1' where id='"+logId+"'; ").executeUpdate();
		
	}
	
}
