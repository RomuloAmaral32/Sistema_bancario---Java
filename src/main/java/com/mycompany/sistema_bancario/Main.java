package com.mycompany.sistema_bancario;

import java.util.Scanner;

import javax.xml.bind.JAXBException;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
 * @author Ian Nakamura Okano Preste
 * @matricula 202335038
 */

public class Main { // main meremente para testes

    public static void main(String[] args) throws IOException {
        String filePath = "src/file/java/com/mycompany/sistema_bancario/usuarios.json";

        // Cria uma instância do UsuarioService
        UsuarioService usuarioService = new UsuarioService(filePath);

        // Carrega os usuários existentes
        // List<Usuario> usuarios = usuarioService.loadFromJson();
        // for (Usuario usuario : usuarios) {
        //     System.out.println("Usuário carregado: " + usuario.getNome() + " (" + usuario.getTipo() + ") - CPF: "
        //             + usuario.getCpf());
        //     System.out.println("Classe do usuário: " + usuario.getClass().getName()); // Verifique a classe real
        // }

        // Caso 1: Cliente com CPF já utilizado por outro cliente
        Cliente clienteDuplicado = new Cliente(
                "Cliente Duplicado",
                "34014199002", // Mesmo CPF de outro cliente
                "senha123",
                "clienteDuplicado@email.com",
                "cliente",
                "36000000",
                "456",
                "0003",
                500.0);
        try {
            usuarioService.adicionarUsuario(clienteDuplicado);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Caso 2: Gerente com CPF já utilizado por um cliente
        Gerente novoGerente = new Gerente(
                "Novo Gerente",
                "34014199002", // Mesmo CPF de um cliente
                "senha123",
                "gerente@email.com",
                "gerente",
                "36000000",
                "456",
                1000.0);
        try {
            usuarioService.adicionarUsuario(novoGerente);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Caso 3: Cliente com CPF único
        Cliente clienteUnico = new Cliente(
                "Cliente Unico",
                "70622788078", // CPF único
                "senha123",
                "unico@email.com",
                "cliente",
                "36000000",
                "456",
                "0004",
                500.0);
        try {
            usuarioService.adicionarUsuario(clienteUnico);
        } catch (Exception e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Scanner scanner = new Scanner(System.in);

        // Cliente cliente1 = new Cliente("João", "161.586.406-70", "senha123",
        // "joao@email.com", "cliente", "12345",
        // "36000000", "22", 5000000.0);
        // Cliente cliente2 = new Cliente("Maria", "161.586.406-70", "senha456",
        // "maria@email.com", "cliente", "67890",
        // "36000000", "22", 3000000.0);
        // Gerente gerente = new Gerente("Carlos", "161.586.406-70", "gerente123",
        // "carlos@email.com", "gerente",
        // "36000000", "22",
        // 2000000.0);

        // boolean continuar = true;
        // while (continuar) {
        // System.out.println("\n--- Menu ---");
        // System.out.println("1. Consultar Saldo");
        // System.out.println("2. Consultar Extrato");
        // System.out.println("3. Transferir Dinheiro");
        // System.out.println("4. Sacar Dinheiro");
        // System.out.println("5. Cadastrar Investimento de Renda Fixa");
        // System.out.println("6. Cadastrar Investimento de Renda Variável");
        // System.out.println("7. Exibir Produtos de Renda Fixa");
        // System.out.println("8. Exibir Produtos de Renda Variável");
        // System.out.println("9. Escolher Investimento");
        // System.out.println("10. Sair");
        // System.out.println("11. Solicitar Crédito");
        // System.out.print("Escolha uma opção: ");
        // int opcao = scanner.nextInt();
        // scanner.nextLine();

        // switch (opcao) {
        // case 1:
        // cliente1.consultarSaldo(null); //
        // break;

        // case 2:
        // cliente1.consultarExtrato(null);
        // break;

        // case 3:
        // System.out.print("Digite o valor a ser transferido: ");
        // double valorTransferencia = scanner.nextDouble();
        // scanner.nextLine();
        // cliente1.transferir(valorTransferencia, cliente2, gerente, null);
        // break;

        // case 4:
        // System.out.print("Digite o valor a ser sacado: ");
        // double valorSaque = scanner.nextDouble();
        // scanner.nextLine();
        // cliente1.sacar(valorSaque, gerente, null);
        // break;

        // case 5:
        // gerente.cadastrarRendaFixa();
        // break;

        // case 6:
        // gerente.cadastrarRendaVariavel();
        // break;

        // case 9:
        // gerente.exibirRendaFixa();
        // gerente.exibirRendaVariavel();
        // cliente1.escolherInvestimento(gerente);
        // break;

        // case 10:
        // continuar = false;
        // System.out.println("Saindo do sistema...");
        // break;

        // case 11:
        // System.out.print("Digite o valor do crédito solicitado: ");
        // double valorCredito = scanner.nextDouble();
        // scanner.nextLine();
        // cliente1.solicitarCredito(valorCredito, gerente, null);
        // break;

        // default:
        // System.out.println("Opção inválida! Tente novamente.");
        // }
        // }

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

        // List<Usuario> usuarios = new ArrayList<>();
        // usuarios.add(new Cliente("Darlan Silva", "34014199002", "123456",
        // "darlan@email.com", "cliente", "36000000", "22", "0001", 1000.0));
        // usuarios.add(new Cliente("Maria Julia", "47712171068", "987654",
        // "mj@email.com", "cliente", "31000000", "01", "0002", 1000.0));
        // usuarios.add(new Caixa("João Santos", "10295176067", "000000",
        // "joao@email.com",
        // "Caixa", "36000000", "22", "001", usuarioService));
        // usuarios.add(
        // new Gerente("Carlos Silva", "71832421023", "776655", "carlos@email.com",
        // "Gerente", "36000000", "22",
        // 1000000.0));

        // JsonHandler<Usuario> jsonHandler = new JsonHandler<>(
        // "src/file/java/com/mycompany/sistema_bancario/usuarios.json");
        // try {
        // jsonHandler.saveToJson(usuarios);
        // System.out.println("Usuários salvos com sucesso!");
        // } catch (IOException e) {
        // System.out.println("Erro ao salvar usuários: " + e.getMessage());
        // }

        // String caminhoArquivoXML =
        // "src/file/java/com/mycompany/sistema_bancario/usuarios.xml";

        // // Salvando os usuários em XML
        // try {
        // XMLHandler.salvarEmXml(usuarios, caminhoArquivoXML);
        // System.out.println("Usuários salvos com sucesso em XML!");
        // } catch (Exception e) {
        // System.out.println("Erro ao salvar usuários em XML: " + e.getMessage());
        // }

        // try {
        // List<Usuario> usuariosCarregados =
        // XMLHandler.carregarDeXml(caminhoArquivoXML);
        // System.out.println("\nUsuários carregados do XML:");
        // for (Usuario usuario : usuariosCarregados) {
        // System.out.println("Nome: " + usuario.getNome() + ", Tipo: " +
        // usuario.getTipo());
        // }
        // } catch (Exception e) {
        // System.out.println("Erro ao carregar usuários do XML: " + e.getMessage());
        // }
    }
}
