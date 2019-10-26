package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.log.logMqSender;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.log.dto.LogRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LogSender {

    @Value("${app-config.queue.usuario-log}")
    private String usuarioLogMq;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(LogRequest request) {
        rabbitTemplate.convertAndSend(request);
    }
}
