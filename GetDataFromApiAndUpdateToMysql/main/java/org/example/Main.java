package org.example;
import java.net.URI;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpClient;
import java.io.IOException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.models.recipe;
import org.example.models.recipes;
import org.example.repository.UpdateFromJava;

public class Main {

    public static recipes getRecipes(String json){
        ObjectMapper objectMapper = new ObjectMapper();
        recipes recipeList = null;
        try {
            recipeList = objectMapper.readValue(json, recipes.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return recipeList;
    }
    public static void main(String[] args) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://dummyjson.com/recipes"))
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();

        HttpResponse<String> response = null;
        try {
            response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(response.body());
        recipes recipeList=getRecipes(response.body());
        System.out.println("Total recipes: " + recipeList);

        UpdateFromJava updateFromJava = new UpdateFromJava();
        updateFromJava.updateData(recipeList);
    }
}