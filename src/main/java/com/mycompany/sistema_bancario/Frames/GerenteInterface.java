package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;

import com.mycompany.sistema_bancario.Gerente;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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

public class GerenteInterface extends JFrame {

    private JButton botaoSaque, botaoDeposito, botaoTransferencia, botaoRendaFixa, botaoRendaVariavel,
            botaoAvaliacaoCredito, botaoGerenciamentoUsuarios, botaoSair;
    private JLabel titulo;

    public GerenteInterface(Gerente gerente) {
        // Configurações da janela
        setTitle("Área do Gerente");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando o título
        titulo = new JLabel("Operações do Gerente", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        // Criando os botões
        botaoSaque = new JButton("Atendimento de Saque");
        botaoDeposito = new JButton("Processamento de Depósitos");
        botaoTransferencia = new JButton("Transferência");
        botaoRendaFixa = new JButton("Cadastro de Opções de Renda Fixa");
        botaoRendaVariavel = new JButton("Cadastro de Opções de Renda Variável");
        botaoAvaliacaoCredito = new JButton("Avaliação de Crédito");
        botaoGerenciamentoUsuarios = new JButton("Gerenciamento de Usuários");
        botaoSair = new JButton("Sair");

        // Definindo o layout como GridBagLayout
        JPanel painel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Espaçamento entre componentes

        // Título ocupa toda a largura (2 colunas)
        gbc.gridwidth = 2;
        gbc.gridx = 0;
        gbc.gridy = 0;
        painel.add(titulo, gbc);

        // Configurações para os botões (ocupam 80% da largura)
        gbc.gridwidth = 1; // Volta para 1 coluna
        gbc.fill = GridBagConstraints.HORIZONTAL; // O botão ocupa horizontalmente
        gbc.weightx = 0.8; // O botão ocupa 80% da largura disponível

        // Centralizando os botões
        gbc.gridx = 0; // Coluna do centro
        gbc.gridy = 1;
        painel.add(botaoSaque, gbc);

        // Preenchimento (vazio) para simular os 20% restantes
        gbc.gridx = 0;
        gbc.weightx = 0.2;
        painel.add(Box.createHorizontalStrut(0), gbc);

        gbc.weightx = 0.8;
        gbc.gridx = 0; // Mantém os botões centralizados
        gbc.gridy = 2;
        painel.add(botaoDeposito, gbc);

        gbc.weightx = 0.2;
        gbc.gridx = 0;
        painel.add(Box.createHorizontalStrut(0), gbc);

        gbc.weightx = 0.8;
        gbc.gridx = 0; // Centraliza o próximo botão
        gbc.gridy = 3;
        painel.add(botaoTransferencia, gbc);

        gbc.weightx = 0.2;
        gbc.gridx = 0;
        painel.add(Box.createHorizontalStrut(0), gbc);

        gbc.weightx = 0.8;
        gbc.gridx = 0;
        gbc.gridy = 4;
        painel.add(botaoRendaFixa, gbc);

        gbc.weightx = 0.2;
        gbc.gridx = 0;
        painel.add(Box.createHorizontalStrut(0), gbc);

        gbc.weightx = 0.8;
        gbc.gridx = 0;
        gbc.gridy = 5;
        painel.add(botaoRendaVariavel, gbc);

        gbc.weightx = 0.2;
        gbc.gridx = 0;
        painel.add(Box.createHorizontalStrut(0), gbc);

        gbc.weightx = 0.8;
        gbc.gridx = 0;
        gbc.gridy = 6;
        painel.add(botaoAvaliacaoCredito, gbc);

        gbc.weightx = 0.2;
        gbc.gridx = 0;
        painel.add(Box.createHorizontalStrut(0), gbc);

        gbc.weightx = 0.8;
        gbc.gridx = 0;
        gbc.gridy = 7;
        painel.add(botaoGerenciamentoUsuarios, gbc);

        gbc.weightx = 0.2;
        gbc.gridx = 0;
        painel.add(Box.createHorizontalStrut(0), gbc);

        gbc.weightx = 0.8;
        gbc.gridx = 0;
        gbc.gridy = 8;
        painel.add(botaoSair, gbc);

        gbc.weightx = 0.2;
        gbc.gridx = 0;
        painel.add(Box.createHorizontalStrut(0), gbc);

        // Adicionando painel à janela
        add(painel);

        // Ação para cada botão
        botaoSaque.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica da operação de saque
                JOptionPane.showMessageDialog(null, "Operação de Atendimento de Saque selecionada.");
            }
        });

        botaoDeposito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica da operação de depósito
                JOptionPane.showMessageDialog(null, "Operação de Processamento de Depósitos selecionada.");
            }
        });

        botaoTransferencia.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                TransferenciaGerente transferegerente = new TransferenciaGerente(gerente); // Volta para a tela de login
                transferegerente.setVisible(true);
            }
        });

        botaoRendaFixa.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
        
                CadastroRendaFixa rendafixa = new CadastroRendaFixa(gerente); // Volta para a tela de login
                rendafixa.setVisible(true);
            }
        });

        botaoRendaVariavel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Fecha a janela atual
                CadastroRendaVariavel rendavariavel = new CadastroRendaVariavel(gerente); // Volta para a tela de login
                rendavariavel.setVisible(true);
            }
        });

        botaoAvaliacaoCredito.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica de avaliação de crédito
                JOptionPane.showMessageDialog(null, "Avaliação de Crédito selecionada.");
            }
        });

        botaoGerenciamentoUsuarios.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GerenciamentoUsuarios gerenciamento = new GerenciamentoUsuarios(gerente);
                gerenciamento.setVisible(true);
                dispose();
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Retorna à tela de login
                dispose(); // Fecha a janela atual
                LoginInterface telaLogin = new LoginInterface(); // Volta para a tela de login
                telaLogin.setVisible(true);
            }
        });
    }
}
