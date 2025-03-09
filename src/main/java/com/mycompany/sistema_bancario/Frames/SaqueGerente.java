package com.mycompany.sistema_bancario.Frames;

import java.util.List;
import com.mycompany.sistema_bancario.UsuarioService;
import com.mycompany.sistema_bancario.Caixa;
import com.mycompany.sistema_bancario.Cliente;
import com.mycompany.sistema_bancario.Gerente;
import com.mycompany.sistema_bancario.JsonHandler;
import com.mycompany.sistema_bancario.Usuario;

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

public class SaqueGerente extends JFrame {

    private JLabel labelTitulo, labelNumeroDono, labelValorSaque, labelSenha;
    private JTextField campoNumeroDono, campoValorSaque;
    private JPasswordField campoSenha;
    private JButton botaoConfirmar, botaoCancelar;
    private UsuarioService usuarioService;

    public SaqueGerente(Gerente gerente) {
        // Inicializar o serviço de usuários
        this.usuarioService = gerente.getUsuarioService();

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
                String valorSaqueStr = campoValorSaque.getText();
                double valorSaque = Double.parseDouble(valorSaqueStr);
                char[] senhaInformada = campoSenha.getPassword();
                String senha = new String(senhaInformada);

                try {
                    boolean saqueRealizado = gerente.saque(valorSaque, numeroDono, senha);
                    System.out.println(gerente.getNome());

                    if (saqueRealizado) {
                        // Busca o cliente atualizado
                        Cliente clienteAtualizado = usuarioService.buscarClientePorNumeroConta(numeroDono);

                        // Atualiza o cliente no JSON
                        JsonHandler<Usuario> jsonHandler = new JsonHandler<>(
                                "src/file/java/com/mycompany/sistema_bancario/usuarios.json");
                        jsonHandler.editDataByContaBancaria(numeroDono, clienteAtualizado, Usuario.class);

                        // Exibe mensagem de sucesso
                        JOptionPane.showMessageDialog(null, "Saque realizado com sucesso!", "Sucesso",
                                JOptionPane.INFORMATION_MESSAGE);

                        // Volta para a interface anterior
                        GerenteInterface gerenteInterface = new GerenteInterface(gerente);
                        gerenteInterface.setVisible(true);
                        dispose();
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor de saque inválido. Por favor, insira um número válido.",
                            "Erro", JOptionPane.ERROR_MESSAGE);
                } catch (IllegalArgumentException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao processar saque: " + ex.getMessage(), "Erro",
                            JOptionPane.ERROR_MESSAGE);
                } catch (IOException ex) {
                    JOptionPane.showMessageDialog(null, "Erro ao salvar os dados do cliente.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Ação do botão Cancelar
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                GerenteInterface gerenteInterface = new GerenteInterface(gerente);
                gerenteInterface.setVisible(true);
            }
        });
    }
}
