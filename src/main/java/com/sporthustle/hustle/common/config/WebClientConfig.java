package com.sporthustle.hustle.common.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;
import reactor.netty.resources.ConnectionProvider;

@Configuration
public class WebClientConfig {

  private final Long READ_TIMEOUT_MS = 3000L;
  private final Long WRITE_TIMEOUT_MS = 3000L;
  private final Integer CONNECTION_TIME_OUT_MS = 3000;
  private final Long RESPONSE_TIMEOUT = 3L;

  @Bean
  public WebClient webClient() {
    HttpClient httpClient =
        HttpClient.create(ConnectionProvider.builder("hustle-webclient").build())
            .doOnConnected(
                connection ->
                    connection
                        .addHandlerLast(
                            new ReadTimeoutHandler(READ_TIMEOUT_MS, TimeUnit.MILLISECONDS))
                        .addHandlerLast(
                            new WriteTimeoutHandler(WRITE_TIMEOUT_MS, TimeUnit.MILLISECONDS)))
            .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, CONNECTION_TIME_OUT_MS)
            .responseTimeout(Duration.ofSeconds(RESPONSE_TIMEOUT));

    return WebClient.builder().clientConnector(new ReactorClientHttpConnector(httpClient)).build();
  }
}
