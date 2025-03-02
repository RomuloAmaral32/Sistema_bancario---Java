package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class Extrato extends JFrame {

    private JLabel labelExtrato;
    private JButton botaoSair;
    private JTextArea areaExtrato;
    private JScrollPane scrollExtrato;

    public Extrato() {
        // Configurações da janela
        setTitle("Seu Extrato");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando o título do extrato
        labelExtrato = new JLabel("Seu Extrato", JLabel.CENTER);
        labelExtrato.setFont(new Font("Arial", Font.BOLD, 20));

        // Criando o JTextArea para o extrato
        areaExtrato = new JTextArea();
        areaExtrato.setEditable(false);
        areaExtrato.setFont(new Font("Arial", Font.PLAIN, 14));

        // Adicionando o histórico de transações
        ArrayList<String> transacoes = obterHistoricoTransacoes();
        for (String transacao : transacoes) {
            areaExtrato.append(transacao + "\n");
        }

        // Criando o JScrollPane para o JTextArea
        scrollExtrato = new JScrollPane(areaExtrato);
        scrollExtrato.setPreferredSize(new Dimension(350, 200));

        // Criando o botão Sair
        botaoSair = new JButton("Sair");

        // Layout
        JPanel painel = new JPanel();
        painel.setLayout(new BorderLayout(10, 10)); // Usando BorderLayout para centralizar o conteúdo

        // Adicionando componentes ao painel
        painel.add(labelExtrato, BorderLayout.NORTH);
        painel.add(scrollExtrato, BorderLayout.CENTER);
        painel.add(botaoSair, BorderLayout.SOUTH);

        // Adicionando painel à janela
        add(painel, BorderLayout.CENTER);

        // Ação do botão Sair
        botaoSair.addActionListener(e -> {
            // Fecha a janela de extrato
            dispose(); // Fecha a janela atual
                ClienteInterface cliente = new ClienteInterface(); // Volta para a tela de login
                cliente.setVisible(true);
        });
    }

    private ArrayList<String> obterHistoricoTransacoes() {
        ArrayList<String> transacoes = new ArrayList<>();
        transacoes.add("01/03/2025 - Depósito: R$ 500,00");
        transacoes.add("02/03/2025 - Saque: R$ 200,00");
        transacoes.add("03/03/2025 - Transferência: R$ 150,00");
        transacoes.add("04/03/2025 - Depósito: R$ 1.000,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");
        transacoes.add("05/03/2025 - Saque: R$ 300,00");

        return transacoes;
    }

    public static void main(String[] args) {
        // Criando e exibindo a tela de extrato
        Extrato extrato = new Extrato();
        extrato.setVisible(true);
    }
}
