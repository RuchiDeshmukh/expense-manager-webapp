package com.expense.manager.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.expense.manager.service.CustomUserDetailsService;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter{

	private final CustomUserDetailsService customUserDetailsService;
	
	
	  @Override public void configure(WebSecurity web) throws Exception { 
		  web
		  	.ignoring() .antMatchers("/h2/**");
	  }
	 
	 
	@Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
            .authorizeRequests()
                // add your resources here. By default, spring security blocks all resources that is not under /resources/**
                .antMatchers(HttpMethod.GET, "/","/resources/**", "/js/**", "/css/**", "/images/**").permitAll()
                // prevent spring security from blocking some pages that doesn't require authentication to be access here.
                .antMatchers("/", "/login","/register","/h2/**").permitAll()
                .anyRequest().authenticated()
            .and()
            // login configuration
            .formLogin()
                .loginPage("/login")// can either be mapping or file
                .permitAll()
                .failureUrl("/login?error=true")
                .defaultSuccessUrl("/expenses")
                .usernameParameter("email")
                .passwordParameter("password")
           .and()
           .logout()
           		.invalidateHttpSession(true)
           		.clearAuthentication(true)
           		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
           		.logoutSuccessUrl("/login?logout")
           		.permitAll()
           .and()
           .csrf().disable();
    }
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsService);
	}
	
	
	
	
}
