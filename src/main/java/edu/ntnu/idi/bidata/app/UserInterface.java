package edu.ntnu.idi.bidata.app;

import edu.ntnu.idi.bidata.inventory.FoodStorage;
import edu.ntnu.idi.bidata.items.Ingredient;
import java.time.LocalDate;


/**
 * The user interface class. Currently in early development stage, and full of
 * hardcode.

 * @author Johannes Nupen Theigen
 * @version 0.0.3
 * @since 28.10.2024
 */
public class UserInterface {

  private FoodStorage foodStorage;

  /**
   * Initialize the application at startup.
   */

  public void init() {
    foodStorage = new FoodStorage();
    foodStorage.addTestIngredients();

  }

  /**
   * Initializes the application and begins interaction with the user.
   */
  public void start() {
    init();
    foodStorage.listIngredients();
    foodStorage.listIngredientsAlphabetically();
  }
}
