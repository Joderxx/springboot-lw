package springboot.lw.core.model;

import lombok.Data;

import java.util.List;

@Data
public class FilterParam {

    private List<String> selectConditions;

    private List<String> filterConditions;
}
