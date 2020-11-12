package com.pentas.clientmobile.common.conf;

import com.pentas.clientmobile.common.auth.CustomOAuth2UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@RequiredArgsConstructor
@EnableWebSecurity
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    public void configure(WebSecurity web) throws Exception {
        // 허용되어야 할 경로들
        web.ignoring().antMatchers("/resources/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .headers()
                    .frameOptions()
                    .sameOrigin()
                    .and()
                .authorizeRequests()
                    .antMatchers("/").permitAll()
                    .antMatchers("/api/auth/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .cors()
                    .and()
                .oauth2Login()
                    .loginPage("http://localhost:8081/#/login")
                    .defaultSuccessUrl("/ssoproc")
                    .failureUrl("http://localhost:8081/#/login?notify=socialCan")
                    .userInfoEndpoint()
                        .userService(customOAuth2UserService);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("http://localhost:8081");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/auth/**", configuration);
        return source;
    }

}
