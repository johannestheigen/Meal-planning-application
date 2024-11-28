package edu.ntnu.idi.bidata.register;

import edu.ntnu.idi.bidata.common.Unit;
import edu.ntnu.idi.bidata.common.UnitConverter;
import edu.ntnu.idi.bidata.items.Ingredient;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>The FoodStorage class is responsible for storing Ingredient objects.
 * It uses a HashMap to store
 * ingredient objects, where the key is the name of the Ingredient
 * and the value is the Ingredient object.</p>
 *
 *
 * @author Johannes Nupen Theigen
 * @version 0.2.9
 * @since 11.28.2024
 */
public class FoodStorage {

  private final Map<String, Ingredient> storage;

  /**
   * Creates a new instance of FoodStorage, initializing an empty storage
   * for holding ingredient objects.
   */
  public FoodStorage() {
    storage = new HashMap<>();
  }

  /**
   * <p>Adds a new ingredient to the food storage.</p>
   *
   * @param name           the name of the ingredient,
   *                       which acts as the unique identifier for the ingredient.
   * @param quantity       the quantity of the ingredient
   * @param unit           the unit of the ingredient (e.g. kg)
   * @param price          the price of the ingredient (e.g. USD, NOK, EUR)
   * @param expirationDate the expiration date of the ingredient,
   *                       formatted as yyyy-MM-dd (e.g., 2025-12-31).
   *
   * @return <code>true</code> if the ingredient is successfully added to the storage,
     and <code>false</code> if the ingredient already exists in the storage.
   */
  public boolean addIngredient(String name, double quantity,
                               Unit unit, double price, LocalDate expirationDate) {

    Ingredient newIngredient = new Ingredient(name,
        quantity, unit, price, expirationDate);
    String ingredientName = newIngredient.getName();

    if (storage.containsKey(ingredientName)) {
      handleExistingIngredient(ingredientName, newIngredient, expirationDate);
    } else {
      storage.put(ingredientName, newIngredient);
    }
    UnitConverter.adjustUnitIfNeeded(newIngredient);
    return true;
  }

  /**
   * <p>Handles the case where an ingredient already exists in the storage.
   * If the expiration date of the new ingredient is after the existing ingredient,
   * the existing ingredient is moved to a new key with the old expiration date.
   * If the expiration date of the new ingredient is before the existing ingredient,
   * the new ingredient is added to a new key with the new expiration date.
   * If the expiration date of the new ingredient is the same as the existing ingredient,
   * the quantity of the existing ingredient is updated.</p>

   * @param ingredientName the name of the ingredient
   * @param newIngredient the new ingredient to be added
   * @param expirationDate the expiration date of the new ingredient
   * @return <code>true</code> if the existing ingredient is successfully handled,
     and <code>false</code> if the existing ingredient is null.
   */
  private boolean handleExistingIngredient(String ingredientName, Ingredient newIngredient,
                                           LocalDate expirationDate) {
    Ingredient existingIngredient = storage.get(ingredientName);
    LocalDate existingExpirationDate = existingIngredient.getExpirationDate();

    if (expirationDate.isAfter(existingExpirationDate)) {
      String newKeyForExistingIngredient = ingredientName + "_" + existingExpirationDate;
      storage.put(newKeyForExistingIngredient, existingIngredient);
      storage.put(ingredientName, newIngredient);
    } else if (expirationDate.isBefore(existingExpirationDate)) {
      String newKeyForNewIngredient = ingredientName + "_" + expirationDate;
      storage.put(newKeyForNewIngredient, newIngredient);
    } else {
      mergeIngredient(existingIngredient, newIngredient);
    }
    return true;
  }

  /**
   * <p>Merges an existing ingredient with a new ingredient.
   * The method calculates the new price of the ingredient based on the weighted average price
   * of the existing and new ingredient.</p>
   * <p>The method also adjusts the unit of the existing ingredient if needed.</p>
   *
   * @param existingIngredient the existing ingredient
   * @param newIngredient the new ingredient
   * @return <code>true</code> if the existing ingredient is successfully
     merged with the new ingredient,
     and <code>false</code> if the existing ingredient is null.
   */
  private boolean mergeIngredient(Ingredient existingIngredient, Ingredient newIngredient) {
    if (existingIngredient == null) {
      return false;
    }
    if (!UnitConverter.isValidConversion(existingIngredient.getUnit(), newIngredient.getUnit())) {
      return false;
    }
    calculateNewPrice(existingIngredient, newIngredient);
    UnitConverter.adjustUnitIfNeeded(existingIngredient);
    return true;
  }

  /**
   * <p>Calculates the new price of an ingredient based on the weighted average price
   * of the existing and new ingredient.</p>
   * <p>The method calculates the price per base unit for both ingredients,
   * sums the total quantity in base units, and calculates the weighted price per base unit.</p>
   * <p>The total quantity and price are then updated for the existing ingredient.</p>

   * @param existingIngredient the existing ingredient
   * @param newIngredient the new ingredient
   */
  private static void calculateNewPrice(Ingredient existingIngredient, Ingredient newIngredient) {
    double existingQuantityInBase =
        existingIngredient.getUnit().toBase(existingIngredient.getQuantity());
    double newQuantityInBase = newIngredient.getUnit().toBase(newIngredient.getQuantity());

    double existingPricePerBaseUnit = existingIngredient.getPrice() / existingQuantityInBase;
    double newPricePerBaseUnit = newIngredient.getPrice() / newQuantityInBase;

    double totalQuantityInBase = existingQuantityInBase + newQuantityInBase;

    double totalPriceInBase = (existingPricePerBaseUnit * existingQuantityInBase)
        + (newPricePerBaseUnit * newQuantityInBase);

    double weightedPricePerBaseUnit = totalPriceInBase / totalQuantityInBase;

    existingIngredient.setQuantity(existingIngredient.getUnit().fromBase(totalQuantityInBase));
    existingIngredient.setPrice(weightedPricePerBaseUnit * totalQuantityInBase);
  }

  /**
   * <p>Removes an ingredient entirely from the food storage.</p>
   * <p>If the ingredient does not exist, the method will return <code>false</code></p>

   * @param ingredientName the name of the ingredient to be removed.
   * @return <code>true</code> if the ingredient exists and is successfully removed,
     and <code>false</code> if the ingredient does not exist.
   */
  public boolean removeIngredient(String ingredientName) {
    return storage.remove(ingredientName) != null;
  }

  /**
   * <p>Decreases the quantity of an ingredient from the food storage.
   * If the quantity gets to 0, the ingredient will be removed from the storage.</p>
   *
   * @param ingredientName the name of the ingredient to be reduced.
   * @param quantity the quantity to be reduced from the ingredient.
   * @return false if the ingredient does not exist, and true if the ingredient exists
   *     and is reduced or removed entirely.
   */

  public boolean reduceQuantity(String ingredientName, double quantity) {
    Ingredient existingIngredient = storage.get(ingredientName);

    if (existingIngredient == null) {
      return false;
    }
    double newQuantity = existingIngredient.getQuantity() - quantity;
    if (newQuantity <= 0) {
      storage.remove(ingredientName);
      return false;
    }
    existingIngredient.setQuantity(newQuantity);
    UnitConverter.adjustUnitIfNeeded(existingIngredient);
    return true;
  }


  /**
   * <p>Changes the price of an ingredient in the food storage.</p>
   * <p>If the ingredient does not exist, the method will return <code>false</code></p>

   * @param ingredientName the name of the ingredient, which is the key for the ingredient object
   * @param newPrice the new price to be set for the ingredient
   * @return <code>true</code> if the ingredient exists and the price is successfully changed,
     and <code>false</code> if the ingredient does not exist.
   */
  public boolean updatePrice(String ingredientName, double newPrice) {
    boolean ingredientFound = false;
    Ingredient existingIngredient = storage.get(ingredientName);
    if (existingIngredient != null) {
      ingredientFound = true;
      existingIngredient.setPrice(newPrice);
    }
    return ingredientFound;
  }

  /**
   * <p>Changes the unit of an ingredient in the food storage.</p>

   * @param ingredientName the name of the ingredient, which is the key for the ingredient object
   * @param newUnit the new unit to be set for the ingredient
   * @return <code>true</code> if the ingredient exists and the unit is successfully changed,
     and <code>false</code> if the ingredient does not exist.
   */
  public boolean updateUnit(String ingredientName, Unit newUnit) {
    boolean ingredientFound = false;
    Ingredient existingIngredient = storage.get(ingredientName);

    if (existingIngredient != null
        && UnitConverter.isValidConversion(existingIngredient.getUnit(), newUnit)) {
      ingredientFound = true;
      UnitConverter.convertAmountBasedOnUnit(existingIngredient);
    }
    return ingredientFound;
  }

  /**
   * <p>Retrieves an ingredient object from the food storage.</p>

   * @param ingredientName the name of the ingredient to retrieve
   * @return the ingredient object if it exists in the storage,
     and <code>null</code> if it does not exist.
   */
  public Ingredient getIngredient(String ingredientName) {
    return storage.get(ingredientName);
  }

  /**
   * <p>Checks if an ingredient exists in the food storage.</p>

   * @param ingredientName the name of the ingredient to check
   * @return <code>true</code> if the ingredient exists in the storage,
     and <code>false</code> if it does not exist.
   */
  public boolean isIngredientExisting(String ingredientName) {
    return storage.containsKey(ingredientName);
  }

  /**
   * <p>Returns an iterator that can be used to retrieve the names (keys)
   * of all ingredients in the storage.
   * The iterator provides each ingredient name in the order they are stored in the map.</p>
   *
   * @return an iterator over the set of ingredient names (keys) in the storage.
   */
  public Iterator<String> getListOfIngredients() {
    return storage.keySet().stream().iterator();
  }

  /**
   * <p>
   *   Returns an iterator that can be used to retrieve a list of all ingredient names (keys)
   *   in the storage, sorted alphabetically.
   * </p>

   * @return an iterator over the ingredient names (keys) in the storage, sorted alphabetically.
   */
  public Iterator<String> getListOfIngredientsAlphabetically() {
    return storage.keySet().stream()
        .sorted()
        .iterator();
  }

  /**
   * <p>Returns an iterator that can be used to
   * retrieve a list of all expired ingredient names (keys) in the storage.
   * The iterator filters the ingredients by
   * their expiration date and only returns those that have expired.</p>
   *
   * @return an iterator over the expired ingredient names (keys) in the storage.
   */
  public Iterator<String> getListOfExpiredIngredients() {
    return storage.values().stream()
        .filter(ingredient -> ingredient.getExpirationDate().isBefore(LocalDate.now()))
        .map(Ingredient::getName).iterator();
  }

  /**
   * <p>Returns an iterator that can be used
   * to retrieve a list of ingredient names (keys) in the storage
   * that have expired on or before a specific expiration date.</p>
   *
   * @param expirationDate the specific expiration date used to filter the ingredients.
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
   * <p>Retrieves the total value of all ingredients in the storage.</p>
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
   * <p>Retrieves the total value of a single ingredient in the storage.</p>
   * <p>The total value is calculated by multiplying the price of the ingredient
   * with the quantity of the ingredient.</p>

   * @param ingredientName the name of the ingredient
   * @return the total value of the ingredient
   */
  public double getValueOfSingleIngredient(String ingredientName) {
    Ingredient ingredient = storage.get(ingredientName);
    if (ingredient == null) {
      return 0;
    }
    return ingredient.getPrice() * ingredient.getQuantity();
  }

  /**
   * <p>Retrieves the total value of all expired ingredients in the storage.</p>
   * <p>The total value is calculated by summing the price times the quantity for each ingredient
   * that has expired (i.e., where its expiration date is before the current date).</p>
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