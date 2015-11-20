package com.pubtools.vd;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

import javax.validation.Constraint;
import javax.validation.Payload;

import com.pubtools.vd.impl.NotEmptyValidator;



@Documented
@Target({ ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE, ElementType.CONSTRUCTOR, ElementType.PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NotEmptyValidator.class)
public @interface NotEmpty {
	String message() default "not_empty";
	
	Class<?>[] groups () default {};
	
	Class<? extends Payload>[] payload() default {};
	
	@interface TestBean{

		NotEmpty[] value();

	}
}
