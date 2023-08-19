package com.sporthustle.hustle.common.config;

import com.sporthustle.hustle.common.converter.CompetitionTypeRequestConverter;
import com.sporthustle.hustle.common.resolver.AuthUserResolver;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry
        .addMapping("/**")
        .allowedOrigins("http://localhost:8080", "http://localhost:3000") // 허용할 출처
        .allowedMethods("GET", "POST", "PATCH", "DELETE") // 허용할 HTTP method
        .allowCredentials(true) // 쿠키 인증 요청 허용
        .maxAge(3000); // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
  }

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new CompetitionTypeRequestConverter());
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(authUserResolver());
  }

  @Bean
  public AuthUserResolver authUserResolver() {
    return new AuthUserResolver();
  }
}
