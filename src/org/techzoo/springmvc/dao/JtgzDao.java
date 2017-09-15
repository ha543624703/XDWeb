package org.techzoo.springmvc.dao;

import java.util.List;

import org.techzoo.springmvc.form.Jbgz;
import org.techzoo.springmvc.form.Jtgz;

public interface JtgzDao {

	public void addJtgz(Jtgz jtgz);
	public void updateJtgz(Jtgz jtgz);
	public List<Jtgz> listJtgzs();
	public Jtgz getJtgzById(Integer jtgzId);
	public List<Jtgz> listJtgzs(String userName,int year,int month);
	public void removeJtgz(Integer id);
	
}
