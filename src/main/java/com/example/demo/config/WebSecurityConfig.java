package com.example.demo.config;

import com.example.demo.security.CustomLoginFailureHandler;
import com.example.demo.social.SocialUsersDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.social.security.SocialUserDetailsService;
import org.springframework.social.security.SpringSocialConfigurer;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .anonymous().and()
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/", true)
                .loginProcessingUrl("/login/authenticate")
                .failureHandler(new CustomLoginFailureHandler())
            .and()
                .logout()
                .deleteCookies("SESSION")
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login")
            .and()
                .authorizeRequests()
                .antMatchers(
                        "/auth/**",
                        "/login",
                        "/error",
                        "/signup",
                        "/css/**",
                        "/js/**"
                ).permitAll()
                .antMatchers("/**").hasRole("USER")
                .and()
                .apply(new SpringSocialConfigurer());
    }

    @Bean
    public SocialUserDetailsService socialUsersDetailService() {
        return new SocialUsersDetailService(userDetailsService);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        daoAuthenticationProvider.setHideUserNotFoundExceptions(false);
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return userDetailsService;
    }

}
