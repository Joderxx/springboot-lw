package springboot.lw.core.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class FilterParam implements Serializable {

    private List<String> selectConditions;

    private List<String> filterConditions;
}
