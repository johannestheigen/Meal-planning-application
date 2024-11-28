package edu.ntnu.idi.bidata.common;

import edu.ntnu.idi.bidata.items.Ingredient;

/**
 * <p>The UnitConverter class is used to adjust the unit of an ingredient if needed. The method
 * checks if the quantity of the ingredient is within a certain range and converts the unit if
 * necessary.</p>
 *
 * @author Johannes Nupen Theigen
 * @version 0.0.1
 * @since 11.28.2024
 */
public class UnitConverter {

  /**
   * <p>Private constructor to prevent instantiation of the class.</p>
   * <p>Since the class only contains static methods, it should not be instantiated.</p>
   * <p>Therefore, the constructor is private to prevent instantiation.</p>
   */
  private UnitConverter() {
  }
  /**
   * <p>Adjusts the unit of an ingredient if needed. The method checks if the quantity of the
   * ingredient is within a certain range and converts the unit if necessary.</p>
   * <p>For example, if the quantity is greater than or equal to 1000 grams, the unit is converted
   * to kilograms. If the quantity is less than 1 kilogram, the unit is converted to grams.</p>
   *
   * @param ingredient the ingredient to adjust the unit of
   */
  public static void adjustUnitIfNeeded(Ingredient ingredient) {
    Unit currentUnit = ingredient.getUnit();
    double quantity = ingredient.getQuantity();

    if (currentUnit == Unit.GRAM && quantity >= 1000) {
      convertUnit(ingredient, Unit.KILOGRAM, 1000);
    } else if (currentUnit == Unit.KILOGRAM && quantity < 1) {
      convertUnit(ingredient, Unit.GRAM, 0.001);
    } else if (currentUnit == Unit.MILLILITER && quantity >= 1000) {
      convertUnit(ingredient, Unit.LITER, 1000);
    } else if (currentUnit == Unit.LITER && quantity < 1) {
      convertUnit(ingredient, Unit.MILLILITER, 0.001);
    }
  }

  private static void convertUnit(Ingredient ingredient, Unit newUnit, double conversionFactor) {
    if (newUnit == Unit.KILOGRAM) {
      ingredient.setQuantity(ingredient.getQuantity() / conversionFactor);
    } else {
      ingredient.setQuantity(ingredient.getQuantity() * conversionFactor);
    }
    ingredient.setPrice(ingredient.getPrice() * conversionFactor);
    ingredient.setUnit(newUnit);
  }

  /**
   * <p>Checks if a conversion between two units is valid.</p>
   *
   * @param currentUnit the current unit of the ingredient
   * @param newUnit     the new unit to convert to
   * @return true if the conversion is valid, false otherwise
   */
  public static boolean isValidConversion(Unit currentUnit, Unit newUnit) {
    if (currentUnit == Unit.GRAM && newUnit == Unit.KILOGRAM) {
      return true;
    } else if (currentUnit == Unit.KILOGRAM && newUnit == Unit.GRAM) {
      return true;
    } else if (currentUnit == Unit.MILLILITER && newUnit == Unit.LITER) {
      return true;
    } else{
      return currentUnit == Unit.LITER && newUnit == Unit.MILLILITER;
    }
  }

  /**
   * <p>Converts the amount of an ingredient based on the unit.</p>
   * <p>For example, if the unit is kilograms, the quantity is converted to grams. If the unit is
   * liters, the quantity is converted to milliliters.</p>
   * <p>The price of the ingredient is also adjusted accordingly.</p>
   * @param ingredient the ingredient to convert the amount of
   */
  public static void convertAmountBasedOnUnit(Ingredient ingredient) {
    Unit currentUnit = ingredient.getUnit();
    double currentQuantity = ingredient.getQuantity();
    double currentPrice = ingredient.getPrice();

    if (currentUnit.equals(Unit.KILOGRAM) || currentUnit.equals(Unit.LITER)) {
      ingredient.setQuantity(currentQuantity * 1000);
      ingredient.setUnit(currentUnit.equals(Unit.KILOGRAM) ? Unit.GRAM : Unit.MILLILITER);
      ingredient.setPrice(currentPrice / 1000);
    } else if (currentUnit.equals(Unit.GRAM) || currentUnit.equals(Unit.MILLILITER)) {
      ingredient.setQuantity(currentQuantity / 1000);
      ingredient.setUnit(currentUnit.equals(Unit.GRAM) ? Unit.KILOGRAM : Unit.LITER);
      ingredient.setPrice(currentPrice * 1000);
    }
  }
}