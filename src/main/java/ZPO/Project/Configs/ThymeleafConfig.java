package ZPO.Project.Configs;

import ZPO.Project.Routing.APIRoutesName;
import ZPO.Project.Routing.StaticRoutesName;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ThymeleafConfig {

    @Bean
    public StaticRoutesName staticRoutesName() {
        return new StaticRoutesName();
    }
    @Bean
    public APIRoutesName apiRoutesName() {
        return new APIRoutesName();
    }
}
