package edu.ntnu.idi.bidata.register;

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
 * This class provides methods to:
 * <ul>
 *   <li>Add a recipe to the recipe book</li>
 *   <li>Remove a recipe from the recipe book</li>
 *   <li>Add an ingredient to a recipe</li>
 *   <li>Retrieve a recipe from the recipe book</li>
 *   <li>Retrieve a list of recipes in the recipe book</li>
 *
 * </ul>
 *
 * <p>Instances of RecipeBook can be used to manage a collection of recipes.</p>
 *

 * @author Johanens Nupen Theigen
 * @version 0.0.2
 * @since 11.21.2024
 */

public class RecipeBook {
  private final Map<String, Recipe> recipes;

  /**
   * <p>Creates a new instance of RecipeBook with an empty collection of recipes.</p>
   * <p>
   *   <b>Example of usage: </b>
   *
   * <pre><code>RecipeBook recipeBook = new RecipeBook();</code></pre>
   * </p>
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
   *
  <p>
  <b>Example of usage: </b>
  <pre><code>recipeBook.addRecipe("Pancakes", "A delicious breakfast",
  "1. Mix the ingredients. Fry the pancakes",
  4);</code></pre>
  </p>
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
   *
  <p>
  <b>Example of usage: </b>
  <pre><code>recipeBook.removeRecipe("Pancakes");</code></pre>
  </p>
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
   *
  <p>
  <b>Example of usage: </b>
  <pre><code>recipeBook.addIngredientToRecipe("Pancakes", "Flour", 2.0, "dl");</code></pre>
  </p>
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

   * @param recipeName the name of the recipe to retrieve
   * @return the recipe object if the recipe exists, null if the recipe does not exist
   *
  <p>
  <b>Example of usage: </b>
  <pre><code>Recipe recipe = recipeBook.getRecipe("Pancakes");</code></pre>
  <pre><code>System.out.println(recipe.getDescription());</code></pre>
  </p>
   */
  public Recipe getRecipe(String recipeName) {
    return recipes.get(recipeName);
  }

  /**
   * <p>Updates the serving size of a recipe. The required ingredients are updated
   * to match the new serving size.</p>

   * @param recipeName the name of the recipe
   * @param newServing the new serving size
   * @return true if the recipe was found and updated successfully,
     false if the recipe does not exist

     <p>
       <b>Example of usage: </b>
       <pre><code>recipeBook.updateServing("Pancakes", 6);</code></pre>
       </p>
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
   * <p>Return an Iterator that retrieves
   * the names of the recipes in the recipe book.</p>

   * @return an Iterator that retrieves the names of the recipes in the recipe book,
  if the recipe book is empty, the Iterator will not return any elements
   *
  <p>
  <b>Example of usage: </b>
  <pre><code>Iterator&lt;String&gt; recipes = recipeBook.getListOfRecipes();</code></pre>
  <pre><code>while (recipes.hasNext()) {</code></pre>
  <pre><code>  String recipe = recipes.next();</code></pre>
  <pre><code>  System.out.println(recipe);</code></pre>
  <pre><code>}</code></pre>
  </p>
   */
  public Iterator<String> getListOfRecipes() {
    return recipes.keySet().iterator();
  }
}