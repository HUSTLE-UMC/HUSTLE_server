package com.sporthustle.hustle.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder().group("v1-definition").pathsToMatch("/api/**").build();
  }

  @Bean
  public OpenAPI springShopOpenAPI() {
    Info info = new Info().title("Hustle API").description("Hustle API 명세서입니다.").version("v0.0.1");
    String jwtSchemeName = "Authorization";
    SecurityRequirement securityRequirement = new SecurityRequirement().addList(jwtSchemeName);
    Components components =
        new Components()
            .addSecuritySchemes(
                jwtSchemeName,
                new SecurityScheme()
                    .name(jwtSchemeName)
                    .type(SecurityScheme.Type.HTTP)
                    .scheme("bearer")
                    //                        .bearerFormat("JWT")
                    .in(SecurityScheme.In.HEADER)
                    .name("Authorization"));
    return new OpenAPI().info(info).addSecurityItem(securityRequirement).components(components);
  }
}
