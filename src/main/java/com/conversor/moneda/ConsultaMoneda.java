package com.conversor.moneda;

import com.google.gson.Gson;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class ConsultaMoneda {

    public Moneda buscarMonedas() {

        String direccion = "https://api.exchangerate-api.com/v4/latest/USD";

        HttpClient client = HttpClient.newHttpClient();

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(direccion))
                .build();

        try {
            HttpResponse<String> response =
                    client.send(request, HttpResponse.BodyHandlers.ofString());

            Gson gson = new Gson();

            return gson.fromJson(response.body(), Moneda.class);

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException("Error al consultar la API");
        }
    }
}
