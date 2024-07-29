package com.algaworks.junit.utilidade;

import java.math.BigDecimal;

public class ContaBancaria {

    private BigDecimal saldo;

    public ContaBancaria(BigDecimal saldo) {
        //TODO 1 - validar saldo: não pode ser nulo, caso seja, deve lançar uma IllegalArgumentException
        SimpleValidate.isNull(saldo);

        //TODO 2 - pode ser zero ou negativo
        setSaldo(saldo);
    }

    public void saque(BigDecimal valor) {
        //TODO 1 - validar valor: não pode ser nulo, zero ou menor que zero, caso seja, deve lançar uma IllegalArgumentException
        SimpleValidate.isNullOrLessOrEqualZero(valor);

        //TODO 3 - Se o saldo for insuficiente deve lançar uma RuntimeException
        SimpleValidate.isGreaterThan(valor, getSaldo());

        //TODO 2 - Deve subtrair o valor do saldo
        setSaldo(getSaldo().subtract(valor));

    }

    public void deposito(BigDecimal valor) {
        SimpleValidate.isNullOrLessOrEqualZero(valor);
        //TODO 2 - Deve adicionar o valor ao saldo
        setSaldo(getSaldo().add(valor));
    }

    public BigDecimal saldo() {
        //TODO 1 - retornar saldo
        return getSaldo();
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }
}
