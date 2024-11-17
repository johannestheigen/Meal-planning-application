package edu.ntnu.idi.bidata.inventory;

import edu.ntnu.idi.bidata.items.Ingredient;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The FoodStorage class is responsible for storing Ingredient objects. It uses a HashMap to store
 * ingredient objects, where the key is the name of the Ingredient
 * and the value is the Ingredient object.
 *
 * <p>The FoodStorage class provides the following methods:</p>
 * <ul>
 *   <li><b>addIngredient</b>: Adds an ingredient to the food storage.</li>
 *   <li><b>reduceIngredient</b>: Reduces the quantity of an ingredient or removes it entirely.</li>
 *   <li><b>changeDescription</b>: Changes the description of an ingredient.</li>
 *   <li><b>changePrice</b>: Changes the price of an ingredient.</li>
 *   <li><b>getIngredient</b>: Retrieves an ingredient object from the food storage.</li>
 *   <li><b>getIngredientName</b>: Retrieves the name of an ingredient,
 *   which is also the key to the ingredient object.</li>
 *   <li><b>getListOfIngredients</b>: Returns an iterator object that
 *   can be used to retrieve a list of ingredients.</li>
 *   <li><b>getListOfIngredientsAlphabetically</b>:
 *   Returns an iterator object to retrieve a list of ingredients in alphabetical order.</li>
 *   <li><b>getListOfExpiredIngredients</b>:
 *   Returns an iterator object to retrieve a list of expired ingredients.</li>
 *   <li><b>getListOfIngredientsByExpirationDate</b>:
 *   Returns an iterator object to retrieve a list of ingredients
 *   by a specific expiration date.</li>
 *   <li><b>getValueOfAllIngredients</b>: Retrieves the total value of all ingredients.</li>
 *   <li><b>getValueOfExpiredIngredients</b>: Retrieves the total value of expired ingredients.</li>
 * </ul>
 *
 * @author Johannes Nupen Theigen
 * @version 0.2.3
 * @since 11.17.2024
 */
public class FoodStorage {

  private final Map<String, Ingredient> storage;

  /**
   * Creates a new instance of FoodStorage, initializing an empty storage
   * for holding ingredient objects.
   *
   * <p><b>Example of usage: </b> <pre><code> foodStorage = new FoodStorage();</code></pre></p>
   */
  public FoodStorage() {
    storage = new HashMap<>();
  }

  /**
   * <p>Creates and adds an ingredient to the food storage. If the ingredient already exists,
   * the quantity of the existing ingredient is incremented by the quantity of the
   * ingredient being added. If the expiration date of the new ingredient is later
   * than the existing ingredient, the existing ingredient is renamed with the old
   * expiration date and the new ingredient is added. If the expiration date of the
   * new ingredient is before the expiration date of the existing ingredient the
   * the new ingredient is renamed with the older expiration date</p>
   *
   * @param name           the name of the ingredient,
   *                       which acts as the unique identifier for the ingredient.
   * @param description    the description of the ingredient (e.g. vegetable, meat, etc.)
   * @param quantity       the quantity of the ingredient
   * @param unit           the unt of the ingredient (e.g. kg)
   * @param price          the price of the ingredient (e.g. USD, NOK, EUR)
   * @param expirationDate the expiration date of the ingredient,
   *                       formatted as yyyy-MM-dd (e.g., 2025-12-31).
   * @return <code>true</code> if the ingredient already exists
     and its quantity is successfully incremented,
   *         or if a new ingredient was added with
   *         a later expiration date than an existing one (replacing the old one).
   *          If the new ingredient has an earlier expiration date, it is added with a new name
   *         based on the older expiration date.
   *         <code>false</code> if the ingredient did not previously exist
   *         and was successfully added to the storage.
   *
     <p><b>Example of usage:</b></p>
     <pre><code>
     foodStorage.addIngredient("Banana", "Fruit", 10, "kg", 15, "2024-07-10");
     </code></pre>
   *
   */
  public boolean addIngredient(String name, String description, double quantity,
                               String unit, double price, LocalDate expirationDate) {

    Ingredient newIngredient = new Ingredient(name, description,
        quantity, unit, price, expirationDate);
    String ingredientName = newIngredient.getName();

    if (storage.containsKey(ingredientName)) {
      Ingredient existingIngredient = storage.get(ingredientName);
      LocalDate existingExpirationDate = existingIngredient.getExpirationDate();

      if (expirationDate.isAfter(existingExpirationDate)) {
        String newName = ingredientName + "_" + existingExpirationDate;
        storage.put(newName, existingIngredient);
        storage.put(ingredientName, newIngredient);
      } else if (expirationDate.isBefore(existingExpirationDate)) {
        String newKey = ingredientName + "_" + expirationDate;
        storage.put(newKey, newIngredient);
      } else {
        double updatedQuantity = existingIngredient.getQuantity() + quantity;
        existingIngredient.setQuantity(updatedQuantity);
      }
      return true;
    }
    storage.put(ingredientName, newIngredient);
    return false;
  }

  /**
   * Decreases the quantity of an ingredient from the food storage.
   * If the quantity gets to 0, the ingredient will be removed from the storage.
   *
   * @param ingredientName the name of the ingredient to be reduced.
   * @param quantity the quantity to be reduced from the ingredient.
   * @return false if the ingredient does not exist, and true if the ingredient exists
   *     and is reduced or removed entirely.
   *
   *<p><b>Example of usage: </b>
   *<pre><code>foodStorage.reduceIngredient("Banana");</code></pre></p>
   */

  public boolean reduceIngredient(String ingredientName, double quantity) {
    boolean ingredientFound = false;
    boolean wasRemoved = false;

    Ingredient existingIngredient = storage.get(ingredientName);

    if (existingIngredient != null) {
      ingredientFound = true;
      double newQuantity = existingIngredient.getQuantity() - quantity;

      if (newQuantity <= 0) {
        storage.remove(ingredientName);
        wasRemoved = true;
      } else {
        existingIngredient.setQuantity(newQuantity);
      }
    }
    return ingredientFound && !wasRemoved;
  }

  /**
   * <p>Changes the description of an ingredient in the food storage.</p>
   * <p>If the ingredient does not exist, the method will return <code>false</code></p>

   * @param ingredientName the name of the ingredient, which is the key for the ingredient object
   * @param newDescription the new description to be set for the ingredient
   * @return <code>true</code> if the ingredient exists and the description is successfully changed,
     and <code>false</code> if the ingredient does not exist.
   *
     <p><b>Example of usage: </b>
     <pre><code>foodStorage.changeDescription("Apple","Fruit");</code></pre></p>
   */
  public boolean updateDescription(String ingredientName, String newDescription) {
    boolean ingredientFound = false;
    Ingredient existingIngredient = storage.get(ingredientName);
    if (existingIngredient != null) {
      ingredientFound = true;
      existingIngredient.setDescription(newDescription);
    }
    return ingredientFound;
  }

  /**
   * <p>Changes the price of an ingredient in the food storage.</p>
   * <p>If the ingredient does not exist, the method will return <code>false</code></p>

   * @param ingredientName the name of the ingredient, which is the key for the ingredient object
   * @param newPrice the new price to be set for the ingredient
   * @return <code>true</code> if the ingredient exists and the price is successfully changed,
     and <code>false</code> if the ingredient does not exist.
   *
     <p><b>Example of usage: </b>
     <pre><code>foodStorage.changeDescription("Apple",10);</code></pre></p>
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
   *Retrieves an ingredient object from the storage when provided with the ingredient's name.
   *If the ingredient does not exist in the storage, the method will return <code>null</code>.
   *
   * @param ingredientName the name of the ingredient to search for in the storage
   * @return the ingredient object associated with the provided name,
     or <code>null</code> if not found
   *
   *<p><b>Example of usage: </b> <pre><code>foodStorage.getIngredient("Banana");</code></pre></p>
   */
  public Ingredient getIngredient(String ingredientName) {
    return storage.get(ingredientName);
  }

  /**
   * Retrieves the name of the ingredient object when provided the name (which is also the key)
   * of the ingredient stored in the food storage.
   *
   * @param ingredientName the name of the ingredient (the key for the ingredient object)
   * @return the name of the ingredient if found, otherwise <code>null</code> or an appropriate
   *     message if the ingredient does not exist.
   *
    <p><b>Example of usage: </b>
  <pre><code>foodStorage.getIngredientName("Banana");</code></pre></p>
   */

  public String getIngredientInfo(String ingredientName) {
    String existingIngredient = null;
    if (storage.containsKey(ingredientName)) {
      existingIngredient = storage.get(ingredientName).getName() + " "
          + storage.get(ingredientName).getDescription() + " "
          + storage.get(ingredientName).getQuantity() + " "
          + storage.get(ingredientName).getUnit() + " "
          + storage.get(ingredientName).getPrice() + " per unit "
          + storage.get(ingredientName).getExpirationDate();
    }
    return existingIngredient;
  }

  /**
   * Returns an iterator that can be used to retrieve the names (keys)
   * of all ingredients in the storage.
   * The iterator provides each ingredient name in the order they are stored in the map.
   *
   * @return an iterator over the set of ingredient names (keys) in the storage.
   *
     <p><b>Example of usage:</b></p>
     <pre><code>
     Iterator&lt;String&gt; ingredientsIterator = foodStorage.getListOfIngredientsAlphabetically();
     while (ingredientsIterator.hasNext()) {
   *     System.out.println(ingredientsIterator.next());
     }
     </code></pre>
   */
  public Iterator<String> getListOfIngredients() {
    return storage.keySet().stream().iterator();
  }

  /**
   * Returns an iterator that can be used to retrieve
   * a list of all ingredient names (keys) in the storage.
   * The iterator first retrieves the ingredient names in the order they are stored,
   * then sorts the names in alphabetical order using a comparator before returning the list.
   *
   * @return an iterator over the ingredient names (keys) in the storage, sorted alphabetically.
   *
     <p><b>Example of usage:</b></p>
     <pre><code>
     Iterator&lt;String&gt; ingredientsIterator = foodStorage.getListOfIngredientsAlphabetically();
     while (ingredientsIterator.hasNext()) {
         System.out.println(ingredientsIterator.next());
     }
     </code></pre>
   */

  public Iterator<String> getListOfIngredientsAlphabetically() {
    return storage.keySet().stream()
        .sorted()
        .iterator();
  }

  /**
   * Returns an iterator that can be used to
   * retrieve a list of all expired ingredient names (keys) in the storage.
   * The iterator filters the ingredients by
   * their expiration date and only returns those that have expired.
   *
   * @return an iterator over the expired ingredient names (keys) in the storage.
   *
     <p><b>Example of usage:</b></p>
     <pre><code>
     Iterator&lt;String&gt; ingredientsIterator = foodStorage.getListOfExpiredIngredients();
     while (ingredientsIterator.hasNext()) {
         System.out.println(ingredientsIterator.next());
     }
     </code></pre>
   */

  public Iterator<String> getListOfExpiredIngredients() {
    return storage.values().stream()
        .filter(ingredient -> ingredient.getExpirationDate().isBefore(LocalDate.now()))
        .map(Ingredient::getName).iterator();
  }

  /**
   * Returns an iterator that can be used
   * to retrieve a list of ingredient names (keys) in the storage
   * that have expired on or before a specific expiration date.
   * The iterator filters the ingredients
   * by the provided expiration date and only returns those that
   * have expired by that date.
   *
   * @param expirationDate the specific expiration date used to filter the ingredients.
   * @return an iterator over the ingredient names (keys)
   *     in the storage that have expired on or before the given date.
   *
     <p><b>Example of usage:</b></p>
     <pre><code>
     Iterator&lt;String&gt; ingredientsIterator =
         foodStorage.getListOfIngredientsByExpirationDate(LocalDate.of(2024, 7, 10));
     while (ingredientsIterator.hasNext()) {
         System.out.println(ingredientsIterator.next());
     }
     </code></pre>
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
   *
   *<p><b>Example of usage: </b>
   *<pre><code>System.out.println(foodStorage.getValueOfAllIngredients());</code></pre> </p>
   */
  public double getValueOfAllIngredients() {
    double totalValue = 0;
    for (Ingredient ingredient : storage.values()) {
      totalValue += ingredient.getPrice() * ingredient.getQuantity();
    }
    return totalValue;
  }

  /**
   * <p>Retrieves the total value of all expired ingredients in the storage.</p>
   * <p>The total value is calculated by summing the price times the quantity for each ingredient
   * that has expired (i.e., where its expiration date is before the current date).</p>
   *
   * @return the total value of all expired ingredients.
   *
   *<p><b>Example of usage:</b>
   *<pre><code>System.out.println(foodStorage.getValueOfExpiredIngredients());</code></pre></p>
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