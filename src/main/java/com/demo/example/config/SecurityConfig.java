package com.demo.example.config;

import javax.annotation.security.PermitAll;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.demo.example.impl.GroupUserDetailService;

@Configuration
@EnableWebSecurity
@EnableWebMvc
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private GroupUserDetailService groupUserDetailsService;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	// For Authentication
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// TODO Auto-generated method stub
		auth.userDetailsService(groupUserDetailsService);
	}

	// For Authorization
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable();
//		http.authorizeRequests().antMatchers("/api/**").permitAll()
//		.and().authorizeRequests().antMatchers("/admin/**").hasAnyRole("ROLE_ADMIN").anyRequest().fullyAuthenticated()
//				.and().httpBasic();
		
//		http
//		.authorizeRequests()
//		.antMatchers("/api/**")
//		.permitAll()
//		.and()
//		.authorizeRequests()
//		.antMatchers("/admin/**")
//		.hasRole("ADMIN")
//		.anyRequest()
//		.authenticated()
//		.and()
//		.httpBasic();
		
		http.authorizeRequests().antMatchers("/api/addStudent").permitAll().and().authorizeRequests()
//		.antMatchers("/v1/api-doc").permitAll().and().authorizeRequests().antMatchers("/api/**").authenticated()
		.and().httpBasic();
	}
	
	 @Override
	    public void configure(WebSecurity web) throws Exception {
	        web.ignoring().antMatchers("/v1/api-docs",
	                                   "/configuration/ui",
	                                   "/swagger-resources/**",
	                                   "/configuration/security",
	                                   "/swagger-ui.html",
	                                   "/webjars/**");
	    }
	
//	@Override
//    public void configure(WebSecurity web) throws Exception {
//        web.ignoring().antMatchers(
//            "/v2/api-docs", "/configuration/ui", "/swagger-resources/**",
//            "/configuration/security", "/swagger-ui.html", "/webjars/**"
//        );
//    }

}
