package br.com.guiadeprojetoapi.guiadeprojetoapi.modules.log.logMqSender;

import br.com.guiadeprojetoapi.guiadeprojetoapi.modules.log.dto.LogRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class LogSender {

    @Autowired
    private RabbitTemplate template;

    @Value("${app-config.queue.usuario-log}")
    private String usuarioLog;

    @Value("${app-config.key.usuario-log}")
    private String usuarioLogKey;

    public void produceMessage(LogRequest request) {
        template.convertAndSend(usuarioLog, request);
    }

    public void produceMessageKey(LogRequest request) {
        template.convertAndSend("biot-admin.topic", usuarioLogKey, request);
    }
}
