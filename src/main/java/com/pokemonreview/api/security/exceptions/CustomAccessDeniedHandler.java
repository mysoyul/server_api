package com.pokemonreview.api.security.exceptions;

import com.pokemonreview.api.exceptions.ErrorObject;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException)
            throws IOException, ServletException {

        //Status code (403) indicating the server understood the request but refused to fulfill it.
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        // Set response content type to JSON
        response.setContentType("application/json;charset=UTF-8");

        ObjectMapper mapper = new ObjectMapper();
        ErrorObject errorObject = new ErrorObject();
        errorObject.setStatusCode(HttpServletResponse.SC_FORBIDDEN);
        errorObject.setMessage("요청된 리소스에 대한 유효한 권한이 없습니다!!");

        // Add content to the response
        String json = mapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(errorObject);
        response.getWriter().write(json);


    }
}