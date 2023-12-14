package com.ssmap.demo.controller;

import com.ssmap.demo.security.AppUser;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/users")
public class UsersController {

  @RequestMapping("/me")
  public ResponseEntity<Object> getUser() {
    Object appUser = SecurityContextHolder.getContext().getAuthentication().getDetails();
    return ResponseEntity.ok(appUser);
  }

}
