package com.aluracursos.conversormonedas.calculos;

import com.aluracursos.conversormonedas.conexion.Conexion;
import com.aluracursos.conversormonedas.modelos.Moneda;

import java.util.Map;

public class ObtenerTarifasConversion {
    protected Map<String, Double> obtenerTarifas(String monedaBase){

        Conexion consulta = new Conexion();

        Moneda consultaMoneda = consulta.buscaTarifaConversion(monedaBase);

        Map<String, Double> tarifasConversion = consultaMoneda.conversion_rates();

        return tarifasConversion;
    }
}
