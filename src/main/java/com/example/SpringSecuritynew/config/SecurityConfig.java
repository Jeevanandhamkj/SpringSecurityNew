package com.example.SpringSecuritynew.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    @Bean
    UserDetailsService userDetailsService(){
//        UserDetails admin= User.withUsername("admin")
//                .password(encoder.encode("123")).roles("admin").build();
//        UserDetails teacher= User.withUsername("jeeva")
//                .password(encoder.encode("kj")).roles("teacher").build();
//        UserDetails Student= User.withUsername("student")
//                .password(encoder.encode("stu")).roles("Student").build();
        return  new UserDetaildemo();
    }
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
               .authorizeHttpRequests(req->req.requestMatchers("/kj/Hm").hasAuthority("ROLE_admin")
                       .requestMatchers("/kj/Tea").hasAuthority("ROLE_teacher")
                       .requestMatchers("/kj/stu").hasAuthority("ROLE_student")
                       .requestMatchers("/kj/add").permitAll()
                       .requestMatchers("/kj/a").permitAll()
                       .anyRequest().permitAll()
               )
               .formLogin(Customizer.withDefaults()).
        httpBasic(Customizer.withDefaults()).logout(Customizer.withDefaults());
       return http.build();


    }
    @Bean
    AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }
}
