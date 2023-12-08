package ZPO.Project.Configs;

import ZPO.Project.PayPal.PayPalConfigurationInfo;
import ZPO.Project.PayPal.PayPalUtilities;
import ZPO.Project.Routing.APIRoutesName;
import ZPO.Project.Routing.PayPalApiRoutes;
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
    @Bean
    public PayPalConfigurationInfo payPalConfigurationInfo() {
        return new PayPalConfigurationInfo();
    }
    @Bean
    public PayPalApiRoutes payPalApiRoutes() {
        return new PayPalApiRoutes();
    }
}
