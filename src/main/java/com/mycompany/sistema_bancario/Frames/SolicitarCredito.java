package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;

import com.mycompany.sistema_bancario.Cliente;

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

public class SolicitarCredito extends JFrame {

    private JLabel labelTitulo, labelValor, labelSenha;
    private JTextField campoValor;
    private JPasswordField campoSenha; // Campo de senha
    private JButton botaoConfirmar, botaoCancelar;
    private final String senhaCorreta = "1234"; // Senha fictícia para validação

    public SolicitarCredito(Cliente cliente) {
        // Configurações da janela
        setTitle("Solicitação de Crédito");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando os componentes
        labelTitulo = new JLabel("Solicitar Crédito", JLabel.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        labelValor = new JLabel("Valor do crédito:");
        campoValor = new JTextField();

        labelSenha = new JLabel("Senha de confirmação:");
        campoSenha = new JPasswordField(); // Campo para senha

        botaoConfirmar = new JButton("Confirmar");
        botaoCancelar = new JButton("Sair");

        // Layout
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 linhas, 2 colunas, espaçamento de 10px

        // Adicionando componentes ao painel
        painel.add(labelValor);
        painel.add(campoValor);
        painel.add(labelSenha);
        painel.add(campoSenha);
        painel.add(new JLabel()); // Campo vazio
        painel.add(botaoConfirmar);
        painel.add(new JLabel()); // Campo vazio
        painel.add(botaoCancelar);

        // Adicionando o título e o painel à janela
        add(labelTitulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        // Ação do botão Confirmar
        botaoConfirmar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica de validação de senha
                char[] senhaInformada = campoSenha.getPassword(); // Obtendo a senha informada
                String senhaStr = new String(senhaInformada); // Convertendo para String
                if (senhaStr.equals(senhaCorreta)) {
                    String valorCredito = campoValor.getText();
                    // Aqui você pode adicionar a lógica para solicitar o crédito
                    JOptionPane.showMessageDialog(null, "Crédito de R$ " + valorCredito + " solicitado com sucesso!");
                } else {
                    JOptionPane.showMessageDialog(null, "Senha incorreta! O crédito não foi solicitado.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão Cancelar
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fecha a janela e volta para a tela anterior
                dispose();
                ClienteInterface clienteInterface = new ClienteInterface(cliente);
                clienteInterface.setVisible(true);
            }
        });
    }

}
