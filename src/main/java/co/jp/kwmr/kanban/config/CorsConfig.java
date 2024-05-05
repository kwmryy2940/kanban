package co.jp.kwmr.kanban.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

// @Configuration
// public class CorsConfig implements WebMvcConfigurer {
//     @Override
//     public void addCorsMappings(CorsRegistry registry) {
//         registry.addMapping("/api/**")  // 適用するパスを指定
//                 .allowedOrigins("http://localhost:8081") // 許可するオリジンを指定
//                 .allowedMethods("GET", "POST", "PUT", "DELETE"); // 許可するHTTPメソッドを指定
//     }
// }
