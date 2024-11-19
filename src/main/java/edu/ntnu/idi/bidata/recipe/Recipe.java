package edu.ntnu.idi.bidata.recipe;

import edu.ntnu.idi.bidata.items.Ingredient;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 * The Recipe class represents a recipe entity that can be stored in a RecipeBook.
 * </p>
 *
 * <p>This class provides methods to: </p>
 * <ul>
 *   <li>Retrieve and update recipe details</li>
 * </ul>
 *
 * <p>Instances of Recipe can be managed within a RecipeBook
 * to maintain an organized collection of recipes.</p>
 *
 * @author Johanens Nupen Theigen
 * @version 0.0.4
 * @since 11.19.2024
 */
public class Recipe {
  private String name;
  private String description;
  private String instruction;
  private Map<String, Ingredient> requiredIngredients;

  /**
   * <p>
   *   Creates a new Recipe that can be stored in a RecipeBook.
   * </p>

   * @param name the name of the recipe
   * @param description the description of the recipe
   * @param instruction the instruction of the recipe
   * @param requiredIngredients the required ingredients for the recipe
   *
   *<p>
     <b>Example of usage: </b>
     <pre><code>Recipe newRecipe = new Recipe("Pancakes","A delicious breakfast treat",
   *                            "1. Mix flour, eggs, and milk",requiredIngredients);</code></pre>
   *</p>
   */
  public Recipe(String name, String description,
                String instruction, Map<String, Ingredient> requiredIngredients) {
    setName(name);
    setDescription(description);
    setInstruction(instruction);
    setRequiredIngredients(requiredIngredients);
  }

  /**
   * <p>Retrieves the name of the recipe.</p>
   *
   * @return the name of the recipe
   */
  public String getName() {
    return name;
  }

  /**
   * <p>
   * Sets the name of the recipe.
   * </p>
   *
   * @param name the name of the recipe
   * @throws IllegalArgumentException if the name is null or empty
   */
  public void setName(String name) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The recipe name cannot be null or empty");
    }
    this.name = name;
  }

  /**
   * <p>
   * Retrieves the description of the recipe.
   * </p>
   *
   * @return the description of the recipe
   */
  public String getDescription() {
    return description;
  }

  /**
   * <p>
   * Sets the description of the recipe.
   * </p>
   *
   * @param description the description of the recipe
   * @throws IllegalArgumentException if the description is null or empty
   */
  public void setDescription(String description) {
    if (description == null || description.isEmpty()) {
      throw new IllegalArgumentException("The recipe description cannot be null or empty");
    }
    this.description = description;
  }

  /**
   * <p>
   * Retrieves the instruction of the recipe.
   * </p>
   *
   * @return the instruction of the recipe
   */
  public String getInstruction() {
    return instruction;
  }

  /**
   * <p>
   * Sets the instruction of the recipe.
   * </p>
   *
   * @param instruction the instruction of the recipe.
   * @throws IllegalArgumentException if the instruction is null or empty.
   */
  public void setInstruction(String instruction) {
    if (instruction == null || instruction.isEmpty()) {
      throw new IllegalArgumentException("The recipe instruction cannot be null or empty");
    }
    this.instruction = instruction;
  }

  /**
   * <p>
   * Return an Iterator that can be used to retrieve
   * the required ingredients for the recipe. It
   * retrives the name, quantity and unit of the ingredient.
   * </p>
   *
   * @return the required ingredients for the recipe
   */
  public Iterator<Map.Entry<String, Ingredient>> getRequiredIngredients() {
    return requiredIngredients.entrySet().iterator();
  }

  /**
   * <p>
   * Sets the required ingredients for the recipe.
   * </p>
   *
   * @param requiredIngredients the required ingredients for the recipe
   * @throws IllegalArgumentException if the required ingredients are null or empty
   */
  public void setRequiredIngredients(Map<String, Ingredient> requiredIngredients) {
    if (requiredIngredients == null || requiredIngredients.isEmpty()) {
      throw new IllegalArgumentException("The recipe must have at least one ingredient");
    }
    this.requiredIngredients = requiredIngredients;
  }

}
