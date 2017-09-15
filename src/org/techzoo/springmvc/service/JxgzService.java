package org.techzoo.springmvc.service;

import java.util.List;

import org.techzoo.springmvc.form.Jtgz;
import org.techzoo.springmvc.form.Jxgz;

public interface JxgzService {
	public void addJxgz(Jxgz jxgz);
	public void updateJxgz(Jxgz jxgz);
	public List<Jxgz> listJxgzs();
	public List<Jxgz> listJxgzs(String userName,int year,int month);
	public Jxgz getJxgzById(Integer jxgzId);
	public void removeJxgz(Integer id);
}
