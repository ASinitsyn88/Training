package com.example.springsecuritydemo.config;

import com.example.springsecuritydemo.security.JwtConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true) // Позволяет указывать permissions над endpoint'ами
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtConfigurer jwtConfigurer;

    @Autowired
    public SecurityConfig(JwtConfigurer jwtConfigurer) {
        this.jwtConfigurer = jwtConfigurer;
    }

    /**
     * Авторизация на основе permissions
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                // Аутентификация при помощи JWT токен не подразумевает создание сессий
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                // Все пользователи имеют доступ в корень проекта
                .antMatchers("/").permitAll()
                .antMatchers("/api/v1/auth/login").permitAll()
                // Каждый запрос
                .anyRequest()
                // Должен быть аутентифицирован
                .authenticated()
                .and()
                .apply(jwtConfigurer);
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /**
     * В БД все пароли хранятся в зашифрованном виде,
     * поэтому нужно уметь шифровать клиентский пароль
     * в формат, в котором хранятся пароли в БД
     * чтобы иметь возможность сравнить клиентский пароль и пароль из БД
     */
    @Bean
    protected PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }
}