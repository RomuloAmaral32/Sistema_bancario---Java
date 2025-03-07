package com.mycompany.sistema_bancario.Frames;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.mycompany.sistema_bancario.Caixa;
import com.mycompany.sistema_bancario.Cliente;
import com.mycompany.sistema_bancario.Gerente;
import com.mycompany.sistema_bancario.JsonHandler;
import com.mycompany.sistema_bancario.Usuario;
import com.mycompany.sistema_bancario.UsuarioService;

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

public class Cadastro extends JFrame {
    private JsonHandler<Usuario> jsonHandler;
    private JLabel labelTitulo, labelNome, labelCpf, labelEmail, labelSenha, labelPerfil, labelCep, labelNumero;
    private JTextField campoNome, campoCpf, campoEmail, campoCep, campoNumero;
    private JPasswordField campoSenha;
    private JComboBox<String> comboBoxPerfil;
    private JButton botaoCadastrar, botaoCancelar;
    private UsuarioService usuarioService;

    public Cadastro() {
        usuarioService = new UsuarioService("src/file/java/com/mycompany/sistema_bancario/usuarios.json"); 

        // Configurações da janela
        setTitle("Tela de Cadastro");
        setSize(400, 350);  // Aumentando a altura da janela para acomodar os novos campos
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); 

        labelTitulo = new JLabel("Cadastro de Usuário", JLabel.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        labelNome = new JLabel("Nome:");
        campoNome = new JTextField();

        labelCpf = new JLabel("CPF:");
        campoCpf = new JTextField();

        labelEmail = new JLabel("Email:");
        campoEmail = new JTextField();

        labelSenha = new JLabel("Senha:");
        campoSenha = new JPasswordField();

        labelPerfil = new JLabel("Perfil:");
        comboBoxPerfil = new JComboBox<>(new String[] { "Cliente", "Caixa", "Gerente" });

        labelCep = new JLabel("CEP:");
        campoCep = new JTextField();

        labelNumero = new JLabel("Número da Casa:");
        campoNumero = new JTextField();

        botaoCadastrar = new JButton("Cadastrar");
        botaoCancelar = new JButton("Cancelar");

        // Layout
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(9, 2, 10, 10)); // Ajustando o GridLayout para 9 linhas

        painel.add(labelNome);
        painel.add(campoNome);
        painel.add(labelCpf);
        painel.add(campoCpf);
        painel.add(labelEmail);
        painel.add(campoEmail);
        painel.add(labelSenha);
        painel.add(campoSenha);
        painel.add(labelPerfil);
        painel.add(comboBoxPerfil);
        painel.add(labelCep);
        painel.add(campoCep);
        painel.add(labelNumero);
        painel.add(campoNumero);
        painel.add(new JLabel()); 
        painel.add(botaoCadastrar);
        painel.add(new JLabel()); 
        painel.add(botaoCancelar);

        add(labelTitulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        botaoCadastrar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nome = campoNome.getText();
                String cpf = campoCpf.getText();
                String email = campoEmail.getText();
                char[] senhaInformada = campoSenha.getPassword();
                String senha = new String(senhaInformada); 
                String perfil = (String) comboBoxPerfil.getSelectedItem();
                String cep = campoCep.getText();
                String numero = campoNumero.getText();
        
                if (nome.isEmpty() || cpf.isEmpty() || email.isEmpty() || senha.isEmpty() || cep.isEmpty() || numero.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Todos os campos devem ser preenchidos!", "Erro", JOptionPane.ERROR_MESSAGE);
                } else {
                    try {
                        // Verificar se o perfil é "Cliente"
                        if (perfil.equals("Cliente")) {
                            // Gerar um novo ID de conta bancária de forma estática
                            String novoIdConta =  usuarioService.gerarNovaContaBancaria(); // Método que gera o novo ID
                            JOptionPane.showMessageDialog(null, "Conta bancária gerada com sucesso! Número da conta: " + novoIdConta);
                            // if (novoIdConta == null) {
                            //     JOptionPane.showMessageDialog(null, "Erro ao gerar conta bancária. Tente novamente.");
                            //     return;
                            // }
            
                            // Criar o novo usuário do tipo Cliente com conta bancária e saldo
                            Cliente novoCliente = new Cliente(nome, cpf, senha, email, perfil.toLowerCase(), cep, numero, 0.0);
                            usuarioService.adicionarUsuario(novoCliente);
                            JOptionPane.showMessageDialog(null, "Cliente cadastrado com sucesso!");
                            dispose(); // Fecha a tela de cadastro
        
                            // Abrir a interface de cliente
                            ClienteInterface telacliente = new ClienteInterface(novoCliente);
                            telacliente.setVisible(true);
                        } else if (perfil.equals("Caixa")) {
                    String numeroFuncionario = "12345"; // Valor estático

                    // Criar o novo usuário do tipo Caixa com o número de funcionário
                    Caixa novoCaixa = new Caixa(nome, cpf, senha, email, perfil.toLowerCase(), cep, numero, numeroFuncionario, usuarioService);
                    usuarioService.adicionarUsuario(novoCaixa);
                    JOptionPane.showMessageDialog(null, "Caixa cadastrado com sucesso!");
                    dispose(); // Fecha a tela de cadastro

                    // Abrir a interface de caixa
                    CaixaInterface telacaixa = new CaixaInterface(novoCaixa);
                    telacaixa.setVisible(true);
                } 
                // Verificar se o perfil é "Gerente"
                else if (perfil.equals("Gerente")) {
                    double nivelDeAcesso = 1000000; // Inicializado com valor de 1 milhão

                    // Criar o novo usuário do tipo Gerente com nível de acesso e arrays de renda
                    Gerente novoGerente = new Gerente(nome, cpf, senha, email, perfil.toLowerCase(), cep, numero, nivelDeAcesso);
                    usuarioService.adicionarUsuario(novoGerente);
                    JOptionPane.showMessageDialog(null, "Gerente cadastrado com sucesso!");
                    dispose(); // Fecha a tela de cadastro

                    // Abrir a interface de gerente
                    GerenteInterface telagerente = new GerenteInterface(novoGerente);
                    telagerente.setVisible(true);
                } 
                // Caso o perfil não seja nenhum dos três, exibe mensagem de erro
                else {
                    throw new IllegalArgumentException("Perfil inválido!");
                }

            } catch (IllegalArgumentException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        }
    }
});
        

        // Ação do botão Cancelar
        botaoCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                campoNome.setText("");
                campoCpf.setText("");
                campoEmail.setText("");
                campoSenha.setText("");
                campoCep.setText("");
                campoNumero.setText("");
                comboBoxPerfil.setSelectedIndex(0);
            }
        });
    }

}
