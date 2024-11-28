package edu.ntnu.idi.bidata.recipe;

import edu.ntnu.idi.bidata.items.Ingredient;
import edu.ntnu.idi.bidata.common.Unit;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * <p>
 * The Recipe class represents a recipe. A recipe consists of name,
 * description, instruction, required ingredients, servings, and
 * required ingredients. The required ingredients are stored in an ArrayList
 * of Ingredient objects. The Ingredient consists of name, quantity, and unit.
 * </p>
 *
 * <p>Instances of Recipe can be managed within a RecipeBook
 * to maintain an organized collection of recipes.</p>
 *
 * @author Johanens Nupen Theigen
 * @version 0.0.8
 * @since 11.28.2024
 */
public class Recipe {
  private String name;
  private String description;
  private String instruction;
  private final ArrayList<Ingredient> requiredIngredients;
  private double servings;

  /**
   * <p>Creates a new instance of Recipe with the specified name, description,
   * instruction, and servings.</p>

   * @param name the name of the recipe
   * @param description the description of the recipe
   * @param instruction the instruction of the recipe
   * @param servings the amount of people the recipe serves
   */

  public Recipe(String name, String description,
                String instruction,
                double servings) {
    setName(name);
    setDescription(description);
    setInstruction(instruction);
    setServings(servings);
    this.requiredIngredients = new ArrayList<>();
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
   * <p>Sets the name of the recipe.</p>
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
   * <p>Retrieves the description of the recipe.</p>
   *
   * @return the description of the recipe
   */
  public String getDescription() {
    return description;
  }

  /**
   * <p>Sets the description of the recipe.</p>
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
   * <p>Retrieves the instruction of the recipe.</p>
   */
  public String getInstruction() {
    return instruction;
  }

  /**
   * <p>Sets the instruction of the recipe.</p>
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
   * <p>Return an Iterator that retrieves
   * the required ingredients for the recipe. It
   * retrieves the name, quantity and unit of the ingredient.</p>
   *
   * @return the required ingredients for the recipe
   */
  public Iterator<Ingredient> getRequiredIngredients() {
    return requiredIngredients.iterator();
  }

  /**
   * <p>Adds an ingredient to the required ingredients for the recipe.</p>

   * @param name the name of the ingredient
   * @param quantity the quantity of the ingredient
   * @param unit the unit of the ingredient
   * @throws IllegalArgumentException if the name is null or empty,
     the quantity is less than or equal to 0, or the unit is null or empty.
   */
  public void addIngredient(String name, double quantity, Unit unit) {
    if (name == null || name.isEmpty()) {
      throw new IllegalArgumentException("The ingredient name cannot be null or empty");
    }
    if (quantity <= 0) {
      throw new IllegalArgumentException("The quantity must be greater than 0");
    }
    if (unit == null) {
      throw new IllegalArgumentException("The unit cannot be null or empty");
    }
    requiredIngredients.add(new Ingredient(name, quantity, unit));
  }

  /**
   * <p>
   *   Retrieves the amount of people the recipe serves.
   *   </p>

   * @return the amount of people the recipe serves
   */
  public double getServings() {
    return servings;
  }

  /**
   * <p>
   *   Sets the amount of people the recipe serves.
   *   </p>

   * @param servings the amount of people the recipe serves
   */
  public void setServings(double servings) {
    if (servings <= 0) {
      throw new IllegalArgumentException("The amount of people must be greater than 0");
    }
    this.servings = servings;
  }
}