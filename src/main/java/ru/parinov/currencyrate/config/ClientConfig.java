package ru.parinov.currencyrate.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "client.impl")
@Data
public class ClientConfig {
    private String baseUrl;
}
