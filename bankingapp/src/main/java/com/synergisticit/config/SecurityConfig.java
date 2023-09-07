package com.synergisticit.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Autowired BCryptPasswordEncoder bCrypt;
	@Autowired UserDetailsService userDetailsService;
	
	/*
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		
		List<UserDetails> users = new ArrayList<>();
		List<GrantedAuthority> authority1 = new ArrayList<>();
		authority1.add(new SimpleGrantedAuthority("Admin"));
		authority1.add(new SimpleGrantedAuthority("User"));
		
		List<GrantedAuthority> authority2 = new ArrayList<>();
		authority2.add(new SimpleGrantedAuthority("HR"));
		authority2.add(new SimpleGrantedAuthority("User"));
		
		List<GrantedAuthority> authority3 = new ArrayList<>();
		authority3.add(new SimpleGrantedAuthority("Programmer"));
		authority3.add(new SimpleGrantedAuthority("User"));
		
		List<GrantedAuthority> authority4 = new ArrayList<>();
		authority4.add(new SimpleGrantedAuthority("User"));
		
		UserDetails user1 = new User("lemar", bCrypt.encode("lemar"), authority1);
		users.add(user1);
		
		UserDetails user2 = new User("garric", bCrypt.encode("garric"), authority1);
		users.add(user2);
		
		UserDetails user3 = new User("alex", bCrypt.encode("alex"), authority2);
		users.add(user3);
		
		UserDetails user4 = new User("ryan", bCrypt.encode("ryan"), authority3);
		users.add(user4);
		
		UserDetails user5 = new User("ramu", bCrypt.encode("ramu"), authority4);
		users.add(user5);
		
		
		
		return new InMemoryUserDetailsManager(users);
		
	}//
	*/

	
	/*
	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		
		http.authorizeHttpRequests().anyRequest().permitAll(); // bypasses all the http security
		return http.build();
	}
	*/
	
	@Bean
	DaoAuthenticationProvider authProvider() {
		
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService);
		//authProvider.setPasswordEncoder(bCrypt);
		authProvider.setPasswordEncoder(NoOpPasswordEncoder.getInstance());
		return authProvider;
	}

}
