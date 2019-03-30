package springboot.lw.core.mapper.service;


import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springboot.lw.core.model.dto.ExcuteDTO;
import springboot.lw.core.service.adapter.MessageSendAdapter;

@Service
public class MessageSendImp extends MessageSendAdapter {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Override
    public void send(ExcuteDTO excuteDTO) {
        CorrelationData correlation = new CorrelationData();
        correlation.setId(excuteDTO.getMessageId());
        rabbitTemplate.convertAndSend("task-exchange","task",excuteDTO,correlation);
    }
}
