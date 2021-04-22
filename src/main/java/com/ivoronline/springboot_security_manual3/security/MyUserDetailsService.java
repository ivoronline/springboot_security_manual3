package com.ivoronline.springboot_security_manual3.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class MyUserDetailsService implements UserDetailsService {

  @Override
  public UserDetails loadUserByUsername(String enteredUsername) throws UsernameNotFoundException {

    //-----------------------------------------------------------------------------------------------
    //MOCK DB: <username, {"password", "ROLE"}>
    HashMap<String, String[]> accounts = new HashMap<>();
                              accounts.put("myuser" , new String[]{"myuserpassword" , "ROLE_USER" });
                              accounts.put("myadmin", new String[]{"myadminpassword", "ROLE_ADMIN"});
    //-----------------------------------------------------------------------------------------------

    //GET USER/ACCOUNT (From DB)
    String[] account = accounts.get(enteredUsername);

    //CHECK IF USER EXISTS
    if (account == null) { throw new UsernameNotFoundException(enteredUsername); }     //Bad credentials

    //GET PASSWORD
    String storedPassword = account[0];

    //GET AUTHORITIES
    List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
                           authorities.add(new SimpleGrantedAuthority(account[1]));

    //CREATE USER DETAILS OBJECT
    UserDetails userDetails = new User(enteredUsername, storedPassword, authorities);

    //RETURN USER DETAILS OBJECT
    return userDetails ;

  }

}

