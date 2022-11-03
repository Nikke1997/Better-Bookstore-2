package com.example.Bookstore1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.example.Bookstore1.web.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailServiceImpl userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().authenticated().and().formLogin().loginPage("/login")
				.defaultSuccessUrl("/booklist", true).permitAll();
	}

	// @Bean
	// @Override
	// public UserDetailsService userDetailsService() {
	// List<UserDetails> users = new ArrayList();

	// PasswordEncoder passwordEncoder =
	// PasswordEncoderFactories.createDelegatingPasswordEncoder();
	// UserDetails user =
	// User.withUsername("user").password(passwordEncoder.encode("user")).roles("USER").build();

	// users.add(user);

	// user =
	// User.withUsername("admin").password(passwordEncoder.encode("admin")).roles("ADMIN").build();

	// users.add(user);
	// return new InMemoryUserDetailsManager(users);

	// }

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(new BCryptPasswordEncoder());
	}
}
