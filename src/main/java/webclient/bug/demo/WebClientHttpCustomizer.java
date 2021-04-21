package webclient.bug.demo;

import io.netty.handler.logging.LogLevel;
import org.springframework.boot.web.reactive.function.client.WebClientCustomizer;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.HttpProtocol;
import reactor.netty.http.client.HttpClient;
import reactor.netty.transport.logging.AdvancedByteBufFormat;

@Component
public class WebClientHttpCustomizer implements WebClientCustomizer {

  @Override
  public void customize(final WebClient.Builder webClientBuilder) {
    final var httpClient =
        HttpClient
            .create()
            .wiretap("logger-name", LogLevel.TRACE, AdvancedByteBufFormat.TEXTUAL);
    ;
    webClientBuilder
        .clientConnector(new ReactorClientHttpConnector(httpClient));
  }
}
