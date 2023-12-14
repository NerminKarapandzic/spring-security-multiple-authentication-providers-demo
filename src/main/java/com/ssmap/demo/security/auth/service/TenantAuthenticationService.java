package com.ssmap.demo.security.auth.service;

import com.ssmap.demo.security.AppTenant;
import com.ssmap.demo.security.AppUser;
import com.ssmap.demo.security.auth.tokens.TenantAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
public class TenantAuthenticationService {

  public TenantAuthenticationToken authenticate(TenantAuthenticationToken authentication) {
    // You would probably get the tenant from the database based on provided credentials and authenticate
    // but for this demo, we will just populate the tenant object with dummy data if clientId = tenant-1 and clientSecret = secret
    if (authentication.getClientId().equals("123456") && authentication.getClientSecret().equals("secret")) {
      AppTenant tenant = new AppTenant("Tenant 1", "123456");
      return new TenantAuthenticationToken(tenant);
    }

    return authentication;
  }

}
