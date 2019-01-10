package com.chengqing.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * session拦截器
 * @author Administrator
 *
 */
public class SystemSessionInterceptor implements HandlerInterceptor{
	
	private static final String LOGIN_URL="/login.jsp"; 
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		 HttpSession session = request.getSession(true); 
		 Object obj = session.getAttribute("userName"); 
	     if (obj==null||"".equals(obj.toString())) { 
	    	 response.sendRedirect(request.getSession().getServletContext().getContextPath()+LOGIN_URL);
	         return false;
	      }
	      return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
