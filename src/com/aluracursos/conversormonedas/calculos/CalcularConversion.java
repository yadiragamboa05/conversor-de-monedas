package com.aluracursos.conversormonedas.calculos;

import java.util.Map;

public class CalcularConversion {
    public Double convertir(String monedaBase, String monedaDestino, Double cantidad) {

        ObtenerTarifasConversion tarifasProvider = new ObtenerTarifasConversion();

        Map<String, Double> tarifas = tarifasProvider.obtenerTarifas(monedaBase);

        if (tarifas == null || tarifas.isEmpty()) {
            throw new RuntimeException("La moneda base '" + monedaBase + "' no fue encontrada.");
        }

        if (tarifas.containsKey(monedaDestino)) {
            Double tarifaDeConversion = tarifas.get(monedaDestino);

            return cantidad * tarifaDeConversion;
        } else {
            throw new RuntimeException("No se encontró la tasa de cambio para: " + monedaDestino);
        }
    }
}