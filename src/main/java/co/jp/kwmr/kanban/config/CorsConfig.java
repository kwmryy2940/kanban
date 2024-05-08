package co.jp.kwmr.kanban.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // registry.addMapping("/api/**")  // 適用するパスを指定
        registry.addMapping("/kanban/api/**")  // 適用するパスを指定
                // .allowedOrigins("http://localhost:5173") // 許可するオリジンを指定
                // .allowedOrigins("http://localhost:5174") // 許可するオリジンを指定
                // .allowedOrigins("http://localhost:5175/kanban") // 許可するオリジンを指定
                .allowedMethods("GET", "POST", "PUT", "DELETE"); // 許可するHTTPメソッドを指定
    }
}
