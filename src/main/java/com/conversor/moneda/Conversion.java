package com.conversor.moneda;

public class Conversion {

    private String origen;
    private String destino;
    private double cantidad;
    private double resultado;

    public Conversion(String origen, String destino,
                      double cantidad, double resultado) {
        this.origen = origen;
        this.destino = destino;
        this.cantidad = cantidad;
        this.resultado = resultado;
    }

    @Override
    public String toString() {
        return cantidad + " " + origen +
                " = " + resultado + " " + destino;
    }
}
