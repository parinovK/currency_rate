package ru.parinov.currencyrate.repository;

import java.util.Map;

public interface CbrRepository {
    Map<String, Double> parseXmlToMap(String xml);
}
