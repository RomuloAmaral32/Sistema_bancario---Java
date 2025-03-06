package com.mycompany.sistema_bancario.Frames;
import java.util.List;
import com.mycompany.sistema_bancario.UsuarioService;
import com.mycompany.sistema_bancario.Cliente;
import com.mycompany.sistema_bancario.JsonHandler;

import javax.swing.*;
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

public class Saque extends JFrame {

    private JLabel labelTitulo, labelNumeroDono, labelValorSaque, labelSenha;
    private JTextField campoNumeroDono, campoValorSaque;
    private JPasswordField campoSenha;
    private JButton botaoConfirmar, botaoCancelar;
    private UsuarioService usuarioService;

    public Saque() {
        // Inicializar o serviço de usuários
        usuarioService = new UsuarioService("usuarios.json"); // Arquivo JSON com usuários

        // Configurações da janela
        setTitle("Tela de Saque");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando os componentes
        labelTitulo = new JLabel("Saque", JLabel.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        labelNumeroDono = new JLabel("Número da Conta:");
        campoNumeroDono = new JTextField();

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
        painel.add(labelNumeroDono);
        painel.add(campoNumeroDono);
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
                String numeroDono = campoNumeroDono.getText();
                String valorSaque = campoValorSaque.getText();
                char[] senhaInformada = campoSenha.getPassword();
                String senha = new String(senhaInformada);
        
                try {
                    // Busca o cliente pelo número da conta
                    Cliente cliente = usuarioService.buscarClientePorNumeroConta(numeroDono);
        
                    // Verificação de null para evitar erros
                    if (cliente.getContaBancaria() != null && cliente.getContaBancaria().equals(numeroDono)) {
                        System.out.println(cliente.getContaBancaria());
                        // Verificação da senha
                        if (cliente.getSenha().equals(senha)) {
                            // Validação do valor de saque
                            double valor = Double.parseDouble(valorSaque);
                            System.out.println(cliente.getNome());
                            if (valor > 0 && cliente.getSaldo() >= valor) {
                                // Realizar o saque
                                cliente.setSaldo(cliente.getSaldo() - valor);
        
                                // Carregar a lista de clientes do JSON usando JsonHandler
                                JsonHandler<Cliente> jsonHandler = new JsonHandler<>("usuarios.json");
                                List<Cliente> clientes = jsonHandler.loadFromJson(Cliente.class);
        
                                // Atualizar o cliente na lista
                                for (int i = 0; i < clientes.size(); i++) {
                                    if (clientes.get(i).getContaBancaria().equals(cliente.getContaBancaria())) {
                                        clientes.set(i, cliente);  // Atualiza o cliente
                                        break;
                                    }
                                }
        
                                // Salvar a lista de clientes atualizada no JSON
                                jsonHandler.saveToJson(clientes);
                                System.out.println(cliente.getContaBancaria());
                                JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
        
                                // Voltar para a interface anterior
                                dispose();
                                CaixaInterface caixaInterface = new CaixaInterface();
                                caixaInterface.setVisible(true);
                            } else if (valor <= 0) {
                                JOptionPane.showMessageDialog(null, "Valor de saque inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                            } else {
                                JOptionPane.showMessageDialog(null, "Saldo insuficiente.", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Senha incorreta! O saque não foi realizado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Conta bancária não encontrada ou inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor de saque inválido. Por favor, insira um número válido.", "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao processar saque: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar os dados do cliente.", "Erro", JOptionPane.ERROR_MESSAGE);
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
