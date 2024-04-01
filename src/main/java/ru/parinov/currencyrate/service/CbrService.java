package ru.parinov.currencyrate.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;
import ru.parinov.currencyrate.client.HttpCurrencyDateRateClient;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Map;

@Service
public class CbrService {
    private Map<LocalDate, Map<String, BigDecimal>> currencyRateData;
    private HttpCurrencyDateRateClient client;

    public CbrService(HttpCurrencyDateRateClient client) {
        this.client = client;
    }

    public BigDecimal requestByCurrencyCode(String code) {
        String xml = client.requestByDate(LocalDate.now());
        Map<String, BigDecimal> parsingData = parseXmlByCurrentRate(xml);
        //parsingData.put("A", BigDecimal.valueOf(60.0001));
        //return parsingData.getOrDefault(code, null);
        return BigDecimal.valueOf(10.22);
    }

    private Map<String,BigDecimal> parseXmlByCurrentRate(String xml){
        DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder documentBuilder = builderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(xml);
            System.out.println(document.getDocumentElement().getTagName(););
            return null;
        } catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }
}
