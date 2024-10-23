package edu.ntnu.idi.bidata.foodstorage;

import edu.ntnu.idi.bidata.ingredients.Ingredient;
import java.time.LocalDate;
import java.util.ArrayList;

/**
 * Stores all the ingredients.
 *
 * @author Johannes Nupen Theigen
 * @version 0.0.5
 * @since 10.23.2024
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
   *
   * @param ingredient an ingredient (anything edible) that can be added to the food storage.
   */
  public void addIngredient(Ingredient ingredient) {
    ingredients.add(ingredient);
  }

  /**
   * Removes an ingredient from the food storage.
   *
   */
  public void removeIngredient() {
    boolean flag = false;
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getIngredientName().contains(ingredient.getIngredientName())) {
        flag = true;
        ingredients.remove(ingredient);
      }
      if (!flag) {
        System.out.println("Could not delete - the ingredient doesn't exist.");
      }
    }
  }

  /**
   * Removes all ingredients from the food storage.
   */
  public void emptyStorage() {
    ingredients.clear();
  }

  /**
   * Returns the number of ingredients in the storage.
   *
   * @return the number of ingredients in the storage
   */
  public int getNumberOfIngredients() {
    return ingredients.size();
  }

  /**
   * Finds an ingredient in the storage when provided a String to search for.
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

  /**
   * Lists all available ingredients in the storage.
   */
  public void listAllIngredients() {
    for (Ingredient ingredient : ingredients) {
      System.out.println(ingredient.getIngredientName() + ingredient.getDescriptionOfIngredient());
    }
  }

  /**
   * Lists all the expired ingredients in the storage.
   */
  public void listExpiredIngredients() {
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getExpirationDate().isBefore(LocalDate.now())) {
        System.out.println(ingredient.getIngredientName()
            + ingredient.getDescriptionOfIngredient());
      }
    }
  }

  /**
   * Prints the value of all ingredients.
   */
  public void printValueOfAllIngredients() {
    double totalValue = 0;
    for (Ingredient ingredient : ingredients) {
      totalValue = totalValue + ingredient.getPrice();
    }
    System.out.println("The total value of all ingredients is: " + totalValue);
  }

  /**
   * Prints the value of all expired ingredients.
   */
  public void printValueOfExpiredIngredients() {
    double totalValue = 0;
    for (Ingredient ingredient : ingredients) {
      if (ingredient.getExpirationDate().isBefore(LocalDate.now())) {
        totalValue = totalValue + ingredient.getPrice();
      }
    }
    System.out.println("The total value of all expired ingredients is: " + totalValue);
  }
}