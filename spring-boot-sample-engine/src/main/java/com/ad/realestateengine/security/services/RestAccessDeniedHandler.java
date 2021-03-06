package com.ad.realestateengine.security.services;


import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import com.ad.realestatemodel.common.ErrorCode;
import com.ad.realestatemodel.common.ErrorResponse;
import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class RestAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
    	ErrorResponse response = new ErrorResponse(ErrorCode.USER_ACCESS_DENIED);
        //response.setMessage(e.getMessage());
    	
    	httpServletResponse.setContentType("application/json");
        OutputStream out = httpServletResponse.getOutputStream();
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(out, response);
        out.flush();
        
    }
}
