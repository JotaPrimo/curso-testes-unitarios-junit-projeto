package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


class ContaBancariaTest {

    // nested serve para agrupar metodos de teste dentro de classes
    @Nested
    class  Saque {
        @Test
        void givenValorNull_whenSaque_thenLancaIllegalArgumentException() {
            ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.valueOf(2500));

            assertThrows(IllegalArgumentException.class, () -> {
                contaBancaria.saque(null);
            });
        }

        @ParameterizedTest
        @CsvSource({
                "0", "-3", "-5", "-4", "-1", "-2"
        })
        void givenValorEqualOrLessZero_whenSaque_thenLancaIllegalArgumentException(BigDecimal valorSaque) {
            ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.valueOf(2500));

            assertThrows(IllegalArgumentException.class, () -> {
                contaBancaria.saque(valorSaque);
            });
        }

        @ParameterizedTest
        @CsvSource({
                "2500, 1500, 1000",
                "3000, 1000, 2000",
                "2000, 500, 1500",
                "5000, 2500, 2500",
                "100, 100, 0",
                "2000, 2000, 0"
        })
        void givenSaldo2500_whenSaque1500_thenSaldoEqual1000(BigDecimal saldoInicial, BigDecimal valorSaque, BigDecimal resultadoEsperado) {
            // setup
            ContaBancaria contaBancaria = new ContaBancaria(saldoInicial);

            // ação
            contaBancaria.saque(valorSaque);
            BigDecimal valorObtido = contaBancaria.getSaldo();

            // verificação
            assertEquals(valorObtido, resultadoEsperado, "O saldo não corresponde ao valor esperado após o saque.");
        }

        @ParameterizedTest
        @CsvSource({
                "2500, 1500, 2000",
                "3000, 1000, 1000",
                "2000, 500, 3500",
                "5000, 2500, 2700"
        })
        void givenSaldo_whenSaque_thenSaldoNotEqual(BigDecimal saldoInicial, BigDecimal valorSaque, BigDecimal resultadoEsperado) {
            // setup
            ContaBancaria contaBancaria = new ContaBancaria(saldoInicial);

            // ação
            contaBancaria.saque(valorSaque);
            BigDecimal valorObtido = contaBancaria.getSaldo();

            // verificação
            assertNotEquals(valorObtido, resultadoEsperado, "O saldo correspondeu ao valor esperado após o saque.");
        }
    }

    @Nested
    class Deposito {
        @ParameterizedTest
        @CsvSource({
                "0", "-1", "-2", "-3", "-4"
        })
        void givenValor_whenDeposito_thenLancaIllegalArgumentException(BigDecimal valorSaque) {
            // deposito validar valor: não pode ser nulo, zero ou menor que zero, caso seja, deve lançar uma IllegalArgumentException

            // cenario
            ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.valueOf(1500));

            // ação
            assertThrows(IllegalArgumentException.class, () -> {
                contaBancaria.saque(valorSaque);
            });
        }

        @Test
        void givenValorNull_whenDeposito_thenThrowIllegalArgumentException() {
            ContaBancaria contaBancaria = new ContaBancaria(BigDecimal.valueOf(520));
            assertThrows(IllegalArgumentException.class, () -> {
                contaBancaria.saque(null);
            });
        }

        @ParameterizedTest
        @CsvSource({
                "0, 1500, 1500",
                "10, 1500, 1510",
                "1000, 1500, 2500",
                "1500, 1500, 3000",
        })
        void givenValor_whenDeposito_thenSaldoEqual(BigDecimal saldoInicial, BigDecimal valorDeposito, BigDecimal saldoEsperado) {
            // cenario
            ContaBancaria contaBancaria = new ContaBancaria(saldoInicial);

            // ação
            contaBancaria.deposito(valorDeposito);
            BigDecimal saldoObtido = contaBancaria.getSaldo();

            // assertion
            assertEquals(saldoEsperado, saldoObtido, "Valor do saldo após o depósito diferente do esperado.");
        }

        @Test
        void givenValorNull_whenDeposito_thenLancaIllegalArgumentException() {
            assertThrows(IllegalArgumentException.class, () -> {
                new ContaBancaria(null);
            });
        }
    }
}