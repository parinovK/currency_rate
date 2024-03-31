package ru.parinov.currencyrate.service;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;
import java.util.concurrent.ExecutionException;

@Service
public class CbrService {
    private final Cache<LocalDate, Map<String, BigDecimal>> cache;

    public CbrService() {
        this.cache = CacheBuilder.newBuilder().build();
    }

    public BigDecimal requestByCurrencyCode(String code){
        try {
            return cache.get(LocalDate.now(), this::callAllByCurrentDate).get(code);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

    private Map<String, BigDecimal> callAllByCurrentDate() {

    }
}
