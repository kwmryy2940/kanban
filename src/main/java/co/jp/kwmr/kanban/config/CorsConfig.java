package co.jp.kwmr.kanban.config;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Value("${ORIGIN_URL}")
    private String originUrl;

    @Value("${ORIGIN_LOCAL_URL}")
    private String originLocalUrl;

    @Bean
    public WebMvcConfigurer corConfigurer() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**") // 適用するパスを指定
                        .allowedOrigins(originLocalUrl, originUrl) // 許可するオリジンを指定
                        .allowedMethods("GET", "POST", "PUT", "DELETE"); // 許可するHTTPメソッドを指定
            }
        };
    }
}
