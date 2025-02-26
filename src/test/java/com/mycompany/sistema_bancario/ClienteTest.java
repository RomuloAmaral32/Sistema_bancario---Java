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
    private Cliente cliente2;
    private Cliente cliente3;
    private Gerente gerente;
    private final InputStream originalSystemIn = System.in;
    private final PrintStream originalSystemOut = System.out;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        cliente = new Cliente("Ian Nakamura", "16380276688", "senha123", "ian@email.com", "comum", "1234-5", "Rua A, 123", 1000.0);
        cliente2 = new Cliente("Rômulo do Amaral", "16380276688", "senha123", "rômulo@email.com", "comum", "1234-5", "Rua A, 123", 1000.0);
        cliente3 = new Cliente("Ian Nakamura", "16380276688", "senha123", "ian@email.com", "comum", "1234-5", "Rua A, 123", 1500000.0);
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
    @Test
    public void testConsultarSaldoSenhaCorreta() {
        cliente.consultarSaldo("senha123"); // Passamos a senha diretamente
        // Aqui podemos capturar a saída com ByteArrayOutputStream e verificar se o saldo foi impresso corretamente.
    }

    @Test
    void testTransferenciaBemSucedida() {
        cliente.transferir(500.0, cliente2, gerente, "senha123");
        assertEquals(500.0, cliente.getSaldo());
        assertEquals(1500.0, cliente2.getSaldo());
    }
    @Test
    void testTransferenciaComSaldoInsuficiente() {
        cliente.transferir(3000.0, cliente2, gerente, "senha123");
        assertEquals(1000.0, cliente.getSaldo()); // Saldo não deve mudar
        assertEquals(1000.0, cliente2.getSaldo()); // Saldo do destinatário inalterado
    }

    @Test
    void testTransferenciaComSenhaIncorreta() {
        cliente.transferir(500.0, cliente2, gerente, "senhaErrada");
        assertEquals(1000.0, cliente.getSaldo()); // Saldo não deve mudar
        assertEquals(1000.0, cliente2.getSaldo()); // Saldo do destinatário inalterado
    }
    @Test
    void testTransferenciaAltaExigeReprovacaoGerente() {
        gerente.acompanharTransacao(false, cliente, cliente2, 1500000.0); // Simular reprovação do gerente
        cliente.transferir(1500000.0, cliente2, gerente, "senha123");
        assertEquals(1000.0, cliente.getSaldo()); 
        assertEquals(1000.0, cliente2.getSaldo()); 
    }
    
}
