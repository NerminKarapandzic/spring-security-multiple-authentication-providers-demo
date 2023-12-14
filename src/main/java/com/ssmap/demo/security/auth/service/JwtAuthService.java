package com.ssmap.demo.security.auth.service;

import com.ssmap.demo.security.AppUser;
import com.ssmap.demo.security.auth.tokens.JwtAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class JwtAuthService {

  public JwtAuthenticationToken authenticate(JwtAuthenticationToken jwtAuthenticationToken) {
    // You would usually verify the token, fetch the user details based on the token and set it to the user object
    // but for this demo, we will just populate the user object with dummy data
    if (jwtAuthenticationToken.getToken().equals("valid-token")) {
      AppUser authenticatedUser = new AppUser("John Doe");
      return new JwtAuthenticationToken(jwtAuthenticationToken.getToken(), authenticatedUser);
    }

    return jwtAuthenticationToken;
  }

}
