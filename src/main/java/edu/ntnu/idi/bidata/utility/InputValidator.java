package edu.ntnu.idi.bidata.utility;

/**
 * <p>The InputValidator class is responsible for validating user input
 * that is provided through the console to determine if the user
 * wants to perform a specific operation.</p>
 *
 * @author Johannes Nupen Theigen
 * @version 0.0.9
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
   * <p>Checks if the user wants to abort a specific operation.</p>
   *
   * @param userInput The user input
   * @return true if the user wants to abort the operation, false otherwise
   */
  public boolean isAbortOperation(String userInput) {
    return userInput.equalsIgnoreCase("n");
  }

  /**
   * <p>Checks if the user has pressed any key to continue the program
   * after a specific operation has been performed.</p>

   * @param userInput The user input
   * @return true if the user has pressed any key, false otherwise
   */
  public boolean isAnyKeyPressed(String userInput) {
    return userInput.isEmpty();
  }

  /**
   * <p>Checks if the user has provided a valid integer.</p>

   * @param value The value to check
   * @return true if the value is a valid integer, false otherwise
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
   * <p>Checks if the provided String is a valid unit.
   * The valid units are: g, kg, ml, l and pcs.</p>

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