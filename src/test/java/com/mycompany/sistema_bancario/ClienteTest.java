package com.mycompany.sistema_bancario;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

public class ClienteTest {
    private Cliente cliente;
    private Cliente destinatario;
    private Gerente gerente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("João", "11122233344", "senha123", "joao@email.com", "Cliente", "12345-6", "Rua A, 100", 5000.0);
        destinatario = new Cliente("Maria", "55566677788", "senha456", "maria@email.com", "Cliente", "78910-1", "Rua B, 200", 3000.0);
        gerente = new Gerente("Carlos Silva", "12345678900", "senha123", "carlos@email.com", "Gerente", 1000000.0);
    }

    @Test
    void testConsultarSaldo() {
        String input = "senha123\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        cliente.consultarSaldo();
        assertEquals(5000.0, cliente.getSaldo(), "O saldo deve ser 5000.0.");
    }


}
