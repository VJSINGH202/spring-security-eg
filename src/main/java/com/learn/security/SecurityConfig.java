package com.learn.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.logout.LogoutFilter;

import com.learn.security.filter.ForbiddenFilter;
import com.learn.security.filter.RobotAccountConfigurer;
import com.learn.security.filter.RobotAuthenticationProvider;


@Configuration
@EnableWebSecurity
public class SecurityConfig {


	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		
		AuthenticationManager authManager = http.getSharedObject(AuthenticationManager.class);
		
		http.authorizeHttpRequests(authorizeConfig -> {
		                 authorizeConfig.requestMatchers("/publicPage/**").permitAll();
		                 authorizeConfig.anyRequest().authenticated();
		                 })
		 .formLogin(Customizer.withDefaults())
		 .oauth2Login(Customizer.withDefaults())
		 .with(new RobotAccountConfigurer(), Customizer.withDefaults())
		 .addFilterBefore(new ForbiddenFilter(), AuthorizationFilter.class);
		// .addFilterBefore(new RobotAuthenicationFilter(authManager), AuthorizationFilter.class)
		// .exceptionHandling(ex -> {ex.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login"));});
		
		return http.build();
	}

	@Bean
	public UserDetailsService userDetailsService() {
		UserDetails user = User.builder().username("user").password("{noop}password").roles("USER").build();
		UserDetails admin = User.builder().username("admin").password("{noop}password").roles("ADMIN", "USER").build();
		return new InMemoryUserDetailsManager(user, admin);
	}
}