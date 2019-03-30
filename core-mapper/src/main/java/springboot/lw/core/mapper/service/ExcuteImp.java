package springboot.lw.core.mapper.service;


import com.alibaba.dubbo.config.annotation.Service;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import springboot.lw.core.model.dto.ExcuteDTO;
import springboot.lw.core.model.request.RequestData;
import springboot.lw.core.service.Crawl;
import springboot.lw.core.service.Execute;

@Service(interfaceClass = Execute.class)
@Component
@Log4j2
public class ExcuteImp implements Execute {


    @Autowired
    private MessageSendImp messageSend;

    @Override
    public boolean execute(RequestData requestData,long tid,long hid) throws Exception {
        /*threadPool.submit(() -> {
            TemplateHistory history = null;
            try {
                history = templateService.useTemplateHistory(tid,hid);
                history.setSuccess(TemplateHistory.SUCCESS);
                templateService.updateHistory(history);
                List list = dealRequest(requestData);
            }catch (Exception e){
                if (history!=null){
                    history.setSuccess(TemplateHistory.FAIL);
                }
                log.error(e);
            }finally {
                if (history!=null){
                    templateService.updateHistory(history);
                }
            }
        });*/
        ExcuteDTO excuteDTO = new ExcuteDTO();
        excuteDTO.setHid(hid);
        excuteDTO.setRequestData(requestData);
        excuteDTO.setTid(tid);
        messageSend.send(excuteDTO);
        return true;
    }


}
