package ds.rest.controller;

import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;

public class OpenApiConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI().info(new io.swagger.v3.oas.models.info.Info()
                .title("Internship system DIGITAL SPIRIT")
                .description("Internship system")
                .version("1.0.0")
                .contact(new io.swagger.v3.oas.models.info.Contact()
                        .name("gitlab: voronezhskiy.nikita"))

        );
    }
}
