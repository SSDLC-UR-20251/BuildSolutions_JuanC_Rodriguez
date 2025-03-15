package com.example;

import org.json.JSONObject;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Arrays;
import org.json.JSONArray;


import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    @Test
public void testLeerArchivo() {
    String path = "src/main/resources/transactions.txt"; // Ruta corregida
    String contenido = App.leerArchivo(path);

    assertNotNull(contenido, "El contenido del archivo no debe ser nulo");
    assertFalse(contenido.isEmpty(), "El archivo no debe estar vacío");
    }
    
    @Test
    public void testObtenerTransacciones() {
        String path = "src/main/resources/transactions.txt";
        String contenido = App.leerArchivo(path);
        JSONObject json = new JSONObject(contenido);

        assertNotNull(json, "El JSON no debe ser nulo");
        assertTrue(json.has("juan.jose@urosario.edu.co"), "Debe existir la clave 'juan.jose@urosario.edu.co'");

        JSONArray transacciones = json.getJSONArray("sara.palaciosc@urosario.edu.co");
        assertNotNull(transacciones, "Las transacciones de Sara no deben ser nulas");
        assertFalse(transacciones.isEmpty(), "Sara debe tener transacciones");
    }

    @Test
    public void testGenerarExtracto() {
        String usuario = "sara.palaciosc@urosario.edu.co";
        List<Transaccion> transacciones = App.obtenerTransacciones("src/main/resources/transactions.txt").get(usuario);

        String extracto = App.generarExtracto(transacciones);
        assertNotNull(extracto, "El extracto no debe ser nulo");
        assertTrue(extracto.contains("Deposit"), "El extracto debe contener depósitos");
        assertTrue(extracto.contains("Withdrawal"), "El extracto debe contener retiros");
    }

}
