package edu.ntnu.idi.bidata.register;

import edu.ntnu.idi.bidata.items.Ingredient;
import edu.ntnu.idi.bidata.recipe.Recipe;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>The recipe book class represents a register of recipes.
 * The recipes are stored in a HashMap where the key is the name of the recipe
 * and the the value is the Recipe object.</p>
 *
 * @author Johanens Nupen Theigen
 * @version 0.0.4
 * @since 12.02.2024
 */

public class RecipeBook {
  private final Map<String, Recipe> recipes;

  /**
   * <p>Initializes a new Instance of the RecipeBook class.
   * with an empty collection of recipes.</p>
   */
  public RecipeBook() {
    recipes = new HashMap<>();
  }

  /**
   * <p>Adds a new recipe to the recipe book.</p>
   * <p>The recipe is stored in a HashMap where the key is the name of the recipe
   * and the value is the Recipe object.</p>
   *
   * @param name        the name of the recipe (e.g. "Chocolate Cake")
   * @param description the description of the recipe (e.g. "A delicious cake")
   * @param instruction the instruction of the recipe (e.g. "Mix all ingredients together")
   * @param servings    the amount of people the recipe serves (e.g. 4)
   * @return true if the recipe was added successfully, false if the recipe already exists
   */
  public boolean addRecipe(String name, String description, String instruction, double servings) {
    if (recipes.containsKey(name)) {
      return false;
    }
    Recipe newRecipe = new Recipe(name, description, instruction, servings);
    recipes.put(name, newRecipe);
    return true;
  }

  /**
   * <p>Removes a recipe from the recipe book.</p>
   * <p>The recipe is removed from the recipe book using the name of the recipe.</p>
   *
   * @param name the name of the recipe to remove
   * @return true if the recipe was removed successfully, false if the recipe does not exist
   */
  public boolean removeRecipe(String name) {
    if (recipes.containsKey(name)) {
      recipes.remove(name);
      return true;
    } else {
      return false;
    }
  }

  /**
   * <p>Adds a new ingredient to the recipe.
   * The ingredients are stored in an ArrayList in the Recipe object.</p>

   * @param recipeName     the name of the recipe (e.g. "Chocolate Cake")
   * @param ingredientName the name of the ingredient (e.g. "Flour")
   * @param quantity       the quantity of the ingredient (e.g. 2)
   * @param unit           the unit of the ingredient (e.g. "kg")
   * @return true if the ingredient was added successfully, false if the recipe does not exist
   */
  public boolean addIngredientToRecipe(String recipeName, String ingredientName,
                                       double quantity, String unit) {
    Recipe existingRecipe = recipes.get(recipeName);
    if (existingRecipe == null) {
      return false;
    } else {
      existingRecipe.addIngredient(ingredientName, quantity, unit);
      return true;
    }
  }

  /**
   * <p>Retrieves a recipe from the recipe book.</p>
   * <p>The recipe is retrieved from the recipe book using the name of the recipe.</p>
   *
   * @param recipeName the name of the recipe to retrieve
   * @return the recipe object if the recipe exists, null if the recipe does not exist
   */
  public Recipe getRecipe(String recipeName) {
    return recipes.get(recipeName);
  }

  /**
   * <p>Checks if a recipe exists in the recipe book.</p>
   * <p>The recipe is checked for existence in the recipe book using the name of the recipe.</p>
   *
   * @param recipeName the name of the recipe
   * @return true if the recipe exists, false if the recipe does not exist
   */
  public boolean isRecipeExisting(String recipeName) {
    return recipes.containsKey(recipeName);
  }

  /**
   * <p>Updates the serving size of a recipe. The quantity of
   * the required ingredients are updated to match the new serving size.</p>
   *
   * @param recipeName the name of the recipe
   * @param newServing the new serving size
   * @return true if the recipe was found and updated successfully,
     false if the recipe does not exist
   */
  public boolean updateServing(String recipeName, double newServing) {
    boolean recipeFound = false;
    Recipe existingRecipe = recipes.get(recipeName);
    if (existingRecipe != null) {
      recipeFound = true;
      double oldServing = existingRecipe.getServings();
      Iterator<Ingredient> requiredIngredients = existingRecipe.getRequiredIngredients();

      while (requiredIngredients.hasNext()) {
        Ingredient requiredIngredient = requiredIngredients.next();
        double newQuantity = requiredIngredient.getQuantity() * newServing / oldServing;
        requiredIngredient.setQuantity(newQuantity);
      }
      existingRecipe.setServings(newServing);
    }
    return recipeFound;
  }

  /**
   * <p>Checks if the required ingredients for a recipe are available in the food storage.</p>

   * @param requiredIngredient the required ingredient
   * @param foodStorage the food storage
   * @return true if the required ingredients are available,
     false if the required ingredients are missing
   */
  public boolean hasMissingIngredient(Ingredient requiredIngredient, FoodStorage foodStorage) {
    return !foodStorage.isIngredientExisting(requiredIngredient.getName());
  }

  /**
   * <p>Checks if the unit of the required ingredient and the available ingredient
   * are mismatched.</p>

   * @param required the required ingredient (e.g. "Flour", 4, "kg",)
   * @param available the available ingredient (e.g. "Flour", 2, "kg")
   * @return true if the unit of the required ingredient
     and the available ingredient are mismatched, false if the units match
   */
  public boolean hasUnitMismatch(Ingredient required, Ingredient available) {
    return !required.getUnit().equalsIgnoreCase(available.getUnit());
  }

  /**
   * <p>Checks if the the quantity of the available ingredient is
   * less than the required quantity.</p>

   * @param requiredIngredient the required ingredient (e.g. "Flour", 4, "kg")
   * @param foodStorage the food storage
   * @return true if the quantity of the available ingredient is less than the required quantity,
     false if the quantity of the available ingredient is greater than
     or equal to the required quantity
   */
  public boolean hasInsufficientIngredient(Ingredient requiredIngredient, FoodStorage foodStorage) {
    if (hasMissingIngredient(requiredIngredient, foodStorage)) {
      return true;
    }
    Ingredient availableIngredient = foodStorage.getIngredient(requiredIngredient.getName());
    return availableIngredient.getQuantity() < requiredIngredient.getQuantity();
  }

  /**
   * <p>Returns an Iterator that retrieves
   * the names of the recipes in the recipe book.</p>
   *
   * @return an Iterator that retrieves the names of the recipes in the recipe book,
     if the recipe book is empty, the Iterator will not return any elements
   */
  public Iterator<String> getListOfRecipes() {
    return recipes.keySet().iterator();
  }
}