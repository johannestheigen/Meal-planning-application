package edu.ntnu.idi.bidata.utility;

import java.util.Iterator;

/**
 * Handles all printing tasks for the user interface, organizing messages into sections
 * for easy understanding and maintainability.
 *
 * <p>This class provides methods for:</p>
 * <ul>
 *   <li>Displaying the main and extended menus.</li>
 *   <li>Prompting the user for ingredient information.</li>
 *   <li>Displaying ingredient-related operations such as
 *   addition, removal, and quantity updates.</li>
 *   <li>Displaying lists of ingredients and expired ingredients.</li>
 *   <li>Displaying error messages for invalid inputs.</li>
 *   <li>Displaying the total value of ingredients and expired ingredients.</li>
 * </ul>
 *
 * @version 0.0.7
 * @since 11.16.2024
 */
public class OutputHandler {

  /**
   * <p>Displays the main menu.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>OutputHandler.printMainMenu();</code></pre>
   */

  public void printMainMenu() {
    System.out.println("""
       Welcome to the FoodStorage App.
       Press '/help' to view commands
       Press 0 to exit
        """);
  }

  /**
   * <p>Prints a warning when the user wants to exit the application.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>OutputHandler.printExitWarning();</code></pre>
   */
  public void printExitWarning() {
    System.out.println("Are you sure you want to exit? (y/n)");
  }

  /**
   * <p>Prints a warning when a user wants to perform
   *    * an operation that may have unintended consequences.</p>
   *
   * <p><b>Example of usage: </b>
   * <pre><code>OutPutHandler.printWarning();</code></pre></p>
   */
  public void printWarning() {
    System.out.println("Are you sure you want to continue? (y/n)");
  }

  /**
   * Prints a message to confirm that the user has successfully completed an operation.
   *
   * <p><b>Example of usage: </b>
   * <pre><code>OutPutHandler.printSuccess();</code></pre></p>
   */
  public void printSuccess() {
    System.out.println("Success");
  }

  /**
   * <p>Prints a message to confirm that the user has exited the application.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>OutputHandler.printExitMessage();</code></pre>
   */
  public void printExitMessage() {
    System.out.println("Exiting the program...");
  }

  /**
   * <p>Prints a message to alert the user that they have aborted an operation.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>OutputHandler.printAbortOperationMessage();</code></pre>
   */
  public void printAbortOperationMessage() {
    System.out.println("Aborted the operation");
  }

  /**
   * <p>Displays an extension of the menu with additional options for the user.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>OutputHandler.printExtendedMenu();</code></pre>
   */

  public void printExtendedMenu() {
    System.out.println("""
       Type '/add' to add an ingredient
       Type '/reduce' to reduce the quantity of an ingredient
       Type '/change-ingredient' to change an ingredient
       Type '/find' to find an ingredient
       Type '/list' to list all ingredients
       Type '/value' to print value of all ingredients
       Type '/value-expired' to print value of all expired ingredients
        """);
  }

  /**
   * <p>Displays a menu with options to change the description or price of an ingredient.</p>
   *
   * <p><b>Example of usage: </b>
   * <pre><code>OutputHandler.printChangeIngredientMenu()</code></pre></p>
   */
  public void printChangeIngredientMenu() {
    System.out.println("""
       Type '/change-description' to change the description of an ingredient
       Type '/change-price' to change the price of an ingredient
        """);
  }

  /**
   * <p>Displays a menu with options to sort ingredients alphabetically, expired ingredients
   * and ingredients by a specific expiration date.</p>
   *
   * <p><b>Example of usage: </b>
   * <pre><code>OutputHandler.printListMenu()</code></pre></p>
   */
  public void printListMenu() {
    System.out.println("""
        Type '/sort' to view ingredients in alphabetical order
        Type '/expired to view expired ingredients
        Type '/date' to view ingredients by a specific expiration date
        """);
  }

  /**
   * <p>Prints a message indicating whether the specified ingredient was found in the storage.</p>
   *
   * <p>If the ingredient is found (based on the provided boolean value),
   * it prints the ingredient's name.
   * If the ingredient is not found, it displays an error message.</p>
   *
   * @param ingredientName the name of the ingredient to be printed.
   * @param found a boolean value indicating whether the ingredient was found or not.
   *
     <p><b>Example of usage:</b></p>
     <pre><code>OutputHandler.printIngredient("Sugar", true);</code></pre>
   */
  public void printIngredient(String ingredientName, boolean found) {
    if (found) {
      System.out.println("Found: " + ingredientName);
    } else {
      System.out.println("Could not find: " + ingredientName);
    }
  }

  /**
   * <p>Prints a message when the quantity of the specified ingredient
   * has been successfully updated.</p>
   *
   * @param name the name of the ingredient whose quantity was updated successfully.
     <p><b>Example of usage:</b></p>
     <pre><code>OutputHandler.printUpdatedQuantity("Strawberry");</code></pre>
   */

  public void printUpdatedQuantity(String name) {
    System.out.println("Successfully updated the quantity of " + name);
  }

  /**
   * <p>Prints a message when an ingredient has been successfully added.</p>
   *
   * @param name the name of the ingredient that was added
   *
     <p><b>Example of usage:</b></p>
     <pre><code>OutputHandler.printAddedIngredient("Strawberry");</code></pre>
   */
  public void printAddedIngredient(String name) {
    System.out.println("Successfully added " + name + " to the storage");
  }

  /**
   * <p>Prints a message when an ingredient has been successfully removed.</p>
   *
   * @param name the name of the ingredient that was removed
   *
     <p><b>Example of usage:</b></p>
     <pre><code>OutputHandler.printRemovedIngredient("Strawberry");</code></pre>
   */

  public void printRemovedIngredient(String name) {
    System.out.println("Successfully removed " + name
        + " because the quantity was reduced to zero.");
  }

  /**
   * <p>Prints a list of all ingredients present in the storage.</p>
   *
   * <p>This method takes an iterator of ingredient names and prints each one on a new line.</p>
   *
   *
   * @param ingredientsIterator the iterator of ingredients to be printed.
   * @param hasIngredients a boolean value indicating whether there are ingredients or not
   *
     <p><b>Example of usage:</b></p>
     <pre><code>OutputHandler.printListOfIngredients(ingredientsIterator);</code></pre>
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
   *
   * <p>This method takes an iterator of ingredient names, sorts them alphabetically and print
   * each one on a new line.</p>
   *
   * @param ingredientsIterator the iterator of ingredients to be printed.
   * @param hasIngredients a boolean value indicating whether there are ingredients or not.
   *
     <p><b>Example of usage:</b></p>
     <pre>
       <code>OutputHandler.printListOfIngredientsAlphabetically(ingredientsIterator);</code>
     </pre>
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
   * @param ingredientsIterator the iterator of ingredients to be printed.
   * @param hasExpiredIngredients a boolean value indicating whether there are expired ingredients.
   *
   *<p><b>Example of usage:</b></p>
     <pre><code>OutputHandler.printListOfExpiredIngredients(ingredientsIterator);</code></pre>
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
   *  and filters them to display only those that are expiring on the specified date.</p>
   *
   *
   * @param ingredientsIterator the iterator of ingredients to be printed.
   * @param ingredientsFound a boolean value indicating whether
   *                         there are ingredients on the specified expiration date.
   *
      <p><b>Example of usage: </b>
      <pre><code>OutputHandler.printListOfIngredientsByExpirationDate
  (ingredientsIterator);</code></pre>
      </p>
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
   * <p>Prompts the user to enter the name of an ingredient.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>outputHandler.promptForIngredientName();</code></pre>
   */
  public void promptForIngredientName() {
    System.out.print("Enter name of ingredient: ");
  }

  /**
   * <p>Prompts the user to enter the description of an ingredient.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>outputHandler.promptForDescription();</code></pre>
   */
  public void promptForDescription() {
    System.out.println("Enter description of ingredient: ");
  }

  /**
   * <p>Prompts the user to enter the quantity of an ingredient.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>outputHandler.promptForQuantity();</code></pre>
   */
  public void promptForQuantity() {
    System.out.println("Enter quantity of ingredient: ");
  }

  /**
   * <p>Prompts the user to enter the quantity of an ingredient.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>outputHandler.promptForUnit();</code></pre>
   */
  public void promptForUnit() {
    System.out.println("Enter valid unit: kg, g, ml and l:");
  }

  /**
   * <p>Prompts the user to enter the price of an ingredient.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>outputHandler.promptForPrice();</code></pre>
   */
  public void promptForPrice() {
    System.out.println("Enter price of ingredient: ");
  }

  /**
   * <p>Prompts the user to enter the expiration date of an ingredient.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>outputHandler.promptForExpirationDate();</code></pre>
   */
  public void promptForExpirationDate() {
    System.out.print("Enter expiration date (YYYY-MM-DD): ");
  }

  public void promptForNewDescription() {
    System.out.print("Enter a new description: ");
  }

  public void promptForNewPrice() {
    System.out.print("Enter a new price: ");
  }

  /**
   * <p>Prints an error message when an invalid input is entered by the user.</p>
   *
   * <p>This method is used to alert the user when their input is invalid
   * It prompts the user to enter a valid input.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>outputHandler.printInvalidInput();</code></pre>
   */
  public void printInvalidInput(String message) {
    System.out.println("Invalid input " + message);
  }

  /**
   * Prints the total value of all the ingredients present in the storage.
   *
   * <p>This method takes the calculated total value of all ingredients and prints it
   * to the console.</p>
   *
   * @param totalValue the calculated total value of all ingredients in storage.
   *
     <p><b>Example of usage:</b></p>
     <pre><code>outputHandler.printValueOfAllIngredients(50);</code></pre>
   */
  public void printValueOfAllIngredients(double totalValue) {
    System.out.println("The value of your ingredients is: " + totalValue);
  }

  /**
   * Prints the total value of all expired ingredients present in the storage.
   *
   * <p>This method takes the calculated total value of all expired ingredients and prints it
   * to the console.</p>
   *
   * @param totalValue the calculated total value of all expired ingredients in storage.
   *
     <p><b>Example of usage:</b></p>
     <pre><code>outputHandler.printValueOfExpiredIngredients(50);</code></pre>
   */
  public void printValueOfExpiredIngredients(double totalValue) {
    System.out.println("The value of your expired ingredients is: " + totalValue);
  }

  public void printIngredientDetails(String ingredientInfo) {
    System.out.println("Found: " + ingredientInfo);
  }
}
