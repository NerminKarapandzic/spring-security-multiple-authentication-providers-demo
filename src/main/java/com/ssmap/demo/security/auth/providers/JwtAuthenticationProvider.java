package com.ssmap.demo.security.auth.providers;

import com.ssmap.demo.security.auth.service.JwtAuthService;
import com.ssmap.demo.security.auth.tokens.JwtAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class JwtAuthenticationProvider implements AuthenticationProvider {
  private final JwtAuthService authService;

  public JwtAuthenticationProvider(JwtAuthService service) {
    this.authService = service;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    return authService.authenticate((JwtAuthenticationToken) authentication);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(JwtAuthenticationToken.class);
  }
}
