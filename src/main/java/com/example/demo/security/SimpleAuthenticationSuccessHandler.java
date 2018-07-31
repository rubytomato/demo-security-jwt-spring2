package com.example.demo.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * 認証が成功した時の処理
 */
@Slf4j
public class SimpleAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

  final private Algorithm algorithm;

  public SimpleAuthenticationSuccessHandler(String secretKey) {
    Objects.requireNonNull(secretKey, "secret key must be not null");
    this.algorithm = Algorithm.HMAC512(secretKey);
  }

  private static final Long EXPIRATION_TIME = TimeUnit.MINUTES.toMillis(10L);

  @Override
  public void onAuthenticationSuccess(HttpServletRequest request,
                                      HttpServletResponse response,
                                      Authentication auth) {
    if (response.isCommitted()) {
      log.info("Response has already been committed.");
      return;
    }
    setToken(response, generateToken(auth));
    response.setStatus(HttpStatus.OK.value());
    clearAuthenticationAttributes(request);
  }

  private String generateToken(Authentication auth) {
    SimpleLoginUser loginUser = (SimpleLoginUser) auth.getPrincipal();
    Date issuedAt = new Date();
    Date notBefore = new Date(issuedAt.getTime());
    Date expiresAt = new Date(issuedAt.getTime() + EXPIRATION_TIME);
    String token = JWT.create()
        .withIssuedAt(issuedAt)
        .withNotBefore(notBefore)
        .withExpiresAt(expiresAt)
        .withSubject(loginUser.getUser().getId().toString())
        .sign(this.algorithm);
    log.debug("generate token : {}", token);
    return token;
  }

  private void setToken(HttpServletResponse response, String token) {
    response.setHeader("Authorization", String.format("Bearer %s", token));
  }

  /**
   * Removes temporary authentication-related data which may have been stored in the
   * session during the authentication process.
   */
  private void clearAuthenticationAttributes(HttpServletRequest request) {
    HttpSession session = request.getSession(false);
    if (session == null) {
      return;
    }
    session.removeAttribute(WebAttributes.AUTHENTICATION_EXCEPTION);
  }

}
