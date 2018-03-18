package com.felipiberdun.order.application.filter;

import com.felipiberdun.order.domain.Customer;
import com.felipiberdun.order.domain.Token;
import com.felipiberdun.order.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.persistence.EntityNotFoundException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Felipi Berdun
 * @since 1.1
 */
public class AuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private AuthenticationService authenticationService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String authorization = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (!StringUtils.isEmpty(authorization)) {
            try {
                final Customer customer = authenticationService.validate(Token.parse(authorization));

                final UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(customer, null, customer.getAuthorities());
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);
            } catch (EntityNotFoundException e) {
                response.setStatus(HttpStatus.NOT_FOUND.value());
                return;
            }
        }

        filterChain.doFilter(request, response);
    }

}
