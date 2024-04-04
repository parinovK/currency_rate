package ru.parinov.currencyrate.client.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import ru.parinov.currencyrate.client.HttpRequest;
import ru.parinov.currencyrate.repository.CbrRepository;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Component
@AllArgsConstructor
public class HttpRequestImpl implements HttpRequest {
    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final String BASE_URL = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=";

    private final CbrRepository repository;

    @Override
    public String requestByCurrencyCode(String charCode, LocalDate date) {
        String sourceXml = requestByDate(date);
        Map<String, String> parseXml = repository.parseXml(sourceXml);
        return parseXml.getOrDefault(charCode, null);
    }

    @Override
    public String requestByDate(LocalDate date) {
        try {
            URL url = new URL(BASE_URL +
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
