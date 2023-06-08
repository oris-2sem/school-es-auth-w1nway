package com.example.security.filters;

import com.example.security.authentication.RefreshTokenAuthentication;
import com.example.security.details.UserDetailsImpl;
import com.example.security.util.AuthorizationHeaderUtil;
import com.example.security.util.JwtUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

@Component
public class JwtAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    public static final String USERNAME_PARAMETER = "email";

    public static final String AUTHENTICATION_URL = "/auth/token";

    private final ObjectMapper objectMapper;

    private final JwtUtil jwtUtil;

    private final AuthorizationHeaderUtil authorizationHeaderUtil;


    public JwtAuthenticationFilter(AuthenticationConfiguration authenticationConfiguration,
                                   JwtUtil jwtUtil,
                                   ObjectMapper objectMapper,
                                   AuthorizationHeaderUtil authorizationHeaderUtil) throws Exception{
        super(authenticationConfiguration.getAuthenticationManager());
        this.setUsernameParameter(USERNAME_PARAMETER);
        this.setFilterProcessesUrl(AUTHENTICATION_URL);
        this.jwtUtil = jwtUtil;
        this.objectMapper = objectMapper;
        this.authorizationHeaderUtil = authorizationHeaderUtil;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (authorizationHeaderUtil.hasAuthorizationToken(request)) {
            String refreshToken = authorizationHeaderUtil.getToken(request);

            RefreshTokenAuthentication authentication = new RefreshTokenAuthentication(refreshToken);

            return super.getAuthenticationManager().authenticate(authentication);

        } else {
            return super.attemptAuthentication(request, response);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        response.setContentType("application/json");
        GrantedAuthority authority = authResult.getAuthorities().stream().findFirst().orElseThrow();

        String email = ((UserDetailsImpl)authResult.getPrincipal()).getUsername();
        String issuer = request.getRequestURI();

        Map<String, String> tokens = jwtUtil.generateTokens(email, authority.toString(), issuer);

        objectMapper.writeValue(response.getOutputStream(), tokens);
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED);
    }

}
