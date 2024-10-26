package edu.ntnu.idi.bidata.inventory;

import edu.ntnu.idi.bidata.items.Ingredient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

/**
 * Storage that holds ingredients.
 *
 * @author Johannes Nupen Theigen
 * @version 0.1.0
 * @since 10.24.2024
 */
public class FoodStorage {
  private final ArrayList<Ingredient> storage;

  /**
   * Creates a storage that.
   */
  public FoodStorage() {
    storage = new ArrayList<>();
  }

  /**
   * Adds an ingredient to the food storage. If the ingredient already exists
   * the quantity of the existing ingredient incremented by 1.
   *
   * @param ingredient an ingredient that can be added to the storage.
   */
  public void addIngredient(Ingredient ingredient) {
    boolean ingredientExists = false;

    for (Ingredient existingIngredient : storage) {
      if (existingIngredient.getName().equals(ingredient.getName())) {
        existingIngredient.setQuantity(existingIngredient.getQuantity() + ingredient.getQuantity());
        System.out.println("Updated the amount of the existing ingredient: "
            + existingIngredient.getName());
        ingredientExists = true;
      }
    }
    if (!ingredientExists) {
      storage.add(ingredient);
      System.out.println("Successfully added" + ingredient.getName() + "to the storage!");
    }
  }

  /**
   * Decreases the quantity an ingredient from the food storage.
   * If the quantity gets to 0, the ingredient will be removed from the storage.
   */
  public void removeIngredient(Ingredient ingredient) {
    boolean ingredientExists = false;
    Iterator<Ingredient> it = storage.iterator();

    while (it.hasNext()) {
      Ingredient existingIngredient = it.next();
      if (existingIngredient.getName().equals(ingredient.getName())) {
        ingredientExists = true;
        if (existingIngredient.getQuantity() > 0) {
          existingIngredient.setQuantity(existingIngredient.getQuantity() - 1);
          System.out.println("Updated the amount of the existing ingredient: "
              + existingIngredient.getName());
        }
        if (existingIngredient.getQuantity() <= 0) {
          it.remove();
          System.out.println("Removed" + existingIngredient.getName());
        }
      }
    }
    if (!ingredientExists) {
      System.out.println("The ingredient you tried to remove does not exist!");
    }
  }

  /**
   * Returns the number of ingredients in the storage.
   *
   * @return the number of ingredients in the storage
   */
  public int getNumberOfIngredients() {
    if (storage.isEmpty()) {
      return 0;
    }
    return storage.size();
  }

  /**
   * Finds an ingredient in the storage when provided a String to search for.
   */
  public void findIngredient(String ingredientName) {
    boolean ingredientExists = false;
    int index = 0;
    while (index < storage.size()) {
      Ingredient ingredient = storage.get(index);
      if (ingredient.getName().equalsIgnoreCase(ingredientName)) {
        ingredientExists = true;
        System.out.println(ingredient.getName());
      }
      index++;
    }
    if (!ingredientExists) {
      System.out.println("The ingredient you tried to search for does not exist!");
    }
  }

  /**
   * Lists all available ingredients in the storage.
   */
  public void listIngredients() {
    if (storage.isEmpty()) {
      System.out.println("The storage is empty!");
    } else {
      System.out.println("List of all ingredients:");
      for (Ingredient ingredient : storage) {
        System.out.println(ingredient.getName() + ingredient.getDescription());
      }
    }
  }

  /**
   * Lists all available ingredients in the storage in alphabetical order.
   */
  public void listIngredientsAlphabetically() {
    if (storage.isEmpty()) {
      System.out.println("The storage is empty!");
    } else {
      storage.sort(Comparator.comparing(Ingredient::getName));
      System.out.println("List of all ingredients in alphabetical order:");
      for (Ingredient ingredient : storage) {
        System.out.println(ingredient.getName());
      }
    }
  }

  /**
   * Lists all the expired ingredients in the storage.
   */
  public void listExpiredIngredients() {
    if (storage.isEmpty()) {
      System.out.println("The storage is empty!");
    } else {
      boolean expired = false;
      for (Ingredient ingredient : storage) {
        if (ingredient.getExpirationDate().isBefore(LocalDate.now())) {
          System.out.println(ingredient.getName() + ingredient.getDescription());
          expired = true;
        }
      }
      if (!expired) {
        System.out.println("There are no expired ingredients in the storage!");
      }
    }
  }

  /**
   * Lists all the ingredients of a specific expiration date.
   *
   * @param expirationDate the expiration date of the ingredient.
   */
  public void listIngredientsByDate(LocalDate expirationDate) {
    boolean found = false;
    int index = 0;
    while (index < storage.size()) {
      Ingredient ingredient = storage.get(index);
      if (ingredient.getExpirationDate().isEqual(expirationDate)) {
        System.out.println(ingredient.getName());
        found = true;
      }
      index++;
    }
    if (!found) {
      System.out.println("Could not find any ingredients with the given expiration date!");
    }
  }

  /**
   * Prints the value of all ingredients.
   */
  public void printValueOfAllIngredients() {
    double totalValue = 0;
    if (storage.isEmpty()) {
      System.out.println("There storage is empty!");
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
      System.out.println("The storage is empty!");
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