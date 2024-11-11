package edu.ntnu.idi.bidata.inventory;

import edu.ntnu.idi.bidata.items.Ingredient;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;

/**
 * The FoodStorage class is responsible for storing Ingredient objects. It uses a HashMap to store
 * ingredient objects, where the key is the name of the Ingredient
 * and the value is the Ingredient object.
 *
 * <p>The FoodStorage class provides the following methods:</p>
 * <ul>
 *   <li><b>addIngredient</b>: Adds an ingredient to the food storage.</li>
 *   <li><b>reduceIngredient</b>: Reduces the quantity of an ingredient or removes it entirely.</li>
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
 * @version 0.1.9
 * @since 11.11.2024
 */
public class FoodStorage {

  private final HashMap<String, Ingredient> storage;

  /**
   * Creates a new instance of FoodStorage, initializing an empty storage
   * for holding ingredient objects.
   *
   * <p><b>Example of usage: </b> <code> foodStorage = new FoodStorage();</code></p>
   */
  public FoodStorage() {
    storage = new HashMap<>();
  }

  /**
   * Creates and adds an ingredient to the food storage. If the ingredient already exists,
   * the quantity of the existing ingredient is incremented by the quantity of the
   * ingredient being added.
   *
   * @param name           the name of the ingredient,
   *                       which acts as the unique identifier for the ingredient.
   * @param description    the description of the ingredient (e.g. vegetable, meat, etc.)
   * @param quantity       the quantity of the ingredient
   * @param unit           the unt of the ingredient (e.g. kg)
   * @param price          the price of the ingredient (e.g. USD, NOK, EUR)
   * @param expirationDate the expiration date of the ingredient,
   *                       formatted as yyyy-MM-dd (e.g., 2025-12-31).
   * @return true if the ingredient already exists and its quantity is successfully
   *     incremented, and false if the ingredient
   *     did not previously exist and was added to the storage.
   *
     <p><b>Example of usage:</b></p>
     <pre><code>
     foodStorage.addIngredient("Banana", "Fruit", 10, "kg", 15, "2024-07-10");
     </code></pre>
   *
   */
  public boolean addIngredient(String name, String description, double quantity,
                               String unit, double price, LocalDate expirationDate) {

    boolean ingredientExists = false;

    Ingredient newIngredient = new Ingredient(name, description, quantity,
        unit, price, expirationDate);
    if (storage.containsKey(newIngredient.getName())) {
      Ingredient existingIngredient = storage.get(newIngredient.getName());
      existingIngredient.setQuantity(existingIngredient.getQuantity()
          + newIngredient.getQuantity());
      ingredientExists = true;
    } else {
      storage.put(newIngredient.getName(), newIngredient);
    }
    return ingredientExists;
  }

  /**
   * Decreases the quantity of an ingredient from the food storage.
   * If the quantity gets to 0, the ingredient will be removed from the storage.
   *
   * @param ingredientName the name of the ingredient to be reduced.
   * @return false if the ingredient does not exist, and true if the ingredient exists
   *     and is reduced or removed entirely.
   *
   *<p><b>Example of usage: </b>
   *<pre><code>foodStorage.reduceIngredient("Banana");</code></pre></p>
   */

  public boolean reduceIngredient(String ingredientName) {
    boolean ingredientFound = false;
    Ingredient existingIngredient = storage.get(ingredientName);

    if (existingIngredient != null) {
      ingredientFound = true;
      if (existingIngredient.getQuantity() > 1) {
        existingIngredient.setQuantity(existingIngredient.getQuantity() - 1);
      } else {
        storage.remove(ingredientName);
      }
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
  public String getIngredientName(String ingredientName) {
    String existingIngredient = null;
    if (storage.containsKey(ingredientName)) {
      existingIngredient = storage.get(ingredientName).getName();
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
    return storage.values().stream()
        .sorted(Comparator.comparing(Ingredient::getName))
        .map(Ingredient::getName)
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