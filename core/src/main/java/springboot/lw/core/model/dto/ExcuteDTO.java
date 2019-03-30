package springboot.lw.core.model.dto;

import lombok.Data;
import springboot.lw.core.model.request.RequestData;

import java.io.Serializable;
import java.util.UUID;

@Data
public class ExcuteDTO implements Serializable {
    private RequestData requestData;
    private long tid;
    private long hid;
    private String messageId;

    public ExcuteDTO(){
        messageId = UUID.randomUUID().toString();
    }
}
