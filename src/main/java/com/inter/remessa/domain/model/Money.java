package com.inter.remessa.domain.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

public record Money(BigDecimal valor, Moeda moeda) {

    public Money {

        //Validaçao dos campos ao criar o obj
        Objects.requireNonNull(valor, "Valor nao pode ser nulo");
        Objects.requireNonNull(moeda, "Moeda nao pode ser nula");

        //Validar valor negativo
        if(valor.compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Valor nao pode ser negativo");
        }

        //Definindo 2 casas decimais e arredondar o valor
        valor = valor.setScale(2, RoundingMode.HALF_UP);
    }

    //Factory Methods
    public static Money of(BigDecimal valor, Moeda moeda){
        return new Money(valor, moeda);
    }

    public static Money zero(Moeda moeda) {
        return new Money(BigDecimal.ZERO, moeda);
    }

    public Money add(Money value){
        validarMesmaMoeda(value);
        return new Money(this.valor.add(value.valor), this.moeda);
    }

    public Money substract(Money value){
        validarMesmaMoeda(value);
        return new Money(this.valor.subtract(value.valor), this.moeda);
    }

    public Money multiply(BigDecimal multiplicador){
        return new Money(this.valor.multiply(multiplicador), this.moeda);
    }

    public boolean isGreaterThanOrEqual(Money outro) {
        validarMesmaMoeda(outro);
        return this.valor.compareTo(outro.valor) >= 0;
    }

    private void validarMesmaMoeda(Money outro) {
        if (this.moeda != outro.moeda) {
            throw new IllegalArgumentException("Moedas diferentes");
        }
    }
}
