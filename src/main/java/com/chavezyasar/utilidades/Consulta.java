package com.chavezyasar.utilidades;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.chavezyasar.models.Moneda;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.net.URI;

public class Consulta {

        public Moneda getBase(String nombreMoneda) throws IOException, InterruptedException {
                String base = "https://v6.exchangerate-api.com/v6/593703fee6aaecfd72bac1e6/latest/";

                HttpClient client = HttpClient.newHttpClient();
                HttpRequest request = HttpRequest.newBuilder()
                                .uri(URI.create(base + nombreMoneda))
                                .build();

                HttpResponse<String> response = client
                                .send(request, HttpResponse.BodyHandlers.ofString());

                String json = response.body();

                Gson gson = new GsonBuilder()
                                .setPrettyPrinting()
                                .create();

                Moneda moneda = gson.fromJson(json, Moneda.class);

                return moneda;
        }
}
