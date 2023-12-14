package com.ssmap.demo.security;

import com.ssmap.demo.security.auth.filter.JwtAuthenticationFilter;
import com.ssmap.demo.security.auth.filter.TenantAuthenticationFilter;
import com.ssmap.demo.security.auth.providers.JwtAuthenticationProvider;
import com.ssmap.demo.security.auth.providers.TenantAuthenticationProvider;
import com.ssmap.demo.security.auth.service.JwtAuthService;
import com.ssmap.demo.security.auth.service.TenantAuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

  @Autowired
  private JwtAuthService jwtAuthService;

  @Autowired
  private TenantAuthenticationService tenantAuthService;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
        .authorizeHttpRequests((authorize) -> {
          authorize.anyRequest().authenticated();
        })
        .formLogin(AbstractHttpConfigurer::disable);

    http.addFilterBefore(new JwtAuthenticationFilter(authenticationManager()), LogoutFilter.class);
    http.addFilterBefore(new TenantAuthenticationFilter(authenticationManager()), JwtAuthenticationFilter.class);

    return http.build();
  }

  @Bean
  public AuthenticationManager authenticationManager() {
    JwtAuthenticationProvider jwtAuthenticationProvider = new JwtAuthenticationProvider(jwtAuthService);
    TenantAuthenticationProvider tenantAuthenticationProvider = new TenantAuthenticationProvider(tenantAuthService);
    return new ProviderManager(jwtAuthenticationProvider, tenantAuthenticationProvider);
  }

}
