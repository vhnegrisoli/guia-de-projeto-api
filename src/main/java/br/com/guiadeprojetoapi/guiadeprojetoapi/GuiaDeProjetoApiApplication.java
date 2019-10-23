package br.com.guiadeprojetoapi.guiadeprojetoapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class GuiaDeProjetoApiApplication {

    public static void main(String[] args) {
        System.setProperty("jsse.enableSNIExtension", "false");
        SpringApplication.run(GuiaDeProjetoApiApplication.class, args);
    }
}