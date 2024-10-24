package edu.ntnu.idi.bidata.inventory;

import edu.ntnu.idi.bidata.items.Ingredient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Stores all the ingredients.
 *
 * @author Johannes Nupen Theigen
 * @version 0.0.8
 * @since 10.24.2024
 */
public class FoodStorage {
  private final ArrayList<Ingredient> storage;

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
    boolean ingredientExists = false;

    for (Ingredient existingIngredient : storage) {
      if (existingIngredient.getName().equals(ingredient.getName())) {
        existingIngredient.setAmount(existingIngredient.getAmount() + ingredient.getAmount());
        System.out.println("Updated the amount of the existing ingredient: "
            + existingIngredient.getName());
        ingredientExists = true;
      }
    }
    if (!ingredientExists) {
      storage.add(ingredient);
      System.out.println("Successfully added an ingredient to the storage!");
    }
  }

  /**
   * Removes an ingredient from the food storage.
   */
  public void removeIngredient(Ingredient ingredient) {
    boolean ingredientExists = false;
    for (Ingredient existingIngredient : storage) {
      if (existingIngredient.getName().equals(ingredient.getName())) {
        existingIngredient.setAmount(existingIngredient.getAmount() - ingredient.getAmount());
        System.out.println("Updated the amount of the existing ingredient: "
            + existingIngredient.getName());
        ingredientExists = true;
      }
      if (existingIngredient.getAmount() <= 0) {
        storage.remove(existingIngredient);
        System.out.println("Removed" + existingIngredient.getName());
      }
    }
    if (!ingredientExists) {
      System.out.println("The ingredient you tried to remove does not exist.");
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
    int index = 0;
    while (index < storage.size()) {
      Ingredient ingredient = storage.get(index);
      if (ingredient.getName().equalsIgnoreCase(ingredientName)) {
        found = true;
        System.out.println(ingredient.getName());
      }
      index++;
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
        System.out.println(ingredient.getName() + ingredient.getDescriptionOfIngredient());
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
          System.out.println(ingredient.getName()
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
   * Prints the value of all ing ingredients.
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