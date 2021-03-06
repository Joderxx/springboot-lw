package springboot.lw.coreweb.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import springboot.lw.coreweb.intercept.AllInterception;
import springboot.lw.coreweb.intercept.LoginInterception;

@Configuration
public class InterceptionConfig implements WebMvcConfigurer {

    @Bean
    @Scope("prototype")
    public LoginInterception loginInterception(){
        return new LoginInterception();
    }

    @Bean
    @Scope("prototype")
    public AllInterception allInterception(){
        return new AllInterception();
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(allInterception())
                .addPathPatterns("/**")
                .addPathPatterns("/");
        registry.addInterceptor(loginInterception())
                .addPathPatterns("/user/**")
                .addPathPatterns("/user");
    }
}
