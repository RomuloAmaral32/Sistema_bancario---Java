package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TelaSenhaExtratoInterface extends JFrame {

    private JPasswordField campoSenha;
    private JButton botaoConfirmar, botaoCancelar;
    private JLabel mensagemStatus;

    public TelaSenhaExtratoInterface() {
        // Configurações da janela
        setTitle("Validação de Senha");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando os componentes
        JLabel labelSenha = new JLabel("Digite a Senha:");
        campoSenha = new JPasswordField(15);

        botaoConfirmar = new JButton("Confirmar");
        botaoCancelar = new JButton("Cancelar");

        mensagemStatus = new JLabel("", JLabel.CENTER);
        mensagemStatus.setFont(new Font("Arial", Font.PLAIN, 14));

        // Painel para organizar os componentes
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(3, 2, 10, 10));

        // Adicionando componentes ao painel
        painel.add(labelSenha);
        painel.add(campoSenha);

        painel.add(botaoConfirmar);
        painel.add(botaoCancelar);

        // Adicionando painel à janela
        add(painel, BorderLayout.CENTER);
        add(mensagemStatus, BorderLayout.SOUTH);

        // Ação do botão Confirmar
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String senha = new String(campoSenha.getPassword());

                // Verificando se a senha está correta
                if (senha.equals("1234")) { // Senha correta para validar
                    mensagemStatus.setText("Senha correta! Ação realizada.");
                    mensagemStatus.setForeground(Color.GREEN);
                   dispose(); // Fecha a janela atual
                Extrato extrato = new Extrato(); // Volta para a tela de login
                extrato.setVisible(true);;
                } else {
                    mensagemStatus.setText("Senha incorreta!");
                    mensagemStatus.setForeground(Color.RED);
                    // Ação quando a senha está incorreta
                    JOptionPane.showMessageDialog(null, "Erro: Senha incorreta.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão Cancelar
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpar o campo de senha e mensagem de status
                campoSenha.setText("");
                mensagemStatus.setText("");
                // Ação alternativa para o Cancelar (exemplo: fechar a janela)
                dispose();
            }
        });
    }

    public static void main(String[] args) {
        TelaSenhaExtratoInterface tela = new TelaSenhaExtratoInterface();
        tela.setVisible(true);
    }
}
