package edu.ntnu.idi.bidata.ingredients;

import java.time.LocalDate;

/**
 * Holds information of an ingredient.
 * The name of the ingredient, description, amount, unit, price and expiration date.

 * @author Johannes Nupen Theigen
 * @version 0.0.4
 * @since 10.16.2024
 */

public class Ingredient {
  private String ingredientName;
  private String descriptionOfIngredient;
  private float amount;
  private String unit;
  private float price;
  private LocalDate expirationDate;

  /**
   * Creates an ingredient.

   * @param ingredientName the name an ingredient
   * @param descriptionOfIngredient the description of an ingredient. (type, taste, etc.)
   * @param amount the amount of an ingredient
   * @param unit the unit of an ingredient (gram, litre, etc.)
   * @param price the price of an ingredient. (price in Norwegian kr)
   * @param expirationDate the expiration date of an ingredient.
   */
  public Ingredient(String ingredientName, String descriptionOfIngredient,
                    float amount, String unit, float price, LocalDate expirationDate) {
    setIngredientName(ingredientName);
    setDescriptionOfIngredient(descriptionOfIngredient);
    setAmount(amount);
    setUnit(unit);
    setPrice(price);
    setExpirationDate(expirationDate);
  }

  /**
   * Returns the name of an ingredient.

   * @return the name of an ingredient
   */
  public String getIngredientName() {
    return ingredientName;
  }

  /**
   * Sets the name of an ingredient
   * If the name is null or empty an IllegalArgumentException is thrown.

   * @param ingredientName The ingredientName cannot be null or empty.
   * @throws IllegalArgumentException If the name is null or empty.
   */
  public void setIngredientName(String ingredientName) throws IllegalArgumentException {
    if (ingredientName == null || ingredientName.isEmpty()) {
      throw new IllegalArgumentException("The ingredient name cannot be null or empty");
    }
    this.ingredientName = ingredientName;
  }

  /**
   * Returns the description of an ingredient.

   * @return The description of an ingredient.
   */
  public String getDescriptionOfIngredient() {
    return descriptionOfIngredient;
  }

  /**
   * Sets the description of an ingredient. If the description of an ingredient is null or empty
   * an IllegalArgumentException is thrown.

   * @param descriptionOfIngredient the description of an ingredient.
   * @throws IllegalArgumentException If the description of an ingredient is null or empty.
   */
  public void setDescriptionOfIngredient(String descriptionOfIngredient)
      throws IllegalArgumentException {
    if (descriptionOfIngredient == null || descriptionOfIngredient.isEmpty()) {
      throw new IllegalArgumentException("The description of the ingredient cannot be null"
          + " or empty.");
    }
    this.descriptionOfIngredient = descriptionOfIngredient;
  }

  /**
   * Returns the amount of an ingredient.

   * @return the amount of an ingredient
   */
  public float getAmount() {
    return amount;
  }

  /**
   * Sets the amount of an ingredient.
   * If the value is less than zero an IllegalArgumentException is thrown.

   * @param amount the amount cannot be less than zero.
   * @throws IllegalArgumentException If the amount is less than zero.
   */
  public void setAmount(float amount)throws IllegalArgumentException {
    if (amount < 0) {
      throw new IllegalArgumentException("The amount cannot be less than 0.");
    }
    this.amount = amount;
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
   * If the unit is null or empty an IllegalArgumentException is thrown.

   * @param unit The unit cannot be null or empty.
   * @throws IllegalArgumentException If the unit is null or empty.
   */
  public void setUnit(String unit) throws IllegalArgumentException {
    if (unit == null || unit.isEmpty()) {
      throw new IllegalArgumentException("The unit cannot be null or empty.");
    }
    this.unit = unit;
  }

  /**
   * Returns the price of an ingredient.

   * @return The price of an ingredient.
   */
  public float getPrice() {
    return price;
  }

  /**
   * Sets the price of the ingredient.
   * If the price of the ingredient is less than zero an IllegalArgumentException is thrown.

   * @param price The price of the ingredient must be greater than zero.
   * @throws IllegalArgumentException If the price of the ingredient is less than zero.
   */
  public void setPrice(float price) throws IllegalArgumentException {
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
   * @throws IllegalArgumentException If the expiration date is null, previous or current.
   */
  public void setExpirationDate(LocalDate expirationDate) throws IllegalArgumentException {
    if (expirationDate == null) {
      throw new IllegalArgumentException("The expiration date cannot be null");
    } else if (expirationDate.isBefore(LocalDate.now())) {
      throw new IllegalArgumentException("The expiration date cannot be the "
          + "current or previous date.");
    }
    this.expirationDate = expirationDate;
  }
}