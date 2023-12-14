package com.ssmap.demo.security.auth.filter;

import com.ssmap.demo.security.auth.tokens.JwtAuthenticationToken;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
  private AuthenticationManager authenticationManager;

  public JwtAuthenticationFilter(AuthenticationManager authenticationManager) {
    this.authenticationManager = authenticationManager;
  }

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    // In case of a jwt you'd want to get the value after Bearer prefix, but this is just an example, we won't use an actual jwt token or actual verification
    String token = request.getHeader("Authorization");
    if(token == null) {
      this.logger.trace("Did not find JWT token in request");
      filterChain.doFilter(request, response);
      return;
    }

    try {
      Authentication authentication = this.authenticationManager.authenticate(new JwtAuthenticationToken(token));
      SecurityContextHolder.getContext().setAuthentication(authentication);
      filterChain.doFilter(request, response);
    } catch (Exception e) {
      this.logger.error("JWT Authentication failed");
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      // If you want to immediatelly return an error response, you can do it like this:
      response.sendError(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase());
      // but you can also just let the request go on and let the next filter handle it
      //filterChain.doFilter(request, response);
    }



  }
}
