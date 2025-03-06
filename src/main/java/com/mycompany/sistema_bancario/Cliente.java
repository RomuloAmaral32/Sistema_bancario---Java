package com.mycompany.sistema_bancario;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlTransient;

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
public class Cliente extends Usuario {
    private String contaBancaria;
    private double saldo;
    private List<String> extrato = new ArrayList<>();
    @XmlTransient
    private Scanner scanner;
    private UsuarioService usuarioService;
    public Cliente() {
        super();
    }

    public Cliente(String nome, String cpf, String senha, String email, String tipo, String cep, String numero,
            String contaBancaria, double saldoInicial) {
        super(nome, cpf, senha, email, tipo, cep, numero);
        this.contaBancaria = contaBancaria;
        this.saldo = saldoInicial;
        this.extrato = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        registrarExtrato("Abertura da conta com saldo inicial: R$" + saldoInicial);
    }

    /*
     * private boolean validarSenha() {
     * System.out.print("Digite sua senha para validar a operação: ");
     * String senhaDigitada = scanner.nextLine();
     * return senhaDigitada.equals(super.getSenha());
     * }
     * 
     * public void consultarSaldo() {
     * if (validarSenha()) {
     * System.out.println("Seu saldo é: R$"+ this.saldo);
     * } else {
     * System.out.println("Senha incorreta.");
     * }
     * }
     */

    // ian
    private boolean validarSenha(String senhaTeste) {
        if (senhaTeste != null) { // Se for um teste, usa a senha fornecida
            return senhaTeste.equals(super.getSenha());
        }
        System.out.print("Digite sua senha para validar a operação: ");
        String senhaDigitada = scanner.nextLine();
        return senhaDigitada.equals(super.getSenha());
    }

    public void consultarSaldo(String senhaTeste) {
        if (validarSenha(senhaTeste)) {
            System.out.println("Seu saldo é: R$" + this.saldo);
        } else {
            System.out.println("Senha incorreta.");
        }
    }

    // final

    public void transferir(double valor, Cliente destinatario, Gerente gerente, String senhaTeste) {
        if (validarSenha(senhaTeste)) {
            if (valor >= 1000000.0) {
                if (valor <= this.saldo) {
                    boolean veracidade;
                    veracidade = gerente.acompanharTransacao(this, destinatario, valor);
                    if (veracidade) {
                        this.saldo -= valor;
                        destinatario.receberTransferencia(valor, this);
                        registrarExtrato("Transferência de R$" + valor + " para " + destinatario.getContaBancaria());
                        System.out.println("Transferência realizada com sucesso.");
                    } else {
                        System.out.println("O gerente nao autorizou a sua transferência");
                    }
                } else {
                    System.out.println("Saldo insuficiente para essa transferência.");
                }
            } else if (valor <= this.saldo) {
                this.saldo -= valor;
                destinatario.receberTransferencia(valor, this);
                registrarExtrato("Transferência de R$" + valor + " para " + destinatario.getNome());
                System.out.println("Transferência realizada com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para essa transferência.");
            }
        } else {
            System.out.println("Senha incorreta. Operação cancelada.");
        }
    }

    public void receberTransferencia(double valor, Cliente remetente) {
        this.saldo += valor;
        registrarExtrato("Recebido R$" + valor + " de " + remetente.getNome());
    }

    public void sacar(double valor, Gerente gerente, String senhaTeste) {
        if (validarSenha(senhaTeste)) {
            if (valor >= 1000000.0) {
                boolean veracidadesaque;
                veracidadesaque = gerente.acompanharSaque(this, valor);
                if (veracidadesaque) {
                    this.saldo -= valor;
                    registrarExtrato("Saque de R$" + valor);
                    System.out.println("Saque de R$" + valor + " realizado com sucesso.");
                } else {
                    System.out.println("O gerente não autorizou o seu saque");
                }
            } else if (valor <= this.saldo) {
                this.saldo -= valor;
                registrarExtrato("Saque de R$" + valor);
                System.out.println("Saque de R$" + valor + " realizado com sucesso.");
            } else {
                System.out.println("Saldo insuficiente para esse saque.");
            }
        } else {
            System.out.println("Senha incorreta. Operação cancelada.");
        }
    }

    public void consultarExtrato(String senhaTeste) {
        if (validarSenha(senhaTeste)) {
            System.out.println("Extrato da conta " + this.contaBancaria + ":");
            for (String registro : extrato) {
                System.out.println(registro);
            }
        } else {
            System.out.println("Senha incorreta. Operação cancelada.");
        }
    }

    public void escolherInvestimento(Gerente gerente) {
        System.out.println("Opções de Investimentos em Renda Fixa:");
        List<String> rendaFixa = gerente.getRendaFixa();
        if (rendaFixa.isEmpty()) {
            System.out.println("Nenhum produto de Renda Fixa disponível.");
        } else {
            for (int i = 0; i < rendaFixa.size(); i++) {
                System.out.println((i + 1) + ". " + rendaFixa.get(i));
            }
        }

        System.out.println("\nOpções de Investimentos em Renda Variável:");
        List<String> rendaVariavel = gerente.getRendaVariavel();
        if (rendaVariavel.isEmpty()) {
            System.out.println("Nenhum produto de Renda Variável disponível.");
        } else {
            for (int i = 0; i < rendaVariavel.size(); i++) {
                System.out.println((i + 1) + ". " + rendaVariavel.get(i));
            }
        }

        System.out.println("\nEscolha o tipo de investimento: ");
        System.out.println("1. Renda Fixa");
        System.out.println("2. Renda Variável");
        int tipoInvestimento = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Digite o número do investimento que deseja escolher: ");
        int escolha = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Digite o valor que deseja investir: R$ ");
        double valorInvestido = scanner.nextDouble();
        scanner.nextLine();

        if (valorInvestido <= this.saldo) {
            if (tipoInvestimento == 1 && escolha > 0 && escolha <= rendaFixa.size()) {
                System.out.println("Você escolheu o investimento de Renda Fixa: " + rendaFixa.get(escolha - 1));
                this.saldo -= valorInvestido;
                registrarExtrato("Investimento de R$" + valorInvestido + " realizado em Renda Fixa: "
                        + rendaFixa.get(escolha - 1));
                System.out.println("Investimento realizado com sucesso!");
            } else if (tipoInvestimento == 2 && escolha > 0 && escolha <= rendaVariavel.size()) {
                System.out.println("Você escolheu o investimento de Renda Variável: " + rendaVariavel.get(escolha - 1));
                this.saldo -= valorInvestido;
                registrarExtrato("Investimento de R$" + valorInvestido + " realizado em Renda Variável: "
                        + rendaVariavel.get(escolha - 1));
                System.out.println("Investimento realizado com sucesso!");
            } else {
                System.out.println("Opção inválida.");
            }
        } else {
            System.out.println("Saldo insuficiente para realizar o investimento.");
        }
    }

    public void solicitarCredito(double valor, Gerente gerente, String senhaTeste) {
        if (validarSenha(senhaTeste)) {
            boolean autoriza;
            autoriza = gerente.analisarCredito(this, valor);
            if (autoriza) {
                this.saldo += valor;
                registrarExtrato("Crédito de R$" + valor + " concedito por:" + gerente.getNome());
            } else {
                System.out.println("O gerente nao concedeu o crédito solicitado.");
            }
        } else {
            System.out.println("Senha incorreta. Operação cancelada.");
        }
    }

    @XmlElement
    private void registrarExtrato(String transacao) {
        extrato.add(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) + " - " + transacao);
    }

    @XmlElement
    public String getContaBancaria() {
        return contaBancaria;
    }

    @XmlElement
    public double getSaldo() {
        return saldo;
    }

    @XmlElement
    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public void registrarMovimentacao(String tipoMovimentacao, double valor) {
        double saldoAnterior = saldo;

        if (tipoMovimentacao.equalsIgnoreCase("Saque")) {
            saldo -= valor;
        } else if (tipoMovimentacao.equalsIgnoreCase("Deposito")) {
            saldo += valor;
        }

        double saldoAtual = saldo;

        Map<String, Object> registro = new HashMap<>();
        Map<String, Object> detalhes = new HashMap<>();
        detalhes.put("saldoAnterior", saldoAnterior);
        detalhes.put("tipoDeMovimentacao", tipoMovimentacao);
        detalhes.put("valor", valor);
        detalhes.put("saldoAtual", saldoAtual);
        detalhes.put("data", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        registro.put(contaBancaria, detalhes);

        try {
            ExtratoHandler.adicionarRegistro(registro);
        } catch (IOException e) {
            System.out.println("Erro ao salvar o extrato: " + e.getMessage());
        }
    }
    public boolean transferencia(double valor, String senhaCliente, String contaDestino) {
        Cliente destinatario = usuarioService.buscarClientePorNumeroConta(contaDestino);
    
        if (destinatario == null) {
            System.out.println("Cliente não encontrado.");
            return false;
        }
        if (valor <= 0) {
            System.out.println("Valor inválido para transferência.");
            return false;
        }
        if (valor > 1000000) {
            System.out.println(
                    "Valor máximo para transferência é de R$1.000.000,00. Para transferências maiores, procurar um gerente.");
            return false;
        }
        if (!this.verificaSenha(senhaCliente)) {
            System.out.println("Senha incorreta.");
            return false;
        }

        this.setSaldo(this.getSaldo() - valor);
        destinatario.setSaldo(destinatario.getSaldo() + valor);
        this.registrarMovimentacao("Transferência", -valor);
        destinatario.registrarMovimentacao("Transferência", valor);

        System.out.println(
                "Transferência de R$" + valor + " da conta " + this.getContaBancaria() + " para a conta " + contaDestino
                        + " realizada com sucesso.");

        return true;
    }
    public UsuarioService getUsuarioService() {
        return usuarioService;
    }

}
