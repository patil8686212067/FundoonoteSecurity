package com.fundoonotes.userservice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.fundoonotes.utility.TokenUtils;

public class UserInterceptor {
	@Autowired
	private UserService userService;
	private static final Logger logger = Logger.getLogger(UserInterceptor.class);


	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception 
	{
		try {
			System.out.println("token for jwt user"+TokenUtils.verifyToken(request.getHeader("Authorization")));
			int userId = TokenUtils.verifyToken(request.getHeader("Authorization"));
			request.setAttribute("userId",userId);
			
			
			logger.info("this  is interceptor");
			logger.info("this is the place where you should get logged user");
			//check user null
			User user=userService.getUserById(userId);
			if(user==null)
			{
				return false;
			}
		   } catch (Exception e) 
		      {
			  e.printStackTrace();
			 return false;
	        	}
		  return true;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {

		logger.info("After handling the request");
		System.out.println("After handling the request");

	}

	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {

		logger.info("After rendering the view");
		System.out.println("After rendering the view");
	}
}
