package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.log.logMqSender;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.log.dto.LogRequest;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class LogSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private Queue queue;

    public void send(@Payload LogRequest request) {
        rabbitTemplate.convertAndSend(queue.getName(), request);
    }
}
