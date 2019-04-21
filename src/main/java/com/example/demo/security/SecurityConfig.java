package com.example.demo.security;

 

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration; 
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler; 

import com.example.demo.model.users;
import com.example.demo.repository.UserRepo;
import com.example.demo.service.UsersService; 

@Configuration
@EnableWebSecurity
@ComponentScan(basePackageClasses = UsersService.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private UserRepo userRepo;

	@Autowired
	public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordencoder());
	}

	@Override
	    protected void configure(HttpSecurity http) throws Exception {
//      http.sessionManagement().sessionFixation().none();
      http.authorizeRequests()
              .antMatchers("/main**").access("hasAnyRole('ROLE_HO','ROLE_BRANCH')")
              .anyRequest().permitAll()

              .and()
              .headers().frameOptions().disable()

              .and()
              .formLogin().loginPage("/login").usernameParameter("username").passwordParameter("password").successHandler(loginSuccess()).failureUrl("/login?error=1")
 
              .and()
              .exceptionHandling().accessDeniedPage("/403")

              .and()
              .csrf().disable();
   } 
	 
	 @Bean
	    public AuthenticationSuccessHandler loginSuccess(){
	        return (httpServletRequest, httpServletResponse, authentication) -> { 
	            users user = userRepo.findByUserName(((users) authentication.getPrincipal()).getUserName()); 
	            userRepo.save(user);

	            httpServletRequest.getSession().setAttribute("currentUser", user);

	            //set response to OK status
	            httpServletResponse.setStatus(HttpServletResponse.SC_OK);

	            //redirect the user after successfully login
	            httpServletResponse.sendRedirect("main");
	        };
	    }
	
	
	@Bean(name = "passwordEncoder")
	public PasswordEncoder passwordencoder() {
		return new BCryptPasswordEncoder();
	}
}
