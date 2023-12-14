package com.ssmap.demo.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Collection;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class AppTenant implements UserDetails {

  public AppTenant(String name, String tenantId) {
    this.tenantId = tenantId;
    this.name = name;
  }

  private String name;
  @Getter
  private String type = "tenant";
  @Getter
  private String tenantId;

  @Override
  @JsonIgnore
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return null;
  }

  @Override
  @JsonIgnore
  public String getPassword() {
    return null;
  }

  @Override
  @JsonProperty("name")
  public String getUsername() {
    return name;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  @JsonIgnore
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  @JsonIgnore
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  @JsonIgnore
  public boolean isEnabled() {
    return false;
  }
}