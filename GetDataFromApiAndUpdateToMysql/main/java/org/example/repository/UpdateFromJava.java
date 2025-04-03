package org.example.repository;

import org.example.models.recipe;
import org.example.models.recipes;

import java.sql.*;
import java.util.List;

public class UpdateFromJava {
    public void updateData(recipes recipesList) {
        List<recipe> recipes = recipesList.getRecipes();
        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3303/crudapp?useSSL=false", "root", "root");

            if(!doesTableExist(con, "recipes")) {
                createTable(con);
            }
            String sql = "INSERT IGNORE INTO recipes ( name, ingredients, instructions, prepTimeMinutes, cookTimeMinutes, servings, difficulty, cuisine, caloriesPerServing, tags, userId, image, rating, reviewCount, mealType) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = con.prepareStatement(sql);

            for (recipe rec : recipes) {
              //  ps.setInt(1, Integer.parseInt(rec.getId()));
                ps.setString(1, rec.getName());
                ps.setString(2, rec.getIngredients().toString());
                ps.setString(3, rec.getInstructions().toString());
                ps.setInt(4, rec.getPrepTimeMinutes());
                ps.setInt(5, rec.getCookTimeMinutes());
                ps.setInt(6, rec.getServings());
                ps.setString(7, rec.getDifficulty());
                ps.setString(8, rec.getCuisine());
                ps.setInt(9, rec.getCaloriesPerServing());
                ps.setString(10, rec.getTags().toString());
                ps.setInt(11, rec.getUserId());
                ps.setString(12, rec.getImage());
                ps.setDouble(13, Double.parseDouble(rec.getRating()));
                ps.setInt(14, (int) rec.getReviewCount());
                ps.setString(15, rec.getMealType().toString());

                ps.addBatch();
            }

            ps.executeBatch();
            System.out.println("Records are inserted successfully......");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (ps != null) ps.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static boolean doesTableExist(Connection connection, String tableName) throws SQLException {
        DatabaseMetaData metaData = connection.getMetaData();
        try (ResultSet resultSet = metaData.getTables(null, null, tableName, new String[] {"TABLE"})) {
            return resultSet.next();
        }
    }
    private static void createTable(Connection con) throws SQLException {
        String createTableSQL = "CREATE TABLE recipes (" +
                "id INT NOT NULL AUTO_INCREMENT, " +
                "name VARCHAR(255) NOT NULL, " +
                "ingredients TEXT, " +
                "instructions TEXT, " +
                "prepTimeMinutes INT, " +
                "cookTimeMinutes INT, " +
                "servings INT, " +
                "difficulty VARCHAR(50), " +
                "cuisine VARCHAR(100), " +
                "caloriesPerServing INT, " +
                "tags TEXT, " +
                "userId INT, " +
                "image VARCHAR(255), " +
                "rating DOUBLE, " +
                "reviewCount INT, " +
                "mealType VARCHAR(100), " +
                "PRIMARY KEY (id))";
        try (Statement stmt = con.createStatement()) {
            stmt.executeUpdate(createTableSQL);
            System.out.println("Table 'recipes' created successfully...");
        }
    }
}