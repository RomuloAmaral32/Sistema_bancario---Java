package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;
import java.awt.*;

public class Saldo extends JFrame {

    private JLabel labelSaldo, saldoAtual;
    private JButton botaoSair;

    public Saldo() {
        // Configurações da janela
        setTitle("Extrato de Conta");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando os componentes
        labelSaldo = new JLabel("Saldo Atual:");
        saldoAtual = new JLabel("R$ 1.500,00");  // Exemplo de saldo atual
        saldoAtual.setFont(new Font("Arial", Font.BOLD, 20));

        botaoSair = new JButton("Sair");

        // Melhorando a aparência do botão
        botaoSair.setBackground(new Color(70, 130, 180));  // Cor de fundo azul suave
        botaoSair.setForeground(Color.WHITE);  // Cor da fonte branca
        botaoSair.setFont(new Font("Arial", Font.BOLD, 16));  // Fonte em negrito
        botaoSair.setFocusPainted(false);  // Remove o foco ao clicar
        botaoSair.setBorder(BorderFactory.createLineBorder(new Color(70, 130, 180), 2));  // Borda azul
        botaoSair.setPreferredSize(new Dimension(100, 35));  // Tamanho mais discreto
        botaoSair.setCursor(new Cursor(Cursor.HAND_CURSOR));  // Cursor de mão ao passar o mouse
        botaoSair.setBorderPainted(true); 
        // Layout
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(2, 2, 10, 10)); // 2 linhas, 2 colunas

        // Adicionando componentes ao painel
        painel.add(labelSaldo);
        painel.add(saldoAtual);
        painel.add(new JLabel()); // Campo vazio para alinhar o botão
        painel.add(botaoSair);

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

    public static void main(String[] args) {
        // Criando e exibindo a tela de extrato
        Saldo saldo = new Saldo();
        saldo.setVisible(true);
    }
}
