package springboot.lw.core.service;

import com.rabbitmq.client.Channel;
import springboot.lw.core.model.dto.ExcuteDTO;

import java.io.IOException;
import java.util.Map;

public interface MessageDealService {

    void send(ExcuteDTO excuteDTO);

    void receive(ExcuteDTO excuteDTO, Map<String,Object>headers,Channel channel) throws IOException;
}
