package edu.ntnu.idi.bidata.register;

import edu.ntnu.idi.bidata.items.Ingredient;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>The FoodStorage class is responsible for storing Ingredient objects.
 * It uses a HashMap to store ingredient objects, where the key is the name of the Ingredient
 * and the value is the Ingredient object.</p>
 *
 * @author Johannes Nupen Theigen
 * @version 0.3.6
 * @since 12.03.2024
 */
public class FoodStorage {

  private final Map<String, Ingredient> storage;

  /**
   * <p>Initializes a new instance of the FoodStorage class
   * with an empty collection of ingredients.</p>
   */
  public FoodStorage() {
    storage = new HashMap<>();
  }

  /**
   * <p>Adds a new ingredient to the food storage.
   * The ingredient is stored in a HashMap where the key is the name of the ingredient
   * and the value is the Ingredient object.</p>
   *
   * @param name           the name of the ingredient (e.g. flour, sugar, salt)
   *
   * @param quantity       the quantity of the ingredient (e.g. 1)
   * @param unit           the unit of the ingredient (e.g. kg)
   * @param price          the price of the ingredient (e.g. NOK)
   * @param expirationDate the expiration date of the ingredient,
   *                       formatted as yyyy-MM-dd (e.g., 2025-12-31).
   *
   * @return true if the ingredient is successfully added to the storage,
  and false if the ingredient already exists in the storage.
   */
  public boolean addIngredient(String name, double quantity,
                               String unit, double price, LocalDate expirationDate) {

    Ingredient newIngredient = new Ingredient(name,
        quantity, unit, price, expirationDate);
    String ingredientName = newIngredient.getName();

    if (storage.containsKey(ingredientName)) {
      return handleExistingIngredient(ingredientName, newIngredient,
          quantity, expirationDate);
    }
    storage.put(ingredientName, newIngredient);
    return false;
  }

  /*
   * Handles the case where an ingredient already exists in the storage.
   * If the expiration date of the new ingredient is after the existing ingredient,
   * the existing ingredient gets a new name (key) with the old expiration date.
   * If the expiration date of the new ingredient is before the existing ingredient,
   * the new ingredient gets a name (key) with its expiration date.
   * If the expiration date of the new ingredient is the same as the existing ingredient,
   * the quantity of the existing ingredient is updated.
   */
  private boolean handleExistingIngredient(String ingredientName, Ingredient newIngredient,
                                           double quantity, LocalDate expirationDate) {
    Ingredient existingIngredient = storage.get(ingredientName);
    LocalDate existingExpirationDate = existingIngredient.getExpirationDate();

    if (expirationDate.isAfter(existingExpirationDate)) {
      String newKeyForExistingIngredient = ingredientName + "-" + existingExpirationDate;
      storage.put(newKeyForExistingIngredient, existingIngredient);
      storage.put(ingredientName, newIngredient);
    } else if (expirationDate.isBefore(existingExpirationDate)) {
      String newKeyForNewIngredient = ingredientName + "-" + expirationDate;
      storage.put(newKeyForNewIngredient, newIngredient);
    } else {
      mergeIngredient(quantity, existingIngredient, newIngredient);
    }
    return true;
  }

  /*
   * Merges two ingredients together by updating the quantity and
   * price of the existing ingredient.
   * The new quantity is the sum of the existing quantity and the new quantity.
   * The new price is the average of the existing price and the new price.
   * If the units of the ingredients do not match, an IllegalArgumentException is thrown.
   */
  private boolean mergeIngredient(double quantity, Ingredient existingIngredient,
                                  Ingredient newIngredient) {
    if (existingIngredient == null || newIngredient == null) {
      return false;
    }
    if (!existingIngredient.getUnit().equalsIgnoreCase(newIngredient.getUnit())) {
      throw new IllegalArgumentException("Unit mismatch: existing ingredient uses '"
          + existingIngredient.getUnit() + "', but the new ingredient uses '"
          + newIngredient.getUnit() + "'.");
    }
    double updatedQuantity = existingIngredient.getQuantity() + quantity;
    double updatedPrice = (existingIngredient.getPrice() + newIngredient.getPrice()) / 2.0;
    existingIngredient.setQuantity(updatedQuantity);
    existingIngredient.setPrice(updatedPrice);
    return true;
  }

  /**
   * <p>Removes an ingredient from the food storage.
   * The ingredient is removed by its name (key).</p>

   * @return true if the ingredient exists and is successfully removed,
  and false if the ingredient does not exist.
   */
  public boolean removeIngredient(String ingredientName) {
    return storage.remove(ingredientName) != null;
  }

  /**
   * <p>Decreases the quantity of an ingredient from the food storage.
   * If the quantity gets to 0, the ingredient will be removed entirely from the storage.
   * The ingredient is decreased by its name (key).</p>
   *
   * @param ingredientName the name of the ingredient to be reduced.
   * @param quantity the quantity to be reduced from the ingredient.
   * @return false if the ingredient does not exist, and true if the ingredient exists
   *     and is reduced or removed entirely.
   */
  public boolean reduceQuantity(String ingredientName, double quantity) {
    boolean wasRemoved = false;

    Ingredient existingIngredient = storage.get(ingredientName);

    if (existingIngredient != null) {
      double newQuantity = existingIngredient.getQuantity() - quantity;

      if (newQuantity <= 0) {
        storage.remove(ingredientName);
        wasRemoved = true;
      } else {
        existingIngredient.setQuantity(newQuantity);
      }
    }
    return !wasRemoved;
  }

  /**
   * <p>Updates the price of an ingredient in the food storage.
   * The ingredient is updated by its name (key).</p>

   *  @param ingredientName the name of the ingredient
   * @param newPrice the new price to be set for the ingredient
   * @return true if the ingredient exists and the price is successfully changed,
  and false if the ingredient does not exist.
   */
  public boolean updatePrice(String ingredientName, double newPrice) {
    Ingredient existingIngredient = storage.get(ingredientName);
    if (existingIngredient != null) {
      existingIngredient.setPrice(newPrice);
      return true;
    }
    return false;
  }

  /**
   * <p>Changes the unit of an ingredient in the food storage.
   * The ingredient is changed by its name (key).</p>

   * @param ingredientName the name of the ingredient, which is the key for the ingredient object
   * @param newUnit the new unit to be set for the ingredient
   * @return true if the ingredient exists and the unit is successfully changed,
  and false if the ingredient does not exist.
   */
  public boolean updateUnit(String ingredientName, String newUnit) {
    Ingredient existingIngredient = storage.get(ingredientName);
    if (existingIngredient != null) {
      existingIngredient.setUnit(newUnit);
      return true;
    }
    return false;
  }

  /**
   * <p>Retrieves an ingredient from the food storage.
   * The ingredient is retrieved by its name (key).</p>
   *
   *  @param ingredientName the name of the ingredient to retrieve
   * @return the ingredient object if it exists in the storage,
  and null if it does not exist.
   */
  public Ingredient getIngredient(String ingredientName) {
    return storage.get(ingredientName);
  }

  /**
   * <p>Checks if an ingredient exists in the food storage.
   * The ingredient is checked by its name (key).</p>

   * @param ingredientName the name of the ingredient to check
   * @return true if the ingredient exists in the storage,
  and false if it does not exist.
   */
  public boolean isIngredientExisting(String ingredientName) {
    return storage.containsKey(ingredientName);
  }

  /**
   * <p>Compares the expiration date of a new ingredient with the existing expiration date.</p>
   *
   * @param existingExpirationDate the existing expiration date
   * @param newExpirationDate      the new expiration date
   * @return true if the new expiration date is newer than the existing expiration date,
  false if the new expiration date is older than the existing expiration date.
   */
  public boolean isNewExpirationDateNewer(LocalDate existingExpirationDate,
                                          LocalDate newExpirationDate) {
    return newExpirationDate.isAfter(existingExpirationDate);
  }

  /**
   * <p>Checks if the expiration date of a new ingredient
   * is the same as the existing expiration date.</p>
   *
   * @param existingExpirationDate the existing expiration date
   * @param newExpirationDate      the new expiration date
   * @return true if the new expiration date is the same as the existing expiration date,
   *         false otherwise.
   */
  public boolean isExpirationDateEqual(LocalDate existingExpirationDate,
                                       LocalDate newExpirationDate) {
    return newExpirationDate.isEqual(existingExpirationDate);
  }

  /**
   * <p>Checks if the quantity of an ingredient has been updated.</p>

   * @param ingredientName the name of the ingredient
   * @param quantity the new quantity of the ingredient
   * @return true if the quantity was updated, false otherwise.
   */
  public boolean isIngredientQuantityUpdated(String ingredientName, double quantity) {
    Ingredient existingIngredient = storage.get(ingredientName);

    return existingIngredient != null && existingIngredient.getQuantity() != quantity;
  }


  /**
   * <p>Returns an iterator that can be used to retrieve a list
   * of all ingredient names (keys) in the storage.</p>
   *
   *  @return an iterator over the ingredient names (keys) in the storage.
   */
  public Iterator<String> getListOfIngredients() {
    return storage.keySet().stream().iterator();
  }

  /**
   * <p>Returns an iterator that can be used to retrieve a list of all ingredient names (keys)
   * in the storage, sorted alphabetically.</p>
   *
   *  @return an iterator over the ingredient names (keys) in the storage, sorted alphabetically.
   */
  public Iterator<String> getListOfIngredientsAlphabetically() {
    return storage.keySet().stream()
        .sorted()
        .iterator();
  }

  /**
   * <p>Returns an iterator that can be used to retrieve a list of ingredient names (keys)
   * in the storage that have expired on or before the current date.</p>

   * @return an iterator over the ingredient names (keys) in the storage that have expired.
   */
  public Iterator<String> getListOfExpiredIngredients() {
    return storage.values().stream()
        .filter(ingredient -> ingredient.getExpirationDate().isBefore(LocalDate.now()))
        .map(Ingredient::getName).iterator();
  }

  /**
   * <p>Returns an iterator that can be used
   * to retrieve a list of ingredient names (keys) in the storage
   * that will or has expired on a specific expiration date.</p>
   *
   * @param expirationDate the expiration date to check for
   * @return an iterator over the ingredient names (keys)
   *     in the storage that have expired on or before the given date.
   */
  public Iterator<String> getListOfIngredientsByExpirationDate(LocalDate expirationDate) {
    return storage.values().stream()
        .filter(ingredient -> ingredient.getExpirationDate().isEqual(expirationDate))
        .map(Ingredient::getName)
        .iterator();
  }

  /**
   * <p>Calculates and retrieves the total value of all ingredients in the storage.</p>
   * <p>The total value is calculated by
   * summing the price times the quantity for each ingredient in the storage.</p>
   *
   * @return the total value of all the ingredients in the storage.
   */
  public double getValueOfAllIngredients() {
    double totalValue = 0;
    for (Ingredient ingredient : storage.values()) {
      totalValue += ingredient.getPrice() * ingredient.getQuantity();
    }
    return totalValue;
  }

  /**
   * <p>Calculates and retrieves the total value of all expired ingredients in the storage.</p>
   * <p>The total value is calculated by summing the price times the quantity for each ingredient
   * that has expired.</p>
   *
   * @return the total value of all expired ingredients.
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