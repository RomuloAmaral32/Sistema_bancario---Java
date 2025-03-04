package com.mycompany.sistema_bancario;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class JsonHandler<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filePath;

    public JsonHandler(String filePath) {
        this.filePath = filePath;
    }

    // Método para salvar a lista de dados no arquivo JSON
    public void saveToJson(List<T> data) throws IOException {
        objectMapper.writeValue(new File(filePath), data);
    }

    // Método para carregar os dados do JSON
    public List<T> loadFromJson(Class<T> clazz) throws IOException {
        File file = new File(filePath);

        // Verificar se o arquivo existe e se não está vazio
        if (!file.exists() || file.length() == 0) {
            // Retornar uma lista vazia se o arquivo não existir ou estiver vazio
            return new java.util.ArrayList<>();
        }

        // Se o arquivo existe e contém dados, carregar a lista
        return objectMapper.readValue(file,
                objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}
