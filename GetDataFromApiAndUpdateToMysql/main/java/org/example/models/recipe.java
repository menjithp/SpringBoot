package org.example.models;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class recipe {
    private String id;
    private String name;
    private List<String> ingredients;
    private List<String> instructions;
    private int prepTimeMinutes;
    private int cookTimeMinutes;
    private int servings;
    private String difficulty;
    private String cuisine;
    private int caloriesPerServing;
    private List<String> tags;
    private int userId;
    private String image;
    private String rating;
    private double reviewCount;
    private List<String> mealType;
}
