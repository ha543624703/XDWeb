package org.techzoo.springmvc.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.techzoo.springmvc.form.Jtgz;
import org.techzoo.springmvc.form.Jxgz;

@Repository
public class JxgzDaoImpl implements JxgzDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addJxgz(Jxgz jxgz){
		sessionFactory.getCurrentSession().save(jxgz);
	}
	
	@Override
	public void updateJxgz(Jxgz jxgz){
		sessionFactory.getCurrentSession().update(jxgz);
	}
	
	@Override
	public List<Jxgz> listJxgzs(){
		return sessionFactory.getCurrentSession()
		.createQuery("from Jxgz").list();
	}
	
	@Override
	public List<Jxgz> listJxgzs(String userName,int year,int month){
		StringBuffer sb=new StringBuffer();
		sb.append("select  a.* from jxjs_Jxgz a inner join jxjs_import_log b on a.ImportId=b.ID ");
		sb.append("where a.gh=?0 and b.year=?1 and b.month=?2 order by b.month");
		SQLQuery sqlQuery=createSQLQuery(sb.toString(),userName,year,month);
		sqlQuery.addEntity(Jxgz.class);
	
		return sqlQuery.list();
	}
	
	public SQLQuery createSQLQuery(final String queryString, final Object... values){
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(queryString);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				sqlQuery.setParameter(String.valueOf(i), values[i]);
			}
		}
		return sqlQuery;
	}
	
	@Override
	public Jxgz getJxgzById(Integer jxgzId){
		Session session = sessionFactory.getCurrentSession();
		List<Jxgz> list = session.createQuery("from Jxgz b where b.id = :jxgzId")
			.setParameter("jxgzId", jxgzId)
			.list();
		return list.size() > 0 ?(Jxgz)list.get(0): null;
	}
	
	@Override
	public void removeJxgz(Integer id){
		Jxgz jxgz = (Jxgz)sessionFactory.getCurrentSession()
		.load(Jxgz.class, id);
		if(null != jxgz) {
			sessionFactory.getCurrentSession().delete(jxgz);
		}
	}
}
