/**
 * This class contains unit tests for the FoodStorage class
 * that validates functionality of the FoodStorage methods
 * including addIngredient, removeIngredient, findIngredient,
 * getIngredientList, listIngredientsAlphabetically,
 * listExpiredIngredients, listIngredientsByDate,
 * printValueOfAllIngredients and printValueOfExpiredIngredients.
 */

package edu.ntnu.idi.bidata.inventory;

import static org.junit.jupiter.api.Assertions.*;

import edu.ntnu.idi.bidata.items.Ingredient;
import org.junit.jupiter.api.*;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class FoodStorageTest {

  FoodStorage testFoodStorage;
  ArrayList<Ingredient> testStorage;


  /**
   * Initializes an instance of FoodStorage before each test
   */
  @BeforeEach
  void setUp() {
    testFoodStorage = new FoodStorage();
    testStorage = new ArrayList<>();

  }

  /**
   * Validates that an ingredient is successfully added to the FoodStorage.
   * It checks that the ingredient can be found in the storage after being added.
   */
  @Test
  void addIngredientTestForPostiveInput() {
    Ingredient newIngredient = new Ingredient("Sugar", "Sweetener", 2, "kg", 4.0, LocalDate.of(2025, 1, 1));
    testFoodStorage.addIngredient(newIngredient);

    assertEquals("Sugar", testFoodStorage.findIngredient("Sugar").getName(), "The output should be Sugar");
  }

  /**
   * Validates that adding an ingredient does not result in incorrect retrieval of another ingredient.
   */

  @Test
  void addIngredientTestForNegativeInput() {
    Ingredient newIngredient = new Ingredient("Potato", "Vegetable", 2, "kg", 4.0, LocalDate.of(2025, 1, 1));
    testFoodStorage.addIngredient(newIngredient);

    assertNotEquals("Sugar", testFoodStorage.findIngredient("Potato").getName(), "The output should not be sugar since potato was added to the storage!");
  }

  /**
   * Validates that an ingredient can be removed from FoodStorage successfully.
   * It ensures that the ingredient no longer exists in the storage after removal.
   */

  @Test
  void removeIngredientTestForPositiveInput() {
    Ingredient newIngredient = new Ingredient("Sugar", "Sweetener", 1, "kg", 2.0, LocalDate.of(2025, 1, 1));
    testFoodStorage.addIngredient(newIngredient);

    boolean removed = testFoodStorage.removeIngredient("Sugar");
    assertTrue(removed, "The ingredient should be removed successfully.");

    assertNull(testFoodStorage.findIngredient("Sugar"), "The ingredient should no longer exist.");
  }

  /**
   * Test that checks if an Ingredient is properly reduced and removed from the storage.
   */

  @Test
  void removeIngredientTestForNegativeInput() {
    Ingredient newIngredient = new Ingredient("Banana", "Fruit", 2, "kg", 2.0, LocalDate.of(2025, 1, 1));
    testFoodStorage.addIngredient(newIngredient);

    boolean removed = testFoodStorage.removeIngredient("Sugar");
    assertFalse(removed, "The ingredient Sugar should not have been removed as it does not exist.");
  }

  /**
   * Validates that an existing ingredient can be found in the FoodStorage.
   */
  @Test
  void findIngredientTestForPositiveInput() {
    Ingredient newIngredient = new Ingredient("Apple", "Fruit", 4, "kg", 17.50, LocalDate.of(2024, 12, 1));
    testFoodStorage.addIngredient(newIngredient);

    Ingredient foundIngredient = testFoodStorage.findIngredient("Apple");
    assertNotNull(foundIngredient, "The ingredient Apple should be found in the storage.");
  }

  /**
   * Validates that a non-existing ingredient cannot be found in the FoodStorage.
   */
  @Test
  void findIngredientTestForNegativeInput() {
    Ingredient newIngredient = new Ingredient("Beef", "Meat", 4, "kg", 6.50, LocalDate.of(2024, 12, 1));
    testFoodStorage.addIngredient(newIngredient);

    Ingredient foundIngredient = testFoodStorage.findIngredient("Apple");
    assertNull(foundIngredient, "The ingredient Apple should be not found in the storage.");
  }
}