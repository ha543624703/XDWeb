package org.techzoo.springmvc.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.techzoo.springmvc.form.Jbgz;

@Repository
public class JbgzDaoImpl implements JbgzDao
{
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addJbgz(Jbgz jbgz)
	{
		sessionFactory.getCurrentSession().save(jbgz);
	}

	@Override
	public void updateJbgz(Jbgz jbgz)
	{
		sessionFactory.getCurrentSession().update(jbgz);
	}

	@Override
	public List<Jbgz> listJbgzs()
	{
		return sessionFactory.getCurrentSession().createQuery("from Jbgz").list();
	}

	@Override
	public List<Jbgz> listJbgzs(String userName, int year, int month)
	{
		StringBuffer sb = new StringBuffer();
		sb.append("select  a.* from jxjs_Jbgz a inner join jxjs_import_log b on a.ImportId=b.ID ");
		sb.append("where a.gh=?0 and b.year=?1 and b.month=?2 order by b.month");

		SQLQuery sqlQuery = createSQLQuery(sb.toString(), userName, year, month);
		sqlQuery.addEntity(Jbgz.class);

		return sqlQuery.list();
	}

	@Override
	public List<Jbgz> listJbgzByYear(String userName, int year)
	{
		String sb = "SELECT a.* FROM jxjs_jbgz a inner join wage_userid b on a.bh=b.wagesid where b.userid=?0 and a.S1=?1 ORDER BY a.S2 DESC";

		SQLQuery sqlQuery = createSQLQuery(sb, userName, year);
		sqlQuery.addEntity(Jbgz.class);

		return sqlQuery.list();
	}

	private SQLQuery createSQLQuery(final String queryString, final Object... values)
	{
		SQLQuery sqlQuery = sessionFactory.getCurrentSession().createSQLQuery(queryString);
		if (values != null)
		{
			for (int i = 0; i < values.length; i++)
			{
				sqlQuery.setParameter(String.valueOf(i), values[i]);
			}
		}
		return sqlQuery;
	}

	@Override
	public Jbgz getBookById(Integer jbgzId)
	{
		Session session = sessionFactory.getCurrentSession();
		List<Jbgz> list = session.createSQLQuery("from Jbgz b where b.id = :jbgzId").setParameter("jbgzId", jbgzId).list();
		return list.size() > 0 ? (Jbgz) list.get(0) : null;
	}

	@Override
	public void removeJbgz(Integer id)
	{
		Jbgz jbgz = (Jbgz) sessionFactory.getCurrentSession().load(Jbgz.class, id);
		if (null != jbgz)
		{
			sessionFactory.getCurrentSession().delete(jbgz);
		}
	}
}
