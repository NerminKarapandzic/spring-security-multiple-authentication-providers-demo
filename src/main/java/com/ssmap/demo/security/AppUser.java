package com.ssmap.demo.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Collection;
import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

// This would likely be a JPA entity in a real application
@ToString
public class AppUser implements UserDetails {

  public AppUser(String username) {
    this.username = username;
  }

  private String username;
  private String password;
  @Getter
  private String type = "user";

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
  public String getUsername() {
    return username;
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
