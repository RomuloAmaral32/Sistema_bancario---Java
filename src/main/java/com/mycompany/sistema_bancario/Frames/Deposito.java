package com.mycompany.sistema_bancario.Frames;

import java.util.List;
import com.mycompany.sistema_bancario.UsuarioService;
import com.mycompany.sistema_bancario.Caixa;
import com.mycompany.sistema_bancario.Cliente;
import com.mycompany.sistema_bancario.JsonHandler;
import com.mycompany.sistema_bancario.Usuario;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Deposito extends JFrame {

    private JLabel labelTitulo, labelNumeroConta, labelValorDeposito, labelSenha;
    private JTextField campoNumeroConta, campoValorDeposito;
    private JPasswordField campoSenha;
    private JButton botaoConfirmar, botaoCancelar;
    private UsuarioService usuarioService;

    public Deposito() {
        // Inicializar o serviço de usuários
        usuarioService = new UsuarioService("src/file/java/com/mycompany/sistema_bancario/usuarios.json"); // Arquivo JSON com usuários

        // Configurações da janela
        setTitle("Tela de Depósito");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando os componentes
        labelTitulo = new JLabel("Depósito", JLabel.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        labelNumeroConta = new JLabel("Número da Conta:");
        campoNumeroConta = new JTextField();

        labelValorDeposito = new JLabel("Valor do Depósito:");
        campoValorDeposito = new JTextField();

        labelSenha = new JLabel("Senha:");
        campoSenha = new JPasswordField();

        botaoConfirmar = new JButton("Confirmar");
        botaoCancelar = new JButton("Cancelar");

        // Layout
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(5, 2, 10, 10)); // 5 linhas, 2 colunas, espaço de 10px

        // Adicionando componentes ao painel
        painel.add(labelNumeroConta);
        painel.add(campoNumeroConta);
        painel.add(labelValorDeposito);
        painel.add(campoValorDeposito);
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
                String numeroConta = campoNumeroConta.getText();
                String valorDepositoStr = campoValorDeposito.getText();
                double valorDeposito = Double.parseDouble(valorDepositoStr);
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

                    // Processa o depósito usando o Caixa
                    boolean depositoRealizado = caixa.deposito(valorDeposito, numeroConta, senha);

                    if (depositoRealizado) {
                        // Busca o cliente atualizado
                        Cliente clienteAtualizado = usuarioService.buscarClientePorNumeroConta(numeroConta);

                        // Atualiza o cliente no JSON
                        JsonHandler<Usuario> jsonHandler = new JsonHandler<>("src/file/java/com/mycompany/sistema_bancario/usuarios.json");
                        jsonHandler.editDataByContaBancaria(numeroConta, clienteAtualizado, Usuario.class);

                        // Exibe mensagem de sucesso
                        JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                        // Volta para a interface anterior
                        dispose();
                        CaixaInterface caixaInterface = new CaixaInterface();
                        caixaInterface.setVisible(true);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor de depósito inválido. Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao processar depósito: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar os dados do cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão Cancelar
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Fecha a janela de Depósito e volta para a tela de CaixaInterface
                dispose();
                CaixaInterface caixaInterface = new CaixaInterface();
                caixaInterface.setVisible(true);
            }
        });
    }

    public static void main(String[] args) {
        // Criando e exibindo a tela de Depósito
        Deposito deposito = new Deposito();
        deposito.setVisible(true);
    }
}
