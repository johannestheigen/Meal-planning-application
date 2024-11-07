package edu.ntnu.idi.bidata.app;

import edu.ntnu.idi.bidata.inventory.FoodStorage;
import java.time.LocalDate;
import java.util.Iterator;
import java.util.Scanner;

/**
 * The user interface class. Currently in early development stage, and full of
 * hardcode.

 * @author Johannes Nupen Theigen
 * @version 0.0.8
 * @since 11.07.2024
 */
public class UserInterface {

  private FoodStorage foodStorage;

  /**
   * Initialize the application at startup.
   */

  public void init() {
    foodStorage = new FoodStorage();
    foodStorage.addIngredient("Kiwi", "Fruit", 1, "kg", 2, LocalDate.of(2027, 10, 10));
    foodStorage.addIngredient("Apple", "Fruit", 1, "kg", 2, LocalDate.of(2027, 10, 10));
  }

  /**
   * Initializes the application and begins interaction with the user.
   */
  public void start() {
    init();
    showMainMenu();
    Scanner sc = new Scanner(System.in);
    sc.nextInt();
  }

  /**
   * Finds an ingredient when provided a string.

   * @param ingredientName the name ingredient
   */
  public void findIngredient(String ingredientName) {
    String ingredient = foodStorage.getIngredientName(ingredientName);
    if (ingredient != null) {
      System.out.println("Found ingredient: " + foodStorage.getIngredientName(ingredientName));
    }
    System.out.println("Could not find the ingredient!");
  }

  /**
   * Prints a list of all current ingredients.
   */
  public void printListOfIngredients() {
    System.out.println("These are your ingredients: ");
    Iterator<String> iterator = foodStorage.getListOfIngredients();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

  /**
   * Prints a list of all ingredients in
   * alphabetical order.
   */
  public void printListOfIngredientsAlphabetically() {
    System.out.println("These are your ingredients in alphabetical order: ");
    Iterator<String> iterator = foodStorage.getListOfIngredientsAlphabetically();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

  /**
   * Prints list of all expired ingredients.
   */
  public void printListOfExpiredIngredients() {
    System.out.println("You currently have these expired ingredients: ");
    Iterator<String> iterator = foodStorage.getListOfExpiredIngredients();
    while (iterator.hasNext()) {
      System.out.println(iterator.next());
    }
  }

  public void printValueOfAllIngredients() {
    System.out.println("The value of your ingredients is: "
        + foodStorage.getValueOfAllIngredients());
  }

  public void printValueOfExpiredIngredients() {
    System.out.println("The value of your expired ingredients is: "
        + foodStorage.getValueOfExpiredIngredients());
  }

  /**
   * Displays the main menu with different options.
   */
  public void showMainMenu() {
    System.out.println("Welcome to the FoodStorage App. ");
    System.out.println("Press 1 to add an ingredient");
    System.out.println("Press 2 to remove an ingredient");
    System.out.println("Press 3 to view your ingredients");
    System.out.println("Press 4 to view more commands");
    System.out.println("Press 0 to exit");
  }

  /**
   * Displays an extension of the main menu
   * with more options.
   */
  public void showExtendedMenu() {
    System.out.println("Press 5 to view expired ingredients");
    System.out.println("Press 6 to view ingredient by expiration date");
    System.out.println("Press 7 to print value of all ingredients");
    System.out.println("Press 8 to print value of all expired ingredients");
    System.out.println("Press 9 to return to main menu");
  }
}