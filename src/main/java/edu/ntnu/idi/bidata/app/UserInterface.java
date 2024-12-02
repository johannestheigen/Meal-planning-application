package edu.ntnu.idi.bidata.app;

import edu.ntnu.idi.bidata.items.Ingredient;
import edu.ntnu.idi.bidata.recipe.Recipe;
import edu.ntnu.idi.bidata.register.FoodStorage;
import edu.ntnu.idi.bidata.register.RecipeBook;
import edu.ntnu.idi.bidata.utility.InputParser;
import edu.ntnu.idi.bidata.utility.InputValidator;
import edu.ntnu.idi.bidata.utility.OutputHandler;
import java.time.LocalDate;
import java.util.Iterator;

/**
 * <p>The UserInterface class manages interactions between the
 * application and the user. It displays a text-based menu
 * to the user and processes the user's choices by calling the
 * correct methods based on the user's input and delegates
 * it to the appropriate classes responsible for handling the
 * operations. (e.g FoodStorage).</p>
 *
 * @author Johannes Nupen Theigen
 * @version 0.4.2
 * @since 12.02.2024
 */
public class UserInterface {

  private FoodStorage foodStorage;
  private RecipeBook recipeBook;
  private OutputHandler outputHandler;
  private InputParser inputParser;
  private InputValidator inputValidator;

  /**
   * <p>Initializes the application at startup by creating instances of
   * FoodStorage, RecipeBook, OutputHandler, InputParser, and InputValidator.</p>
   */
  public void init() {
    try {
      foodStorage = new FoodStorage();
      recipeBook = new RecipeBook();
      outputHandler = new OutputHandler();
      inputParser = new InputParser();
      inputValidator = new InputValidator();
    } catch (Exception e) {
      handleException(e);
    } finally {
      userInput();
    }
  }

  /**
   * <p>Starts the application by calling the init method.</p>
   */
  public void start() {
    try {
      init();
    } catch (Exception e) {
      handleException(e);
    }
  }

  /*
   * Allows the user to abort an operation.
   * If the user chooses to abort the operation, a message is displayed.
   * to inform the user that the operation has been aborted.
   */
  private boolean abort() {
    outputHandler.printContinuationWarning();
    if (inputValidator.isAbortOperation(inputParser.stringInput())) {
      outputHandler.printAbortOperationMessage();
      pressAnyKeyToContinue();
      return true;
    }
    return false;
  }

  /*
    * Prompts the user to confirm if they want to exit the program.
    * If the user confirms the operation the program is terminated.
    * If the user chooses to abort, a message is displayed to
    * inform the user that they have chosen to abort the operation.
   */
  private boolean exitProgram() {
    outputHandler.printExitWarning();

    String confirmation = inputParser.stringInput(); // Get user input
    if (inputValidator.isExiting(confirmation)) {
      outputHandler.printExitMessage();
      inputParser.close();
      return true;
    }
    outputHandler.printAbortOperationMessage();
    outputHandler.printMainMenu();
    return false;
  }

  /*
   * Handles exceptions that occur during the execution of the application.
   * (e.g. IllegalArgumentException)
   * If an exception occurs, an error message is displayed,
   * and the user is then prompted to press any key to continue.
   */
  private void handleException(Exception e) {
    if (e instanceof IllegalArgumentException) {
      outputHandler.printInvalidInput(e.getMessage());
      pressAnyKeyToContinue();
    } else {
      outputHandler.printError(e.getMessage());
      pressAnyKeyToContinue();
    }
  }

  /**
   * <p>Displays a message to the user and prompts the user to press any key to continue
   * After the user presses any key, the main menu is displayed.</p>
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

      String unit = getUnitInput();

      double price = getPriceInput();

      LocalDate expirationDate = getExpirationDateInput();

      handleIngredientAddition(name, quantity, unit, price, expirationDate);
      pressAnyKeyToContinue();
    } catch (IllegalArgumentException e) {
      handleException(e);
    }
  }

  /*
   * Prompts the user for the name of the ingredient to be added.
   * If the user provides an empty string, an error message is displayed,
   * and the user is prompted to provide a valid name.
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

  /*
   * Prompts the user for the quantity of the ingredient to be added.
   * If the user provides a value that is zero or negative, an error message is displayed,
   * and the user is prompted to provide a valid quantity.
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

  /*
   * Prompts the user for the unit of the ingredient to be added.
   * If the user provides an invalid unit, an error message is displayed,
   * and the user is prompted to provide a valid unit.
   */
  private String getUnitInput() {
    outputHandler.promptForUnit();
    String unit = inputParser.stringInput();
    while (!inputValidator.isValidUnit(unit)) {
      outputHandler.printInvalidUnit();
      unit = inputParser.stringInput();
    }
    return unit;
  }

  /*
   * Prompts the user for the price of the ingredient to be added.
   * If the user provides a value that is zero or negative, an error message is displayed,
   * and the user is prompted to provide a valid price.
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

  /*
   * Prompts the user for the expiration date of the ingredient to be added.
   * If the user provides an invalid date format (e.g. 12-02-2024),
   * an error message is displayed, and the user is prompted to provide a valid date.
   */
  private LocalDate getExpirationDateInput() {
    outputHandler.promptForExpirationDate();
    return inputParser.expirationDateInput();
  }

  /*
   * Handles the addition of an ingredient. If the user confirms the operation,
   * the ingredient is added. If the user chooses to abort the operation,
   * a message is displayed. If the ingredient already exists in the storage,
   * the quantity of the ingredient is updated.
   */
  private void handleIngredientAddition(String name, double quantity,
                                        String unit, double price, LocalDate expirationDate) {
    if (abort()) {
      return;
    }
    if (foodStorage.isIngredientExisting(name)) {
      Ingredient existingIngredient = foodStorage.getIngredient(name);
      LocalDate existingExpirationDate = existingIngredient.getExpirationDate();

      handleExpirationDate(existingExpirationDate, expirationDate);

      if (foodStorage.isIngredientQuantityUpdated(name, quantity)) {
        outputHandler.printUpdatedQuantity(name);
      }
    } else {
      foodStorage.addIngredient(name, quantity, unit, price, expirationDate);
      outputHandler.printAddedIngredient(name);
    }
  }

  /*
   * Handles the expiration date of an ingredient.
   * If the new expiration date is older than the existing expiration date,
   * a message notifying the user that the user should smell or taste the ingredient is displayed.
   * If the new expiration date is newer than the existing expiration date,
   * a message is displayed to inform the user that the user should check if the
   * existing ingredient is still good to use.
   */
  private void handleExpirationDate(LocalDate existingExpirationDate, LocalDate newExpirationDate) {
    if (foodStorage.isExpirationDateEqual(existingExpirationDate, newExpirationDate)) {
      return;
    }
    if (foodStorage.isNewExpirationDateNewer(existingExpirationDate, newExpirationDate)) {
      outputHandler.printNewerExpirationDateUpdate(newExpirationDate, existingExpirationDate);
    } else {
      outputHandler.printExpirationDateWarning(newExpirationDate, existingExpirationDate);
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
    } catch (Exception e) {
      handleException(e);
    }
  }

  /*
   * Handles the removal of an ingredient. If the user confirms the operation,
   * the ingredient is removed. If the user chooses to abort the operation,
   * a message is displayed. In cases the user chooses to abort the operation,
   * a message is displayed to inform the user that the operation has been aborted.
   */
  private void handleIngredientRemoval(String name) {
    if (abort()) {
      return;
    }
    foodStorage.removeIngredient(name);
    outputHandler.printRemovedIngredient(name);
  }

  /**
   * <p>Reduces the quantity of an ingredient in the storage through user interaction.
   * If the ingredient does not exist, an error message is displayed.</p>
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
    } catch (Exception e) {
      handleException(e);
    }
  }

  /*
   * Handles the reduction of an ingredient. If ingredient's quantity becomes less than 1
   * after the reduction, the ingredient is completely removed from the storage.
   */
  private void handleIngredientReduction(String name, double quantity) {
    if (abort()) {
      return;
    }
    boolean updated = foodStorage.reduceQuantity(name, quantity);

    if (updated) {
      outputHandler.printUpdatedQuantity(name);
    } else {
      outputHandler.printRemovedIngredient(name);
    }
  }

  /**
   * <p>Changes the price of an ingredient through user interaction.
   * If the ingredient does not exist, an error message is displayed.</p>
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
    } catch (Exception e) {
      handleException(e);
    }
  }

  /*
   * Handles the change of price for an ingredient.
   * If the user confirms the operation, the price of the ingredient is updated.
   * If the user chooses to abort the operation, a message is displayed.
   */
  private void handlePriceChange(String name, double newPrice) {
    if (abort()) {
      return;
    }
    foodStorage.updatePrice(name, newPrice);
    outputHandler.printUpdatedPrice(name);
  }

  /**
   * <p>Changes the unit of an ingredient through user interaction.</p>
   * <p>If the ingredient does not exist, an error message is displayed.</p>
   */
  public void changeUnit() {
    try {
      String name = getIngredientNameInput();
      if (!foodStorage.isIngredientExisting(name)) {
        outputHandler.printIngredient(name, false);
      } else {
        String newUnit = getUnitInput();
        handleUnitChange(name, newUnit);
      }
      pressAnyKeyToContinue();
    } catch (Exception e) {
      handleException(e);
    }
  }

  /*
   * Handles the change of unit for an ingredient.
   * If the user confirms the operation, the unit of the ingredient is updated.
   * If the user chooses to abort the operation, a message is displayed.
   * */
  private void handleUnitChange(String name, String newUnit) {
    if (abort()) {
      return;
    }
    foodStorage.updateUnit(name, newUnit);
    outputHandler.printUpdatedUnit(name);
  }

  /**
   * <p>Finds an ingredient by its name.
   * If the ingredient is found, all its details is displayed.
   * If the ingredient does not exist, an error message is displayed.</p>
   */
  public void findIngredient() {
    String ingredientName = getIngredientNameInput();
    Ingredient ingredient = foodStorage.getIngredient(ingredientName);
    if (ingredient != null) {
      outputHandler.printIngredientDetails(
          ingredient.getName(),
          ingredient.getQuantity(),
          ingredient.getUnit(),
          ingredient.getPrice(),
          ingredient.getExpirationDate()
      );
    } else {
      outputHandler.printIngredient(ingredientName, false);
    }
    pressAnyKeyToContinue();
  }


  /**
   * <p>Displays a list of all ingredients present in the foodStorage.
   * This method uses an iterator to retrieve
   * and display the names of all ingredients in the storage.
   * If there are no ingredients in the storage, a message is displayed
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
   * This method uses an iterator to retrieve and sort the ingredient names in alphabetical order.
   * If there are no ingredients in the storage, a message is displayed
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
   * filter the ingredients based on their expiration date.
   * If there are no expired ingredients in the storage, a message is displayed
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
   * in the foodStorage that have given expiration date.
   * If there are no ingredients with the provided expiration date in the storage,
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
   * and then outputs the result. If there are no ingredients in the storage,
   * a message is displayed to inform the user that the storage is empty.</p>
   */
  public void displayValueOfAllIngredients() {
    outputHandler.printValueOfAllIngredients(foodStorage.getValueOfAllIngredients());
    pressAnyKeyToContinue();
  }

  /**
   * <p>Displays the value of all  expired ingredients present in the storage.
   * This method calculates the total value of all expired ingredients
   * based on their price and quantity, and then outputs the result.
   * If there are no expired ingredients in the storage, a message is displayed
   * to inform the user that there are no expired ingredients.</p>
   */
  public void displayValueOfExpiredIngredients() {
    outputHandler.printValueOfExpiredIngredients(foodStorage.getValueOfExpiredIngredients());
    pressAnyKeyToContinue();
  }

  /**
   * <p>Adds a new recipe to the recipe book through user interaction.
   * If the recipe already exists in the recipe book, an error message is displayed.</p>
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
    } catch (Exception e) {
      handleException(e);
    }
  }

  /*
   * Prompts the user for the name of the recipe to be added.
   * If the user provides an empty string, an error message is displayed,
   * and the user is prompted to provide a valid name.
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

  /*
   *Prompts the user for the description of the recipe to be added.
   * If the user provides an empty string, an error message is displayed,
   * and the user is prompted to provide a valid description.
   */
  private String getDescriptionInput() {
    outputHandler.promptForRecipeDescription();
    String description = inputParser.stringInput();
    while (!inputValidator.isNonEmptyString(description)) {
      outputHandler.printInvalidRecipeDescription();
    }
    return description;
  }

  /*
   * Prompts the user for the instructions of the recipe to be added.
   * If the user provides an empty string, an error message is displayed,
   * and the user is prompted to provide valid instructions.
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

  /*
   * Prompts the user for the number of servings for the recipe to be added.
   * If the user provides a value that is zero or negative, an error message is displayed,
   * and the user is prompted to provide a valid number of servings.
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

  /*
   * Handles the addition of a recipe. If the user confirms the operation, the recipe is added.
   * If the user chooses to abort the operation, a message is displayed.
   */
  private void handleRecipeAddition(String nameOfRecipe, String description,
                                    String instructions, double servings) {
    if (abort()) {
      return;
    }
    recipeBook.addRecipe(nameOfRecipe, description, instructions, servings);
    handleAdditionOfIngredients(nameOfRecipe);
    outputHandler.addedRecipe(nameOfRecipe);
  }

  /*
   * Handles the addition of ingredients to a recipe.
   * The user is prompted for the number of ingredients
   * to be added to the recipe, and then prompted for the name,
   * quantity, and unit of each ingredient.
   * If the user provides invalid values, an error message is displayed,
   * and the user is prompted to provide valid values.
   */
  private void handleAdditionOfIngredients(String nameOfRecipe) {
    int numberOfIngredients = getNumberOfIngredientsInput();

    for (int i = 0; i < numberOfIngredients; i++) {
      String ingredientName = getIngredientNameInput();
      double quantity = getQuantityInput();
      String unit = getUnitInput();
      recipeBook.addIngredientToRecipe(nameOfRecipe, ingredientName, quantity, unit);
    }
  }

  /*
   * Prompts the user for the number of ingredients to be added to the recipe.
   * If the user provides a  value that is zero or negative, an error message is displayed,
   * and the user is prompted to provide a valid number of ingredients.
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
      handleException(e);
    }
  }

  /*
   * Handles the removal of a recipe. If the user confirms the operation, the recipe is removed.
   * If the user chooses to abort the operation, a message is displayed.
   */
  private void handleRecipeRemoval(String name) {
    if (abort()) {
      return;
    }
    recipeBook.removeRecipe(name);
    outputHandler.removedRecipe(name);
  }

  /**
   * <p>Finds a recipe by its name.
   * If the recipe is found, all its details are displayed.
   * If the recipe does not exist, an error message is displayed.</p>
   */
  public void findRecipe() {
    String name = getRecipeNameInput();
    Recipe recipe = recipeBook.getRecipe(name);

    if (!recipeBook.isRecipeExisting(name)) {
      outputHandler.printRecipeNotFound(name);
    } else {
      outputHandler.printRecipeDetails(
          recipe.getName(),
          recipe.getDescription(),
          recipe.getInstruction(),
          recipe.getServings());

      recipe.getRequiredIngredients().forEachRemaining(ingredient ->
          outputHandler.printRecipeIngredients(ingredient.getName(),
              ingredient.getQuantity(), ingredient.getUnit())
      );
    }
    pressAnyKeyToContinue();
  }

  /**
   * <p>Displays a list of all recipes present in the recipe book.
   * This method uses an iterator to retrieve and display the names of all recipes in the book.
   * If there are no recipes in the book, a message is displayed
   * to inform the user that the book is empty.</p>
   */
  public void displayRecipes() {
    Iterator<String> recipesIterator = recipeBook.getListOfRecipes();
    boolean hasRecipes = recipesIterator != null && recipesIterator.hasNext();
    outputHandler.printListOfRecipes(recipesIterator, hasRecipes);
    pressAnyKeyToContinue();
  }

  /**
   * <p>Checks if a recipe can be made based on the ingredients present in the storage.
   * If the recipe does not exist, an error message is displayed.</p>
   */
  public void checkIfRecipeCanBeMade() {
    String name = getRecipeNameInput();

    if (!recipeBook.isRecipeExisting(name)) {
      outputHandler.printRecipeNotFound(name);
      pressAnyKeyToContinue();
      return;
    }
    evaluateRecipe(name);
    pressAnyKeyToContinue();
  }

  /*
    * Evaluates the recipe to check if it can be made based on the ingredients
    * present in the storage. An error message is displayed if
    * the recipe has missing ingredients, incompatible units, or
    * insufficient ingredients. If the recipe can be made, a message is displayed
    * to inform the user that the recipe can be made.
   */
  private void evaluateRecipe(String name) {
    Iterator<Ingredient> requiredIngredients = recipeBook.getRecipe(name).getRequiredIngredients();

    while (requiredIngredients.hasNext()) {
      Ingredient required = requiredIngredients.next();
      Ingredient available = foodStorage.getIngredient(required.getName());

      if (recipeBook.hasMissingIngredient(required, foodStorage)) {
        outputHandler.printMissingIngredient(required.getQuantity(),
            required.getUnit(), required.getName());
      } else if (recipeBook.hasUnitMismatch(required, available)) {
        outputHandler.printIncompatibleUnits(required.getName(),
            required.getUnit(), required.getQuantity(), available.getQuantity(),
            available.getUnit());
      } else if (recipeBook.hasInsufficientIngredient(required, foodStorage)) {
        outputHandler.printInsufficientIngredientAmount(available.getQuantity(),
            available.getUnit(), required.getName(), required.getQuantity(), required.getUnit());
      } else {
        outputHandler.printRecipeCanBeMade(name);
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
      handleException(e);
    }
  }

  /*
   * Handles the change of serving size for a recipe.
   * If the user confirms the operation, the serving size of the recipe is updated.
   * If the user chooses to abort the operation, a message is displayed.
   */
  private void handleNewServing(String name, double newServing) {
    if (abort()) {
      return;
    }
    recipeBook.updateServing(name, newServing);
    outputHandler.printUpdatedServing(name);
  }

  /**
   * <p>Displays the main menu to the user and prompts the user to enter a command.
   * The user can choose to perform different operations by entering the corresponding
   * command. If the user enters an invalid command, an error message is displayed,
   * and the user is prompted to enter a valid command.</p>
   */
  public void userInput() {
    outputHandler.printMainMenu();
    boolean running = true;

    while (running) {
      try {
        String userChoice = inputParser.stringInput();
        switch (userChoice.toLowerCase()) {
          case "0" -> running = !exitProgram();
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
          case "/total-val" -> displayValueOfAllIngredients();
          case "/expired-val" -> displayValueOfExpiredIngredients();
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
      } catch (Exception e) {
        handleException(e);
      }
    }
  }
}