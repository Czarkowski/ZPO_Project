package ZPO.Project.Configs;

import ZPO.Project.PayPal.PayPalUtilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public PayPalUtilities payPalUtilities() {
        return new PayPalUtilities();
    }
}
