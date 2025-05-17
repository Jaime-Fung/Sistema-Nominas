/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LogicaDeNegocio;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author esteban
 */
public class IdControl {
    
    
    private String controlFile;
    private Map<String, Integer> idMap;

    public IdControl(String controlFile) throws IOException {
        this.controlFile = controlFile;
        this.idMap = new HashMap<>();
        cargarIds();
    }

    private void cargarIds() throws IOException {
        
        try (BufferedReader reader = new BufferedReader(new FileReader(controlFile))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("=");
                idMap.put(parts[0], Integer.parseInt(parts[1]));
            }
        }
    }

    private void guardarIds() throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(controlFile))) {
            for (Map.Entry<String, Integer> entry : idMap.entrySet()) {
                writer.write(entry.getKey() + "=" + entry.getValue());
                writer.newLine();
            }
        }
    }

    public synchronized int getSiguienteId(String fileName) throws IOException {
        int nextId = idMap.getOrDefault(fileName, 1);
        idMap.put(fileName, nextId + 1);
        guardarIds();
        return nextId;
    }
    
    
}
