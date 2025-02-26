package com.mycompany.sistema_bancario;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.List;


public class ClienteTest {
    private Cliente cliente;
    private Gerente gerente;
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Ian Nakamura", "16380276688", "senha123", "ian@email.com", "comum", "1234-5", "Rua A, 123", 1000.0);
        gerente = new Gerente("Carlos Silva", "16380276688", "senha123", "carlos@email.com", "Gerente", 1000000.0);
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testCriacaoCliente() {
        assertEquals("Ian Nakamura", cliente.getNome());
        assertEquals("16380276688", cliente.getCpf());
        assertEquals("senha123", cliente.getSenha());
        assertEquals("ian@email.com", cliente.getEmail());
        assertEquals("comum", cliente.getTipo());
        assertEquals("1234-5", cliente.getContaBancaria());
        assertEquals("Rua A, 123", cliente.getEndereco());
        assertEquals(1000.0, cliente.getSaldo(), 0.001);
    }


}
