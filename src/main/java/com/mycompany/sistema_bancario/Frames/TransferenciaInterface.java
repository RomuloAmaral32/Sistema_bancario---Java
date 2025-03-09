package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;

import com.mycompany.sistema_bancario.Cliente;
import com.mycompany.sistema_bancario.JsonHandler;
import com.mycompany.sistema_bancario.Usuario;
import com.mycompany.sistema_bancario.UsuarioService;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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

public class TransferenciaInterface extends JFrame {

    private JTextField campoContaDestino, campoValor;
    private JPasswordField campoSenha;
    private JButton botaoConfirmar, botaoCancelar;
    private JLabel mensagemStatus;

    public TransferenciaInterface(Cliente cliente) {
        UsuarioService usuarioService = new UsuarioService("src/file/java/com/mycompany/sistema_bancario/usuarios.json");

        // Configurações da janela
        setTitle("Transferência Bancária");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela
        // Criando o título
        JLabel titulo = new JLabel("Transferência Bancária", JLabel.CENTER);
        titulo.setFont(new Font("Arial", Font.BOLD, 18));

        // Criando os campos
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
    

                String contaDestino = campoContaDestino.getText();
                String senha = new String(campoSenha.getPassword());

                // Verificando se os campos estão vazios
                if (contaDestino.isEmpty() || campoValor.getText().isEmpty() || senha.isEmpty()) {
                    mensagemStatus.setText("Por favor, preencha todos os campos.");
                    mensagemStatus.setForeground(Color.RED);
                } else {
                    try {
                        // Convertendo valor para double
                        double valor = Double.parseDouble(campoValor.getText());

                        // Simulando validação de senha e operação de transferência
                        if (senha.equals(cliente.getSenha())) {
                            boolean transferenciaRealizada = cliente.transferencia(valor, senha, contaDestino);

                            if( valor >= 1000000){
                                JOptionPane.showMessageDialog(null, "Valores a partir de R$1.000.000,00 devem procurar um gerente.", "Erro", JOptionPane.ERROR_MESSAGE);
                            }

                            if (transferenciaRealizada) {

                                // Exibe mensagem de sucesso
                                JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!", "Sucesso",
                                        JOptionPane.INFORMATION_MESSAGE);
                                mensagemStatus.setText("Transferência realizada com sucesso!");
                                mensagemStatus.setForeground(Color.BLUE);
                            } else {
                                mensagemStatus.setText("Falha na transferência. Verifique as informações.");
                                mensagemStatus.setForeground(Color.RED);
                            }
                        } else {
                            mensagemStatus.setText("Senha incorreta. Operação não autorizada.");
                            mensagemStatus.setForeground(Color.RED);
                        }
                    } catch (NumberFormatException ex) {
                        mensagemStatus.setText("Valor inválido. Insira um valor numérico.");
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
                campoContaDestino.setText("");
                campoValor.setText("");
                campoSenha.setText("");
                mensagemStatus.setText("");
                dispose(); // Fecha a janela atual
                ClienteInterface telacliente = new ClienteInterface(cliente);
                telacliente.setVisible(true);
            }
        });
    }

}
