package org.techzoo.springmvc.dao;

public interface WagesidDao
{
	void inputWagesUserId(String userId, String gzId);

	void removeWagesUserIdByUserId(String userid);

	void removeWagesUserIdByWagesId(String wagesid);
}
