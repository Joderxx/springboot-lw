package springboot.lw.core.service;


import springboot.lw.core.model.ExcuteParameter;
import springboot.lw.core.model.Result;
import springboot.lw.core.model.request.RequestData;

public interface Excute {

    Result excute(ExcuteParameter parameter) throws Exception;

    boolean excute(RequestData requestData,long tid,long hid) throws Exception;
}
