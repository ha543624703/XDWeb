package org.techzoo.springmvc.service;

import java.util.List;

import org.techzoo.springmvc.form.Jbgz;

public interface JbgzService
{
	public void addJbgz(Jbgz jbgz);

	public void updateJbgz(Jbgz jbgz);

	public List<Jbgz> listJbgzs(String userName, int year, int month);

	List<Jbgz> listJbgzsByYear(String userName, int year);

	public List<Jbgz> listJbgzs();

	public Jbgz getBookById(Integer jbgzId);

	public void removeJbgz(Integer id);

	void inputWagesUserid(String userid, String wagesid);
}
