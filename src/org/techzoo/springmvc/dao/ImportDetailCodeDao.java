package org.techzoo.springmvc.dao;

import java.util.List;

import org.techzoo.springmvc.form.ImportDetailCode;

public interface ImportDetailCodeDao {
	public void addImportDetailCode(ImportDetailCode importDetailCode);
	public void updateImportDetailCode(ImportDetailCode importDetailCode);
	public List<ImportDetailCode> listImportDetailCode(String pkid);
	public ImportDetailCode getImportDetailCodeById(String id);
	public void removeImportDetailCode(String id);
}
