package com.example.security.config;

import com.example.security.details.UserDetailsServiceImpl;
import com.example.security.filters.JwtAuthenticationFilter;
import com.example.security.filters.JwtAuthorizationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration
public class TokenSecurityConfig {
    private final UserDetailsServiceImpl userDetailsServiceImpl;
    private final PasswordEncoder passwordEncoder;

    private final AuthenticationProvider provider;


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity security,
                                                   JwtAuthorizationFilter jwtAuthorizationFilter,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {

        security.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        security.csrf().disable();

        security.authorizeHttpRequests((authorize) -> authorize
                .requestMatchers("/auth/token").permitAll()
                .requestMatchers(HttpMethod.POST, "/users").permitAll()
                .requestMatchers(HttpMethod.POST,"/tasks").hasAuthority("TEACHER")
                .requestMatchers(HttpMethod.PUT,"/tasks").hasAuthority("TEACHER")
                .requestMatchers(HttpMethod.DELETE,"/tasks").hasAuthority("TEACHER")
                .requestMatchers(HttpMethod.POST,"/lessons").hasAuthority("TEACHER")
                .requestMatchers(HttpMethod.PUT,"/lessons").hasAuthority("TEACHER")
                .requestMatchers(HttpMethod.DELETE,"/lessons").hasAuthority("TEACHER")
                .anyRequest().authenticated());

        security.addFilter(jwtAuthenticationFilter);
        security.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

        return security.build();
    }

    @Autowired
    public void bindUserDetailsServiceAndPasswordEncoder(AuthenticationManagerBuilder builder) throws Exception {
        builder.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder);
        builder.authenticationProvider(provider);
    }

}
