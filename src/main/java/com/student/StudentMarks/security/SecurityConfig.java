package com.student.StudentMarks.security;
//
//
//
//import com.student.StudentMarks.util.JwtFilter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.annotation.*;
//import org.springframework.security.authentication.*;
//import org.springframework.security.config.annotation.authentication.configuration.*;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.core.userdetails.*;
//import org.springframework.security.crypto.password.NoOpPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//
//@Configuration
//public class SecurityConfig {
//
//    @Autowired
//    private CustomUserDetailsService userDetailsService;
//
//    @Autowired
//    private JwtFilter jwtFilter;
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/auth/login").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .sessionManagement(session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                );
//
//        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }
//
//    @Bean
//    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
//        return config.getAuthenticationManager();
//    }
//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return NoOpPasswordEncoder.getInstance(); // Use only for dev/testing
//    }
//
//}
//
//
////@Configuration
////@EnableWebSecurity
////public class SecurityConfig {
////
////    @Bean
////    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
////        http
////                .csrf(csrf -> csrf.disable())
////                .authorizeHttpRequests(auth -> auth
////                        // allow Swagger and H2 console without authentication
////                        .requestMatchers(
////                                "/swagger-ui/**",
////                                "/v3/api-docs/**",
////                                "/h2-console/**"
////                        ).permitAll()
////                        // everything else requires authentication
////                        .anyRequest().authenticated()
////                )
////                // if using JWT, add your JWT filter here
////                .headers(headers -> headers.frameOptions().disable()); // needed for H2 console
////
////        return http.build();
////    }
////}



import com.student.StudentMarks.util.JwtFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll() // ✅ allow Swagger
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );

        http.addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // ✅ New way to expose AuthenticationManager in Spring Security 6
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // ✅ Define authentication provider instead of AuthenticationManagerBuilder
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // ⚠️ NoOp only for testing; replace with BCryptPasswordEncoder in real apps
        return NoOpPasswordEncoder.getInstance();
    }
}
