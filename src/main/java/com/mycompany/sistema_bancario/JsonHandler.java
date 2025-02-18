/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.sistema_bancario;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Darlan Henrique da Costa Silva
 * @matricula 202176038
 */
public class JsonHandler<T> {
    private final String filePath;

    public JsonHandler(String filePath) {
        this.filePath = filePath;
    }

    public void saveToJson(List<T> data) throws IOException {
        Gson gson = new Gson();
        try (FileWriter writer = new FileWriter(filePath)) {
            gson.toJson(data, writer);
        }
    }

    public List<T> loadFromJson(Class<T> clazz) throws IOException {
        Gson gson = new Gson();
        try (FileReader reader = new FileReader(filePath)) {
            Type listType = TypeToken.getParameterized(ArrayList.class, clazz).getType();
            return gson.fromJson(reader, listType);
        } catch (IOException e) {
            return new ArrayList<>();
        }
    }
}
