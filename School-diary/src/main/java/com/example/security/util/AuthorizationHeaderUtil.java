package com.example.security.util;

import jakarta.servlet.http.HttpServletRequest;

public interface AuthorizationHeaderUtil {

    boolean hasAuthorizationToken(HttpServletRequest request);

    String getToken(HttpServletRequest request);

}
