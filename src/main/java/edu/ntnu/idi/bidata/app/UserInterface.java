package edu.ntnu.idi.bidata.app;

import edu.ntnu.idi.bidata.items.Ingredient;
import edu.ntnu.idi.bidata.register.FoodStorage;
import edu.ntnu.idi.bidata.register.RecipeBook;
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
 *   <li><b>changeDescription</b>: Allows the user to change the description of an ingredient.</li>
 *   <li><b>changePrice</b>: Allows the user to change the price of an ingredient.</li>
 *   <li><b>findIngredient</b>: Searches for an ingredient by name and displays its details</li>
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
 *   <li><b>addRecipe</b>: Allows the user to add a new recipe.</li>
 *   <li><b>removeRecipe</b>: Allows the user to remove a recipe.</li>
 *   <li><b>findRecipe</b>: Searches for a recipe by name and displays its details.</li>
 *   <li><b>displayRecipes</b>: Displays a list of all recipes.</li>
 *   <li><b>checkIfRecipeCanBeMade</b>: Checks if a recipe can be
 *   made based on the ingredients available.</li>
 *   <li><b>userInput</b>: Captures and processes user input.</li>
 * </ul>
 *
 * <p>Each method is designed to facilitate specific user actions within the application.</p>
 *
 * @version 0.2.0
 * @since 11.21.2024
 */
public class UserInterface {

  private FoodStorage foodStorage;
  private RecipeBook recipeBook;
  private OutputHandler output;
  private InputHandler input;

  /**
   * <p>Initializes the application at startup by creating instances of
   * <code>FoodStorage</code>, <code>RecipeBook</code>,
   * <code>OutputHandler</code>, and <code>InputHandler</code>.</p>
   *
   * <p>This method also adds two default ingredients to the <code>FoodStorage</code>
   * and a default recipe to the <code>RecipeBook</code>.</p>
   * for testing and demonstration purposes, providing initial data that can be
   * used to showcase application functionality.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>
   * userInterface.init();
   * </code></pre>
   */

  public void init() {
    foodStorage = new FoodStorage();
    recipeBook = new RecipeBook();
    output = new OutputHandler();
    input = new InputHandler();
    foodStorage.addIngredient("Kiwi", "Fruit", 1, "kg", 2, LocalDate.of(2027, 10, 10));
    foodStorage.addIngredient("Apple", "Fruit", 2, "kg", 2, LocalDate.of(2027, 10, 10));
    recipeBook.addRecipe("Apple Pie", "A delicious apple pie", "Bake it", 4);
    recipeBook.addIngredientToRecipe("Apple Pie", "Apple", 2, "kg");
    userInput();
  }

  /**
   * Starts interaction with the user.
   *
   * <p>This method calls {@link #init()} to prepare necessary resources
   * and sets up the environment for user interaction. It serves as an entry
   * point for starting the application's user interface.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.start();</code></pre>
   */
  public void start() {
    init();
  }


  /**
   * <p>Adds a new ingredient to the storage through user interaction.</p>
   *
   * <p>This method prompts the user for all necessary ingredient details, including:
   * <ul>
   *   <li>Name</li>
   *   <li>Description</li>
   *   <li>IngredientQuantity</li>
   *   <li>Unit (e.g., grams, liters)</li>
   *   <li>Price</li>
   *   <li>Expiration date</li>
   * </ul>
   *
   * <p>The user is prompted to confirm if they want to add the ingredient.</p>
   * <p>If the user chooses to abort the operation, a message is displayed.</p>
   *
   * <p>If the values provided by the user is invalid an error message is displayed
   * with the details of the error.</p>
   *
   * <p>Once all details are collected, the ingredient is added to the storage.
   * If an ingredient with the same name already exists, its quantity is incremented;
   * otherwise, a new ingredient is created and added to the storage.</p>
   *
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.addIngredient();</code></pre>
   */
  public void addIngredient() {
    try {
      output.promptForIngredientName();
      final String name = input.stringInput();

      output.promptForDescription();
      final String description = input.stringInput();

      output.promptForQuantity();
      final double quantity = input.doubleInput();

      output.promptForUnit();
      final String unit = input.stringInput();

      output.promptForPrice();
      double price = input.doubleInput();

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
    } catch (IllegalArgumentException e) {
      output.printInvalidInput(e.getMessage());
    } catch (Exception e) {
      output.printError(e.getMessage());
    }
  }

  /**
   * <p>Reduces the quantity of an ingredient in the storage through user interaction.</p>
   *
   * <p>This method prompts the user for the <b>name</b> and <b>quantity</b>
   * of the ingredient to identify it in the storage.</p>
   *
   * <p>The user is prompted to confirm if they want to reduce the ingredient.</p>
   * <p>If the user chooses to abort the operation, a message is displayed.</p>
   *
   * <p>If the values provided by the user is invalid an error message is displayed
   * with the details of the error.</p>
   *
   * <p>If the ingredient is found, its quantity is reduced by 1.
   * If the ingredient's quantity becomes less than 1 after the reduction,
   * the ingredient is completely removed from storage.
   * If the ingredient does not exist, an error message is displayed.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.reduceIngredient();</code></pre>
   */
  public void reduceIngredient() {
    try {
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
    } catch (IllegalArgumentException e) {
      output.printInvalidInput(e.getMessage());
    } catch (Exception e) {
      output.printError(e.getMessage());
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
   * <p>If the description provided by the user is invalid an error message is displayed
   * with the details of the error.</p>
   *
   * <p>If the user confirms the operation, the description of the ingredient is updated.</p>
   *
   * <p><b>Example of usage:</b>
   * <pre><code>foodStorage.changeDescription();</code></pre></p>
   */
  public void changeDescription() {
    try {
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
        } else {
          foodStorage.updateDescription(name, newDescription);
          output.printUpdatedDescription(name);
        }
      }
    } catch (IllegalArgumentException e) {
      output.printInvalidInput(e.getMessage());
    } catch (Exception e) {
      output.printError(e.getMessage());
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
   * <p>If the price provided by the user is invalid an error message is displayed
   * with the details of the error.</p>
   *
   * <p>If the user confirms the operation, the price of the ingredient is updated.</p>
   *
   * <p><b>Example of usage:</b>
   * <pre><code>foodStorage.changePrice();</code></pre></p>
   */
  public void changePrice() {
    try {
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
          foodStorage.updatePrice(name, newPrice);
          output.printUpdatedPrice(name);
        }
      }
    } catch (IllegalArgumentException e) {
      output.printInvalidInput(e.getMessage());
    } catch (Exception e) {
      output.printError(e.getMessage());
    }
  }

  /**
   * <p>Finds an ingredient by its name.
   * If the ingredient is found, all its details is displayed.
   * If the ingredient does not exist, an error message is displayed.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.findIngredient();</code></pre>
   */

  public void findIngredient() {
    output.promptForIngredientName();
    String ingredientName = input.stringInput();
    if (foodStorage.getIngredient(ingredientName) != null) {
      output.printIngredientDetails(ingredientName,
          foodStorage.getIngredient(ingredientName).getDescription(),
          foodStorage.getIngredient(ingredientName).getQuantity(),
          foodStorage.getIngredient(ingredientName).getUnit(),
          foodStorage.getIngredient(ingredientName).getPrice(),
          foodStorage.getIngredient(ingredientName).getExpirationDate());
    } else {
      output.printIngredient(ingredientName, false);
    }
  }

  /**
   * <p>Displays a list of all ingredients present in the foodStorage.
   *  This method uses an iterator to retrieve
   *  and display the names of all ingredients in the storage.</p>
   *
   *  <p>If there are no ingredients in the storage, a message is displayed
   *  to inform the user that the storage is empty.</p>
   *
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
   * <p>Displays a list of all ingredients present in the foodStorage in alphabetical order.
   * This method uses an iterator to retrieve and sort the ingredient names in alphabetical order
   * before displaying them.</p>
   *
   * <p>If there are no ingredients in the storage, a message is displayed
   * to inform the user that the storage is empty.</p>
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
   * <p>Displays a list of all expired ingredients present in the foodStorage.
   * This method uses an iterator to retrieve,
   * filter the ingredients based on their expiration date,
   * and then displays only those that have expired.</p>
   *
   * <p>If there are no expired ingredients in the storage, a message is displayed
   * to inform the user that there are no expired ingredients.</p>
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
   * <p>Displays a list of all ingredients present
   * in the foodStorage that have given expiration date.
   * This method uses an iterator to retrieve,
   * filter the ingredients based on the provided expiration date,
   * and then displays only those that match the specific expiration date.</p>
   *
   * <p>If there are no ingredients with the provided expiration date in the storage,
   * a message is displayed to inform the user that
   * there are no ingredients with that expiration date.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>
   * userInterface.displayListOfIngredientsByExpirationDate(LocalDate.of(2024, 7, 10));</code></pre>
   */
  public void displayListOfIngredientsByExpirationDate() {
    output.promptForExpirationDate();
    LocalDate expirationDate = input.expirationDateInput();

    Iterator<String> ingredientsIterator
        = foodStorage.getListOfIngredientsByExpirationDate(expirationDate);

    boolean hasIngredients = ingredientsIterator != null && ingredientsIterator.hasNext();

    output.printListOfIngredientsByExpirationDate(ingredientsIterator, hasIngredients);
  }

  /**
   * <p>Displays the value of all ingredients present in the storage.
   * This method calculates the total value of all ingredients based on their price and quantity,
   * and then outputs the result.</p>
   *
   * <p>If there are no ingredients in the storage, a message is displayed
   * to inform the user that the storage is empty.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.displayValueOfAllIngredients();</code></pre>
   */
  public void displayValueOfAllIngredients() {
    output.printValueOfAllIngredients(foodStorage.getValueOfAllIngredients());
  }

  /**
   * <p>Displays the value of all  expired ingredients present in the storage.
   * This method calculates the total value of all expired ingredients
   * based on their price and quantity,
   * and then outputs the result.</p>
   *
   * <p>If there are no expired ingredients in the storage, a message is displayed
   * to inform the user that there are no expired ingredients.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.displayValueOfExpiredIngredients();</code></pre>
   */
  public void displayValueOfExpiredIngredients() {
    output.printValueOfExpiredIngredients(foodStorage.getValueOfExpiredIngredients());
  }

  /**
   * <p>Adds a new recipe to the recipe book through user interaction.</p>
   *
   * <p>This method prompts the user for all necessary recipe details, including:
   * <ul>
   *   <li>Name</li>
   *   <li>Description</li>
   *   <li>Instruction</li>
   *   <li>Servings</li>
   *   <li>Confirmation to add the recipe</li>
   *   <li>Amount of ingredients</li>
   *   <li>Ingredient name</li>
   *   <li>Ingredient quantity</li>
   *   <li>Ingredient unit</li>
   *  </ul>
   *
   *  <p>If the user chooses to abort the operation, a message is displayed.</p>
   *
   *  <p>If the values provided by the user is invalid an error message is displayed
   *  with the details of the error.</p>
   *
   *  <p>Once all details are collected, the recipe is added to the recipe book.</p>
   *  <p>If a recipe with the same name already exists, an error message is displayed.</p>
   *
   *  <p><b>Example of usage:</b></p>
   *  <pre><code>userInterface.addRecipe();</code></pre>
   *  </p>
   *
   */
  public void addRecipe() {
    try {
      output.promptForRecipeName();
      String name = input.stringInput();

      if (recipeBook.getRecipe(name) != null) {
        output.recipeExists(name);
      } else {
        output.promptForRecipeDescription();
        final String description = input.stringInput();
        output.promptForRecipeInstruction();
        String instructions = input.stringInput();
        output.promptForRecipeServings();
        double servings = input.doubleInput();

        output.printWarning();
        if (input.stringInput().equalsIgnoreCase("n")) {
          output.printAbortOperationMessage();
        } else {
          recipeBook.addRecipe(name, description, instructions, servings);
          output.promptForRecipeIngredients();
          int numberOfIngredients = input.intInput();
          for (int i = 0; i < numberOfIngredients; i++) {
            output.promptForIngredientName();
            String ingredientName = input.stringInput();
            output.promptForQuantity();
            double quantity = input.doubleInput();
            output.promptForUnit();
            String unit = input.stringInput();
            recipeBook.addIngredientToRecipe(name, ingredientName, quantity, unit);
          }
          output.addedRecipe(name);
        }
      }
    } catch (IllegalArgumentException e) {
      output.printInvalidInput(e.getMessage());
    } catch (Exception e) {
      output.printError(e.getMessage());
    }
  }

  /**
   * <p>Removes a recipe from the recipe book through user interaction.</p>
   *
   * <p>This method prompts the user for the name of the recipe to be removed.</p>
   * <p>If the recipe is found, the user is prompted to confirm the operation.</p>
   * <p>If the user chooses to abort the operation, a message is displayed.</p>
   * <p>If the recipe does not exist, an error message is displayed.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.removeRecipe();</code></pre>
   * </p>
   */
  public void removeRecipe() {
    try {
      output.promptForRecipeName();
      String name = input.stringInput();
      if (recipeBook.getRecipe(name) == null) {
        output.printRecipeNotFound(name);
      } else {
        output.printWarning();
        if (input.stringInput().equalsIgnoreCase("n")) {
          output.printAbortOperationMessage();
        } else {
          recipeBook.removeRecipe(name);
          output.removedRecipe(name);
        }
      }
    } catch (IllegalArgumentException e) {
      output.printInvalidInput(e.getMessage());
    } catch (Exception e) {
      output.printError(e.getMessage());
    }
  }

  /**
   * <p>Finds a recipe by its name.
   * If the recipe is found, all its details are displayed.
   * If the recipe does not exist, an error message is displayed.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.findRecipe();</code></pre>
   */
  public void findRecipe() {
    output.promptForRecipeName();
    String name = input.stringInput();

    if (recipeBook.getRecipe(name) != null) {
      String recipeName = recipeBook.getRecipe(name).getName();
      String recipeDescription = recipeBook.getRecipe(name).getDescription();
      String recipeInstruction = recipeBook.getRecipe(name).getInstruction();
      double recipeServings = recipeBook.getRecipe(name).getServings();
      output.printRecipeDetails(recipeName, recipeDescription, recipeInstruction, recipeServings);

      Iterator<Ingredient> ingredients = recipeBook.getRecipe(name).getRequiredIngredients();

      while (ingredients.hasNext()) {
        Ingredient ingredient = ingredients.next();
        output.printRecipeIngredients(ingredient.getName(),
            ingredient.getQuantity(), ingredient.getUnit());
      }
    } else {
      output.printRecipeNotFound(name);
    }
  }

  /**
   * <p>Displays a list of all recipes present in the recipe book.
   * This method uses an iterator to retrieve and display the names of all recipes in the book.</p>
   *
   * <p>If there are no recipes in the book, a message is displayed
   * to inform the user that the book is empty.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.displayRecipes();</code></pre>
   * </p>
   */
  public void displayRecipes() {
    Iterator<String> recipesIterator = recipeBook.getListOfRecipes();
    boolean hasRecipes = recipesIterator != null && recipesIterator.hasNext();
    output.printListOfRecipes(recipesIterator, hasRecipes);
  }

  /**
   * <p>Checks if a recipe can be made based on the ingredients available in the storage.</p>
   *
   * <p>This method prompts the user for the name of the recipe to be checked.</p>
   * <p>If the recipe does not exist, an error message is displayed.</p>
   * <p>If the recipe exists, the method checks if all the required ingredients are available
   * in the storage and in the required quantity.</p>
   *
   * <p>If all ingredients are available in the required quantity, a message is displayed
   * to inform the user that the recipe can be made.</p>
   * <p>If any ingredient is missing or not available in
   * the required quantity, a message is displayed
   * to inform the user that the recipe cannot be made.</p>
   *
   * <p><b>Example of usage:</b></p>
   * <pre><code>userInterface.checkIfRecipeCanBeMade();</code></pre>
   * </p>
   */
  public void checkIfRecipeCanBeMade() {
    output.promptForRecipeName();
    String name = input.stringInput();

    if (recipeBook.getRecipe(name) == null) {
      output.recipeExists(name);
      return;
    }
    Iterator<Ingredient> requiredIngredients = recipeBook.getRecipe(name).getRequiredIngredients();
    boolean canMakeRecipe = true;

    while (requiredIngredients.hasNext()) {
      Ingredient currentRequiredIngredient = requiredIngredients.next();
      String requiredIngredientName = currentRequiredIngredient.getName();
      double requiredQuantity = currentRequiredIngredient.getQuantity();
      String requiredUnit = currentRequiredIngredient.getUnit();

      if (foodStorage.isIngredientExisting(requiredIngredientName)) {
        Ingredient availableIngredient = foodStorage.getIngredient(requiredIngredientName);
        double availableQuantity = availableIngredient.getQuantity();
        String availableUnit = availableIngredient.getUnit();

        if (availableQuantity >= requiredQuantity) {
          output.printIngredientAvailable(requiredQuantity, requiredUnit, requiredIngredientName);
        } else {
          output.printInsufficientIngredientAmount(requiredQuantity,
              requiredUnit, requiredIngredientName, availableQuantity, availableUnit);
          canMakeRecipe = false;
        }
      } else {
        output.printMissingIngredient(requiredQuantity, requiredUnit, requiredIngredientName);
        canMakeRecipe = false;
      }
    }
    if (canMakeRecipe) {
      output.printRecipeCanBeMade(name);
    } else {
      output.printRecipeCannotBeMade(name);
    }
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
   *   <li>Access the food storage menu</li>
   *   <li>Access the recipe book menu</li>
   *   <li>Add an ingredient</li>
   *   <li>Reduce the quantity of an ingredient</li>
   *   <li>Change the description of an ingredient</li>
   *   <li>Change the price of an ingredient</li>
   *   <li>Find an ingredient</li>
   *   <li>Display a list of all ingredients</li>
   *   <li>Display ingredients in alphabetical order</li>
   *   <li>Display expired ingredients</li>
   *   <li>Display ingredients based on a specific expiration date</li>
   *   <li>Display the total value of all ingredients</li>
   *   <li>Display the total value of expired ingredients</li>
   *   <li>Add a recipe</li>
   *   <li>Remove a recipe</li>
   *   <li>Find a recipe</li>
   *   <li>Display a list of all recipes</li>
   *   <li>Check if a recipe can be made</li>
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
            output.printMainMenu();
          }
        }
        case "/food-storage" -> output.printFoodStorageMenu();
        case "/recipe-book" -> output.printRecipeBookMenu();
        case "/add" -> addIngredient();
        case "/change-description" -> changeDescription();
        case "/change-price" -> changePrice();
        case "/reduce" -> reduceIngredient();
        case "/list-ingredients" -> displayListOfIngredients();
        case "/find-ingredient" -> findIngredient();
        case "/sort" -> displayListOfIngredientsAlphabetically();
        case "/expired" -> displayListOfExpiredIngredients();
        case "/date" -> displayListOfIngredientsByExpirationDate();
        case "/value" -> displayValueOfAllIngredients();
        case "/value-expired" -> displayValueOfExpiredIngredients();
        case "/add-recipe" -> addRecipe();
        case "/remove-recipe" -> removeRecipe();
        case "/find-recipe" -> findRecipe();
        case "/list-recipes" -> displayRecipes();
        case "/check-recipe" -> checkIfRecipeCanBeMade();
        default -> output.printInvalidInput(userChoice);
      }
      if (!(userChoice.equalsIgnoreCase("/food-storage")
          || userChoice.equalsIgnoreCase("0") || userChoice.equalsIgnoreCase("/recipe-book"))) {
        output.printMainMenu();
      }
    }
  }
}