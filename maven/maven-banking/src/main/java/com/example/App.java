package com.example;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import org.json.*;

public class App {

    // 🔹 1. Leer el archivo JSON desde un .txt
    public static String leerArchivo(String rutaArchivo) {
    try (InputStream inputStream = App.class.getClassLoader().getResourceAsStream("transactions.txt")) {
        if (inputStream == null) {
            System.out.println("Error: El archivo no fue encontrado en resources.");
            return null;
        }
        return new String(inputStream.readAllBytes());
    } catch (IOException e) {
        System.out.println("Error al leer el archivo de transacciones.");
        return null;
    }
}

    // 🔹 2. Obtener transacciones de un usuario específico
    public static List<JSONObject> obtenerTransacciones(String jsonData, String usuario) {
        List<JSONObject> transaccionesUsuario = new ArrayList<>();
        JSONArray transacciones = new JSONArray(jsonData);
        for (int i = 0; i < transacciones.length(); i++) {
            JSONObject transaccion = transacciones.getJSONObject(i);
            if (transaccion.getString("usuario").equalsIgnoreCase(usuario)) {
                transaccionesUsuario.add(transaccion);
            }
        }
        return transaccionesUsuario;
    }

    // 🔹 3. Generar extracto bancario en un archivo .txt
    public static void generarExtracto(String usuario, List<JSONObject> transacciones) {
        String nombreArchivo = "extracto_" + usuario + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(nombreArchivo))) {
            writer.write("Extracto bancario para " + usuario + "\n");
            writer.write("====================================\n");
            for (JSONObject transaccion : transacciones) {
                writer.write("Fecha: " + transaccion.getString("fecha") + ", Monto: " + transaccion.getDouble("monto") + ", Descripción: " + transaccion.getString("descripcion") + "\n");
            }
            System.out.println("Extracto generado: " + nombreArchivo);
        } catch (IOException e) {
            System.out.println("Error al generar el extracto bancario.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del usuario: ");
        String usuario = scanner.nextLine();
        scanner.close();
        
        String jsonData = leerArchivo("src/main/resources/transactions.txt");
        if (jsonData != null) {
            List<JSONObject> transacciones = obtenerTransacciones(jsonData, usuario);
            generarExtracto(usuario, transacciones);
        }
    }
}
