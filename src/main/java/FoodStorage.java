import java.util.ArrayList;

/**
 * Stores all the ingredients.

 * @author Johannes Nupen Theigen
 * @version 12.10.2024
 */
public class FoodStorage {
  private ArrayList<Ingredient> ingredients;

  /**
   * Creates a storage for ingredients.
   */
  public FoodStorage() {
    ingredients = new ArrayList<>();
  }

  /**
   * Adds an ingredient to the food storage.

   * @param ingredient an ingredient (anything edible) that can be added to the food storage.
   */
  public void addIngredient(Ingredient ingredient) {
    ingredients.add(ingredient);
  }

  /**
   * Removes an ingredient from the food storage.

   * @param ingredient an ingredient (anything edible) that can be removed from the food storage.
   */
  public void removeIngredient(Ingredient ingredient) {
    ingredients.remove(ingredient);
  }

  /**
   * Returns the number of ingredients in the storage.

   * @return the number of ingredients in the storage
   */
  public int getNumberOfIngredients() {
    return ingredients.size();
  }

  /**
   * Lists all available ingredients in the storage.
   */
  public void listIngredients() {
    for (Ingredient ingredient : ingredients) {
      System.out.println(ingredient.getIngredientName());
    }
  }
}