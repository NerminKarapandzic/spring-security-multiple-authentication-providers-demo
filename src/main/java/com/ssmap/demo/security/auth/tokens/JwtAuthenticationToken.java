package com.ssmap.demo.security.auth.tokens;

import com.ssmap.demo.security.AppUser;
import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class JwtAuthenticationToken implements Authentication {
  private boolean isAuthenticated;
  private AppUser userDetails;
  @Getter
  private final String token;

  // Constructor to be used pre-authentication
  public JwtAuthenticationToken(String token) {
    this.token = token;
  }

  // Constructor to be used after successful authentication
  public JwtAuthenticationToken(String token, AppUser userDetails) {
    this.token = token;
    this.userDetails = userDetails;
    this.isAuthenticated = true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public Object getCredentials() {
    return token;
  }

  @Override
  public Object getDetails() {
    return userDetails;
  }

  @Override
  public Object getPrincipal() {
    return userDetails;
  }

  @Override
  public boolean isAuthenticated() {
    return isAuthenticated;
  }

  @Override
  public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
    throw new IllegalArgumentException("Not supported, use constructor");
  }

  @Override
  public String getName() {
    return userDetails.getUsername();
  }
}
