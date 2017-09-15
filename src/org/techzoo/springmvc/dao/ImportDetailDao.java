package org.techzoo.springmvc.dao;

import java.util.List;

import org.techzoo.springmvc.form.ImportDetail;
public interface ImportDetailDao {
	public void addImportDetail(ImportDetail importDetail);
	public void updateImportDetail(ImportDetail importDetail);
	public List<ImportDetail> listImportDetail(String pkid);
	public ImportDetail getImportDetailById(String id);
	public void removeImportDetail(String id);
}
