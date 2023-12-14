package com.ssmap.demo.security.auth.filter;

import com.ssmap.demo.security.auth.tokens.TenantAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class TenantAuthenticationFilter extends OncePerRequestFilter {
  private AuthenticationManager authenticationManager;

  public TenantAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {

    String clientId = request.getHeader("X-Client-Id");
    String clientSecret = request.getHeader("X-Client-Secret");
    if(clientId == null || clientSecret == null) {
      this.logger.trace("Did not find client id or client secret in request");
      filterChain.doFilter(request, response);
      return;
    }

    try {
      Authentication authentication = this.authenticationManager.authenticate(new TenantAuthenticationToken(clientId, clientSecret));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      filterChain.doFilter(request, response);
    } catch (Exception e) {
      this.logger.error("Tenant Authentication failed");
      response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
      // If you want to immediatelly return an error response, you can do it like this:
      // response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
      // but you can also just let the request go on
      filterChain.doFilter(request, response);
    }
  }
}
