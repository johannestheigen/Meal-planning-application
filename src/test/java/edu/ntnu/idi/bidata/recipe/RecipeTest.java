package edu.ntnu.idi.bidata.recipe;

import static org.junit.jupiter.api.Assertions.*;
import edu.ntnu.idi.bidata.items.Ingredient;
import org.junit.jupiter.api.*;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

class RecipeTest {

  Map<String, Ingredient> requiredIngredients;
  Recipe testRecipe;

  /**
   * <p>
   *   Sets up the test by creating a new Recipe object and a map of required ingredients.
   * </p>
   */
  @BeforeEach
  void setUp() {
    requiredIngredients = new HashMap<>();
    requiredIngredients.put("Flour", new Ingredient(200.0, "kg"));
    requiredIngredients.put("Milk", new Ingredient(100.0,"kg"));
    testRecipe = new Recipe(
        "Pancakes",
        "A delicious breakfast treat",
        "1. Mix flour, eggs, and milk. Cook on a hot pan until golden.",
        requiredIngredients);
  }

  /**
   * <p>Positive test that checks if the name of the recipe is set correctly.</p>
   */
  @Test
  void setNamePositiveTest() {
    testRecipe.setName("Delicious pancakes");
    assertEquals("Delicious pancakes", testRecipe.getName(), "The output should be 'Delicious pancakes'");
  }

  /**
   * <p>Negative test that checks if the name of the recipe is not incorrectly
   * set to a different name</p>
   */
  @Test
  void setNameNegativeTest() {
    testRecipe.setName("Pancakes");
    assertNotEquals("Blueberry Pie", testRecipe.getName(), "The output should not be 'Blueberry Pie'");
  }

  /**
   * <p>Positive test that checks if the description of the recipe is set correctly.</p>
   */
  @Test
  void setDescriptionPositiveTest() {
    testRecipe.setDescription("A delicious treat you can enjoy for breakfast");
    assertEquals("A delicious treat you can enjoy for breakfast",
        testRecipe.getDescription(), "The output should be" +
            " 'A delicious treat you can enjoy for breakfast'");
  }

  /**
   * <p>Negative test that checks if the description of the recipe is not
   * incorrectly set to a different description</p>
   */
  @Test
  void setDescriptionNegativeTest() {
    testRecipe.setDescription("A delicious treat you can enjoy for breakfast");
    assertNotEquals("A nice dinner",
        testRecipe.getDescription(), "The output should not be" +
            " 'A nice dinner'");
  }

  /**
   * <p>Positive test that checks if the instruction of the recipe is set correctly.</p>
   */
  @Test
  void setInstructionPositiveTest() {
    testRecipe.setInstruction("1. Mix flour.");

    assertEquals("1. Mix flour.",
        testRecipe.getInstruction(),
        "The output should be '1. Mix flour.'");
  }

  /**
   * <p>Negative test that checks if the instruction of the recipe is not set
   * incorrectly to a different instruction</p>
   * </p>
   */
  @Test
  void setInstructionNegativeTest() {
    testRecipe.setInstruction("1. Mix flour.");

    assertNotEquals("1. Mix milk.",
        testRecipe.getInstruction(),
        "The output should not be '1. Mix milk.'");
  }

  /**
   * <p>Positive test that checks if the Iterator retrieves the correct required ingredients
   *  from the recipe with the correct quantity and unit</p>
   */
  @Test
  void getRequiredIngredientsPositiveTest() {
    Iterator<Map.Entry<String, Ingredient>> iterator = testRecipe.getRequiredIngredients();

    Map.Entry<String, Ingredient> firstEntry = iterator.next();
    assertEquals("Flour", firstEntry.getKey(), "First ingredient should be 'Flour'.");
    assertEquals(200.0, firstEntry.getValue().getQuantity(), "Quantity for should be '200.0'.");
    assertEquals("kg", firstEntry.getValue().getUnit(), "Unit for should be 'kg'.");

    Map.Entry<String, Ingredient> secondEntry = iterator.next();
    assertEquals("Milk", secondEntry.getKey(), "Second ingredient should be 'Milk'.");
    assertEquals(100.0, secondEntry.getValue().getQuantity(), "Quantity for should be '100.0'.");
    assertEquals("kg", secondEntry.getValue().getUnit(), "Unit for should be 'kg'.");

    assertFalse(iterator.hasNext(), "The iterator should not have more than two ingredients.");
  }

  /**
   * <p>Negative test that checks if the Iterator does not retrieve incorrect required ingredients
   *  from the recipe with the incorrect quantity and unit</p>
   */
  @Test
  void getRequiredIngredientsNegativeTest() {
    requiredIngredients.clear();
    requiredIngredients.put("Sugar", new Ingredient(50.0, "g"));
    requiredIngredients.put("Butter", new Ingredient(30.0, "g"));
    testRecipe.setRequiredIngredients(requiredIngredients);

    Iterator<Map.Entry<String, Ingredient>> iterator = testRecipe.getRequiredIngredients();

    Map.Entry<String, Ingredient> firstEntry = iterator.next();
    assertNotEquals("Flour", firstEntry.getKey(), "First ingredient should not be 'Flour'.");
    assertNotEquals(200.0, firstEntry.getValue().getQuantity(), "Quantity should not be '200.0'.");
    assertNotEquals("kg",firstEntry.getValue().getUnit(),"Unit should not be 'kg'.");

    Map.Entry<String, Ingredient> secondEntry = iterator.next();
    assertNotEquals("Milk", secondEntry.getKey(), "Second ingredient should not be 'Milk'.");
    assertNotEquals(100.0,secondEntry.getValue().getQuantity(), "Quantity should not be '100.0'.");
    assertNotEquals("kg",secondEntry.getValue().getUnit(),"Unit should not be 'kg'");

    assertFalse(iterator.hasNext(), "The iterator should not have more than two ingredients.");
  }
}