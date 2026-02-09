package com.aluracursos.conversormonedas.conexion;

import com.aluracursos.conversormonedas.modelos.Moneda;
import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class Conexion {
    public Moneda buscaTarifaConversion(String monedaOrigen){
        String API_Key = "73f458043b0851f3a9f048ac";
        URI direccion = URI.create("https://v6.exchangerate-api.com/v6/" + API_Key + "/latest/" + monedaOrigen);

        // Creando cliente HTTP
        HttpClient cliente = HttpClient.newHttpClient();

        // Creando la solicitud
        HttpRequest request = HttpRequest.newBuilder()
                .uri(direccion)
                .GET()
                .build();

        try {
            HttpResponse<String> respuesta = cliente
                    .send(request, HttpResponse.BodyHandlers.ofString());
            return new Gson().fromJson(respuesta.body(), Moneda.class);
        } catch (Exception e){
            throw new RuntimeException("No encontré esa película");
        }
    }
}