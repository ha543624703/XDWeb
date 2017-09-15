package org.springside.modules.orm;

import java.util.Collections;
import java.util.List;

import org.apache.commons.lang.StringUtils;

/**
 * 与具体ORM实现无关的分页参数及查询结果封装. 注意所有序号从1开始.
 * 
 * @param <T>
 *            Page中记录的类型.
 * 
 * @author calvin
 */
public class Page<T> {
	// 公共变量 //
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	// 分页参数 //
	protected int pageNo = 1;
	protected int pageSize = 1;
	protected String orderBy = null;
	protected String order = null;
	protected boolean autoCount = true;

	// 返回结果 //
	protected List<T> result = Collections.emptyList();
	protected long totalCount = -1;

	// 构造函数 //

	public Page() {
		super();
	}

	public Page(final int pageSize) {
		setPageSize(pageSize);
	}

	public Page(final int pageSize, final boolean autoCount) {
		setPageSize(pageSize);
		this.autoCount = autoCount;
	}

	// 查询参数访问函数 //

	/**
	 * 获得当前页的页号,序号从1开始,默认为1.
	 */
	public int getPageNo() {
		return pageNo;
	}

	/**
	 * 设置当前页的页号,序号从1开始,低于1时自动调整为1.
	 */
	public void setPageNo(final int pageNo) {
		this.pageNo = pageNo;

		if (pageNo < 1) {
			this.pageNo = 1;
		}
	}

	/**
	 * 获得每页的记录数量,默认为1.
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 设置每页的记录数量,低于1时自动调整为1.
	 */
	public void setPageSize(final int pageSize) {
		this.pageSize = pageSize;

		if (pageSize < 1) {
			this.pageSize = 1;
		}
	}

	/**
	 * 根据pageNo和pageSize计算当前页第一条记录在总结果集中的位置,序号从1开始.
	 */
	public int getFirst() {
		return ((pageNo - 1) * pageSize) + 1;
	}

	/**
	 * 获得排序字段,无默认值.多个排序字段时用','分隔.
	 */
	public String getOrderBy() {
		return orderBy;
	}

	/**
	 * 设置排序字段,多个排序字段时用','分隔.
	 */
	public void setOrderBy(final String orderBy) {
		this.orderBy = orderBy;
	}

	/**
	 * 是否已设置排序字段,无默认值.
	 */
	public boolean isOrderBySetted() {
		return StringUtils.isNotBlank(orderBy);
	}

	/**
	 * 获得排序方向.
	 * 
	 * @param order
	 *            可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public String getOrder() {
		return order;
	}

	/**
	 * 设置排序方式向.
	 * 
	 * @param order
	 *            可选值为desc或asc,多个排序字段时用','分隔.
	 */
	public void setOrder(final String order) {
		// 检查order字符串的合法值
		String[] orders = StringUtils.split(StringUtils.lowerCase(order), ',');
		for (String orderStr : orders) {
			if (!StringUtils.equals(DESC, orderStr)
					&& !StringUtils.equals(ASC, orderStr))
				throw new IllegalArgumentException("排序方向" + orderStr + "不是合法值");
		}

		this.order = StringUtils.lowerCase(order);
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数, 默认为false.
	 */
	public boolean isAutoCount() {
		return autoCount;
	}

	/**
	 * 查询对象时是否自动另外执行count查询获取总记录数.
	 */
	public void setAutoCount(final boolean autoCount) {
		this.autoCount = autoCount;
	}

	// 访问查询结果函数 //

	/**
	 * 取得页内的记录列表.
	 */
	public List<T> getResult() {
		return result;
	}

	public void setResult(final List<T> result) {
		this.result = result;
	}

	/**
	 * 取得总记录数, 默认值为-1.
	 */
	public long getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(final long totalCount) {
		this.totalCount = totalCount;
	}

	/**
	 * 根据pageSize与totalCount计算总页数, 默认值为-1.
	 */
	public long getTotalPages() {
		if (totalCount < 0)
			return -1;

		long count = totalCount / pageSize;
		if (totalCount % pageSize > 0) {
			count++;
		}
		return count;
	}

	/**
	 * 是否还有下一页.
	 */
	public boolean isHasNext() {
		return (pageNo + 1 <= getTotalPages());
	}

	/**
	 * 取得下页的页号, 序号从1开始. 当前页为尾页时仍返回尾页序号.
	 */
	public int getNextPage() {
		if (isHasNext())
			return pageNo + 1;
		else
			return pageNo;
	}

	/**
	 * 是否还有上一页.
	 */
	public boolean isHasPre() {
		return (pageNo - 1 >= 1);
	}

	/**
	 * 取得上页的页号, 序号从1开始. 当前页为首页时返回首页序号.
	 */
	public int getPrePage() {
		if (isHasPre())
			return pageNo - 1;
		else
			return pageNo;
	}

	public String getParameter() {
		StringBuffer paraStr = new StringBuffer();
		paraStr.append(
				"<input type=\"hidden\" name=\"page.pageNo\" id=\"pageNo\" value=\""
						+ getPageNo() + "\"/>").append(
				"<input type=\"hidden\" name=\"page.orderBy\" id=\"orderBy\" value=\""
						+ (getOrderBy() == null ? "" : getOrderBy()) + "\"/>")
				.append(
						"<input type=\"hidden\" name=\"page.order\" id=\"order\" value=\""
								+ (getOrder() == null ? "" : getOrder())
								+ "\"/>");
		return paraStr.toString();
	}

	public String getFoot() {
		StringBuffer foot = new StringBuffer();
		foot.append("总计<font color='red'>").append(getTotalCount()).append(
				"</font>条信息,").append("【共<font  color='red'>").append(
				getTotalPages()).append("</font>页】【").append(getPageSize())
				.append("条/页】,").append("当前第<font color='red'>").append(
						getPageNo()).append("</font>页,");
		foot.append("<a href=\"javascript:jumpPage(1)\">首页</a>");
		if (isHasPre()) {
			foot.append("&nbsp;<a href=\"javascript:jumpPage(" + getPrePage()
					+ ")\">上一页</a>");
		}
		if (isHasNext()) {
			foot.append("&nbsp;<a href=\"javascript:jumpPage(" + getNextPage()
					+ ")\">下一页</a>");
		}
		foot.append("&nbsp;<a href=\"javascript:jumpPage(" + getTotalPages()
				+ ")\">末页</a>");
		foot
				.append(
						"转到<input size=\"1\" id=\"mypage\" onblur='$(\"#pageNo\").val(this.value)' onKeyUp=\"value=value.replace(/[^\\d]/g,'') \"/>页")
				.append("  <input type=\"submit\" name='Submit' value='go'/>");
		return foot.toString();
	}
	
	/**
	 * 黄缙
	 * 2011-5-20  下午03:24:18
	 * @return
	 */
	public String getFoot2() {
		StringBuffer foot2 = new StringBuffer();
		foot2.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\"><tr><td width=\"33%\"><div align=\"left\"><span class=\"STYLE22\">&nbsp;&nbsp;&nbsp;&nbsp;共有<strong>")
		.append(getTotalCount()).append("</strong> 条记录，当前第<strong>").append(getPageNo()).append("</strong> 页，共 <strong>").append(getTotalPages())
		.append("</strong> 页</span></div></td> <td width=\"67%\"><table width=\"312\" border=\"0\" align=\"right\" cellpadding=\"0\" cellspacing=\"0\">");
		
		if (isHasPre()) {
			foot2.append("<tr><td width=\"49\"><div align=\"center\"><a href=\"javascript:jumpPage(1)\"><img src=\"../images/main_54.gif\" width=\"40\" height=\"15\" /></a></div></td>")
			.append("<td width=\"49\"><div align=\"center\"><a href=\"javascript:jumpPage(" + getPrePage()+ ")\"><img src=\"../images/main_56.gif\" width=\"45\" height=\"15\" /></a></div></td>");
		}else{
			foot2.append("<tr><td width=\"49\"><div align=\"center\"><img src=\"../images/main_54_2.gif\" width=\"40\" height=\"15\" /></div></td>")
			.append("<td width=\"49\"><div align=\"center\"><img src=\"../images/main_56_2.gif\" width=\"45\" height=\"15\" /></div></td>");
		}
		if (isHasNext()) {
			foot2.append("<td width=\"49\"><div align=\"center\"><a href=\"javascript:jumpPage(" + getNextPage()+ ")\"><img src=\"../images/main_58.gif\" width=\"45\" height=\"15\" /></a></div></td>")
			.append("<td width=\"49\"><div align=\"center\"><a href=\"javascript:jumpPage(" + getTotalPages()+ ")\"><img src=\"../images/main_60.gif\" width=\"40\" height=\"15\" /></a></div></td>");
		}else{
			foot2.append("<td width=\"49\"><div align=\"center\"><img src=\"../images/main_58_2.gif\" width=\"45\" height=\"15\" /></div></td>")
			.append("<td width=\"49\"><div align=\"center\"><img src=\"../images/main_60_2.gif\" width=\"40\" height=\"15\" /></div></td>");
		}
	
		foot2.append("<td width=\"37\" class=\"STYLE22\"><div align=\"center\">转到</div></td><td width=\"22\"><div align=\"center\"><input class=\"text_go\" id=\"mypage\" onblur='$(\"#pageNo\").val(this.value)' onKeyUp=\"value=value.replace(/[^\\d]/g,'') \"/>")
        .append("</div></td> <td width=\"22\" class=\"STYLE22\"><div align=\"center\">页</div></td><td width=\"35\"><input type=\"submit\" class='page_go' name='Submit' value=''/></td></tr> </table></td> </tr> </table>");
		
		return foot2.toString();
	}
}
