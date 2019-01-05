package springboot.lw.coreweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springboot.lw.core.service.Crawl;
import springboot.lw.core.service.Excute;
import springboot.lw.core.service.imp.CrawlImp;
import springboot.lw.core.service.imp.ExcuteImp;

/**
 * @author xiejiedun on 2019/1/5
 */
@Configuration
public class BeanConfig {

    @Bean
    public Excute excute(){
        return new ExcuteImp();
    }

    @Bean
    public Crawl crawl(){
        return new CrawlImp();
    }
}
