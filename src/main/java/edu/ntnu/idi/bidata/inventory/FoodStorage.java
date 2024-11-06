package edu.ntnu.idi.bidata.inventory;

import edu.ntnu.idi.bidata.items.Ingredient;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Storage that stores ingredient objects.
 *
 * @author Johannes Nupen Theigen
 * @version 0.1.7
 * @since 11.06.2024
 */
public class FoodStorage {

  private final HashMap<String, Ingredient> storage;

  /**
   * Creates a new instance of FoodStorage, initializing an empty storage
   * for holding ingredients.
   */
  public FoodStorage() {
    storage = new HashMap<>();
  }

  /**
   * Creates and adds an ingredient to the food storage. If the ingredient already exists,
   * the quantity of the existing ingredient is incremented by the quantity of the
   * ingredient being added.

   * @param name the name of the ingredient, which acts as the unique identifier for the ingredient.
   * @param description the description of the ingredient (e.g. vegetable, meat, etc.)
   * @param quantity the quantity of the ingredient
   * @param unit the unt of the ingredient (e.g. kg)
   * @param price the price of the ingredient (e.g. USD, NOK, EUR)
   * @param expirationDate the expiration date of the ingredient,
   *                       formatted as yyyy-MM-dd (e.g., 2025-12-31).
   * @return true if the ingredient already exists and its quantity is successfully
   *                       incremented, and false if the ingredient
   *                       did not previously exist and was added to the storage.
   */
  public boolean addIngredient(String name, String description, double quantity,
                               String unit, double price, LocalDate expirationDate) {

    Ingredient newIngredient = new Ingredient(name, description, quantity,
        unit, price, expirationDate);
    if (storage.containsKey(newIngredient.getName())) {
      Ingredient existingIngredient = storage.get(newIngredient.getName());
      existingIngredient.setQuantity(existingIngredient.getQuantity()
          + newIngredient.getQuantity());
      return true;
    } else {
      storage.put(newIngredient.getName(), newIngredient);
      return false;
    }
  }

  /**
   * Decreases the quantity of an ingredient from the food storage.
   * If the quantity gets to 0, the ingredient will be removed from the storage.
   *
   * @param ingredientName the name of the ingredient to be reduced.
   * @return false if the ingredient does not exist, and true if the ingredient exists
   *         and is reduced or removed entirely.
   */

  public boolean reduceIngredient(String ingredientName) {
    Ingredient existingIngredient = storage.get(ingredientName);
    if (existingIngredient == null) {
      return false;
    }
    if (existingIngredient.getQuantity() > 1) {
      existingIngredient.setQuantity(existingIngredient.getQuantity() - 1);
    } else {
      storage.remove(ingredientName);
    }
    return true;
  }


  /**
   * Returns an ingredient in the storage when provided a String to search for.
   *
   * @return an ingredient in the storage
   */
  public Ingredient getIngredient(String ingredientName) {
    return storage.get(ingredientName);
  }

  /**
   * Returns a list of all ingredients in the storage.
   *
   * @return a list of all ingredients present in the storage
   */

  public Iterator<Ingredient> getListOfIngredients() {
    return storage.values().stream().toList().iterator();
  }

  /**
   * Returns a list of available ingredients in the storage in alphabetical order.
   *
   * @return a list of all ingredients present in the storage in alphabetical order.
   */
  public Iterator<Ingredient> getListOfIngredientsAlphabetically() {
    return storage.values().stream().sorted(Comparator.comparing(Ingredient::getName)).iterator();
  }

  /**
   * Returns a list of all the expired ingredients in the storage.
   *
   * @return a list of all expired ingredients present in the storage
   */
  public Iterator<Ingredient> getListOfExpiredIngredients() {
    return storage.values().stream().filter(ingredient ->
        ingredient.getExpirationDate().isBefore(LocalDate.now())).iterator();
  }

  /**
   * Returns a list of all the ingredients of a specific expiration date.
   *
   * @param expirationDate expirationDate the expiration date of the ingredient.
   * @return a list of ingredients of a specific expiration date.
   */

  public Iterator<Ingredient> getListOfIngredientsByExpirationDate(LocalDate expirationDate) {
    return storage.values().stream().filter(ingredient ->
        ingredient.getExpirationDate().isEqual(expirationDate)).iterator();
  }

  /**
   * Returns the value of all ingredients.
   *
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
   *
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