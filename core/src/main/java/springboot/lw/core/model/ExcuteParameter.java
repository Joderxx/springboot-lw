package springboot.lw.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Data
public class ExcuteParameter implements Serializable {

    private RequestParam requestParam;
    private FilterParam filterParam;
}
