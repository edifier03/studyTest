package com.pubtools.rest;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

public class CorsFilter implements Filter {
	
	private String allowOrigin;
	private String allowMethods;
	private String Credentials;
	private String allowHeaders;
	private String exposeHeaders;
	private String allowCredentials;
	
	@Override
	public void init(FilterConfig fileterConfig) throws ServletException {
		this.allowOrigin =  fileterConfig.getInitParameter("allowOrigin");
		this.allowMethods = fileterConfig.getInitParameter("allowMethods");
		this.allowCredentials = fileterConfig.getInitParameter("allowCredentials");
		this.allowHeaders = fileterConfig.getInitParameter("allowHeaders");
		this.exposeHeaders = fileterConfig.getInitParameter("exposeHeaders");
	}
	
	@Override
	public void doFilter(ServletRequest req, ServletResponse res,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)res;
		/*
		 * 注释掉这部分 为了其他功能的测试方便 
		 */
//		if(this.allowOrigin!=null&&!this.allowOrigin.equals(""))
//		{
//			List allowOriginList = Arrays.asList(allowOrigin.split(","));
//			if(!CollectionUtils.isEmpty(allowOriginList)){
//				String currentOrigin = request.getHeader("Origin");
//				if(allowOriginList.contains(currentOrigin))
//				{
//					response.setHeader("Access-Control-Allow-Origin", currentOrigin);
//				}
//			}
//		}
		//注释上面 加入部分 start
		String currentOrigin = request.getHeader("Origin");
		response.setHeader("Access-Control-Allow-Origin", currentOrigin);
		//注释上面 加入部分 end
		if(this.allowMethods!=null&&!this.allowMethods.equals(""))
		{
			response.setHeader("Access-Control-Allow-Methods", allowMethods);
		}
		
		if(this.allowCredentials!=null&&!this.allowCredentials.equals(""))
		{
			
			
			response.setHeader("Access-Control-Allow-Credentials", allowCredentials);
		}
		
		if(this.allowHeaders!=null&&!this.allowHeaders.equals(""))
		{
			response.setHeader("Access-Control-Allow-Headers", allowHeaders);
		}
		
		if(this.exposeHeaders!=null&&!this.exposeHeaders.equals(""))
		{
			response.setHeader("Access-Control-Expose-Headers", "true");
		}

		chain.doFilter(req, res);
	}


	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		
	}


	
	

}
