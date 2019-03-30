package springboot.lw.core.service.adapter;

import com.rabbitmq.client.Channel;
import springboot.lw.core.model.dto.ExcuteDTO;
import springboot.lw.core.service.MessageDealService;

import java.io.IOException;
import java.util.Map;

public abstract class MessageReceiveAdapter implements MessageDealService {

    @Override
    public final void send(ExcuteDTO excuteDTO) {

    }


}
