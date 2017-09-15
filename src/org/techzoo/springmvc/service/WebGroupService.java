package org.techzoo.springmvc.service;

import java.util.List;
import java.util.Map;

public interface WebGroupService
{
	Map<String, Object> getSchoolNews(long id);
	List<Map<String, Object>> querySchoolNewsList(int page, int pageSize);
	int querySchoolNewsCount();
	List<Map<String, Object>> NewList();

	Map<String, Object> getNotice(long id);
	List<Map<String, Object>> queryNoticeList(int page, int pageSize);
	int queryNoticeCount();
	List<Map<String, Object>> NoticeList();

	Map<String, Object> getDynamic(long id);
	List<Map<String, Object>> queryDynamicList(int page, int pageSize);
	int queryDynamicCount();
	List<Map<String, Object>> DynamicList();
}
