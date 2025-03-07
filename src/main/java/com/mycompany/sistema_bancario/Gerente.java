package com.mycompany.sistema_bancario;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

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

@XmlRootElement

public class Gerente extends Usuario {
    private double nivelDeAcesso;
    @XmlTransient
    private Scanner scanner;

    private List<String> rendaFixa;
    private List<String> rendaVariavel;

    private JsonHandler<String> jsonHandlerRendaFixa;
    private JsonHandler<String> jsonHandlerRendaVariavel;

    public Gerente() {
        super();
    }

    public Gerente(String nome, String cpf, String senha, String email, String tipo, String cep, String numero,
            double nivelDeAcesso) {
        super(nome, cpf, senha, email, tipo, cep, numero);
        this.nivelDeAcesso = nivelDeAcesso;
        this.scanner = new Scanner(System.in);
        this.rendaFixa = new ArrayList<>();
        this.rendaVariavel = new ArrayList<>();

        this.jsonHandlerRendaFixa = new JsonHandler<>("src/file/java/com/mycompany/sistema_bancario/rendaFixa.json");
        this.jsonHandlerRendaVariavel = new JsonHandler<>(
                "src/file/java/com/mycompany/sistema_bancario/rendaVariavel.json");
    }

    public boolean verificarAcesso() {
        return nivelDeAcesso >= 1000000.0;
    }

    public void cadastrarRendaFixa(String nomeProduto, double taxaRendimento, int prazoMinimo, int prazoMaximo) {
        String produtoRendaFixa = "Produto: " + nomeProduto + ", Taxa de Rendimento: " + taxaRendimento
                + "%, Prazo Mínimo: " + prazoMinimo + " meses, Prazo Máximo: " + prazoMaximo + " meses";

        try {
            // Adiciona o produto ao arquivo JSON
            jsonHandlerRendaFixa.addSingleData(produtoRendaFixa);
            System.out.println("Cadastro de Renda Fixa realizado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o produto de Renda Fixa: " + e.getMessage());
        }
    }

    public void cadastrarRendaVariavel() {
        System.out.print("Digite o nome do produto de Renda Variável: ");
        String nomeProduto = scanner.nextLine();
        System.out.print("Digite o percentual de risco (%): ");
        double risco = scanner.nextDouble();
        System.out.print("Digite a rentabilidade esperada (%): ");
        double rentabilidade = scanner.nextDouble();
        scanner.nextLine();

        String produtoRendaVariavel = "Produto: " + nomeProduto + ", Risco: " + risco + "%, Rentabilidade Esperada: "
                + rentabilidade + "%";

        try {
            // Adiciona o produto ao arquivo JSON
            jsonHandlerRendaVariavel.addSingleData(produtoRendaVariavel);
            System.out.println("Cadastro de Renda Variável realizado com sucesso!");
        } catch (IOException e) {
            System.out.println("Erro ao salvar o produto de Renda Variável: " + e.getMessage());
        }
    }

    public List<String> getRendaFixa() {
        try {
            return jsonHandlerRendaFixa.loadFromJson(String.class);
        } catch (IOException e) {
            System.out.println("Erro ao carregar produtos de Renda Fixa: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public List<String> getRendaVariavel() {
        try {
            return jsonHandlerRendaVariavel.loadFromJson(String.class);
        } catch (IOException e) {
            System.out.println("Erro ao carregar produtos de Renda Variável: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @XmlElement
    public double getNivelDeAcesso() {
        return nivelDeAcesso;
    }

    @XmlElement
    public void setNivelDeAcesso(double nivelDeAcesso) {
        this.nivelDeAcesso = nivelDeAcesso;
    }

    public boolean acompanharTransacao(Cliente cliente, Cliente destinatario, double valor) {
        System.out.println("Gerente, uma transação de R$" + valor + " foi solicitada.");
        System.out.print("Deseja autorizar a transação? (y/n): ");
        String escolha = scanner.nextLine();

        if (escolha.equalsIgnoreCase("y")) {
            System.out.println("Gerente autorizando transação de R$" + valor + "...");
            return true;
        }
        return false;
    }

    public boolean acompanharSaque(Cliente cliente, double valor) {
        System.out.println("Gerente, um saque de R$" + valor + " foi solicitado.");
        System.out.print("Deseja autorizar o saque? (y/n): ");
        String escolha = scanner.nextLine();

        if (escolha.equalsIgnoreCase("y")) {
            System.out.println("Gerente autorizando saque de R$" + valor + "...");
            return true;
        } else {
            return false;
        }
    }

    public boolean analisarCredito(Cliente cliente, double valor) {
        System.out.println(
                "Gerente, uma solicitação de crédito de R$" + valor + " foi solicitado por:" + cliente.getNome());
        System.out.print("Deseja autorizar o crédito? (y/n): ");
        String escolha = scanner.nextLine();

        if (escolha.equalsIgnoreCase("y")) {
            System.out.println("Gerente concedendo crédito de R$" + valor + "...");
            return true;
        } else {
            return false;
        }

    }
    // dois métodos para o teste testAnalisarCreditoAutomatizadoAprovado e
    // testAnalisarCreditoAutomatizadoRejeitado ~ian

    public boolean acompanharTransacao(boolean decisao, Cliente cliente, Cliente destinatario, double valor) {
        System.out.println("Gerente, uma transação de R$" + valor + " foi solicitada.");
        return decisao;
    }

    public boolean analisarCreditoAutomatizado(Cliente cliente, double valor, boolean aprovado) {
        return aprovado;
    }

    public double getnivelDeAcesso() {
        return nivelDeAcesso;
    }

}
