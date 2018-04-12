package com.example.demo.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 認証が必要なリソースに未認証でアクセスしたときの処理
 */
@Slf4j
public class SimpleAuthenticationEntryPoint implements AuthenticationEntryPoint {

    public SimpleAuthenticationEntryPoint() {
    }

    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException exception) throws IOException, ServletException {
        if (response.isCommitted()) {
            log.info("Response has already been committed.");
            return;
        }
        dump(exception);
        response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }

    private void dump(AuthenticationException e) {
        if (e instanceof BadCredentialsException) {
            log.debug("BadCredentialsException : {}", e.getMessage());
        } else if (e instanceof LockedException) {
            log.debug("LockedException : {}", e.getMessage());
        } else if (e instanceof DisabledException) {
            log.debug("DisabledException : {}", e.getMessage());
        } else if (e instanceof AccountExpiredException) {
            log.debug("AccountExpiredException : {}", e.getMessage());
        } else if (e instanceof CredentialsExpiredException) {
            log.debug("CredentialsExpiredException : {}", e.getMessage());
        } else if (e instanceof SessionAuthenticationException) {
            log.debug("SessionAuthenticationException : {}", e.getMessage());
        } else {
            log.debug("AuthenticationException : {}", e.getMessage());
        }
    }
}
