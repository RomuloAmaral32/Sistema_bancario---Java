package com.mycompany.sistema_bancario.Frames;

import javax.swing.*;

import com.mycompany.sistema_bancario.Caixa;
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
 * @author  Ian Nakamura Okano Preste
 * @matricula 202335038
 */

public class TransferenciaCaixa extends JFrame {

    private JTextField campoContaOrigem, campoContaDestino, campoValor;
    private JPasswordField campoSenha;
    private JButton botaoConfirmar, botaoCancelar;
    private JLabel mensagemStatus;
     private UsuarioService usuarioService;

    public TransferenciaCaixa() {
        usuarioService = new UsuarioService("src/file/java/com/mycompany/sistema_bancario/usuarios.json");
        // Configurações da janela
        setTitle("Transferência Bancária do caixa");
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
        String valorTransferenciaStr = campoValor.getText();
        double valorTransferencia = Double.parseDouble(valorTransferenciaStr);
        char[] senhaInformada = campoSenha.getPassword();
        String senha = new String(senhaInformada);

        try {
            // Cria uma instância do Caixa
            Caixa caixa = new Caixa(
                    "Caixa",
                    "11357820674",
                    "senhaCaixa",
                    "caixa@email.com",
                    "caixa",
                    "36000000",
                    "123",
                    "001",
                    usuarioService);

            // Processa a transferência usando o Caixa
            boolean transferenciaRealizada = caixa.transferencia(valorTransferencia, contaOrigem, senha, contaDestino);
            if (transferenciaRealizada) {
                // Busca os clientes atualizados
                Cliente clienteOrigemAtualizado = usuarioService.buscarClientePorNumeroConta(contaOrigem);
                Cliente clienteDestinoAtualizado = usuarioService.buscarClientePorNumeroConta(contaDestino);
                // Atualiza ambos os clientes no JSON
                JsonHandler<Usuario> jsonHandler = new JsonHandler<>(
                        "src/file/java/com/mycompany/sistema_bancario/usuarios.json");
                jsonHandler.editDataByContaBancaria(contaOrigem, clienteOrigemAtualizado, Usuario.class);
                jsonHandler.editDataByContaBancaria(contaDestino, clienteDestinoAtualizado, Usuario.class);

                // Exibe mensagem de sucesso
                JOptionPane.showMessageDialog(null, "Transferência realizada com sucesso!", "Sucesso",
                        JOptionPane.INFORMATION_MESSAGE);

                // Volta para a interface anterior
                dispose();
                CaixaInterface caixaInterface = new CaixaInterface();
                caixaInterface.setVisible(true);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Valor de transferência inválido. Por favor, insira um número válido.",
                    "Erro", JOptionPane.ERROR_MESSAGE);
        } catch (IllegalArgumentException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao processar transferência: " + ex.getMessage(), "Erro",
                    JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro ao salvar os dados dos clientes.", "Erro",
                    JOptionPane.ERROR_MESSAGE);
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
        TransferenciaCaixa transferenciaCaixa = new TransferenciaCaixa();
        transferenciaCaixa.setVisible(true);
    }
}
