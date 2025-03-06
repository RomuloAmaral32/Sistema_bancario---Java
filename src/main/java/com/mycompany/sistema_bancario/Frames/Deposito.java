package com.mycompany.sistema_bancario.Frames;

import com.mycompany.sistema_bancario.UsuarioService;
import com.mycompany.sistema_bancario.Cliente;
import com.mycompany.sistema_bancario.JsonHandler;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;

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
                String valorDeposito = campoValorDeposito.getText();
                char[] senhaInformada = campoSenha.getPassword();
                String senha = new String(senhaInformada);

                try {
                    // Busca o cliente pelo número da conta
                    Cliente cliente = usuarioService.buscarClientePorNumeroConta(numeroConta);

                    // Verificação de null para evitar erros
                    if (cliente != null && cliente.getContaBancaria().equals(numeroConta)) {
                        // Verificação da senha
                        System.out.println(cliente.getSaldo());
                        if (cliente.getSenha().equals(senha)) {
                            // Validação do valor de depósito
                            double valor = Double.parseDouble(valorDeposito);
                            if (valor > 0) {
                                // Realizar o depósito
                                cliente.setSaldo(cliente.getSaldo() + valor);
                                System.out.println(cliente.getSaldo());


                                // Carregar a lista de clientes do JSON usando JsonHandler
                                JsonHandler<Cliente> jsonHandler = new JsonHandler<>("src/file/java/com/mycompany/sistema_bancario/usuarios.json");
                                List<Cliente> clientes = jsonHandler.loadFromJson(Cliente.class);

                                // Atualizar o cliente na lista
                                for (int i = 0; i < clientes.size(); i++) {
                                    if (clientes.get(i).getContaBancaria().equals(cliente.getContaBancaria())) {
                                        clientes.set(i, cliente);  // Atualiza o cliente
                                        break;
                                    }
                                }
                                System.out.println(cliente.getSaldo());

                                // Salvar a lista de clientes atualizada no JSON
                                jsonHandler.saveToJson(clientes);

                                JOptionPane.showMessageDialog(null, "Depósito realizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

                                // Voltar para a interface anterior
                                dispose();
                                CaixaInterface caixaInterface = new CaixaInterface();
                                caixaInterface.setVisible(true);
                            } else {
                                JOptionPane.showMessageDialog(null, "Valor de depósito inválido.", "Erro", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Senha incorreta! O depósito não foi realizado.", "Erro", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Conta bancária não encontrada ou inválida.", "Erro", JOptionPane.ERROR_MESSAGE);
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
