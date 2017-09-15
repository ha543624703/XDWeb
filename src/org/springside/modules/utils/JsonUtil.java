package org.springside.modules.utils;

import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.util.List;

/**
 * json的操作类
 * 
 * @author NanGuoCan
 * 
 */
public class JsonUtil {

	/**
	 * @param object
	 *            任意对象
	 * @return java.lang.String
	 */
	public static String objectToJson(Object object) {
		StringBuilder json = new StringBuilder();
		if (object == null) {
			json.append("\"\"");
		} else if (object instanceof String || object instanceof Integer) {
			json.append("\"").append(object.toString()).append("\"");
		} else {
			json.append(beanToJson(object));
		}
		return json.toString();
	}

	/**
	 * 功能描述:传入任意一个 javabean 对象生成一个指定规格的字符串
	 * 
	 * @param bean
	 *            bean对象
	 * @return String
	 */
	public static String beanToJson(Object bean) {
		StringBuilder json = new StringBuilder();
		json.append("{");
		PropertyDescriptor[] props = null;
		try {
			props = Introspector.getBeanInfo(bean.getClass(), Object.class)
					.getPropertyDescriptors();
		} catch (IntrospectionException e) {
		}
		if (props != null) {
			for (int i = 0; i < props.length; i++) {
				try {
					String name = objectToJson(props[i].getName());
					String value = objectToJson(props[i].getReadMethod()
							.invoke(bean));
					json.append(name);
					json.append(":");
					json.append(value);
					json.append(",");
				} catch (Exception e) {
				}
			}
			json.setCharAt(json.length() - 1, '}');
		} else {
			json.append("}");
		}
		return json.toString();
	}

	/**
	 * 功能描述:通过传入一个列表对象,调用指定方法将列表中的数据生成一个JSON规格指定字符串
	 * 
	 * @param list
	 *            列表对象
	 * @return java.lang.String
	 */
	public static String listToJson(List<?> list) {
		StringBuilder json = new StringBuilder();
		json.append("[");
		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append(objectToJson(obj));
				json.append(",");
			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

	public static String listTojsonByTD(List list) {
		StringBuilder json = new StringBuilder();
		json.append("[");

		if (list != null && list.size() > 0) {
			for (Object obj : list) {
				json.append("{");
				String[] litString = obj.toString().split(",");
				for (int i = 0; i < litString.length; i++) {
					if (i == 0) //当为第一条数据时
					{
						json.append("\"" + (litString[i].split("=")[0].substring(1, litString[i].split("=")[0].length())).trim() + "\":\""
								+ litString[i].split("=")[1].trim() + "\",");
					} else if (i == litString.length - 1)//当为最后一条数据时
					{
						json.append("\"" + (litString[i].split("=")[0]).trim() + "\":\""
								+ (litString[i].split("=")[1].substring(0, litString[i].split("=")[1].length()-1)).trim() + "\"},");

					} else {

						json.append("\"" + (litString[i].split("=")[0]).trim() + "\":\""
								+ (litString[i].split("=")[1]).trim() + "\",");

					}
				}
				//json.setCharAt(json.length() - 1, ' ');

			}
			json.setCharAt(json.length() - 1, ']');
		} else {
			json.append("]");
		}
		return json.toString();
	}

}
