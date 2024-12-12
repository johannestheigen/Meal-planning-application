/**
 * <h1>Unit Tests for the <code>FoodStorage</code> Class</h1>
 *
 * <p>The purpose of this test class is to validate the functionality of the <code>FoodStorage</code> class
 * through both positive and negative test cases. The following methods are tested:</p>
 */

package edu.ntnu.idi.bidata.register;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import edu.ntnu.idi.bidata.items.Ingredient;
import java.time.LocalDate;
import java.util.Iterator;
import org.junit.jupiter.api.*;

class FoodStorageTest {

  FoodStorage foodStorageTest;

  /**
   * <p>Initializes a shared instance of <code>FoodStorage</code> to be used
   * throughout the entire test.</p>
   */

  @BeforeEach
  void setUp() {
    foodStorageTest = new FoodStorage();
  }

  /**
   * <p>Positive test which checks that an ingredient is added to the storage
   * with the correct name, and that its quantity increases when a duplicate
   * ingredient is added.</p>
   */
  @Test
  void addIngredientPositiveTest() {
    foodStorageTest.addIngredient("Tomato",  1, "kg", 15.50, LocalDate.of(2024, 12, 1));
    foodStorageTest.addIngredient("Tomato", 1, "kg", 15.50, LocalDate.of(2024, 12, 1));

    Ingredient addedIngredient = foodStorageTest.getIngredient("Tomato");

    assertNotNull(addedIngredient, "Expected the ingredient 'Tomato' to exist in the storage after addition, but it was found to be null.");

    assertEquals("Tomato", addedIngredient.getName(), "Expected the name of the ingredient to be 'Tomato', but found: " + addedIngredient.getName());

    assertEquals(2, addedIngredient.getQuantity(), "Expected the quantity of 'Tomato' to be 2 after duplication, but found: " + addedIngredient.getQuantity());
  }

  /**
   * <p Negative test which checks if an ingredient is added to the storage
   *  with the incorrect name and quantity.</p>
   * </p>
   */
  @Test
  void addIngredientNegativeTest() {
    foodStorageTest.addIngredient("Tomato", 1, "kg", 15.50, LocalDate.of(2024, 12, 1));

    assertNotEquals("Tomata", foodStorageTest.getIngredient("Tomato").getName(), "The name should not be 'Tomata'.");

    assertNotNull(foodStorageTest.getIngredient("Tomato"), "Expected the ingredient 'Tomato' to exist in storage, but it was found to be null.");

    assertNotEquals(0, foodStorageTest.getIngredient("Tomato").getQuantity(), "Expected the quantity of 'Tomato' to be greater than 0, but found: " + foodStorageTest.getIngredient("Tomato").getQuantity());
  }

  /**
   * Positive test that checks if an ingredient is reduced correctly
   * and that it remains in storage after the reduction.
   */
  @Test
  void updateQuantityPositiveTest() {
    foodStorageTest.addIngredient("Apple",  2, "kg", 4.50, LocalDate.of(2024, 11, 1));

    foodStorageTest.reduceQuantity("Apple", 1);

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
  void updateQuantityNegativeTest() {
    foodStorageTest.addIngredient("Apple", 1, "kg", 4.50, LocalDate.of(2024, 11, 1));

    foodStorageTest.reduceQuantity("Apple", 1);

    Ingredient removedIngredient = foodStorageTest.getIngredient("Apple");
    assertNull(removedIngredient, "Expected 'Apple' to be null after reduction, but found: " + removedIngredient);
  }

  /**
   * Positive test that checks if an ingredient's quantity is properly updated.
   */
  @Test
  void updatePricePositiveTest() {
    foodStorageTest.addIngredient("Banana",1,"kg",5.50,LocalDate.of(2025,1,1));

    Ingredient ingredientBeforeUpdate = foodStorageTest.getIngredient("Banana");
    assertEquals(5.50,ingredientBeforeUpdate.getPrice(),"Expected the price of the ingredient to be 5.50 before the update");

    foodStorageTest.updatePrice("Banana",6.50);

    Ingredient updatedIngredient = foodStorageTest.getIngredient("Banana");

    assertEquals(6.50,updatedIngredient.getPrice(),"Expected the price of the ingredient to be 6.50 after the update");
  }

  /**
   * Negative test that checks if an ingredient's price is not wrongly updated.
   */
  @Test
  void updatePriceNegativeTest() {
    foodStorageTest.addIngredient("Banana",1,"kg",5.50,LocalDate.of(2025,1,1));

    Ingredient ingredientBeforeUpdate = foodStorageTest.getIngredient("Banana");
    assertEquals(5.50,ingredientBeforeUpdate.getPrice(),"Expected the price of the ingredient to be 5.50 before the update");

    foodStorageTest.updatePrice("Banana",3.50);

    Ingredient updatedIngredient = foodStorageTest.getIngredient("Banana");

    assertEquals(3.50,updatedIngredient.getPrice(),"Expected the price of the ingredient to be 3.50 after the update");

    assertNotEquals(5.50, updatedIngredient.getPrice(), "Price should no longer be '5.50' after update");
  }

  @Test
  void updateUnitPositiveTest() {
    foodStorageTest.addIngredient("Banana",1,"kg",5.50,LocalDate.of(2025,1,1));

    Ingredient ingredientBeforeUpdate = foodStorageTest.getIngredient("Banana");
    assertEquals("kg",ingredientBeforeUpdate.getUnit(),"Expected the unit of the ingredient to be 'kg' before the update");

    foodStorageTest.updateUnit("Banana","g");

    Ingredient updatedIngredient = foodStorageTest.getIngredient("Banana");

    assertEquals("g",updatedIngredient.getUnit(),"Expected the unit of the ingredient to be 'g' after the update");
  }

  @Test
  void updateUnitNegativeTest() {
    foodStorageTest.addIngredient("Banana",1,"kg",5.50,LocalDate.of(2025,1,1));

    Ingredient ingredientBeforeUpdate = foodStorageTest.getIngredient("Banana");
    assertEquals("kg",ingredientBeforeUpdate.getUnit(),"Expected the unit of the ingredient to be 'kg' before the update");

    foodStorageTest.updateUnit("Banana","g");

    Ingredient updatedIngredient = foodStorageTest.getIngredient("Banana");

    assertEquals("g",updatedIngredient.getUnit(),"Expected the unit of the ingredient to be 'g' after the update");

    assertNotEquals("kg", updatedIngredient.getUnit(), "Unit should no longer be 'kg' after update");
  }

  /**
   * <p>Positive test that checks if an ingredient's expiration date is properly updated.</p>
   */
  @Test
  void isIngredientExistPositiveTest() {
    foodStorageTest.addIngredient("Banana",1,"kg",5.50,LocalDate.of(2025,1,1));
    assertNotNull(foodStorageTest.getIngredient("Banana"),"Expected the ingredient to exist in the storage");
  }

  /**
   * <p>Negative test that checks if an ingredient's expiration date is not wrongly updated.</p>
   */
  @Test
  void isIngredientExistNegativeTest() {
    foodStorageTest.addIngredient("Banana",1,"kg",5.50,LocalDate.of(2025,1,1));
    assertNull(foodStorageTest.getIngredient("Apple"),"Expected the ingredient not to exist in the storage");
  }

  /**
   * <p>
   *   Positive test that checks if an ingredient has been properly updated.
   * </p>
   */
  @Test
  void isIngredientQuantityUpdatedPositiveTest() {
    foodStorageTest.addIngredient("Banana",1,"kg",5.50,LocalDate.of(2025,1,1));
    foodStorageTest.getIngredient("Banana").setQuantity(2);
    assertEquals(2, foodStorageTest.getIngredient("Banana").getQuantity(),"Expected the quantity of the ingredient to be updated");
  }

  /**
   * <p>Negative test that checks if an ingredient's quantity is not wrongly updated.</p>
   */
  @Test
  void isIngredientQuantityUpdatedNegativeTest() {
    foodStorageTest.addIngredient("Banana",1,"kg",5.50,LocalDate.of(2025,1,1));
    foodStorageTest.getIngredient("Banana").setQuantity(2);
    assertNotEquals(-1, foodStorageTest.getIngredient("Banana").getQuantity(),"Expected the quantity of the ingredient not to be updated");
  }

  /**
   * <p>
   *   Positive test that checks if an ingredient's with the same name
   *   is given the name of the ingredient with the same name and that the
   *   older ingredient is renamed with its name and the old expiration date.
   * </p>
   */

  /**
   * <p>Positive test that checks that the correct list of
   * ingredients is returned after adding ingredients to the storage.</p>
   */
  @Test
  void getListOfIngredientsPositiveTest() {
    foodStorageTest.addIngredient("Tomato", 1, "kg", 2.50, LocalDate.of(2028, 1, 1));
    foodStorageTest.addIngredient("Apple", 1, "kg", 1.50, LocalDate.of(2025, 1, 1));

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
   * <p>Negative test that checks that incorrect names are not returned
   *  when retrieving a list of added ingredients from storage.</p>
   */
  @Test
  void getListOfIngredientsNegativeTest() {
    foodStorageTest.addIngredient("Tomato", 1, "kg", 2.50, LocalDate.of(2028, 1, 1));
    foodStorageTest.addIngredient("Apple", 1, "kg", 1.50, LocalDate.of(2025, 1, 1));

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
   * <p>Positive test that checks that the correct list of
   * ingredients is returned in alphabetical order
   * after adding ingredients to the storage.</p>
   */
  @Test
  void getListOfIngredientsAlphabeticallyPositiveTest() {
    foodStorageTest.addIngredient("Avocado",  1, "kg", 5.50, LocalDate.of(2028, 1, 1));
    foodStorageTest.addIngredient("Lemon", 1, "kg", 7.50, LocalDate.of(2024, 1, 1));

    Iterator<String> iterator = foodStorageTest.getListOfIngredientsAlphabetically();

    assertTrue(iterator.hasNext(), "The iterator should have at least one ingredient.");
    assertEquals("Avocado", iterator.next(), "The first ingredient should be 'Avocado'.");
    assertTrue(iterator.hasNext(), "The iterator should have a second ingredient.");
    assertEquals("Lemon", iterator.next(), "The second ingredient should be 'Lemon'.");
  }

  /**
   * <p>Negative test that checks that the list of ingredient names
   * is not returned in the wrong alphabetical order
   * when retrieving a list of ingredients.</p>
   */

  @Test
  void getListOfIngredientsAlphabeticallyNegativeTest() {
    foodStorageTest.addIngredient("Avocado",  1, "kg", 5.50, LocalDate.of(2028, 1, 1));
    foodStorageTest.addIngredient("Lemon",  1, "kg", 7.50, LocalDate.of(2024, 1, 1));

    Iterator<String> iterator = foodStorageTest.getListOfIngredientsAlphabetically();

    assertTrue(iterator.hasNext(), "The iterator should have at least one ingredient.");
    assertNotEquals("Lemon", iterator.next(), "The first ingredient should not be 'Lemon'. It should be 'Avocado'.");
    assertTrue(iterator.hasNext(), "The iterator should have a second ingredient.");
    assertNotEquals("Avocado", iterator.next(), "The second ingredient should not be 'Avocado'. It should be 'Lemon'.");
  }

  /**
   * <p>Positive test that checks that the correct list of
   * expired ingredients is returned.</p>
   */
  @Test
  void getListOfExpiredIngredientsPositiveTest() {
    foodStorageTest.addIngredient("Chocolate",  1, "kg", 10.50, LocalDate.of(2015, 1, 1));
    foodStorageTest.addIngredient("Bread",  1, "kg", 7.50, LocalDate.of(2011, 1, 1));

    Iterator<String> iterator = foodStorageTest.getListOfExpiredIngredients();

    assertTrue(iterator.hasNext(), "The iterator should have at least one expired ingredient.");
    assertEquals("Chocolate", iterator.next(), "First expired ingredient should be 'Chocolate'.");
    assertTrue(iterator.hasNext(), "The iterator should have a second expired ingredient.");
    assertEquals("Bread", iterator.next(), "Second expired ingredient should be 'Bread'.");
  }

  /**
   * <p>Negative test that checks that unexpired ingredients
   * are not included in the list of expired ingredients.</p>
   */
  @Test
  void getListOfExpiredIngredientsNegativeTest() {
    foodStorageTest.addIngredient("Chocolate", 1, "kg", 10.50, LocalDate.of(2029, 1, 1));
    foodStorageTest.addIngredient("Bread", 1, "kg", 7.50, LocalDate.of(2025, 1, 1));

    Iterator<String> iterator = foodStorageTest.getListOfExpiredIngredients();

    assertFalse(iterator.hasNext(), "The iterator should not have any expired ingredients.");
  }

  /**
   * <p>
   * Positive test that checks that the correct list of
   * ingredients from a given expiration date is returned.
   * </p>
   */
  @Test
  void getListOfIngredientsByExpirationDatePositiveTest()
  {
    foodStorageTest.addIngredient("Strawberry",  1, "kg", 5.50, LocalDate.of(2021, 1, 1));
    foodStorageTest.addIngredient("Orange",  1, "kg", 7.50, LocalDate.of(2021, 1, 1));

    Iterator<String> iterator = foodStorageTest.getListOfIngredientsByExpirationDate(LocalDate.of(2021, 1, 1));

    assertTrue(iterator.hasNext(), "The iterator should have at least one ingredient with the expiration date of 2021-01-01.");
    assertEquals("Strawberry", iterator.next(), "First expired ingredient should be 'Strawberry'.");
    assertTrue(iterator.hasNext(), "The iterator should have a second ingredient with the expiration date of 2021-01-01.");
    assertEquals("Orange", iterator.next(), "Second expired ingredient should be 'Orange'.");
  }

  /**
   * <p>
   * Negative test that checks that unexpired ingredients
   * are not returned when retrieving a list of expired ingredients
   * for a given expiration date.
   * </p>
   */
  @Test
  void getListOfIngredientsByExpirationDateNegativeTest() {

    foodStorageTest.addIngredient("Strawberry", 1, "kg", 5.50, LocalDate.of(2026, 1, 1));
    foodStorageTest.addIngredient("Orange",  1, "kg", 7.50, LocalDate.of(2026, 1, 1));

    Iterator<String> iterator = foodStorageTest.getListOfIngredientsByExpirationDate(LocalDate.of(2006, 1, 1));

    assertFalse(iterator.hasNext(), "The iterator should not have any ingredients with the expiration date of 2006-01-01.");
  }

    /**
     * <p>
     * Positive test that checks that the correct total value of all ingredients
     * is returned from the storage.
     * </p>
     */
  @Test
  void getValueOfAllIngredientsPositiveTest() {
    foodStorageTest.addIngredient("Bacon",  13, "kg", 25.50, LocalDate.of(2026, 1, 1));
    foodStorageTest.addIngredient("Milk",  5, "kg", 47.50, LocalDate.of(2026, 1, 1));

    double expectedTotalValue = (13 * 25.50) + (5 * 47.50); // Total value of ingredients

    double actualTotalValue = foodStorageTest.getValueOfAllIngredients();

    assertEquals(expectedTotalValue, actualTotalValue, "The total value of all ingredients should match the expected value.");
  }

  /**
   * <p>
   * Negative test that checks that an incorrect total value of all ingredients
   * is not returned from the storage.
   * </p>
   */
  @Test
  void getValueOfAllIngredientsNegativeTest() {
    foodStorageTest.addIngredient("Bacon", 13, "kg", 25.50, LocalDate.of(2026, 1, 1));
    foodStorageTest.addIngredient("Milk",  5, "kg", 47.50, LocalDate.of(2026, 1, 1));

    double expectedTotalValue = (13 * 25.50) + (5 * 47.50); // Total value of ingredients

    double actualTotalValue = foodStorageTest.getValueOfAllIngredients();

    double incorrectValue = expectedTotalValue + 100.0; // This is a value we know should not match

    assertNotEquals(incorrectValue, actualTotalValue, "The total value should not equal an incorrect value.");
  }

  /**
   * <p>
   * Positive test that checks that the correct total value of all expired ingredients
   * is returned from the storage.
   * </p>
   */
  @Test
  void getValueOfExpiredIngredientsPositiveTest() {
    foodStorageTest.addIngredient("Chocolate", 2, "kg", 10.00, LocalDate.of(2020, 1, 1));
    foodStorageTest.addIngredient("Bread",  1, "kg", 3.50, LocalDate.of(2020, 1, 1));

    double expectedTotalValue = (2 * 10.00) + (1 * 3.50);

    double actualTotalValue = foodStorageTest.getValueOfExpiredIngredients();

    assertEquals(expectedTotalValue, actualTotalValue, "The total value of expired ingredients should match the expected value.");
  }

  /**
   * <p>
   * Negative test that checks that the value of unexpired
   * ingredients is not included when retrieving the value
   * of all expired ingredients from the storage.
   * </p>
   */
  @Test
  void getValueOfExpiredIngredientsNegativeTest() {
    foodStorageTest.addIngredient("Chocolate", 2, "kg", 10.00, LocalDate.of(2025, 1, 1));
    foodStorageTest.addIngredient("Bread", 1, "kg", 3.50, LocalDate.of(2025, 6, 1));

    double expectedTotalValue = 0.0;

    double actualTotalValue = foodStorageTest.getValueOfExpiredIngredients();

    assertEquals(expectedTotalValue, actualTotalValue, "The total value of expired ingredients should be 0.");
  }
}