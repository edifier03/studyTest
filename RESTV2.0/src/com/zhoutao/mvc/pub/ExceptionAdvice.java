package com.zhoutao.mvc.pub;

import javax.validation.ValidationException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pubtools.rest.Response;
import org.springframework.web.bind.MethodArgumentNotValidException;
import javax.servlet.ServletException;
/**
 * 全局异常处理，首先要放到spring能够扫描到的地方。拦截status + 异常类
 * @author zhoutao
 *
 */
@ControllerAdvice
@ResponseBody
public class ExceptionAdvice { 
	
	
	/** * 404 - Bad Request */
	@ResponseStatus(HttpStatus.BAD_REQUEST) 
	@ExceptionHandler(ValidationException.class) 
	public Response handleValidationException(ValidationException e) { 
		System.out.println(e.getMessage());
		return new Response().failure("validation_exception:"+e.getLocalizedMessage()	); 
	} 
	
	@ResponseStatus(HttpStatus.BAD_REQUEST) 
	@ExceptionHandler(MethodArgumentNotValidException.class) 
	public Response runtimeException(MethodArgumentNotValidException e) { 
		System.out.println(e.getMessage());
//		e.getBindingResult().getFieldError().getDefaultMessage().getAllErrors().get(0).getDefaultMessage()
		return new Response().failure(e.getBindingResult().getFieldError().getDefaultMessage()); 
	} 
	
	/** * 404 - Bad Request */
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) 
	@ExceptionHandler(ServletException.class) 
	public Response handleValidationException2(ServletException e) { 
		System.out.println(e.getMessage());
		return new Response().failure("validation_exception:"+e.getLocalizedMessage()	); 
	} 
	
}