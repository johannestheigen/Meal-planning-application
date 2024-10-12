import java.util.ArrayList;

/**
 * Holds all the ingredients.

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
}
