package ru.parinov.currencyrate.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Valute { //for XmlModel
    private Integer NumCode;
    private String CharCode;
    private Integer Nominal;
    private String Name;
    private Double Value;
    private Double VunitRate;
}
