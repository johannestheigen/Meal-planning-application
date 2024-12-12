package edu.ntnu.idi.bidata.recipe;

import edu.ntnu.idi.bidata.items.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RecipeTest {

  Recipe testRecipe;

  /**
   * <p>Initializes a new instance of the Recipe class before each test.</p>
   */
  @BeforeEach
  void setUp() {
    testRecipe = new Recipe("Pancakes", "A delicious breakfast treat", "instruction", 1);
    testRecipe.addIngredient("Egg",2,"pcs");
  }

  /**
   * <p>Test that checks if a valid input of recipeName is set correctly.</p>
   */
  @Test
  void setNamePositiveTest()
  {
    testRecipe.setName("Pancakes");
    assertEquals("Pancakes", testRecipe.getName(), "The output should be Pancakes");
  }

  /**
   * <p>Test that checks that the recipeName is not incorrectly set</p>
   */
  @Test
  void setNameNegativeTest()
  {
    testRecipe.setName("Pancakes");
    assertNotEquals("Waffles", testRecipe.getName(), "The output should not be Waffles");
  }

  @Test
  void setDescriptionPositiveTest()
  {
    testRecipe.setDescription("A delicious breakfast treat");
    assertEquals("A delicious breakfast treat", testRecipe.getDescription(), "The output should be A delicious breakfast treat");
  }

  /**
   * <p>Test that checks that the recipeDescription is not incorrectly set</p>
   */
  @Test
  void setDescriptionNegativeTest()
  {
    testRecipe.setDescription("A delicious breakfast treat");
    assertNotEquals("A delicious lunch treat", testRecipe.getDescription(), "The output should not be A delicious lunch treat");
  }

  /**
   * <p>Test that checks if a valid input of instruction is set correctly.</p>
   */
  @Test
  void setInstructionPositiveTest()
  {
    testRecipe.setInstruction("instruction");
    assertEquals("instruction", testRecipe.getInstruction(), "The output should be instruction");
  }

  /**
   * <p>Test that checks that an ingredient is added to the recipe.</p>
   */
  @Test
  void addIngredientPositiveTest() {
    testRecipe.addIngredient("Egg",2,"pcs");

    Ingredient addedIngredient = testRecipe.getRequiredIngredients().next();

    assertNotNull(addedIngredient, "Expected the ingredient 'Egg' to exist in the required ingredients after addition, but it was found to be null.");

    assertEquals("Egg", addedIngredient.getName(), "Expected the name of the ingredient to be 'Egg', but found: " + addedIngredient.getName());

    assertEquals(2, addedIngredient.getQuantity(), "Expected the quantity of 'Egg' to be 2, but found: " + addedIngredient.getQuantity());
  }

  @Test
  void addIngredientNegativeTest() {
    testRecipe.addIngredient("Egg",2,"pcs");

    Ingredient addedIngredient = testRecipe.getRequiredIngredients().next();

    assertNotNull(addedIngredient, "Expected the ingredient 'Egg' to exist in the required ingredients after addition, but it was found to be null.");

    assertNotEquals("Apple", addedIngredient.getName(), "Expected the name of the ingredient to be 'Egg', but found: " + addedIngredient.getName());

    assertNotEquals(3, addedIngredient.getQuantity(), "Expected the quantity of 'Egg' to be 2, but found: " + addedIngredient.getQuantity());
  }

  @Test
  void setServingsPositiveTest() {
    testRecipe.setServings(2);
    assertEquals(2, testRecipe.getServings(), "The output should be 2");
  }

  @Test
  void setServingsNegativeTest() {
    testRecipe.setServings(2);
    assertNotEquals(3, testRecipe.getServings(), "The output should not be 3");
  }
}