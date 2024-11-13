package edu.ntnu.idi.bidata.app;

import edu.ntnu.idi.bidata.inventory.FoodStorage;
import edu.ntnu.idi.bidata.utility.InputHandler;
import edu.ntnu.idi.bidata.utility.OutputHandler;
import java.time.LocalDate;
import java.util.Iterator;

/**
 * <p>The UserInterface class manages interactions between the
 * application and the user.</p>
 *
 * <p>This class provides the following key methods:</p>
 * <ul>
 *   <li><b>init</b>: Initializes the required instances for user interaction.</li>
 *   <li><b>start</b>: Starts the application from the Launch class.</li>
 *   <li><b>addIngredient</b>: Allows the user to add a new ingredient.</li>
 *   <li><b>reduceIngredient</b>: Reduces the quantity of an existing ingredient
 *   or removes it if quantity reaches zero.</li>
 *   <li><b>findIngredient</b>: Searches for an ingredient by name.</li>
 *   <li><b>displayListOfIngredients</b>: Displays a list of all ingredients.</li>
 *   <li><b>displayListOfIngredientsAlphabetically</b>:
 *   Displays a list of ingredients sorted alphabetically.</li>
 *   <li><b>displayListOfExpiredIngredients</b>: Displays a list of expired ingredients.</li>
 *   <li><b>displayListOfIngredientsByExpirationDate</b>:
 *   Displays ingredients filtered by a specific expiration date.</li>
 *   <li><b>displayValueOfAllIngredients</b>:
 *   Displays the total value of all ingredients.</li>
 *   <li><b>displayValueOfExpiredIngredients</b>:
 *   Displays the total value of expired ingredients.</li>
 *   <li><b>userInput</b>: Captures and processes user input.</li>
 * </ul>
 *
 * <p>Each method is designed to facilitate specific user actions within the application.</p>
 *
 * @version 0.1.1
 * @since 11.13.2024
 */
public class UserInterface {

  private FoodStorage foodStorage;
  private OutputHandler output;
  private InputHandler input;

  /**
   * <p>Initializes the application at startup by creating instances of
   * <code>FoodStorage</code>, <code>OutputHandler</code>, and <code>InputHandler</code>.</p>
   *
   * <p>This method also adds two default ingredients to the <code>FoodStorage</code>
   * for testing and demonstration purposes, providing initial data that can be
   * used to showcase application functionality.</p>
   *
     <p><b>Example of usage:</b></p>
     <pre><code>
     userInterface.init();
     </code></pre>
   */

  public void init() {
    foodStorage = new FoodStorage();
    output = new OutputHandler();
    input = new InputHandler();
    foodStorage.addIngredient("Kiwi", "Fruit", 1, "kg", 2, LocalDate.of(2027, 10, 10));
    foodStorage.addIngredient("Apple", "Fruit", 2, "kg", 2, LocalDate.of(2027, 10, 10));
    userInput();
  }

  /**
   * Starts interaction with the user.
   *
   * <p>This method calls {@link #init()} to prepare necessary resources
   * and sets up the environment for user interaction. It serves as an entry
   * point for starting the application's user interface, and can be extended
   * to include additional startup logic if needed.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.start();</code></pre>
   */
  public void start() {
    init();
  }

  /**
   * Adds a new ingredient to the storage through user interaction.
   *
   * <p>This method prompts the user for all necessary ingredient details, including:
   * <ul>
   *   <li>Name</li>
   *   <li>Description</li>
   *   <li>Quantity</li>
   *   <li>Unit (e.g., grams, liters)</li>
   *   <li>Price</li>
   *   <li>Expiration date</li>
   * </ul>
   *
   * <p>The user is prompted to confirm if they want to add the ingredient.</p>
   *
   * <p>Once all details are collected, the ingredient is added to the storage.
   * If an ingredient with the same name already exists, its quantity is incremented;
   * otherwise, a new ingredient is created and added to the storage.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.addIngredient();</code></pre>
   */
  private void addIngredient() {
    output.promptForIngredientName();
    final String name = input.stringInput();

    output.promptForDescription();
    final String description = input.stringInput();

    output.promptForQuantity();
    final double quantity = input.doubleInput();

    output.promptForUnit();
    final String unit = input.stringInput();

    output.promptForPrice();
    final double price = input.doubleInput();

    output.promptForExpirationDate();
    final LocalDate expirationDate = input.expirationDateInput();

    output.printWarning();
    if (input.stringInput().equalsIgnoreCase("n")) {
      output.printAbortOperationMessage();
    } else {
      boolean ingredientExists = foodStorage.addIngredient(name, description,
          quantity, unit, price, expirationDate);
      if (ingredientExists) {
        output.printUpdatedQuantity(name);
      } else {
        output.printAddedIngredient(name);
      }
    }
  }

  /**
   * Reduces the quantity of an ingredient in the storage through user interaction.
   *
   * <p>This method prompts the user for the <b>name</b> and <b>quantity</b>
   * of the ingredient to identify it in the storage.</p>
   *
   * <p>The user is prompted to confirm if they want to reduce the ingredient.</p>
   *
   * <p>If the ingredient is found, its quantity is reduced by 1.
   * If the ingredient's quantity becomes less than 1 after the reduction,
   * the ingredient is completely removed from storage.
   * If the ingredient does not exist, an error message is displayed.</p>
   **
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.reduceIngredient();</code></pre>
   */
  public void reduceIngredient() {
    output.promptForIngredientName();
    String name = input.stringInput();

    output.promptForQuantity();
    double quantity = input.doubleInput();

    if (foodStorage.getIngredient(name) == null) {
      output.printIngredient(name, false);
    } else {
      boolean wasReduced = foodStorage.reduceIngredient(name, quantity);
      output.printWarning();
      if (input.stringInput().equalsIgnoreCase("n")) {
        output.printAbortOperationMessage();
      } else {
        if (wasReduced) {
          output.printUpdatedQuantity(name);
        } else {
          output.printRemovedIngredient(name);
        }
      }
    }
  }

  /**
   * <p>Allows the user to change the description of an ingredient.</p>
   *
   * <p>The user is prompted to enter the name of the ingredient and
   * a new description for the ingredient.</p>
   *
   * <p>Before the change is made, the user is prompted to confirm the operation.</p>
   *
   * <p>If the user confirms the operation, the description of the ingredient is updated.</p>
   *
   * <p><b>Example of usage:</b>
   * <pre><code>foodStorage.changeDescription();</code></pre></p>
   */
  public void changeDescription() {
    output.promptForIngredientName();
    String name = input.stringInput();
    if (foodStorage.getIngredient(name) == null) {
      output.printIngredient(name, false);
    } else {
      output.promptForNewDescription();
      String newDescription = input.stringInput();
      output.printWarning();
      if (input.stringInput().equalsIgnoreCase("n")) {
        output.printAbortOperationMessage();
      }
      foodStorage.changeDescription(name, newDescription);
      output.printSuccess();
    }
  }

  /**
   * <p>Allows the user to change the price of an ingredient.</p>
   *
   * <p>The user is prompted to enter the name of the ingredient and
   * a new price for the ingredient.</p>
   *
   * <p>Before the change is made, the user is prompted to confirm the operation.</p>
   *
   * <p>If the user confirms the operation, the price of the ingredient is updated.</p>
   *
   * <p><b>Example of usage:</b>
   * <pre><code>foodStorage.changePrice();</code></pre></p>
   */
  public void changePrice() {
    output.promptForIngredientName();
    String name = input.stringInput();
    if (foodStorage.getIngredient(name) == null) {
      output.printIngredient(name, false);
    } else {
      output.promptForNewPrice();
      double newPrice = input.doubleInput();
      output.printWarning();
      if (input.stringInput().equalsIgnoreCase("n")) {
        output.printAbortOperationMessage();
      } else {
        foodStorage.changePrice(name, newPrice);
        output.printSuccess();
      }
    }
  }

  /**
   * Finds an ingredient by its name.
   * If the ingredient is found, all its details is displayed.
   * If the ingredient does not exist, an error message is displayed.
   *
     <p><b>Example of usage:</b></p>
     <pre><code>userInterface.findIngredient();</code></pre>
   */

  public void findIngredient() {
    output.promptForIngredientName();
    String ingredientName = input.stringInput();
    String ingredientInfo = foodStorage.getIngredientInfo(ingredientName);
    if (ingredientInfo != null) {
      output.printIngredientDetails(ingredientInfo);
    } else {
      output.printIngredient(ingredientName, false);  // Prints a not-found message
    }
  }

  /**
   * Displays a list of all ingredients present in the foodStorage.
   * This method uses an iterator to retrieve
   * and display the names of all ingredients in the storage.
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.displayListOfIngredients();</code></pre>
   */
  public void displayListOfIngredients() {
    Iterator<String> ingredientsIterator = foodStorage.getListOfIngredients();
    boolean hasIngredients = ingredientsIterator != null && ingredientsIterator.hasNext();
    output.printListOfIngredients(ingredientsIterator, hasIngredients);
  }

  /**
   * Displays a list of all ingredients present in the foodStorage in alphabetical order.
   * This method uses an iterator to retrieve and sort the ingredient names in alphabetical order
   * before displaying them.
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.displayListOfIngredientsAlphabetically();</code></pre>
   */
  public void displayListOfIngredientsAlphabetically() {
    Iterator<String> ingredientsIterator = foodStorage.getListOfIngredientsAlphabetically();
    boolean hasIngredients = ingredientsIterator != null && ingredientsIterator.hasNext();
    output.printListOfIngredientsAlphabetically(ingredientsIterator, hasIngredients);
  }

  /**
   * Displays a list of all expired ingredients present in the foodStorage.
   * This method uses an iterator to retrieve
   * and filter the ingredients based on their expiration date,
   * and then displays only those that have expired.
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.displayListOfExpiredIngredients();</code></pre>
   */
  public void displayListOfExpiredIngredients() {
    Iterator<String> ingredientsIterator = foodStorage.getListOfExpiredIngredients();
    boolean hasExpiredIngredients = ingredientsIterator != null && ingredientsIterator.hasNext();
    output.printListOfExpiredIngredients(ingredientsIterator, hasExpiredIngredients);
  }

  /**
   * Displays a list of all ingredients present in the foodStorage that have given expiration date.
   * This method uses an iterator to retrieve
   * and filter the ingredients based on the provided expiration date,
   * and then displays only those that match the specific expiration date.
   *
     <p><b>Example of usage:</b></p>
     <pre><code>
  userInterface.displayListOfIngredientsByExpirationDate(LocalDate.of(2024, 7, 10));</code></pre>
   */

  public void displayListOfIngredientsByExpirationDate() {
    output.promptForExpirationDate();
    LocalDate expirationDate = input.expirationDateInput();

    Iterator<String> ingredientsIterator =
        foodStorage.getListOfIngredientsByExpirationDate(expirationDate);

    boolean hasExpiredIngredients = ingredientsIterator != null && ingredientsIterator.hasNext();

    output.printListOfExpiredIngredients(ingredientsIterator, hasExpiredIngredients);
  }

  /**
   * Displays the value of all ingredients present in the storage.
   * This method calculates the total value of all ingredients based on their price and quantity,
   * and then outputs the result.
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.displayValueOfAllIngredients();</code></pre>
   */
  public void displayValueOfAllIngredients() {
    output.printValueOfAllIngredients(foodStorage.getValueOfAllIngredients());
  }

  /**
   * Displays the value of all  expired ingredients present in the storage.
   * This method calculates the total value of all expired ingredients
   * based on their price and quantity,
   * and then outputs the result.
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.displayValueOfExpiredIngredients();</code></pre>
   */
  public void displayValueOfExpiredIngredients() {
    output.printValueOfExpiredIngredients(foodStorage.getValueOfExpiredIngredients());
  }

  /**
   * Handles the interaction between the user and the application.
   * <p>
   * This method displays the main menu and processes the user's choices by calling the correct
   * methods based on the user's input.
   * The application will keep running until the user chooses to exit.
   * </p>
   * <p>
   * The available options allow the user to:
   * <ul>
   *    <li>Help command to view the different options</li>
   *   <li>Add an ingredient</li>
   *   <li>Reduce the quantity of an ingredient</li>
   *   <li>Change the description of an ingredient</li>
   *   <li>Change the price of an ingredient</li>
   *   <li>Display a list of all ingredients</li>
   *   <li>Display ingredients in alphabetical order</li>
   *   <li>Display expired ingredients</li>
   *   <li>Display ingredients based on a specific expiration date</li>
   *   <li>Display the total value of all ingredients</li>
   *   <li>Display the total value of expired ingredients</li>
   *   <li>Exit the application</li>
   * </ul>
   * </p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.userInput();</code></pre>
   */
  public void userInput() {
    output.printMainMenu();
    boolean running = true;
    while (running) {
      String userChoice = input.stringInput();

      switch (userChoice.toLowerCase()) {
        case "0" -> {
          output.printExitWarning();
          if (input.stringInput().equalsIgnoreCase("y")) {
            output.printExitMessage();
            input.close();
            running = false;
          } else {
            output.printAbortOperationMessage();
          }
        }
        case "/help" -> output.printExtendedMenu();
        case "/add" -> addIngredient();
        case "/change-description" -> changeDescription();
        case "/change-price" -> changePrice();
        case "/reduce" -> reduceIngredient();
        case "/view" -> displayListOfIngredients();
        case "/find" -> findIngredient();
        case "/sort" -> displayListOfIngredientsAlphabetically();
        case "/expired" -> displayListOfExpiredIngredients();
        case "/date" -> displayListOfIngredientsByExpirationDate();
        case "/value" -> displayValueOfAllIngredients();
        case "/value-expired" -> displayValueOfExpiredIngredients();
        default -> output.printInvalidInput();
      }
      if (!(userChoice.equals("/help") || userChoice.equals("0"))) {
        output.printMainMenu();
      }
    }
  }
}