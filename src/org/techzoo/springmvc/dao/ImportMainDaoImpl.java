package org.techzoo.springmvc.dao;

import java.util.List;

import javax.sql.DataSource;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.techzoo.springmvc.form.ImportMain;
import org.techzoo.springmvc.form.Importlog;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.IntType;

@Repository
public class ImportMainDaoImpl implements ImportMainDao {

	@Autowired
	JdbcTemplate jdbcTemplate;

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void addImportMain(ImportMain importMain) {
		Session session = sessionFactory.getCurrentSession();
		sessionFactory.getCurrentSession().save(importMain);

	}

	@Override
	public ImportMain getImportMainById(String id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<ImportMain> list = session.createQuery(
				"from ImportMain b where b.id = :logId and b.IsDelete=1")
				.setParameter("logId", id).list();
		return list.size() > 0 ? (ImportMain) list.get(0) : null;
	}

	@Override
	public List<ImportMain> listImportMain(String year, String month,
			String wageType) {
		// TODO Auto-generated method stub
		return sessionFactory
				.getCurrentSession()
				.createQuery(
						"from ImportMain b where b.WYear=:logyear and b.WMonth=:logmonth and b.WageType=:wageType and isDelete=1")
				.setParameter("logyear", Integer.parseInt(year)).setParameter(
						"logmonth", Integer.parseInt(month)).setParameter(
						"wageType", Integer.parseInt(wageType)).list();
	}

	@Override
	public void removeImportMain(String id) {
		Session session = sessionFactory.getCurrentSession();
		session
				.createSQLQuery(
						"update wage_ImportMain set isDelete=0 where id='" + id
								+ "' ").executeUpdate();
	}

	@Override
	public void updateImportMain(ImportMain importMain) {
		// TODO Auto-generated method stub
		sessionFactory.getCurrentSession().update(importMain);
	}

	// 设置有效无效
	@Transactional
	public void ImportMianEnable(String id) {

		Session session = sessionFactory.getCurrentSession();
		ImportMain entity = getImportMainById(id);// 获取对象
		if (entity != null) {
			if (entity.getIsEnable() == 1)// 设置失效
			{
				session.createQuery(
						"update ImportMain set IsEnable=0 where id='" + id
								+ "'").executeUpdate();
			} else// 设置生效
			{
				// 生效条件为同一年月日学期周次只允许有一条生效记录

				if (entity.getWageType() == 1 || entity.getWageType() == 2
						|| entity.getWageType() == 3
						|| entity.getWageType() == 4
						|| entity.getWageType() == 5) {
					List<ImportMain> list = sessionFactory
							.getCurrentSession()
							.createQuery(
									"from ImportMain b where b.WYear=:logyear and b.WMonth=:logmonth and b.WageType=:wageType  and b.IsDelete=1")
							.setParameter("logyear", entity.getWYear())
							.setParameter("logmonth", entity.getWMonth())
							.setParameter("wageType", entity.getWageType())
							.list();
					if (list != null && list.size() > 0) {
						for (ImportMain item : list) {
							session.createQuery(
									"update ImportMain set IsEnable=0 where id='"
											+ item.getId() + "'")
									.executeUpdate();
						}
					}

				} else if (entity.getWageType() == 6) {

					List<ImportMain> list = sessionFactory
							.getCurrentSession()
							.createQuery(
									"from ImportMain b where b.WYear=:logyear AND b.WageType=:wageType  and b.IsDelete=1")
							.setParameter("logyear", entity.getWYear())
							.setParameter("wageType", entity.getWageType())
							.list();
					if (list != null && list.size() > 0) {
						for (ImportMain item : list) {
							session.createQuery(
									"update ImportMain set IsEnable=0 where id='"
											+ item.getId() + "' ")
									.executeUpdate();
						}
					}

				} else if (entity.getWageType() == 7) {
					List<ImportMain> list = sessionFactory
							.getCurrentSession()
							.createQuery(
									"from ImportMain b where b.WMonth=:logmonth and b.YearTerm=:yearTerm AND b.WeekStart=:weekStart AND b.WeekEnd=:weekEnd and b.IsDelete=1")
							.setParameter("logmonth", entity.getWMonth())
							.setParameter("yearTerm", entity.getYearTerm())
							.setParameter("weekStart", entity.getWeekStart())
							.setParameter("weekEnd", entity.getWeekEnd())
							.list();
					if (list != null && list.size() > 0) {
						for (ImportMain item : list) {
							session.createQuery(
									"update ImportMain set IsEnable=0 where id='"
											+ item.getId() + "' ")
									.executeUpdate();
						}
					}
				}
				session.createQuery(
						"update ImportMain set IsEnable=1 where id='" + id
								+ "'").executeUpdate();
			}
		}

	}

	@Transactional
	public List SelectWageList(String pid) {
		// TODO Auto-generated method stub
		// Session session = sessionFactory.getCurrentSession();
		String sql = "declare @sql varchar(8000) set @sql='select   Snumber 序号,UserCode AS 工号,Depanrment AS 部门,UserName AS 姓名,Remark AS 说明'"
				+ "select @sql=@sql+',max(case TitleName when '''+TitleName+''' then TitleValue else ''0'' end) ['+TitleName+']' "
				+ "from (select distinct(titleName),b.wOrderby from wage_ImportDetail a inner join wage_ImportDetailCode b on a.ID=b.ImportDetailID "
				+ "inner join wage_ImportMain c on c.ID=a.MainDetailID where c.ID='"
				+ pid
				+ "' AND  c.isDelete=1   group by TitleName,b.wOrderby"
				+ ") a order by Worderby set @sql=@sql+' from wage_ImportDetail a inner join wage_ImportDetailCode b on a.ID=b.ImportDetailID "
				+ "inner join wage_ImportMain c on c.ID=a.MainDetailID where c.id=''"
				+ pid
				+ "'' AND c.isDelete=1  group by Snumber,UserCode,Depanrment,UserName,c.CreateMan,c.CreateDate,WYear,WMonth,IsEnable,Remark order by UserCode' "
				+ "exec(@sql)";
		// this.getJdbcTemplate().queryForList(strSQL);
		List list = jdbcTemplate.queryForList(sql);
		// List list = session.ceateSQLQuery(sql).list();
		return list;
	}
	@Transactional
	public List SelectWageList(String userCode,String year,String month,String wType) {
		// TODO Auto-generated method stub
		// Session session = sessionFactory.getCurrentSession();
		String sql = "declare @sql varchar(8000) set " +
				"@sql='select   Snumber 序号,UserCode AS 工号,Depanrment AS 部门,UserName AS 姓名,Remark AS 说明' " +
				"select @sql=@sql+',max(case TitleName when '''+TitleName+''' then TitleValue else ''0'' end) ['+TitleName+']' from (select distinct(titleName),b.wOrderby from wage_ImportDetail a inner join wage_ImportDetailCode b on a.ID=b.ImportDetailID " +
				"inner join wage_ImportMain c on c.ID=a.MainDetailID " +
				"where c.IsEnable=1 AND WYear='" 
				+year
				+"' AND isDelete='1' AND WMonth='"
			    +month
				+"' AND WageType='" 
				+wType
				+"' AND  UserCode='" 
				+userCode
				+"'  group by TitleName,b.wOrderby) " +
				"a order by Worderby set @sql=@sql+' " +
				"from wage_ImportDetail a inner join wage_ImportDetailCode b on a.ID=b.ImportDetailID inner join " +
				" wage_ImportMain c on c.ID=a.MainDetailID where  c.IsEnable=1 AND isDelete=''1''  AND WYear=''"+year+"'' AND WMonth=''"+month+"'' AND WageType=''"+wType+"'' AND  UserCode=''"+userCode+"''  group by Snumber,UserCode,Depanrment,UserName,c.CreateMan,c.CreateDate,WYear," +
				"WMonth,IsEnable,Remark order by UserCode' exec(@sql)";
		List list = jdbcTemplate.queryForList(sql);
		return list;
	}
	
	

	// 判断是否跨列存在
	@Transactional
	public boolean isKL(String pid) {
		String sql = "select  COUNT(1) from wage_ImportMain a "
				+ "inner join  (select top 1 * from  wage_ImportDetail  "
				+ "where MainDetailID='"
				+ pid
				+ "') AS b on a.ID=b.MainDetailID  "
				+ "inner join wage_ImportDetailCode c on c.ImportDetailID=b.ID  "
				+ "where a.ID='" + pid
				+ "' AND isDelete=1  AND PID is not null";
		return jdbcTemplate.queryForInt(sql) > 0 ? true : false;
	}
	public String getImportMainID(String wyear,String wMonth,String wageType)
	{
		List<ImportMain> list= sessionFactory
		.getCurrentSession()
		.createQuery(
				"from ImportMain b where b.WYear=:logyear and b.WMonth=:logmonth and b.WageType=:wageType and isDelete=1")
		.setParameter("logyear", Integer.parseInt(wyear)).setParameter(
				"logmonth", Integer.parseInt(wMonth)).setParameter(
				"wageType", Integer.parseInt(wageType)).list();
		 
		if(list!=null&&list.size()>0)
		{
			
			return list.get(0).getId();
			
		}
		else 
		{
			return null;
			
		}
		 
	}
	
	
	
	

	// 处理跨列数据
	@Override
	public List SelectWageLitKL(String pid) {
		// TODO Auto-generated method stub

		String sql = " select  c.Pid,c.TitleName,c.TitleValue,UserCode,c.ID from wage_ImportMain a "
				+ "inner join  (select top 1 * from  wage_ImportDetail  "
				+ "where MainDetailID='"
				+ pid
				+ "') AS b on a.ID=b.MainDetailID  "
				+ "inner join wage_ImportDetailCode c on c.ImportDetailID=b.ID  "
				+ "where a.ID='"
				+ pid
				+ "' AND isDelete=1  ORDER By c.wOrderby";
		List list = jdbcTemplate.queryForList(sql);
		return list;
	}

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Transactional
	public List SelectWageList(String wageType, String userCode, String pWhere) {
		// TODO Auto-generated method stub
		List list=null;
		if(wageType.equals("1")||wageType.equals("2")||wageType.equals("3")||wageType.equals("4")||wageType.equals("5"))
		{
			String year=pWhere.split(",")[0];
			String month=pWhere.split(",")[1];
			
			String sql = "declare @sql varchar(8000) set @sql='select   Snumber 序号,UserCode AS 工号,Depanrment AS 部门,UserName AS 姓名,Remark AS 说明'"
				+ "select @sql=@sql+',max(case TitleName when '''+TitleName+''' then TitleValue else ''0'' end) ['+TitleName+']' "
				+ "from (select distinct(titleName),b.wOrderby from wage_ImportDetail a inner join wage_ImportDetailCode b on a.ID=b.ImportDetailID "
				+ "inner join wage_ImportMain c on c.ID=a.MainDetailID where c.wageType='"
				+ wageType
				+ "' AND c.isDelete=1 AND c.IsEnable=1 AND c.WYear="+year+" AND c.WMonth="+month+" AND a.UserCode='"+userCode+"' group by TitleName,b.wOrderby"
				+ ") a order by Worderby set @sql=@sql+' from wage_ImportDetail a inner join wage_ImportDetailCode b on a.ID=b.ImportDetailID "
				+ "inner join wage_ImportMain c on c.ID=a.MainDetailID where c.wageType=''"
				+ wageType
				+ "'' AND c.isDelete=1 AND c.IsEnable=1 AND a.UserCode=''"+userCode+"'' AND c.WYear="+year+" AND c.WMonth="+month+"    group by Snumber,UserCode,Depanrment,UserName,c.CreateMan,c.CreateDate,WYear,WMonth,IsEnable,Remark order by UserCode' "
				+ "exec(@sql)";
		 list = jdbcTemplate.queryForList(sql);		
		}
		else if(wageType.equals("6"))
		{
			String yearTerm=pWhere.split(",")[0];
			String month=pWhere.split(",")[1];
			String sql = "declare @sql varchar(8000) set @sql='select   Snumber 序号,UserCode AS 工号,Depanrment AS 部门,UserName AS 姓名,Remark AS 说明'"
				+ "select @sql=@sql+',max(case TitleName when '''+TitleName+''' then TitleValue else ''0'' end) ['+TitleName+']' "
				+ "from (select distinct(titleName),b.wOrderby from wage_ImportDetail a inner join wage_ImportDetailCode b on a.ID=b.ImportDetailID "
				+ "inner join wage_ImportMain c on c.ID=a.MainDetailID where c.wageType='"
				+ wageType
				+ "' AND c.isDelete=1 AND c.IsEnable=1 AND c.WMonth="+month+"  AND a.UserCode='"+userCode+"' AND c.yearTerm='"+yearTerm+"'  group by TitleName,b.wOrderby"
				+ ") a order by Worderby set @sql=@sql+' from wage_ImportDetail a inner join wage_ImportDetailCode b on a.ID=b.ImportDetailID "
				+ "inner join wage_ImportMain c on c.ID=a.MainDetailID where c.wageType=''"
				+ wageType
				+ "'' AND c.isDelete=1 AND c.IsEnable=1 AND a.UserCode=''"+userCode+"'' AND c.WMonth="+month+"  AND c.yearTerm=''"+yearTerm+"''  group by Snumber,UserCode,Depanrment,UserName,c.CreateMan,c.CreateDate,WYear,WMonth,IsEnable,Remark order by UserCode' "
				+ "exec(@sql)";
		 list = jdbcTemplate.queryForList(sql);	
			
		}
		else if(wageType.equals("7"))
		{
			
			String year=pWhere;
			String sql = "declare @sql varchar(8000) set @sql='select   Snumber 序号,UserCode AS 工号,Depanrment AS 部门,UserName AS 姓名,Remark AS 说明'"
				+ "select @sql=@sql+',max(case TitleName when '''+TitleName+''' then TitleValue else ''0'' end) ['+TitleName+']' "
				+ "from (select distinct(titleName),b.wOrderby from wage_ImportDetail a inner join wage_ImportDetailCode b on a.ID=b.ImportDetailID "
				+ "inner join wage_ImportMain c on c.ID=a.MainDetailID where c.wageType='"
				+ wageType
				+ "' AND c.isDelete=1 AND c.IsEnable=1 AND c.WYear="+year+"  AND a.UserCode='"+userCode+"' group by TitleName,b.wOrderby"
				+ ") a order by Worderby set @sql=@sql+' from wage_ImportDetail a inner join wage_ImportDetailCode b on a.ID=b.ImportDetailID "
				+ "inner join wage_ImportMain c on c.ID=a.MainDetailID where c.wageType=''"
				+ wageType
				+ "'' AND c.isDelete=1 AND c.IsEnable=1 AND a.UserCode=''"+userCode+"'' AND c.WYear="+year+"    group by Snumber,UserCode,Depanrment,UserName,c.CreateMan,c.CreateDate,WYear,WMonth,IsEnable,Remark order by UserCode' "
				+ "exec(@sql)";
		 list = jdbcTemplate.queryForList(sql);	
			
		}
		

	// List list = session.ceateSQLQuery(sql).list();
	return list;
	}

}
