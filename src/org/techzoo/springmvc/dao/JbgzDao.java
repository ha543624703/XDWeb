package org.techzoo.springmvc.dao;

import java.util.List;

import org.techzoo.springmvc.form.Jbgz;

public interface JbgzDao
{
	public void addJbgz(Jbgz jbgz);

	public void updateJbgz(Jbgz jbgz);

	public List<Jbgz> listJbgzs();

	public List<Jbgz> listJbgzs(String userName, int year, int month);

	List listJbgzByYear(String userName, int year);

	public Jbgz getBookById(Integer jbgzId);

	public void removeJbgz(Integer id);
}
