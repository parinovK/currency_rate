package ru.parinov.currencyrate.service;

import com.thoughtworks.xstream.io.xml.DocumentReader;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import ru.parinov.currencyrate.client.HttpCurrencyDateRateClient;
import ru.parinov.currencyrate.model.Valute;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.*;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;

@Service
public class CbrService {
    private HttpCurrencyDateRateClient client;

    public CbrService(HttpCurrencyDateRateClient client) {
        this.client = client;
    }

    public String requestByCurrencyCode(String code) {
        String xml = client.requestByDate(LocalDate.now());
        Map<String, String> parsingData = parseXmlByCurrentRate(xml);
        return parsingData.getOrDefault(code, null);
    }

    private Map<String,String> parseXmlByCurrentRate(String xml) {//CharCode, Value
        Map<String, String> parsingData = new HashMap<>();
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(new InputSource(new StringReader(xml)));
            XPathFactory xPathFactory = XPathFactory.newInstance();
            XPath path = xPathFactory.newXPath();
            XPathExpression xPathExpression = path.compile("/ValCurs/Valute");
            NodeList nodes = (NodeList) xPathExpression.evaluate(document, XPathConstants.NODESET);
            for (int i = 0; i < nodes.getLength(); i++) {
                Element node = (Element) nodes.item(i);
                Valute employee = new Valute();
                employee.setNumCode(node.getElementsByTagName("NumCode").item(0).getTextContent());
                employee.setCharCode(node.getElementsByTagName("CharCode").item(0).getTextContent());
                employee.setNominal(node.getElementsByTagName("Nominal").item(0).getTextContent());
                employee.setName(node.getElementsByTagName("Name").item(0).getTextContent());
                employee.setValue(node.getElementsByTagName("Value").item(0).getTextContent());
                employee.setVunitRate(node.getElementsByTagName("VunitRate").item(0).getTextContent());
                parsingData.put(employee.getCharCode(), employee.getValue());
            }
            return parsingData;
        } catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
            throw new RuntimeException(e);
        }
    }
}
