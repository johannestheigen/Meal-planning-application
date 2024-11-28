package edu.ntnu.idi.bidata.utility;

import edu.ntnu.idi.bidata.register.FoodStorage;

/**
 *<p>This class is responsible for validating user input.</p>
 * <p>It checks if the user input is valid or not.</p>
 * <p>It checks if the user input is in the correct format or not.</p>
 *
 *
 * @author Johannes Nupen Theigen
 * @version 0.0.7
 * @since 11.27.2024
 */
public class InputValidator {


  /**
   * <p>Checks if the user wants to exit the program.</p>

   * @param userInput The user input
   * @return true if the user wants to exit the program, false otherwise
   */
  public boolean isExiting(String userInput) {
    return userInput.equalsIgnoreCase("y");
  }

  /**
   * <p>Checks if the user wants to abort the operation.</p>
   * <p>For example, when the user is asked if
   * he wants to abort the operation of adding a new ingredient.</p>

   * @param userInput The user input
   * @return true if the user wants to abort the operation, false otherwise
   */
  public boolean isAbortOperation(String userInput) {
    return userInput.equalsIgnoreCase("n");
  }

  /**
   * <p>Checks if the user has pressed any key.</p>

   * @param userInput The user input
   * @return true if the user has pressed any key, false otherwise
   */
  public boolean isAnyKeyPressed(String userInput) {
    return userInput.isEmpty();
  }

  /**
   * <p>Checks if an ingredient was reduced.</p>

   * @param foodStorage The food storage
   * @param name The name of the ingredient
   * @param amount The amount of the ingredient
   * @return true if the ingredient was reduced, false otherwise
   */
  public boolean ingredientWasReduced(FoodStorage foodStorage, String name, double amount) {
    return foodStorage.getIngredient(name) != null;
  }

  /**
   * <p>Checks if a recipe does not exist in the recipe book.</p>

   * @param value The value to check
   * @return true if the recipe does not exist, false otherwise
   */
  public boolean isPositiveInteger(int value) {
    return value > 0;
  }

  /**
   * <p>Checks if a value is a positive double.</p>

   * @param value The value to check
   * @return true if the value is a positive double, false otherwise
   */
  public boolean isPositiveDouble(double value) {
    return value > 0.0;
  }

  /**
   * <p>Checks if a value is a non-empty string.</p>

   * @param value The value to check
   * @return true if the value is a non-empty string, false otherwise
   */
  public boolean isNonEmptyString(String value) {
    return value != null && !value.trim().isEmpty();
  }

  /**
   * <p>Checks if the provided String is a valid unit.</p>

   * @param unit The unit to check
   * @return true if the unit is valid, false otherwise
   */
  public boolean isValidUnit(String unit) {
    return unit != null
        && (unit.equalsIgnoreCase("g")
        || unit.equalsIgnoreCase("kg")
        || unit.equalsIgnoreCase("ml")
        || unit.equalsIgnoreCase("l")
        || unit.equalsIgnoreCase("pcs"));
  }
}