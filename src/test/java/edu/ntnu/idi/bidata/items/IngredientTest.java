package edu.ntnu.idi.bidata.items;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.time.LocalDate;

/**
 * <p>Test class for the Ingredient class.
 */
class IngredientTest {

  Ingredient testIngredient;

  /**
   * <p>Initializes a new instance of the Ingredient class before each test.</p>
   */
  @BeforeEach
  void beforeEach() {
    testIngredient = new Ingredient("Test Ingredient", 10, "kg", 100, LocalDate.of(2025, 1, 1));
  }

  /**
   * <p>Test that checks if a valid input of ingredientName is set correctly.</p>
   */
  @Test
  void setIngredientNameTestPositiveTest() {
    testIngredient.setName("Tomato");
    assertEquals("Tomato", testIngredient.getName(), "The output should be Tomato");
  }

  /**
   * <p>Test that checks that the ingredientName is not incorrectly set</p>
   */
  @Test
  void setIngredientNameTestNegativeTest() {
    testIngredient.setName("Tomato");
    assertNotEquals("Apple",testIngredient.getName(),"The output should not be Apple.");
  }

  /**
   * <p>Test that checks if a valid input of quantity is set correctly.</p>
   */
  @Test
  void setQuantityPositiveTest() {
    testIngredient.setQuantity(10);
    assertEquals(10,testIngredient.getQuantity(),"The output should be 10");
  }

  /**
   * <p>Test that checks that the quantity is not incorrectly set.</p>
   */
  @Test
  void setQuantityNegativeTest() {
    testIngredient.setQuantity(100);
    assertNotEquals(-50,testIngredient.getQuantity(),"The output should not -50");
  }

  /**
   * <p>Test that checks if a valid input of unit is set correctly.</p>
   */
  @Test
  void setUnitPositiveTest() {
    testIngredient.setUnit("kg");
    assertEquals("kg",testIngredient.getUnit(),"The output should be kg");
  }

  /**
   * <p>Test that checks that the unit is not incorrectly set.</p>
   */
  @Test
  void setUnitNegativeTest() {
    testIngredient.setUnit("kg");
    assertNotEquals("mg",testIngredient.getUnit(),"The output should not be g");
  }

  /**
   * <p>Test that checks if a valid input of price is set correctly.</p>
   */
  @Test
  void setPricePositiveTest() {
    testIngredient.setPrice(1);
    assertEquals(1,testIngredient.getPrice(),"The output should be 1.");
  }

  /**
   * <p>Test that checks that the price is not incorrectly set.</p>
   */
  @Test
  void setPriceNegativeTest() {
    testIngredient.setPrice(5);
    assertNotEquals(9,testIngredient.getPrice(), "The output should not be 9");
  }

  /**
   * <p>Test that checks if a valid input of expirationDate is set correctly.</p>
   */
  @Test
  void setExpirationDatePositiveTest() {
    testIngredient.setExpirationDate(LocalDate.of(2035,1,1));
    assertEquals(LocalDate.of(2035,1,1),testIngredient.getExpirationDate(),"The output should be 2035 1 1");
  }

  /**
   * <p>Test that checks that the expirationDate is not incorrectly set</p>
   */
  @Test
  void setExpirationDateNegativeTest() {
    testIngredient.setExpirationDate(LocalDate.of(2037,6,17));
    assertNotEquals(LocalDate.of(2029,1,1),testIngredient.getExpirationDate(),"The output should not be 2029 1 1");
  }
}