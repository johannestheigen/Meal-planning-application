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
 * @version 0.0.2
 * @since 11.11.2024
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
       Press 1 to add an ingredient
       Press 2 to remove an ingredient
       Press 3 to view your ingredients
       Press 4 to view more commands
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
       Press 5 to find an ingredient
       Press 6 to view ingredients in alphabetical order
       Press 7 to view expired ingredients
       Press 8 to view ingredients by a specific expiration date
       Press 9 to print value of all ingredients
       Press 10 to print value of all expired ingredients
        """);
  }


  /**
   * <p>Prints a message indicating whether the specified ingredient was found in the storage.</p>
   *
   * <p>If the ingredient is found (based on the provided boolean value),
   * it prints the ingredient's name.
   * If the ingredient is not found, it displays an error message.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>OutputHandler.printIngredient("Sugar", true);</code></pre>
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
   * <p><b>Example of usage:</b></p>
   * <pre><code>OutputHandler.printUpdatedQuantity("Strawberry");</code></pre>
   */

  public void printUpdatedQuantity(String name) {
    System.out.println("Successfully updated the quantity of " + name);
  }

  /**
   * <p>Prints a message when an ingredient has been successfully added.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>OutputHandler.printAddedIngredient("Strawberry");</code></pre>
   */
  public void printAddedIngredient(String name) {
    System.out.println("Successfully added " + name + " to the storage");
  }

  /**
   * <p>Prints a message when an ingredient has been successfully removed.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>OutputHandler.printRemovedIngredient("Strawberry");</code></pre>
   */
  public void printRemovedIngredient(String name) {
    System.out.println("Successfully removed " + name + " from the storage");
  }

  /**
   * <p>Prints a list of all ingredients present in the storage.</p>
   *
   * <p>This method takes an iterator of ingredient names and prints each one on a new line.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>OutputHandler.printListOfIngredients(ingredientsIterator);</code></pre>
   */
  public void printListOfIngredients(Iterator<String> ingredientsIterator) {
    System.out.println("These are your ingredients: ");
    while (ingredientsIterator.hasNext()) {
      System.out.println(ingredientsIterator.next());
    }
  }

  /**
   * <p>Prints a list of all ingredients present in the storage in alphabetical order.</p>
   *
   * <p>This method takes an iterator of ingredient names, sorts them alphabetically and print
   * each one on a new line.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre>
   *   <code>OutputHandler.printListOfIngredientsAlphabetically(ingredientsIterator);</code>
   * </pre>
   */
  public void printListOfIngredientsAlphabetically(Iterator<String> ingredientsIterator) {
    System.out.println("These are your ingredients in alphabetical order: ");
    while (ingredientsIterator.hasNext()) {
      System.out.println(ingredientsIterator.next());
    }
  }

  /**
   * <p>Prints a list of all expired ingredients present in the storage,
   * sorted by expiration date.</p>
   *
   * <p>This method takes an iterator of ingredients
   * and filters them to display only those that are expired.
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>OutputHandler.printListOfExpiredIngredients(ingredientsIterator);</code></pre>
   */
  public void printListOfExpiredIngredients(Iterator<String> ingredientsIterator) {
    System.out.println("These are your expired ingredients:");
    while (ingredientsIterator.hasNext()) {
      System.out.println(ingredientsIterator.next());
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
    System.out.println("Enter valid unit: kg, g, ml and liter:");
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

  /**
   * <p>Prints an error message when an invalid input is entered by the user.</p>
   *
   * <p>This method is used to alert the user when their input is invalid
   * It prompts the user to enter a valid input.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>outputHandler.printInvalidInput();</code></pre>
   */
  public void printInvalidInput() {
    System.out.println("Invalid input, please enter a valid input.");
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
}
