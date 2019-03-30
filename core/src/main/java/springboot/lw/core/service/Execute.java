package springboot.lw.core.service;

import springboot.lw.core.model.request.RequestData;

public interface Execute {

    boolean execute(RequestData requestData,long tid,long hid) throws Exception;
}
