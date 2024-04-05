package ru.parinov.currencyrate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import ru.parinov.currencyrate.config.ClientConfig;

@SpringBootApplication
@EnableConfigurationProperties(ClientConfig.class)
public class CurrencyRateApplication {
    public static void main(String[] args) {
        SpringApplication.run(CurrencyRateApplication.class, args);
    }
}
