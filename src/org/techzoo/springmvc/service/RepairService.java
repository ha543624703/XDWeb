package org.techzoo.springmvc.service;

import java.util.List;
import java.util.Map;

import org.techzoo.springmvc.form.RepairPresentation;

public interface RepairService
{
	/**
	 * @description:新增报修
	 * @param repairPresentation
	 * @return
	 * @author:duzl
	 * @createTime:2017年6月8日 上午8:41:22
	 */
	int insertRepairPresentation(RepairPresentation repairPresentation);

	/**
	 * @description:查询报修列表
	 * @param userId
	 * @param status 状态-1查全部
	 * @return
	 * @author:钢银商城交易项目组 杜章亮
	 * @createTime:2017年6月22日 下午3:44:02
	 */
	List<Map<String, Object>> queryRepairList(int status);

	/**
	 * @description:查询报修
	 * @param id
	 * @return
	 * @author:钢银商城交易项目组 杜章亮
	 * @createTime:2017年6月22日 下午4:06:20
	 */
	Map<String, Object> getRepair(int id);

	/**
	 * @description:审核报修
	 * @param id
	 * @param status
	 * @return
	 * @author:云联高科 杜章亮
	 * @createTime:2017年6月22日 下午4:19:35
	 */
	int setRepairAudited(int id, int status);
}
