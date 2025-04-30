package com.realestate.config;

import com.realestate.service.CustomUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailService customUserDetailsService;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    // Security filter chain configuration
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable()  // Disabling CSRF protection (ensure you're fine with this)
            .authorizeHttpRequests()
                .requestMatchers("/auth/**", "/css/**", "/js/**", "/images/**").permitAll() // Public endpoints
                .requestMatchers("/admin/**").hasRole("ADMIN")  // Admin access
                .requestMatchers("/agent/**").hasRole("AGENT")  // Agent access
                .requestMatchers("/seller/**").hasRole("SELLER")  // Seller access
                .requestMatchers("/buyer/**").hasRole("BUYER")  // Buyer access
                .anyRequest().authenticated() // Any other request requires authentication
            .and()
            .formLogin()
                .loginPage("/auth/login")  // Custom login page
                .defaultSuccessUrl("/dashboard", true) // Redirect after successful login
                .permitAll()
            .and()
            .logout()
                .logoutSuccessUrl("/auth/login?logout") // Redirect after logout
                .permitAll()
            .and()
            .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class); // Add JWT filter

        return http.build(); // Use .build() to return SecurityFilterChain
    }

    // Authentication manager bean for JWT-based authentication
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // Password encoder bean using BCrypt
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
