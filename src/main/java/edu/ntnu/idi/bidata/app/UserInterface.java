package edu.ntnu.idi.bidata.app;

import edu.ntnu.idi.bidata.inventory.FoodStorage;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

/**
 * The user interface class. Currently in early development stage, and full of
 * hardcode.

 * @author Johannes Nupen Theigen
 * @version 0.0.7
 * @since 11.06.2024
 */
public class UserInterface {

  private FoodStorage foodStorage;

  /**
   * Initialize the application at startup.
   */

  public void init() {
    foodStorage = new FoodStorage();
    foodStorage.addIngredient("Kiwi", "Fruit", 1, "kg", 2, LocalDate.of(2027, 10, 10));
  }

  /**
   * Initializes the application and begins interaction with the user.
   */
  public void start() {
    init();
    System.out.println("Welcome to the FoodStorage App. ");
    System.out.println("You currently have: ");
    Iterator<String> iterator = foodStorage.getListOfIngredientsAlphabetically();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
    System.out.println(foodStorage.getValueOfAllIngredients());
    Scanner sc = new Scanner(System.in);
    sc.nextInt();
  }
}