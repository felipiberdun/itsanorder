package com.felipiberdun.order.application.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public class CorsFilter extends OncePerRequestFilter {

    private final boolean corsEnabled;

    @Autowired
    public CorsFilter(@Value("${api.cors.enabled}") final Boolean corsEnabled) {
        this.corsEnabled = Optional.ofNullable(corsEnabled).orElse(false);
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        if (corsEnabled) {
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN, request.getHeader(HttpHeaders.ORIGIN));
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_CREDENTIALS, "true");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_METHODS, "GET, POST, PUT, PATCH, DELETE, OPTIONS");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_MAX_AGE, "1209600");
            response.setHeader(HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS, "Authorization, Content-Type");
        }

        chain.doFilter(request, response);
    }
}
