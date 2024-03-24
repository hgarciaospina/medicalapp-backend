package com.hegaro.medicalapp.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;

@OpenAPIDefinition(
        info = @Info(
                title = "MEDICAL Application",
                description = "Registration and follow-up of medical consultations.",
                version = "v1.0",
                contact = @Contact(
                        name = "Engineer. Henry García Ospina",
                        email = "henrygoslingg@gmail.com"
                )
        )
)
public class OpenApiConfig {
}