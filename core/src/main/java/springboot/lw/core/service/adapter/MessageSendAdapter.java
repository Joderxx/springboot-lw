package springboot.lw.core.service.adapter;

import com.rabbitmq.client.Channel;
import springboot.lw.core.model.dto.ExcuteDTO;
import springboot.lw.core.service.MessageDealService;

import java.io.IOException;
import java.util.Map;

public abstract class MessageSendAdapter implements MessageDealService {

    @Override
    public final void receive(ExcuteDTO excuteDTO, Map<String, Object> headers, Channel channel) throws IOException {

    }
}
