package org.techzoo.springmvc.service;

import java.util.List;

import org.techzoo.springmvc.form.Importlog;

public interface ImportlogService {

	public void addImportlog(Importlog importlog);
	public void updateImportlog(Importlog importlog);
	public List<Importlog> listImportlogs(int year, int month);
	public Importlog getImportlogById(String logId);
	public void removeImportlog(String logId);
	public void removeImport(String tableName,String logId);
}
