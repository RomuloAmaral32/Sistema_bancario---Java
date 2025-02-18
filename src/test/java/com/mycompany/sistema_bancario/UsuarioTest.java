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

/**
 *
 * @author Darlan
 */
public class UsuarioTest {

    private static Usuario usuario;

    public UsuarioTest() {
    }

    @BeforeAll
    public static void setUpClass() {
        usuario = new Usuario("Darlan", "24677692084", "123456", "darlan@email.com", "cliente");
    }

    @AfterAll
    public static void tearDownClass() {
    }

    @BeforeEach
    public void setUp() {
    }

    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of getNome method, of class Usuario.
     */
    @Test
    public void testGetNome() {
        System.out.println("getNome");
        String expResult = "Darlan";
        String result = usuario.getNome();
        assertEquals(expResult, result, "O nome do usuário deve ser 'Darlan'.");
    }

    /**
     * Test of setNome method, of class Usuario.
     */
    @Test
    public void testSetNome() {
        System.out.println("setNome");
        String nome = "Darlan silva";
        usuario.setNome(nome);
        assertEquals(nome, usuario.getNome(), "O nome do usuário deve ser 'Darlan silva'.");
    }

    /**
     * Test of getCpf method, of class Usuario.
     */
    @Test
    public void testGetCpf() {
        System.out.println("getCpf");
        String expResult = "24677692084";
        String result = usuario.getCpf();
        assertEquals(expResult, result, "O CPF do usuário deve ser '24677692084'.");
    }

    /**
     * Test of setCpf method, of class Usuario.
     */
    @Test
    public void testSetCpf() {
        System.out.println("setCpf");
        String cpf = "73502780048";
        usuario.setCpf(cpf);
        assertEquals(cpf, usuario.getCpf(), "O CPF do usuário deve ser '73502780048'.");
    }

    /**
     * Test of getSenha method, of class Usuario.
     */
    @Test
    public void testGetSenha() {
        System.out.println("getSenha");
        String expResult = "123456";
        String result = usuario.getSenha();
        assertEquals(expResult, result, "A senha do usuário deve ser '123456'.");
    }

    /**
     * Test of setSenha method, of class Usuario.
     */
    @Test
    public void testSetSenha() {
        System.out.println("setSenha");
        String senha = "senha123";
        usuario.setSenha(senha);
        assertEquals(senha, usuario.getSenha(), "A senha do usuário deve ser 'senha123'.");
    }

    /**
     * Test of getEmail method, of class Usuario.
     */
    @Test
    public void testGetEmail() {
        System.out.println("getEmail");
        String expResult = "darlan@email.com";
        String result = usuario.getEmail();
        assertEquals(expResult, result, "O email do usuário deve ser 'darlan@email.com'.");
    }

    /**
     * Test of setEmail method, of class Usuario.
     */
    @Test
    public void testSetEmail() {
        System.out.println("setEmail");
        String email = "darlan.silva@email.com";
        usuario.setEmail(email);
        assertEquals(email, usuario.getEmail(), "O email do usuário deve ser 'darlan.silva@email.com'.");
    }

    /**
     * Test of getTipo method, of class Usuario.
     */
    @Test
    public void testGetTipo() {
        System.out.println("getTipo");
        String expResult = "cliente";
        String result = usuario.getTipo();
        assertEquals(expResult, result, "O tipo do usuário deve ser 'Cliente'.");
    }

    /**
     * Test of setTipo method, of class Usuario.
     */
    @Test
    public void testSetTipo() {
        System.out.println("setTipo");
        String tipo = "caixa";
        usuario.setTipo(tipo);
        assertEquals(tipo, usuario.getTipo(), "O tipo do usuário deve ser 'Gerente'.");
    }

    /**
     * Test of login method, of class Usuario.
     */
    @Test
    public void testLogin() {
        // Arrange (Preparação)
        Usuario usuario = new Usuario("João Silva", "73502780048", "senha123", "joao@example.com", "cliente");
        String cpfInserido = "73502780048";
        String senhaInserida = "senha123";

        // Act (Ação)
        boolean result = usuario.login(cpfInserido, senhaInserida);

        // Assert (Verificação)
        assertTrue(result, "O login deve ser bem-sucedido com CPF e senha corretos.");
    }

    @Test
    public void testLoginInvalido() {
        // Arrange (Preparação)
        Usuario usuario = new Usuario("João Silva", "73502780048", "senha123", "joao@example.com", "cliente");
        String cpfInserido = "05484032016"; // CPF incorreto
        String senhaInserida = "senhaerrada"; // Senha incorreta

        // Act (Ação)
        boolean result = usuario.login(cpfInserido, senhaInserida);

        // Assert (Verificação)
        assertFalse(result, "O login deve falhar com CPF ou senha inválidos.");
    }
}
