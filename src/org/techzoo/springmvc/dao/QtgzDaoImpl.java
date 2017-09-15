package org.techzoo.springmvc.dao;

import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.techzoo.springmvc.form.Jxgz;
import org.techzoo.springmvc.form.Qtgz;

@Repository
public class QtgzDaoImpl implements QtgzDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public void addQtgz(Qtgz qtgz){
		sessionFactory.getCurrentSession().save(qtgz);
	}
	
	@Override
	public void updateQtgz(Qtgz qtgz){
		sessionFactory.getCurrentSession().update(qtgz);
	}
	
	@Override
	public List<Qtgz> listQtgzs(){
		return sessionFactory.getCurrentSession()
		.createQuery("from Qtgz").list();
	}
	
	@Override
	public List<Qtgz> listQtgzs(String userName,int year,int month){
		StringBuffer sb=new StringBuffer();
		sb.append("select  a.* from jxjs_Qtgz a inner join jxjs_import_log b on a.ImportId=b.ID ");
		sb.append("where a.gh=?0 and b.year=?1 and b.month=?2 order by b.month");
		SQLQuery sqlQuery=createSQLQuery(sb.toString(),userName,year,month);
		sqlQuery.addEntity(Qtgz.class);
	
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
	public Qtgz getQtgzById(Integer qtgzId){
		Session session = sessionFactory.getCurrentSession();
		List<Qtgz> list = session.createQuery("from Qtgz b where b.id = :qtgzId")
			.setParameter("qtgzId", qtgzId)
			.list();
		return list.size() > 0 ?(Qtgz)list.get(0): null;
	}
	
	@Override
	public void removeQtgz(Integer id){
		Qtgz qtgz = (Qtgz)sessionFactory.getCurrentSession()
		.load(Qtgz.class, id);
		if(null != qtgz) {
			sessionFactory.getCurrentSession().delete(qtgz);
		}
	}
}
