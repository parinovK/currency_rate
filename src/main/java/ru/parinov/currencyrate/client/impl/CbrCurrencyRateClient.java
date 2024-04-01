package ru.parinov.currencyrate.client.impl;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;
import org.springframework.web.util.UriComponentsBuilder;
import org.w3c.dom.Document;
import ru.parinov.currencyrate.client.HttpCurrencyDateRateClient;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLConnection;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Component
public class CbrCurrencyRateClient implements HttpCurrencyDateRateClient {
    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern(DATE_PATTERN);
    private static final String BASE_URL = "https://www.cbr.ru/scripts/XML_daily.asp?date_req=";
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

    private String buildUrlRequest(String baseUrl, LocalDate date) {
        return UriComponentsBuilder.fromHttpUrl(baseUrl)
                .queryParam("date_req", DATE_TIME_FORMATTER.format(date))
                .build().toUriString();
    }
}
