package com.aluracursos.conversormonedas.utils;

import java.io.FileWriter;
import java.io.IOException;

public class GeneradorDeArchivos {

    public void guardarJson(String mensaje) {
        try {
            FileWriter escritura = new FileWriter("historial_conversiones.txt", true);

            escritura.write(mensaje);
            escritura.write("\n");
            escritura.close();
        } catch (IOException e) {
            System.out.println("Error al guardar el historial: " + e.getMessage());
        }
    }
}