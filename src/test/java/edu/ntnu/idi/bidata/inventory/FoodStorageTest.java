/**
 * <h1>Unit Tests for the <code>FoodStorage</code> Class</h1>
 *
 * <p>The purpose of this test class is to validate the functionality of the <code>FoodStorage</code> class
 * through both positive and negative test cases. The following methods are tested:</p>
 *
 * <ol>
 *   <li><b>addIngredient:</b> Tests if valid ingredients are added correctly and handles duplicate ingredients.</li>
 *   <li><b>reduceIngredient:</b> Tests if existing ingredients can be reduced and if ingredients are removed when the quantity reaches zero.</li>
 *   <li><b>getIngredient:</b> Tests if existing ingredients can be retrieved by name.</li>
 *   <li><b>getListOfIngredients:</b> Tests if all ingredients can be listed correctly.</li>
 *   <li><b>getListOfIngredientsAlphabetically:</b> Tests if ingredients can be listed in alphabetical order.</li>
 *   <li><b>getListOfExpiredIngredients:</b> Tests if expired ingredients are listed correctly.</li>
 *   <li><b>getListOfIngredientsByExpirationDate:</b> Tests if ingredients can be retrieved by a specific expiration date.</li>
 *   <li><b>getValueOfAllIngredients:</b> Tests if the total value of all ingredients is calculated correctly.</li>
 *   <li><b>getValueOfExpiredIngredients:</b> Tests if the total value of expired ingredients is calculated correctly.</li>
 * </ol>
 *
 * <p>Each method is validated for both expected success and failure cases to ensure robustness.</p>
 */

package edu.ntnu.idi.bidata.inventory;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import edu.ntnu.idi.bidata.items.Ingredient;
import java.time.LocalDate;
import java.util.Iterator;
import org.junit.jupiter.api.*;

class FoodStorageTest {

  FoodStorage foodStorageTest;

  /**
   * Initializes a shared instance of <code>FoodStorage</code> to be used
   * throughout the entire test.
   */

  @BeforeEach
  void setUp() {
    foodStorageTest = new FoodStorage();
  }

  /**
   * Positive test which checks that an ingredient is added to the storage
   * with the correct name, and that its quantity increases when a duplicate
   * ingredient is added.
   */
  @Test
  void addIngredientPositiveTest() {
    foodStorageTest.addIngredient("Tomato", "Vegetable", 1, "kg", 15.50, LocalDate.of(2024, 12, 1));
    foodStorageTest.addIngredient("Tomato", "Vegetable", 1, "kg", 15.50, LocalDate.of(2024, 12, 1));

    Ingredient addedIngredient = foodStorageTest.getIngredient("Tomato");

    assertNotNull(addedIngredient, "Expected the ingredient 'Tomato' to exist in the storage after addition, but it was found to be null.");

    assertEquals("Tomato", addedIngredient.getName(), "Expected the name of the ingredient to be 'Tomato', but found: " + addedIngredient.getName());

    assertEquals(2, addedIngredient.getQuantity(), "Expected the quantity of 'Tomato' to be 2 after duplication, but found: " + addedIngredient.getQuantity());
  }

  /**
   * Negative test to ensure that when an ingredient is added to storage,
   * it is actually added, and its name does not match an incorrect value.
   */
  @Test
  void addIngredientNegativeTest() {
    foodStorageTest.addIngredient("Tomato", "Vegetable", 1, "kg", 15.50, LocalDate.of(2024, 12, 1));

    assertNotEquals("Tomata", foodStorageTest.getIngredient("Tomato").getName(), "The name should not be 'Tomata'.");

    assertNotNull(foodStorageTest.getIngredient("Tomato"), "Expected the ingredient 'Tomato' to exist in storage, but it was found to be null.");

    assertNotEquals(0, foodStorageTest.getIngredient("Tomato").getQuantity(), "Expected the quantity of 'Tomato' to be greater than 0, but found: " + foodStorageTest.getIngredient("Tomato").getQuantity());
  }

  /**
   * Positive test that checks if an ingredient is reduced correctly
   * and that it remains in storage after the reduction.
   */
  @Test
  void reduceIngredientPositiveTest() {
    foodStorageTest.addIngredient("Apple", "Fruit", 2, "kg", 4.50, LocalDate.of(2024, 11, 1));

    foodStorageTest.reduceIngredient("Apple");

    Ingredient reducedIngredient = foodStorageTest.getIngredient("Apple");

    assertNotNull(reducedIngredient, "Expected 'Apple' to still exist after reduction, but it was found to be null.");

    assertEquals(1, reducedIngredient.getQuantity(), "Expected the quantity of 'Apple' to be 1 after reduction, but found: " + reducedIngredient.getQuantity());
  }

  /**
   * Negative test that checks if an ingredient is reduced correctly
   * and that it is entirely removed from storage when its quantity
   * reaches 0.
   */
  @Test
  void reduceIngredientNegativeTest() {
    foodStorageTest.addIngredient("Apple", "Fruit", 1, "kg", 4.50, LocalDate.of(2024, 11, 1));

    foodStorageTest.reduceIngredient("Apple");

    Ingredient removedIngredient = foodStorageTest.getIngredient("Apple");
    assertNull(removedIngredient, "Expected 'Apple' to be null after reduction, but found: " + removedIngredient);
  }

  /**
   * Positive test that checks if an ingredient can be retrieved
   * after being added to the storage.
   */
  @Test
  void getIngredientPositiveTest() {
    foodStorageTest.addIngredient("Blueberry", "Fruit", 1, "kg", 2.50, LocalDate.of(2025, 1, 1));

    Ingredient addedIngredient = foodStorageTest.getIngredient("Blueberry");

    assertEquals("Blueberry", addedIngredient.getName(), "Expected the name to be 'Blueberry', but found: " + addedIngredient.getName());
  }

  /**
   * Negative test that checks that an incorrect ingredient
   * is not returned when retrieving it from the storage.
   */
  @Test
  void getIngredientNegativeTest() {
    foodStorageTest.addIngredient("Blueberry", "Fruit", 1, "kg", 2.50, LocalDate.of(2025, 1, 1));

    Ingredient addedIngredient = foodStorageTest.getIngredient("Blueberry");

    assertNotEquals("Raspberry", addedIngredient.getName(), "Expected the name to be 'Blueberry', but found: " + addedIngredient.getName());
  }

  /**
   * Positive test that checks that the correct name can be
   * retrieved after being added.
   */
  @Test
  void getIngredientNamePositiveTest() {
    foodStorageTest.addIngredient("Tomato", "Vegetable", 1, "kg", 15.50, LocalDate.of(2024, 12, 1));

    String ingredientName = foodStorageTest.getIngredientName("Tomato");

    assertEquals("Tomato", ingredientName, "Ingredient name should be 'Tomato'.");
  }

  /**
   * Negative test that checks that an incorrect ma,e
   * is not returned from the storage.
   */
  @Test
  void getIngredientNameNegativeTest() {
    String ingredientName = foodStorageTest.getIngredientName("Blueberry");

    assertNull(ingredientName, "Ingredient 'Blueberry' should not exist in storage, expected null.");
  }

  /**
   * Positive test that checks that the correct list of
   * ingredients is returned after adding ingredients to the storage.
   */
  @Test
  void getListOfIngredientsPositiveTest() {
    foodStorageTest.addIngredient("Tomato", "Vegetable", 1, "kg", 2.50, LocalDate.of(2028, 1, 1));
    foodStorageTest.addIngredient("Apple", "Fruit", 1, "kg", 1.50, LocalDate.of(2025, 1, 1));

    Iterator<String> iterator = foodStorageTest.getListOfIngredients();

    int count = 0;
    while (iterator.hasNext()) {
      String ingredientName = iterator.next();
      count++;
      assertTrue(ingredientName.equals("Tomato") || ingredientName.equals("Apple"),
          "Ingredient should be either 'Tomato' or 'Apple'.");
    }

    assertEquals(2, count, "Expected exactly 2 ingredients in the list.");
  }

  /**
   * Negative test that checks that incorrect names are not returned
   * when retrieving a list of added ingredients from storage.
   */
  @Test
  void getListOfIngredientsNegativeTest() {
    foodStorageTest.addIngredient("Tomato", "Vegetable", 1, "kg", 2.50, LocalDate.of(2028, 1, 1));
    foodStorageTest.addIngredient("Apple", "Fruit", 1, "kg", 1.50, LocalDate.of(2025, 1, 1));

    Iterator<String> iterator = foodStorageTest.getListOfIngredients();

    int count = 0;
    while (iterator.hasNext()) {
      String ingredientName = iterator.next();
      count++;
      assertNotEquals("Banana", ingredientName, "Expected the ingredient not to be 'Banana'.");
      assertNotEquals("Chocolate", ingredientName, "Expected the ingredient not to be 'Chocolate'.");
    }

    assertEquals(2, count, "Expected exactly 2 ingredients in the list.");
  }

  /**
   * Positive test that checks that the correct list of
   * ingredients is returned in alphabetical order
   * after adding ingredients to the storage.
   */
  @Test
  void getListOfIngredientsAlphabeticallyPositiveTest() {
    foodStorageTest.addIngredient("Avocado", "Vegetable", 1, "kg", 5.50, LocalDate.of(2028, 1, 1));
    foodStorageTest.addIngredient("Lemon", "Fruit", 1, "kg", 7.50, LocalDate.of(2024, 1, 1));

    Iterator<String> iterator = foodStorageTest.getListOfIngredientsAlphabetically();

    assertTrue(iterator.hasNext(), "The iterator should have at least one ingredient.");
    assertEquals("Avocado", iterator.next(), "The first ingredient should be 'Avocado'.");
    assertTrue(iterator.hasNext(), "The iterator should have a second ingredient.");
    assertEquals("Lemon", iterator.next(), "The second ingredient should be 'Lemon'.");
  }

  /**
   * Negative test that checks that the list of ingredient names
   * is not returned in the wrong alphabetical order
   * when retrieving a list of ingredients.
   */

  @Test
  void getListOfIngredientsAlphabeticallyNegativeTest() {
    foodStorageTest.addIngredient("Avocado", "Vegetable", 1, "kg", 5.50, LocalDate.of(2028, 1, 1));
    foodStorageTest.addIngredient("Lemon", "Fruit", 1, "kg", 7.50, LocalDate.of(2024, 1, 1));

    Iterator<String> iterator = foodStorageTest.getListOfIngredientsAlphabetically();

    assertTrue(iterator.hasNext(), "The iterator should have at least one ingredient.");
    assertNotEquals("Lemon", iterator.next(), "The first ingredient should not be 'Lemon'. It should be 'Avocado'.");
    assertTrue(iterator.hasNext(), "The iterator should have a second ingredient.");
    assertNotEquals("Avocado", iterator.next(), "The second ingredient should not be 'Avocado'. It should be 'Lemon'.");
  }

  /**
   * Positive test that checks that the correct list of
   * expired ingredients is returned.
   */
  @Test
  void getListOfExpiredIngredientsPositiveTest() {
    foodStorageTest.addIngredient("Chocolate", "Sweet", 1, "kg", 10.50, LocalDate.of(2015, 1, 1));
    foodStorageTest.addIngredient("Bread", "Baked goods", 1, "kg", 7.50, LocalDate.of(2011, 1, 1));

    Iterator<String> iterator = foodStorageTest.getListOfExpiredIngredients();

    assertTrue(iterator.hasNext(), "The iterator should have at least one expired ingredient.");
    assertEquals("Chocolate", iterator.next(), "First expired ingredient should be 'Chocolate'.");
    assertTrue(iterator.hasNext(), "The iterator should have a second expired ingredient.");
    assertEquals("Bread", iterator.next(), "Second expired ingredient should be 'Bread'.");
  }

  /**
   * Negative test that checks that unexpired ingredients
   * are not included in the list of expired ingredients.
   */
  @Test
  void getListOfExpiredIngredientsNegativeTest() {
    foodStorageTest.addIngredient("Chocolate", "Sweet", 1, "kg", 10.50, LocalDate.of(2029, 1, 1));
    foodStorageTest.addIngredient("Bread", "Baked goods", 1, "kg", 7.50, LocalDate.of(2025, 1, 1));

    Iterator<String> iterator = foodStorageTest.getListOfExpiredIngredients();

    assertFalse(iterator.hasNext(), "The iterator should not have any expired ingredients.");
  }

  /**
   * Positive test that checks that the correct list of
   * ingredients from a given expiration date is returned.
   */

  @Test
  void getListOfIngredientsByExpirationDatePositiveTest()
  {
    foodStorageTest.addIngredient("Strawberry", "Berry", 1, "kg", 5.50, LocalDate.of(2021, 1, 1));
    foodStorageTest.addIngredient("Orange", "Fruit", 1, "kg", 7.50, LocalDate.of(2021, 1, 1));

    Iterator<String> iterator = foodStorageTest.getListOfIngredientsByExpirationDate(LocalDate.of(2021, 1, 1));

    assertTrue(iterator.hasNext(), "The iterator should have at least one ingredient with the expiration date of 2021-01-01.");
    assertEquals("Strawberry", iterator.next(), "First expired ingredient should be 'Strawberry'.");
    assertTrue(iterator.hasNext(), "The iterator should have a second ingredient with the expiration date of 2021-01-01.");
    assertEquals("Orange", iterator.next(), "Second expired ingredient should be 'Orange'.");
  }

  /**
   * Negative test that checks that unexpired ingredients
   * are not returned when retrieving a list of expired ingredients
   * for a given expiration date.
   */

  @Test
  void getListOfIngredientsByExpirationDateNegativeTest() {

    foodStorageTest.addIngredient("Strawberry", "Berry", 1, "kg", 5.50, LocalDate.of(2026, 1, 1));
    foodStorageTest.addIngredient("Orange", "Fruit", 1, "kg", 7.50, LocalDate.of(2026, 1, 1));

    Iterator<String> iterator = foodStorageTest.getListOfIngredientsByExpirationDate(LocalDate.of(2006, 1, 1));

    assertFalse(iterator.hasNext(), "The iterator should not have any ingredients with the expiration date of 2006-01-01.");
  }

    /**
     * Positive test that checks that the correct total value of all ingredients
     * is returned from the storage.
     */
  @Test
  void getValueOfAllIngredientsPositiveTest() {
    foodStorageTest.addIngredient("Bacon", "Meat", 13, "kg", 25.50, LocalDate.of(2026, 1, 1));
    foodStorageTest.addIngredient("Milk", "Dairy", 5, "kg", 47.50, LocalDate.of(2026, 1, 1));

    double expectedTotalValue = (13 * 25.50) + (5 * 47.50); // Total value of ingredients

    double actualTotalValue = foodStorageTest.getValueOfAllIngredients();

    assertEquals(expectedTotalValue, actualTotalValue, "The total value of all ingredients should match the expected value.");
  }

  /**
   * Negative test that checks that an incorrect total value of all ingredients
   * is not returned from the storage.
   */
  @Test
  void getValueOfAllIngredientsNegativeTest() {
    foodStorageTest.addIngredient("Bacon", "Meat", 13, "kg", 25.50, LocalDate.of(2026, 1, 1));
    foodStorageTest.addIngredient("Milk", "Dairy", 5, "kg", 47.50, LocalDate.of(2026, 1, 1));

    double expectedTotalValue = (13 * 25.50) + (5 * 47.50); // Total value of ingredients

    double actualTotalValue = foodStorageTest.getValueOfAllIngredients();

    double incorrectValue = expectedTotalValue + 100.0; // This is a value we know should not match

    assertNotEquals(incorrectValue, actualTotalValue, "The total value should not equal an incorrect value.");
  }

  /**
   * Positive test that checks that the correct total value of all expired ingredients
   * is returned from the storage.
   */
  @Test
  void getValueOfExpiredIngredientsPositiveTest() {
    foodStorageTest.addIngredient("Chocolate", "Sweet", 2, "kg", 10.00, LocalDate.of(2020, 1, 1));
    foodStorageTest.addIngredient("Bread", "Baked goods", 1, "kg", 3.50, LocalDate.of(2020, 1, 1));

    double expectedTotalValue = (2 * 10.00) + (1 * 3.50);

    double actualTotalValue = foodStorageTest.getValueOfExpiredIngredients();

    assertEquals(expectedTotalValue, actualTotalValue, "The total value of expired ingredients should match the expected value.");
  }

  /**
   * Negative test that checks that the value of unexpired
   * ingredients is not included when retrieving the value
   * of all expired ingredients from the storage.
   */
  @Test
  void getValueOfExpiredIngredientsNegativeTest() {
    foodStorageTest.addIngredient("Chocolate", "Sweet", 2, "kg", 10.00, LocalDate.of(2025, 1, 1));
    foodStorageTest.addIngredient("Bread", "Baked goods", 1, "kg", 3.50, LocalDate.of(2025, 6, 1));

    double expectedTotalValue = 0.0;

    double actualTotalValue = foodStorageTest.getValueOfExpiredIngredients();

    assertEquals(expectedTotalValue, actualTotalValue, "The total value of expired ingredients should be 0.");
  }
}