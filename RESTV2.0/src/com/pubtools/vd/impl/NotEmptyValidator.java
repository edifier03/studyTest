package com.pubtools.vd.impl;

import java.lang.annotation.Annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ValidationException;

import org.springframework.web.bind.annotation.ExceptionHandler;

import com.pubtools.vd.NotEmpty;




public class NotEmptyValidator implements ConstraintValidator<NotEmpty,Object> {

	@Override
	public void initialize(NotEmpty constraintAnnotation) {
		// TODO Auto-generated method stub
		
	}

//	@Override
//	@ExceptionHandler
//	public boolean isValid(String value, ConstraintValidatorContext context) {
//		boolean result = value!=null&&!value.trim().equals("");
//		System.out.println("result:"+result);
////		if(1==1)
////		throw new ValidationException("123");
//		return result;
//	}
	
	@Override
	@ExceptionHandler
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		boolean result = (value != null);
		return result;
	}

}
