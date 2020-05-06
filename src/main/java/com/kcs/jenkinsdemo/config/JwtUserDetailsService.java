package com.kcs.jenkinsdemo.config;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		if (StringUtils.isNotBlank(username) && StringUtils.equals(username, "admin")) {
			return new org.springframework.security.core.userdetails.User(username, encoder.encode("admin"), new ArrayList<GrantedAuthority>());
		}
		
		throw new UsernameNotFoundException(String.format("No user found with username '%s'.", username));    	    
	}
}