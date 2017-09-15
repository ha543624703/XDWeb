package org.springside.modules.utils;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.jasig.cas.client.validation.Assertion;

public class CasForInvokeContextFilter implements Filter
{
	public void destroy()
	{
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
	{
		HttpSession session = ((HttpServletRequest) request).getSession();
		if (session != null)
		{
			Assertion assertion = (Assertion) session.getAttribute("_const_cas_assertion_");
			String userName = assertion.getPrincipal().getName();
			if (userName == null || "".equals(userName))
			{
				session.invalidate();
			}
			else
			{
				session.setAttribute("username", userName);
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
	}

	public void init(FilterConfig config) throws ServletException
	{
	}
}
