package edu.ntnu.idi.bidata.inventory;

import edu.ntnu.idi.bidata.items.Ingredient;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

/**
 * Storage that holds ingredients.
 *
 * @author Johannes Nupen Theigen
 * @version 0.1.5
 * @since 10.30.2024
 */
public class FoodStorage {

  private final HashMap<String, Ingredient> storage;

  /**
   * Creates a storage that holds ingredients.
   */
  public FoodStorage() {
    storage = new HashMap<>();
  }

  /**
   * Adds an ingredient to the food storage. If the ingredient already exists
   * the quantity of the existing ingredient incremented by 1.
   *
   * @param ingredient the name of the ingredient to be removed.
   */
  public void addIngredient(Ingredient ingredient) {
    if (storage.containsKey(ingredient.getName())) {
      Ingredient existingIngredient = storage.get(ingredient.getName());
      existingIngredient.setQuantity(existingIngredient.getQuantity() + ingredient.getQuantity());
    } else {
      storage.put(ingredient.getName(), ingredient);
    }
  }

  /**
   * Decreases the quantity of an ingredient from the food storage.
   * If the quantity gets to 0, the ingredient will be removed from the storage.
   *
   * @param ingredientName the name of the ingredient to be removed.
   */
  public void reduceIngredient(String ingredientName) {
    Ingredient existingIngredient = storage.get(ingredientName);
    if (existingIngredient != null) {
      if (existingIngredient.getQuantity() > 1) {
        existingIngredient.setQuantity(existingIngredient.getQuantity() - 1);
      } else {
        storage.remove(ingredientName);
      }
    }
  }

  /**
   * Returns an ingredient in the storage when provided a String to search for.

   * @return an ingredient in the storage
   */
  public Ingredient getIngredient(String ingredientName) {
    return storage.get(ingredientName);
  }

  /**
   * Returns a list of all ingredients in the storage.

   * @return a list of all ingredients present in the storage
   */
  public List<Ingredient> getListOfIngredients() {
    return new ArrayList<>(storage.values());
  }

  /**
   * Returns a list of available ingredients in the storage in alphabetical order.

   * @return a list of all ingredients present in the storage in alphabetical order.
   */
  public List<Ingredient> getListOfIngredientsAlphabetically() {
    List<Ingredient> ingredientsList = new ArrayList<>(storage.values());
    if (!storage.isEmpty()) {
      ingredientsList.sort(Comparator.comparing(Ingredient::getName));
    }
    return ingredientsList;
  }

  /**
   * Returns a list of all the expired ingredients in the storage.

   * @return a list of all expired ingredients present in the storage
   */
  public List<Ingredient> getListOfExpiredIngredients() {
    List<Ingredient> expiredIngredientsList = new ArrayList<>();

    for (Ingredient ingredient : storage.values()) {
      if (ingredient.getExpirationDate().isBefore(LocalDate.now())) {
        expiredIngredientsList.add(ingredient);
      }
    }
    return expiredIngredientsList;
  }

  /**
   * Returns a list of all the ingredients of a specific expiration date.

   * @param expirationDate expirationDate the expiration date of the ingredient.
   * @return a list of ingredients of a specific expiration date.
   */
  public List<Ingredient> getListOfIngredientsByExpirationDate (LocalDate expirationDate) {
    List<Ingredient> expiredIngredientsList = new ArrayList<>();

    for (Ingredient ingredient : storage.values()) {
      if (ingredient.getExpirationDate().isEqual(expirationDate)) {
        expiredIngredientsList.add(ingredient);
      }
    }
    return expiredIngredientsList;
  }

  /**
   * Returns the value of all ingredients.

   * @return the value of all the ingredients.
   */
  public double getValueOfAllIngredients() {
    double totalValue = 0;
    for (Ingredient ingredient : storage.values()) {
      totalValue += ingredient.getPrice() * ingredient.getQuantity();
    }
    return totalValue;
  }

  /**
   * Returns the value of all expired ingredients.

   * @return the value of all expired ingredients.
   */

  public double getValueOfExpiredIngredients() {
    double totalValue = 0;
    for (Ingredient ingredient : storage.values()) {
      if (ingredient.getExpirationDate().isBefore(LocalDate.now())) {
        totalValue += ingredient.getPrice() * ingredient.getQuantity();
      }
    }
    return totalValue;
  }
}