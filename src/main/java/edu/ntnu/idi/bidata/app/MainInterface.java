package edu.ntnu.idi.bidata.app;

/**
 * The main starting point of your application. Let this class create the
 * instance of your main-class that starts your application.
 */
public class MainInterface {
  /**
   * The main method that serves as the entry point for the application.
   * It initializes the UserInterface and starts the interaction process.

   *  @param args command line arguments passed during application execution.
   */
  public static void main(String[] args) {
    UserInterface userInterface = new UserInterface();
    userInterface.init();
    userInterface.start();
  }
}