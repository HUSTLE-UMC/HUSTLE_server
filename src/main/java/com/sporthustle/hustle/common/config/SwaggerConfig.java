package com.sporthustle.hustle.common.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import java.util.ArrayList;
import java.util.List;
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
    List<Server> servers = this.getServers();
    Info info = this.getInfo();
    SecurityRequirement securityRequirement = this.getSecurityRequirement();
    Components components = this.getComponents();

    OpenAPI openAPI =
        new OpenAPI().info(info).addSecurityItem(securityRequirement).components(components);

    servers.stream().forEach(server -> openAPI.addServersItem(server));

    return openAPI;
  }

  private List<Server> getServers() {
    /*
    url 을 지정해주지 않으면 실서버에서 스웨거 테스트 시 http 프로토콜로 실행되어 오류 발생으로 테스트가 불가능해짐
    */
    Server productionServer =
        new Server().url("https://api.sport-hustle.com").description("Production Server");

    Server localServer = new Server().url("/").description("Local/Current Server");

    Server frontLocalServer = new Server().url("http://localhost:3000").description("For FrontEnd Server");

    List<Server> servers = new ArrayList<>();
    servers.add(productionServer);
    servers.add(localServer);
    servers.add(frontLocalServer);

    return servers;
  }

  private Info getInfo() {
    String title = "Hustle API";
    String description = "Hustle API 명세서입니다.";
    String version = buildProperties.getVersion();

    return new Info().title(title).description(description).version(version);
  }

  private SecurityRequirement getSecurityRequirement() {
    return new SecurityRequirement().addList(JWT_HEADER_NAME);
  }

  private Components getComponents() {
    SecurityScheme securityScheme =
        new SecurityScheme()
            .name(JWT_HEADER_NAME)
            .type(SecurityScheme.Type.HTTP)
            .scheme(JWT_SCHEME)
            .in(SecurityScheme.In.HEADER);

    return new Components().addSecuritySchemes(JWT_HEADER_NAME, securityScheme);
  }
}
