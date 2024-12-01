package edu.ntnu.idi.bidata.app;

/**
 * <p>The main starting point of the application. Let this class create the
 * instance of your main-class that starts your application.</p>

 * @author Johannes Nupen Theigen
 * @version 0.0.5
 * @since 12.01.2024
 */
public class Main {
  /**
   * <p>The main method that serves as the entry point for the application.
   * It initializes the UserInterface and starts the interaction process
   * with the user.</p>

   * @param args command line arguments passed during application execution.
   */
  public static void main(String[] args) {
    UserInterface userInterface = new UserInterface();
    userInterface.start();
  }
}