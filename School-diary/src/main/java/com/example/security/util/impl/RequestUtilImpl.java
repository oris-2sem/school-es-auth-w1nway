package com.example.security.util.impl;

import com.example.security.util.AuthorizationHeaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;

@Component
public class RequestUtilImpl implements AuthorizationHeaderUtil {

    private static final String AUTHORIZATION_HEADER_NAME = "Authorization";

    private static final String BEARER = "Bearer ";

    @Override
    public boolean hasAuthorizationToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER_NAME);
        return header != null && header.startsWith(BEARER);
    }

    @Override
    public String getToken(HttpServletRequest request) {
        String header = request.getHeader(AUTHORIZATION_HEADER_NAME);
        return header.substring(BEARER.length());
    }
}

