package edu.ntnu.idi.bidata.inventory;

import edu.ntnu.idi.bidata.items.Ingredient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Stores all the ingredients.
 *
 * @author Johannes Nupen Theigen
 * @version 0.0.7
 * @since 10.24.2024
 */
public class FoodStorage {
  private ArrayList<Ingredient> storage;

  /**
   * Creates a storage for ingredients.
   */
  public FoodStorage() {
    storage = new ArrayList<>();
  }

  /**
   * Adds an ingredient to the food storage.
   *
   * @param ingredient an ingredient (anything edible) that can be added to the food storage.
   */
  public void addIngredient(Ingredient ingredient) {
    if (storage.contains(ingredient)) {
      System.out.println("The ingredient already exists!");
    } else {
      storage.add(ingredient);
      System.out.println("Successfully added an ingredient to the storage!");
    }
  }

  /**
   * Removes an ingredient from the food storage.
   */
  public void removeIngredient(String ingredientName) {
    boolean found = false;
    Iterator<Ingredient> iterator = storage.iterator();

    while (iterator.hasNext()) {
      Ingredient ingredient = iterator.next();
      if (ingredient.getIngredientName().equalsIgnoreCase(ingredientName)) {
        iterator.remove();
        found = true;
        System.out.println("The ingredient has been removed!");
      }
    }
    if (!found) {
      System.out.println("Could not delete - the ingredient doesn't exist in the storage!");
    }
  }

  /**
   * Returns the number of ingredients in the storage.
   *
   * @return the number of ingredients in the storage
   */
  public int getNumberOfIngredients() {
    if (storage.isEmpty()) {
      System.out.println("There are no ingredients in the storage!");
      return 0;
    }
    return storage.size();
  }

  /**
   * Finds an ingredient in the storage when provided a String to search for.
   */
  public void findIngredient(String ingredientName) {
    boolean found = false;
    for (Ingredient ingredient : storage) {
      if (ingredient.getIngredientName().equalsIgnoreCase(ingredientName)) {
        found = true;
        System.out.println(ingredient.getIngredientName());
      }
    }
    if (!found) {
      System.out.println("Could not find the ingredient in the storage!");
    }
  }

  /**
   * Lists all available ingredients in the storage.
   */
  public void listAllIngredients() {
    if (storage.isEmpty()) {
      System.out.println("There are no ingredients in the storage!");
    } else {
      for (Ingredient ingredient : storage) {
        System.out.println(ingredient.getIngredientName() + ingredient.getDescriptionOfIngredient());
      }
    }
  }

  /**
   * Lists all the expired ingredients in the storage.
   */
  public void listExpiredIngredients() {
    if (storage.isEmpty()) {
      System.out.println("There are no ingredients in the storage!");
    } else {
      boolean expired = false;
      for (Ingredient ingredient : storage) {
        if (ingredient.getExpirationDate().isBefore(LocalDate.now())) {
          System.out.println(ingredient.getIngredientName()
              + ingredient.getDescriptionOfIngredient());
          expired = true;
        }
      }
      if (!expired) {
        System.out.println("There are no expired ingredients in the storage!");
      }
    }
  }

  /**
   * Prints the value of all ingredients.
   */
  public void printValueOfAllIngredients() {
    double totalValue = 0;
    if (storage.isEmpty()) {
      System.out.println("There are no ingredients in the storage!");
    } else {
      for (Ingredient ingredient : storage) {
        totalValue += ingredient.getPrice();
      }
      System.out.println("The total value of all ingredients is: " + totalValue);
    }
  }

  /**
   * Prints the value of all expired ingredients.
   */
  public void printValueOfExpiredIngredients() {
    double totalValue = 0;

    if (storage.isEmpty()) {
      System.out.println("There are no ingredients in the storage!");
    } else {
      boolean expired = false;
      for (Ingredient ingredient : storage) {
        if (ingredient.getExpirationDate().isBefore(LocalDate.now())) {
          totalValue += ingredient.getPrice();
          expired = true;
        }
      }
      if (expired) {
        System.out.println("The total value of all expired ingredients is: " + totalValue);
      } else {
        System.out.println("There are no expired ingredients in the storage!");
      }
    }
  }
}