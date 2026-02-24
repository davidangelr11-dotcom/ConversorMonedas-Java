package com.conversor.moneda;

import java.util.Map;

public class Moneda {

    private String base;
    private Map<String, Double> rates;

    public String getBase() {
        return base;
    }

    public Map<String, Double> getRates() {
        return rates;
    }
}