package com.conversor.moneda;

import java.util.ArrayList;
import java.util.Scanner;
import java.text.DecimalFormat;

/*
 * Conversor de Monedas
 * Proyecto Java - Consumo de API REST
 * Autor: David Styvenn Angel Rodriguez
 */

public class Principal {

    public static void main(String[] args) {

        // Consulta a la API
        ConsultaMoneda consulta = new ConsultaMoneda();

        // Obtener datos de monedas
        Moneda datos = consulta.buscarMonedas();

        // Lectura del usuario
        Scanner teclado = new Scanner(System.in);
        DecimalFormat df = new DecimalFormat("#.00");

        // Historial de conversiones
        ArrayList<Conversion> historial = new ArrayList<>();

        // Monedas populares
        String[] monedas = {"USD", "COP", "EUR", "BRL", "ARS"};

        // Nombres de monedas
        String[] nombres = {
                "Dólar estadounidense",
                "Peso colombiano",
                "Euro",
                "Real brasileño",
                "Peso argentino"
        };

        // Menú principal
        while (true) {

            System.out.println("\n===== CONVERSOR DE MONEDAS =====");

            for (int i = 0; i < monedas.length; i++) {
                System.out.println(
                        (i + 1) + " - " +
                                monedas[i] + " - " +
                                nombres[i]
                );
            }

            System.out.println("6 - Ver historial");
            System.out.println("7 - Salir");

            System.out.print("Seleccione moneda origen: ");
            int opcionOrigen = teclado.nextInt();

            // Salir
            if (opcionOrigen == 7) {
                System.out.println("Programa finalizado.");
                break;
            }

            // Mostrar historial
            if (opcionOrigen == 6) {
                System.out.println("\n===== HISTORIAL =====");

                if (historial.isEmpty()) {
                    System.out.println("No hay conversiones aún.");
                } else {
                    for (Conversion c : historial) {
                        System.out.println(c);
                    }
                }
                continue;
            }

            String origen = monedas[opcionOrigen - 1];

            System.out.print("Seleccione moneda destino: ");
            int opcionDestino = teclado.nextInt();

            String destino = monedas[opcionDestino - 1];

            System.out.print("Cantidad: ");
            double cantidad = teclado.nextDouble();

            // Obtener tasas
            Double tasaOrigen = datos.getRates().get(origen);
            Double tasaDestino = datos.getRates().get(destino);

            // Conversión
            double resultado = cantidad * (tasaDestino / tasaOrigen);

            // Guardar en historial
            Conversion nuevaConversion =
                    new Conversion(origen, destino, cantidad, resultado);

            historial.add(nuevaConversion);

            // Mostrar resultado
            System.out.println(
                    df.format(cantidad) + " " + origen +
                            " = " + df.format(resultado) + " " + destino
            );
        }

        teclado.close();
    }
}
