package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;
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
 * @author  Ian Nakamura Okano Preste
 * @matricula 202335038
 */

public class TransferenciaCaixa extends JFrame {

    private JTextField campoContaOrigem, campoContaDestino, campoValor;
    private JPasswordField campoSenha;
    private JButton botaoConfirmar, botaoCancelar;
    private JLabel mensagemStatus;

    public TransferenciaCaixa() {
        // Configurações da janela
        setTitle("Transferência Bancária");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando o título
        JLabel titulo = new JLabel("Transferência Bancária", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        // Criando os campos
        JLabel labelContaOrigem = new JLabel("Conta de Origem:");
        campoContaOrigem = new JTextField(15);

        JLabel labelContaDestino = new JLabel("Conta de Destino:");
        campoContaDestino = new JTextField(15);

        JLabel labelValor = new JLabel("Valor:");
        campoValor = new JTextField(15);

        JLabel labelSenha = new JLabel("Senha para Confirmar:");
        campoSenha = new JPasswordField(15);

        // Botões
        botaoConfirmar = new JButton("Confirmar");
        botaoCancelar = new JButton("Sair");

        // Mensagem de status (sucesso ou erro)
        mensagemStatus = new JLabel("", JLabel.CENTER);
        mensagemStatus.setFont(new Font("Arial", Font.PLAIN, 14));

        // Painel para organizar os componentes
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(6, 2, 10, 10));

        // Adicionando componentes ao painel
        painel.add(labelContaOrigem);
        painel.add(campoContaOrigem);

        painel.add(labelContaDestino);
        painel.add(campoContaDestino);

        painel.add(labelValor);
        painel.add(campoValor);

        painel.add(labelSenha);
        painel.add(campoSenha);

        painel.add(botaoConfirmar);
        painel.add(botaoCancelar);

        // Adicionando painel à janela
        add(titulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);
        add(mensagemStatus, BorderLayout.SOUTH);

        // Ação do botão Confirmar
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String contaOrigem = campoContaOrigem.getText();
                String contaDestino = campoContaDestino.getText();
                String valor = campoValor.getText();
                String senha = new String(campoSenha.getPassword());

                // Verificando se os campos estão vazios
                if (contaOrigem.isEmpty() || contaDestino.isEmpty() || valor.isEmpty() || senha.isEmpty()) {
                    mensagemStatus.setText("Por favor, preencha todos os campos.");
                    mensagemStatus.setForeground(Color.RED);
                } else {
                    // Simulando validação de senha e operação de transferência
                    if (senha.equals("1234")) { // Aqui você pode substituir por validação real de senha
                        mensagemStatus.setText("Transferência realizada com sucesso!");
                        mensagemStatus.setForeground(Color.BLUE);
                    } else {
                        mensagemStatus.setText("Senha incorreta. Operação não autorizada.");
                        mensagemStatus.setForeground(Color.RED);
                    }
                }
            }
        });

        // Ação do botão Cancelar
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Limpar os campos
                campoContaOrigem.setText("");
                campoContaDestino.setText("");
                campoValor.setText("");
                campoSenha.setText("");
                mensagemStatus.setText("");
                dispose(); // Fecha a janela atual
                CaixaInterface telacaixa = new CaixaInterface(); 
                telacaixa.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        TransferenciaInterface transferenciaInterface = new TransferenciaInterface();
        transferenciaInterface.setVisible(true);
    }
}
