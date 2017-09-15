package org.techzoo.springmvc.dao;

import java.util.List;

public interface ArticleDao {
	
	public List listCount(String deptCode, String begDate, String endDate);
	
	public List listNewsAccount();

}
