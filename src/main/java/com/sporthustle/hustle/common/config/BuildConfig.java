package com.sporthustle.hustle.common.config;

import java.util.Properties;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class BuildConfig {

  @Bean
  @ConditionalOnMissingBean(BuildProperties.class)
  BuildProperties buildProperties() {
    return new BuildProperties(new Properties());
  }
}
