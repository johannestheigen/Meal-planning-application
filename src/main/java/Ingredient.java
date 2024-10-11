/**
 * Holds information about a specific ingredient.
 * The name of the ingredient,unit and amount.
 */

public class Ingredient
{
  private String ingredientName;
  private String unit;
  private float amount;

  /**
   * Creates an ingredient.
   * @param ingredientName the name of the ingredient
   * @param unit the unit of the ingredient (gram, litre, etc.)
   * @param amount the amount of the ingrdient
   */
  public Ingredient(String ingredientName, String unit, float amount) {
    setIngredientName(ingredientName);
    setUnit(unit);
    this.amount = 0;
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
   */
  public void setIngredientName() {
    this.ingredientName = ingredientName;
  }

  /**
   * Returns the unit of an ingredient
   * @return the unit of an ingredient
   */
  public String getUnit() {
    return unit;
  }

  /**
   * Sets the unit of an ingredient
   */
  public void setUnit() {
    this.unit = unit;
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
   */
  public void setAmount() {
    this.amount = amount;
  }
}
