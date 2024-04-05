package ru.parinov.currencyrate.repository.impl;

import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import ru.parinov.currencyrate.model.Valute;
import ru.parinov.currencyrate.repository.CbrRepository;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathFactory;
import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

@Repository
public class ParserRepository implements CbrRepository {

    Map<String, Double> parsingData;

    public ParserRepository() {
        this.parsingData = new HashMap<>();
    }

    @Override
    public Map<String, Double> parseXmlToMap(String xml){
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
                employee.setNumCode(Integer.valueOf(node.getElementsByTagName("NumCode").item(0).getTextContent()));
                employee.setCharCode(node.getElementsByTagName("CharCode").item(0).getTextContent());
                employee.setNominal(Integer.valueOf(node.getElementsByTagName("Nominal").item(0).getTextContent()));
                employee.setName(node.getElementsByTagName("Name").item(0).getTextContent());
                employee.setValue(Double.valueOf(node.getElementsByTagName("Value").item(0).getTextContent().replace(',','.')));
                employee.setVunitRate(Double.valueOf(node.getElementsByTagName("VunitRate").item(0).getTextContent().replace(',','.')));
                parsingData.put(employee.getCharCode(), employee.getValue());
            }
            return parsingData;
        } catch (Exception exception){
            throw new RuntimeException(exception);
        }
    }
}
