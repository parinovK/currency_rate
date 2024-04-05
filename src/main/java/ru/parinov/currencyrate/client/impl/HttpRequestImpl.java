package ru.parinov.currencyrate.client.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.parinov.currencyrate.client.HttpRequest;
import ru.parinov.currencyrate.config.ClientConfig;
import ru.parinov.currencyrate.repository.CbrRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class HttpRequestImpl implements HttpRequest {
    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);

    private final CbrRepository repository;
    private final ClientConfig config;

    @Override
    public Double requestByCurrencyCode(String charCode, LocalDate date) {
        String sourceXml = requestByDate(date);
        Map<String, Double> parseXml = repository.parseXmlToMap(sourceXml);
        return parseXml.getOrDefault(charCode, null);
    }

    @Override
    public String requestByDate(LocalDate date) {
        try {
            URL url = new URL( config.getBaseUrl() +
                    DATE_TIME_FORMATTER.format(date));
            URLConnection connection = url.openConnection();
            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String content;
            while ((content = reader.readLine()) != null){
                response.append(content).append("\n");
            }
            return response.toString();
        } catch (Exception exception) {
            throw new RuntimeException(exception);
        }
    }
}
