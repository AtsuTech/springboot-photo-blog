package com.packt.spring_auth_phot_blog;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

//import org.springframework.security.core.userdetails.User;

import com.packt.spring_auth_phot_blog.service.UserDetailServiceImpl;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final UserDetailServiceImpl userDetailsService;
    public SecurityConfig(UserDetailServiceImpl userDetailsService){
        this.userDetailsService = userDetailsService;
    }

    @Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.formLogin(login -> login //  フォーム認証を使う
        .loginPage("/login") //  自作のログインフォーム画面のを設定
        .defaultSuccessUrl("/demo/auth")//ログイン成功後の遷移先
        .permitAll()) //  フォーム認証画面は認証不要
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/demo/welcome","/user/register","/user/add","demo/all","/demo/all/posts").permitAll() //  トップページは認証不要
            .anyRequest().authenticated() //  他のURLはログイン後アクセス可能
        );

		return http.build();
	}



	@Bean
	public AuthenticationManager authenticationManager(
			UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);

		return new ProviderManager(authenticationProvider);
	}

	// @Bean
	// public UserDetailsService userDetailsService() {

    //     UserDetails userDetails = User.withUsername("user")
    //         .password("{bcrypt}$2a$10$dXJ3SW6G7P50lGmMkkmwe.20cQQubK3.HZWzG3YB1tlRy.fqvM/BG")//passwordは"password"
    //         .roles("USER")
    //         .build();

	// 	return new InMemoryUserDetailsManager(userDetails);
	// }

	@Bean
	public PasswordEncoder passwordEncoder() {
		return PasswordEncoderFactories.createDelegatingPasswordEncoder();
	}
}
