package edu.ntnu.idi.bidata.app;

/**
 * <p>The main starting point of the application. Let this class create the
 * instance of your main-class that starts your application.</p>

 * @author Johannes Nupen Theigen
 * @version 0.0.6
 * @since 12.03.2024
 */
public class Main {

  /**
   * <p>The main method that serves as the entry point for the application.
   * It initializes an instance of the UserInterface class
   * and starts the interaction with the user.</p>

   * @param args the arguments passed to the application
   */
  public static void main(String[] args) {
    UserInterface userInterface = new UserInterface();
    userInterface.start();
  }
}