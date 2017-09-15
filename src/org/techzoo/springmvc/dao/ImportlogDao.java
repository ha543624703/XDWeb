package org.techzoo.springmvc.dao;

import java.util.List;

import org.techzoo.springmvc.form.Importlog;



public interface ImportlogDao {

	public void addImportlog(Importlog importlog);
	public void updateImportlog(Importlog importlog);
	public List<Importlog> listImportlogs(int year, int month);
	public Importlog getImportlogById(String logId);
	public void removeImportlog(String logId);
	public void removeImport(String tableName,String logId);
	
}
