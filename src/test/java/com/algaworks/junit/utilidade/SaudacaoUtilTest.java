package com.algaworks.junit.utilidade;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SaudacaoUtilTest {

    @Test
    public void saudar() {
        String saudacao = SaudacaoUtil.saudar(9);
        // o ultimo parametro, a message é para casos de erro
        assertEquals("Bom diads", saudacao, "Strings são diferentes");
    }

    // teste para lançar exception, tem assertions expecifica para isso
    @Test
    public  void deveLancarExeception() {
        assertThrows(IllegalArgumentException.class, () -> { SaudacaoUtil.saudar(-9); });
    }
}