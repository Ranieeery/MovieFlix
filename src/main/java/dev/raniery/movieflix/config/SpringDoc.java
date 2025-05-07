package dev.raniery.movieflix.config;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

@Configuration
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer"
)
public class SpringDoc {

    public OpenAPI getOpenAPI() {

        Contact contact = new Contact().name("Raniery").url("https://github.com/Ranieeery/MovieFlix");

        return new OpenAPI().info(new Info()
            .title("Movieflix API")
            .description("Movies in streaming")
            .version("1.0")
            .contact(contact)
        );
    }
}
