package org.techzoo.springmvc.dao;

import java.util.List;
import java.util.UUID;

import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.techzoo.springmvc.form.WageUserId;

@Repository
public class WagesidDaoImpl implements WagesidDao
{
	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void inputWagesUserId(String userId, String gzId)
	{
		WageUserId wageUserId = new WageUserId();
		UUID uuid = UUID.randomUUID();
		wageUserId.setId(uuid.toString());
		wageUserId.setUserid(userId);
		wageUserId.setWagesid(gzId);
		sessionFactory.getCurrentSession().save(wageUserId);
	}

	@Override
	public void removeWagesUserIdByUserId(String userid)
	{
		SQLQuery sqlQuery = createSQLQuery("SELECT * FROM wage_userid WHERE userid=?0", userid);
		sqlQuery.addEntity(WageUserId.class);

		List<WageUserId> list = sqlQuery.list();
		if (null != list && list.size() > 0)
		{
			sessionFactory.getCurrentSession().delete(list.get(0));
		}
	}

	@Override
	public void removeWagesUserIdByWagesId(String wagesid)
	{
		SQLQuery sqlQuery = createSQLQuery("SELECT * FROM wage_userid WHERE wagesid=?0", wagesid);
		sqlQuery.addEntity(WageUserId.class);

		List<WageUserId> list = sqlQuery.list();
		if (null != list && list.size() > 0)
		{
			sessionFactory.getCurrentSession().delete(list.get(0));
		}
	}

	public SQLQuery createSQLQuery(final String queryString, final Object... values)
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
}
