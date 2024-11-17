package edu.ntnu.idi.bidata.recipe;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * The RecipeBook class represents a recipe book that can store and manages recipes.
 * </p>
 *
 * <p>>It provides methods to:</p>
 *
 * <ul>
 *   <li>Add a new recipe to the recipe book</li>
 *   <li>Remove a recipe from the recipe book</li>
 * </ul>
 *
 * @author Johannes Nupen Theigen
 * @version 0.0.1
 * @since 11.17.2024
 */
public class RecipeBook {
  private final Map<String, Recipe> recipes;

  /**
   * <p>
   * Creates a new instance of RecipeBook that can store and manage recipes.
   * </p>
   *
   * <p>
   * Example of usage:
   * <pre>
   *     <code>
   *       RecipeBook recipeBook = new RecipeBook();
   *     </code>
   *   </pre>
   * </p>
   */
  public RecipeBook() {
    recipes = new HashMap<>();
  }

  /**
   * <p>
   * Adds a new recipe to the recipe book.
   * </p>
   *
   * @param name                the name of the recipe
   * @param description         the description of the recipe
   * @param instruction         the instruction of the recipe
   * @param requiredIngredients the ingredients required for the rcipe
   * @return true if the recipe already exists in the recipe book, false otherwise.
   *
   * <p>
   * Example of usage:
   * </p>
   *
   * <pre>
   * <code>
   * recipeBook.addRecipe("Apple Pie","A delicious pie",
   * "1 preheat the oven to 180 degrees",ingredients);
   * </code>
   * </pre>
   */
  public boolean addRecipe(String name, String description,
                           String instruction, Map<String, Double> requiredIngredients) {
    boolean recipeExists = false;
    if (recipes.containsKey(name)) {
      recipeExists = true;
    } else {
      Recipe newRecipe = new Recipe(name, description, instruction, requiredIngredients);
      recipes.put(name, newRecipe);
    }
    return recipeExists;
  }

  /**
   * <p>
   * Removes a recipe from the recipe book.
   * </p>
   *
   * @param name the name of the recipe to remove
   * @return true if the recipe exists in the recipe book, false otherwise.
   */
  public boolean removeRecipe(String name) {
    boolean recipeExists = false;
    if (recipes.containsKey(name)) {
      recipes.remove(name);
      recipeExists = true;
    }
    return recipeExists;
  }
}
