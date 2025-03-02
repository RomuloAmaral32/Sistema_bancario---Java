package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Saque extends JFrame {

    private JLabel labelTitulo, labelNomeDono, labelValorSaque, labelSenha;
    private JTextField campoNomeDono, campoValorSaque;
    private JPasswordField campoSenha;
    private JButton botaoConfirmar, botaoCancelar;
    private final String senhaCorreta = "1234"; // Senha fictícia para validação

    public Saque() {
        // Configurações da janela
        setTitle("Tela de Saque");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando os componentes
        labelTitulo = new JLabel("Saque", JLabel.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        labelNomeDono = new JLabel("Nome do Dono da Conta:");
        campoNomeDono = new JTextField();

        labelValorSaque = new JLabel("Valor a ser sacado:");
        campoValorSaque = new JTextField();

        labelSenha = new JLabel("Verificador de Senha:");
        campoSenha = new JPasswordField();

        botaoConfirmar = new JButton("Confirmar");
        botaoCancelar = new JButton("Cancelar");

        // Layout
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 linhas, 2 colunas, espaço de 10px

        // Adicionando componentes ao painel
        painel.add(labelNomeDono);
        painel.add(campoNomeDono);
        painel.add(labelValorSaque);
        painel.add(campoValorSaque);
        painel.add(labelSenha);
        painel.add(campoSenha);
        painel.add(new JLabel()); // Campo vazio para espaçamento
        painel.add(botaoConfirmar);
        painel.add(new JLabel()); // Campo vazio para espaçamento
        painel.add(botaoCancelar);

        // Adicionando o título e o painel à janela
        add(labelTitulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        // Ação do botão Confirmar
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nomeDono = campoNomeDono.getText();
                String valorSaque = campoValorSaque.getText();
                char[] senhaInformada = campoSenha.getPassword();
                String senha = new String(senhaInformada);

                // Verificação da senha
                if (senha.equals(senhaCorreta)) {
                    // Aqui você pode adicionar a lógica para realizar o saque
                    dispose();
                CaixaInterface caixaInterface = new CaixaInterface();
                caixaInterface.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "Senha incorreta! O saque não foi realizado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão Cancelar
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fecha a janela de Saque e volta para a tela de CaixaInterface
                dispose();
                CaixaInterface caixaInterface = new CaixaInterface();
                caixaInterface.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        // Criando e exibindo a tela de Saque
        Saque saque = new Saque();
        saque.setVisible(true);
    }
}
