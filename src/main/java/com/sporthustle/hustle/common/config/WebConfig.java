package com.sporthustle.hustle.common.config;

import com.sporthustle.hustle.common.converter.CompetitionTypeRequestConverter;
import com.sporthustle.hustle.common.converter.InGameTypeRequestConverter;
import com.sporthustle.hustle.common.resolver.AuthUserResolver;
import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  @Override
  public void addFormatters(FormatterRegistry registry) {
    registry.addConverter(new CompetitionTypeRequestConverter());
    registry.addConverter(new InGameTypeRequestConverter());
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
