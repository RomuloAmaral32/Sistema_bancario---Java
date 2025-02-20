/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_bancario;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.core.type.TypeReference;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author Darlan Henrique da Costa Silva
 * @matricula 202176038
 */
public class JsonHandler<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final String filePath;

    public JsonHandler(String filePath) {
        this.filePath = filePath;
    }

    public void saveToJson(List<T> data) throws IOException {
        objectMapper.writeValue(new File(filePath), data);
    }

    public List<T> loadFromJson(Class<T> clazz) throws IOException {
        return objectMapper.readValue(new File(filePath),
                objectMapper.getTypeFactory().constructCollectionType(List.class, clazz));
    }
}
