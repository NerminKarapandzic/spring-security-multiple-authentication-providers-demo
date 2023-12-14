package com.ssmap.demo.security.auth.tokens;

import com.ssmap.demo.security.AppTenant;
import com.ssmap.demo.security.AppUser;
import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

public class TenantAuthenticationToken implements Authentication {

  private boolean isAuthenticated;
  private AppTenant userDetails;
  @Getter
  private String clientId;
  @Getter
  private String clientSecret;

  // Constructor to be used pre-authentication
  public TenantAuthenticationToken(String clientId, String clientSecret) {
    this.clientId = clientId;
    this.clientSecret = clientSecret;
  }

  // Constructor to be used after successful authentication
  public TenantAuthenticationToken(AppTenant tenant) {
    this.userDetails = tenant;
    this.isAuthenticated = true;
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  public Object getCredentials() {
    return null;
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
