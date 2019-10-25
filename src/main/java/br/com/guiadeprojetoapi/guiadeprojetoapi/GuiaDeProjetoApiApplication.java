package br.com.guiadeprojetoapi.guiadeprojetoapi;

import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class GuiaDeProjetoApiApplication {

    @Value("${app-config.queue.usuario-log}")
    private String usuarioLogQueue;

    @Bean
    public Queue queue() {
        return new Queue(usuarioLogQueue);
    }

    public static void main(String[] args) {
        System.setProperty("jsse.enableSNIExtension", "false");
        SpringApplication.run(GuiaDeProjetoApiApplication.class, args);
    }
}