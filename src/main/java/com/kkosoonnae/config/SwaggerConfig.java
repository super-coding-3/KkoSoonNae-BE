package com.kkosoonnae.config;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes("Bearer Token", apiKeySecurityScheme()))
                .addSecurityItem(new SecurityRequirement().addList("Bearer Token"));
    }

    private SecurityScheme apiKeySecurityScheme() {
        return new SecurityScheme()
                .type(SecurityScheme.Type.HTTP)
                .scheme("bearer")
                .bearerFormat("JWT")
                .in(SecurityScheme.In.HEADER)
                .name("Authorization");
    }

    @Bean
    public GroupedOpenApi userApi() {
        return GroupedOpenApi.builder()
                .group("user")
                .pathsToMatch("/api/**")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info()
                        .title("User API")
                        .description("회원 전용 Swagger API 문서")
                        .version("v1")))
                .build();
    }

    @Bean
    public GroupedOpenApi presidentApi() {
        return GroupedOpenApi.builder()
                .group("president")
                .pathsToMatch("/api/president/**")
                .addOpenApiCustomizer(openApi -> openApi.info(new Info()
                        .title("President API")
                        .description("사장 전용 Swagger API 문서")
                        .version("v1")))
                .build();
    }
}
