package com.zhoutao.mvc.rest.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import javax.validation.ValidationException;


import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pubtools.rest.Response;
import com.pubtools.sercurity.IgnoreSecurity;
import com.zhoutao.mvc.auth.bean.AuthBean;
import com.zhoutao.mvc.eg.bean.TestBean;
import com.zhoutao.mvc.rest.bean.AdvertiserParam;

@RestController  
public class AdvertiserController {
	/**
	 * json 方式 传输样例 
	 * @throws Exception 
	 */
	@RequestMapping(value="/security/advertiser",method=RequestMethod.GET)
	public @ResponseBody 
		Response getAdvertiser(HttpServletRequest request,HttpServletResponse response) throws Exception{
		System.out.println("here & id =" +request.getHeader("x-token"));
		TestBean bean = new TestBean();
		bean.setId("");
		bean.setName("");
		if("123".equals(""))
		{
			return new Response().failure(new ResponseEntity<String>("test exception",HttpStatus.BAD_REQUEST));
		}
		
		if("121".equals(""))
		{
			throw new ValidationException("123");
		}
		
		return new Response().success(bean);
		
	}
	/**
	 * jsonP 方式 传输样例
	 */
	@RequestMapping(value="/advertiserP/{id}",method=RequestMethod.GET)
	
	public void getAdvertiserP(HttpServletRequest request,HttpServletResponse response,  
		@PathVariable("id") String advertiserId){
		System.out.println("here & id =" +request.getHeader("x-token"));
		TestBean bean = new TestBean();
		bean.setId(advertiserId);
		bean.setName(advertiserId);
		
		ObjectMapper objectMapper = new ObjectMapper();
		String jsonResult  = null;
		try{
			jsonResult = objectMapper.writeValueAsString(bean);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		PrintWriter out = null;
		
		response.setCharacterEncoding("UTF-8");
		
		try{
			out = response.getWriter();
			out.println("callback("+jsonResult+")");
//			out.println(jsonResult);
		}catch(Exception e)
		{
			e.printStackTrace();
		}finally{
			out.close();
		}
		
	}
	
	/*
	 * 自定义validate 
	 */
	@RequestMapping(value="/advValidate",method=RequestMethod.POST)
	@IgnoreSecurity()
	public Response advValidate(@RequestBody @Valid AdvertiserParam advertiserParam) {
		
		System.out.println(advertiserParam.getId());
		System.out.println(advertiserParam.getName());
		AdvertiserParam bean = new AdvertiserParam();
		bean.setId("收到id:"+advertiserParam.getId());
		bean.setName("收到name:"+advertiserParam.getName());

		return new Response().success(bean);
		
	}
	
	@RequestMapping(value="/authLogin",method=RequestMethod.GET)
	@IgnoreSecurity
	public 
		Response authLogin(@RequestBody @Valid AuthBean authBean) {
		System.out.println("userName:"+authBean.getUserName());
		System.out.println("passWord:"+authBean.getPassWord());
		if("zhoutao".equals(authBean.getUserName())
				&&"123".equals(authBean.getPassWord()))
		{
//			String tokenid = tokenManager.createToken(authBean);
//			authBean.setTokenid(tokenid);

			return new Response().success(authBean);
		}else
		{
			return new Response().failure(authBean);
		}
		
		
	}
}
