package com.inter.remessa.domain.model;

public enum Moeda {
    BRL("Real Brasileiro", "R$"),
    USD("Dolar Americano", "$");

    private final String name;
    private final String simbol;

    Moeda(String name, String simbol) {
        this.name = name;
        this.simbol = simbol;
    }

    public String getName() {
        return name;
    }

    public String getSimbol() {
        return simbol;
    }
}

