package ru.ecosystem.carsale.app.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity,
                                                   CustomSuccessHandler customSuccessHandler,
                                                   CustomLogoutHandler customLogoutHandler,
                                                   CustomFailureHandler customFailureHandler) throws Exception {
        return httpSecurity.csrf().disable()
                .authorizeRequests()
                .antMatchers("/posts", "/posts/info/*", "/auth/registration").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/auth/authorization")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/auth/authorization")
                .defaultSuccessUrl("/posts", true)
                .failureHandler(customFailureHandler)
                .permitAll()
                .successHandler(customSuccessHandler)
                .and()
                .logout()
                .logoutUrl("/auth/logout")
                .logoutSuccessHandler(customLogoutHandler)
                .and()
                .build();
    }

    @Bean
    public CustomSuccessHandler customSuccessHandler() {
        return new CustomSuccessHandler();
    }

    @Bean
    public CustomLogoutHandler customLogoutHandler() { return new CustomLogoutHandler(); }

    @Bean
    public CustomFailureHandler failureHandler() { return new CustomFailureHandler(); }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
