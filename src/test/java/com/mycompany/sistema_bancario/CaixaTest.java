/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package com.mycompany.sistema_bancario;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Darlan Henrique da Costa Silva
 * @matricula 202176038
 */
/**
 *
 * @author Rômulo Ferreira do Amaral
 * @matricula 202335015
 */
/**
 *
 * @author  Ian Nakamura Okano Preste
 * @matricula 202335038
 */

 
public class CaixaTest {
    private static Caixa caixa;
    private static UsuarioService usuarioService;

    public CaixaTest() {
    }

    @BeforeEach
    public void setUpClass() {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Cliente("Joao Carlos", "71832421023", "123456", "joaocarlos@email.com", "cliente", "36000000",
                "22", "0001", 0));
        usuarios.add(
                new Cliente("Mariana Silveira", "10295176067", "987654", "marianasilveira@email.com", "cliente",
                        "36000000", "22", "0002", 0));

        usuarioService = new UsuarioService(usuarios);
        caixa = new Caixa("Darlan", "24677692084", "123456", "darlan@email.com", "caixa", "36000000", "22", "123",
                usuarioService);
    }

    @AfterEach
    public void tearDownClass() {
        caixa = null;
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getNumeroFuncionario method, of class Caixa.
     */
    @Test
    public void testGetNumeroFuncionario() {
        System.out.println("getNumeroFuncionario");
        String expResult = "123";
        String result = caixa.getNumeroFuncionario();
        assertEquals(expResult, result, "O numero de funcionario deve ser '123'.");
    }

    /**
     * Test of setNumeroFuncionario method, of class Caixa.
     */
    @Test
    public void testSetNumeroFuncionario() {
        System.out.println("setNumeroFuncionario");
        String numeroFuncionario = "9988";
        caixa.setNumeroFuncionario(numeroFuncionario);
        assertEquals(numeroFuncionario, caixa.getNumeroFuncionario(), "O numero de funcionario deve ser '9988'.");
    }

    /**
     * Test of deposito method, of class Caixa.
     */
    @Test
    public void testDepositoValido() {
        System.out.println("depositoValido");
        double valor = 5000;
        String contaCliente = "0001";
        caixa.deposito(valor, contaCliente, "123456");
    }

    /**
     * Test of deposito method, of class Caixa.
     */
    @Test
    public void testDepositoInvalido() {
        System.out.println("depositoInvalido");
        double valor = -5000;
        String contaCliente = "0002";
        try {
            caixa.deposito(valor, contaCliente, "987654");
            fail("Deveria ter lançado uma exceção para depósito inválido.");
        } catch (IllegalArgumentException e) {
            assertEquals("Valor inválido para depósito.", e.getMessage());
        }
    }

    /**
     * Test of saque method, of class Caixa.
     */
    @Test
    public void testSaqueValido() {
        System.out.println("saqueValido");
        double valor = 50000;
        String contaCliente = "0001";
        String senhaCliente = "123456";
        boolean resultado = caixa.saque(valor, contaCliente, senhaCliente);
        assertTrue(resultado, "O saque dentro do limite deve ser bem-sucedido.");
    }

    /**
     * Test of saque method, of class Caixa.
     */
    @Test
    public void testSaqueInvalido() {
        System.out.println("saqueInvalido");
        double valor = 1950000;
        String contaCliente = "0002";
        String senhaCliente = "987654";
        boolean resultado = caixa.saque(valor, contaCliente, senhaCliente);
        assertFalse(resultado, "O saque dentro do limite deve falhar.");
    }

    /**
     * Test of transferencia method, of class Caixa.
     */
    @Test
    public void testTransferenciaValida() {
        System.out.println("transferenciaValida");
        double valor = 50000;
        String contaCliente = "0001";
        String senhaCliente = "123456";
        String contaDestino = "0002";
        boolean resultado = caixa.transferencia(valor, contaCliente, senhaCliente, contaDestino);
        assertTrue(resultado, "A transferência dentro do limite deve ser bem-sucedida.");
    }

    /**
     * Test of transferencia method, of class Caixa.
     */
    @Test
    public void testTransferenciaInvalida() {
        System.out.println("transferenciaInvalida");
        double valor = 1950000;
        String contaCliente = "0002";
        String senhaCliente = "987654";
        String contaDestino = "0001";
        boolean resultado = caixa.transferencia(valor, contaCliente, senhaCliente, contaDestino);
        assertFalse(resultado, "A transferência acima do limite deve falhar.");
    }
}
