package springboot.lw.core.model;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ExcuteParameter {

    private RequestParam requestParam;
    private FilterParam filterParam;
}
