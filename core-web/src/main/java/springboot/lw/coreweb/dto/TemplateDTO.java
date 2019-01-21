package springboot.lw.coreweb.dto;

import lombok.Data;
import springboot.lw.core.model.Template;
import springboot.lw.core.model.User;

/**
 * @author xiejiedun on 2019/1/21
 */
@Data
public class TemplateDTO {

    /**
     * 创建模板id
     */
    private long tid;
    /**
     * 名称
     */
    private String name;
    /**
     * 创建时间
     */
    private long createTime;
    /**
     * 是否公开
     */
    private boolean publish;

    /**
     * 描述
     */
    private String description;

    /**
     * 对应user
     */
    private String username;

    private long userId;

    private String sign;

    public static TemplateDTO copy(Template template){
        TemplateDTO templateDTO = new TemplateDTO();
        templateDTO.setTid(template.getTid());
        templateDTO.setName(template.getName());
        templateDTO.setCreateTime(template.getCreateTime());
        templateDTO.setPublish(template.isPublish());
        templateDTO.setDescription(template.getDescription());
        templateDTO.setUsername(template.getUser().getUsername());
        templateDTO.setUserId(template.getUser().getId());
        return templateDTO;
    }
}
