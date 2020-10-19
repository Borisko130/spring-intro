package spring.intro.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = {
        "spring.intro.service",
        "spring.intro.dao"
})
public class AppConfig {
}
