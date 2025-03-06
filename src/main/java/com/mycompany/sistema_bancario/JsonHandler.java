package com.mycompany.sistema_bancario;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.jsontype.impl.LaissezFaireSubTypeValidator;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class JsonHandler<T> {
    private final ObjectMapper objectMapper;
    private final String filePath;

    public JsonHandler(String filePath) {
        this.filePath = filePath;
        this.objectMapper = new ObjectMapper();

        // Habilita o polimorfismo diretamente no ObjectMapper
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.enable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

    }

    public void saveToJson(List<T> data) throws IOException {
        objectMapper.writeValue(new File(filePath), data);
    }

    // Método para carregar os dados do JSON
    public List<T> loadFromJson(Class<T> clazz) throws IOException {
        File file = new File(filePath);
        if (!file.exists() || file.length() == 0) {
            return new ArrayList<>();
        }
        return objectMapper.readValue(file,
                objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }

    public void addSingleData(T newData) throws IOException {
        List<T> dataList = loadFromJson((Class<T>) newData.getClass());
        dataList.add(newData);
        saveToJson(dataList);
    }

    public void editDataByCpf(String cpf, T updatedData, Class<T> clazz) throws IOException {
        List<T> dataList = loadFromJson(clazz);
        boolean found = false;

        for (int i = 0; i < dataList.size(); i++) {
            T data = dataList.get(i);
            if (data instanceof Usuario) {
                Usuario usuario = (Usuario) data;
                if (usuario.getCpf().equals(cpf)) {
                    dataList.set(i, updatedData);
                    found = true;
                    break;
                }
            }
        }

        if (!found) {
            throw new IllegalArgumentException("Usuário com CPF " + cpf + " não encontrado.");
        }

        saveToJson(dataList);
    }

    public void editDataByContaBancaria(String contaBancaria, T updatedData, Class<T> clazz) throws IOException {
        try {
            List<T> dataList = loadFromJson(clazz);
            boolean found = false;

            for (int i = 0; i < dataList.size(); i++) {
                T data = dataList.get(i);
                if (data instanceof Cliente cliente) {
                    if (cliente.getContaBancaria().equals(contaBancaria)) {
                        dataList.set(i, updatedData); // Atualiza o cliente na lista
                        found = true;
                        break;
                    }
                }
            }

            if (!found) {
                throw new IllegalArgumentException("Usuário com conta bancária " + contaBancaria + " não encontrado.");
            }

            this.saveToJson(dataList); // Salva a lista atualizada no JSON
        } catch (IOException e) {
            System.out.println("Erro no método editDataByContaBancaria: " + e.getMessage());
            throw e; // Relança a exceção após registrar o erro
        }
    }

    public void removeDataByCpf(String cpf, Class<T> clazz) throws IOException {
        List<T> dataList = loadFromJson(clazz);
        boolean removed = dataList.removeIf(data -> {
            if (data instanceof Usuario) {
                Usuario usuario = (Usuario) data;
                return usuario.getCpf().equals(cpf);
            }
            return false;
        });

        if (!removed) {
            throw new IllegalArgumentException("Usuário com CPF " + cpf + " não encontrado.");
        }

        saveToJson(dataList);
    }

}