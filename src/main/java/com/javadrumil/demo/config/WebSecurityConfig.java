package com.javadrumil.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.CustomAutowireConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.javadrumil.demo.security.CustomUserDetailsService;
import com.javadrumil.demo.security.JwtRequestFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private CustomUserDetailsService userDetailsService;
	
	@Bean
	public JwtRequestFilter authenticationJwtRequestFilter() {
		return new JwtRequestFilter();
	}
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/login","/register").permitAll()
			.and()
			.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
			http.addFilterBefore(authenticationJwtRequestFilter(), UsernamePasswordAuthenticationFilter.class);
			http.httpBasic();
	}
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.inMemoryAuthentication()
//			.withUser("drumil").password("12345").authorities("admin")
//			.and()
//			.withUser("vipul").password("12345").authorities("user")
//			.and()
//			.passwordEncoder(NoOpPasswordEncoder.getInstance());
		
//			another approach -->
//			InMemoryUserDetailsManager userDetailsManager = new InMemoryUserDetailsManager();
//			UserDetails user1 = User.withUsername("drumil").password("12345").authorities("admin").build();
//			UserDetails user2 = User.withUsername("vipul").password("12345").authorities("user").build();
//			userDetailsManager.createUser(user1);
//			userDetailsManager.createUser(user2);
			
			auth.userDetailsService(userDetailsService);
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		// TODO Auto-generated method stub
		return super.authenticationManagerBean();
	}


}
