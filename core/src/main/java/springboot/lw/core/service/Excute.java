package springboot.lw.core.service;


import springboot.lw.core.model.ExcuteParameter;
import springboot.lw.core.model.Result;

public interface Excute {

    Result excute(ExcuteParameter parameter) throws Exception;
}
