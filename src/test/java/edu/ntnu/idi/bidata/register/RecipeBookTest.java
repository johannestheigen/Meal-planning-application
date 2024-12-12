package edu.ntnu.idi.bidata.register;

import edu.ntnu.idi.bidata.items.Ingredient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecipeBookTest {

  RecipeBook testRecipeBook;
  FoodStorage testStorage;

  /**
   * <p>Initializes a new instance of the Ingredient class before each test.</p>
   */
  @BeforeEach
  void setUp() {
    testRecipeBook = new RecipeBook();
    testStorage = new FoodStorage();
    testRecipeBook.addRecipe("Pancakes", "A delicious breakfast treat", "instruction", 1);
    testRecipeBook.addIngredientToRecipe("Pancakes", "Egg", 2, "pcs");
    testRecipeBook.addRecipe("Blueberry Pie", "A delicious pie", "instructions", 2);
    testRecipeBook.addIngredientToRecipe("Blueberry Pie", "Blueberries", 200, "g");
  }

  /**
   * <p>Positive test which checks if a recipe is added to the storage
   * with the correct name, description, instruction and serving size.</p>
   * </p>
   */
  @Test
  void addRecipePositiveTest() {
    testRecipeBook.addRecipe("Waffles", "A delicious breakfast treat", "instructions", 2);
    assertNotNull(testRecipeBook.getRecipe("Waffles"), "The recipe should be added to the storage");
    assertEquals("Waffles", testRecipeBook.getRecipe("Waffles").getName(), "The recipe name should be Waffles");
    assertEquals("A delicious breakfast treat", testRecipeBook.getRecipe("Waffles").getDescription(), "The recipe description should be A delicious breakfast treat");
    assertEquals("instructions", testRecipeBook.getRecipe("Waffles").getInstruction(), "The recipe instruction should be instructions");
    assertEquals(2, testRecipeBook.getRecipe("Waffles").getServings(), "The recipe serving size should be 2");
  }

  /**
   * <p>Negative test which checks if a recipe is added to the recipe book
   * with the incorrect name, description, instruction and serving size.</p>
   */
  @Test
  void addRecipeNegativeTest() {
    testRecipeBook.addRecipe("Pancakes", "A delicious breakfast treat", "instruction", 1);

    assertNotNull(testRecipeBook.getRecipe("Pancakes"), "The recipe should be added to the storage");
    assertNotEquals("Waffles", testRecipeBook.getRecipe("Pancakes").getName(), "The recipe name should not be Waffles");
    assertNotEquals("A delicious lunch treat", testRecipeBook.getRecipe("Pancakes").getDescription(), "The recipe description should not be A delicious lunch treat");
    assertNotEquals("instructions", testRecipeBook.getRecipe("Pancakes").getInstruction(), "The recipe instruction should not be instructions");
    assertNotEquals(2, testRecipeBook.getRecipe("Pancakes").getServings(), "The recipe serving size should not be 2");
  }

  /**
   * <p>Positive test which checks if a recipe is removed from the storage.</p>
   */
  @Test
  void removeRecipePositiveTest() {
    testRecipeBook.addRecipe("Pancakes", "A delicious breakfast treat", "instruction", 1);
    testRecipeBook.removeRecipe("Pancakes");
    assertNull(testRecipeBook.getRecipe("Pancakes"), "The recipe should be removed from the storage");
  }

  /**
   * <p>Negative test which checks if a recipe is removed from the storage.</p>
   */
  @Test
  void removeRecipeNegativeTest() {
    testRecipeBook.addRecipe("Waffles", "A delicious breakfast treat", "instruction", 1);
    testRecipeBook.removeRecipe("Pancakes");
    assertNotNull(testRecipeBook.getRecipe("Waffles"), "The recipe should not be removed from the storage");
  }

  /**
   * <p>Positive test which checks if an ingredient is added to the recipe.</p>
   */
  @Test
  void addIngredientToRecipePositiveTest() {
    testRecipeBook.addIngredientToRecipe("Pancakes", "Milk", 2, "l");

    Ingredient addedIngredient = testRecipeBook.getRecipe("Pancakes").getRequiredIngredients().next();

    assertNotNull(addedIngredient, "Expected the ingredient 'Milk' to exist in the required ingredients after addition, but it was found to be null.");

    assertEquals("Egg", addedIngredient.getName(), "Expected the name of the ingredient to be 'Milk', but found: " + addedIngredient.getName());

    assertEquals(2, addedIngredient.getQuantity(), "Expected the quantity of 'Milk' to be 2, but found: " + addedIngredient.getQuantity());
  }

  /**
   * <p>Negative test which checks if an ingredient is added to the recipe.</p>
   */
  @Test
  void addIngredientToRecipeNegativeTest() {
    testRecipeBook.addIngredientToRecipe("Pancakes", "Milk", 2, "l");

    Ingredient addedIngredient = testRecipeBook.getRecipe("Pancakes").getRequiredIngredients().next();

    assertNotNull(addedIngredient, "Expected the ingredient 'Milk' to exist in the required ingredients after addition, but it was found to be null.");

    assertNotEquals("Apple", addedIngredient.getName(), "Expected the name of the ingredient to be 'Milk', but found: " + addedIngredient.getName());

    assertNotEquals(10, addedIngredient.getQuantity(), "Expected the quantity of 'Milk' to be 2, but found: " + addedIngredient.getQuantity());
  }

  /**
   * <p>Positive test which checks if a recipe is existing in the storage.</p>
   */
  @Test
  void isRecipeExistingPositiveTest() {
    assertNotNull(testRecipeBook.getRecipe("Pancakes"), "The recipe should exist in the storage");
  }

  /**
   * <p>Negative test which checks if a recipe is existing in the storage.</p>
   */
  @Test
  void isRecipeExistingNegativeTest() {
    assertNull(testRecipeBook.getRecipe("Chocolate Cake"), "The recipe should not exist in the storage");
  }

  /**
   * <p>Positive test which checks if a recipe is updated with the correct name.</p>
   */
  @Test
  void updateServingPositiveTest() {
    testRecipeBook.updateServing("Pancakes", 2);
    assertEquals(2, testRecipeBook.getRecipe("Pancakes").getServings(), "The recipe serving size should be 2");
  }

  /**
   * <p>Negative test which checks if a recipe is updated with the incorrect name.</p>
   */
  @Test
  void updateServingNegativeTest() {
    testRecipeBook.updateServing("Pancakes", 2);
    assertNotEquals(3, testRecipeBook.getRecipe("Pancakes").getServings(), "The recipe serving size should not be 3");
  }
}