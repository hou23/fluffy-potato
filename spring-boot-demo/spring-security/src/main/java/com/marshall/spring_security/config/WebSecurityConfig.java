package com.marshall.spring_security.config;

import com.marshall.spring_security.filter.AfterCsrfFilter;
import com.marshall.spring_security.filter.BeforeLoginFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfFilter;

/**
 * @author yaojie.hou
 * @create 2018/4/26
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final AnyUserDetailsService anyUserDetailsService;

	@Autowired
	public WebSecurityConfig(AnyUserDetailsService anyUserDetailsService) {
		this.anyUserDetailsService = anyUserDetailsService;
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/", "/home", "/register").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.permitAll();

		// 加入自定义的filter
		http.addFilterBefore(new BeforeLoginFilter(), UsernamePasswordAuthenticationFilter.class);
		http.addFilterAfter(new AfterCsrfFilter(), CsrfFilter.class);
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth
		//	.inMemoryAuthentication()
		//		.withUser("user").password("{noop}password").roles("USER");

		// 实现自定义登录校验
		auth
			.userDetailsService(anyUserDetailsService)
				.passwordEncoder(passwordEncoder());
	}

	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
