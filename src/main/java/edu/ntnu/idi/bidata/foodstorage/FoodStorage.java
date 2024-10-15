package edu.ntnu.idi.bidata.foodstorage;

import edu.ntnu.idi.bidata.ingredients.Ingredient;
import java.util.ArrayList;

/**
 * Stores all the ingredients.

 * @author Johannes Nupen Theigen
 * @since 10.14.2024
 * @version 0.0.3
 */
public class FoodStorage {
  private ArrayList<Ingredient> ingredients;

  /**
   * Creates a storage for ingredients.
   */
  public FoodStorage() {
    ingredients = new ArrayList<>();
  }

  /**
   * Adds an ingredient to the food storage.

   * @param ingredient an ingredient (anything edible) that can be added to the food storage.
   */
  public void addIngredient(Ingredient ingredient) {
    ingredients.add(ingredient);
  }

  /**
   * Removes an ingredient from the food storage.

   * @param ingredient an ingredient (anything edible) that can be removed from the food storage.
   */
  public void removeIngredient(Ingredient ingredient) {
    ingredients.remove(ingredient);
  }

  /**
   * Returns the number of ingredients in the storage.

   * @return the number of ingredients in the storage
   */
  public int getNumberOfIngredients() {
    return ingredients.size();
  }

  /**
   * Lists all available ingredients in the storage.
   */
  public void listIngredients() {
    for (Ingredient ingredient : ingredients) {
      System.out.println(ingredient.getIngredientName());
    }
  }

  /**
   * Finds an available ingredient when provided a String to search for.
   */
  public void findIngredient() {
    boolean flag = false;
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getIngredientName().contains(ingredient.getIngredientName())) {
        flag = true;
        System.out.println(ingredient.getIngredientName());
      }
    }
    if (!flag) {
      System.out.println("Could not find the ingredient");
    }
  }
}