package com.sporthustle.hustle.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.GroupedOpenApi;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class SwaggerConfig {

  private final BuildProperties buildProperties;

  private final String JWT_HEADER_NAME = "Authorization";
  private final String JWT_SCHEME = "bearer";
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
                    .in(SecurityScheme.In.HEADER)
                    .name("Authorization"));
    return new OpenAPI().info(info).addSecurityItem(securityRequirement).components(components);

  private List<Server> getServers() {
    /*
    url 을 지정해주지 않으면 실서버에서 스웨거 테스트 시 http 프로토콜로 실행되어 오류 발생으로 테스트가 불가능해짐
    */
    Server productionServer =
        new Server().url("https://api.sport-hustle.com").description("Production Server");

    Server localServer = new Server().url("/").description("Local/Current Server");

    List<Server> servers = new ArrayList<>();
    servers.add(productionServer);
    servers.add(localServer);

    return servers;
  }
  }
}
