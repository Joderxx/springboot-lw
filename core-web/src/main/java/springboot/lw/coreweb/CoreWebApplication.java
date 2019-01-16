package springboot.lw.coreweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CoreWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(CoreWebApplication.class, args);
    }

}

