package ru.parinov.currencyrate.repository;

import java.time.LocalDate;
import java.util.Map;

public interface CbrRepository {
    Map<String, String> parseXml(String xml);
}
