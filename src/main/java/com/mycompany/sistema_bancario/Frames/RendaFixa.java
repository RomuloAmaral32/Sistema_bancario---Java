package com.mycompany.sistema_bancario.Frames;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

public class RendaFixa extends JFrame {

    private JLabel labelTitulo, labelValor, labelSenha, labelInvestimento;
    private JTextField campoValor;
    private JPasswordField campoSenha; // Usando JPasswordField para senha
    private JButton botaoConfirmar, botaoSair;
    private JComboBox<String> comboBoxInvestimentos;
    private final String senhaCorreta = "1234"; // Senha fictícia para validação

    private JTextArea labelDetalhes; // Alterado para JTextArea para suportar múltiplas linhas
    private Map<String, String[]> detalhesInvestimentos; // Mapa para armazenar os detalhes dos investimentos

    public RendaFixa() {
        // Configurações da janela
        setTitle("Investimentos em Renda Fixa");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centraliza a janela na tela

        // Criando os componentes
        labelTitulo = new JLabel("Investimentos em Renda Fixa", JLabel.CENTER);
        labelTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        labelInvestimento = new JLabel("Escolha o investimento:");
        comboBoxInvestimentos = new JComboBox<>();

        labelValor = new JLabel("Valor a investir:");
        campoValor = new JTextField();

        labelSenha = new JLabel("Senha de confirmação:");
        campoSenha = new JPasswordField(); // Usando JPasswordField

        botaoConfirmar = new JButton("Confirmar Investimento");
        botaoSair = new JButton("Sair");

        labelDetalhes = new JTextArea(3, 20); // JTextArea para exibir detalhes em várias linhas
        labelDetalhes.setEditable(false); // Torna o JTextArea somente leitura
        labelDetalhes.setFont(new Font("Arial", Font.PLAIN, 14));
        labelDetalhes.setForeground(Color.BLUE);

        carregarInvestimentos();

        // Layout
        JPanel painel = new JPanel();
        painel.setLayout(new GridLayout(7, 2, 10, 10)); // 7 linhas, 2 colunas, espaço entre componentes

        // Adicionando componentes ao painel
        painel.add(labelInvestimento);
        painel.add(comboBoxInvestimentos);
        painel.add(new JLabel("Detalhes do Investimento:")); // Título para os detalhes
        painel.add(new JScrollPane(labelDetalhes)); // Adiciona o JTextArea dentro de um JScrollPane
        painel.add(labelValor);
        painel.add(campoValor);
        painel.add(labelSenha);
        painel.add(campoSenha);
        painel.add(new JLabel()); // Campo vazio
        painel.add(botaoConfirmar);
        painel.add(new JLabel()); // Campo vazio
        painel.add(botaoSair);

        // Adicionando título e painel à janela
        add(labelTitulo, BorderLayout.NORTH);
        add(painel, BorderLayout.CENTER);

        // Ação ao selecionar um investimento no ComboBox
        comboBoxInvestimentos.addActionListener(e -> {
            String investimentoSelecionado = (String) comboBoxInvestimentos.getSelectedItem();
            if (investimentoSelecionado != null && detalhesInvestimentos.containsKey(investimentoSelecionado)) {
                String[] detalhes = detalhesInvestimentos.get(investimentoSelecionado);
                labelDetalhes.setText(String.join("\n", detalhes)); // Exibe os detalhes em várias linhas
            } else {
                labelDetalhes.setText("Nenhum detalhe disponível.");
            }
        });

        // Ação do botão Confirmar Investimento
        botaoConfirmar.addActionListener(e -> {
            // Lógica de validação de senha
            char[] senhaInformada = campoSenha.getPassword(); // Usando getPassword() para pegar a senha
            String senhaStr = new String(senhaInformada); // Convertendo para String
            if (senhaStr.equals(senhaCorreta)) {
                String investimentoSelecionado = (String) comboBoxInvestimentos.getSelectedItem();
                String valorInvestido = campoValor.getText();
                try {
                    double valor = Double.parseDouble(valorInvestido);
                    if (valor > 0) {
                        JOptionPane.showMessageDialog(null, "Investimento de R$ " + valorInvestido + " em "
                                + investimentoSelecionado + " realizado com sucesso!");
                    } else {
                        JOptionPane.showMessageDialog(null, "Valor inválido! Insira um valor positivo.", "Erro",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Valor inválido! Insira um número válido.", "Erro",
                            JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(null, "Senha incorreta! O investimento não foi realizado.", "Erro",
                        JOptionPane.ERROR_MESSAGE);
            }
        });

        // Ação do botão Sair
        botaoSair.addActionListener(e -> {
            // Fecha a janela de Renda Fixa
            dispose();
            ClienteInterface clienteInterface = new ClienteInterface();
            clienteInterface.setVisible(true);
        });
    }

    // Método para carregar os investimentos do arquivo JSON
    private void carregarInvestimentos() {
        detalhesInvestimentos = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            File file = new File("src/file/java/com/mycompany/sistema_bancario/rendaFixa.json");
            if (!file.exists() || file.length() == 0) {
                System.out.println("Arquivo rendaFixa.json não encontrado ou vazio.");
                return;
            }

            List<String> investimentos = objectMapper.readValue(file, new TypeReference<List<String>>() {
            });
            for (String investimento : investimentos) {
                // Extrair o nome do investimento
                String nomeInvestimento = investimento.split(",")[0].replace("Produto: ", "").trim();

                // Extrair os detalhes do investimento
                String taxaRendimento = investimento.split(",")[1].replace("Taxa de Rendimento: ", "").trim();
                String prazoMinimo = investimento.split(",")[2].replace("Prazo Mínimo: ", "").trim();
                String prazoMaximo = investimento.split(",")[3].replace("Prazo Máximo: ", "").trim();

                // Armazenar os detalhes do investimento no mapa
                detalhesInvestimentos.put(nomeInvestimento, new String[] {
                        "Taxa de Rendimento: " + taxaRendimento,
                        "Prazo Mínimo: " + prazoMinimo,
                        "Prazo Máximo: " + prazoMaximo
                });

                // Adicionar o nome do investimento ao ComboBox
                comboBoxInvestimentos.addItem(nomeInvestimento);
            }
        } catch (IOException e) {
            System.out.println("Erro ao carregar investimentos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        // Criando e exibindo a tela de Renda Fixa
        SwingUtilities.invokeLater(() -> {
            RendaFixa rendaFixa = new RendaFixa();
            rendaFixa.setVisible(true);
        });
    }
}