package com.example.ddashmanagement.Security;

import com.example.ddashmanagement.Ennum.RoleUser;
import com.example.ddashmanagement.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.properties.SwaggerUiConfigProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final UserService userService ;
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeHttpRequests(req -> req.requestMatchers("/api/auth/***" , "/api/category/getAll").permitAll()
                       .requestMatchers("/api/Admin").hasAuthority(RoleUser.Admin.name())
                        .requestMatchers("/api/Acheteur").hasAuthority(RoleUser.Acheteur.name())
                        .requestMatchers("/api/Vendeur").hasAuthority(RoleUser.Vendeur.name())
                        .requestMatchers("/api/SupportAnalytics").hasAuthority(RoleUser.supportAnalytics.name())
                        .requestMatchers("/api/SupportEnchereProduit").hasAuthority(RoleUser.supportEnchereProduit.name())
                        .requestMatchers("/api/SupportUtilisateurTransaction").hasAuthority(RoleUser.supportUtilisateurTransaction.name())
                        .anyRequest().authenticated()


    )
                .sessionManagement(manager -> manager.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider()).addFilterBefore(jwtAuthenticationFilter , UsernamePasswordAuthenticationFilter.class);
        return http.build();

    }

@Bean
    public AuthenticationProvider authenticationProvider(){
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userService.userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider ;

}
    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
   public AuthenticationManager authenticationManager(AuthenticationConfiguration config)  throws  Exception{
        return config.getAuthenticationManager();
    }






}
