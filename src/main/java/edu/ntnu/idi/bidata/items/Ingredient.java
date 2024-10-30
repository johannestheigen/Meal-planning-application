package edu.ntnu.idi.bidata.items;

import java.time.LocalDate;

/**
 * Holds information of an ingredient.
 * The name of the ingredient, description, quantity, unit, price and expiration date.

 * @author Johannes Nupen Theigen
 * @version 0.0.8
 * @since 10.30.2024
 */

public class Ingredient {
  private String name;
  private String description;
  private double quantity;
  private String unit;
  private double price;
  private LocalDate expirationDate;

  /**
   * Creates an ingredient.

   * @param name the name an ingredient
   * @param description the description of an ingredient. (type, taste, etc.)
   * @param quantity the quantity of an ingredient
   * @param unit the unit of an ingredient (gram, litre, etc.)
   * @param price the price of an ingredient. (price in Norwegian kr)
   * @param expirationDate the expiration date of an ingredient.
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
   * Returns the name of an ingredient.

   * @return the name of an ingredient
   */

  public String getName() {
    return name;
  }

  /**
   * Sets the name of an ingredient
   * If the name is null or empty an IllegalArgumentException is thrown.

   * @param name The ingredientName cannot be null or empty.
   * @throws IllegalArgumentException If the name is null or empty.
   */
  public void setName(String name) throws IllegalArgumentException {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The ingredient name cannot be null or empty");
    }
    this.name = name;
  }

  /**
   * Returns the description of an ingredient.

   * @return The description of an ingredient.
   */
  public String getDescription() {
    return description;
  }

  /**
   * Sets the description of an ingredient. If the description of an ingredient is null or empty
   * an IllegalArgumentException is thrown.

   * @param description the description of an ingredient.
   * @throws IllegalArgumentException If the description of an ingredient is null or empty.
   */
  public void setDescription(String description)
      throws IllegalArgumentException {
    if (description == null || description.isEmpty()) {
      throw new IllegalArgumentException("The description of the ingredient cannot be null"
          + " or empty.");
    }
    this.description = description;
  }

  /**
   * Returns the quantity of an ingredient.

   * @return the quantity of an ingredient
   */
  public double getQuantity() {
    return quantity;
  }

  /**
   * Sets the quantity of an ingredient.
   * If the quantity is less than zero an IllegalArgumentException is thrown.

   * @param quantity the quantity cannot be less than zero.
   * @throws IllegalArgumentException If the quantity is less than zero.
   */
  public void setQuantity(double quantity)throws IllegalArgumentException {
    if (quantity < 0) {
      throw new IllegalArgumentException("The amount cannot be less than 0.");
    }
    this.quantity = quantity;
  }

  /**
   * Returns the unit of an ingredient.

   * @return the unit of an ingredient
   */
  public String getUnit() {
    return unit;
  }

  /**
   * Sets the unit of an ingredient.
   * If the unit is null, empty or invalid an IllegalArgumentException is thrown.

   * @param unit The unit cannot be null empty, and it must be kg, g, l or ml.
   * @throws IllegalArgumentException If the unit null, empty or invalid.
   */
  public void setUnit(String unit) throws IllegalArgumentException {
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
   * Returns the price of an ingredient.

   * @return The price of an ingredient.
   */
  public double getPrice() {
    return price;
  }

  /**
   * Sets the price of the ingredient.
   * If the price of the ingredient is less than zero an IllegalArgumentException is thrown.

   * @param price The price of the ingredient must be greater than zero.
   * @throws IllegalArgumentException If the price of the ingredient is less than zero.
   */
  public void setPrice(double price) throws IllegalArgumentException {
    if (price < 0) {
      throw new IllegalArgumentException("The price of the ingredient cannot be less than 0");
    }
    this.price = price;
  }

  /**
   * Returns the expiration date of an ingredient.

   * @return The expiration date of an ingredient.
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
   */
  public void setExpirationDate(LocalDate expirationDate) throws IllegalArgumentException {
    if (expirationDate == null) {
      throw new IllegalArgumentException("The expiration date cannot be null");
    }
    this.expirationDate = expirationDate;
  }
}