package com.mycompany.sistema_bancario;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Rômulo Amaral
 * @matricula 202335015
 */

@XmlRootElement

public class Gerente extends Usuario {
    private double nivelDeAcesso; 
    @XmlTransient
    private Scanner scanner;

    private List<String> rendaFixa;
    private List<String> rendaVariavel;

    public Gerente(String nome, String cpf, String senha, String email, String tipo, String cep, String numero,
            double nivelDeAcesso) {
        super(nome, cpf, senha, email, tipo, cep, numero);
        this.nivelDeAcesso = nivelDeAcesso;
        this.scanner = new Scanner(System.in);
        this.rendaFixa = new ArrayList<>();
        this.rendaVariavel = new ArrayList<>();
    }

    
    public boolean verificarAcesso() {
        return nivelDeAcesso >= 1000000.0;
    }

    public void cadastrarRendaFixa() {
        System.out.print("Digite o nome do produto de Renda Fixa: ");
        String nomeProduto = scanner.nextLine();
        System.out.print("Digite a taxa de rendimento (%): ");
        double taxaRendimento = scanner.nextDouble();
        System.out.print("Digite o prazo mínimo para resgatar (meses): ");
        int prazoMinimo = scanner.nextInt();
        System.out.print("Digite o prazo máximo para o investimento (meses): ");
        int prazoMaximo = scanner.nextInt();
        scanner.nextLine();

        String produtoRendaFixa = "Produto: " + nomeProduto + ", Taxa de Rendimento: " + taxaRendimento
                + "%, Prazo Mínimo: " + prazoMinimo + " meses, Prazo Máximo: " + prazoMaximo + " meses";
        rendaFixa.add(produtoRendaFixa);
        System.out.println("Cadastro de Renda Fixa realizado com sucesso!");
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
        rendaVariavel.add(produtoRendaVariavel);
        System.out.println("Cadastro de Renda Variável realizado com sucesso!");
    }

    public void exibirRendaFixa() {
        if (!rendaFixa.isEmpty()) {
            System.out.println("Produtos de Renda Fixa cadastrados:");
            for (String produto : rendaFixa) {
                System.out.println(produto);
            }
        } else {
            System.out.println("Nenhum produto de Renda Fixa cadastrado.");
        }
    }

    public void exibirRendaVariavel() {
        if (!rendaVariavel.isEmpty()) {
            System.out.println("Produtos de Renda Variável cadastrados:");
            for (String produto : rendaVariavel) {
                System.out.println(produto);
            }
        } else {
            System.out.println("Nenhum produto de Renda Variável cadastrado.");
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
    @XmlElement
    public List<String> getRendaFixa() {
        return rendaFixa;
    }
    @XmlElement
    public List<String> getRendaVariavel() {
        return rendaVariavel;
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

}
