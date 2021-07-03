package hercerm.contactsapp.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfiguration {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                // Enable CORS globally for the dev server
                registry.addMapping("/api/*").allowedOrigins("http://localhost:8081");

                // This implementation only allows "simple" methods (GET, HEAD, POST). To allow
                // other HTTP methods look at allowedMethods:
                // https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/web/servlet/config/annotation/CorsRegistration.html#allowedMethods-java.lang.String...-
            }
        };
    }
}
