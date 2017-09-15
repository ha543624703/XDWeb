package org.techzoo.springmvc.service;

import java.util.List;

import org.techzoo.springmvc.form.ImportDetailCode;

public interface ImportDetailCodeService {
	public void addImportDetailCode(ImportDetailCode importDetail);
	public void updateImportDetailCode(ImportDetailCode importDetail);
	public List<ImportDetailCode> listImportDetailCode(String pkid);
	public ImportDetailCode getImportDetailCodeById(String id);
	public void removeImportDetailCode(String id);
}
