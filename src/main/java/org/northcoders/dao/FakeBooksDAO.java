package org.northcoders.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import org.northcoders.model.Book;


import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class FakeBooksDAO {
    private static final String BASE_URL = "https://fakerapi.it/api/v1/books";

    public enum queryP {

        LOCALE("?_locale"),
        QUANTITY("?_quantity="),
        SEED("?_seed=");
        public final String label;

        queryP(String label) {
            this.label = label;
        }
    }

    public static String paramLocal ="";
    public static String paramSeed="";
    public static String paramQuantity="";

    //?_locale=esp_ESP&quantity=2
    // CHeck how many
    //sout asking inout]
    //depending input, call different method

    public static void askForInput() throws URISyntaxException {
        Scanner sc = new Scanner(System.in);
        System.out.println("How many parameters: ");
        int counter = Integer.parseInt(sc.nextLine());

        while (counter > 0) {
            System.out.println("Parameter: ");
            queryP value = queryP.valueOf(sc.nextLine().toUpperCase());
            switch (value) {
                case LOCALE -> checkLocale();
                case SEED -> checkSeed();
                case QUANTITY -> checkQuantity();

            }
            counter--;
            getData();
        }

    }
    //?_locale=esp_ESP&quantity=2
    public static void checkLocale() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What country in lowercase: ");
        String input = scanner.nextLine();
        paramLocal = queryP.LOCALE.label+input + "_" + input.toUpperCase();
    }

    public static void checkQuantity() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("How many: ");
        paramQuantity=queryP.QUANTITY.label+Integer.parseInt(scanner.nextLine());
    }

    public static void checkSeed() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce seed: ");
        while (true) {
            int userInput = Integer.parseInt(scanner.nextLine());
            if (Integer.toString(userInput).length() <= 5) {
                paramSeed = queryP.SEED.label+userInput;
                break;
            } else {
                System.out.println("Wrong seed length ");
            }
        }
    }


    public static void getData() throws URISyntaxException {
        List<Book> bookList = new ArrayList<>();


        try {
            ObjectMapper mapper = new ObjectMapper();
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(new URI(BASE_URL + paramLocal+paramQuantity+paramSeed))
                    .GET().build();

            var response = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
            //Navigate a tree JSON structure
            JsonNode jsonNode = mapper.readTree(response.body());
            //Represent a JSON array when more than One book is summoned from jsonNode.get().
            ArrayNode responseArray = (ArrayNode) jsonNode.get("data");

            // Using convertValue to map the JSON array to a list of Book objects
            bookList = mapper.convertValue(responseArray, new TypeReference<List<Book>>() {
            });

            //    System.out.println(result);
            //         bookList.forEach(System.out::println);
        } catch (IOException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
        bookList.stream().forEach(b -> System.out.println(b.title() + "  - Author: " + b.author()));


    }
}
