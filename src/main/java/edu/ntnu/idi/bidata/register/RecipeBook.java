package edu.ntnu.idi.bidata.register;

import edu.ntnu.idi.bidata.common.Unit;
import edu.ntnu.idi.bidata.items.Ingredient;
import edu.ntnu.idi.bidata.recipe.Recipe;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>The recipe book class represents a register of recipes. A recipe book
 * The recipes are stored in a HashMap with the recipe name as the key and the
 * Recipe object as value. The Recipe object consists of name, description,
 * instruction, servings, and required ingredients.</p>
 *
 * <p>Instances of RecipeBook can be used to manage a collection of recipes.</p>
 *
 * @author Johanens Nupen Theigen
 * @version 0.0.4
 * @since 11.28.2024
 */
public class RecipeBook {
  private final Map<String, Recipe> recipes;

  /**
   * <p>Creates a new instance of RecipeBook with an empty collection of recipes.</p>
   */
  public RecipeBook() {
    recipes = new HashMap<>();
  }

  /**
   * <p>Adds a recipe to the recipe book. The recipe is stored in the recipe book
   * with the recipe name as the key and the Recipe object as value.</p>

   * @param name the name of the recipe
   * @param description the description of the recipe
   * @param instruction the instruction of the recipe
   * @param servings the amount of people the recipe serves
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
   * <p>Adds an ingredient to a recipe. The ingredient is stored in the recipe.</p>

   * @param recipeName the name of the recipe
   * @param ingredientName the name of the ingredient
   * @param quantity the quantity of the ingredient
   * @param unit the unit of the ingredient
   * @return true if the ingredient was added successfully, false if the recipe does not exist
   */
  public boolean addIngredientToRecipe(String recipeName, String ingredientName,
                                       double quantity, Unit unit) {
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

   * @param recipeName the name of the recipe to retrieve
   * @return the recipe object if the recipe exists, null if the recipe does not exist
   */
  public Recipe getRecipe(String recipeName) {
    return recipes.get(recipeName);
  }

  /**
   * <p>Checks if a recipe exists in the recipe book.</p>

   * @param recipeName the name of the recipe
   * @return true if the recipe exists, false if the recipe does not exist
   */
  public boolean isRecipeExisting(String recipeName) {
    return recipes.containsKey(recipeName);
  }

  /**
   * <p>Updates the serving size of a recipe. The required ingredients are updated
   * to match the new serving size.</p>

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
   * <p>Checks if a recipe can be made with the available ingredients in the food storage.</p>

   * @param recipeName the name of the recipe
   * @param foodStorage the food storage
   * @return true if the recipe can be made, false if the recipe cannot be made
   */
  public boolean canRecipeBeMade(String recipeName, FoodStorage foodStorage) {
    if (!isRecipeExisting(recipeName)) {
      return false;
    }
    Iterator<Ingredient> requiredIngredients = getRecipe(recipeName).getRequiredIngredients();
    while (requiredIngredients.hasNext()) {
      Ingredient requiredIngredient = requiredIngredients.next();
      if (!isIngredientAvailable(requiredIngredient, foodStorage)) {
        return false;
      }
    }
    return true;
  }

  /**
   * <p>Checks if an ingredient is available in the food storage.</p>

   * @param requiredIngredient the required ingredient
   * @param foodStorage the food storage
   * @return true if the ingredient is available, false if the ingredient is missing or insufficient
   */
  public boolean isIngredientAvailable(Ingredient requiredIngredient, FoodStorage foodStorage) {
    return !isIngredientMissing(requiredIngredient, foodStorage)
        && !isIngredientInsufficient(requiredIngredient, foodStorage);
  }

  /**
   * <p>Checks if an ingredient is missing in the food storage.</p>

   * @param requiredIngredient the required ingredient
   * @param foodStorage the food storage
   * @return true if the ingredient is missing, false if the ingredient is available
   */
  public boolean isIngredientMissing(Ingredient requiredIngredient, FoodStorage foodStorage) {
    return !foodStorage.isIngredientExisting(requiredIngredient.getName());
  }

  /**
   * <p>Checks if an ingredient is insufficient in the food storage.</p>

   * @param requiredIngredient the required ingredient
   * @param foodStorage the food storage
   * @return true if the ingredient is insufficient, false if the ingredient is available
   */
  public boolean isIngredientInsufficient(Ingredient requiredIngredient, FoodStorage foodStorage) {
    if (isIngredientMissing(requiredIngredient, foodStorage)) {
      return true;
    }
    Ingredient availableIngredient = foodStorage.getIngredient(requiredIngredient.getName());
    return availableIngredient.getQuantity() < requiredIngredient.getQuantity()
        || !availableIngredient.getUnit().equals(requiredIngredient.getUnit());
  }

  /**
   * <p>Return an Iterator that retrieves
   * the names of the recipes in the recipe book.</p>

   * @return an Iterator that retrieves the names of the recipes in the recipe book,
     if the recipe book is empty, the Iterator will not return any elements
   */
  public Iterator<String> getListOfRecipes() {
    return recipes.keySet().iterator();
  }
}