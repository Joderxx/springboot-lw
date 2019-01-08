package springboot.lw.coreweb.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class ParamConfig extends PropertyPlaceholderConfigurer {

    private Properties props;

    public ParamConfig(){
        super.setLocation(new ClassPathResource("application.properties"));
    }

    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactoryToProcess, Properties props)
            throws BeansException {
        super.processProperties(beanFactoryToProcess, props);
        this.props = props;
    }

    public String getProperty(String key){
        return this.props.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return this.props.getProperty(key, defaultValue);
    }

    public Object setProperty(String key, String value) {
        return this.props.setProperty(key, value);
    }
}


