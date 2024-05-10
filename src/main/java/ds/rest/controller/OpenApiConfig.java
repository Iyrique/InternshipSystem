package ds.rest.controller;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;

@OpenAPIDefinition(
        info = @Info(
                title = "Internship system DIGITAL SPIRIT",
                description = "Internship system", version = "1.0.0",
                contact = @Contact(
                        name = "gitlab: voronezhskiy.nikita"
                )
        )
)
public class OpenApiConfig {

}
