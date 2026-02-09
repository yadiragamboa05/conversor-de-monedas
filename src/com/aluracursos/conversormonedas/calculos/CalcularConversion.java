package com.aluracursos.conversormonedas.calculos;

import java.util.Map;

public class CalcularConversion {
    public Double convertir(String monedaBase, String monedaDestino, Double cantidad) {

        ObtenerTarifasConversion tarifasProvider = new ObtenerTarifasConversion();

        Map<String, Double> tarifas = tarifasProvider.obtenerTarifas(monedaBase);

        if (tarifas.containsKey(monedaDestino)) {
            Double tarifaDeConversion = tarifas.get(monedaDestino);

            return cantidad * tarifaDeConversion;
        } else {
            System.out.println("Error: No se encontró la tasa para " + monedaDestino);
            return 0.0;
        }
    }
}