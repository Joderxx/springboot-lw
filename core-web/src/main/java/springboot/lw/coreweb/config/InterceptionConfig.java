package springboot.lw.coreweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springboot.lw.coreweb.intercept.LoginInterception;

@Configuration
public class InterceptionConfig implements WebMvcConfigurer {

    @Bean
    @Scope("prototype")
    public LoginInterception loginInterception(){
        return new LoginInterception();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterception())
                .addPathPatterns("/user/**")
                .addPathPatterns("/user");
    }
}
