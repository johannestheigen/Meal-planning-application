package edu.ntnu.idi.bidata.app;

import edu.ntnu.idi.bidata.common.Unit;
import edu.ntnu.idi.bidata.common.UnitConverter;
import edu.ntnu.idi.bidata.items.Ingredient;
import edu.ntnu.idi.bidata.register.FoodStorage;
import edu.ntnu.idi.bidata.register.RecipeBook;
import edu.ntnu.idi.bidata.utility.InputParser;
import edu.ntnu.idi.bidata.utility.InputValidator;
import edu.ntnu.idi.bidata.utility.OutputHandler;
import java.time.LocalDate;
import java.util.Iterator;

/**
 * <p>The UserInterface class manages interactions between the
 * application and the user.</p>
 *
 *
 * @version 0.3.5
 * @since 11.28.2024
 */
public class UserInterface {

  private FoodStorage foodStorage;
  private RecipeBook recipeBook;
  private OutputHandler outputHandler;
  private InputParser inputParser;
  private InputValidator inputValidator;

  /**
   * <p>Initializes the application at startup by creating instances of
   * <code>FoodStorage</code>, <code>RecipeBook</code>,
   * <code>OutputHandler</code>, and <code>InputParser</code>.</p>
   */

  public void init() {
    foodStorage = new FoodStorage();
    recipeBook = new RecipeBook();
    outputHandler = new OutputHandler();
    inputParser = new InputParser();
    inputValidator = new InputValidator();
    foodStorage.addIngredient("Milk", 900, Unit.MILLILITER, 0.2, LocalDate.of(2024, 12, 1));
    userInput();
  }

  /**
   * <p>Starts the application by initializing the application.</p>
   */
  public void start() {
    init();
  }

  /**
   * <p>This method is used to after an operation is completed or aborted to
   *  return the user to the main menu. The user is prompted to press any key</p>
   */
  public void pressAnyKeyToContinue() {
    outputHandler.printPressAnyKey();
    inputValidator.isAnyKeyPressed(inputParser.stringInput());
    outputHandler.printMainMenu();
  }

  /**
   * <p>Adds a new ingredient to the storage through user interaction.</p>
   */
  public void addIngredient() {
    try {
      String name = getIngredientNameInput();

      double quantity = getQuantityInput();

      Unit unit = getUnitInput();

      double price = getPriceInput();

      LocalDate expirationDate = getExpirationDateInput();

      handleIngredientAddition(name, quantity, unit, price, expirationDate);
      pressAnyKeyToContinue();
    } catch (IllegalArgumentException e) {
      outputHandler.printInvalidInput(e.getMessage());
    } catch (Exception e) {
      outputHandler.printError(e.getMessage());
    }
  }

  /**
   * <p>Prompts the user for the name of the ingredient to be added.</p>
   * <p>If the user provides an empty string, an error message is displayed.</p>

   * @return name of the ingredient
   */
  private String getIngredientNameInput() {
    outputHandler.promptForIngredientName();
    String name = inputParser.stringInput();
    while (!inputValidator.isNonEmptyString(name)) {
      outputHandler.printInvalidQuantity();
      name = inputParser.stringInput();
    }
    return name;
  }

  /**
   * <p>Prompts the user for the quantity of the ingredient to be added.</p>
   * <p>If the user provides a negative value, an error message is displayed.</p>

   * @return quantity of the ingredient
   */
  private double getQuantityInput() {
    outputHandler.promptForQuantity();
    double quantity = inputParser.doubleInput();

    while (!inputValidator.isPositiveDouble(quantity)) {
      outputHandler.printInvalidQuantity();
      quantity = inputParser.doubleInput();
    }
    return quantity;
  }

  /**
   * <p>Prompts the user for the unit of the ingredient to be added.</p>
   * <p>If the user provides an invalid unit, an error message is displayed.</p>

   * @return unit of the ingredient
   */
  private Unit getUnitInput() {
    while (true) {
      outputHandler.promptForUnit();
      String input = inputParser.stringInput().trim();

      for (Unit unit : Unit.values()) {
        if (unit.name().equalsIgnoreCase(input) || unit.getSymbol().equalsIgnoreCase(input)) {
          return unit;
        }
      }
      outputHandler.printInvalidUnit();
    }
  }


  /**
   * <p>Prompts the user for the price of the ingredient to be added.</p>
   * <p>If the user provides a negative value, an error message is displayed.</p>

   * @return price of the ingredient
   */
  private double getPriceInput() {
    outputHandler.promptForPrice();
    double price = inputParser.doubleInput();
    while (!inputValidator.isPositiveDouble(price)) {
      outputHandler.printInvalidPrice();
      price = inputParser.doubleInput();
    }
    return price;
  }

  /**
   * <p>Prompts the user for the expiration date of the ingredient to be added.</p>
   * <p>If the user provides an invalid date, an error message is displayed.</p>

   * @return expiration date of the ingredient
   */
  private LocalDate getExpirationDateInput() {
    outputHandler.promptForExpirationDate();
    return inputParser.expirationDateInput();
  }

  /**
   * <p>Handles the addition of an ingredient. If the ingredient already exists in the storage,
   * the quantity of the ingredient is updated. The user is prompted to confirm the operation.
   * If the user chooses to abort the operation,
   * a message is displayed. If the user confirms the operation,
   * the quantity of the ingredient is updated.
   * If the ingredient does not exist, it is added to the storage.</p>
   *
   * @param name ingredient name
   * @param quantity ingredient quantity
   * @param unit ingredient unit
   * @param price ingredient price
   * @param expirationDate ingredient expiration date
   */
  private void handleIngredientAddition(String name, double quantity,
                                        Unit unit, double price, LocalDate expirationDate) {
    outputHandler.printWarning();
    if (inputValidator.isAbortOperation(inputParser.stringInput())) {
      outputHandler.printAbortOperationMessage();
      pressAnyKeyToContinue();
      return;
    }

    if (foodStorage.isIngredientExisting(name)) {
      Ingredient existingIngredient = foodStorage.getIngredient(name);
      if (!UnitConverter.isValidConversion(existingIngredient.getUnit(), unit)) {
        outputHandler.printInvalidUnitConversion(existingIngredient.getUnit(), unit);
        return;
      }
      foodStorage.addIngredient(name, quantity, unit, price, expirationDate);
      outputHandler.printUpdatedQuantity(name);
    } else {
      foodStorage.addIngredient(name, quantity, unit, price, expirationDate);
      outputHandler.printAddedIngredient(name);
    }
  }

  /**
   * <p>Removes an ingredient entirely from the storage through user interaction.</p>
   * <p>If the ingredient does not exist, an error message is displayed.</p>
   */
  public void removeIngredient() {
    try {
      String name = getIngredientNameInput();
      if (!foodStorage.isIngredientExisting(name)) {
        outputHandler.printIngredient(name, false);
      } else {
        handleIngredientRemoval(name);
      }
      pressAnyKeyToContinue();
    } catch (IllegalArgumentException e) {
      outputHandler.printInvalidInput(e.getMessage());
    } catch (Exception e) {
      outputHandler.printError(e.getMessage());
    }
  }

  /**
   * <p>Handles the removal of an ingredient. If the user confirms the operation,
   * the ingredient is removed.
   * If the user chooses to abort the operation, a message is displayed.</p>

   * @param name ingredient name
   */
  private void handleIngredientRemoval(String name) {
    outputHandler.printWarning();
    if (inputValidator.isAbortOperation(inputParser.stringInput())) {
      outputHandler.printAbortOperationMessage();
      pressAnyKeyToContinue();
    } else {
      foodStorage.removeIngredient(name);
      outputHandler.printRemovedIngredient(name);
    }
  }

  /**
   * <p>Reduces the quantity of an ingredient in the storage through user interaction.</p>
   * <p>If the ingredient does not exist, an error message is displayed.</p>
   */
  public void reduceQuantity() {
    try {
      String name = getIngredientNameInput();
      double quantity = getQuantityInput();

      if (!foodStorage.isIngredientExisting(name)) {
        outputHandler.printIngredient(name, false);
      } else {
        handleIngredientReduction(name, quantity);
      }
      pressAnyKeyToContinue();
    } catch (IllegalArgumentException e) {
      outputHandler.printInvalidInput(e.getMessage());
    } catch (Exception e) {
      outputHandler.printError(e.getMessage());
    }
  }

  /**
   * <p>Handles the reduction of an ingredient. If the ingredient's quantity becomes less than 1
   * after the reduction, the ingredient is completely removed from the storage.
   * If the ingredient does not exist, an error message is displayed.</p>

   * @param name ingredient name
   * @param quantity quantity to reduce
   */
  private void handleIngredientReduction(String name, double quantity) {
    outputHandler.printWarning();
    if (inputValidator.isAbortOperation(inputParser.stringInput())) {
      outputHandler.printAbortOperationMessage();
    } else {
      foodStorage.reduceQuantity(name, quantity);
      if (inputValidator.ingredientWasReduced(foodStorage, name, quantity)) {
        outputHandler.printUpdatedQuantity(name);
      } else {
        outputHandler.printRemovedIngredient(name);
      }
    }
  }

  /**
   * <p>Changes the price of an ingredient through user interaction.</p>
   * <p>If the ingredient does not exist, an error message is displayed.</p>
   */
  public void changePrice() {
    try {
      String name = getIngredientNameInput();
      if (!foodStorage.isIngredientExisting(name)) {
        outputHandler.printIngredient(name, false);
      } else {
        double newPrice = getPriceInput();
        handlePriceChange(name, newPrice);
      }
      pressAnyKeyToContinue();
    } catch (IllegalArgumentException e) {
      outputHandler.printInvalidInput(e.getMessage());
    } catch (Exception e) {
      outputHandler.printError(e.getMessage());
    }
  }

  /**
   * <p>Handles the change of price for an ingredient.
   * If the user confirms the operation, the price of the ingredient is updated.
   * If the user chooses to abort the operation, a message is displayed.</p>

   * @param name ingredient name
   * @param newPrice new price for the ingredient
   */
  private void handlePriceChange(String name, double newPrice) {
    outputHandler.printWarning();
    if (inputValidator.isAbortOperation(inputParser.stringInput())) {
      outputHandler.printAbortOperationMessage();
    } else {
      foodStorage.updatePrice(name, newPrice);
      outputHandler.printUpdatedPrice(name);
    }
  }

  /**
   * Changes the unit of an ingredient through user interaction.
   * If the ingredient does not exist, an error message is displayed.
   */
  public void changeUnit() {
    try {
      String name = getIngredientNameInput();
      Unit newUnit = getUnitInput();

      if (!foodStorage.isIngredientExisting(name)) {
        outputHandler.printIngredient(name, false);
        return;
      }
      Unit currentUnit = foodStorage.getIngredient(name).getUnit();
      handleUnitChange(currentUnit, newUnit, name);
      pressAnyKeyToContinue();
    } catch (IllegalArgumentException e) {
      outputHandler.printInvalidInput(e.getMessage());
    } catch (Exception e) {
      outputHandler.printError(e.getMessage());
    }
  }

  /**
   * <p>Handles the change of unit for an ingredient.
   * If the user confirms the operation, the unit of the ingredient is updated.
   * If the user chooses to abort the operation, a message is displayed.</p>

   * @param name ingredient name
   * @param newUnit new unit for the ingredient
   */
  private void handleUnitChange(Unit currentUnit, Unit newUnit, String name) {
    if (!UnitConverter.isValidConversion(currentUnit, newUnit)) {
      outputHandler.printInvalidUnitConversion(currentUnit, newUnit);
      pressAnyKeyToContinue();
      return;
    }
    outputHandler.printWarning();
    if (inputValidator.isAbortOperation(inputParser.stringInput())) {
      outputHandler.printAbortOperationMessage();
    } else {
      foodStorage.updateUnit(name, newUnit);
      outputHandler.printUpdatedUnit(name);
    }
  }

  /**
   * <p>Finds an ingredient by its name.
   * If the ingredient is found, all its details is displayed.
   * If the ingredient does not exist, an error message is displayed.</p>
   */

  public void findIngredient() {
    String ingredientName = getIngredientNameInput();
    if (foodStorage.isIngredientExisting(ingredientName)) {
      outputHandler.printIngredientDetails(ingredientName,
          foodStorage.getIngredient(ingredientName).getQuantity(),
          foodStorage.getIngredient(ingredientName).getUnit().getSymbol(),
          foodStorage.getIngredient(ingredientName).getPrice(),
          foodStorage.getIngredient(ingredientName).getExpirationDate());
    } else {
      outputHandler.printIngredient(ingredientName, false);
    }
    pressAnyKeyToContinue();
  }

  /**
   * <p>Displays a list of all ingredients present in the foodStorage.
   * This method uses an iterator to retrieve
   * and display the names of all ingredients in the storage.</p>
   *
   * <p>If there are no ingredients in the storage, a message is displayed
   * to inform the user that the storage is empty.</p>
   */
  public void displayListOfIngredients() {
    Iterator<String> ingredientsIterator = foodStorage.getListOfIngredients();
    boolean hasIngredients = ingredientsIterator != null && ingredientsIterator.hasNext();
    outputHandler.printListOfIngredients(ingredientsIterator, hasIngredients);
    pressAnyKeyToContinue();
  }

  /**
   * <p>Displays a list of all ingredients present in the foodStorage in alphabetical order.
   * This method uses an iterator to retrieve and
   * sort the ingredient names in alphabetical order.</p>
   *
   * <p>If there are no ingredients in the storage, a message is displayed
   * to inform the user that the storage is empty.</p>
   */
  public void displayListOfIngredientsAlphabetically() {
    Iterator<String> ingredientsIterator = foodStorage.getListOfIngredientsAlphabetically();
    boolean hasIngredients = ingredientsIterator != null && ingredientsIterator.hasNext();
    outputHandler.printListOfIngredientsAlphabetically(ingredientsIterator, hasIngredients);
    pressAnyKeyToContinue();
  }

  /**
   * <p>Displays a list of all expired ingredients present in the foodStorage.
   * This method uses an iterator to retrieve,
   * filter the ingredients based on their expiration date.</p>
   *
   * <p>If there are no expired ingredients in the storage, a message is displayed
   * to inform the user that there are no expired ingredients.</p>
   */
  public void displayListOfExpiredIngredients() {
    Iterator<String> ingredientsIterator = foodStorage.getListOfExpiredIngredients();
    boolean hasExpiredIngredients = ingredientsIterator != null && ingredientsIterator.hasNext();
    outputHandler.printListOfExpiredIngredients(ingredientsIterator, hasExpiredIngredients);
    pressAnyKeyToContinue();
  }

  /**
   * <p>Displays a list of all ingredients present
   * in the foodStorage that have given expiration date.</p>
   *
   * <p>If there are no ingredients with the provided expiration date in the storage,
   * a message is displayed to inform the user that
   * there are no ingredients with that expiration date.</p>
   */
  public void displayListOfIngredientsByExpirationDate() {
    outputHandler.promptForExpirationDate();
    LocalDate expirationDate = inputParser.expirationDateInput();

    Iterator<String> ingredientsIterator
        = foodStorage.getListOfIngredientsByExpirationDate(expirationDate);

    boolean hasIngredients = ingredientsIterator != null && ingredientsIterator.hasNext();

    outputHandler.printListOfIngredientsByExpirationDate(ingredientsIterator, hasIngredients);
    pressAnyKeyToContinue();
  }

  /**
   * <p>Displays the value of all ingredients present in the storage.
   * This method calculates the total value of all ingredients based on their price and quantity,
   * and then outputs the result.</p>
   *
   * <p>If there are no ingredients in the storage, a message is displayed
   * to inform the user that the storage is empty.</p>
   */
  public void displayTotalValueOfAllIngredients() {
    outputHandler.printTotalValueOfAllIngredients(foodStorage.getValueOfAllIngredients());
    pressAnyKeyToContinue();
  }

  /**
   * <p>Displays the value of a single ingredient present in the storage.
   * This method calculates the total value of the ingredient based on its price and quantity,
   * and then outputs the result.</p>
   *
   * <p>If the ingredient does not exist, an error message is displayed.</p>
   */
  public void displayTotalValueOfIngredient() {
    String ingredientName = getIngredientNameInput();
    outputHandler.printTotalValueOfIngredient(foodStorage
        .getValueOfSingleIngredient(ingredientName));
    pressAnyKeyToContinue();
  }

  /**
   * <p>Displays the value of all  expired ingredients present in the storage.
   * This method calculates the total value of all expired ingredients
   * based on their price and quantity,
   * and then outputs the result.</p>
   *
   * <p>If there are no expired ingredients in the storage, a message is displayed
   * to inform the user that there are no expired ingredients.</p>
   */
  public void displayTotalValueOfExpiredIngredients() {
    outputHandler.printTotalValueOfExpiredIngredients(foodStorage.getValueOfExpiredIngredients());
    pressAnyKeyToContinue();
  }

  /**
   * <p>Adds a new recipe to the recipe book through user interaction.</p>
   * <p>If the recipe already exists in the recipe book, an error message is displayed.</p>
   */
  public void addRecipe() {
    try {
      String nameOfRecipe = getRecipeNameInput();

      if (recipeBook.getRecipe(nameOfRecipe) != null) {
        outputHandler.recipeExists(nameOfRecipe);
      } else {
        String description = getDescriptionInput();

        String instruction = getInstructionInput();

        double servings = getServingsInput();

        handleRecipeAddition(nameOfRecipe, description, instruction, servings);
      }
      pressAnyKeyToContinue();
    } catch (IllegalArgumentException e) {
      outputHandler.printInvalidInput(e.getMessage());
    } catch (Exception e) {
      outputHandler.printError(e.getMessage());
    }
  }

  /**
   * <p>Prompts the user for the name of the recipe to be added.</p>
   * <p>If the user provides an empty string, an error message is displayed.</p>

   * @return name of the recipe
   */
  private String getRecipeNameInput() {
    outputHandler.promptForRecipeName();
    String nameOfRecipe = inputParser.stringInput();
    while (!inputValidator.isNonEmptyString(nameOfRecipe)) {
      outputHandler.printInvalidRecipeName();
      nameOfRecipe = inputParser.stringInput();
    }
    return nameOfRecipe;
  }

  /**
   * <p>Prompts the user for the description of the recipe to be added.</p>
   * <p>If the user provides an empty string, an error message is displayed.</p>

   * @return description of the recipe
   */
  private String getDescriptionInput() {
    outputHandler.promptForRecipeDescription();
    String description = inputParser.stringInput();
    while (!inputValidator.isNonEmptyString(description)) {
      outputHandler.printInvalidRecipeDescription();
    }
    return description;
  }

  /**
   * <p>Prompts the user for the instructions of the recipe to be added.</p>
   * <p>If the user provides an empty string, an error message is displayed.</p>

   * @return instructions of the recipe
   */
  private String getInstructionInput() {
    outputHandler.promptForRecipeInstruction();
    String instructions = inputParser.stringInput();
    while (!inputValidator.isNonEmptyString(instructions)) {
      outputHandler.printInvalidRecipeInstructions();
      instructions = inputParser.stringInput();
    }
    return instructions;
  }

  /**
   * <p>Prompts the user for the number of servings for the recipe to be added.</p>
   * <p>If the user provides a negative value, an error message is displayed.</p>

   * @return number of servings for the recipe
   */
  private double getServingsInput() {
    outputHandler.promptForRecipeServings();
    double servings = inputParser.doubleInput();
    while (!inputValidator.isPositiveDouble(servings)) {
      outputHandler.printInvalidServings();
      servings = inputParser.doubleInput();
    }
    return servings;
  }

  /**
   * <p>Handles the addition of a recipe. If the user confirms the operation, the recipe is added.
   * If the user chooses to abort the operation, a message is displayed.</p>

   * @param nameOfRecipe name of the recipe
   * @param description description of the recipe
   * @param instructions instructions for the recipe
   * @param servings number of servings for the recipe
   */
  private void handleRecipeAddition(String nameOfRecipe, String description,
                                    String instructions, double servings) {
    outputHandler.printWarning();
    if (inputValidator.isAbortOperation(inputParser.stringInput())) {
      outputHandler.printAbortOperationMessage();
      pressAnyKeyToContinue();
    } else {
      recipeBook.addRecipe(nameOfRecipe, description, instructions, servings);
      handleAdditionOfIngredients(nameOfRecipe);
      outputHandler.addedRecipe(nameOfRecipe);
    }
  }

  /**
   * <p>Handles the addition of ingredients to a recipe.
   * The user is prompted for the number of ingredients
   * to be added to the recipe. The user is then prompted for the name,
   * quantity, and unit of each ingredient.</p>
   * <p>If the user provides invalid values, an error message is displayed.</p>
   * <p>Once all ingredients are added, the recipe is updated with the new ingredients.</p>

   * @param nameOfRecipe name of the recipe
   */
  private void handleAdditionOfIngredients(String nameOfRecipe) {
    int numberOfIngredients = getNumberOfIngredientsInput();

    for (int i = 0; i < numberOfIngredients; i++) {
      String ingredientName = getIngredientNameInput();
      double quantity = getQuantityInput();
      Unit unit = getUnitInput();
      recipeBook.addIngredientToRecipe(nameOfRecipe, ingredientName, quantity, unit);
    }
  }

  /**
   * <p>Prompts the user for the number of ingredients to be added to the recipe.</p>
   * <p>If the user provides a negative value, an error message is displayed.</p>

   * @return number of ingredients to be added to the recipe
   */
  private int getNumberOfIngredientsInput() {
    outputHandler.promptForRecipeIngredients();
    int numberOfIngredients = inputParser.intInput();
    while (!inputValidator.isPositiveInteger(numberOfIngredients)) {
      outputHandler.printInvalidNumberOfIngredients();
      numberOfIngredients = inputParser.intInput();
    }
    return numberOfIngredients;
  }

  /**
   * <p>Removes a recipe from the recipe book through user interaction.</p>
   * <p>If the recipe does not exist, an error message is displayed.</p>
   */
  public void removeRecipe() {
    try {
      String name = getRecipeNameInput();
      if (!recipeBook.isRecipeExisting(name)) {
        outputHandler.printRecipeNotFound(name);
      } else {
        handleRecipeRemoval(name);
      }
      pressAnyKeyToContinue();
    } catch (IllegalArgumentException e) {
      outputHandler.printInvalidInput(e.getMessage());
    } catch (Exception e) {
      outputHandler.printError(e.getMessage());
    }
  }

  /**
   * <p>Handles the removal of a recipe. If the user confirms the operation, the recipe is removed.
   * If the user chooses to abort the operation, a message is displayed.</p>

   * @param name recipe name
   */
  private void handleRecipeRemoval(String name) {
    outputHandler.printWarning();
    if (inputValidator.isAbortOperation(inputParser.stringInput())) {
      outputHandler.printAbortOperationMessage();
    } else {
      recipeBook.removeRecipe(name);
      outputHandler.removedRecipe(name);
    }
  }

  /**
   * <p>Finds a recipe by its name.
   * If the recipe is found, all its details are displayed.
   * If the recipe does not exist, an error message is displayed.</p>
   */
  public void findRecipe() {
    String name = getRecipeNameInput();

    if (!recipeBook.isRecipeExisting(name)) {
      outputHandler.printRecipeNotFound(name);
    } else {
      outputHandler.printRecipeDetails(
          recipeBook.getRecipe(name).getName(),
          recipeBook.getRecipe(name).getDescription(),
          recipeBook.getRecipe(name).getInstruction(),
          recipeBook.getRecipe(name).getServings());

      recipeBook.getRecipe(name).getRequiredIngredients().forEachRemaining(ingredient ->
          outputHandler.printRecipeIngredients(ingredient.getName(),
              ingredient.getQuantity(), ingredient.getUnit())
      );
    }
    pressAnyKeyToContinue();
  }

  /**
   * <p>Displays a list of all recipes present in the recipe book.
   * This method uses an iterator to retrieve and display the names of all recipes in the book.</p>
   *
   * <p>If there are no recipes in the book, a message is displayed
   * to inform the user that the book is empty.</p>
   */
  public void displayRecipes() {
    Iterator<String> recipesIterator = recipeBook.getListOfRecipes();
    boolean hasRecipes = recipesIterator != null && recipesIterator.hasNext();
    outputHandler.printListOfRecipes(recipesIterator, hasRecipes);
    pressAnyKeyToContinue();
  }

  /**
   * <p>Checks if a recipe can be made based on the ingredients available in the storage.</p>
   * <p>If the recipe does not exist, an error message is displayed.</p>
   */
  public void checkIfRecipeCanBeMade() {
    String name = getRecipeNameInput();

    if (!recipeBook.isRecipeExisting(name)) {
      outputHandler.printRecipeNotFound(name);
      return;
    }
    evaluateRecipeAvailability(name);
  }

  /**
   * <p>Evaluates if a recipe can be made based on the ingredients available in the storage.
   * If the recipe can be made, a message is displayed
   * to inform the user that the recipe can be made.
   * If the recipe cannot be made, a message is displayed
   * to inform the user that the recipe cannot be made.</p>

   * @param name recipe name
   */
  private void evaluateRecipeAvailability(String name) {
    if (recipeBook.canRecipeBeMade(name, foodStorage)) {
      outputHandler.printRecipeCanBeMade(name);
    } else {
      outputHandler.printRecipeCannotBeMade(name);
      processMissingIngredients(name);
    }
  }

  /**
   * <p>Processes missing ingredients for a recipe.
   * If an ingredient is missing, a message is displayed to inform the user
   * that the ingredient is missing.
   * If an ingredient is insufficient, a message
   * is displayed to inform the user that the ingredient is insufficient.</p>

   * @param name recipe name
   */
  private void processMissingIngredients(String name) {
    Iterator<Ingredient> requiredIngredients = recipeBook.getRecipe(name).getRequiredIngredients();
    while (requiredIngredients.hasNext()) {
      Ingredient requiredIngredient = requiredIngredients.next();

      if (recipeBook.isIngredientMissing(requiredIngredient, foodStorage)) {
        outputHandler.printMissingIngredient(
            requiredIngredient.getQuantity(),
            requiredIngredient.getUnit(),
            requiredIngredient.getName()
        );
      } else if (recipeBook.isIngredientInsufficient(requiredIngredient, foodStorage)) {
        Ingredient availableIngredient = foodStorage.getIngredient(requiredIngredient.getName());
        outputHandler.printInsufficientIngredientAmount(
            requiredIngredient.getQuantity(),
            requiredIngredient.getUnit(),
            requiredIngredient.getName(),
            availableIngredient.getQuantity(),
            availableIngredient.getUnit()
        );
      }
    }
  }

  /**
   * <p>Changes the serving size of a recipe through user interaction.</p>
   * <p>If the recipe does not exist, an error message is displayed.</p>
   */
  public void changeServing() {
    try {
      String name = getRecipeNameInput();

      if (!recipeBook.isRecipeExisting(name)) {
        outputHandler.printRecipeNotFound(name);
        pressAnyKeyToContinue();
        return;
      }
      double newServing = getServingsInput();

      handleNewServing(name, newServing);
      pressAnyKeyToContinue();

    } catch (IllegalArgumentException e) {
      outputHandler.printInvalidInput(e.getMessage());
    } catch (Exception e) {
      outputHandler.printError(e.getMessage());
    }
  }

  private void handleNewServing(String name, double newServing) {
    outputHandler.printWarning();
    if (inputValidator.isAbortOperation(inputParser.stringInput())) {
      outputHandler.printAbortOperationMessage();
      pressAnyKeyToContinue();
    } else {
      recipeBook.updateServing(name, newServing);
      outputHandler.printUpdatedServing(name);
    }
  }

  /**
   * Handles the interaction between the user and the application.
   * <p>
   * This method displays the main menu and processes the user's choices by calling the correct
   * methods based on the user's input.
   * The application will keep running until the user chooses to exit.
   * </p>
   *
   */
  public void userInput() {
    outputHandler.printMainMenu();
    boolean running = true;
    while (running) {
      String userChoice = inputParser.stringInput();

      switch (userChoice.toLowerCase()) {
        case "0" -> {
          outputHandler.printExitWarning();
          if (inputValidator.isExiting(inputParser.stringInput())) {
            outputHandler.printExitMessage();
            inputParser.close();
            running = false;
          } else {
            outputHandler.printAbortOperationMessage();
            outputHandler.printMainMenu();
          }
        }
        case "/main" -> outputHandler.printMainMenu();
        case "/storage" -> outputHandler.printFoodStorageMenu();
        case "/recipes" -> outputHandler.printRecipeBookMenu();
        case "/add-ing" -> addIngredient();
        case "/edit-price" -> changePrice();
        case "/del-ing" -> removeIngredient();
        case "/reduce-ing" -> reduceQuantity();
        case "/edit-unit" -> changeUnit();
        case "/list-ing" -> displayListOfIngredients();
        case "/find-ing" -> findIngredient();
        case "/sort-ing" -> displayListOfIngredientsAlphabetically();
        case "/expired" -> displayListOfExpiredIngredients();
        case "/by-date" -> displayListOfIngredientsByExpirationDate();
        case "/total-val" -> displayTotalValueOfAllIngredients();
        case "single-val" -> displayTotalValueOfIngredient();
        case "/expired-val" -> displayTotalValueOfExpiredIngredients();
        case "/add-rec" -> addRecipe();
        case "/del-rec" -> removeRecipe();
        case "/edit-serv" -> changeServing();
        case "/find-rec" -> findRecipe();
        case "/list-rec" -> displayRecipes();
        case "/check-rec" -> checkIfRecipeCanBeMade();
        default -> {
          outputHandler.printInvalidInput(userChoice);
          pressAnyKeyToContinue();
        }
      }
    }
  }
}