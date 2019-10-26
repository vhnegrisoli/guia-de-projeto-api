package br.com.guiadeprojetoapi.guiadeprojetoapi;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRabbit
public class GuiaDeProjetoApiApplication {

    public static void main(String[] args) {
        System.setProperty("jsse.enableSNIExtension", "false");
        SpringApplication.run(GuiaDeProjetoApiApplication.class, args);
    }
}