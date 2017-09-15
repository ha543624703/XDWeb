package org.springside.modules.utils;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.namespace.QName;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class UisForInvokeContextFilter implements Filter
{
	public void destroy()
	{
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	{
		System.out.println("----进入过滤器----");
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		HttpSession session = ((HttpServletRequest) request).getSession();

		String credence = request.getParameter("OrgCredence");// 获取凭据
		String pUserID;
		if (credence != null)
		{
			String endpoint = "http://111.75.211.91:8080/LoginService.asmx";// webservice文件地址()
			String namespace = "http://tempuri.org/";// webservice命名空间

			Service service = new Service();
			try
			{
				Call call = (Call) service.createCall();
				call.setTargetEndpointAddress(new java.net.URL(endpoint));

				call.setOperationName(new QName(namespace, "TestCredence"));// webservice命名空间/发布的方法名

				// "pCredence"参数需要和webservice发布的方法的参数一致
				call.addParameter(new QName(namespace, "pCredence"), org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);

				call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);// 返回参数的类型
				call.setUseSOAPAction(true);
				call.setSOAPActionURI(namespace + "TestCredence");// webservice的命名控件和方法
				pUserID = (String) call.invoke(new Object[] { credence });// 获取到返回的字符串
				if (pUserID.toString().trim() != "")
				{// 凭证是否有效
					/* 跳转页面 */
					session.setAttribute("username", pUserID);
					// httpResponse.sendRedirect(request.getScheme() + "://" +
					// request.getServerName() + ":" + request.getServerPort() +
					// "/JSWeb/QueryWage");
				}
				else
				{
					System.out.print("单点登陆超时，请重新登陆！");
				}
			}
			catch (Exception e)
			{
				System.out.print("出现异常:" + e);
			}
		}
		else
		{
			// response.sendRedirect(request.getContextPath()+"/login.jsp");
		}
		try
		{
			chain.doFilter(request, response);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		catch (ServletException e)
		{
			e.printStackTrace();
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
		// TODO Auto-generated method stub

	}
}
