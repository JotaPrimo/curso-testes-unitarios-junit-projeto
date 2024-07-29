package com.algaworks.junit.blog.negocio;

import com.algaworks.junit.blog.modelo.Editor;
import com.algaworks.junit.blog.modelo.Ganhos;
import com.algaworks.junit.blog.modelo.Post;
import com.algaworks.junit.blog.utilidade.ProcessadorTextoSimples;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraGanhosTest {

    static CalculadoraGanhos calculadora;
    Editor autor;
    Post post;

    @BeforeAll
    static void beforeAll() {
        calculadora = new CalculadoraGanhos(new ProcessadorTextoSimples(), BigDecimal.TEN);
    }

    @BeforeEach
    void beforeEach() {
        autor = new Editor(1L, "Jota Santos", "jotasantos@gmail", new BigDecimal(5), true);
        post = new Post(1L, "Java Spring", "A expressão Lorem ipsum em design gráfico", autor,
                "teste-java", null, false, false);
    }

    @Test
    public void deveCalcularGanhos() {
        Ganhos ganho = calculadora.calcular(post);

        assertEquals(BigDecimal.valueOf(45), ganho.getTotalGanho());
        assertEquals(7, ganho.getQuantidadePalavras());
        assertEquals(autor.getValorPagoPorPalavra(), ganho.getValorPagoPorPalavra());
    }

    @Test
    public void deveCalcularGanhosSemPremium() {
        autor.setPremium(false);

        Ganhos ganho = calculadora.calcular(post);

        assertEquals(BigDecimal.valueOf(35), ganho.getTotalGanho());
        assertEquals(7, ganho.getQuantidadePalavras());
        assertEquals(autor.getValorPagoPorPalavra(), ganho.getValorPagoPorPalavra());
    }
}