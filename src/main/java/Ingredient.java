import java.util.Date;
/**
 * Holds information of an ingredient.
 * The name of the ingredient, description, amount, unit, price and expiration date.

 * @author Johannes Nupen Theigen
 * @version 12.10.2024
 */

public class Ingredient {
  private String ingredientName;
  private String descriptionOfIngredient;
  private int amount;
  private String unit;
  private float price;
  private Date expirationDate;

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
                    float amount, String unit, float price, Date expirationDate) {
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
   */
  public void setIngredientName(String ingredientName) {
    boolean isEmpty = (ingredientName == null || ingredientName.isEmpty());
    if (isEmpty) {
      throw new IllegalArgumentException("The ingredient name cannot be null or empty");
    } else {
      this.ingredientName = ingredientName;
    }
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
   */
  public void setDescriptionOfIngredient(String descriptionOfIngredient) {
    boolean isEmpty = (descriptionOfIngredient == null || descriptionOfIngredient.isEmpty());
    if (isEmpty) {
      throw new IllegalArgumentException("The description of the ingredient cannot be null"
          + " or empty.");
    } else {
      this.descriptionOfIngredient = descriptionOfIngredient;
    }
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

   * @param amount the amount must be greater than zero.
   */
  public void setAmount(float amount) {
    if (amount <= 0) {
      throw new IllegalArgumentException("The amount cannot be less than 0.");
    } else {
      this.amount = amount;
    }
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
   */
  public void setUnit(String unit) {
    boolean isEmpty = (unit == null || unit.isEmpty());
    if (isEmpty) {
      throw new IllegalArgumentException("The unit cannot be null or empty.");
    } else {
      this.unit = unit;
    }
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
   */
  public void setPrice(float price) {
    if (price <= 0) {
      throw new IllegalArgumentException("The price of the ingredient cannot be less than 0");
    } else {
      this.price = price;
    }
  }

  /**
   * Returns the expiration date of an ingredient.

   * @return The expiration date of an ingredient.
   */
  public Date getExpirationDate() {
    return expirationDate;
  }

  /**
   * Sets the expiration date of an ingredient.
   * If the expiration date is null or empty an IllegalArgumentException is thrown.

   * @param expirationDate The expiration date cannot be null.
   */
  public void setExpirationDate(Date expirationDate) {
    if (expirationDate == null) {
      throw new IllegalArgumentException("The expiration date cannot be null");
    } else {
      this.expirationDate = expirationDate;
    }
  }
}