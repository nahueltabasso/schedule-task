package scheduletask.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.thymeleaf.dialect.IDialect;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

@Configuration
@EnableScheduling
public class Config {

    @Bean
    public IDialect conditionalCommentDialect() {
        return new Java8TimeDialect();
    }
}
