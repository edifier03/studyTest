package com.pubtools.rest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.PropertyNamingStrategy;
import org.codehaus.jackson.map.SerializationConfig;
import org.codehaus.jackson.map.annotate.JsonSerialize.Inclusion;
import org.springframework.util.StringUtils;


public class CustomObjectMapper extends ObjectMapper{
	private boolean camelCaseToLowerCaseWithUnderscores = false;
	private String dateFormatPattern;
	
	public String getDateFormatPattern() {
		return dateFormatPattern;
	}
	public void setDateFormatPattern(String dateFormatPattern) {
		this.dateFormatPattern = dateFormatPattern;
	}
	public boolean isCamelCaseToLowerCaseWithUnderscores() {
		return camelCaseToLowerCaseWithUnderscores;
	}
	public void setCamelCaseToLowerCaseWithUnderscores(
			boolean camelCaseToLowerCaseWithUnderscores) {
		this.camelCaseToLowerCaseWithUnderscores = camelCaseToLowerCaseWithUnderscores;
	}
	
	public void init(){
		this.setSerializationInclusion(Inclusion.NON_NULL);
		configure(SerializationConfig.Feature.INDENT_OUTPUT,true);
//		this.setSerializationInclusion( Include.NON_NULL ); //排除空值
//		configure(SerializationFeature.INDENT_OUTPUT,true);//将驼峰转为下划线
		
		//进行日期格式化
		if(camelCaseToLowerCaseWithUnderscores) { 
			setPropertyNamingStrategy(PropertyNamingStrategy.CAMEL_CASE_TO_LOWER_CASE_WITH_UNDERSCORES);
		}
		
		if(dateFormatPattern!=null&&!dateFormatPattern.trim().equals("")){
			DateFormat dateFormat = new SimpleDateFormat(dateFormatPattern);
			setDateFormat(dateFormat);
		}
		
	}
	
	

}
