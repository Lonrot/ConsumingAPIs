package org.northcoders.extension.dao;


import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.northcoders.extension.FlightDetails;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;

import java.net.http.HttpRequest;
import java.net.http.HttpResponse;


public class FlightDAO {
    private static final String BASE_URL = "https://api.schiphol.nl/public-flights/flights";
    private static API_config api_config = new API_config();
    public static void getData() {

        ObjectMapper mapper = new ObjectMapper();
        try {
            HttpClient client = HttpClient.newHttpClient();
            HttpRequest request = HttpRequest.newBuilder().headers("app_key", api_config.getKey(), "app_id", api_config.getID(), "ResourceVersion", "v4").uri(new URI(BASE_URL))
                    .GET().build();
            var response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var objResult = mapper.readValue(response.body(), FlightDetails.class);
            JSONPObject parser = new JsonParser();
            System.out.println(objResult.getFlightName());
        } catch (IOException | InterruptedException|URISyntaxException e) {
            System.out.println(e.getMessage());
        }



    }

    public static void main(String[] args) {
        getData();
    }
}
