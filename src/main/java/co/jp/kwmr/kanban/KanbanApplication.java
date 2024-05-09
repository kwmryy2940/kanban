package co.jp.kwmr.kanban;

import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class KanbanApplication {


	// @Bean
	// public WebMvcConfigurer corsConfigurer() {
	// 	return new WebMvcConfigurer() {
	// 		@Override
	// 		public void addCorsMappings(CorsRegistry registry) {
	// 			registry.addMapping("/api/v1/**") // 適用するパスを指定
	// 					.allowedOrigins("http://localhost:5174") // 許可するオリジンを指定
	// 					.allowedMethods("GET", "POST", "PUT", "DELETE"); // 許可するHTTPメソッドを指定
	// 		}
	// 	};
	// }

	// @Bean
	// SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	// 	http
	// 			.csrf(csrf -> csrf
	// 					.ignoringRequestMatchers(
	// 							"/api/v1/**"))
	// 			.authorizeHttpRequests(auth -> auth
	// 					.anyRequest().permitAll());
	// 	return http.build();
	// }

	public static void main(String[] args) {
		SpringApplication.run(KanbanApplication.class, args);
	}

}
