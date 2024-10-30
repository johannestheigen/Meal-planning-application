/**
 * This class contains unit tests for the FoodStorage class.
 *
 * The purpose of this test class is to validate the functionality of the FoodStorage class
 * through both positive and negative test cases. The following methods will be tested:
 *
 * 1. addIngredient: Tests if valid ingredients are added correctly and handles duplicate ingredients.
 * 2. reduceIngredient: Tests if existing ingredients can be reduced and handles cases where the quantity becomes zero.
 * 3. getIngredient: Tests if existing ingredients can be retrieved.
 * 4. getListOfIngredients: Tests if all ingredients can be listed correctly.
 * 5. getListOfIngredientsAlphabetically: Tests if ingredients can be listed in alphabetical order.
 * 6. getListOfExpiredIngredients: Tests if expired ingredients are listed correctly.
 * 7. getListOfIngredientsByExpirationDate: Tests if ingredients can be retrieved by a specific expiration date.
 * 8. getValueOfAllIngredients: Tests if the total value of all ingredients is calculated correctly.
 * 9. getValueOfExpiredIngredients: Tests if the total value of expired ingredients is calculated correctly.
 */

package edu.ntnu.idi.bidata.inventory;

import static org.junit.jupiter.api.Assertions.*;
import edu.ntnu.idi.bidata.items.Ingredient;
import java.time.LocalDate;
import org.junit.jupiter.api.*;

class FoodStorageTest {

  FoodStorage foodStorageTest;

  @BeforeEach
  void setUp()
  {
    foodStorageTest = new FoodStorage();
  }

  @Test
  void addIngredientForPositiveInput()
  {
  }

  @Test
  void addIngredientForNegativeInput()
  {
  }

  @Test
  void reduceIngredient() {
  }

  @Test
  void findIngredient() {
  }

  @Test
  void listIngredients() {
  }

  @Test
  void listIngredientsAlphabetically() {
  }

  @Test
  void listExpiredIngredients() {
  }

  @Test
  void listIngredientsByExpirationDate() {
  }

  @Test
  void getValueOfAllIngredients() {
  }

  @Test
  void getValueOfExpiredIngredients() {
  }
}