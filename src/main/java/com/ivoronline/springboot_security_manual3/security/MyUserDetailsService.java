package com.ivoronline.springboot_security_manual3.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String enteredUsername) throws UsernameNotFoundException {

    //HARD CODED STORED USER
    String storedUsername = "myuser";
    String storedPassword = "myuserpassword";
    String role           = "ROLE_USER";

    //FIND USER
    if (!storedUsername.equals(enteredUsername)) { throw new UsernameNotFoundException(enteredUsername); }

    //CREATE AUTHORITIES
    List<GrantedAuthority> authorities = new ArrayList<>();
                           authorities.add(new SimpleGrantedAuthority(role));

    //CREATE USER DETAILS OBJECT
    UserDetails userDetails = new User(storedUsername, storedPassword, authorities);

    //RETURN USER
    return userDetails;

  }

}

