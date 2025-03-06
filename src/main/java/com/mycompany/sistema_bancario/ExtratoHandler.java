/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_bancario;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Map;

/**
 *
 * @author Darlan Henrique da Costa Silva
 * @matricula 202176038
 *
 * @author Rômulo Ferreira do Amaral
 * @matricula 202335015
 *
 * @author Ian Nakamura Okano Preste
 * @matricula 202335038
 *
 */

public class ExtratoHandler {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final String EXTRATO_FILE_PATH = "src/file/java/com/mycompany/sistema_bancario/extrato.json";

    // Carregar o extrato existente
    public static List<Map<String, Object>> carregarExtrato() throws IOException {
        File file = new File(EXTRATO_FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    // Salvar o extrato atualizado
    public static void salvarExtrato(List<Map<String, Object>> extrato) throws IOException {
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(EXTRATO_FILE_PATH), extrato);
    }

    // Adicionar um novo registro ao extrato
    public static void adicionarRegistro(Map<String, Object> novoRegistro) throws IOException {
        List<Map<String, Object>> extrato = carregarExtrato();
        extrato.add(novoRegistro);
        salvarExtrato(extrato);
    }
}
