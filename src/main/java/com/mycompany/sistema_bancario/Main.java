package com.mycompany.sistema_bancario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static UsuarioService usuarioService;

    public static void main(String[] args) {

        // String filePath = "usuarios.json";
        // LoginService loginService = new LoginService(filePath);

        // String cpfInserido = "34014199002";
        // String senhaInserida = "senha123";

        // boolean loginValido = loginService.verificarLogin(cpfInserido,
        // senhaInserida);
        // if (loginValido) {
        // System.out.println("Login bem-sucedido!");
        // } else {
        // System.out.println("CPF ou senha inválidos.");
        // }

        // carregar usuarios
        // JsonHandler<Usuario> jsonHandler = new
        // JsonHandler<>("src/file/java/com/mycompany/sistema_bancario/usuarios.json");

        // try {
        // List<Usuario> usuarios = jsonHandler.loadFromJson(Usuario.class);
        // System.out.println("Usuários carregados:");
        // for (Usuario usuario : usuarios) {
        // System.out.println(usuario.getNome() + " - " + usuario.getCpf());
        // }
        // } catch (IOException e) {
        // }

        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Cliente("Darlan Silva", "34014199002", "123456",
                "darlan@email.com", "cliente", "0001", "Rua A, 123 - Belmiro BRaga - MG", 0));
        usuarios.add(new Cliente("Maria Julia", "47712171068", "987654",
                "mj@email.com", "cliente", "0002", "Rua B, 456 - Belmiro BRaga - MG", 0));
        usuarios.add(new Caixa("João Santos", "10295176067", "000000", "joao@email.com",
                "Caixa", "001", usuarioService));
        usuarios.add(new Gerente("Carlos Silva", "71832421023", "776655", "carlos@email.com", "Gerente",
                1500000.0));

        JsonHandler<Usuario> jsonHandler = new JsonHandler<>(
                "src/file/java/com/mycompany/sistema_bancario/usuarios.json");
        try {
            jsonHandler.saveToJson(usuarios);
            System.out.println("Usuários salvos com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar usuários: " + e.getMessage());
        }

    }
}
