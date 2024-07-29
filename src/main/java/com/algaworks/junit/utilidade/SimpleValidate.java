package com.algaworks.junit.utilidade;

import java.math.BigDecimal;

public class SimpleValidate {

    public static void isNull(BigDecimal valor) {
        if (valor == null) {
            throw new IllegalArgumentException("Saldo não pode ser null");
        }
    }

    public static void isNullOrLessOrEqualZero(BigDecimal valor) {
        if (valor == null || valor.compareTo(BigDecimal.valueOf(0)) <= 0) {
            throw new IllegalArgumentException("Valor não pode ser null, nem menor ou igual a 0");
        }
    }

    public static void isGreaterThan(BigDecimal valor, BigDecimal saldo) {
        if (valor.compareTo(saldo) > 0) {
            throw new RuntimeException("Saldo insuficiente");
        }
    }
    public static void isLessZero(BigDecimal valorSaque, BigDecimal saldo) {
        if (valorSaque.compareTo(saldo) > 0) {
            throw new RuntimeException("Saldo insuficiente");
        }
    }
}
