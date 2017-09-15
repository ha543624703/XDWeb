package org.techzoo.springmvc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.techzoo.springmvc.dao.JbgzDao;
import org.techzoo.springmvc.dao.WagesidDao;
import org.techzoo.springmvc.form.Jbgz;

@Service
public class JbgzServiceImpl implements JbgzService
{

	@Autowired
	private JbgzDao jbgzDao;
	@Autowired
	private WagesidDao wagesidDao;

	@Transactional
	public void addJbgz(Jbgz jbgz)
	{
		jbgzDao.addJbgz(jbgz);
	}

	@Transactional
	public void updateJbgz(Jbgz jbgz)
	{
		jbgzDao.updateJbgz(jbgz);
	}

	@Transactional
	public List<Jbgz> listJbgzs()
	{
		return jbgzDao.listJbgzs();
	}

	@Transactional
	public List<Jbgz> listJbgzs(String userName, int year, int month)
	{
		return jbgzDao.listJbgzs(userName, year, month);
	}

	/**
	 * @description:根据年份查工资
	 * @param userName
	 * @param year
	 * @return
	 * @author:duzl
	 * @createTime:2017年6月19日 下午4:37:28
	 */
	@Transactional
	public List<Jbgz> listJbgzsByYear(String userName, int year)
	{
		return jbgzDao.listJbgzByYear(userName, year);
	}

	@Transactional
	public Jbgz getBookById(Integer jbgzId)
	{
		return jbgzDao.getBookById(jbgzId);
	}

	@Transactional
	public void removeJbgz(Integer id)
	{
		jbgzDao.removeJbgz(id);
	}

	/**
	 * @description:插入工资编号和工号的对应关系
	 * @param userid
	 * @param wagesid
	 * @author:duzl
	 * @createTime:2017年6月20日 上午11:28:46
	 */
	@Transactional
	@Override
	public void inputWagesUserid(String userid, String wagesid)
	{
		wagesidDao.removeWagesUserIdByUserId(userid);
		wagesidDao.removeWagesUserIdByWagesId(wagesid);
		wagesidDao.inputWagesUserId(userid, wagesid);
	}
}
