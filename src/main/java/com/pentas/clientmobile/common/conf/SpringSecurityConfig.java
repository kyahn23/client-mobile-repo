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
//                    .antMatchers("/api/auth/**").permitAll()
                    .antMatchers("/css/**").permitAll()
                    .antMatchers("/fonts/**").permitAll()
                    .antMatchers("/icons/**").permitAll()
                    .antMatchers("/images/**").permitAll()
                    .antMatchers("/js/**").permitAll()
                    .antMatchers("/api/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .cors()
                    .and()
                .oauth2Login()
                    .loginPage("/layer/login")
                    .defaultSuccessUrl("/ssoproc")
                    .failureUrl("/layer/login?notify=socialCan")
                    .userInfoEndpoint()
                        .userService(customOAuth2UserService);
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOrigin("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/api/**", configuration);
        source.registerCorsConfiguration("/css/**", configuration);
        source.registerCorsConfiguration("/fonts/**", configuration);
        source.registerCorsConfiguration("/icons/**", configuration);
        source.registerCorsConfiguration("/images/**", configuration);
        source.registerCorsConfiguration("/js/**", configuration);
        return source;
    }

}
