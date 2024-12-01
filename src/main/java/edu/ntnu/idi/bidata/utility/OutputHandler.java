package edu.ntnu.idi.bidata.utility;

import java.time.LocalDate;
import java.util.Iterator;

/**
 * Handles all printing tasks for the user interface, organizing messages into sections
 * for easy understanding and maintainability.
 *
 * @author Johannes Nupen Theigen
 * @version 0.2.7
 * @since 12.01.2024
 */
public class OutputHandler {

  /**
   * <p>Prints the main menu of the application, which includes
   * the options to manage the food storage, recipe book, and exit the application.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>OutputHandler.printMainMenu();</code></pre>
   */

  public void printMainMenu() {
    System.out.println("""
        -------------------------------------------
        Welcome to the Meal Planning Application.
        -------------------------------------------
        Type '/storage' to manage your food storage
        Type '/recipes' to manage your recipe book
        Press 0 to exit
        """);
  }

  /**
   * <p>Prints the extended menu for managing the food storage,
   * which includes options to add, remove, and update ingredients, as well as
   * view ingredient details, lists, and values.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>OutputHandler.printFoodStorageMenu();</code></pre>
   * </p>
   */
  public void printFoodStorageMenu() {
    System.out.println("""
        Type '/add-ing' to add an ingredient
        Type '/del-ing' to remove an ingredient
        Type '/reduce-ing' to reduce the quantity of an ingredient
        Type '/edit-price' to change the price of an ingredient
        Type '/edit-unit' to change the unit of an ingredient
        Type '/find-ing' to find an ingredient and display its details
        Type '/list-ing' to view all ingredients
        Type '/sort-ing' to view ingredients in alphabetical order
        Type '/expired' to view all expired ingredients
        Type '/by-date' to view ingredients by a specific expiration date
        Type '/total-val' to print value of all ingredients
        Type '/expired-val' to print value of all expired ingredients
        Type '/main' to return to the main menu
        """);
  }

  /**
   * <p>Prints the extended menu for managing the recipe book,
   * which includes options to add, remove, and find recipes, as well as
   * list all recipes and make a recipe.</p>
   */
  public void printRecipeBookMenu() {
    System.out.println("""
        Type '/add-rec' to add a recipe
        Type '/del-rec' to remove a recipe
        Type '/edit-serv' to change the servings of a recipe
        Type '/find-rec' to find a recipe and display its details
        Type '/list-rec' to list all recipes
        Type '/check-rec' to check if a recipe can be made
        Type '/main' to return to the main menu
        """);
  }

  /**
   * <p>Prints a warning when the user wants to exit the application.</p>
   */
  public void printExitWarning() {
    System.out.println("Are you sure you want to exit? (y/n)");
  }

  /**
   * <p>Prints a warning when a user wants to perform
   * an operation that may have unintended consequences.</p>
   */
  public void printContinuationWarning() {
    System.out.println("Are you sure you want to continue? (y/n)");
  }

  /**
   * <p>Prints a message to confirm that the user has pressed any key to continue.</p>
   */
  public void printPressAnyKey() {
    System.out.println("Press any key to continue...");
  }

  /**
   * <p>Prints a message to confirm that the user has exited the application.</p>
   */
  public void printExitMessage() {
    System.out.println("Thank you for using the Meal Planning Application. Goodbye!");
  }

  /**
   * <p>Prints a message to alert the user that they have aborted an operation.</p>
   */
  public void printAbortOperationMessage() {
    System.out.println("Aborted the operation...");
  }

  /**
   * <p>Prints an error message when an invalid input is entered by the user.</p>
   */
  public void printInvalidInput(String message) {
    System.out.println("Invalid input " + message);
  }

  public void printInvalidNumberOfIngredients() {
    System.out.println("Invalid number of ingredients. Please enter a number greater than 0.");
  }

  public void printInvalidQuantity() {
    System.out.println("Invalid quantity. Please enter a number greater than 0.");
  }

  public void printInvalidUnit() {
    System.out.println("Invalid unit. Please enter kg, g, ml, l or pcs.");
  }

  /**
   * <p>Prints a message that the the price of an ingredient is invalid.</p>
   */
  public void printInvalidPrice() {
    System.out.println("Invalid price. Please enter a number greater than 0.");
  }

  /**
   * <p>Prints a message that the the expiration date of an ingredient is invalid.</p>
   */
  public void printInvalidRecipeName() {
    System.out.println("Invalid recipe name. Please enter a valid name.");
  }

  /**
   * <p>Prints a message that the the description of a recipe is invalid.</p>
   */
  public void printInvalidRecipeDescription() {
    System.out.println("Invalid recipe description. Please enter a valid description.");
  }

  /**
   * <p>Prints a message that the the instruction of a recipe is invalid.</p>
   */
  public void printInvalidRecipeInstructions() {
    System.out.println("Invalid recipe instructions. Please enter a valid instruction.");
  }

  /**
   * <p>Prints a message that the the servings of a recipe is invalid.</p>
   */
  public void printInvalidServings() {
    System.out.println("Invalid servings. Please enter a number greater than 0.");
  }

  /**
   * <p>Prints an error message when an unexpected error occurs.</p>
   * <p>This method is used to alert the user when an unexpected error occurs.</p>
   *
   * @param message the error message to be displayed.
   */
  public void printError(String message) {
    System.out.println("Unexpected error: " + message);
  }

  /**
   * <p>Prints a message when an ingredient has been successfully added.</p>
   *
   * @param name the name of the ingredient that was added
   */
  public void printAddedIngredient(String name) {
    System.out.println("Successfully added " + name + " to the storage");
  }

  /**
   * <p>Prints a message when an ingredient as an expiration
   * date that is older than the current date.</p>
   *
   * @param newExpirationDate      the new expiration date
   * @param existingExpirationDate the existing expiration date
   */
  public void printExpirationDateWarning(LocalDate newExpirationDate,
                                         LocalDate existingExpirationDate) {
    System.out.println("The new expiration date "
        + newExpirationDate + " is older than the current date "
        + existingExpirationDate + " Remember to smell and taste the ingredient before using it.");
  }

  /**
   * <p>Prints a message when an ingredient has
   * an expiration date that is newer than the current date.</p>
   *
   * @param newExpirationDate      the new expiration date
   * @param existingExpirationDate the existing expiration date
   */
  public void printNewerExpirationDateUpdate(LocalDate newExpirationDate,
                                             LocalDate existingExpirationDate) {
    System.out.println("The new expiration date " + newExpirationDate
        + " is newer than the current date " + " " + existingExpirationDate
        + "." + "Remember to check if the old ingredient is still good.");
  }

  /**
   * <p>Prints a message when an ingredient has been successfully removed.</p>
   *
   * @param name the name of the ingredient that was removed
   */


  public void printRemovedIngredient(String name) {
    System.out.println("Successfully removed " + name
        + " because the quantity was reduced to zero.");
  }

  /**
   * <p>Prints a message when the quantity of the specified ingredient
   * has been successfully updated.</p>
   *
   * @param name the name of the ingredient whose quantity was updated successfully.
   */

  public void printUpdatedQuantity(String name) {
    System.out.println("Successfully updated the quantity of " + name);
  }

  /**
   * <p>Prints a message when the price of the specified ingredient
   * has been successfully updated.</p>
   *
   * @param name the name of the ingredient whose price was updated successfully.
   */
  public void printUpdatedPrice(String name) {
    System.out.println("Successfully updated the price of " + name);
  }

  /**
   * <p>Prints a message when the unit of the specified ingredient
   * has been successfully updated.</p>
   *
   * @param name the name of the ingredient whose unit was updated successfully.
   */
  public void printUpdatedUnit(String name) {
    System.out.println("Successfully updated the unit of " + name);
  }

  /**
   * <p>Prints a message indicating whether the specified ingredient was found in the storage.</p>
   *
   * <p>If the ingredient is found (based on the provided boolean value),
   * it prints the ingredient's name.
   * If the ingredient is not found, it displays an error message.</p>
   *
   * @param ingredientName the name of the ingredient to be printed.
   * @param found          a boolean value indicating whether the ingredient was found or not.
   */
  public void printIngredient(String ingredientName, boolean found) {
    if (found) {
      System.out.println("Found: " + ingredientName);
    } else {
      System.out.println("Could not find: " + ingredientName);
    }
  }

  /**
   * <p>Prints the details of the specified ingredient.</p>
   *
   * @param ingredientName the name of the ingredient
   * @param quantity       the quantity of the ingredient
   * @param unit           the unit of the ingredient
   * @param price          the price of the ingredient
   * @param expirationDate the expiration date of the ingredient
   */
  public void printIngredientDetails(String ingredientName,
                                     double quantity, String unit,
                                     double price, LocalDate expirationDate) {
    System.out.println("Found: " + ingredientName
        + "\nQuantity: " + quantity
        + "\nUnit: " + unit
        + "\nPrice: " + price + " NOK " + " per " + unit
        + "\nExpiration Date: " + expirationDate);
  }

  /**
   * <p>Prints a list of all ingredients present in the storage.</p>
   * <p>This method takes an iterator of ingredient names and prints each one on a new line.</p>
   *
   * @param ingredientsIterator the iterator of ingredients to be printed.
   * @param hasIngredients      a boolean value indicating whether there are ingredients or not
   */
  public void printListOfIngredients(Iterator<String> ingredientsIterator, boolean hasIngredients) {
    if (hasIngredients && ingredientsIterator != null) {
      System.out.println("These are your ingredients: ");
      while (ingredientsIterator.hasNext()) {
        System.out.println(ingredientsIterator.next());
      }
    } else {
      System.out.println("There are no ingredients in the storage.");
    }
  }

  /**
   * <p>Prints a list of all ingredients present in the storage in alphabetical order.</p>
   * <p>This method takes an iterator of ingredient names, sorts them alphabetically and print
   * each one on a new line.</p>
   *
   * @param ingredientsIterator the iterator of ingredients to be printed.
   * @param hasIngredients      a boolean value indicating whether there are ingredients or not.
   */
  public void printListOfIngredientsAlphabetically(Iterator<String> ingredientsIterator,
                                                   boolean hasIngredients) {
    if (hasIngredients && ingredientsIterator != null) {
      System.out.println("These are your ingredients in alphabetical order: ");
      while (ingredientsIterator.hasNext()) {
        System.out.println(ingredientsIterator.next());
      }
    } else {
      System.out.println("No ingredients found.");
    }
  }

  /**
   * <p>Prints a list of all expired ingredients present in the storage,
   * sorted by expiration date.</p>
   *
   * <p>This method takes an iterator of ingredients
   * and filters them to display only those that are expired.
   *
   * @param ingredientsIterator   the iterator of ingredients to be printed.
   * @param hasExpiredIngredients a boolean value indicating whether there are expired ingredients.
   */

  public void printListOfExpiredIngredients(Iterator<String> ingredientsIterator,
                                            boolean hasExpiredIngredients) {
    if (hasExpiredIngredients && ingredientsIterator != null) {
      System.out.println("These are your expired ingredients:");
      while (ingredientsIterator.hasNext()) {
        System.out.println(ingredientsIterator.next());
      }
    } else {
      System.out.println("No expired ingredients found.");
    }
  }

  /**
   * <p>Prints a list of ingredients by a specific expiration date.</p>
   * <p>This method takes an iterator of ingredients
   * and filters them to display only those that are expiring on the specified date.</p>
   *
   * @param ingredientsIterator the iterator of ingredients to be printed.
   * @param ingredientsFound    a boolean value indicating whether
   *                            there are ingredients on the specified expiration date.
   */
  public void printListOfIngredientsByExpirationDate(Iterator<String> ingredientsIterator,
                                                     boolean ingredientsFound) {
    if (ingredientsFound && ingredientsIterator != null) {
      System.out.println("These are the ingredients expiring on the specified date:");
      while (ingredientsIterator.hasNext()) {
        System.out.println(ingredientsIterator.next());
      }
    } else {
      System.out.println("No ingredients found on the specified date.");
    }
  }

  /**
   * <p>Prints a message that prompts the user to enter the name of an ingredient.</p>
   */
  public void promptForIngredientName() {
    System.out.print("Enter name of ingredient: ");
  }

  /**
   * <p>Prompts the user to enter the quantity of an ingredient.</p>
   */
  public void promptForQuantity() {
    System.out.println("Enter quantity of ingredient: ");
  }

  /**
   * <p>Prints a message that prompts the user to enter the quantity of an ingredient.</p>
   */
  public void promptForUnit() {
    System.out.println("Enter valid unit: kg, g, ml, l and pcs:");
  }

  /**
   * <p>Prints a message that prompts the user to enter the price of an ingredient.</p>
   */
  public void promptForPrice() {
    System.out.println("Enter price of ingredient: ");
  }

  /**
   * <p>Prints a message that prompts the user to enter the expiration date of an ingredient.</p>
   */
  public void promptForExpirationDate() {
    System.out.print("Enter expiration date (YYYY-MM-DD): ");
  }

  /**
   * <p>Prints the total value of all the ingredients present in the storage.</p>
   *
   * <p>This method takes the calculated total value of all ingredients and prints it
   * to the console.</p>
   *
   * @param totalValue the calculated total value of all ingredients in storage.
   */
  public void printValueOfAllIngredients(double totalValue) {
    System.out.println("The value of your ingredients is: " + totalValue);
  }

  /**
   * <p>Prints the total value of all expired ingredients present in the storage.</p>
   *
   * <p>This method takes the calculated total value of all expired ingredients and prints it
   * to the console.</p>
   *
   * @param totalValue the calculated total value of all expired ingredients in storage.
   */
  public void printValueOfExpiredIngredients(double totalValue) {
    System.out.println("The value of your expired ingredients is: " + totalValue);
  }

  /**
   * </p>Prints a message that prompts the user to enter the name of a recipe.</p>
   */
  public void promptForRecipeName() {
    System.out.println("Enter the name of the recipe: ");
  }

  /**
   * <p>Prints a message that prompts the user to enter the description of a recipe.</p>
   */
  public void promptForRecipeDescription() {
    System.out.println("Enter the description of the recipe: ");
  }

  /**
   * <p>Prints a message that prompts the user to enter the instruction of a recipe.</p>
   */
  public void promptForRecipeInstruction() {
    System.out.println("Enter the instruction of the recipe: ");
  }

  /**
   * <p>Prints a message that prompts the user to enter the total servings of a recipe.</p>
   */
  public void promptForRecipeServings() {
    System.out.println("Enter total servings of the recipe: ");
  }

  /**
   * <p>Prints a message that prompts the user to enter the ingredients of a recipe.</p>
   */
  public void promptForRecipeIngredients() {
    System.out.println("Enter the ingredients of the recipe: ");
  }

  /**
   * <p>Prints a message that prompts the user
   * to enter the quantity of an ingredient in a recipe.</p>
   *
   * @param recipeName the name of the recipe
   */
  public void addedRecipe(String recipeName) {
    System.out.println("Successfully added " + recipeName + " to the recipe book");
  }

  /**
   * <p>Prints a message that the recipe already exists in the recipe book.</p>
   *
   * @param recipeName the name of the recipe
   */
  public void recipeExists(String recipeName) {
    System.out.println("Recipe " + recipeName + " already exists");
  }

  /**
   * <p>Prints a message that the recipe was not found in the recipe book.</p>
   *
   * @param recipeName the name of the recipe
   */
  public void printRecipeNotFound(String recipeName) {
    System.out.println("Could not find " + recipeName + " in the recipe book");
  }

  /**
   * <p>Prints a message that the recipe was successfully removed from the recipe book.</p>
   *
   * @param recipeName the name of the recipe
   */
  public void removedRecipe(String recipeName) {
    System.out.println("Successfully removed " + recipeName + " from the recipe book");
  }

  /**
   * <p>Prints a message that the recipe was successfully updated.</p>
   *
   * @param recipeName the name of the recipe
   */
  public void printUpdatedServing(String recipeName) {
    System.out.println("Successfully updated the serving of " + recipeName);
  }

  /**
   * <p>Prints the details of the specified recipe.</p>
   * <p>This method takes the name, description, instruction, and servings of a recipe
   * and prints them to the terminal.</p>
   *
   * @param recipeName  the name of the recipe
   * @param description the description of the recipe
   * @param instruction the instruction of the recipe
   * @param servings    the servings of the recipe
   */
  public void printRecipeDetails(String recipeName, String description,
                                 String instruction, double servings) {
    System.out.println("Recipe: " + recipeName
        + "\nDescription: " + description
        + "\nInstruction: " + instruction
        + "\nServings: " + servings);
  }

  /**
   * <p>Prints the ingredients of the specified recipe.</p>
   * <p>This method takes the name, quantity, and unit of an ingredient in a recipe
   * and prints them to the terminal.</p>
   *
   * @param ingredientName the name of the ingredient
   * @param quantity       the quantity of the ingredient
   * @param unit           the unit of the ingredient
   */
  public void printRecipeIngredients(String ingredientName, double quantity, String unit) {
    System.out.println("Ingredients: " + ingredientName + " " + quantity + " " + unit);
  }

  /**
   * <p>Prints a list of all recipes present in the recipe book.</p>
   * <p>This method takes an iterator of recipe names and prints each one on a new line.</p>
   *
   * @param recipesIterator the iterator of recipes to be printed
   * @param hasRecipes      a boolean value indicating whether there are recipes or not
   */
  public void printListOfRecipes(Iterator<String> recipesIterator, boolean hasRecipes) {
    if (hasRecipes && recipesIterator != null) {
      while (recipesIterator.hasNext()) {
        System.out.println("These are your recipes: ");
        System.out.println(recipesIterator.next());
      }
    } else {
      System.out.println("No recipes found.");
    }
  }

  /**
   * <p>Prints a message that the recipe cannot be made due to insufficient ingredient amount.</p>
   *
   * @param availableQuantity the available quantity of the ingredient
   * @param availableUnit     the available unit of the ingredient
   * @param ingredientName    the name of the ingredient
   * @param requiredQuantity  the required quantity of the ingredient
   * @param requiredUnit      the required unit of the ingredient
   */
  public void printInsufficientIngredientAmount(double availableQuantity, String availableUnit,
                                                String ingredientName, double requiredQuantity,
                                                String requiredUnit) {
    System.out.println("You have " + availableQuantity + " " + availableUnit
        + " of " + ingredientName + " but you need " + requiredQuantity + " " + requiredUnit);
  }

  /**
   * <p>Prints a message that the recipe cannot be made due to missing ingredients.</p>
   *
   * @param requiredQuantity the required quantity of the ingredient
   * @param requiredUnit     the required unit of the ingredient
   * @param ingredientName   the name of the ingredient
   */
  public void printMissingIngredient(double requiredQuantity, String requiredUnit,
                                     String ingredientName) {
    System.out.println("You are missing " + requiredQuantity + " "
        + requiredUnit + " of " + ingredientName);
  }

  /**
   * <p>Prints a message that the recipe cannot be made due to incompatible units.</p>

   * @param requiredIngredientName the name of the required ingredient
   * @param requiredUnit the required unit
   * @param requiredQuantity the required quantity
   * @param availableQuantity the available quantity
   * @param availableUnit the available unit
   */
  public void printIncompatibleUnits(String requiredIngredientName,
                                     String requiredUnit, double requiredQuantity,
                                     double availableQuantity, String availableUnit) {
    System.out.println("Unit conflict for: "
        + requiredIngredientName
        + ". requires: " + requiredQuantity + " " + requiredUnit
        + ", available: " + availableQuantity + " " + availableUnit
        + ". Please update the unit of the ingredient.");
  }

  /**
   * <p>Prints a message that the recipe can be made due to all ingredients being available.</p>
   *
   * @param recipeName the name of the recipe
   */
  public void printRecipeCanBeMade(String recipeName) {
    System.out.println("All ingredients are available. You can make: " + recipeName);
  }

  /**
   * <p>Prints a message that the recipe cannot be made due to missing ingredients.</p>
   *
   * @param recipeName the name of the recipe
   */
  public void printRecipeCannotBeMade(String recipeName) {
    System.out.println("Some ingredients are missing. You cannot make the recipe: " + recipeName);
  }
}