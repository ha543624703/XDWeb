package org.techzoo.springmvc.dao;

import java.util.List;

import org.techzoo.springmvc.form.Jxgz;
import org.techzoo.springmvc.form.Qtgz;

public interface QtgzDao {

	public void addQtgz(Qtgz qtgz);
	public void updateQtgz(Qtgz qtgz);
	public List<Qtgz> listQtgzs();
	public List<Qtgz> listQtgzs(String userName,int year,int month);
	public Qtgz getQtgzById(Integer qtgzId);
	public void removeQtgz(Integer id);
	
}
