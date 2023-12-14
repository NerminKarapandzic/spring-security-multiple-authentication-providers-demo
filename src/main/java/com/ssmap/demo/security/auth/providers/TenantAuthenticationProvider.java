package com.ssmap.demo.security.auth.providers;

import com.ssmap.demo.security.auth.service.TenantAuthenticationService;
import com.ssmap.demo.security.auth.tokens.TenantAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

public class TenantAuthenticationProvider implements AuthenticationProvider {

  private final TenantAuthenticationService authService;
  public TenantAuthenticationProvider(TenantAuthenticationService authService) {
    this.authService = authService;
  }

  @Override
  public Authentication authenticate(Authentication authentication) throws AuthenticationException {
    return authService.authenticate((TenantAuthenticationToken) authentication);
  }

  @Override
  public boolean supports(Class<?> authentication) {
    return authentication.equals(TenantAuthenticationToken.class);
  }
}
