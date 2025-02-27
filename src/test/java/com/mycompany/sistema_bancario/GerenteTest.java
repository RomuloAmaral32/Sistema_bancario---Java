package com.mycompany.sistema_bancario;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;

public class GerenteTest {
    private Gerente gerente;
    
    @BeforeEach
    void setUp() {
        gerente = new Gerente("Carlos Silva", "16380276688", "senha123", "carlos@email.com", "Gerente", 1000000.0);
    }

    
    @Test
    void testVerificarAcesso() {
        Gerente gerente1 = new Gerente("Carlos Silva", "16380276688", "senha123", "carlos@email.com", "Gerente", 1000000.0);
        Gerente gerente2 = new Gerente("João Souza", "10006426662", "senha456", "joao@email.com", "Gerente", 500000.0);
        
        // Testa que o gerente com nível de acesso >= 1000000 tem acesso
        assertTrue(gerente1.verificarAcesso());
        
        // Testa que o gerente com nível de acesso < 1000000 não tem acesso
        assertFalse(gerente2.verificarAcesso());
    }
    @Test
    void testGetNivelDeAcesso() {
        assertEquals(1000000.0, gerente.getNivelDeAcesso());
    }

    @Test
    void testSetNivelDeAcesso() {
        gerente.setNivelDeAcesso(2000000.0);
        assertEquals(2000000.0, gerente.getNivelDeAcesso());
    }

    @Test
    void testGerenteInicializacao() {
        assertEquals("Carlos Silva", gerente.getNome());
        assertEquals("16380276688", gerente.getCpf());
        assertEquals("carlos@email.com", gerente.getEmail());
        assertEquals(1000000.0, gerente.getNivelDeAcesso());
    }

    @Test
    void testAdicionarRendaFixa() {
        gerente.getRendaFixa().add("CDB - 12% ao ano");
        List<String> rendaFixa = gerente.getRendaFixa();
        assertFalse(rendaFixa.isEmpty());
        assertEquals("CDB - 12% ao ano", rendaFixa.get(0));
    }

    @Test
    void testAdicionarRendaVariavel() {
        gerente.getRendaVariavel().add("Ações - Fundo XYZ");
        List<String> rendaVariavel = gerente.getRendaVariavel();
        assertFalse(rendaVariavel.isEmpty());
        assertEquals("Ações - Fundo XYZ", rendaVariavel.get(0));
    }

    @Test
void testAcompanharTransacaoAutomatizado() {
    Cliente cliente = new Cliente("João", "16380276688", "senha456", "joao@email.com", "Cliente", "1234-5", "Rua A, 123", 5000.0);
    Cliente destinatario = new Cliente("Maria", "10006426662", "senha789", "maria@email.com", "Cliente", "6789-0", "Rua B, 456", 3000.0);

    boolean autorizado = gerente.acompanharTransacao(true, cliente, destinatario, 2000.0);
    assertTrue(autorizado);
}

@Test
void testAnalisarCreditoAutomatizadoAprovado() {
    Cliente cliente = new Cliente("João", "16380276688", "senha456", "joao@email.com", "Cliente", "1234-5", "Rua A, 123", 5000.0);
    boolean aprovado = gerente.analisarCreditoAutomatizado(cliente, 1000000.0, true);
    assertTrue(aprovado, "O crédito deveria ser aprovado automaticamente.");
}

@Test
void testAnalisarCreditoAutomatizadoRejeitado() {
    Cliente cliente = new Cliente("João", "16380276688", "senha456", "joao@email.com", "Cliente", "1234-5", "Rua A, 123", 5000.0);
    boolean aprovado = gerente.analisarCreditoAutomatizado(cliente, 1000000.0, false);
    assertFalse(aprovado, "O crédito deveria ser rejeitado automaticamente.");
}
}
//ian