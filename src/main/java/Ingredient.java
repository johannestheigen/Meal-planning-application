/**
 * Holds information about a specific ingredient.
 * The name of the ingredient,unit and amount.
 */

public class Ingredient
{
  private String ingredientName;
  private float amount;
  private String unit;
  private float price;
  private String expirationDate;

  /**
   * Creates an ingredient.
   * @param ingredientName the name of the ingredient
   * @param amount the amount of the ingredient
   * @param unit the unit of the ingredient (gram, litre, etc.)
   * @param price the price of the ingredient.
   * @param expirationDate the expiration date of the ingredient.
   */
  public Ingredient(String ingredientName, float amount, String unit, float price, String expirationDate) {
    setIngredientName(ingredientName);
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
    if (isEmpty){
      throw new IllegalArgumentException("The ingredient name cannot be null or empty");
    } else {
      this.ingredientName = ingredientName;
    }
  }

  /**
   * Returns the amount of an ingredient
   * @return the amount of an ingredient
   */
  public float getAmount() {
    return amount;
  }

  /**
   * Sets the amount of an ingredient.
   * If the value is less than zero an IllegalArgumentException is thrown.
   * @param amount the amount must be non-negative.
   */
  public void setAmount(float amount) {
    if (amount < 0){
      throw new IllegalArgumentException("The amount cannot be less than 0.");
    } else {
      this.amount = amount;
    }
  }

  /**
   * Returns the unit of an ingredient
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
    if(isEmpty) {
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
   * If the price of the ingredient is less than 0 an IllegalArgumentException is thrown.
   * @param price The price of the ingredient must be non-negative.
   */
  public void setPrice(float price) {
    if (price < 0) {
      throw new IllegalArgumentException("The price of the ingredient cannot be less than 0");
    }
    else {
      this.price = price;
    }
  }

  /**
   * Returns the expiration date of an ingredient.
   * @return The expiration date of an ingredient.
   */
  public String getExpirationDate() {
    return expirationDate;
  }

  /**
   * Sets the expiration date of an ingredient.
   * If the expiration date is null or empty an IllegalArgumentException is thrown.
   * @param expirationDate The expiration date cannot be null or empty.
   */
  public void setExpirationDate(String expirationDate){
    boolean isEmpty = (expirationDate == null || expirationDate.isEmpty());
    if (isEmpty) {
      throw new IllegalArgumentException("The expiration date cannnot be null or empty");
    }
    else {
      this.expirationDate = expirationDate;
    }
  }
}