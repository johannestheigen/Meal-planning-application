/**
 * This class contains unit tests for the Ingredient class.
 * It tests the functionality of the Ingredient methods
 * including setIngredientName, setAmount, and setExpirationDate.
 */

package edu.ntnu.idi.bidata.items;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.bidata.common.Unit;
import org.junit.jupiter.api.*;
import java.time.LocalDate;

class IngredientTest {

  Ingredient testIngredient;

  /**
   * Creates a test object of Ingredient that will be used throughout the entire test.
   */
  @BeforeEach
  void beforeEach() {
    testIngredient = new Ingredient("Test Ingredient", 10, Unit.KILOGRAM, 100, LocalDate.of(2025, 1, 1));
  }

  /**
   * Test that checks if a valid input of ingredientName is set correctly.
   */
  @Test
  void setIngredientNameTestForPositiveInput() {
    testIngredient.setName("Tomato");
    assertEquals("Tomato", testIngredient.getName(), "The output should be Tomato");
  }

  @Test
  void setIngredientNameTestForNegativeInput() {
    testIngredient.setName("Tomato");
    assertNotEquals("Tomata",testIngredient.getName(),"The output should not be Tomata.");
  }

  @Test
  void setAmountForPositiveInput() {
    testIngredient.setQuantity(10);
    assertEquals(10,testIngredient.getQuantity(),"The output should be 10");
  }

  @Test
  void setAmountForNegativeInput() {
    testIngredient.setQuantity(100);
    assertNotEquals(-50,testIngredient.getQuantity(),"The output should not -50");
  }

  @Test
  void setUnitForPositiveInput() {
    testIngredient.setUnit(Unit.KILOGRAM);
    assertEquals(Unit.KILOGRAM,testIngredient.getUnit(),"The output should be" + Unit.KILOGRAM);
  }

  @Test
  void setUnitForNegativeInput() {
    testIngredient.setUnit(Unit.KILOGRAM);
    assertNotEquals(Unit.GRAM,testIngredient.getUnit(),"The output should not be" + Unit.GRAM);
  }

  @Test
  void setPriceForPositiveInput() {
    testIngredient.setPrice(1);
    assertEquals(1,testIngredient.getPrice(),"The output should be 1.");
  }

  @Test
  void setPriceForNegativeInput() {
    testIngredient.setPrice(5);
    assertNotEquals(9,testIngredient.getPrice(), "The output should not be 9");
  }

  @Test
  void setExpirationDateForPositiveInput() {
    testIngredient.setExpirationDate(LocalDate.of(2035,1,1));
    assertEquals(LocalDate.of(2035,1,1),testIngredient.getExpirationDate(),"The output should be 2035 1 1");
  }

  @Test
  void setExpirationDateForNegativeInput() {
    testIngredient.setExpirationDate(LocalDate.of(2037,6,17));
    assertNotEquals(LocalDate.of(2029,1,1),testIngredient.getExpirationDate(),"The output should not be 2029 1 1");
  }
}