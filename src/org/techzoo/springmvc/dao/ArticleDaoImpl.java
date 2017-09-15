package org.techzoo.springmvc.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class ArticleDaoImpl implements ArticleDao{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Override
	public List listCount(String deptCode, String begDate, String endDate){
		Session session = sessionFactory.getCurrentSession();
		List list = jdbcTemplate.queryForList("select (select NodeName from siteserver_Node where NodeID=a.nodeid) as nodename,COUNT(0) as rowsum from cms_Content a where a.AddUserName='"+deptCode+"'  and (a.AddDate BETWEEN '"+begDate+"' AND '"+endDate+"') and a.nodeid >0 group by a.NodeID");
		return list;
	}
	
	@Override
	public List listNewsAccount(){
		Session session = sessionFactory.getCurrentSession();
		List list = jdbcTemplate.queryForList("select UserName,DisplayName from dbo.bairong_Users where  TypeID=4");
		return list;
	}
}
