package webclient.bug.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

@Component
public class StartupListener implements
    ApplicationListener<ContextRefreshedEvent> {

  @Autowired
  private WebClient webClientDemo;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
    final var queryResponse =
        this.webClientDemo
            .post()
            .headers(httpHeaders -> httpHeaders
                .add("traceparent", "00-4bf92f3577b34da6a3ce929d0e0e4736-d75597dee50b0cac-01"))
            .retrieve()
            .bodyToMono(String.class)
            .block();
  }
}
