package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;

import com.mycompany.sistema_bancario.Cliente;
import com.mycompany.sistema_bancario.ExtratoService;

import java.awt.*;
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

public class Extrato extends JFrame {

    private JLabel labelExtrato;
    private JButton botaoSair;
    private JTextArea areaExtrato;
    private JScrollPane scrollExtrato;

    public Extrato(Cliente cliente) {
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
        List<String> transacoes = obterHistoricoTransacoes(cliente);
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
            ClienteInterface clientea = new ClienteInterface(cliente); // Volta para a tela de login
            clientea.setVisible(true);
        });
    }

    private List<String> obterHistoricoTransacoes(Cliente cliente) {


        List<String> historicoTransacoes = ExtratoService.exibirExtrato(cliente.getContaBancaria());

        return historicoTransacoes;
    }
}
