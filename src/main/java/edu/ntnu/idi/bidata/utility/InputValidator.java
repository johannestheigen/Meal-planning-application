package edu.ntnu.idi.bidata.utility;

import edu.ntnu.idi.bidata.items.Ingredient;
import edu.ntnu.idi.bidata.register.FoodStorage;
import edu.ntnu.idi.bidata.register.RecipeBook;
import java.util.Iterator;

/**
 *<p>This class is responsible for validating user input.</p>
 * <p>It checks if the user input is valid or not.</p>
 * <p>It checks if the user input is in the correct format or not.</p>
 *
 *
 * @author Johannes Nupen Theigen
 * @version 0.0.4
 * @since 11.21.2024
 */
public class InputValidator {


  /**
   * <p>Checks if the user wants to exit the program.</p>

   * @param userInput The user input
   * @return true if the user wants to exit the program, false otherwise
   *
   *<p>
       <b>Example of usage:</b>
       <pre><code>InputValidator inputValidator = new InputValidator();</code></pre>
   *</p>
   */
  public boolean isExiting(String userInput) {
    return userInput.equalsIgnoreCase("y");
  }

  /**
   * <p>Checks if the user wants to abort the operation.</p>
   * <p>For example, when the user is asked if
   * he wants to abort the operation of adding a new ingredient.</p>

   * @param userInput The user input
   * @return true if the user wants to abort the operation, false otherwise
   *
     <p>
       <b>Example of usage:</b>
       <pre><code>InputValidator inputValidator = new InputValidator();</code></pre>
       </p>
   */
  public boolean isAbortOperation(String userInput) {
    return userInput.equalsIgnoreCase("n");
  }

  /**
   * <p>Checks if an ingredient exists in the food storage.</p>

   * @param foodStorage The food storage
   * @param name The name of the ingredient
   * @return true if the ingredient exists, false otherwise
   *
     <p>
       <b>Example of usage:</b>
       <pre><code>InputValidator inputValidator = new InputValidator();</code></pre>
       </p>
   */
  public boolean ingredientExists(FoodStorage foodStorage, String name) {
    return foodStorage.getIngredient(name) != null;
  }

  /**
   * <p>Checks if an ingredient does not exist in the food storage.</p>

   * @param foodStorage The food storage
   * @param name The name of the ingredient
   * @return true if the ingredient does not exist, false otherwise
   *
     <p>
       <b>Example of usage:</b>
       <pre><code>InputValidator inputValidator = new InputValidator();</code></pre>
       </p>
   */
  public boolean ingredientNotExists(FoodStorage foodStorage, String name) {
    return foodStorage.getIngredient(name) == null;
  }

  /**
   * <p>Checks if an ingredient was reduced.</p>

   * @param foodStorage The food storage
   * @param name The name of the ingredient
   * @param amount The amount of the ingredient
   * @return true if the ingredient was reduced, false otherwise
   *
     <p>
       <b>Example of usage:</b>
       <pre><code>InputValidator inputValidator = new InputValidator();</code></pre>
       </p>
   */
  public boolean ingredientWasReduced(FoodStorage foodStorage, String name, double amount) {
    return foodStorage.getIngredient(name) != null;
  }

  /**
   * <p>Checks if a recipe exists in the recipe book.</p>

   * @param recipeBook The recipe book
   * @param name The name of the recipe
   * @return true if the recipe exists, false otherwise
   *
     <p>
       <b>Example of usage:</b>
       <pre><code>InputValidator inputValidator = new InputValidator();</code></pre>
       </p>
   */
  public boolean recipeExists(RecipeBook recipeBook, String name) {
    return recipeBook.getRecipe(name) != null;
  }

  /**
   * <p>Checks if a recipe does not exist in the recipe book.</p>

   * @param value The value to check
   * @return true if the recipe does not exist, false otherwise
   *
     <p>
       <b>Example of usage:</b>
       <pre><code>InputValidator inputValidator = new InputValidator();</code></pre>
       </p>
   */
  public boolean isPositiveInteger(int value) {
    return value > 0;
  }

  /**
   * <p>Checks if a value is a positive double.</p>

   * @param value The value to check
   * @return true if the value is a positive double, false otherwise
   *
     <p>
       <b>Example of usage:</b>
       <pre><code>InputValidator inputValidator = new InputValidator();</code></pre>
       </p>
   */
  public boolean isPositiveDouble(double value) {
    return value > 0.0;
  }

  /**
   * <p>Checks if a value is a non-empty string.</p>

   * @param value The value to check
   * @return true if the value is a non-empty string, false otherwise
   *
     <p>
       <b>Example of usage:</b>
       <pre><code>InputValidator inputValidator = new InputValidator();</code></pre>
       </p>
   */
  public boolean isNonEmptyString(String value) {
    return value != null && !value.trim().isEmpty();
  }

  /**
   * <p>Checks if a value is a valid unit.</p>

   * @param unit The unit to check
   * @return true if the unit is valid, false otherwise
   *
     <p>
       <b>Example of usage:</b>
       <pre><code>InputValidator inputValidator = new InputValidator();</code></pre>
       </p>
   */
  public boolean isValidUnit(String unit) {
    return unit != null
        && (unit.equalsIgnoreCase("g")
        || unit.equalsIgnoreCase("kg")
        || unit.equalsIgnoreCase("ml")
        || unit.equalsIgnoreCase("l")
        || unit.equalsIgnoreCase("pcs"));
  }

  /**
   * <p> Checks if the user has sufficient quantity of an ingredient in the food storage.</p>
   *

   * @param foodStorage The food storage
   * @param ingredientName The name of the ingredient
   * @param requiredQuantity The required quantity of the ingredient
   * @return true if the user has sufficient quantity of the ingredient, false otherwise
   *
     <p>
        <b>Example of usage:</b>
        <pre><code>InputValidator inputValidator = new InputValidator();</code></pre>
        </p>
   */
  public boolean hasSufficientQuantity(FoodStorage foodStorage,
                                       String ingredientName, double requiredQuantity) {
    Ingredient availableIngredient = foodStorage.getIngredient(ingredientName);
    return availableIngredient.getQuantity() >= requiredQuantity;
  }

  /**
   * <p>Checks if all ingredients are valid when a user
   * wants to cook a recipe.</p>

   * @param foodStorage The food storage
   * @param ingredients The ingredients
   * @return true if all ingredients are valid, false otherwise
     <p>
       <b>Example of usage:</b>
       <pre><code>InputValidator inputValidator = new InputValidator();</code></pre>
       </p>
   */
  public boolean allIngredientsValid(FoodStorage foodStorage, Iterator<Ingredient> ingredients) {
    while (ingredients.hasNext()) {
      Ingredient ingredient = ingredients.next();
      String ingredientName = ingredient.getName();
      double requiredQuantity = ingredient.getQuantity();

      if (!ingredientExists(foodStorage, ingredientName)) {
        return false;
      }
      if (!hasSufficientQuantity(foodStorage, ingredientName, requiredQuantity)) {
        return false;
      }
    }
    return true;
  }
}