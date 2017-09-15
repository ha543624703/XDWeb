package org.techzoo.springmvc.service;

import java.util.List;

import org.techzoo.springmvc.form.Jbgz;
import org.techzoo.springmvc.form.Jtgz;

public interface JtgzService {
	public void addJtgz(Jtgz jtgz);
	public void updateJtgz(Jtgz jtgz);
	public List<Jtgz> listJtgzs();
	public List<Jtgz> listJtgzs(String userName,int year,int month);
	public Jtgz getJtgzById(Integer jtgzId);
	public void removeJtgz(Integer id);
}
