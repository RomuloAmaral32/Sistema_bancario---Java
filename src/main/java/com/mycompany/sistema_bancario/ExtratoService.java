/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_bancario;

import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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
public class ExtratoService {
    public static List<String> exibirExtrato(String contaBancaria) {
        List<String> transacoesFormatadas = new ArrayList<>();

        try {
            List<Map<String, Object>> extrato = ExtratoHandler.carregarExtrato();

            for (Map<String, Object> registro : extrato) {
                if (registro.containsKey(contaBancaria)) {
                    @SuppressWarnings("unchecked")
                    Map<String, Object> detalhes = (Map<String, Object>) registro.get(contaBancaria);

                    String tipoMovimentacao = (String) detalhes.get("tipoDeMovimentacao");
                    double valor = (double) detalhes.get("valor");
                    String data = (String) detalhes.get("data");

                    LocalDate dataMovimentacao = LocalDate.parse(data);
                    String dataFormatada = dataMovimentacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));

                    DecimalFormat df = new DecimalFormat("#,##0.00");
                    String valorFormatado = df.format(valor);

                    String transacao = dataFormatada + " - " + tipoMovimentacao + ": R$ " + valorFormatado;
                    transacoesFormatadas.add(transacao);
                }
            }

        } catch (IOException e) {
            System.out.println("Erro ao carregar o extrato: " + e.getMessage());
        }

        return transacoesFormatadas;
    }
}
