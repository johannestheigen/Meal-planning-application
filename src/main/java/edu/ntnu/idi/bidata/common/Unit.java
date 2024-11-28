package edu.ntnu.idi.bidata.common;

/**
 * <p>The Unit enum represents different units of measurement that can be used for ingredients.
 * The enum contains a conversion factor to convert
 * the unit to a base unit (e.g., grams or milliliters).
 * The enum also contains a symbol that represents the unit (e.g., "g" for grams).</p>
 *
 * @author Johannes Nupen Theigen
 * @version 0.0.1
 * @since 11.28.2024
 */
public enum Unit {
  GRAM(1.0, "g"),
  KILOGRAM(1000.0, "kg"),
  MILLILITER(0.001, "ml"),
  LITER(1.0, "l");
  private final double toBaseFactor;
  private final String symbol;

  Unit(double toBaseFactor, String symbol) {
    this.toBaseFactor = toBaseFactor;
    this.symbol = symbol;
  }

  /**
   * <p>Converts a quantity of the unit to the base unit (e.g., grams or milliliters).</p>
   *
   * @param quantity the quantity of the unit to convert
   * @return the quantity in the base unit
   */
  public double toBase(double quantity) {
    return quantity * toBaseFactor;
  }

  /**
   * <p>Converts a quantity in the base unit (e.g., grams or milliliters) to the unit.</p>
   *
   * @param quantityInBase the quantity in the base unit to convert
   * @return the quantity in the unit
   */
  public double fromBase(double quantityInBase) {
    return quantityInBase / toBaseFactor;
  }

  /**
   * <p>Returns the symbol that represents the unit (e.g., "g" for grams).</p>
   *
   * @return the symbol of the unit
   */
  public String getSymbol() {
    return symbol;
  }
}