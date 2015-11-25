package com.pubtools.sercurity.core2;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ValidationException;


import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.pubtools.rest.Response;


public class SessionTokenAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
	public void commence( HttpServletRequest request, HttpServletResponse response, AuthenticationException authException )
			throws IOException, ServletException {

		// 认证时发生错误
		if ( authException instanceof SessionTokenAuthenticationException ) {


				// 业务异常
				String json = "";
				ObjectMapper mapper = new ObjectMapper();
				json = mapper.writeValueAsString( authException.getCause() );
				response.setStatus(HttpStatus.OK.value());
				response.addHeader( "Content-Type", "application/json;charset=UTF-8" );
				response.getOutputStream().write( json.getBytes( "UTF-8" ) );

				response.flushBuffer();
		} else {
				// 系统错误
				response.setStatus( HttpStatus.INTERNAL_SERVER_ERROR.value() );
				response.flushBuffer();
//				String json = "Error";
//				response.setStatus( HttpStatus.BAD_REQUEST.value() );
//				Response r = new Response().failure(new ResponseEntity<String>("test exception",HttpStatus.BAD_REQUEST));
//				response.getOutputStream().write(r.toString().getBytes( "UTF-8" ));
//				response.flushBuffer();
				
//				throw new ServletException("123");
			}
		}

}
