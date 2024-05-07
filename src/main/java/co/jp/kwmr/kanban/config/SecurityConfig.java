package co.jp.kwmr.kanban.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

// 参考
// https://enjoy-pglife.com/programming/4554/#PostgresSQL%E3%81%AB%E3%83%86%E3%83%BC%E3%83%96%E3%83%AB%E3%82%92%E7%94%9F%E6%88%90

@EnableWebSecurity
@Configuration
public class SecurityConfig {
  // CSRF認証設定
  @Bean
  SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

    http
        .csrf(csrf -> csrf.disable());
        //     .ignoringRequestMatchers(
        //         "/api/v1/*"))
        //         // "/api/v1/tm_users", "/api/v1/tm_users/{userId}"))
        // .authorizeHttpRequests(auth -> auth
        //     .anyRequest().permitAll());
    return http.build();
  }

}
