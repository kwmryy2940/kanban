package co.jp.kwmr.kanban.config;

import org.apache.catalina.filters.CorsFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/kanban/api/**") // 適用するパスを指定
                .allowedOrigins("https://kwmryy2940.github.io") // 許可するオリジンを指定
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // 許可するHTTPメソッドを指定
    }
}
