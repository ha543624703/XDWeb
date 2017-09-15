package org.techzoo.springmvc.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.techzoo.springmvc.form.Jbgz;
import org.techzoo.springmvc.form.Jtgz;

@Repository
public class JtgzDaoImpl implements JtgzDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addJtgz(Jtgz jtgz){
		sessionFactory.getCurrentSession().save(jtgz);
	}
	
	@Override
	public void updateJtgz(Jtgz jtgz){
		sessionFactory.getCurrentSession().update(jtgz);
	}
	
	@Override
	public List<Jtgz> listJtgzs(){
		return sessionFactory.getCurrentSession()
		.createQuery("from Jtgz").list();
	}
	
	@Override
	public List<Jtgz> listJtgzs(String userName,int year,int month){
		StringBuffer sb=new StringBuffer();
		sb.append("select  a.* from jxjs_Jtgz a inner join jxjs_import_log b on a.ImportId=b.ID ");
		sb.append("where a.gh=?0 and b.year=?1 and b.month=?2 order by b.month");
		SQLQuery sqlQuery=createSQLQuery(sb.toString(),userName,year,month);
		sqlQuery.addEntity(Jtgz.class);
	
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
	public Jtgz getJtgzById(Integer jtgzId){
		Session session = sessionFactory.getCurrentSession();
		List<Jtgz> list = session.createQuery("from Jtgz b where b.id = :jtgzId")
			.setParameter("jtgzId", jtgzId)
			.list();
		return list.size() > 0 ?(Jtgz)list.get(0): null;
	}
	
	@Override
	public void removeJtgz(Integer id){
		Jtgz jtgz = (Jtgz)sessionFactory.getCurrentSession()
		.load(Jtgz.class, id);
		if(null != jtgz) {
			sessionFactory.getCurrentSession().delete(jtgz);
		}
	}
}
