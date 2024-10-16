/**
 * This class contains unit tests for the Ingredient class.
 * It tests the functionality of the Ingredient methods
 * including setIngredientName, setAmount, and setExpirationDate.
 * The other methods setDescriptionOfIngredient, setUnit,
 * and setPrice are not tested as they have similar implementations.
 */

package edu.ntnu.idi.bidata.ingredients;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.time.LocalDate;

class IngredientTest {

  Ingredient testIngredient;

  /**
   * Creates a test object of Ingredient that will be used throughout the entire test.
   */
  @BeforeEach
  void beforeEach() {
    testIngredient = new Ingredient("Test Ingredient", "Test Description", 10, "kg", 100, LocalDate.of(2025, 1, 1));
  }

  /**
   * Test that checks if a valid input of ingredientName is set correctly.
   */
  @Test
  void setIngredientNameTestForValidInput() {
    testIngredient.setIngredientName("Tomato");
    assertEquals("Tomato", testIngredient.getIngredientName(), "The output should be Tomato");
  }

  /**
   * Test that checks if an invalid input of null for ingredientName is set,
   * throws the expected IllegalArgumentException.
   */
  @Test
  void setIngredientNameTestForNullInput() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      testIngredient.setIngredientName(null);
    });
    assertEquals("The ingredient name cannot be null or empty", exception.getMessage());
  }

  /**
   * Test that checks if an invalid input of an empty string for ingredientName is set,
   * throws the expected IllegalArgumentException.
   */
  @Test
  void setIngredientNameTestForEmptyInput() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      testIngredient.setIngredientName("");
    });
    assertEquals("The ingredient name cannot be null or empty", exception.getMessage());
  }

  /**
   * Test that checks if a positive input of amount is set,
   * returns the same positive input.
   */
  @Test
  void setAmountTestForPositiveValues() {
    testIngredient.setAmount(10);
    assertEquals(10, testIngredient.getAmount(), "The output should be 10");
  }

  /**
   * Test that checks if a negative input of amount is set,
   * throws the expected IllegalArgumentException.
   */
  @Test
  void setAmountTestForNegativeValues() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      testIngredient.setAmount(-1);
    });
    assertEquals("The amount cannot be less than 0.", exception.getMessage());
  }

  /**
   * Test that checks if an amount of zero is valid.
   */
  @Test
  void setAmountTestForZero() {
    testIngredient.setAmount(0);
    assertEquals(0, testIngredient.getAmount(), "The output should be 0");
  }

  /**
   * Test that checks if a valid input of expirationDate is set correctly.
   */
  @Test
  void setExpirationDateForValidInput() {
    LocalDate validDate = LocalDate.of(2026, 10, 25);
    testIngredient.setExpirationDate(validDate);
    assertEquals(validDate, testIngredient.getExpirationDate(), "The expiration date should be 2026-10-25");
  }

  /**
   * Test that checks if an invalid input of null for expirationDate is set,
   * throws the expected IllegalArgumentException.
   */
  @Test
  void setExpirationDateForNullInput() {
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      testIngredient.setExpirationDate(null);
    });
    assertEquals("The expiration date cannot be null", exception.getMessage());
  }

  /**
   * Test that checks if an invalid input for expirationDate is before the current date,
   * throws the expected IllegalArgumentException.
   */
  @Test
  void setExpirationDateForIsBeforeDateInput() {
    LocalDate pastDate = LocalDate.of(2022, 1, 1); // Fixed past date for stability
    Exception exception = assertThrows(IllegalArgumentException.class, () -> {
      testIngredient.setExpirationDate(pastDate);
    });
    assertEquals("The expiration date cannot be the current or previous date.", exception.getMessage());
  }
}
