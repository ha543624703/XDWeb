package org.techzoo.springmvc.service;

import java.util.List;

import org.techzoo.springmvc.form.ImportDetail;

public interface ImportDetailService {
	public void addImportDetail(ImportDetail importDetail);
	public void updateImportDetail(ImportDetail importDetail);
	public List<ImportDetail> listImportDetail(String pkid);
	public ImportDetail getImportDetailById(String id);
	public void removeImportDetail(String id);
}
