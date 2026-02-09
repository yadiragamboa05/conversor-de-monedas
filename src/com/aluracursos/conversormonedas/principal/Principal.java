package com.aluracursos.conversormonedas.principal;

import com.aluracursos.conversormonedas.calculos.CalcularConversion;
import com.aluracursos.conversormonedas.utils.GeneradorDeArchivos;

import java.util.Scanner;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Principal {
    public static void main(String[] args) {
        Scanner lectura = new Scanner(System.in);
        CalcularConversion conversor = new CalcularConversion();
        GeneradorDeArchivos generador = new GeneradorDeArchivos();

        int opcion = 0;

        while (opcion != 8) {
            MenuPrincipal.exibirMenu();

            try {
                opcion = lectura.nextInt();
                lectura.nextLine();
            } catch (Exception e) {
                System.out.println("Por favor, ingrese solo números.");
                esperar(lectura);
                lectura.nextLine();
                continue;
            }

            if (opcion == 8) {
                System.out.println("Saliendo del programa, gracias por utilizar nuestros servicios");
                break;
            }

            String monedaBase = "";
            String monedaDestino = "";

            switch (opcion) {
                case 1:
                    monedaBase = "USD";
                    monedaDestino = "ARS";
                    break;
                case 2:
                    monedaBase = "ARS";
                    monedaDestino = "USD";
                    break;
                case 3:
                    monedaBase = "USD";
                    monedaDestino = "COP";
                    break;
                case 4:
                    monedaBase = "COP";
                    monedaDestino = "USD";
                    break;
                case 5:
                    monedaBase = "ARS";
                    monedaDestino = "COP";
                    break;
                case 6:
                    monedaBase = "COP";
                    monedaDestino = "ARS";
                    break;
                case 7:
                    System.out.println("Ingrese el código de la moneda base (ej: BRL, EUR, PEN): ");
                    monedaBase = lectura.nextLine().toUpperCase();

                    if (monedaBase.length() != 3) {
                        System.out.println("Error: El código de moneda debe tener exactamente 3 letras.");
                        esperar(lectura);
                        continue;
                    }

                    System.out.println("Ingrese el código de la moneda destino (ej: JPY, RUB): ");
                    monedaDestino = lectura.nextLine().toUpperCase();

                    if (monedaDestino.length() != 3) {
                        System.out.println("Error: El código de moneda debe tener exactamente 3 letras.");
                        esperar(lectura);
                        continue;
                    }
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
                    esperar(lectura);
                    continue;
            }

            System.out.println("Ingrese el valor que desea convertir:");

            try {
                Double cantidad = lectura.nextDouble();

                lectura.nextLine();

                Double resultado = conversor.convertir(monedaBase, monedaDestino, cantidad);

                System.out.println("El valor " + cantidad + " [" + monedaBase + "] corresponde al valor final de =>>> "
                        + resultado + " [" + monedaDestino + "]");

                LocalDateTime fechaActual = LocalDateTime.now();

                DateTimeFormatter formateador = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String fechaFormateada = fechaActual.format(formateador);

                String registro = "[" + fechaFormateada + "] Se convirtió " + cantidad + " " + monedaBase + " a " + resultado + " " + monedaDestino;

                generador.guardarJson(registro);

                esperar(lectura);
            } catch (RuntimeException e) {
                System.out.println("Error: " + e.getMessage());
                esperar(lectura);
            } catch (Exception e) {
                System.out.println("Error al ingresar el monto. Asegúrese de usar el formato correcto (ej: 100,50 o 100.50 según tu sistema).");
                lectura.nextLine();
                esperar(lectura);
            }
        }

        lectura.close();
    }

    private static void esperar(Scanner lectura) {
        System.out.println("\nPresione Enter para volver al menú principal...");
        lectura.nextLine();
    }
}
