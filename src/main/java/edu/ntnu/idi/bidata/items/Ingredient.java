package edu.ntnu.idi.bidata.items;

import java.time.LocalDate;

/**
 * <p>The Ingredient class represents an ingredient entity that can be stored in a FoodStorage.</p>
 *
 * <p>Each Ingredient object holds key information about the ingredient, including its
 * <b>name</b>, <b>description</b>, <b>quantity</b>, <b>unit</b>, <b>price</b>, and
 * <b>expiration date</b>.</p>
 *
 * <p>This class provides methods to:</p>
 * <ul>
 *   <li>Retrieve and update ingredient details</li>
 *   <li>Check expiration status</li>
 *   <li>Calculate the total value based on quantity and price</li>
 * </ul>
 *
 * <p>Instances of Ingredient can be managed within a FoodStorage object to maintain
 * an organized collection of ingredients.</p>
 *
 * @author Johannes Nupen Theigen
 * @version 0.1.3
 * @since 11.19.2024
 */

public class Ingredient {
  private String name;
  private String description;
  private double quantity;
  private String unit;
  private double price;
  private LocalDate expirationDate;

  /**
   * Creates a new instance of Ingredient that can be
   * stored in a FoodStorage.

   * @param name the name an ingredient
   * @param description the description of an ingredient. (type, taste, etc.)
   * @param quantity the quantity of an ingredient
   * @param unit the unit of an ingredient (gram, litre, etc.)
   * @param price the price of an ingredient. (price in Norwegian kr)
   * @param expirationDate the expiration date of an ingredient.
   *
     <p><b>Example of usage:</b></p>
     <pre><code>
     Ingredient newIngredient = new Ingredient("Apple", "Fruit", 1, "kg", 10,
  LocalDate.Of(2024,07,01));
     </code></pre>
   */
  public Ingredient(String name, String description,
                    double quantity, String unit, double price, LocalDate expirationDate) {
    setName(name);
    setDescription(description);
    setQuantity(quantity);
    setUnit(unit);
    setPrice(price);
    setExpirationDate(expirationDate);
  }

  /**
   * <p>
   * Creates a new instance of Ingredient that is used when adding
   * a new ingredient to a recipe which only requires the name, quantity
   * and unit.
   * </p>

   * @param quantity the quantity of an ingredient
   * @param unit the unit of an ingredient (gram, liter, etc.)
   *
     <p>
     <b>Example of usage:</b>
     <pre><code>Ingredient newIngredient = new Ingredient(1, "kg");</code></pre>
     </p>
   */
  public Ingredient(String name, Double quantity, String unit) {
    setName(name);
    setQuantity(quantity);
    setUnit(unit);
  }

  /**
   * Retrieves the name of an ingredient.
   *
   * @return the name of this ingredient
   *
     <p><b>Example of usage:</b></p>
     <pre><code>
     String ingredientName = newIngredient.getName();
     </code></pre>
   */
  public String getName() {
    return name;
  }

  /**
   * Sets the name of the ingredient.
   * If the name is null or empty, an IllegalArgumentException is thrown.
   *
   * @param name the name of the ingredient. The name cannot be null or empty.
   * @throws IllegalArgumentException if the name is null or empty.
   *
     <p><b>Example of usage:</b></p>
     <pre><code>
     newIngredient.setName("Banana");
     </code></pre>
   */
  public void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The ingredient name cannot be null or empty");
    }
    this.name = name;
  }

  /**
   * Retrieves the description of an ingredient.

   * @return The description of an ingredient.
   *
   *
   *      <p><b>Example of usage:</b></p>
   *      <pre><code>
   *      String ingredientName = newIngredient.getDescription();
   *      </code></pre>
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of an ingredient. If the description of an ingredient is null or empty
   * an IllegalArgumentException is thrown.

   * @param description the description of an ingredient.
   * @throws IllegalArgumentException If the description of an ingredient is null or empty.
   *
   *
   *      <p><b>Example of usage:</b></p>
   *      <pre><code>
   *      String ingredientName = newIngredient.setDescription("Vegetable");
   *      </code></pre>
   */
  public void setDescription(String description) {
    if (description == null || description.isEmpty()) {
      throw new IllegalArgumentException("The description of the ingredient cannot be null"
          + " or empty.");
    }
    this.description = description;
  }

  /**
   * Retrieves the quantity of an ingredient.

   * @return the quantity of an ingredient
   *
   *
   *      <p><b>Example of usage:</b></p>
   *      <pre><code>
   *      String ingredientName = newIngredient.getQuantity();
   *      </code></pre>
   */
  public double getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of an ingredient.
   * If the quantity is less than zero an IllegalArgumentException is thrown.

   * @param quantity the quantity cannot be less than zero.
   * @throws IllegalArgumentException If the quantity is less than zero.
   *
   *
   *      <p><b>Example of usage:</b></p>
   *      <pre><code>
   *      String ingredientName = newIngredient.setQuantity(2);
   *      </code></pre>
   */
  public void setQuantity(double quantity) {
    if (quantity <= 0) {
      throw new IllegalArgumentException("The amount cannot be 0 or less.");
    }
    this.quantity = quantity;
  }

  /**
   * Retrieves the unit of an ingredient.

   * @return the unit of an ingredient
   *
   *
   *      <p><b>Example of usage:</b></p>
   *      <pre><code>
   *      String ingredientName = newIngredient.getUnit();
   *      </code></pre>
   */
  public String getUnit() {
    return unit;
  }

  /**
   * Sets the unit of an ingredient.
   * If the unit is null, empty or invalid an IllegalArgumentException is thrown.

   * @param unit The unit cannot be null empty, and it must be kg, g, l or ml.
   * @throws IllegalArgumentException If the unit null, empty or invalid.
   *
   *
   *      <p><b>Example of usage:</b></p>
   *      <pre><code>
   *      String ingredientName = newIngredient.setUnit("kg");
   *      </code></pre>
   */
  public void setUnit(String unit) {
    if (unit == null || unit.isEmpty()) {
      throw new IllegalArgumentException("The unit cannot be null or empty");
    }
    if (unit.equalsIgnoreCase("kg") || unit.equalsIgnoreCase("g")
        || unit.equalsIgnoreCase("l") || unit.equalsIgnoreCase("ml")) {
      this.unit = unit;
    } else {
      throw new IllegalArgumentException("The unit must be kg, g, l or ml.");
    }
  }

  /**
   * Retrieves the price of an ingredient.

   * @return The price of an ingredient.
   *
   *
   *      <p><b>Example of usage:</b></p>
   *      <pre><code>
   *      String ingredientName = newIngredient.getPrice();
   *      </code></pre>
   */
  public double getPrice() {
    return price;
  }

  /**
   * Sets the price of the ingredient.
   * If the price of the ingredient is less than zero an IllegalArgumentException is thrown.

   * @param price The price of the ingredient must be greater than zero.
   * @throws IllegalArgumentException If the price of the ingredient is less than zero.
   *
   *
   *      <p><b>Example of usage:</b></p>
   *      <pre><code>
   *      String ingredientName = newIngredient.getPrice(15);
   *      </code></pre>
   */
  public void setPrice(double price) {
    if (price <= 0) {
      throw new IllegalArgumentException("The price of the ingredient cannot be 0 or less");
    }
    this.price = price;
  }

  /**
   * Returns the expiration date of an ingredient.

   * @return The expiration date of an ingredient.
   *
   *
   *      <p><b>Example of usage:</b></p>
   *      <pre><code>
   *      String ingredientName = newIngredient.getExpirationDate();
   *      </code></pre>
   */
  public LocalDate getExpirationDate() {
    return expirationDate;
  }

  /**
   * Sets the expiration date of an ingredient.
   * If the expiration date is null, the previous or current date
   * an IllegalArgumentException is thrown.

   * @param expirationDate The expiration date cannot be null.
   * @throws IllegalArgumentException If the expiration date is null.
   *
   *       <p><b>Example of usage:</b></p>
   *          <pre><code>
   *          String ingredientName = newIngredient.setExpirationDate(LocalDate.Of(2024,08,14);
   *          </code></pre>
   */
  public void setExpirationDate(LocalDate expirationDate) {
    if (expirationDate == null) {
      throw new IllegalArgumentException("The expiration date cannot be null");
    }
    this.expirationDate = expirationDate;
  }
}