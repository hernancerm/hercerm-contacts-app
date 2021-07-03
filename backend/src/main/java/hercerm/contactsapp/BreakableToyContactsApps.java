package hercerm.contactsapp;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*
 * SpringDoc documentation:
 * https://springdoc.org/
 *
 * How to use SpringDoc:
 * https://www.baeldung.com/spring-rest-openapi-documentation
 *
 * Swagger annotations:
 * https://javadoc.io/doc/io.swagger.core.v3/swagger-annotations/latest/index.html
 */

@SpringBootApplication
@OpenAPIDefinition(info = @Info(
		title = "Contacts API",
		description = "Provide data and operations to a small Java+SpringBoot+Vue app deployed in Heroku."
))
public class BreakableToyContactsApps {

	public static void main(String[] args) {
		SpringApplication.run(BreakableToyContactsApps.class, args);
	}

}
