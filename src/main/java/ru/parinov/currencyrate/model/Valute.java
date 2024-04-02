package ru.parinov.currencyrate.model;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Getter
@Setter
public class Valute { //for XmlModel
    private String NumCode;
    private String CharCode;
    private String Nominal;
    private String Name;
    private String Value;
    private String VunitRate;
}
